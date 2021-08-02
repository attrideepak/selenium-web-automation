package automation.webtest;

import automation.core.utils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class DummyTest {

    private static final Logger logger = LogManager.getLogger(DummyTest.class);

    String filePath = System.getProperty("user.dir") + "/src/main/resources/runner";
    @Test
    public void dummyTest(){
        String value =  PropertyUtils.getProperty(filePath,"mode");
        logger.info(value);
        logger.warn(value);
        logger.debug(value);
        logger.error(value);
    }
}
