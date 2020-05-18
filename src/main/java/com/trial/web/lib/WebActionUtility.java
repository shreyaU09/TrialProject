package com.trial.web.lib;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;
import com.trial.web.listeners.ExtentTestManager;

public class WebActionUtility {

	public WebDriver driver;
	WebDriverWait wait;
	public long ETO = 10;
	public JavascriptExecutor jsExecutor;
	public Actions action;
	ITestResult result;
	
	//private static Logger Log = Logger.getLogger(WebActionUtility.class.getName());
	public WebActionUtility(WebDriver driver, long ETO) {
		this.driver = driver;
		this.ETO = ETO;
		wait = new WebDriverWait(driver, ETO);
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
	}

	public  void info(String message) {
		ExtentTestManager.getTest().info(message);
		Reporter.log(message, true);
		//Log.info(MarkupHelper.createLabel(result.getTestName(), ExtentColor.GREEN));

	}

	public  void warn(String message) {

		ExtentTestManager.getTest().log(Status.WARNING, message);
		Reporter.log(message, true);
	}

	public void error(String message) {

		ExtentTestManager.getTest().log(Status.ERROR, message);
		Reporter.log(message, true);

	}

	public  void fatal(String message) {

		ExtentTestManager.getTest().log(Status.FATAL, message);
		Reporter.log(message, true);
	}


	/* Click on the Element */
	public void clickOnElement(WebElement element, String elementName) {
//		if (isElementClickable(element, elementName)) {
//			// generic.logMessage(LogStatus.PASS, "Click on " + elementName);
			element.click();
		
	}

	/* Verify the Element is Clickable or Not */
	public boolean isElementClickable(WebElement element, String elementName) {

		// generic.logMessage(LogStatus.INFO, "Verifying Element is Clickable or Not");
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			// generic.logMessage(LogStatus.INFO, elementName + " is not Clickable ");
			return false;
		}
	}

	/* Verify the Text */
	public void verifyElementText(WebElement element, String expectedText) {
		String actualText = element.getText();
		Assert.assertEquals(actualText, expectedText);
		info(actualText + " is matching with " + expectedText);
	}

	/* Verify the page Title */
	public void verifyTheTitle(String expectedTitle) {
		String actualTitle = driver.getTitle();
		info(":" + actualTitle);
		Assert.assertEquals(actualTitle, expectedTitle);
		info("Compare 'Actual title' with the 'Expected Title' ");
		info(actualTitle + " is matching with " + expectedTitle);
	}

	/* To Enter the Text to the Text filed */
	public void typeText(WebElement element, String value, String elementName) {
		try {
			// waitForElement(element, elementName);
			info("Enter the value into " + elementName);
			element.sendKeys(value);
			info("User is able to type " + value + " into " + elementName);
		} catch (AssertionError error) {
			error(" User is not able to type " + value + " into " + elementName);
			// MyExtentListners.test.addScreenCaptureFromPath(capture(driver, element));
			Assert.fail("Unable to type on " + elementName);
		} catch (Exception e) {
			error(" User is not able to type " + value + "into " + elementName);
			// MyExtentListners.test.addScreenCaptureFromPath(capture(driver, element));
			Assert.fail("Unable to type in " + elementName);
		}
	}

	/* Click on the Element using JavaSCript */
	public void clickOnElementUsingJS(WebElement element, String elementName) {
		if (isElementClickable(element, elementName)) {
			info("User is able to click "+" into " + elementName);
			jsExecutor.executeScript("arguments[0].click();", element);
		} else {
			info("User is not able to click "+" into " + elementName);
			Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(element)) == null);
		}
	}
	
	public void doubleClickOnElement(WebElement element ,String elementName)
    {
		info("User is able to click "+" into " + elementName);
		action.doubleClick(element).perform();
    }
	

	public void sleep(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	/* Get the Text From the Page */
	public void clearText(WebElement element, String elementName) {
		info("Clear the Text Present in" + elementName);
		element.clear();
		info("Cleared the Text Present in" + elementName);
	}
	
	public void isElementDisplayedOrNot(WebElement element)
	{
		sleep(3);
		boolean actual = element.isDisplayed();
		Assert.assertEquals(actual, true,"Element is Not Displayed");
	}
	
}
