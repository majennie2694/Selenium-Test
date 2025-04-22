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

public class loginTest extends BaseTest {

    WebDriver webdriver;

    @BeforeMethod
    public void setUp() {
        // Set up ExtentReports
        setupExtentReports();
        test = extent.createTest("loginTest", "Verify login functionality");

        // Set up the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webdriver = new ChromeDriver();
        webdriver.manage().window().maximize();
        test.info("Browser launched and maximized", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Browser_Launched")).build());
    }

    @Test (priority = 4)
    public void TestLoginButton() {
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

            // Step 3 - Click the login button
            String loginBtnXpath = "//a[@id='login2']";
            WebElement loginButton = webdriver.findElement(By.xpath(loginBtnXpath));
            loginButton.click();
            test.info("Clicked on the login button", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Login_Button_Clicked")).build());

            // Step 4 - Verify the login modal is displayed
            String loginTitleModalXpath = "//h5[@id='logInModalLabel']";
            WebDriverWait wait = new WebDriverWait(webdriver, 10); // Wait up to 10 seconds
            WebElement loginModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loginTitleModalXpath)));
            Assert.assertTrue(loginModal.isDisplayed(), "Login modal is not displayed");
            test.pass("Login modal is displayed", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Login_Modal_Displayed")).build());
            
            // Step 5 - Close modal
            String closeBtnXpath = "//button[@class='btn btn-secondary']";
            WebElement closeButton = webdriver.findElement(By.xpath(closeBtnXpath));
            closeButton.click();
            test.info("Closed the login modal", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Closed_Button_Clicked")).build());
            
            //Step 6 - Click the Cart button
           // String cartBtnXpath = "////b[@class='cartur']";
           // WebElement cartButton = webdriver.findElement(By.xpath(cartBtnXpath));
           // cartButton.click();
           // test.info("Clicked on the login button", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Cart_Button_Clicked")).build());
        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Test_Failed")).build());
        }
    }
    
    //public void cartTest() {
    //    try {
            // Step 1 - Go to the URL
            //String url = "https://www.demoblaze.com/index.html";
            //webdriver.get(url);
            //test.info("Navigated to " + url, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Navigated_To_URL")).build());

            // Step 2 - Verify the home page is displayed
            //String navBarXpathCart = "//a[@class='navbar-brand']";
            //WebElement cartPageElement = webdriver.findElement(By.xpath(navBarXpathCart));
            //Assert.assertTrue(cartPageElement.isDisplayed(), "Home page is not displayed");
            //test.pass("Home page is displayed", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Home_Page_Displayed")).build());

            // Step 3 - Click the cart button
   //         String loginBtnXpath = "//div[@class='cartur']";
   //         WebElement loginButton = webdriver.findElement(By.xpath(loginBtnXpath));
   //         loginButton.click();
   //         test.info("Clicked on the login button", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Login_Button_Clicked")).build());

            // Step 4 - Verify the product page is displayed
    //        String navBarXpath = "//div[@class='panel panel-info']";
    //        WebElement homePageElement = webdriver.findElement(By.xpath(navBarXpath));
    //        Assert.assertTrue(homePageElement.isDisplayed(), "Products page is not displayed");
    //        test.pass("Products page is displayed", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Products_Page_Displayed")).build());
    //    	} catch (Exception e) {
    //        test.fail("Test failed: " + e.getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Test_Failed")).build());
     //   }
    //}

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
