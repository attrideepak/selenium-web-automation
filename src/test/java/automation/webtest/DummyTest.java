package automation.webtest;

import automation.core.utils.PropertyUtils;
import org.testng.annotations.Test;

public class DummyTest {

    String filePath = System.getProperty("user.dir") + "/src/main/resources/runner";
    @Test
    public void dummyTest(){
        String value =  PropertyUtils.getProperty(filePath,"mode");
        System.out.println(value);
    }
}
