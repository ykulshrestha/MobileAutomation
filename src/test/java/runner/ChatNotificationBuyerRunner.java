package runner;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = {"src/test/resources/features/Engagement/notification/ChatNotification/ChatNotifications_Buyer.feature"}
        ,glue = {"base", "stepdefinition"}
        ,tags= "@Test1"
        ,publish = true
        ,plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)

public class ChatNotificationBuyerRunner extends RunnerBase{
}
