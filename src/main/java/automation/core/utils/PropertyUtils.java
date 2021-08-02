package automation.core.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Deepak Attri
 * <p>This class is used for reading property values.
 */
public class PropertyUtils {
    private static Logger logger = LogManager.getLogger(PropertyUtils.class);

    public static String getProperty(String filePath,String propertyKey) {
        String propertyValue =  initializePropertiesFiles(filePath).getProperty(propertyKey.trim());
        if (propertyValue == null || propertyValue.trim().length() == 0) {
            System.out.println("Unable to read property !!");
        }
        return propertyValue;
    }


    public static void setProperty(String propertyKey, String value,String propertyFilePath) {
        initializePropertiesFiles(propertyFilePath).setProperty(propertyKey, value);
    }

    public static Properties initializePropertiesFiles(String filePath) {
        Properties pr = new Properties();
        try {
            logger.info("Loading properties from path : " + filePath);
            FileInputStream fis = new FileInputStream(filePath);
            pr.load(fis);
            logger.info("Properties initialised");
        } catch (Exception e) {
            logger.error("Unable to initialise property file!\n " + e.getStackTrace());
        }
        return pr;
    }

}
