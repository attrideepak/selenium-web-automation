package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class BasicTest {

    String filePath = System.getProperty("user.dir") + "/src/main/java/resources/chromedriver";
    String chromeDriver = "webdriver.chrome.driver";
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        System.setProperty(chromeDriver,filePath);
        driver = new ChromeDriver();
    }

   @Test
    public void launchBrowser(){
       driver.navigate().to("http://www.zoomcar.com/");
       driver.manage().window().maximize();

       WebElement bangaloreCity = driver.findElement(By.xpath("//div[@class = 'name' and contains(text(),'Bangalore')]"));
       try {
           Thread.sleep(3000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       System.out.println(bangaloreCity.getText());
       bangaloreCity.click();
   }

   @AfterClass
    public void afterClass(){
        if(driver!=null){
            driver.quit();
        }
   }

}
