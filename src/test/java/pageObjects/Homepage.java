package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage {

	public Homepage(WebDriver driver) {
		super(driver);

	}

	//WebElement linkMyaccount = driver.findElement(By.xpath("//span[normalize-space()='My Account']"));

    @FindBy(xpath="//span[normalize-space()='My Account']") 
    WebElement lnkMyaccount;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy (xpath = "//a[text()='Login']")
	WebElement lnkLogin;
	
	public void clickMyAccount() {
		lnkMyaccount.click();
	}

	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clicklogin() {
		lnkLogin.click();
	}
	
	

}
