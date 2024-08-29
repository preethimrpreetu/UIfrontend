package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver) {
		
		super(driver);
	}
	
	@FindBy(xpath="//input[@name='firstname']")
	WebElement firstName;
	
	@FindBy(xpath="//input[@name='lastname']")
	WebElement lastName;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement email;
	
	@FindBy(xpath="//input[@name='telephone']")
	WebElement phoneNo;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement confirmPswd;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement agreeCheckBox;
	
	@FindBy(xpath="//input[@class='btn btn-primary']")
	WebElement continueBtn;
	
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	WebElement confirmationMsg;
	
	public void enterFirstName(String fName) {
		firstName.sendKeys(fName);
	

}
	public void enterLastName(String lName) {
		lastName.sendKeys(lName);
	}

	
	public void enterEmail(String emailN) {
		email.sendKeys(emailN);
	

}
	
	public void enterPhoneNo(String phone) {
		phoneNo.sendKeys(phone);
	

}
	

	public void enterPswd(String pswd) {
		password.sendKeys(pswd);
	

}

	
	public void enterConfirmPswd(String cPswd) {
		confirmPswd.sendKeys(cPswd);
	

}


	public void clickOnAgreeBtn() {
		agreeCheckBox.click();
	

}
	
	public void clickOnContinueBtn() {
		continueBtn.click();
	

}
	
	public String getConfirmationMsg() {
		try
		{
			return (confirmationMsg.getText());
		}
		catch(Exception e)
		{
		return (e.getMessage());
		}
	}

	

}
