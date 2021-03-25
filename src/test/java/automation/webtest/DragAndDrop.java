package automation.webtest;

import automation.base.BaseTest;
import automation.pageobjects.SelectCityPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DragAndDrop extends BaseTest {
    private WebDriver localWebDriver;


    @BeforeClass
    public void initialiseClass() {
        localWebDriver = super.driver;
         localWebDriver.get("https://jqueryui.com/droppable/");
    }

    @Test
    public void testDragDrop(){
        localWebDriver.switchTo().frame(0);
        Actions actions = new Actions(localWebDriver);
        actions.clickAndHold(localWebDriver.findElement(By.xpath("//*[@id ='draggable']")))
                .moveToElement(localWebDriver.findElement(By.xpath("//div[@id='droppable']")))
                .release()
                .build()
                .perform();

    }
}
