package stepdefinition.buyerSellerChat;

import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import modals.InboxMessageThread;
import modals.NeverMissCustomerModal;
import modals.ReportedListingModal;
import org.testng.Assert;
import pages.ChatInboxPage;
import pages.ChatThreadPage;
import pages.onboarding.WelcomePage;
import pages.sellerAppPages.HomePage;
import pages.sellerAppPages.LoginPage;
import util.ActionUtils;

import java.util.List;
import java.util.Objects;

public class SellerChat {

    List<MobileElement> chatThread;
    @Given("Seller is logged in on seller App")
    public void sellerIsLoggedInOnSellerApp() throws InterruptedException {
        WelcomePage welcomePage = new WelcomePage();
        LoginPage loginPage = new LoginPage();
        NeverMissCustomerModal neverMissCustomerModal = new NeverMissCustomerModal();
        ReportedListingModal reportedListingModal = new ReportedListingModal();
        ActionUtils.clickButton(welcomePage.getPostYourProperty());
        loginPage.loginWithPassword("6111111111", "housing@1234");
        ActionUtils.clickButton(neverMissCustomerModal.getLater());
        ActionUtils.clickButton(reportedListingModal.getIWillDoLater());
    }

    @When("seller opens chat inbox")
    public void sellerOpensChatInbox() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isUnreadMessageExist());
        ActionUtils.clickButton(homePage.getFloatingCta());
    }

    @Then("Buyer message should be visible")
    public void buyerMessageShouldBeVisible() {
        ChatInboxPage chatInboxPage = new ChatInboxPage();
//        InboxMessageThread inboxMessageThread = new InboxMessageThread(chatInboxPage, 0);
//        MobileElement buyerThread= ActionUtils.scroll("text", "Hemidail");
//        Assert.assertTrue(Objects.nonNull(chatInboxPage.getUnreadCount().get(0)));
//        Assert.assertEquals(inboxMessageThread.getName().getText(), "Manthan patel");
//        Assert.assertTrue(inboxMessageThread.getUnreadCount().isDisplayed());
        MobileElement element = chatInboxPage.getUnreadCount(0);
        Assert.assertTrue(element.isDisplayed());
        Assert.assertEquals(chatInboxPage.getName(0).getText(), "Hemidail");
        ActionUtils.clickButton(chatInboxPage.getChatThreads().get(0));
    }

    @And("Seller replies back")
    public void sellerRepliesBack() {
        ChatThreadPage chatThreadPage = new ChatThreadPage();
        ActionUtils.clickButton(chatThreadPage.getPills().get(0));
        ActionUtils.clickButton(chatThreadPage.getSendButton());
    }

    }


