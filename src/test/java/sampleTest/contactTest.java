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

public class contactTest extends BaseTest {

    WebDriver webdriver;

    @BeforeMethod
    public void setUp() {
        // Set up ExtentReports
        setupExtentReports();
        test = extent.createTest("contactTest", "Verify contact button functionality");

        // Set up the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webdriver = new ChromeDriver();
        webdriver.manage().window().maximize();
        test.info("Browser launched and maximized", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Browser_Launched")).build());
    }

    @Test(priority = 1)
    public void TestContactButton() {
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

            // Step 3 - Click the contact button
            String contactBtnXpath = "//a[@data-target='#exampleModal']";
            WebElement contactButton = webdriver.findElement(By.xpath(contactBtnXpath));
            contactButton.click();
            test.info("Clicked on the contact button", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Contact_Button_Clicked")).build());

            // Step 4 - Verify the contact modal is displayed
            String contactTitleModalXpath = "//h5[@id='exampleModalLabel']";
            WebDriverWait wait = new WebDriverWait(webdriver, 10); // Wait up to 10 seconds
            WebElement contactModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contactTitleModalXpath)));
            Assert.assertTrue(contactModal.isDisplayed(), "Contact modal is not displayed");
            test.pass("Login modal is displayed", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Contact_Modal_Displayed")).build());
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
