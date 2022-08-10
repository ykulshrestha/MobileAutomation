package configs;

import constants.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AppiumConfig {

    // This functions read appium.properties file and put each key and value in a map object and returns the same
    public Map readProperties(){
        Map<String, String> appiumMap = new HashMap<>();

        ClassLoader cl = getClass().getClassLoader();
        URL resource = cl.getResource(Constant.APPIUM_PROPERTIES);
        InputStream is = null;
        try {
            is = resource.openStream();
        } catch (IOException e) {
            throw new RuntimeException("Exception in creating connection with property file: ");
        }
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
        }

        properties.keySet()
                .forEach(k -> {
                    appiumMap.put(k.toString(), properties.get(k).toString());
                });

        return appiumMap;
    }
}
