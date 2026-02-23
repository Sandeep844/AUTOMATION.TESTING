package kuby.web.extentReport;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import kuby.web.testBase.TestBase;
import kuby.web.utility.CommonUtilities;
import kuby.web.utility.MailUtils;
import kuby.web.utility.ReportsUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import static kuby.web.testBase.TestBase.*;

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
private List<Step> steps;

private static class Step {
	String description;
	String status;

	Step(String description, String status) {
		this.description = description;
		this.status = status;
	}
}

public MyReportListner() {
	steps = new ArrayList<>();
}

@Override
public void onStart(ITestContext context) {
	logg.info("====================== On Start ==============================");
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss");
	sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
	Date currentDate = new Date();
	suiteStarttime = sdf.format(currentDate);

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
	eTest=  report.createTest(testName);
//		Code to segregate Test cases Module wise
	String[] testclassArray = result.getTestClass().getName().split("[.]");
	String testclassName = testclassArray[testclassArray.length-1];
	System.out.println("Category Name:= "+testclassName);
	System.out.println("TestCase Name:= "+testName);
	eTest.assignCategory(testclassName);
	totalcount++;
	long startTime  = result.getStartMillis();
	testStartTimes.put(removeUnderScore, startTime);
	eTest.info("<b>Test Started: </b>"+testName);
	screenshotRequired=false;
}
	@Override
	public void onTestSuccess(ITestResult result) {

		String testName = result.getName().replaceAll("_", " ");
		String removeUnderScore = ReportsUtils.removeUnderScore(result);

		logStep("Test Passed : " + testName, "pass");

		// 🔥 Highlight success element
		CommonUtilities.highlightSuccessElement();


		String screenshotPath = takeScreenshot(removeUnderScore);

		if (screenshotPath != null && !screenshotPath.isEmpty()) {

			File screenshotFile = new File(screenshotPath);
			String relativePath = "screenshot" + File.separator + screenshotFile.getName();

			eTest.addScreenCaptureFromPath(relativePath);

			logStep("PASS Screenshot attached successfully", "pass");
		}
		eTest.log(Status.INFO, result.getThrowable());
		passcount++;

		long endTime = result.getEndMillis();
		long startTime = testStartTimes.get(removeUnderScore);

		TestTimes td = new TestTimes(removeUnderScore,(endTime-startTime)/1000.0, "PASS");
		allTestData.put(removeUnderScore, td);
		//logSteps();
	}
//@Override
//public void onTestSuccess(ITestResult result) {
//	String testName=result.getName().replaceAll("_", " ");
//	String removeUnderScore=ReportsUtils.removeUnderScore(result);
//	eTest.log(Status.PASS, testName);
//	if(screenshotRequired==true) {
//		eTest.addScreenCaptureFromBase64String(((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64),"Screenshot click here");
//	}
//	eTest.log(Status.INFO, result.getThrowable());
//	passcount++;
//	long endTime=result.getEndMillis();
//	long startTime = testStartTimes.get(removeUnderScore);
//	TestTimes td = new TestTimes(removeUnderScore,(endTime-startTime)/1000.0, "PASS");
//	allTestData.put(removeUnderScore,td);
//	logSteps();
//}
	public void onTestFailure(ITestResult result) {

		String testName = result.getName().replaceAll("_", " ");
		String removeUnderScore = ReportsUtils.removeUnderScore(result);

		// ✅ Use logStep instead of direct extent log
		logStep("<b>Test Failed : </b>" + testName, "fail");

	/*	eTest.addScreenCaptureFromBase64String(
				((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64),
				"Screenshot click here");*/

		logStep("Exception Occurred : " + result.getThrowable(), "fail");

		// 🔥 ALWAYS highlight last interacted element first
		CommonUtilities.highlightLastAction();

// 🔥 Try exception highlight if throwable exists
		Throwable throwable = result.getThrowable();

		if (throwable != null) {

			boolean highlighted = CommonUtilities.autoHighlightFromException(throwable);

			if (!highlighted) {
				CommonUtilities.forceHighlightFromException(throwable);
			}
		}
		String screenshotPath = takeScreenshot(removeUnderScore);

		if (screenshotPath != null && !screenshotPath.isEmpty()) {
			try {
				if (eTest != null) {

					File screenshotFile = new File(screenshotPath);
					String relativePath = "screenshot" + File.separator + screenshotFile.getName();

					eTest.addScreenCaptureFromPath(relativePath);

					logStep("Screenshot attached successfully", "pass");

				} else {
					logStep("eTest is null, cannot attach screenshot", "skip");
				}

			} catch (Exception e) {
				logStep("Failed to attach screenshot : " + e.getMessage(), "fail");
			}
		}

		failedcount++;

		long endTime = result.getEndMillis();
		long startTime = testStartTimes.get(removeUnderScore);

		TestTimes td = new TestTimes(removeUnderScore,(endTime-startTime)/1000.0, "FAIL");
		allTestData.put(removeUnderScore,td);

		logStep("Test execution finished with FAILURE", "fail");

	}

//@Override
//public void onTestFailure(ITestResult result) {
//	String testName=result.getName().replaceAll("_", " ");
//	String removeUnderScore= ReportsUtils.removeUnderScore(result);
//	eTest.log(Status.FAIL, testName);
//eTest.addScreenCaptureFromBase64String(((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64),"Screenshot click here");
//	eTest.log(Status.INFO, result.getThrowable());
//	// 🔥 AUTO HIGHLIGHT FAILURE ELEMENT (NO TEST CASE CHANGES)
//CommonUtilities.autoHighlightFromException(result.getThrowable());
//	CommonUtilities.forceHighlightFromException(result.getThrowable());
//	String screenshotPath = takeScreenshot(removeUnderScore);
//
//	// Attach screenshot to report (HOT FIX)
//	if (screenshotPath != null && !screenshotPath.isEmpty()) {
//		try {
//			if (eTest != null) {  // ✅ Added null check for safety
//				File screenshotFile = new File(screenshotPath);
//				String relativePath = "screenshot" + File.separator + screenshotFile.getName();  // Use File.separator for portability
//				eTest.addScreenCaptureFromPath(relativePath);
//
//				logg.info("Screenshot attached to report for failed test: " + testName);
//			} else {
//				logg.warn("eTest is null, cannot attach screenshot");
//			}
//		} catch (Exception e) {
//			logg.error("Failed to attach screenshot to report: " + e.getMessage(), e);  //  Include full exception for debugging
//		}
//	}
//	failedcount++;
//	long endTime=result.getEndMillis();
//	long startTime = testStartTimes.get(removeUnderScore);
//	TestTimes td = new TestTimes(removeUnderScore,(endTime-startTime)/1000.0, "FAIL");
//	allTestData.put(removeUnderScore,td);
//	eTest.info("<b>Test Failed: </b>"+testName);
//	logSteps();
//}



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
	logSteps();
}

@Override
public void onFinish(ITestContext context) {
	report.flush();
	logg.info("Appending data in to StringBuffer");
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss");
	sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
	Date currentDate = new Date();
	suiteEndtime = sdf.format(currentDate);
	allTestData.forEach((k,v)->{
		if(v.testResult.equals("PASS")) {
			sb.append("<tr bgcolor='#F9FFF8'><td>").append(v).append("</td></tr>");
		}else if(v.testResult.equals("FAIL")) {
			sb.append("<tr bgcolor='#FFF1F1'><td>").append(v).append("</td></tr>");
		}else {
			sb.append("<tr bgcolor='#FCFFE4'><td>").append(v).append("</td></tr>");
		}
		//sb.append(v+","+"\n");
		//sb.append("<tr><td>").append(v).append("</td></tr>");
	});
	logg.info("Test Case Over Sending Mail");
	MailUtils.sendAutomationReportMail();
	String getEnv= CommonUtilities.getEnvironment();
	logg.info("environment == "+getEnv);
	if(getEnv.equals("dev")) {
		logg.info("Test Case Over Deleting Inventory");
		//APIHandlerUtils.deleteInventoryMethod();
		logg.info("Scrip Ran on "+getEnv+" env");
	}else {
		logg.info("Script Ran on "+getEnv+" env");
	}
	logg.info("=========================== On Finish  ==========================");
}

public void logStep(String stepDescription, String status) {
	steps.add(new Step(stepDescription, status)); // Store the step
	if (eTest != null) {
		if(status.equalsIgnoreCase("pass")) {
			eTest.log(Status.PASS, stepDescription);
		}else if(status.equalsIgnoreCase("fail")) {
			eTest.log(Status.FAIL, stepDescription);
		}else if(status.equalsIgnoreCase("skip")){
			eTest.log(Status.SKIP, stepDescription);

		}
	}

}
private void logSteps() {
	for (Step step : steps) {
		switch (step.status.toLowerCase()) {
			case "pass":
				eTest.log(Status.PASS, step.description);
				break;
			case "fail":
				eTest.log(Status.FAIL, step.description);
				break;
			case "skip":
				eTest.log(Status.SKIP, step.description);
				break;
			default:
				eTest.info(step.description + " - Status: " + step.status);
		}
	}
}

}

