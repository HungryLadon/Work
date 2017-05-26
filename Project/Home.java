package Project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {

	WebDriver driver;
	
	@FindBy(xpath ="//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")
	WebElement signInBtn;
	
	
	public Home(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
			
	}
	
	public WebElement getSignIn(){
		
		return signInBtn;
		
	}

	public void clickSignIn() {
		getSignIn().click();
		
	}
	
}
