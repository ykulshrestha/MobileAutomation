package runner;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = {"src/test/resources/features/Engagement/InboxEntryPoints"}
        ,glue = {"base", "stepdefinition"}
//        ,tags= "@Test3"
        ,publish = true
        ,plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class InboxRunner extends RunnerBase{

}
