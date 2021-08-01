package automation.core.driver;

import automation.core.utils.PropertyUtils;
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

import java.net.MalformedURLException;
import java.sql.DriverManager;

public class CreateWebDriver {
    private Logger logger = Logger.getLogger(CreateWebDriver.class);


    public WebDriver getDriver(String browserName) {
        WebDriver driver = null;
        try {
            driver = DriverFactory.getDriver(browserName);
        } catch (MalformedURLException e) {
           throw new RuntimeException("Incorrect URL");
        }
        return driver;
    }
}


