package automation.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.SkipException;

import java.util.logging.Level;


public class CreateWebDriver {
    private Logger logger = Logger.getLogger(CreateWebDriver.class);


    public WebDriver getDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            //System.setProperty("webdriver.chrome.logfile", "D:\\chromedriver.log"); //Writing logs to external file
            System.setProperty("webdriver.chrome.verboseLogging", "false");
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            return new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            return new SafariDriver();
        } else {
            logger.info(
                    "Unsupported browser passed, Please provide browserName as chrome or firefox or safari");
            throw new SkipException(
                    "Unsupported browser passed, Please provide browserName as chrome or firefox or safari");
        }
    }
}


