package verification;

import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.asserts.SoftAssert;
import util.ActionUtils;

//TODO: check for throwing exception in failure cases
public class Verification {

private static SoftAssert softAssert = new SoftAssert();

public static void verifyTrue(Boolean condition){

    softAssert.assertTrue(condition);
}

    public static void verifyText(MobileElement element, String expected){
        try{
            softAssert.assertEquals(element.getText(),expected);
        }catch (AssertionError e){
            e.fillInStackTrace().printStackTrace();
            System.out.println("Error occured while processing");
        }
    }

    @AfterSuite
    public void assertAll(MobileElement element, String expected){
        softAssert.assertAll();
    }

}
