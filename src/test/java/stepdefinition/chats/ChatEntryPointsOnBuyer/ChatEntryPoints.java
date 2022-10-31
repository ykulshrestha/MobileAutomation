package stepdefinition.chats.ChatEntryPointsOnBuyer;

import enums.BhkEnum;
import enums.MyActivityEnums;
import io.appium.java_client.MobileBy;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import modals.CrfFormModal;
import modals.LoginModal;
import org.testng.Assert;
import pages.BuyerTabs;
import pages.ChatThreadPage;
import pages.buyerAppPages.*;
import util.ActionUtils;
import utils.AppUtil;

public class ChatEntryPoints {
    AppUtil appUtil = new AppUtil();
    HomePage homePage = new HomePage();
    SearchLocalityPage searchLocalityPage = new SearchLocalityPage();
    SerpPage serpPage = new SerpPage();
    DetailsPage detailsPage = new DetailsPage();
    ChatThreadPage chatThreadPage = new ChatThreadPage();
    FilterPage filterPage = new FilterPage();
    LoginModal loginModal = new LoginModal();

    CrfFormModal crfFormModal = new CrfFormModal();

    ProfilePage profilePage = new ProfilePage();

    BuyerTabs buyerTabs = new BuyerTabs();


    private String propertyName;
    private String sellerName;
    @Given("Buyer is on detail Page of {string} in {string}")
    public void buyerIsOnDetailPageOfProjectInCity(String locality, String city) throws InterruptedException {
        appUtil.buyerOnboarding(city);
        ActionUtils.clickButton(homePage.getBuy());
        ActionUtils.sendText(searchLocalityPage.getSearchTextBox(), locality);
        ActionUtils.clickButton(ActionUtils.elementWithMatchingText(searchLocalityPage.getSearchedProjectList(),locality));
        ActionUtils.clickButton(searchLocalityPage.getSearchButton());
        filterPage.setBhk(BhkEnum.ONERK.value());
         ActionUtils.clickButton(filterPage.getViewProperties());
        ActionUtils.clickButton(serpPage.getPropertyCards().get(1));
    }

    @When("Buyer click on chat header icon")
    public void buyerClickOnChatHeaderIcon() {
//        detailsPage.handleChatCoachmark();
        ActionUtils.waitForVisibilityOf(detailsPage.getPropertyName());
        propertyName = detailsPage.getPropertyName().getText();
        ActionUtils.waitForVisibilityOf(detailsPage.getSellerName());
        sellerName = detailsPage.getSellerName().getText();
        ActionUtils.scroll(detailsPage.getAskForLocation(), "up");
        ActionUtils.clickButton(detailsPage.getChatHeaderIcon());
    }

    @And("Buyer logged in using login modal with {string} and {string}")
    public void buyerLoggedInUsingLoginModalWithNumberAndPassword(String Number, String password) {
        loginModal.loginWithPassword(Number, password);
    }

    @Then("Chat screen is visible to Buyer")
    public void chatScreenIsVisibleToBuyer() {
        ActionUtils.waitForVisibilityOf(chatThreadPage.getRecieverName());
        ActionUtils.waitForVisibilityOf(chatThreadPage.getPropertyName());
        ActionUtils.waitForVisibilityOf(chatThreadPage.getMessageTextBox());
    }

    @And("sellerName should be visible on chat inbox")
    public void sellerNameShouldBeVisibleOnChatInbox() {
        Assert.assertEquals(chatThreadPage.getRecieverName().getText(), sellerName);
    }

    @And("Property Name should be visible")
    public void propertyNameShouldBeVisible() {
        Assert.assertEquals(chatThreadPage.getPropertyName().getText(), propertyName);
    }


    @When("Buyer click on Chat for details")
    public void buyerClickOnChatForDetails() {
        ActionUtils.scroll(detailsPage.getChatForDetails(), "up");
        ActionUtils.clickButton(detailsPage.getChatForDetails());
    }


    @And("Buyer clicks on housing chat from bottom tray")
    public void buyerClicksOnHousingChatFromBottomTray() {
        ActionUtils.clickButton(detailsPage.getChatFloatingCta());
        ActionUtils.clickButton(detailsPage.getHousingChatInBottomTray());
    }


    @Then("Buyer sends message to seller")
    public void Buyer_sends_message_to_seller() throws InterruptedException {
        ActionUtils.clickButton(chatThreadPage.getPills().get(0));
        ActionUtils.clickButton(chatThreadPage.getSendButton());
        Thread.sleep(1000);
        Assert.assertEquals(chatThreadPage.getMessages(1).getText(), chatThreadPage.getPillText(0).getText());
    }

    @When("Buyer click on Chat Now")
    public void buyerClickOnChatNow() {
        ActionUtils.clickButton(detailsPage.getChatNow());
    }



    @And("Buyer login using crf form with {string}, {string} and {string}")
    public void buyerLoginUsingCrfFormWithAnd(String Number, String Name, String email) {
        ActionUtils.dropModal(loginModal.getLoginContainer(), loginModal.getDraggingPoint());
        crfFormModal.fillCrfForm(Name, Number, email);
    }



    @Given("User logged in with {string} and {string} onboarded for {string}")
    public void userLoggedInWithAndOnboardedFor(String number, String password, String city) {
        appUtil.buyerOnboarding(city);
        ActionUtils.clickButton(buyerTabs.getProfileTab());
        ActionUtils.clickButton(profilePage.getLoginButton());
        loginModal.loginWithPassword(number, password);
        ActionUtils.clickButton(buyerTabs.getSearch());
    }

    @And("Logged in Buyer is on detail Page of {string}")
    public void loggedInBuyerIsOnDetailPageOf(String locality) {

    }


    @And("Buyer navigates back to profile page and click on chat of {string}")
    public void buyerNavigatesBackToProfilePageAndClickOnChatOf(String arg0) {
        ActionUtils.gestureBack();
        ActionUtils.clickButton(serpPage.getProfileFloatingCta());
        switch (arg0){
            case "saved": {
                ActionUtils.clickButton(profilePage.getMyActivityTab().get(MyActivityEnums.SAVED_PROPERTIES.value()));
                break;
            }
            case "seen":
            {
                ActionUtils.clickButton(profilePage.getMyActivityTab().get(MyActivityEnums.SEEN_PROPERTIES.value()));
                break;
            }
            case "contacted":
            {
                ActionUtils.clickButton(profilePage.getMyActivityTab().get(MyActivityEnums.CONTACTED_PROPERTIES.value()));
                break;
            }
            case "searched":
            {
                ActionUtils.clickButton(profilePage.getMyActivityTab().get(MyActivityEnums.SEARCHED_PROPERTIES.value()));
                break;
            }
        }
        ActionUtils.clickButton(profilePage.getChatCta(0));
    }


    @And("Buyer is on detail Page of {string} service of {string}")
    public void buyerIsOnDetailPageOfServiceOf(String service, String locality) {
        switch(service){
            case "buy": {
                ActionUtils.clickButton(homePage.getBuy());
                break;
            }
            case "rent": {
                ActionUtils.clickButton(homePage.getRent());
                break;
            }
        }
        ActionUtils.sendText(searchLocalityPage.getSearchTextBox(), locality);
        ActionUtils.clickButton(ActionUtils.elementWithMatchingText(searchLocalityPage.getSearchedProjectList(), locality));
        ActionUtils.clickButton(searchLocalityPage.getSearchButton());
        filterPage.setBhk(BhkEnum.ONERK.value());
        ActionUtils.clickButton(filterPage.getViewProperties());
        ActionUtils.clickButton(serpPage.getPropertyCards().get(1));
    }

    @Given("Buyer completed onboarding for {string}")
    public void  buyerCompletedOnboardingFor(String city) {
        appUtil.buyerOnboarding(city);
    }

    @Then("Chat entry points should be visible")
    public void chatEntryPointsShouldBeVisible() {
        Assert.assertTrue(ActionUtils.isElementDisplayed(detailsPage.getChatNow()));
        Assert.assertTrue(ActionUtils.isElementDisplayed(ActionUtils.scroll(detailsPage.getChatForDetails(), "up")));
        Assert.assertTrue(ActionUtils.isElementDisplayed(detailsPage.getChatHeaderIcon()));
        Assert.assertTrue(ActionUtils.isElementDisplayed(detailsPage.getChatFloatingCta()));
    }

    @And("Buyer is on detail Page of {string}")
    public void buyerIsOnDetailPageOf(String project) {
        ActionUtils.clickButton(homePage.getBuy());
        ActionUtils.sendText(searchLocalityPage.getSearchTextBox(), project);
        ActionUtils.clickButton(ActionUtils.elementWithPartialMatchingText(searchLocalityPage.getSearchedProjectList(), project));
    }


    @And("Buyer is on detail Page of other {string} service of {string}")
    public void buyerIsOnDetailPageOfOtherServiceOf(String service, String locality) throws InterruptedException {
        switch(service){
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
        ActionUtils.sendText(searchLocalityPage.getSearchTextBox(), locality);
        ActionUtils.clickButton(ActionUtils.elementWithMatchingText(searchLocalityPage.getSearchedProjectList(), locality));
        if (service.equalsIgnoreCase("commercial"))
            ActionUtils.clickButton(searchLocalityPage.getSearchButton());
        if (service.equalsIgnoreCase("pg"))
            ActionUtils.clickButton(filterPage.getViewProperties());
        ActionUtils.clickButton(serpPage.getPropertyCards().get(1));
        }

    @Then("Chat entry points should not be visible")
    public void chatEntryPointsShouldNotBeVisible() {
        Assert.assertFalse(ActionUtils.isElementDisplayed(detailsPage.getChatNow()));
        Assert.assertFalse(ActionUtils.isElementDisplayed(ActionUtils.scroll(detailsPage.getChatForDetails(), "up")));
        Assert.assertFalse(ActionUtils.isElementDisplayed(detailsPage.getChatHeaderIcon()));
        Assert.assertFalse(ActionUtils.isElementDisplayed(detailsPage.getChatFloatingCta()));
    }
}

