package runner;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = {"src/test/resources/features/Engagement/notification"}
        ,glue = {"base", "stepdefinition"}
        ,tags= "@inapp"
        ,publish = true
        ,plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

public class NotificationRunner extends RunnerBase{
}
