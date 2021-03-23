package automation.webtest;

import automation.base.BaseTest;
import automation.pageobjects.SelectCityPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {
    private WebDriver localWebDriver;
    private Logger logger = Logger.getLogger(HomePageTest.class);
    private SelectCityPage selectCityPage;

    @BeforeClass
    public void initialiseClass() {
        localWebDriver = super.driver;
        selectCityPage = new SelectCityPage(localWebDriver);
    }

    @Test
    public void selectCity(){
        selectCityPage.clickBangaloreCity();
    }

}
