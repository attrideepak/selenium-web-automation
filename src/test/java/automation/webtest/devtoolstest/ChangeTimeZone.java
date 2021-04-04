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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChangeTimeZone {
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
    public void emulateTimezoneTest() {
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("timezoneId", "Antarctica/Casey");

        devTools.send(Network.enable(Optional.of(100000000), Optional.empty(), Optional.empty()));
        ((ChromeDriver)driver).executeCdpCommand("Emulation.setTimezoneOverride", deviceMetrics);
        driver.get("https://momentjs.com/");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        devTools.send(Network.disable());
        driver.quit();
    }
}
