package AUTOMATION.TESTING.extentReport;


/**This class will store data of one test*/
public class TestTimes {
	double millis;
	String testName;
	String testResult;//Skip/Pass/Fail
	
	
	public TestTimes(String testName,double millis,String testResult) {
		super();
		this.millis = millis;
		this.testName = testName;
		this.testResult = testResult;
	}
	public String getTestName() {
		return testName;
	}
	public double getMillis() {
	return millis;
	}
	public String getTestResult() {
		return testResult;
	}
	
	public void setMillis(double millis) {
		this.millis = millis;
	}
	
	public void setTestName(String testName) {
		this.testName = testName;
	}
	
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	public  String toString() {
		return testName+", "+millis+", "+testResult;
	}
	
}

