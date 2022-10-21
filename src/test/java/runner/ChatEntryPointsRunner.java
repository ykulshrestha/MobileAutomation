package runner;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = {"src/test/resources/features/Engagement/detailPage/Chats/Buyer/ChatEntryPoints.feature"}
        ,glue = {"base", "stepdefinition/chats/buyer"}
//        ,tags= "@Test5"
        ,publish = true
        ,plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

public class ChatEntryPointsRunner extends RunnerBase{
}
