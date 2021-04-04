package automation.webtest.devtoolstest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v89.network.Network;
import org.openqa.selenium.devtools.v89.network.model.Cookie;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CookiesTest {
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
    public void getAllCookies() {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        driver.get("https://google.com");

        //Getting all cookies
        List<Cookie> cookies = devTools.send(Network.getAllCookies());
        cookies.forEach(cookie -> System.out.println(cookie.getName()));
        List<String> cookieName = cookies.stream().map(cookie -> cookie.getName()).sorted().collect(Collectors.toList());
        Set<org.openqa.selenium.Cookie> seleniumCookie = driver.manage().getCookies();
        List<String> selCookieName = seleniumCookie.stream().map(selCookie -> selCookie.getName()).sorted().collect(Collectors.toList());
        Assert.assertEquals(cookieName, selCookieName);

        //Clearing browser cookies
        devTools.send(Network.clearBrowserCookies());
        List<Cookie> cookiesAfterClearing = devTools.send(Network.getAllCookies());
        Assert.assertTrue(cookiesAfterClearing.isEmpty());


    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        devTools.send(Network.disable());
        driver.quit();
    }
}
