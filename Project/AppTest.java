package Project;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AppTest {
	ExtentReports report;
	ExtentTest test;
	WebDriver driver;
	
	// pass scenario
	Home home;
	SignIn signIn;

	static String userName ="alokjunk@hotmail.co.uk";
	static String password= "Testing123";
	static String LastName2 ="Bobson";
	
	
	@BeforeClass
	public void verifyHomePageTitle()  {//this one tests whether or not the page is the right page
		
		// where to create the report file
		report = new ExtentReports(
				"C:\\Users\\Administrator\\Desktop\\eclipse\\Reports\\htmlreport.html",
				true);
		// init/start the test
		test = report.startTest("Navigate to page");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// add a note to the test
		test.log(LogStatus.INFO, "Browser started");
		driver.get("http://automationpractice.com/index.php");
		String title = driver.getTitle();
		System.out.println(title);
		
		if (title.equals(
				"My Store")) {
			// report the test as a pass
			test.log(LogStatus.PASS, "verify Title of the page");
		} else {
			test.log(LogStatus.FAIL, "verify Title of the page");
		}
		report.endTest(test);
		report.flush();
	}
	@Test(priority = 1, enabled = true)//GOES TO THE DRESSES PAGE AND VERIFIES WHETHER OR NOT ITS THE RIGHT PAGE OR NOT
	public void verifyBrowsingPage(){
		
		
		report = new ExtentReports(
				"C:\\Users\\Administrator\\Desktop\\eclipse\\Reports\\htmlreport.html",
				true);
		test = report.startTest("Navigate to Browsing Page and Browse Dress");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		test.log(LogStatus.INFO, "Browser started");
		driver.get("http://automationpractice.com/index.php");
		test.log(LogStatus.INFO, "Clicking on Dresses Button");
		
		
		WebElement datboi = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a"));//clicking on the 'dress' button on the home page.
		datboi.click();
		WebElement catName = driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1/span[1]"));
		String word = catName.getText().trim();
		System.out.println(word);
		
		if(word.equals("DRESSES")){
			test.log(LogStatus.PASS, "Verify Dress Page");
		} else {
			test.log(LogStatus.FAIL, "verify Dress Page");
		}
		report.endTest(test);
		report.flush();	
		driver.close();
	}
	@Test(priority = 2, enabled = true)//this scenario logs in and changes the last name of the person and saves it and checks whether or not the last name has been changed or not.
	public void verifyLogIn() throws InterruptedException{
		report = new ExtentReports(
				"C:\\Users\\Administrator\\Desktop\\eclipse\\Reports\\htmlreport.html",
				true);
		test = report.startTest("Testing Signing In");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		home = new Home(driver);
		signIn = new SignIn(driver);
		driver.get("http://automationpractice.com/index.php");//load the Homepage.
		test.log(LogStatus.INFO, "Clicking Sign In Button");
		home.clickSignIn();
		Thread.sleep(2000);
		test.log(LogStatus.INFO, "Adding Information");
		signIn.getUserName().sendKeys(userName);		
		signIn.getPassword().sendKeys(password);
		signIn.clickLogin();
		try{

			driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a"));//checking for element that is only available after the login is successful. Checking for Bob bobson
			test.log(LogStatus.INFO, "Checking if Log in was successful");
		}
		
		
		catch(Exception e){
			System.out.println(e);
			test.log(LogStatus.INFO, "Checking if Log in was unsuccessful");
		}
		
		test.log(LogStatus.INFO, "Attempting to change last name");
		WebElement personalInfoBtn = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[4]/a/span"));personalInfoBtn.click();
		WebElement lastName2 = driver.findElement(By.name("lastname"));//finds the box of last name
		lastName2.clear();lastName2.sendKeys(LastName2);
		WebElement confirmationPass = driver.findElement(By.name("old_passwd"));
		confirmationPass.sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/form/fieldset/div[11]/button/span")).click();//find save button and click
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")).click();
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[4]/a/span")).click();
		test.log(LogStatus.INFO, "Checking last name");
		
		WebElement surname = driver.findElement(By.name("lastname"));
		String sname = surname.getAttribute("value");
		System.out.println(sname);
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")).click();//find logout button and click
		
		if (sname.equals(LastName2)){
			test.log(LogStatus.PASS, "Successfullly Changed Last Name");
		} else {
			test.log(LogStatus.FAIL, "Couldnt Change Last Name To Desired One");
		}
		report.endTest(test);
		report.flush();		
		driver.close();
	}
	
	
	@Test(priority = 3, enabled = true)//navigates to the dress and buys the dress
	public void browse() throws InterruptedException{
		
		report = new ExtentReports(
				"C:\\Users\\Administrator\\Desktop\\eclipse\\Reports\\htmlreport4.html",
				true);
		test = report.startTest("Navigate to Browsing Page and Browse Dress");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		
		home = new Home(driver);
		signIn = new SignIn(driver);

		
		test.log(LogStatus.INFO, "Browser started");
		driver.get("http://automationpractice.com/index.php");
		test.log(LogStatus.INFO, "rowsing what to buy");
		driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a")).click();//click on dress page
		test.log(LogStatus.INFO, "That dress looks nice");
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img")).click();//click on the dress 
		
		Thread.sleep(4000);
		driver.navigate().back();//go back to dress page
		test.log(LogStatus.INFO, "actually this one looks better. Imma buy this one");
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[2]/div/div[1]/div/a[1]/img")).click();//click on a second dress
		driver.findElement(By.xpath("//*[@id=\"quantity_wanted_p\"]/a[2]/span/i")).click();//add more to count
		driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span")).click();//click on add to cart
		

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a")).click();//click on continue
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span")).click();
		
		Thread.sleep(5000);
		test.log(LogStatus.INFO, "Adding Information to log in");
		signIn.getUserName().sendKeys(userName);		
		signIn.getPassword().sendKeys(password);
		signIn.clickLogin();
		test.log(LogStatus.INFO, "Ordering...");
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button/span")).click();
		driver.findElement(By.xpath("//*[@id=\"cgv\"]")).click();		
		driver.findElement(By.xpath("//*[@id=\"form\"]/p/button/span")).click();
		driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")).click();
		driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button/span")).click();
		
		
		WebElement confimationOrder = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/p/strong"));//<---- xpath for order confirmation. Should say "Your order on My Store is complete."
		String confirm = confimationOrder.getText().trim();
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")).click();//click log out
		
		if (confirm.equals("Your order on My Store is complete.")){
			test.log(LogStatus.PASS, "Successfullly Ordered");
		} else {
			test.log(LogStatus.FAIL, "Order failed");
		}
		report.endTest(test);
		report.flush();	
		driver.close();
		
		
	}
	
	

}