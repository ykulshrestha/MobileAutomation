package stepdefinition.buyerSellerChat;

import configs.DriverConfig;
import enums.BhkEnum;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import modals.LoginModal;
import org.testng.Assert;
import pages.ChatThreadPage;
import pages.buyerAppPages.*;
import util.ActionUtils;
import utils.AppUtil;

public class BuyerChat {

    @Given("Logged in user is on Detail Page")
    public void logged_in_user_is_on_detail_page() {
        AppUtil appUtil = new AppUtil();
        HomePage homePage = new HomePage();
        SearchLocalityPage searchLocalityPage = new SearchLocalityPage();
        ProfilePage profilePage = new ProfilePage();
        FilterPage filterPage = new FilterPage();
        SerpPage serpPage = new SerpPage();
        LoginModal loginModal = new LoginModal();
        appUtil.buyerOnboarding("Ajmer");
        ActionUtils.clickButton(homePage.getTabs().getProfileTab());
        ActionUtils.clickButton(profilePage.getLoginButton());
        loginModal.loginWithPassword("7317141086", "12345");
        ActionUtils.clickButton(profilePage.getTabs().getSearch());
        ActionUtils.clickButton(homePage.getBuy());
        ActionUtils.sendText(searchLocalityPage.getSearchTextBox(), "Makadwali");
        ActionUtils.clickButton(ActionUtils.elementWithMatchingText(searchLocalityPage.getSearchedProjectList(),"Makadwali"));
        ActionUtils.clickButton(searchLocalityPage.getSearchButton());
        filterPage.setBhk(BhkEnum.ONERK.value());
        ActionUtils.clickButton(filterPage.getViewProperties());
        ActionUtils.clickButton(serpPage.getPropertyCards().get(1));
    }

    @When("Buyer click on Chat Now")
    public void buyerClicksOnChatNow() {
        DetailsPage detailsPage = new DetailsPage();
        ActionUtils.clickButton(detailsPage.getChatNow());
    }

    @Then("Buyer sends message to seller")
    public void chatScreenIsVisibleToUser() {
        ChatThreadPage chatThreadPage = new ChatThreadPage();
        Assert.assertTrue(ActionUtils.isElementPresent(chatThreadPage.getSellerName(), 120));
        Assert.assertTrue(ActionUtils.isElementPresent(chatThreadPage.getPropertyDetails(), 40));
        Assert.assertTrue(ActionUtils.isElementPresent(chatThreadPage.getMessageTextBox(), 40));
        ActionUtils.clickButton(chatThreadPage.getPills().get(0));
        ActionUtils.clickButton(chatThreadPage.getSendButton());
    }
}
