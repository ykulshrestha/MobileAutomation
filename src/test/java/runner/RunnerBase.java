package runner;

//import io.cucumber.junit.CucumberOptions;
import configs.DriverConfig;
import configs.ServerConfig;
import constants.Constant;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import util.BaseCapabilitiesUtil;
import util.ConfigUtil;

import java.net.MalformedURLException;
import java.net.URL;


public class RunnerBase extends AbstractTestNGCucumberTests {

    private static final Logger logger = LogManager.getLogger(BaseCapabilitiesUtil.class);


    @BeforeTest
    @Parameters({"AUTOMATION_NAME",
            "PLATFORM_NAME",
            "DEVICE_NAME",
            "PLATFORM_VERSION",
            "APP",
            "AVD"})
    public void beforeTest(String AUTOMATION_NAME, String
            PLATFORM_NAME, String
                    DEVICE_NAME, String
                    PLATFORM_VERSION,String
                    APP, String
                    AVD){
        System.out.println("executing before test==========================");
        new ServerConfig().startServer();
        DesiredCapabilities desiredCapabilities = new BaseCapabilitiesUtil().setCapability();
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATION_NAME);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + APP);
        if (PLATFORM_NAME.equalsIgnoreCase("Android"))
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AVD, AVD);
//        if (PLATFORM_NAME.equalsIgnoreCase("ios"))
//            desiredCapabilities.setCapability(IOSMobileCapabilityType., AVD);


        logger.info("Platform type is "+ PLATFORM_NAME + ", creating "+ desiredCapabilities.getCapability(MobileCapabilityType.PLATFORM_NAME) + "driver");

        if (PLATFORM_NAME.equalsIgnoreCase("Android"))
            try {
                DriverConfig.setDriver(new AndroidDriver<MobileElement>(new URL(new ConfigUtil().readProperties(Constant.APPIUM_PROPERTIES).get("url").toString()), desiredCapabilities));
            } catch (Exception e) {
                logger.error("Error occurred while creating Android driver {} ", e.getMessage());
                e.printStackTrace();
            }
        if (PLATFORM_NAME.equalsIgnoreCase("ios"))
            try {
                DriverConfig.setDriver(new IOSDriver<MobileElement>(new URL(new ConfigUtil().readProperties(Constant.APPIUM_PROPERTIES).get("url").toString()), desiredCapabilities));
            } catch (MalformedURLException e) {
                logger.error("Error occurred while creating ios driver {} ", e.getMessage());
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
