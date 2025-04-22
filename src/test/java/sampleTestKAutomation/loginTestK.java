package sampleTestKAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.BaseTest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;

public class loginTestK extends BaseTest {
	
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


	@Test 
	public void loginTestDemoB() {
		
		//Step 1: Open/select Browser
		
		//webdriver = new ChromeDriver(); //open a Chrome Driver from a Repository
		 //String url = "https://www.demoblaze.com/index.html";
         //webdriver.get(url);
         //test.info("Navigated to " + url, MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Navigated_To_URL")).build());
		try {
            // Step 1 - Go to the URL
			//webdriver = new ChromeDriver();
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
        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(webdriver, "Test_Failed")).build());
        }
    }
		
		
		//Step 2: Go to URL https://www.demoblaze.com/index.html#
		//Step 3: Click the log in button
		//Step 4: Check if login button is working/model should be open
	
	
}
