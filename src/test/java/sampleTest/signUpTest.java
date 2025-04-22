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

public class signUpTest extends BaseTest {

    WebDriver webdriver;

    @BeforeMethod
    public void setUp() {
        // Set up ExtentReports
        setupExtentReports();
        test = extent.createTest("signupTest", "Verify sign up button functionality");

        // Set up the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webdriver = new ChromeDriver();
        webdriver.manage().window().maximize();
        test.info("Browser launched and maximized", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Browser_Launched")).build());
    }

    @Test (priority = 5)
    public void TestSignUpButton() {
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

            // Step 3 - Click the signup button
            String signUpBtnXpath = "//a[@id='signin2']";
            WebElement signUpButton = webdriver.findElement(By.xpath(signUpBtnXpath));
            signUpButton.click();
            test.info("Clicked on the sign up button", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Sign_Up_Button_Clicked")).build());

            // Step 4 - Verify the sign up modal is displayed
            String signUpTitleModalXpath = "//h5[@id='signInModalLabel']";
            WebDriverWait wait = new WebDriverWait(webdriver, 10); // Wait up to 10 seconds
            WebElement signUpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(signUpTitleModalXpath)));
            Assert.assertTrue(signUpModal.isDisplayed(), "Sign Up modal is not displayed");
            test.pass("Sign Up modal is displayed", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Sign_Up_Modal_Displayed")).build());
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
