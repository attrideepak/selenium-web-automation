package automation.core.extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import org.testng.annotations.Parameters;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class ExtentReportUtils {

    static String configPath = System.getProperty("user.dir") + "/src/main/java/automation/configs/extent-report-config";
    static String reportsPath = "target/spark/spark.html";

    private static ExtentReports extentReport = new ExtentReports();
    private static ExtentTest extentTest ;

    static final File conf = new File(configPath);

    public static void setupReports() throws IOException {
            ExtentSparkReporter spark = new ExtentSparkReporter(reportsPath).viewConfigurer().viewOrder().as(new ViewName[]{ViewName.DASHBOARD,ViewName.TEST,ViewName.CATEGORY}).apply();
            spark.loadJSONConfig(conf);
            extentReport.attachReporter(spark);
    }

    public static void tearDownReports(){
        if(extentReport!=null) {
            extentReport.flush();
            try {
                Desktop.getDesktop().browse(new File(reportsPath).toURI());
            } catch (IOException e) {
                e.getMessage();
                System.out.println("No report found, check report name and path!!!");
            }
            ExtentReportManager.removeExtentThread();
        }
    }

    public static void createTest(String testName){
         extentTest = extentReport.createTest(testName);
         ExtentReportManager.setExtentTest(extentTest);
    }

    public static void addAuthors(String[] authors){
        for(String author:authors){
            ExtentReportManager.getExtentTest().assignAuthor(author);
        }
    }

    public static void addCategories(String[] categories){
        for(String category:categories){
            ExtentReportManager.getExtentTest().assignCategory(category);
        }
    }

    public static void setSystemInfo(String browserName){
        extentReport.setSystemInfo("Browser",browserName);
    }
}
