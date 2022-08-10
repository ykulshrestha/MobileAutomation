package configs;

import constants.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PlatformConfig {

    // This functions read platform.properties file and put each key and value in a map object and returns the same
    public  Map readProperties(){
        Map<String, String> platformMap = new HashMap<>();

        ClassLoader cl = getClass().getClassLoader();
        URL resource = cl.getResource(Constant.PATH_TO_PLATFORM_CABABILITIES);
        InputStream is = null;
        try {
            is = resource.openStream();
        } catch (IOException e) {
            throw new RuntimeException("Exception in creating connection with property file: " + Constant.PATH_TO_PLATFORM_CABABILITIES, e);
        }
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
        }

        properties.keySet()
                .forEach(k -> {
                    platformMap.put(k.toString(), properties.get(k).toString());
                });

        return platformMap;
    }
}
