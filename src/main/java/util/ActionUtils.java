package util;

import configs.DriverConfig;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// This is general actions util class in which actions that can be performed on a element can be added and used throughout across the project
public class ActionUtils {

 //This function is used to add wait condition for any element
    public static void waitForVisibilityOf(MobileElement element)
    {
        WebDriverWait wait = new WebDriverWait(DriverConfig.getDriver(), 30 );
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    //This function is used for checking the existance of any element
    boolean isElementPresent(MobileElement element, int timeout){
        try{
            WebDriverWait wait = new WebDriverWait(DriverConfig.getDriver(), timeout);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        }catch(Exception e){
            return false;
        }
    }

    //This function performs click operation on a mobile element once it is visible
    public static void clickButton(MobileElement element){
        waitForVisibilityOf(element);
        element.click();
    }

    //This function sends a text to a mobile element once it is visible
    public static void sendText(MobileElement element, String text){
        waitForVisibilityOf(element);
//        Thread.sleep(20000);
        element.sendKeys(text);
    }

}
