package stepdefinition.chats.ChatEntryPointsOnBuyer;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_old.Ac;
import modals.NeverMissCustomerModal;
import modals.ReportedListingModal;
import org.testng.Assert;
import pages.ChatInboxPage;
import pages.ChatThreadPage;
import pages.onboarding.WelcomePage;
import pages.sellerAppPages.HomePage;
import pages.sellerAppPages.LoginPage;
import util.ActionUtils;

public class SellerVerification {

    ChatThreadPage chatThreadPage = new ChatThreadPage();
    ChatInboxPage chatInboxPage = new ChatInboxPage();


    @Given("seller logged in using {string} and {string} on seller App")
    public void sellerLoggedInUsingAndOnSellerApp(String number, String password) {
        WelcomePage welcomePage = new WelcomePage();
        LoginPage loginPage = new LoginPage();
        NeverMissCustomerModal neverMissCustomerModal = new NeverMissCustomerModal();
        ReportedListingModal reportedListingModal = new ReportedListingModal();
        ActionUtils.clickButton(welcomePage.getPostYourProperty());
        loginPage.loginWithPassword(number, password);
//        ActionUtils.clickButton(neverMissCustomerModal.getLater());
        ActionUtils.clickButton(reportedListingModal.getIWillDoLater());
    }


    @When("seller opens chat inbox")
    public void sellerOpensChatInbox() {
        HomePage homePage = new HomePage();
        ActionUtils.clickButton(homePage.getFloatingCta());
    }


    @And("Seller replies back")
    public void sellerRepliesBack() throws InterruptedException {
        ActionUtils.clickButton(chatThreadPage.getPills().get(0));
        ActionUtils.clickButton(chatThreadPage.getSendButton());
        Thread.sleep(1000);
        Assert.assertEquals(chatThreadPage.getMessages(0), chatThreadPage.getPillText(0).getText());
    }




    @Then("{string} message should be visible on chat inbox and chat thread")
    public void messageShouldBeVisibleOnChatInboxAndChatThread(String buyer) {
        Assert.assertTrue(chatInboxPage.waitForVisibilityOfUnreadCount(0, buyer, 600));
        Assert.assertEquals(chatInboxPage.getName(0).getText(), buyer);
        ActionUtils.clickButton(chatInboxPage.getChatThreads().get(0));
        Assert.assertEquals(chatThreadPage.getRecieverName().getText(), buyer);
    }
}
