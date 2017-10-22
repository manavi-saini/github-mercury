package Common;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Listerners implements ISuiteListener, ITestListener {
	
	public void onFinish(ISuite suite) {
		System.out.println("Test Suite Run Finished: " +suite.getName());		
	}

	public void onStart(ISuite suite) {
		System.out.println("Test Run Suite Started: "+suite.getName());
	}

	public void onFinish(ITestContext arg0) {		
		System.out.println("Test Finished: " +arg0.getName());	
	}

	public void onStart(ITestContext arg0) {
		System.out.println("Test Started: "+arg0.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		System.out.println("Test Case Failed but within Success %age: " +arg0.getName());		
	}

	public void onTestFailure(ITestResult arg0) {
		System.out.println("Test Case Failed: " +arg0.getName());		
	}

	public void onTestSkipped(ITestResult arg0) {
		System.out.println("Test Case Skipped: " +arg0.getName());		
	}

	public void onTestStart(ITestResult arg0) {
		System.out.println("Test Case Started: " +arg0.getName());		
	}

	public void onTestSuccess(ITestResult arg0) {
		System.out.println("Test Case Passed: " +arg0.getName());		
	}


}
