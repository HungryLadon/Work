package Project;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Testing1 {
	static WebDriver driver;
	static Home home;
	static SignIn signIn;
	
	static String userName ="alokjunk@hotmail.co.uk";
	static String password= "Testing123";
	static String LastName2 ="Bobson";
	
	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Desktop\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		home = new Home(driver);
		signIn = new SignIn(driver);
		driver.get("http://automationpractice.com/index.php");//load the Homepage.




	}
	
	public void testRegister() throws InterruptedException {
		//creates account, goes to account page, logs out.
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation");
		
		
		String emailAddr = "alokjunk@hotmail.co.uk";//will have to change it when making another account
		String firstname = "Bob";
		String seconname = "Marley";
		String password = "Testing123";
		String Address ="13 Court Street";
		String Address2 = "";
		String City = "Z-City";
		String PostCode ="66441";
		String PhoneMobile ="07833888211";
		
		WebElement email = driver.findElement(By.name("email_create"));//returns box to write the email address on.
		WebElement btn = driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]/span"));//get the button
		email.sendKeys(emailAddr);//writes the email address 
		btn.click();//clicks the buttons
		
		Thread.sleep(3000);
		WebElement rbutton = driver.findElement(By.xpath("//*[@id=\"id_gender1\"]"));//set value as 1 for male value or 2 for female.
		rbutton.click();
		WebElement customerFirstname = driver.findElement(By.name("customer_firstname"));//first name on the register pagw
		customerFirstname.sendKeys(firstname);
		WebElement customerLastname = driver.findElement(By.name("customer_lastname"));//last name on the register page
		customerLastname.sendKeys(seconname);
		WebElement passwordBox = driver.findElement(By.name("passwd"));//password
		passwordBox.sendKeys(password);
		
		WebElement days = driver.findElement(By.xpath("//*[@id=\"days\"]"));
		days.findElement(By.xpath("//*[@id=\"days\"]/option[10]")).click();//find xpath for the value. click to get the dates out and click on the xpath of the value you want the date to be on.
		WebElement month = driver.findElement(By.xpath("//*[@id=\"months\"]"));
		month.findElement(By.xpath("//*[@id=\"months\"]/option[5]")).click();
		WebElement year = driver.findElement(By.xpath("//*[@id=\"years\"]"));
		year.findElement(By.xpath("//*[@id=\"years\"]/option[13]")).click();
		
		
		WebElement address1 = driver.findElement(By.name("address1"));//address bit
		address1.sendKeys(Address);
		
		WebElement city = driver.findElement(By.name("city"));//where city is
		city.sendKeys(City);
		WebElement state = driver.findElement(By.xpath("//*[@id=\"id_state\"]/option[45]"));state.click();//state
				
		WebElement postcode = driver.findElement(By.name("postcode"));//post code
		postcode.sendKeys(PostCode);
		WebElement country = driver.findElement(By.xpath("//*[@id=\"id_country\"]/option[2]"));country.click();//country
		

		WebElement phoneMobile = driver.findElement(By.name("phone_mobile"));//mobile number
		phoneMobile.sendKeys(PhoneMobile);
		WebElement submitBtn = driver.findElement(By.xpath("//*[@id=\"submitAccount\"]/span"));submitBtn.click();//signup button
		Thread.sleep(2000);
		


		
		try{
			driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a"));
			
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("You're not logged in");
		}
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")).click();
		
		
	}

	//logs in and changes last name and logs out.
	
	public void logIn() throws InterruptedException{
		driver.get("http://automationpractice.com/index.php");
		try{
			home.clickSignIn();
			
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("Not in Home Page");
		}
	
		signIn.getUserName().sendKeys(userName);		
		signIn.getPassword().sendKeys(password);
		signIn.clickLogin();
		
		try{
			driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a"));//checking for element that is only on after the login is successful. Checking for Bob bobson.
			
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("You're not logged in");
		}
		WebElement personalInfoBtn = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div[1]/ul/li[4]/a/span"));personalInfoBtn.click();
		WebElement lastName2 = driver.findElement(By.name("lastname"));
		lastName2.clear();//clears your last name so when you update it, it doesnt concat with your last name.
		lastName2.sendKeys(LastName2);
		WebElement confirmationPass = driver.findElement(By.name("old_passwd"));
		confirmationPass.sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/form/fieldset/div[11]/button/span")).click();//find save button and click
		
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")).click();//click log out
		
		
		
		
	}
	
	@Test
	public void browse() throws InterruptedException{
		driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a")).click();//click
		
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img")).click();
		
		Thread.sleep(4000);
		driver.navigate().back();
		
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[2]/div/div[1]/div/a[1]/img")).click();
		driver.findElement(By.xpath("//*[@id=\"quantity_wanted_p\"]/a[2]/span/i")).click();
		driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span")).click();
		

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a")).click();
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span")).click();
		
		Thread.sleep(2000);
		
		
		WebElement uNameBox = driver.findElement(By.name("email"));
		WebElement pwd = driver.findElement(By.name("passwd"));
		WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]/span"));
		
		uNameBox.sendKeys(userName);
		pwd.sendKeys(password);
		loginBtn.click();
		
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button/span")).click();
		driver.findElement(By.xpath("//*[@id=\"cgv\"]")).click();		
		driver.findElement(By.xpath("//*[@id=\"form\"]/p/button/span")).click();
		driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")).click();
		driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button/span")).click();
		//driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")).click();//click log out
		////*[@id="center_column"]/div/p/strong<---- xpath for order confirmation. Should say "Your order on My Store is complete."
	}
	
	

/*@After
	public void tearDown() {
	
		try {
			driver.close();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
}*/
}
