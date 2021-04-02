package automation.webtest;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CaptureHttpRequestUsingBrowserMob {

    public WebDriver driver;
    public BrowserMobProxy proxy;
    public String harFilePath = System.getProperty("user.dir") + "/target/har-files/";

    @BeforeClass
    public void setup(){
        //start proxy server
        System.setProperty("webdriver.chrome.verboseLogging", "true");
        proxy = new BrowserMobProxyServer();
        proxy.setTrustAllServers(true);
        proxy.start(0);

        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        // configure it as a desired capability
        options.setCapability(CapabilityType.PROXY, seleniumProxy);
        options.addArguments("--incognito");
        options.setAcceptInsecureCerts(true);
      //  options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);


        // start the browser up
        WebDriver driver = new ChromeDriver(options);

        // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_HEADERS, CaptureType.REQUEST_CONTENT);

        // create a new HAR with the label "zoomcar.com"
        proxy.newHar("makemytrip.com");


        // open zoomcar.com
        driver.get("http://makemytrip.com");

        // get the HAR data
        Har har = proxy.getHar();
    }

    @Test
    public void testNetworkTraffic(){
        System.out.println("Selecting bangalore city");
        System.out.println("Clicking on search box");
        //<a class="primaryBtn font24 latoBold widgetSearchBtn ">Search</a>
        driver.findElement(By.xpath("//a[contains(text(),'Bangalore')]")).click();
        driver.findElement(By.className("search")).click();
    }

    @AfterTest
    public void tearDown() {
        // get the HAR data
        Har har = proxy.getHar();

        String timeStamp = String.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        // Write HAR Data in a File
        String harFileName = harFilePath+timeStamp+".har";
        File harFile = new File(harFileName);
        try {
            har.writeTo(harFile);
        } catch (IOException ex) {
            System.out.println (ex.toString());
            System.out.println("Could not find file " + harFileName);
        }
        if (driver != null) {
            proxy.stop();
            driver.quit();
        }
    }

    public void readHar(){

    }
}
