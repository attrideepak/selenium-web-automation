package automation.webtest;

import automation.base.BaseTest;
import automation.pageobjects.SelectCityPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v89.performance.Performance;
import org.openqa.selenium.devtools.v89.performance.model.Metric;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class PerformanceMetricsTest extends BaseTest {
    private WebDriver localWebDriver;
    private SelectCityPage selectCityPage;

    @BeforeClass
    public void initialiseClass() {
        localWebDriver = super.driver;
        selectCityPage = new SelectCityPage(localWebDriver);
        localWebDriver.get("https://www.zoomcar.com");
    }

    @Test
    public void getPerformanceMetrics(){
        DevTools devTools = ((ChromeDriver)driver).getDevTools();
        devTools.createSession();
        devTools.send(Performance.enable(Optional.empty()));

        selectCityPage.clickBangaloreCity().clickStartYourJouney();

        List<Metric> metrics = devTools.send(Performance.getMetrics());
        List<String> metricNames = metrics.stream()
                .map(o -> o.getName())
                .collect(Collectors.toList());

        devTools.send(Performance.disable());

        List<String> metricsToCheck = Arrays.asList(
                "Timestamp", "Documents", "Frames", "JSEventListeners",
                "LayoutObjects", "MediaKeySessions", "Nodes",
                "Resources", "DomContentLoaded", "NavigationStart");

        metricsToCheck.forEach( metric -> System.out.println(metric +
                " is : " + metrics.get(metricNames.indexOf(metric)).getValue()));
    }
}
