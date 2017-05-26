package Project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignIn {

	WebDriver driver;
	
	@FindBy(name= "email")
	WebElement uNameBox;
	
	@FindBy(name = "passwd")
	WebElement pwd;
	
	@FindBy(xpath = "//*[@id=\"SubmitLogin\"]/span")
	WebElement loginBtn;
	
	
	
	public SignIn(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	
	public WebElement getLogin(){
		
		return loginBtn;
		
	}
	public void clickLogin(){
		
		getLogin().click();
			
	}
	
	public WebElement getUserName(){
		
		return uNameBox;
		
	}
	
	public WebElement getPassword(){
		return pwd;
		
		
	}
}
