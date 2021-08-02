package automation.core.driver;

import automation.core.utils.PropertyUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.SkipException;

import java.net.MalformedURLException;
import java.net.URL;

public final class DriverFactory {

    private DriverFactory(){};

    private static Logger logger = LogManager.getLogger(CreateWebDriver.class);
    private static String filePath = System.getProperty("user.dir") + "/src/main/resources/runner";
    private static String mode =  PropertyUtils.getProperty(filePath,"mode");

    public static WebDriver getDriver(String browserName) throws MalformedURLException {
        WebDriver driver = null;
        if (browserName.equalsIgnoreCase("chrome")) {
            if(mode.equalsIgnoreCase("local")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
            }else {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName(BrowserType.CHROME);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                options.addArguments("--headless");
                options.addArguments("start-maximized");
                capabilities.setCapability(ChromeOptions.CAPABILITY,options);
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
            }
        } else if (browserName.equalsIgnoreCase("firefox")) {
            if(mode.equalsIgnoreCase("local")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--disable-infobars");
                options.addArguments("-private");
                driver = new FirefoxDriver(options);
            }else{
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName(BrowserType.FIREFOX);
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--disable-infobars");
                options.addArguments("-private");
                capabilities.setCapability(ChromeOptions.CAPABILITY,options);
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
            }
        } else if (browserName.equalsIgnoreCase("edge")) {
            if(mode.equalsIgnoreCase("local")) {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                driver = new EdgeDriver(options);
            }else{
                System.out.println("code for remote browser");
            }
        } else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            logger.info(
                    "Unsupported browser passed, Please provide browserName as chrome or firefox or safari");
            throw new SkipException(
                    "Unsupported browser passed, Please provide browserName as chrome or firefox or safari");
        }
        return driver;
    }
}
