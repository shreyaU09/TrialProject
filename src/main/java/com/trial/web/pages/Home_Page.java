package com.trial.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.trial.web.lib.BasePage;
import com.trial.web.lib.WebActionUtility;

public class Home_Page extends BasePage{

	
	public Home_Page(WebDriver driver, long ETO, WebActionUtility WebActionUtility) {
		super(driver, ETO, WebActionUtility);
	}
	
	@FindBy(xpath= "//a[text()='WebTable']")
	private WebElement webtableBtn;
	
	@FindBy(xpath= "(//button[@class='btn btn-xs btn-custom'])[1]")
	private WebElement editBtn;
	
	@FindBy(xpath= "//div[@class='ui-grid-cell ng-scope ui-grid-coluiGrid-0005']/div/input")
	private WebElement emailTxtBx;
	
	@FindBy(xpath= "//div[@class='ui-grid-cell ng-scope ui-grid-coluiGrid-0006']/div/input")
	private WebElement firstNameTxtBx;
	
	@FindBy(xpath= "//div[@class='ui-grid-cell ng-scope ui-grid-coluiGrid-0007']/div/input")
	private WebElement genderTxtBx;
	
	@FindBy(xpath= "//div[@class='ui-grid-cell ng-scope ui-grid-coluiGrid-0008']/div/input")
	private WebElement lastNameTxtBx;
	
	@FindBy(xpath= "//div[@class='ui-grid-cell ng-scope ui-grid-coluiGrid-0009']/div/input")
	private WebElement  phoneTxtBx;
	
	@FindBy(xpath= "(//button[text()='Save'])[1]")
	private WebElement  saveBtn;
	
	
	
	
	public void editTable(String email, String firstName,String gender ,String lastname,String phoneNo) {
		
		
		//WebActionUtility.typeText(passWordTxtBx, password, "Password Text Box");
		WebActionUtility.clickOnElementUsingJS(webtableBtn, "'Login' Button");
		WebActionUtility.sleep(3);
		WebActionUtility.doubleClickOnElement(editBtn, "'Edit' Button");
		WebActionUtility.sleep(5);
		WebActionUtility.clearText(emailTxtBx, "email Txt Bx");
		WebActionUtility.typeText(emailTxtBx, email, "Email Text Box");
		WebActionUtility.clearText(firstNameTxtBx, "firstName Txt Bx");
		WebActionUtility.typeText(firstNameTxtBx, firstName, "Email Text Box");
		WebActionUtility.clearText(lastNameTxtBx, "lastname Txt Bx");
		WebActionUtility.typeText(lastNameTxtBx, lastname, "lastname Text Box");
		WebActionUtility.clearText(phoneTxtBx, "phoneNo Txt Bx");
		WebActionUtility.typeText(phoneTxtBx, phoneNo, "phoneNo Text Box");
		WebActionUtility.sleep(2);
		WebActionUtility.clickOnElementUsingJS(saveBtn, "'Save' Button");
		WebActionUtility.info("Successfully edited the Table");
	}
	
	
}
