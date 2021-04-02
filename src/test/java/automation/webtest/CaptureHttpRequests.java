package automation.webtest;

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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

public class CaptureHttpRequests {
    private WebDriver driver;
    DevTools devTools;

    @BeforeClass
    public void initialiseClass() {
        System.setProperty("webdriver.chrome.verboseLogging", "false");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);

        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(),
                entry -> {
                    if (entry.getRequest().getUrl().contains("https://api.segment.io")) {
                        System.out.println("Request Method : " + entry.getRequest().getMethod());
                        System.out.println("Request URI : " + entry.getRequest().getUrl() + "\n");
                        System.out.println("Request headers:");
                        entry.getRequest().getHeaders().toJson().forEach((k, v) -> System.out.println((k + ":" + v)));
                        if (entry.getRequest().getMethod().equalsIgnoreCase("get")) {
                            System.out.println("Not request body found for get request");
                        } else {
                            System.out.println("\n" + "Request Body:");
                            String postData = entry.getRequest().getPostData().get();
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            String prettyJsonString = gson.toJson(JsonParser.parseString(postData));
                            System.out.println(prettyJsonString);
                        }
                    }
                });
    }

    @Test
    public void captureNetworkCalls() {
        driver.get("https://www.zoomcar.com/bangalore");
        driver.findElement(By.className("search")).click();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        devTools.send(Network.disable());
        driver.quit();
    }
}
