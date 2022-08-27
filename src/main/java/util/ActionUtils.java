package util;

import configs.DriverConfig;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.appium.java_client.MobileElement;
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
        WebDriverWait wait = new WebDriverWait(DriverConfig.getDriver(), 30);
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
            if (i.getText().equals(text))
                return i;
        }
        return null;
    }

    public static MobileElement elementWithMatchingText(List<MobileElement> elementsList, int index) {
        return elementsList.get(index);
    }

    //TODO: Function not working as expected
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