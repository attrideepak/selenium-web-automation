package automation.base;

import automation.core.allurelistener.AllureListener;
import automation.core.driver.CreateWebDriver;
import automation.core.driver.LocalDriverManager;
import automation.core.extentreport.ExtentReportListner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.time.Duration;

@Listeners({AllureListener.class,ExtentReportListner.class})
public class BaseTest {

    private WebDriver driver = null;
    private CreateWebDriver createWebDriver = new CreateWebDriver();
    private Logger logger = LogManager.getLogger(BaseTest.class);
    public WebDriver threadLocalDriver = null;


    @BeforeTest(alwaysRun = true)
    @Parameters({"browserName"})
    public void beforeTest(@Optional(value = "chrome") String browserName) {
        driver = createWebDriver.getDriver(browserName);
        LocalDriverManager.setWebDriver(driver);
        LocalDriverManager.getDriver().manage().window().maximize();
        LocalDriverManager.getDriver().get("https://www.zoomcar.com/bangalore/");
        LocalDriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @AfterTest(alwaysRun = true)
    public void afterTest() {
        if (LocalDriverManager.getDriver() != null) {
            LocalDriverManager.getDriver().quit();
            LocalDriverManager.removeWebDriver();
        }
    }
}
