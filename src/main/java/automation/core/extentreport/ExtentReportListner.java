package automation.core.extentreport;

import automation.core.annotations.ExtentReportAnnotation;

import com.aventstack.extentreports.Status;
import org.testng.*;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.Arrays;

public class ExtentReportListner implements ISuiteListener, ITestListener {


    @Override
    public void onStart(ISuite suite) {
        try {
            ExtentReportUtils.setupReports();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReportUtils.tearDownReports();

    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportUtils.createTest(result.getMethod().getDescription());
        ExtentReportUtils.addAuthors(result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(ExtentReportAnnotation.class).author());
        ExtentReportUtils.addCategories(result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(ExtentReportAnnotation.class).category());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getExtentTest().pass(result.getMethod().getMethodName()+ " is passed");
        ExtentReportManager.getExtentTest().assignDevice(result.getTestContext().getCurrentXmlTest().getParameter("browserName"));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReportManager.getExtentTest().fail(result.getMethod().getMethodName()+ " is failed");
        ExtentReportManager.getExtentTest().log(Status.FAIL,result.getThrowable());
        ExtentReportManager.getExtentTest().assignDevice(result.getTestContext().getCurrentXmlTest().getParameter("browserName"));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getExtentTest().skip(result.getMethod().getMethodName()+ " is skipped");
        ExtentReportManager.getExtentTest().log(Status.SKIP,result.getThrowable());
        ExtentReportManager.getExtentTest().assignDevice(result.getTestContext().getCurrentXmlTest().getParameter("browserName"));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        ExtentReportUtils.setSystemInfo(context.getCurrentXmlTest().getParameter("browserName"));
    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
