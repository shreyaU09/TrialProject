package com.trial.web.lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	public WebDriver driver;
	public WebActionUtility WebActionUtility;
	public long ETO=0;
	
	public BasePage(WebDriver driver,long ETO,WebActionUtility WebActionUtility) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		this.WebActionUtility=WebActionUtility;
		this.ETO=ETO;
	
	}	
}


