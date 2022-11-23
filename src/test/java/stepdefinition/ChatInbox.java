package stepdefinition;

import api.ApiExecutor;
import apiRequest.quickblox.ChatDialogRequest;
import apiRequest.quickblox.SessionRequest;
import apiResponse.chatDialogResponse.ChatDialogResponse;
import enums.ChatUserEnums;
import enums.ErrorMessageEnums;
import enums.LocatorsEnum;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_old.Ac;
import modals.LoginModal;
import org.testng.Assert;
import pages.BuyerTabs;
import pages.ChatFloatingCta;
import pages.ChatInboxPage;
import pages.buyerAppPages.HomePage;
import pages.buyerAppPages.ProfilePage;
import pages.buyerAppPages.SearchLocalityPage;
import pages.buyerAppPages.SerpPage;
import util.ActionUtils;
import utils.AppUtil;

public class ChatInbox {

    HomePage homePage = new HomePage();
    ChatFloatingCta chatFloatingCta = new ChatFloatingCta();
    BuyerTabs tabs = new BuyerTabs();
    ProfilePage profilePage = new ProfilePage();
    SearchLocalityPage searchLocalityPage = new SearchLocalityPage();

     @Given("User login on seller app")
        public void userLoginOnSellerApp() {

      }

    @And("User lands on <tab> with <service> selected")
    public void userLandsOnTab() {

    }


    @When("User clicks on chat floating cta")
        public void userClicksOnChatFloatingcta() {
         Assert.assertTrue(ActionUtils.isElementDisplayed(chatFloatingCta.getFloatingCta()));
         ActionUtils.clickButton(chatFloatingCta.getFloatingCta());
     }

    @Then("Chat Inbox Opens")
        public void loginFailedWithToast() throws InterruptedException {
        }

    @And("Chat card for the user is visible")
    public void ChatCardIsVisible() {

    }

    @And("Buyer selects service {string}")
    public void buyerSelectsService(String serviceName) {
        switch(serviceName){
            case "plot": {
                ActionUtils.scrollUsingElement(homePage.getBuy(), homePage.getPlots(), "right");
                ActionUtils.clickButton(homePage.getPlots());
                break;
            }
            case "buy": {
                    ActionUtils.clickButton(homePage.getBuy());
                    break;
                }
            case "rent": {
                    ActionUtils.clickButton(homePage.getRent());
                    break;
                }
            case "commercial": {
                ActionUtils.scrollUsingElement(homePage.getBuy(), homePage.getCommercial(), "right");
                ActionUtils.clickButton(homePage.getCommercial());
                break;
            }
            case "pg": {
                ActionUtils.scrollUsingElement(homePage.getBuy(), homePage.getPg_co_living(), "right");
                ActionUtils.clickButton(homePage.getPg_co_living());
                break;
            }
        }
//        ActionUtils.waitForVisibilityOf(searchLocalityPage.getSearchTextBox());
        MobileElement LandmarkSearch = ActionUtils.findElementBylocators(LocatorsEnum.XPATH.value(), "//android.widget.TextView[@text='Ok, got it']");
        if (LandmarkSearch != null)
            ActionUtils.clickButton(LandmarkSearch);
        ActionUtils.clickButton(searchLocalityPage.getBack());
//        ActionUtils.gestureBack();
    }

    @Then("Chat floating cta should be absent")
    public void chatFloatingCtaShouldBeAbsent() {
         Assert.assertFalse(ActionUtils.isElementDisplayed(chatFloatingCta.getFloatingCta()));
    }

    @And("Buyer is on profile page")
    public void buyerIsOnProfilePage() {
        ActionUtils.clickButton(tabs.getProfileTab());
    }

    @Then("Buyer clicks on My Chats")
    public void buyerClicksOnMyChats() {
        ActionUtils.scroll(profilePage.getMyChats(),"up");
        ActionUtils.clickButton(profilePage.getMyChats());
     }

    @And("User should land on desired chat inbox of {string} for {string}")
    public void userShouldLandOnDesiredChatInboxOfFor(String name, String role) {
        ChatInboxPage chatInboxPage = new ChatInboxPage();
        ChatDialogRequest chatDialogRequest = new ChatDialogRequest();
        SessionRequest.createSession(ChatUserEnums.valueOf(name).getValue());
        ChatDialogResponse chatDialogResponse = chatDialogRequest.getChatDialogMessages();
        chatInboxPage.verifyChatCardDataInView(name, chatDialogResponse, role);
        Assert.assertTrue(ActionUtils.isElementDisplayed(chatInboxPage.getMyChatsText()));
        Assert.assertTrue(ActionUtils.isElementDisplayed(chatInboxPage.getExploreMoreProperties()));
        Assert.assertTrue(ActionUtils.isElementDisplayed(chatInboxPage.getBack()));
    }
}

