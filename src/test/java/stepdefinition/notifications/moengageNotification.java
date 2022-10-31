package stepdefinition.notifications;

import api.ApiExecutor;
import api.ApiResponse;
import apiRequest.moengage.SendPushRequest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import modals.NeverMissCustomerModal;
import modals.ReportedListingModal;
import org.hamcrest.Matchers;
import org.testng.Assert;
import pages.ChatInboxPage;
import pages.onboarding.WelcomePage;
import pages.sellerAppPages.LoginPage;
import util.ActionUtils;

public class moengageNotification {

    @And("Chat Inbox notification is sent on {string} and {string}")
    public void chatNotificationIsSent(String number, String title) throws InterruptedException {
        Thread.sleep(3000);
        SendPushRequest sendPushRequest = new SendPushRequest();
        sendPushRequest.setKeyValue("category", "CHAT_INBOX");
        sendPushRequest.setUserAttribute(number);
        sendPushRequest.setTitle(title);
        sendPushRequest.setSignature();
        new ApiExecutor(sendPushRequest)
                .execute()
                .validatableResponse()
                .statusCode(200)
                .spec(ApiResponse.SUCCESS())
                .spec(new ResponseSpecBuilder().expectBody("responseId", Matchers.notNullValue()).build());
    }

    @When("User clicks on notification having {string}")
    public void userClicksOnNotification(String title) {
        ActionUtils.openHousingNotification(title);
    }
    @And("Chat Inbox should be visible")
    public void chatInboxShouldBeVisible() {
        ChatInboxPage chatInboxPage = new ChatInboxPage();
        Assert.assertEquals(chatInboxPage.getName(1) ,"Mohammed Rabban Ansari");
    }

}
