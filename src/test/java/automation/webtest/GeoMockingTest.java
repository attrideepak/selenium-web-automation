package automation.webtest;

import automation.base.BaseTest;
import automation.pageobjects.SelectCityPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v89.emulation.Emulation;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

public class GeoMockingTest extends BaseTest {
    private WebDriver localWebDriver;
    private Logger logger = Logger.getLogger(HomePageTest.class);

    @BeforeClass
    public void initialiseClass() {
        localWebDriver = super.driver;
    }

    @Test
    public void mockLocation(){
        DevTools devTools = ((ChromeDriver)localWebDriver).getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setGeolocationOverride(
                Optional.of(35.8235),
                Optional.of(-78.8256),
                Optional.of(100)));
        driver.get("https://mycurrentlocation.net/");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
