package automation.webtest;

import automation.base.BaseTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class FileUploadTest extends BaseTest {
    private WebDriver localWebDriver;
    private Logger logger = Logger.getLogger(AlertTest.class);



    @BeforeClass
    public void initialiseClass() {
        localWebDriver = super.driver;
        localWebDriver.get("https://html.com/input-type-file/");
        localWebDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void testPopUp(){
        WebElement element = localWebDriver.findElement(By.id("fileupload"));
        //don't click button
        element.sendKeys("/Users/Aeepakattri/Desktop/Screenshot_1614673229.png");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //<input type="submit" value="submit">
        WebElement submitbutton = localWebDriver.findElement(By.xpath("//input[@type='submit']"));
        submitbutton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
