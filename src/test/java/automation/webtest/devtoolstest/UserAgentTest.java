package automation.webtest.devtoolstest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v89.network.Network;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

public class UserAgentTest {
    private WebDriver driver;
    private DevTools devTools;

    @BeforeMethod
    public void initialiseClass() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
    }

    @Test
    public void userAgentTest(){
            String fakeAgent = "Mozilla/5.0 (Linux; Android 7.0; SM-G930V Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.125 Mobile Safari/537.36";
            devTools.send(Network.setUserAgentOverride(fakeAgent,
                    Optional.empty(), Optional.empty(),Optional.empty()));
            driver.get("https://www.zoomcar.com");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        devTools.send(Network.disable());
        driver.quit();
    }
}
