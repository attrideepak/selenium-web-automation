package automation.webtest.devtoolstest;

import automation.base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v89.emulation.Emulation;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

public class GeoMockingTest extends BaseTest {
    private WebDriver localWebDriver;


    @BeforeClass
    public void initialiseClass() {
        localWebDriver = threadLocalDriver;
    }

    @Test
    public void mockLocation(){
        DevTools devTools = ((ChromeDriver)localWebDriver).getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setGeolocationOverride(
                Optional.of(48.8584),
                Optional.of(2.2945),
                Optional.of(100)));
        threadLocalDriver.get("https://mycurrentlocation.net/");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
