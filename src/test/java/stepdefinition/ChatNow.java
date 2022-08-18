package stepdefinition;

import modals.LoginModal;
import pages.buyerAppPages.DetailsPage;
import pages.buyerAppPages.HomePage;
import pages.buyerAppPages.ProfilePage;
import pages.buyerAppPages.SearchLocalityPage;
import utils.AppUtil;
import io.cucumber.java.en.*;
import util.ActionUtils;

public class ChatNow {
    @Given("User is on Detail Page")
    public void userIsOnDetailPage() {
        AppUtil appUtil = new AppUtil();
        HomePage homePage = new HomePage();
        SearchLocalityPage searchLocalityPage = new SearchLocalityPage();
        appUtil.buyerOnboarding();
        ActionUtils.clickButton(homePage.getBuy());
        ActionUtils.sendText(searchLocalityPage.getSearchTextBox(), "Godrej south estate");
        ActionUtils.clickButton(ActionUtils.elementWithMatchingText(searchLocalityPage.getSearchedProjectList(), 0));
//        ActionUtils.clickButton(searchLocalityPage.getSearchButton());
    }

    @Given("Logged in User is on Detail Page")
    public void loggedInUserIsOnDetailPage() throws InterruptedException {
        AppUtil appUtil = new AppUtil();
        HomePage homePage = new HomePage();
        SearchLocalityPage searchLocalityPage = new SearchLocalityPage();
        ProfilePage profilePage = new ProfilePage();
        LoginModal loginModal = new LoginModal();
        appUtil.buyerOnboarding();
        ActionUtils.clickButton(homePage.getProfileTab());
        ActionUtils.clickButton(profilePage.getLoginButton());
        loginModal.loginWithPassword("7317141110", "12345");
        ActionUtils.clickButton(homePage.getSearch());
        ActionUtils.clickButton(homePage.getBuy());
        ActionUtils.sendText(searchLocalityPage.getSearchTextBox(), "Godrej south estate");
        ActionUtils.clickButton(ActionUtils.elementWithMatchingText(searchLocalityPage.getSearchedProjectList(), 0));
    }


    @When("User click on Chat Now")
    public void userClickOnChatNow()  {
        DetailsPage detailsPage = new DetailsPage();
        ActionUtils.clickButton(detailsPage.getChatNow());
    }

//    @Then("Chat screen is visible to user")
//    public void chatScreenIsVisibleToUser()  {
//        ChatThreadPage chatThreadPage = new ChatThreadPage();
//        Assert.assertTrue(ActionUtils.isElementPresent(chatThreadPage.getSellerName(), 120));
//        Assert.assertTrue(ActionUtils.isElementPresent(chatThreadPage.getPropertyDetails(), 40));
//        Assert.assertTrue(ActionUtils.isElementPresent(chatThreadPage.getMessageTextBox(), 40));
//        ActionUtils.clickButton(chatThreadPage.getHelloPill());
//        ActionUtils.clickButton(chatThreadPage.getSendButton());
////        Thread.sleep(10000);
//    }


    @And("User drop off login through login modal")
    public void userDropOffLoginThroughLoginModal() {
    }

    @And("User login through login modal")
    public void userLoginThroughLoginModal() throws InterruptedException {
        LoginModal loginModal = new LoginModal();
        loginModal.loginWithPassword("7317141086", "12345");
//        Thread.sleep(30000);
    }


}
