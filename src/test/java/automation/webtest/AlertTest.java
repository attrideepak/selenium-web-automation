package automation.webtest;

import automation.base.BaseTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertTest extends BaseTest {
    private WebDriver localWebDriver;
    private Logger logger = Logger.getLogger(AlertTest.class);



    @BeforeClass
    public void initialiseClass() {
        localWebDriver = super.driver;
        localWebDriver.get("http://register.rediff.com/register/register.php?FormName=user_details");
        localWebDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void testPopUp(){
        WebElement element = localWebDriver.findElement(By.id("Register"));
        element.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Alert alert = localWebDriver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
