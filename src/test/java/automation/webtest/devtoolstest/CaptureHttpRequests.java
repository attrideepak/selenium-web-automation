package automation.webtest.devtoolstest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v89.network.Network;
import org.openqa.selenium.devtools.v89.network.model.RequestId;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

public class CaptureHttpRequests {
    private WebDriver driver;
    DevTools devTools;
    static RequestId requestid;

    @BeforeClass
    public void initialiseClass() {
        System.setProperty("webdriver.chrome.verboseLogging", "false");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
    }

    @Test
    public void captureNetworkCalls() {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(),
                entry -> {
                    requestid = entry.getRequestId();
                    System.out.println("Request Method : " + entry.getRequest().getMethod());
                    System.out.println("Request URI : " + entry.getRequest().getUrl());
                    System.out.println("Request headers:");
                    entry.getRequest().getHeaders().toJson().forEach((k, v) -> System.out.println((k + ":" + v)));
                    Optional<String> postData = entry.getRequest().getPostData();
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                    postData.ifPresentOrElse(p -> System.out.println("Request Body: \n" + gson.toJson(JsonParser.parseString(p)) + "\n"),
//                            () -> System.out.println("Not request body found \n"));

                });
        driver.get("https://www.booking.com");
        driver.findElement(By.className("bui-button__text")).click();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        devTools.send(Network.disable());
        driver.quit();
    }
}
