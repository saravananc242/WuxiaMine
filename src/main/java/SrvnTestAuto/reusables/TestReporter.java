package SrvnTestAuto.reusables;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestReporter {
	public static ExtentReports report;
	public ExtentTest test;
	
	public void passTest() {
		test.log(Status.PASS, "pass");
	}
	
	
	public void failTest() {
		test.log(Status.FAIL, "fail");
	}

}
