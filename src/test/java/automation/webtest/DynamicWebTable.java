package automation.webtest;

import automation.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DynamicWebTable extends BaseTest {
    private WebDriver localWebDriver;


    @BeforeClass
    public void initialiseClass() {
        localWebDriver = threadLocalDriver;
        localWebDriver.get("https://ui.cogmento.com/");
    }

    @Test
    public void webTableTest() throws InterruptedException {
        WebElement emailEditBox = localWebDriver.findElement(By.xpath("//input[@name='email']"));
        WebElement passwordEditBox = localWebDriver.findElement(By.xpath("//input[@name='password']"));
        WebElement loginButton = localWebDriver.findElement(By.xpath("//div[contains(text(),'Login')]"));
        emailEditBox.sendKeys("deepak.attri02@gmail.com");
        passwordEditBox.sendKeys("Rajnikant");
        loginButton.click();
        Thread.sleep(5000);
        localWebDriver.navigate().to("https://ui.cogmento.com/contacts");
        Thread.sleep(5000);

        ////*[@id="main-content"]/div/div[2]/div/table/tbody/tr[1]
        ////*[@id="main-content"]/div/div[2]/div/table/tbody/tr[2]
        ////*[@id="main-content"]/div/div[2]/div/table/tbody/tr[3]

        //*[@id="main-content"]/div/div[2]/div/table/tbody/tr[1]/td[2]
        //*[@id="main-content"]/div/div[2]/div/table/tbody/tr[2]/td[2]
        //*[@id="main-content"]/div/div[2]/div/table/tbody/tr[3]/td[2]

        //*[@id="main-content"]/div/div[2]/div/table/tbody/tr[1]/td[1]/div/label
        //*[@id="main-content"]/div/div[2]/div/table/tbody/tr[2]/td[1]/div/label

//        String beforeXpath = "//*[@id='main-content']/div/div[2]/div/table/tbody/tr[";
//        String afterXpath = "]/td[2]";
//
//        for(int i = 1; i<=3; i++){
//            String name = localWebDriver.findElement(By.xpath(beforeXpath +i+ afterXpath)).getText();
//            System.out.println(name);
//            if(name.contains("beta sharma")){
//                localWebDriver.findElement(By.xpath(beforeXpath +i+"]/td[1]/div/label")).click();
//                Thread.sleep(4000);
//            }
//        }
        Thread.sleep(5000);
        WebElement element = localWebDriver.findElement(By.xpath("//a[contains(text(),'alpha singh')]/parent::td/preceding-sibling::td/div//input[@class='hidden']"));
//        Actions actions = new Actions(localWebDriver);
//        actions.moveToElement(element).click().perform();
        JavascriptExecutor jse = (JavascriptExecutor)threadLocalDriver;
        jse.executeScript("arguments[0].click()", element);
        Thread.sleep(5000);

    }
}
