package com.trial.web.lib;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements IConst {

	public static WebDriver driver;
	public long ETO = 10, ITO = 10;
	public WebActionUtility WebActionUtility;
	public ExcelLibrary excelLibrary = new ExcelLibrary();
	public boolean skipTest = false;
	//public final static Logger logger = Logger.class.getClass().getName().toString();
	
//	@Parameters({ "browserName", "ITO", "ETO" })
	@BeforeClass
	public void launchApp(@Optional(browsername) String browserName, @Optional(strITO) String strITO,
			@Optional(strETO) String strETO) throws Exception {
	

		try {
			ITO = Long.parseLong(strITO);
			ETO = Long.parseLong(strETO);
		} catch (NumberFormatException e) {
		
		}

//		MyListeners.info("Run in Current System");
		if (browserName.equalsIgnoreCase("firefox")) {
			//WebActionUtility.info("Run in Firefox");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addPreference("dom.webnotifications.enabled", false);
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(firefoxOptions);
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else {
			//WebActionUtility.info("Run in Chrome");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-infobars");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(ITO, TimeUnit.SECONDS);
		WebActionUtility = new WebActionUtility(driver, ETO);
	}

	@AfterClass()
	public void closeApp() {

		/* Close the browser */
		try {
			if (driver != null) {
				driver.quit();
				//WebActionUtility.info("Closing Browser");
			} else {
				WebActionUtility.error("@AfterClass driver instance is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			//WebActionUtility.error(e.getMessage());
		}

	}

	@Parameters({ "appURL", "userName", "password" })
	@BeforeMethod
	public void signInToApp(@Optional(appURL) String appURL, @Optional(userName) String userName,
			@Optional(passWord) String passWord) {
		/* Enter the application URL and login to the application */
		try {
			if (skipTest) {
				//WebActionUtility.warn("Skipping BeforeMethod");
			} else {
				//WebActionUtility.info("Open the Application...");
				driver.get(appURL);
				driver.manage().window().maximize();

				//WebActionUtility.info("Enter the Credentials");

			}

		} catch (Exception e) {
			e.printStackTrace();
		//	WebActionUtility.error(e.getMessage());
		}
	}

	@AfterMethod()
	public void signOutFromApp() {
		/* Signout from the application */
		try {
			if (skipTest) {
			//	WebActionUtility.info("Not performing any action in AfterMethod.");
			} else {

				//WebActionUtility.info("Signing out from the Application");

			}
		} catch (Exception e) {
			e.printStackTrace();
			//WebActionUtility.error(e.getMessage());
		}
	}

}
