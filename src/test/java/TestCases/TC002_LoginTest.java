package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;

public class TC002_LoginTest extends baseClass{
	HomePage hp;
	LoginPage lp;
	MyAccountPage mp;
	
	@Test(groups= {"sanity","regression"})
	public void verifyLogin() throws InterruptedException
	{
		 hp=new HomePage(driver);
			
		hp.clickMyAccountLink();
		hp.clickLoginLink();
		lp = new LoginPage(driver);
		lp.enterEmail(pro.getProperty("Email"));
		lp.enterPswd(pro.getProperty("Pswd"));
		lp.clickSubmit();
		mp = new MyAccountPage(driver);
		Assert.assertEquals(mp.isMyAccountPageExist(), "My Account");
		log.info("successfully logged in");
	}
	
	
	

}
