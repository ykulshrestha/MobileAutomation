package base;

import configs.DriverConfig;
//import cucumber.api.java.After;
//import cucumber.api.java.Before;
//import cucumber.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import util.Installation;

import java.net.MalformedURLException;

public class Hooks {

        @Before
        public void initializeTest() throws MalformedURLException {

            DriverConfig.setDriver(new Installation().getDriver());
        }

        @After
        public void tearDownTest(Scenario scenario) {
            if(scenario.isFailed()){
                byte[] screenshot = DriverConfig.getDriver().getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
            DriverConfig.getDriver().quit();
        }
    }


