package runner;

//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = {"src/test/resources/features/Engagement/detailPage/BuyerSellerChat/BuyerChat.feature"}
        ,glue = {"base", "stepdefinition/buyerSellerChat"}
//        ,tags= "@Test1"
        ,publish = true
        ,plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

public class BuyerRunner extends RunnerBase {


}
