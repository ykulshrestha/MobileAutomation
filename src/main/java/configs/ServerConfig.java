package configs;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ActionUtils;

import java.io.File;
import java.util.HashMap;

public class ServerConfig {

    private static final Logger logger = LogManager.getLogger(ServerConfig.class);

    private static AppiumDriverLocalService server;

    public static AppiumDriverLocalService getServer(){
        return server;
    }

    public void startServer(){
        server = getAppiumService();
        logger.info("Starting appium server on "+ server.getUrl());
            server.start();
        if(server == null || !server.isRunning()){
            logger.error("Error occured while starting appium server"+ server.getUrl());
            throw new AppiumServerHasNotBeenStartedLocallyException("Appium server not started. ABORT!!!");
        }
        logger.info("Appium server started successfully on "+ server.getUrl());
    }

    public AppiumDriverLocalService getAppiumService() {
        HashMap<String, String> environment = new HashMap<String, String>();
        environment.put("ANDROID_HOME", System.getenv("ANDROID_HOME"));
        environment.put("JAVA_HOME", System.getenv("JAVA_HOME"));

        logger.info("Setting JAVA_HOME for appium server "+ System.getenv("JAVA_HOME"));
        logger.info("Setting ANDROID_HOME for appium server "+ System.getenv("ANDROID_HOME"));
        logger.info("Setting Node js path for appium server "+ System.getenv("NODE_PATH"));
        logger.info("Setting Appium js path for appium server "+ System.getenv("APPIUMJS_PATH"));
        logger.info("Setting PORT for appium server ");

        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File(System.getenv("NODE_PATH")))
                .withAppiumJS(new File(System.getenv("APPIUMJS_PATH")))
                .usingPort(Integer.parseInt(System.getenv("PORT")))
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withEnvironment(environment));

    }

}
