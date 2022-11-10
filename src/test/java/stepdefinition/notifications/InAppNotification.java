package stepdefinition.notifications;

import apiRequest.quickblox.ChatDialogRequest;
import apiRequest.quickblox.SessionRequest;
import apiResponse.chatDialogResponse.ChatDialogResponse;
import configs.DriverConfig;
import enums.ChatUserEnums;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pages.ChatInboxPage;
import util.ActionUtils;

public class InAppNotification {
    @And("User Kills the app")
    public void userKillsTheApp() throws InterruptedException {
        Thread.sleep(1000);
        ActionUtils.closeApp("com.locon.housing.alpha");

    }

    @When("User opens the app again")
    public void userOpensTheAppAgain() {
        ActionUtils.openApp("com.locon.housing.alpha");
    }


    @And("User clicks on MyChats button")
    public void userClicksOnMyChatsButton() throws InterruptedException {
        Thread.sleep(5000);
        ActionUtils.clickButton(ActionUtils.retryFindElement(MobileBy.xpath("//android.widget.Button[@text='Chat now!']")));
        MobileElement androidAlways = ActionUtils.findElementBylocators("id", "android:id/button_always");
        if (androidAlways!= null)
        ActionUtils.clickButton(androidAlways);
    }



}
