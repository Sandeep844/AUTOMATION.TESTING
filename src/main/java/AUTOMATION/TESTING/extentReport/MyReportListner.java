package AUTOMATION.TESTING.extentReport;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import AUTOMATION.TESTING.testBase.TestBase;
import AUTOMATION.TESTING.utility.CommonUtilities;
import AUTOMATION.TESTING.utility.ReportsUtils;

public class MyReportListner extends TestBase implements ITestListener {
	public static final Logger logg=Logger.getLogger(MyReportListner.class.getName());
	public static int totalcount = 0;
	public static int passcount = 0;
	public static int failedcount = 0;
	public static int skippedcount=0;
	private static Map<String, Long> testStartTimes = new HashMap<>();
	public static Map<String, TestTimes> allTestData = new HashMap<>();
	public static StringBuffer sb=new StringBuffer();
	ExtentReports report=ReportsUtils.getExtentReport();
    public static ExtentTest eTest;
  
     @Override
	public void onStart(ITestContext context) {
    	 logg.info("====================== On Start ==============================");
    	 if (!destPath.exists()) destPath.mkdirs();
    	 File[] files =destPath.listFiles();
    	 for (File file :files) {
   		  file.delete();
   		  }
    	 
    	 if (!log4jPath.exists()) log4jPath.mkdirs();
    	 File[] logfiles =log4jPath.listFiles();
    	 for (File lfile :logfiles) {
   		  lfile.delete();
   		  }
	  }

	@Override
	public void onTestStart(ITestResult result) {	 
		String testName=result.getName().replaceAll("_", " ");
		String removeUnderScore=ReportsUtils.removeUnderScore(result);
		eTest=	report.createTest(testName);
		totalcount++;
		long startTime  = result.getStartMillis();
		testStartTimes.put(removeUnderScore, startTime);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName=result.getName().replaceAll("_", " ");
		String removeUnderScore=ReportsUtils.removeUnderScore(result);
		eTest.log(Status.PASS, testName);
		passcount++;
		long endTime=result.getEndMillis();
		long startTime = testStartTimes.get(removeUnderScore);
		
		TestTimes td = new TestTimes(removeUnderScore,(endTime-startTime)/1000.0, "PASS");
		allTestData.put(removeUnderScore,td);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName=result.getName().replaceAll("_", " ");
		String removeUnderScore=ReportsUtils.removeUnderScore(result);
		eTest.log(Status.FAIL, testName);
		eTest.log(Status.INFO, result.getThrowable());
		failedcount++;
		long endTime=result.getEndMillis();
		long startTime = testStartTimes.get(removeUnderScore);
		TestTimes td = new TestTimes(removeUnderScore,(endTime-startTime)/1000.0, "FAIL");
		allTestData.put(removeUnderScore,td);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName=result.getName().replaceAll("_", " ");
		String removeUnderScore=ReportsUtils.removeUnderScore(result);
		eTest.log(Status.SKIP, testName);
		eTest.log(Status.INFO, result.getThrowable());
		skippedcount++;
		long endTime=result.getEndMillis();
		long startTime = testStartTimes.get(removeUnderScore);
		TestTimes td = new TestTimes(removeUnderScore,(endTime-startTime)/1000.0, "SKIP");
		allTestData.put(removeUnderScore,td);
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
		logg.info("Appending data in to StringBuffer");
		allTestData.forEach((k,v)->{
			if(v.testResult.equals("PASS")) {
		    	sb.append("<tr bgcolor='#F9FFF8'><td>").append(v).append("</td></tr>");
            }else if(v.testResult.equals("FAIL")) {
		    	sb.append("<tr bgcolor='#FFF1F1'><td>").append(v).append("</td></tr>");
		    }else {
		    	sb.append("<tr bgcolor='#FCFFE4'><td>").append(v).append("</td></tr>");
		    }
		});
		logg.info("Test Case Over Sending Mail");
		String getEnv=CommonUtilities.getEnvironment();
		logg.info("environment == "+getEnv);
		if(getEnv.equals("dev")) {
	     logg.info("Test Case Over Deleting Inventory");
         logg.info("Scrip Ran on "+getEnv+" env");
		}else {
		logg.info("Script Ran on "+getEnv+" env");
		}
		logg.info("=========================== On Finish  ==========================");
	}	
}
