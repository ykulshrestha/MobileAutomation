package base;

import configs.DriverConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class Hooks {

    @Before
    public void initializeTest() {
        DriverConfig.getDriver().resetApp();
        }


    @After (order = 1)
    public void takeScreenshotOnFailure(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            File src = ((TakesScreenshot) DriverConfig.getDriver()).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(src);
            scenario.attach(fileContent, "image/png", "");
        }
    }


    @After (order = 0)
    public void endTest(Scenario scenario) {
//        DriverConfig.getDriver().quit();
//            ServerConfig serverManager = new ServerConfig();
//            if(serverManager.getServer() != null){
//                serverManager.getServer().stop();
//            }
    }
}


