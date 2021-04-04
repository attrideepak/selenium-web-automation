package automation.webtest.devtoolstest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v89.network.Network;
import org.openqa.selenium.devtools.v89.network.model.Headers;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BasicAuthenticationTest {
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
    public void basicAuthTest() {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        String userName = "guest";
        String password = "guest";
        driver.get("https://jigsaw.w3.org/HTTP/");

        Map<String, Object> headers = new HashMap<>();
        String basicAuth = "Basic " + new String(new Base64().encode(String.format("%s:%s", userName, password).getBytes()));
        headers.put("Authorization", basicAuth);
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.findElement(By.linkText("Basic Authentication test")).click();

        String loginSuccessMsg = driver.findElement(By.tagName("html")).getText();
        Assert.assertTrue(loginSuccessMsg.contains("Your browser made it!"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        devTools.send(Network.disable());
        driver.quit();
    }
}
