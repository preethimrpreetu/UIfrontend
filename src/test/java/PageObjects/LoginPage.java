package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement email;
	@FindBy(xpath="//input[@id='input-password']")
	WebElement pswd;
	@FindBy(xpath="//input[@class='btn btn-primary']")
	WebElement submit;
	
	
	public void enterEmail(String emailId) {
		email.sendKeys(emailId);
	}
	
	public void enterPswd(String password) {
		pswd.sendKeys(password);
	}
	
	public void clickSubmit() {
		submit.click();
	}
	

}
