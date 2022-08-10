package configs;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.util.HashMap;

public class ServerConfig {
    private static ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<>();

    public AppiumDriverLocalService getServer(){
        return server.get();
    }

    public void startServer(){
        AppiumDriverLocalService server = getAppiumService();
        server.start();
        if(server == null || !server.isRunning()){
            throw new AppiumServerHasNotBeenStartedLocallyException("Appium server not started. ABORT!!!");
        }
        this.server.set(server);
    }

    public AppiumDriverLocalService getAppiumService() {
        HashMap<String, String> environment = new HashMap<String, String>();
        environment.put("ANDROID_HOME", System.getenv("ANDROID_HOME"));
        environment.put("JAVA_HOME", System.getenv("JAVA_HOME"));

        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File(System.getenv("NODE_PATH")))
                .withAppiumJS(new File(System.getenv("APPIUMJS_PATH")))
                .usingPort(Integer.parseInt(System.getenv("PORT")))
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withEnvironment(environment));

    }

}
