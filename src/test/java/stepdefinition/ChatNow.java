package stepdefinition;

import org.testng.Assert;
import utils.AppUtil;
import io.cucumber.java.en.*;
import pages.DetailsPage;
import pages.HomePage;
import pages.SearchLocalityPage;
import util.ActionUtils;

public class ChatNow {
    @Given("User is on Detail Page")
    public void userIsOnDetailPage() throws InterruptedException {
        AppUtil appUtil = new AppUtil();
        HomePage homePage = new HomePage();
        SearchLocalityPage searchLocalityPage = new SearchLocalityPage();
        appUtil.onboarding();
        ActionUtils.clickButton(homePage.getBuy());
        ActionUtils.sendText(searchLocalityPage.getSearchTextBox(), "Godrej south estate");
        ActionUtils.clickButton(ActionUtils.elementWithMatchingText(searchLocalityPage.getSearchedProjectList(), 0));
//        ActionUtils.clickButton(searchLocalityPage.getSearchButton());
    }

    @When("User click on Chat Now")
    public void userClickOnChatNow() throws InterruptedException {
        DetailsPage detailsPage = new DetailsPage();
        ActionUtils.clickButton(detailsPage.getChatNow());
    }

    @Then("Chat screen is visible to user")
    public void chatScreenIsVisibleToUser() {

    }

    @And("User enter username on login modal")
    public void userEnterUsernameOnLoginModal() {
    }

    @And("User enter otp")
    public void userEnterOtp() {
    }

    @And("User click on login")
    public void userClickOnLogin() {
    }

    @Then("Chat screen is visible to use")
    public void chatScreenIsVisibleToUse() {
    }

    @And("User drop off login modal")
    public void userDropOffLoginModal() {
    }

    @And("User fill crf form")
    public void userFillCrfForm() {
    }

    @And("user click on contact")
    public void userClickOnContact() {
    }


    @Given("User is on Detail Page aa")
    public void userIsOnDetailPageAa() {
        AppUtil appUtil = new AppUtil();
        HomePage homePage = new HomePage();
        SearchLocalityPage searchLocalityPage = new SearchLocalityPage();
        appUtil.onboarding();
        ActionUtils.clickButton(homePage.getBuy());
        ActionUtils.sendText(searchLocalityPage.getSearchTextBox(), "Godrej south estate");
        ActionUtils.clickButton(ActionUtils.elementWithMatchingText(searchLocalityPage.getSearchedProjectList(), 0));
    }

    @When("User click on Chat Now aa")
    public void userClickOnChatNowAa() throws InterruptedException {
        DetailsPage detailsPage = new DetailsPage();
        ActionUtils.clickButton(detailsPage.getChatNow());
    }

    @Then("Chat screen is visible to user aa")
    public void chatScreenIsVisibleToUserAa() {
    }
}
