package stepdefinition;

import enums.ErrorMessageEnums;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import modals.LoginModal;
import org.testng.Assert;
import pages.buyerAppPages.*;
import util.ActionUtils;
import utils.AppUtil;

public class Loginflow {


    @Given("User opens login modal on profile page")
    public void userOpensLoginModalOnProfilePage() {
        AppUtil appUtil = new AppUtil();
        appUtil.buyerOnboarding("delhi");
        ActionUtils.clickButton( ActionUtils.scroll("text" , "Login Now" ));
    }

    @When("User login by using number and password")
    public void userLoginByUsingNumberAndPassword() {
        LoginModal loginModal = new LoginModal();
        ActionUtils.sendText(loginModal.getEnterPhone(), "7317141087");
        ActionUtils.clickButton(loginModal.getContinueButton());
        ActionUtils.clickButton(loginModal.getLoginWithPassword());
        ActionUtils.sendText(loginModal.getPasswordTextbox(), "111111");
        ActionUtils.clickButton(loginModal.getContinuePassword());
    }

    @Then("Login failed with toast")
    public void loginFailedWithToast() {
        Assert.assertTrue(ActionUtils.getErrorToast(ErrorMessageEnums.TRY_AGAIN_CORRECT_CREDENTIALS.value()).isDisplayed());
    }
}
