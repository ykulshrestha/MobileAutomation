package runner;

//import io.cucumber.junit.CucumberOptions;
import configs.DriverConfig;
import configs.PropertyConfig;
import configs.ServerConfig;
import constants.Constant;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;


public class RunnerBase extends AbstractTestNGCucumberTests {

    @BeforeTest
    @Parameters({"AUTOMATION_NAME",
            "PLATFORM_NAME",
            "DEVICE_NAME",
            "PLATFORM_VERSION",
            "APP",
            "AVD",
    "PORT"})
    public void beforeTest(String AUTOMATION_NAME, String
            PLATFORM_NAME, String
                    DEVICE_NAME, String
                    PLATFORM_VERSION,String
                    APP, String
                    AVD, String PORT){
        System.out.println("executing before test==========================");
        new ServerConfig().startServer();
        DesiredCapabilities desiredCapabilities;
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATION_NAME);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + APP);
        desiredCapabilities.setCapability("avd", AVD);
        desiredCapabilities.setCapability("no-reset", "false");
        desiredCapabilities.setCapability("full-reset", "true");


        if (PLATFORM_NAME.equals("Android"))
            try {
                DriverConfig.setDriver(new AndroidDriver<MobileElement>(new URL(new PropertyConfig().readProperties(Constant.APPIUM_PROPERTIES).get("url").toString()), desiredCapabilities));
            } catch (Exception e) {
                e.printStackTrace();
            }
        if (PLATFORM_NAME.equals("ios"))
            try {
                DriverConfig.setDriver(new IOSDriver<MobileElement>(new URL(new PropertyConfig().readProperties(Constant.APPIUM_PROPERTIES).get("url").toString()), desiredCapabilities));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

    }

    @AfterTest
    public void quitDriver(){
            DriverConfig.getDriver().quit();
    }

    @AfterSuite
    public void tearDown()
    {
        if(ServerConfig.getServer() != null) {
            ServerConfig.getServer().stop();
        }
    }
}
