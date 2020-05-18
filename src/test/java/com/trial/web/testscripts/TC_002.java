package com.trial.web.testscripts;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.trial.web.lib.BaseTest;

public class TC_002 extends BaseTest{
	
	@Test(alwaysRun=true, description = "Trial Scripts")
	public void demoTestMethod2()  {

		if (skipTest) {
			throw new SkipException("TC_001");
		}

}
}