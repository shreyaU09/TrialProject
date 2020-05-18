package com.trial.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.trial.web.lib.BasePage;
import com.trial.web.lib.WebActionUtility;
public class Login_Page extends BasePage{
	
	public Login_Page(WebDriver driver, long ETO, WebActionUtility WebActionUtility) {
		super(driver, ETO, WebActionUtility);
	}

	@FindBy(xpath = "//input[@class='txtbox ng-pristine ng-valid ng-touched' and @placeholder='E mail']")
	private WebElement emailTxtBx;

	@FindBy(name = "//input[@class='txtbox ng-pristine ng-valid ng-touched' and @placeholder='Password']")
	private WebElement passWordTxtBx;

	@FindBy(xpath= "//img[@id='enterbtn']")
	private WebElement loginBtn;
	
	@FindBy(id= "email")
	private WebElement emailTxtBx1;

	
	@FindBy(id= "enterimg")
	private WebElement loginBtn1;
	

	public void loginToApplication(String userName, String password)  {
		WebActionUtility.info("Start Loging in");
		WebActionUtility.doubleClickOnElement(emailTxtBx1, "Email Text Box");
		WebActionUtility.typeText(emailTxtBx1, userName, "Email Address Text box");
		WebActionUtility.clickOnElementUsingJS(loginBtn1, "'Login' Button");
		WebActionUtility.info("Successfully logged into Home page of the Application");
	}

	/* Verify the SignOut */
	public void verifySignOut() {
		WebActionUtility.verifyTheTitle("actiTIME - Login");
		WebActionUtility.info( " Verified the 'Sign Out' Successfully");
	}
}
