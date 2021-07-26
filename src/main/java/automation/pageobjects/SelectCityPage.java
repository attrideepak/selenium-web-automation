package automation.pageobjects;

import automation.core.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class SelectCityPage {
    private WebDriver localWebDriver;
    WebDriverWait wait;
    CommonActions commonActions;

    public SelectCityPage(WebDriver webDriver){
        localWebDriver = webDriver;
        commonActions = new CommonActions(localWebDriver);
        wait = new WebDriverWait(localWebDriver, Duration.ofSeconds(30));
        PageFactory.initElements(localWebDriver, this);
    }

    //<div class="name">Bangalore</div>
    @FindBy(xpath = "//div[@class = 'name' and contains(text(),'Bangalore')]")
    private WebElement bangaloreCity;

    //<a class="search" href="/bangalore/search" title="Start your wonderful journey">Start your wonderful journey</a>
    @FindBy(className = "search")
    private WebElement startYourJourney;

    public SelectCityPage clickBangaloreCity(){
       commonActions.clickElement(bangaloreCity);
       return this;
    }

    public void clickStartYourJouney(){
        commonActions.clickElement(startYourJourney);
    }
}
