package kuby.web.utility;



import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import kuby.web.testBase.TestBase;

public class WebEventListner extends TestBase implements WebDriverEventListener{



	 public static final Logger logg=Logger.getLogger(WebEventListner.class.getName());
	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateTo(String url, WebDriver driver) {
		logg.info("Before Navigation :"+url);
		
		
	}
	public void afterNavigateTo(String URL, WebDriver driver) {
		logg.info("After Navigation :"+URL);	
	}

	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	
	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		logg.info("Finding Element : "+by.toString());	
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		logg.info("Found The Element : "+by.toString());		
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		logg.info("Clicking on element : "+element.toString());
		
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
       logg.info("Clicked on element : "+element.toString());
		
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		//logg.info("Value of the "+element.toString()+"before any changes made");
		
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
	}

	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	
	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
//	private static boolean takingScreenshot = false;

	public  void  onException(Throwable throwable, WebDriver driver) {
//	logg.info("Exception Occured :" + throwable);
//		synchronized(this) {
//			if (takingScreenshot) return;
//			else takingScreenshot = true;
//		}
//		String fs = FileSystems.getDefault().getSeparator();
//		try {
//			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//			String currentDir = System.getProperty("user.dir");
//			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			
//			FileHandler.copy(scrFile, new File(
//					currentDir + fs + "test-output" + fs +"htmlReport"+ fs +"screenshot" + fs + timestamp + "_screenshot.png"));
			
//		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		synchronized(this) {
//			takingScreenshot = false;
//		}
	}
	public void beforeGetText(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		// TODO Auto-generated method stub
		
	}

}
