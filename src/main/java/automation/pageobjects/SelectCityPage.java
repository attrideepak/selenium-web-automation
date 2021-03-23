package automation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

public class SelectCityPage {
    private WebDriver localWebDriver;
    WebDriverWait wait;

    public SelectCityPage(WebDriver webDriver){
        localWebDriver = webDriver;
        wait = new WebDriverWait(localWebDriver, 30);
        PageFactory.initElements(localWebDriver, this);
    }

    @FindBy(xpath = "//div[@class = 'name' and contains(text(),'Bangalore')]")
    private WebElement bangaloreCity;

    public void clickBangaloreCity(){
        wait.until(ExpectedConditions.visibilityOf(bangaloreCity));
        bangaloreCity.click();
    }
}
