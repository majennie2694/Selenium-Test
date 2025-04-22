package sampleTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class aboutTest extends BaseTest {

    WebDriver webdriver;

    @BeforeMethod
    public void setUp() {
        // Set up ExtentReports
        setupExtentReports();
        test = extent.createTest("aboutTest", "Verify about button functionality");

        // Set up the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webdriver = new ChromeDriver();
        webdriver.manage().window().maximize();
        test.info("Browser launched and maximized", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Browser_Launched")).build());
    }

    @Test (priority = 2)
    public void TestAboutButton() {
        try {
            // Step 1 - Go to the URL
            String url = "https://www.demoblaze.com/index.html";
            webdriver.get(url);
            test.info("Navigated to " + url, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Navigated_To_URL")).build());

            // Step 2 - Verify the home page is displayed
            String navBarXpath = "//a[@class='navbar-brand']";
            WebElement homePageElement = webdriver.findElement(By.xpath(navBarXpath));
            Assert.assertTrue(homePageElement.isDisplayed(), "Home page is not displayed");
            test.pass("Home page is displayed", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Home_Page_Displayed")).build());

            // Step 3 - Click the about button
            String aboutBtnXpath = "//a[@data-target='#videoModal']";
            WebElement aboutButton = webdriver.findElement(By.xpath(aboutBtnXpath));
            aboutButton.click();
            test.info("Clicked on the about button", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "About_Button_Clicked")).build());

            // Step 4 - Verify the about modal is displayed
            String aboutTitleModalXpath = "//a[@data-target='#videoModal']";
            WebDriverWait wait = new WebDriverWait(webdriver, 10); // Wait up to 10 seconds
            WebElement aboutModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(aboutTitleModalXpath)));
            Assert.assertTrue(aboutModal.isDisplayed(), "About modal is not displayed");
            test.pass("About modal is displayed", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "ABout_Modal_Displayed")).build());
        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Test_Failed")).build());
        }
    }

    @AfterMethod
    public void tearDown() {
        try {
            // Capture a screenshot before closing the browser
            if (webdriver != null) {
                test.info("Browser closed", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Browser_Closed")).build());
            }
        } catch (Exception e) {
            test.warning("Failed to capture screenshot during teardown: " + e.getMessage());
        } finally {
            // Close the browser
            if (webdriver != null) {
                webdriver.quit();
            }
        }

        // Flush ExtentReports
        tearDownExtentReports();
    }
}
