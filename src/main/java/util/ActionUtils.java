package util;

import configs.DriverConfig;
import enums.LocatorsEnum;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en_old.Ac;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;


// This is general actions util class in which actions that can be performed on a element can be added and used throughout across the project
public class ActionUtils {

    private static final Logger logger = LogManager.getLogger(ActionUtils.class);


    //This function is used to add wait condition for any element
    public static void waitForVisibilityOf(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(DriverConfig.getDriver(), 120);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            logger.error("{} element is not present ", element.getText());
            logger.error(e.getStackTrace());
            e.printStackTrace();
        }
    }


    //This function is used for checking the existence of any element
    public static boolean isElementPresent(MobileElement element, int timeout) {
        WebDriverWait webDriverWait = new WebDriverWait(DriverConfig.getDriver(), timeout);
        try {
            logger.info("Waiting for visibility of " + element.getText());
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            logger.error("{} element is not present: ", element.getText());
            logger.error(e.getStackTrace());
            e.printStackTrace();
            return false;
        }
    }

    //TODO: Ask to add text on button elements
    //TODO: Correct logger
    //This function performs click operation on a mobile element once it is visible
    public static void clickButton(MobileElement element) {
        waitForVisibilityOf(element);
        logger.info("Performing click operation on {}", element.getId());
        element.click();
    }

    //This function sends a text to a mobile element once it is visible
    public static void sendText(MobileElement element, String text) {
        waitForVisibilityOf(element);
        logger.info("Entering {} in \'{}\' textbox", text, element.getText());
        element.sendKeys(text);
    }

    public static MobileElement androidScroll(String attribute, String value) {
        return (MobileElement) ((FindsByAndroidUIAutomator) DriverConfig.getDriver()).findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
                        + "new UiSelector()." + attribute + "(\"" + value + "\"));");
    }

    private static MobileElement androidScroll(MobileElement element, String direction) throws Exception {
        Dimension size = DriverConfig.getDriver().manage().window().getSize();
        int startX = (int) (size.width * 0.5);
        int endX = (int) (size.width * 0.5);
        int startY = 0;
        int endY = 0;
        boolean isFound = false;

        switch (direction) {
            case "up":
                endY = (int) (size.height * 0.4);
                startY = (int) (size.height * 0.6);
                break;

            case "down":
                endY = (int) (size.height * 0.6);
                startY = (int) (size.height * 0.4);
                break;

            case "left":
                startY = (int) (size.height * 0.5);
                endY = (int) (size.height * 0.5);
                startX = (int) (size.width * 0.4);
                endX = (int) (size.width * 0.6);
                break;

            case "right":
                startY = (int) (size.height * 0.5);
                endY = (int) (size.height * 0.5);
                startX = (int) (size.width * 0.6);
                endX = (int) (size.width * 0.4);
                break;

        }

        for (int i = 0; i < 20; i++) {
            if (find(element, 1)) {
                isFound = true;
                break;
            } else {
                swipe(startX, startY, endX, endY, 1000);
            }
        }
        if(!isFound){
            return null;
        }
        return element;
    }


    private static MobileElement androidScrollUsingElement(MobileElement startElement, MobileElement endElement, String direction) throws Exception {

        Dimension startElementSize = startElement.getSize();
        Dimension screenSize = DriverConfig.getDriver().manage().window().getSize();
        int startX = 0;
        int endX = 0;
        int startY = (int) ((startElementSize.height * 0.5) + startElement.getLocation().getY());
        int endY = (int) ((startElementSize.height * 0.5) + startElement.getLocation().getY());
        boolean isFound = false;

        switch (direction) {
            case "left":
                startX = (int) (screenSize.width * 0.2);
                endX = (int) (screenSize.width * 0.8);
                break;

            case "right":
                startX = (int) (screenSize.width * 0.8);
                endX = (int) (screenSize.width * 0.2);
                break;
        }
        for (int i = 0; i < 20; i++) {

            if (find(endElement, 1)) {
                return endElement;
            } else {
            swipe(startX, startY, endX, endY, 1000);
            }
        }
        if(!isFound){
            throw new Exception("Element not found");
        }
        return endElement;
    }


    private static boolean find(final MobileElement element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverConfig.getDriver(), timeout);
            return wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver driver) {
                    if (element.isDisplayed()) {
                        return true;
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            return false;
        }
    }

    //TODO: Add scroll method for ios
    public static MobileElement scroll(MobileElement element, String direction) {
        if (DriverConfig.getDriver() instanceof AndroidDriver) {
            try {
                return androidScroll(element, direction);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    //TODO: Add scroll method for ios
    public static MobileElement scrollUsingElement(MobileElement startElement, MobileElement endElement, String direction) {
        if (DriverConfig.getDriver() instanceof AndroidDriver) {
            try {
                return androidScrollUsingElement(startElement, endElement, direction);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static MobileElement scroll(String attribute, String value) {
        if (DriverConfig.getDriver() instanceof AndroidDriver) {
            try {
                return androidScroll(attribute, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static void dropModal(MobileElement modalContainer, MobileElement startPoint) {
        int anchor = startPoint.getLocation().getX() + (startPoint.getSize().getWidth() / 2);
        int start = startPoint.getLocation().getY() + (startPoint.getSize().getHeight() / 2);
        int end = startPoint.getLocation().getY() + (startPoint.getSize().getHeight() / 2) + modalContainer.getSize().height - 100;
        new TouchAction(DriverConfig.getDriver())
                .press(point(anchor, start))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(anchor, end))
                .release().perform();
    }

    public static void gestureBack() {
        DriverConfig.getDriver().navigate().back();
    }

    public static MobileElement elementWithMatchingText(List<MobileElement> elementsList, String text) {
        for (MobileElement i : elementsList) {
            if (i.getText().equalsIgnoreCase(text))
                return i;
        }
        return null;
    }

    public static MobileElement elementWithMatchingText(String text) {
        return  (MobileElement) DriverConfig.getDriver().findElement(MobileBy.xpath("//android.widget.TextView" +
                "[contains(text(),‘" + text + "’)]"));
    }

    public static MobileElement elementWithPartialMatchingText(List<MobileElement> elementsList, String text) {
        for (MobileElement i : elementsList) {
            if (i.getText().toLowerCase().contains(text.toLowerCase()))
                return i;
        }
        return null;
    }

    public static MobileElement findElementBylocators(String locatorsEnum, String value){
        switch (locatorsEnum)
        {
            case "xpath":
                return (MobileElement) DriverConfig.getDriver().findElement(MobileBy.xpath(value));
            case "name":
                return (MobileElement) DriverConfig.getDriver().findElement(MobileBy.name(value));
            case "AccessibilityId":
                return (MobileElement) DriverConfig.getDriver().findElement(MobileBy.AccessibilityId(value));
            case "className":
                return (MobileElement) DriverConfig.getDriver().findElement(MobileBy.className(value));
            case "id":
                return (MobileElement) DriverConfig.getDriver().findElement(MobileBy.id(value));
            case "linkText":
                return (MobileElement) DriverConfig.getDriver().findElement(MobileBy.linkText(value));
            case "partialLinkText":
                return (MobileElement) DriverConfig.getDriver().findElement(MobileBy.partialLinkText(value));
        }
        throw new RuntimeException("attribute type not supported/added");
    }

    public static MobileElement getToast(String errorMessageEnums, int timeOutInSeconds) {
        try {
            long start = System.currentTimeMillis();
            long end = start + timeOutInSeconds * 1000;
            while (DriverConfig.getDriver().findElements(MobileBy.xpath("//*[@text=\'"+errorMessageEnums+"\']")).
                    size() == 0 || System.currentTimeMillis() < end){}
            return findElementBylocators(LocatorsEnum.XPATH.value(), "//*[@text=\'"+errorMessageEnums+"\']");
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            e.printStackTrace();
        }
        return null;
    }

    public static void dragOperation(MobileElement startElement, MobileElement endElement) {
        int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
        int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);
        int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
        int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);

        TouchAction touchAction = new TouchAction(DriverConfig.getDriver());
                touchAction.press(point(startX,startY))
                        .moveTo(point(endX,  endY))
                        .release();
    }

    //TODO : ADD housing notification function for ios

    public static void openHousingNotification(String title){
        if (DriverConfig.getDriver() instanceof AndroidDriver)
            openAndroidNotification1(title);
    }

    private static void openAndroidNotification(String title) {
        AndroidDriver driver = (AndroidDriver) DriverConfig.getDriver();
        boolean isNotificationFound = false;
        driver.openNotifications();
        List<MobileElement> notifications = driver.findElements(MobileBy.id("android:id/title"));
        for (MobileElement list : notifications) {
            String extractTitle = list.getText();
            if (extractTitle.equalsIgnoreCase(title)) {
                list.click();
                isNotificationFound = true;
                break;
            }
        }
        if (!isNotificationFound)
            throw new RuntimeException("Desired Notification not found");
    }

    public static MobileElement retryFindElement( By by) {
        int attempts = 0;
        while(attempts < 10) {
            try {
                DriverConfig.getDriver().findElement(by);
                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
        return (MobileElement) DriverConfig.getDriver().findElement(by);
    }

    public static List<MobileElement> retryFindElements(By by) {
        int attempts = 0;
        while(attempts < 3) {
            try {
                DriverConfig.getDriver().findElements(by);
                break;
            } catch(StaleElementReferenceException e) {
            }
            attempts++;
        }
        return DriverConfig.getDriver().findElements(by);
    }

    private static void openAndroidNotification1(String title) {
        AndroidDriver driver =
                (AndroidDriver) DriverConfig.getDriver();
        boolean isNotificationFound = false;
        driver.openNotifications();
        List<MobileElement> notifications ;

        int i=0;
        while (isNotificationFound!=true) {

            notifications = retryFindElements(MobileBy.id("android:id/title"));
            if (notifications.size() != 0) {
                if (notifications.get(i).getText().equalsIgnoreCase(title)) {
                    notifications.get(i).click();
                    isNotificationFound = true;
                    break;
                }
                else
                    i++;
            }

            if (i == notifications.size())
                i = 0;

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (!isNotificationFound)
            throw new RuntimeException("Desired Notification not found");
    }

    private static void swipe(int startX, int startY, int endX, int endY, int millis)
            throws InterruptedException {
        TouchAction t = new TouchAction(DriverConfig.getDriver());
        t.press(point(startX, startY)).waitAction(waitOptions(ofMillis(millis))).moveTo(point(endX, endY)).release()
                .perform();
    }


    public static boolean isElementDisplayed(MobileElement element){
        try{
            waitForVisibilityOf(element);
            return element.isDisplayed();
        }catch(Exception e){
            //System.out.println(e);
            return false;
        }
    }

    public static void closeApp(String bundleId){
        DriverConfig.getDriver().terminateApp(bundleId);
    }

    public static void openApp(String bundleId){
        DriverConfig.getDriver().activateApp(bundleId);
    }


}