package stepdefinition.notifications;

import api.ApiExecutor;
import api.ApiResponse;
import apiRequest.moengage.SendPushRequest;
import apiRequest.quickblox.ChatDialogRequest;
import apiRequest.quickblox.MessageRequest;
import apiRequest.quickblox.SessionRequest;
import apiResponse.chatDialogResponse.ChatDialogResponse;
import apiResponse.messageResponse.MessageResponse;
import configs.DriverConfig;
import enums.ChatUserEnums;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import modals.NeverMissCustomerModal;
import modals.ReportedListingModal;
import org.hamcrest.Matchers;
import org.testng.Assert;
import pages.ChatInboxPage;
import pages.ChatThreadPage;
import pages.onboarding.WelcomePage;
import pages.sellerAppPages.LoginPage;
import util.ActionUtils;

public class moengageNotification {

    @And("Chat Inbox notification is sent on {string} and {string}")
    public void chatNotificationIsSent(String number, String title) throws InterruptedException {
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
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ActionUtils.openHousingNotification(title);
    }
    @And("Chat Inbox of {string} for {string} role should be visible")
    public void chatInboxShouldBeVisible(String name, String role) {
        ChatInboxPage chatInboxPage = new ChatInboxPage();
        ChatDialogRequest chatDialogRequest = new ChatDialogRequest();
        SessionRequest.createSession(ChatUserEnums.valueOf(name).getValue());
        ChatDialogResponse chatDialogResponse = chatDialogRequest.getChatDialogMessages();
        chatInboxPage.verifyChatCardData(chatDialogResponse, role);

    }

    @And("Seller waits for chat notification from {string}")
    public void sellerWaitsForChatNotificationFrom(String arg0) {

        ActionUtils.closeApp("com.locon.housing");
        ActionUtils.openApp("com.locon.housing");
    }

    @Then("Chat screen is visible to User for {string}")
    public void chatScreenIsVisibleToUser(String name) {
        ChatThreadPage chatThreadPage = new ChatThreadPage();
        SessionRequest.createSession(ChatUserEnums.valueOf(name).getValue());
        MessageResponse messageResponse = new MessageRequest()
                .getMessages(new ChatDialogRequest().getChatDialogMessages().getItems().get(0).get_id());
        chatThreadPage.verifyChatMessages(messageResponse);
    }
}
