package TestCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.AccountRegistrationPage;
import PageObjects.HomePage;
import jdk.internal.org.jline.utils.Log;



public class TC001_AccountRegistrationTest extends baseClass{
	
@Test(groups= {"sanity"})
public void verifyRegistration() {
	log.info("started login");
	HomePage hp = new HomePage(driver);
	hp.clickMyAccountLink();
	hp.clickRegistertLink();
	AccountRegistrationPage rp = new AccountRegistrationPage(driver);
	rp.enterFirstName(randomString().toUpperCase());
	rp.enterLastName(randomString().toUpperCase());
	rp.enterEmail(randomString()+"@gmail.com");
	rp.enterPhoneNo(randomNumber());
	String pswd=randomAlphaNumeric();
	rp.enterPswd(pswd);
	rp.enterConfirmPswd(pswd);
	rp.clickOnAgreeBtn();
	rp.clickOnContinueBtn();
	String msg=rp.getConfirmationMsg();
	Assert.assertEquals(msg, "Your Account Has Been Created!");
	log.info("Successfully completed registration");
	
}





}
