package com.trial.web.testscripts;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.trial.web.lib.BaseTest;
import com.trial.web.lib.ExcelLibrary;
import com.trial.web.pages.Home_Page;
import com.trial.web.pages.Login_Page;


public class TC_001 extends BaseTest{
	@Test(alwaysRun=true, description = "Trial Scripts")
	public void demoTestMethod2()  {

		if (skipTest) {
			throw new SkipException("TC_001");
		}
		
		/* Fetch the test data from the EXCEL SHEET */
	
		
		
		int col2 = ExcelLibrary.getColumnIndex(xlPath, "Demo", "Email", "TCID");
		int col3 = ExcelLibrary.getColumnIndex(xlPath, "Demo", "FirstName", "TCID");
		int col4 = ExcelLibrary.getColumnIndex(xlPath, "Demo", "Gender", "TCID");
		int col5 = ExcelLibrary.getColumnIndex(xlPath, "Demo", "LastName", "TCID");
		int col6 = ExcelLibrary.getColumnIndex(xlPath, "Demo", "Phone", "TCID");
		
		String[] excel1 = ExcelLibrary.toReadExcelData(xlPath, "Demo", "TC_001");
		
		Home_Page homepage = new Home_Page(driver, ETO, WebActionUtility);
		homepage.editTable( excel1[col2],excel1[col3],excel1[col4],excel1[col5],excel1[col6]);
		
	}
	
}
