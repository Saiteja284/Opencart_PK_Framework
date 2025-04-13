package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = { "Regression", "Sanity", "Master", "Datadriven" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException, InterruptedException {
		// loading config file
		FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());
		
		//remote 
//		if(p.getProperty("execution_env").equalsIgnoreCase("remote")){
//			DesiredCapabilities capabilities = new DesiredCapabilities();
//			
//			if(os.equalsIgnoreCase("windows")) 
//			{
//			capabilities.setPlatform(Platform.WIN11);
//			}
//			else if(os.equalsIgnoreCase("mac"))
//			{
//			capabilities.setPlatform(Platform.MAC);
//			}else
//			{
//			System.out.println("No matching os");
//			return;
//			}
//			
//			//browser
//			switch(br.toLowerCase())
//			{
//			case "chrome": capabilities.setBrowserName("chrome"); break;
//			case "edge": capabilities.setBrowserName("edge"); break;
//			default : System.out.println("No matching browser"); return;
//			}
//			driver = new RemoteWebDriver(new URL("http://192.168.29.248:4444/wd/hub"), capabilities);
//		}
//		
//		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
//		{

			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "firefox":
				driver = new EdgeDriver();
				break;
			case "edge":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid browser name..");
				return;
			}
//		}
				

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
		Thread.sleep(3000);
		driver.get(p.getProperty("appURL2")); // browser reading URL from properties file
		driver.manage().window().maximize();
		Thread.sleep(3000);

	}

	@AfterClass(groups = { "Regression", "Sanity", "Master", "Datadriven" })
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumber() {
		String genertednumber = RandomStringUtils.randomNumeric(10);
		return genertednumber;
	}

	public String randomAlphanumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String genertednumber = RandomStringUtils.randomNumeric(10);
		return (generatedString + "@" + genertednumber);
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "C:\\Users\\2142226\\eclipse-workspace-javaIDE\\Opencart_PK_Framework\\screenshots" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}
	// adding it to check get updated or not

}
