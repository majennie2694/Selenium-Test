package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    protected static ExtentReports extent;
    protected static ExtentTest test;

    // Initialize ExtentReports
    public static void setupExtentReports() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("DemoBlaze Test Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    // Flush ExtentReports
    public static void tearDownExtentReports() {
        if (extent != null) {
            extent.flush();
        }
    }

    // Capture Screenshot
    public static String captureScreenshot(WebDriver driver, String stepName) {
        // Generate a timestamp for the screenshot file
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotPath = "screenshots/" + stepName + "_" + timestamp + ".png";

        // Take the screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Save the screenshot to the desired location
        try {
            FileUtils.copyFile(screenshot, new File(screenshotPath));
            return screenshotPath; // Return the path for ExtentReports
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
            return null;
        }
    }
}