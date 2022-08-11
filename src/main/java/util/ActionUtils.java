package util;

import configs.DriverConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// This is general actions util class in which actions that can be performed on a element can be added and used throughout across the project
public class ActionUtils {

    private static final Logger logger = LogManager.getLogger(ActionUtils.class);


    //This function is used to add wait condition for any element
    public static void waitForVisibilityOf(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(DriverConfig.getDriver(), 30);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            logger.error((element.getText() + " element is not present: "));
            e.printStackTrace();
        }
    }


        //This function is used for checking the existence of any element
        public static boolean isElementPresent(MobileElement element, int timeout){
        WebDriverWait webDriverWait = new WebDriverWait(DriverConfig.getDriver(), timeout);
        try{
            logger.info("Waiting for visibility of "+ element.getText());
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
            return true;
        }catch(Exception e){
            logger.error(element.getText() + " element is not present: ");
            e.printStackTrace();
            return false;
        }
    }

        //This function performs click operation on a mobile element once it is visible
        public static void clickButton(MobileElement element){
        waitForVisibilityOf(element);
        logger.info("Performing click operation on "+ element.getText());
        element.click();
    }

        //This function sends a text to a mobile element once it is visible
        public static void sendText(MobileElement element, String text){
        waitForVisibilityOf(element);
        logger.info("Entering " + text + "in " + element.getText() + "textbox");
        element.sendKeys(text);
    }

    }
