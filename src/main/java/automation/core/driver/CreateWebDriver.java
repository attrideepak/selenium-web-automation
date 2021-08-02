package automation.core.driver;

import org.openqa.selenium.WebDriver;
import java.net.MalformedURLException;

public class CreateWebDriver {
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


