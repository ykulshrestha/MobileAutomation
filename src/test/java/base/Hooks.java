package base;

import configs.DriverConfig;
import configs.ServerConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import util.Installation;

import java.io.IOException;
import java.net.MalformedURLException;

public class Hooks {

        @Before
        public void initializeTest() throws MalformedURLException {

            new ServerConfig().startServer();
            DriverConfig.setDriver(new Installation().getDriver());
        }

        @After
        public void endTest(Scenario scenario) {

            DriverConfig.getDriver().quit();
            ServerConfig serverManager = new ServerConfig();
            if(serverManager.getServer() != null){
                serverManager.getServer().stop();
            }
        }
    }


