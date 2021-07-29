package automation.core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.SkipException;

public class CreateWebDriver {
    private Logger logger = Logger.getLogger(CreateWebDriver.class);


    public WebDriver getDriver(String browserName) {
        WebDriver driver = null;
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
            return driver;
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-infobars");
            options.addArguments("-private");
            driver = new FirefoxDriver(options);
            return driver;
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);
            return driver;
        } else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
            return driver;
        } else {
            logger.info(
                    "Unsupported browser passed, Please provide browserName as chrome or firefox or safari");
            throw new SkipException(
                    "Unsupported browser passed, Please provide browserName as chrome or firefox or safari");
        }
    }
}


