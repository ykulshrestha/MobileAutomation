package runner;

//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = {"src/test/resources/features/Engagement/detailPage/Loginflow.feature"}
        ,glue = {"base", "stepdefinition"}
//        ,tags= "@Test1"
        ,publish = true
        ,plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

public class LoginRunner extends RunnerBase {


}
