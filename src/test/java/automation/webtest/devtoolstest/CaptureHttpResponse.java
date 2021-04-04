package automation.webtest.devtoolstest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v89.network.Network;
import org.openqa.selenium.devtools.v89.network.model.RequestId;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


public class CaptureHttpResponse {
    private WebDriver driver;
    private DevTools devTools;
    // final  String[] url = new String[1];
    // final  RequestId[] requestid = new RequestId[1];
    final AtomicReference<RequestId> requestid = new AtomicReference<>();
    final AtomicReference<String> url = new AtomicReference<>();

    @BeforeMethod
    public void initialiseClass() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.get("https://www.zoomcar.com/bangalore");
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();

    }

    @Test(enabled = false)
    public void captureResponse() {
        devTools.send(Network.enable(Optional.of(1024 * 1204 * 200), Optional.of(1024 * 1204 * 100), Optional.of(1024 * 1204 * 100)));
        devTools.addListener(Network.responseReceived(),
                response -> {
                    if (response.getResponse().getUrl().contains("api.zoomcar.com")) {
                        url.set(response.getResponse().getUrl());
                        requestid.set(response.getRequestId());
                    }
                });

        devTools.addListener(Network.loadingFinished(), loadingFinished -> {
            if (requestid.get() != null) {
                System.out.println("Request URL: " + url.get());
                System.out.println("Request id " + loadingFinished.getRequestId());
                System.out.println("Response Body: \n" + devTools.send(Network.getResponseBody(requestid.get())).getBody() + "\n");
            }
        });
        driver.findElement(By.className("search")).click();
    }

    @Test(enabled = false)
    public void captureResponseReceived() {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.responseReceived(),
                response -> {
                    System.out.println("URL: " + response.getResponse().getUrl());
                    System.out.println("Status " + response.getResponse().getStatus());
                    System.out.println("Type" + response.getType().toJson());
                });
        driver.get("https://www.zoomcar.com/bangalore");
        driver.findElement(By.className("search")).click();

    }

    @Test
    public void validateResponse() {
        final RequestId[] requestIds = new RequestId[1];
        devTools.send(Network.enable(Optional.of(100000000), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.responseReceived(), responseReceived -> {
            if (responseReceived.getResponse().getUrl().contains("api.zoomcar.com")) {
                System.out.println("URL: " + responseReceived.getResponse().getUrl());
                System.out.println("Status: " + responseReceived.getResponse().getStatus());
                System.out.println("Type: " + responseReceived.getType().toJson());
                responseReceived.getResponse().getHeaders().toJson().forEach((k, v) -> System.out.println((k + ":" + v)));
                requestIds[0] = responseReceived.getRequestId();
                System.out.println("Response Body: \n" + devTools.send(Network.getResponseBody(requestIds[0])).getBody() + "\n");
            }
        });
        driver.get("https://www.zoomcar.com/bangalore");
        driver.findElement(By.className("search")).click();
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        devTools.send(Network.disable());
        devTools.close();
        driver.quit();
    }
}
