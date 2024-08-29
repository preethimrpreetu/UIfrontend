package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		
		super(driver);
		
		
				
	}
	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement MyAccountLink;
	
	
	@FindBy(xpath="//a[text()='Register']")
	WebElement registerLink;
	
	@FindBy(xpath="//li/a[text()='Login'][1]")
	WebElement loginLink;
	
	public void clickMyAccountLink()
	{
		MyAccountLink.click();
	}

	public void clickRegistertLink()
	{
		registerLink.click();
	}
	
	public void clickLoginLink()
	{
		loginLink.click();
	}
	
}
