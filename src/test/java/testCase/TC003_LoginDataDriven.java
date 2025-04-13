package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDriven extends BaseClass {
	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void verify_login(String email, String pwd ,String exp) throws InterruptedException {
		
		//logger.info("************* Starting TC002_LoginTest ****************");
		
		try {
			
		
		Homepage hp = new Homepage(driver);
		hp.clickMyAccount();
		hp.clicklogin();
		Thread.sleep(3000);
		
		//Login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clicklogin(); //Login button
		
		//My Account Page
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		}
		catch(Exception e)
		{
			Assert.fail("An exception occurred: " + e.getMessage());
		}
	

}
}