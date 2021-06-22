package com.techproed.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.techproed.utilities.BrowserUtils;
import com.techproed.utilities.ConfigurationReader;
import com.techproed.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.Dimension;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public WebDriver driver;
    public Actions actions;
    public WebDriverWait wait;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports reports;
    public static ExtentTest extentLogger;

    @BeforeTest
    public void setUpTest(){
        //initialize the class
        reports=new ExtentReports();

        //create the location of path
        String projectPath=System.getProperty("user.dir");
        String path=projectPath+"/test-output/report.html";

        //initialize the html class with the path
        htmlReporter=new ExtentHtmlReporter(path);
        

        //attach the html report to the report object
        reports.attachReporter(htmlReporter);

        //title of the html report
        htmlReporter.config().setDocumentTitle("Vytrack Smoke Test");

        reports.setSystemInfo("Environment", "QA");
        reports.setSystemInfo("Browser", ConfigurationReader.get("url"));
        reports.setSystemInfo("OS", System.getProperty("os.name"));


    }

    @BeforeMethod
    public void setup(){
        driver= Driver.get();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        actions=new Actions(driver);
        //driver.get(ConfigurationReader.get("url"));

    }


    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException, IOException {
        //if test fails
        if(result.getStatus()==ITestResult.FAILURE){
            //record the name of failed test case
            extentLogger.fail(result.getName());

            //take the screenshot and return location of screenshot
            String screenShotPath = BrowserUtils.getScreenshot(result.getName());

            //add your screenshot to your report
            extentLogger.addScreenCaptureFromPath(screenShotPath);

            //capture the exception and put inside the report
            extentLogger.fail(result.getThrowable());

        }
        Thread.sleep(2000);
        Driver.closeDriver();
    }

    @AfterTest
    public void tearDownTest(){
        //this is when the report actually created
        reports.flush();
    }

}
