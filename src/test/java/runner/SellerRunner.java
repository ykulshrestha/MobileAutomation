package runner;

//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = {"src/test/resources/features/Engagement/detailPage/BuyerSellerChat/SellerChat.feature"}
        ,glue = {"base", "stepdefinition/buyerSellerChat"}
//        ,tags= "@Test1"
        ,publish = true
        ,plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

public class SellerRunner extends RunnerBase {


}
