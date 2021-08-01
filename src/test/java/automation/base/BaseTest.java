package automation.base;

import automation.core.driver.CreateWebDriver;
import automation.core.driver.LocalDriverManager;
import automation.core.extentreport.ExtentReportManager;
import automation.core.utils.PropertyUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {

    private WebDriver driver = null;
    private CreateWebDriver createWebDriver = new CreateWebDriver();
    private Logger logger = Logger.getLogger(BaseTest.class);
    public WebDriver threadLocalDriver = null;
    String filePath = System.getProperty("user.dir") + "/src/main/resources/runner";
    String value =  PropertyUtils.getProperty(filePath,"mode");

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
