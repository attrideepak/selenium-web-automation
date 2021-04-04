package automation.webtest.devtoolstest;

import com.google.common.collect.ImmutableList;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v89.network.Network;
import org.openqa.selenium.devtools.v89.network.model.BlockedReason;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

public class BlockUrlTest {
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
    public void blockUrl() {
        String urlToBlock = "https://medium.com/_/graphql";
        devTools.send(Network.enable(Optional.of(100000000), Optional.empty(), Optional.empty()));
        devTools.send(Network.setBlockedURLs(ImmutableList.of(urlToBlock)));

        devTools.addListener(Network.loadingFailed(), loadingFailed -> {
            System.out.println("Blocking reason: " + loadingFailed.getBlockedReason().get());
            Assert.assertEquals(loadingFailed.getBlockedReason().get(), BlockedReason.INSPECTOR);
        });

        driver.get("https://medium.com");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        devTools.send(Network.disable());
        driver.quit();
    }
}
