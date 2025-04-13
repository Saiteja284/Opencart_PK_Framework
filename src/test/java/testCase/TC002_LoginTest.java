package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BasePage;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Sanity","Master"})
	public void verify_login() {
		
		logger.info("************* Starting TC002_LoginTest ****************");
		
	try {
			
			//Homepage 
			Homepage hp = new Homepage(driver);
			hp.clickMyAccount();
			hp.clicklogin();
			
			//Login
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clicklogin(); 
			 
			//MyAccount
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetpage=macc.isMyAccountPageExists();
			
			Assert.assertTrue(targetpage);
	}
	catch(Exception e ) {
		Assert.fail();
	}}}
	
		  
