package automation.webtest;

import automation.base.BaseTest;
import automation.pageobjects.SelectCityPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v89.network.Network;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

public class CaptureHttpRequests extends BaseTest {
    private WebDriver localWebDriver;
    private Logger logger = Logger.getLogger(HomePageTest.class);
    private SelectCityPage selectCityPage;

    @BeforeClass
    public void initialiseClass() {
        localWebDriver = super.driver;
        selectCityPage = new SelectCityPage(localWebDriver);
       // localWebDriver.get("https://www.zoomcar.com");
    }

    @Test
    public void captureNetworkCalls(){
        DevTools devTools = ((ChromeDriver)driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(),
                entry -> {
                    System.out.println("Request URI : " + entry.getRequest().getUrl()+"\n"
                            + " With method : "+entry.getRequest().getMethod() + "\n");
                    entry.getRequest().getMethod();
                });

        localWebDriver.get("https://www.zoomcar.com");
        selectCityPage.clickBangaloreCity().clickStartYourJouney();

        devTools.send(Network.disable());

    }
}
