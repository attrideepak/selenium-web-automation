package automation.webtest;

import automation.base.BaseTest;
import automation.pageobjects.SelectCityPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v89.network.Network;
import org.openqa.selenium.devtools.v89.network.model.ConnectionType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

public class EmulateNetwokSpeedTest extends BaseTest {
    private WebDriver localWebDriver;
    private Logger logger = Logger.getLogger(HomePageTest.class);


    @BeforeClass
    public void initialiseClass() {
        localWebDriver = super.driver;
    }

    @Test
    public void simulateNetwork(){
        DevTools devTools = ((ChromeDriver)driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(
                false,
                20,
                20,
                50,
                Optional.of(ConnectionType.CELLULAR2G)
        ));
        driver.get("https://www.zoomcar.com"); //simulate network and the launch
    }
}
