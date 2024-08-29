package Utilities;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestCases.baseClass;

public class ReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReprter;//ui of report
	public ExtentReports extent;//populate common info on report
	public ExtentTest test;//creating testcase entries in the report and update status of the methods
	
	public void onStart(ITestContext context) {
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repname= "Test Report"+timeStamp+".html";
		sparkReprter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\Reports\\"+repname);
		
		sparkReprter.config().setDocumentTitle(" Automation Report");
		sparkReprter.config().setReportName("function testing");
		sparkReprter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReprter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "qa");
		String os=context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		String browser=context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		List<String> includedGroups=context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("groups", includedGroups.toString());
		}
	  }
	
	
	public void onTestSuccess(ITestResult result) {
		 test=extent.createTest(result.getTestClass().getName());//create a new entry in the report
		 test.assignCategory(result.getMethod().getGroups());//to display groups in ethe report
		 test.log(Status.PASS, "Test case passed is:"+ result.getName());//update status as s/f/ski
		 
				
	  }
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test case failed is:"+ result.getName());
		test.log(Status.INFO, "Test case failed cause is:"+result.getThrowable().getMessage());
		try {
		String path=new baseClass().captureScreen(result.getName());
		test.addScreenCaptureFromPath(path);
		}catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
		
		
		
	    
	  }
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test case skipped is:"+ result.getThrowable());
	    
	  }

	public void onFinish(ITestContext context) {
		extent.flush();
	   
	  }

}
