package automation.base;

import automation.core.CreateWebDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class BaseTest {

    public WebDriver driver = null;
    public WebDriverWait wait;
    public CreateWebDriver createWebDriver = new CreateWebDriver();
    private Logger logger = Logger.getLogger(BaseTest.class);



    @BeforeTest
    @Parameters({"browserName"})
    public void beforeTest(@Optional(value = "safari") String browserName){
            driver = createWebDriver.getDriver(browserName);
            driver.manage().window().maximize();
            driver.get("https://zoomcar.com");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterTest
    public void afterTest(){
            driver.quit();
    }
}
