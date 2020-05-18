package com.trial.web.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;
import com.trial.web.lib.BaseTest;

public class MyListeners  implements ITestListener{
	
	public MyListeners() {  }
   
	public void onStart(ITestContext context) {
		Reporter.log("*************** Test Suite " + context.getName() + " Started *************", true);
	}

	public void onFinish(ITestContext context) {
		Reporter.log(("*************** Test Suite " + context.getName() + " Ending *************"), true);
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}


	public void onTestStart(ITestResult result) {
		Reporter.log(("*************** Running test method " + result.getMethod().getMethodName() + "..."), true);
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	
	public void onTestSuccess(ITestResult result) {
		Reporter.log("*************** Executed " + result.getMethod().getMethodName() + " Test Successfully...", true);
		ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
		
	}

	public void onTestFailure(ITestResult result) {
		Reporter.log("*************** Test Execution " + result.getMethod().getMethodName() + " Failed...", true);
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
		
	
	}

	public void onTestSkipped(ITestResult result) {
		Reporter.log("************* Test " + result.getMethod().getMethodName() + " skipped...", true);
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*************** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

	
	public void getScreenshotAndAddToReport( WebDriver driver,String screenShotNmae) {

		String screenShotpath = getScreenShot(driver,screenShotNmae);
		try {
			ExtentTestManager.getTest().addScreenCaptureFromPath(screenShotpath);
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getScreenShot( WebDriver driver,String screenshotName) {

	
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination
		String DestFile = System.getProperty("user.dir") + "/ScreenShots/" + screenshotName + dateName + ".png";

		File finalDestFile = new File(DestFile);

		// Copy file at destination
		try {
			FileUtils.copyFile(SrcFile, finalDestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return DestFile;
	}}

