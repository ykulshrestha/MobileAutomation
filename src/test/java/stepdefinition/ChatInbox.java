package stepdefinition;

import enums.ErrorMessageEnums;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import modals.LoginModal;
import org.testng.Assert;
import util.ActionUtils;
import utils.AppUtil;

public class ChatInbox {


     @Given("User login on seller app")
        public void userLoginOnSellerApp() {

      }

    @And("User lands on <tab> with <service> selected")
    public void userLandsOnTab() {

    }


    @When("User clicks on chat floating cta")
        public void userLoginByUsingNumberAndPassword() {

        }

    @Then("Chat Inbox Opens")
        public void loginFailedWithToast() throws InterruptedException {
        }

    @And("Chat card for the user is visible")
    public void ChatCardIsVisible() {

    }

}

