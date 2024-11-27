package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPERTIES = "config/project.properties";
    public static Properties properties;

    public static void initialize(){
        //Load Default Properties
        properties = loadProperties();

        //Check for any override
        for(String key: properties.stringPropertyNames()){
            if(System.getProperties().containsKey(key)){
                properties.setProperty(key, System.getProperty(key));
            }
        }
        //Print in console to debug
        logger.info("Test properties are:");
        logger.info("---------------------------------");
        for(String key: properties.stringPropertyNames()){
            logger.info("{}={}", key, properties.getProperty(key));
        }
        logger.info("---------------------------------");
    }

    public static String get(String key){
        return properties.getProperty(key);
    }

    private static Properties loadProperties(){
        Properties properties = new Properties();
        try(InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES)){
            properties.load(stream);
        }catch (Exception e){
            logger.error("Unable to load Properties from: {}", DEFAULT_PROPERTIES, e);
        }
        return properties;
    }
}
