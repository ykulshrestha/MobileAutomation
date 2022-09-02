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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static io.appium.java_client.touch.offset.PointOption.point;


// This is general actions util class in which actions that can be performed on a element can be added and used throughout across the project
public class ActionUtils {

    private static final Logger logger = LogManager.getLogger(ActionUtils.class);


    //This function is used to add wait condition for any element
    public static void waitForVisibilityOf(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(DriverConfig.getDriver(), 1000);
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

    //TODO: Add scroll method for ios
    public static MobileElement scroll(String attribute, String value) {
        if (DriverConfig.getDriver() instanceof AndroidDriver)
            return androidScroll(attribute, value);
        return null;
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

}