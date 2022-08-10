package runner;

//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = {"src/test/resources/features/Engagement/detailPage/ChatNow.feature"}
        ,glue = {"base", "stepdefinition"}
        ,tags= "@Test1"
        ,publish = true
)

public class EngagementRunnerTestNg extends AbstractTestNGCucumberTests {

}
