package automation.webtest;

import automation.base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SelectDropDown extends BaseTest {

    private WebDriver localWebDriver;


    @BeforeClass
    public void initialiseClass() {
        localWebDriver = threadLocalDriver;
        localWebDriver.get("https://facebook.com");
    }

    @Test
    public void dropDown(){

    }
}
