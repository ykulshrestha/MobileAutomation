package runner;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = {"src/test/resources/features/Engagement/detailPage/Chats/ChatEntryPointsOnBuyer/ChatDiscoveryExistance.feature"}
        ,glue = {"base", "stepdefinition/chats/ChatEntryPointsOnBuyer"}
        ,tags= "@Test1"
        ,publish = true
        ,plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

public class ChatDiscoveryExistanceRunner extends RunnerBase{
}
