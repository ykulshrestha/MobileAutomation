package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/Engagement/detailPage/ChatNow.feature"}
        ,glue = {"base", "stepdefinition"}
        ,tags= "@Test1"
)

public class EngagementRunner {

}
