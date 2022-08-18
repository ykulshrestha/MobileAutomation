package stepdefinition.buyerSellerChat;

import io.appium.java_client.MobileElement;
import io.cucumber.java.en.Given;
import modals.NeverMissCustomerModal;
import modals.ReportedListingModal;
import pages.ChatInboxPage;
import pages.ChatThreadPage;
import pages.onboarding.WelcomePage;
import pages.sellerAppPages.HomePage;
import pages.sellerAppPages.LoginPage;
import util.ActionUtils;

import java.util.List;

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

    @Given("seller opens chat inbox")
    public void sellerOpensChatInbox() {
        HomePage homePage = new HomePage();
        ActionUtils.clickButton(homePage.getFloatingCta());
    }

    @Given("Buyer message should be visible")
    public void buyerMessageShouldBeVisible() {
        ChatInboxPage chatInboxPage = new ChatInboxPage();
        chatThread = chatInboxPage.getUnreadThreads();
    }

    @Given("Seller replies back")
    public void sellerRepliesBack() {

        ChatThreadPage chatThreadPage = new ChatThreadPage();
        for (int i=0;i< chatThread.size(); i++){
            ActionUtils.clickButton(chatThread.get(i));
            ActionUtils.clickButton(chatThreadPage.getHelloPill());
            ActionUtils.clickButton(chatThreadPage.getSendButton());
            ActionUtils.gestureBack();
        }

    }


}
