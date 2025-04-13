package testCase;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
 
import pageObjects.AccountRegistrationPage;
import pageObjects.Homepage;
import testBase.BaseClass;


public class TC001_AccountRegistrationTest extends BaseClass { 

	

	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {

		
		logger.info("******* Starting TC001_AccountRegistration ********");
 
		
		Homepage hp = new Homepage(driver);
		hp.clickMyAccount();
		logger.info("****** clicked on my account link ******");
		hp.clickRegister();
		logger.info("****** clicked on register link ******");
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		logger.info("****** Providing customer details ******");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString() + "@gmail.com"); // regerate random Emails
		regpage.setTelephone(randomNumber());

		String password = randomAlphanumeric();

		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();

		logger.info("****** Validating Expected message ******");

		String confmsg = regpage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}else {
			logger.error("Test Failed....");
			logger.debug("debug logs......");
			Assert.assertTrue(false); 
		}
		
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		logger.info("*********** Finished TC001_AccountRegistration ************");	
		
	}
}
