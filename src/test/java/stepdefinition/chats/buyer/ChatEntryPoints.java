package stepdefinition.chats.buyer;

import enums.BhkEnum;
import enums.SaleTypeEnum;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_old.Ac;
import modals.CrfFormModal;
import modals.LoginModal;
import org.testng.Assert;
import pages.ChatThreadPage;
import pages.buyerAppPages.*;
import util.ActionUtils;
import utils.AppUtil;
import verification.Verification;

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
        ActionUtils.waitForVisibilityOf(chatThreadPage.getSellerName());
        ActionUtils.waitForVisibilityOf(chatThreadPage.getPropertyName());
        ActionUtils.waitForVisibilityOf(chatThreadPage.getMessageTextBox());
    }

    @And("sellerName should be visible on chat inbox")
    public void sellerNameShouldBeVisibleOnChatInbox() {
        Assert.assertEquals(chatThreadPage.getSellerName().getText(), sellerName);
    }

    @And("Property Name should be visible")
    public void propertyNameShouldBeVisible() {
        Assert.assertEquals(chatThreadPage.getPropertyName().getText(), propertyName);
    }

    @Given("Buyer is on detail Page")
    public void buyerIsOnDetailPage() {
    }


    @Given("Logged in Buyer is on detail Page")
    public void loggedInBuyerIsOnDetailPage() {
    }

    @When("Buyer click on Chat for details")
    public void buyerClickOnChatForDetails() {
    }

    @When("Buyer click on chat from bottom floating cta")
    public void buyerClickOnChatFromBottomFloatingCta() {
    }

    @And("Buyer clicks on housing chat from bottom tray")
    public void buyerClicksOnHousingChatFromBottomTray() {
    }

    @Given("Logged in Buyer is on Profile Page")
    public void loggedInBuyerIsOnProfilePage() {
    }

    @When("Buyer click on chat on fav cards")
    public void buyerClickOnChatOnFavCards() {
    }

    @Then("Buyer sends message to seller")
    public void Buyer_sends_message_to_seller() {
        ActionUtils.clickButton(chatThreadPage.getPills().get(0));
        ActionUtils.clickButton(chatThreadPage.getSendButton());
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
}
