package AUTOMATION.TESTING.testBase;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import AUTOMATION.TESTING.utility.CommonUtilities;
import AUTOMATION.TESTING.utility.WebEventListner;

@SuppressWarnings("deprecation")
public class TestBase {


	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver edriver;
	public static WebEventListner eventlistner;
	public static final Logger logg=Logger.getLogger(TestBase.class.getName());
	private static String fs =FileSystems.getDefault().getSeparator();
	public static String downloadPath=System.getProperty("user.dir")+fs+"test-output" + fs +"htmlReport"+ fs +"screenshot";
	public TestBase() {
		String log4jConfigPath=System.getProperty("user.dir")+fs+"src"+fs+"main"+fs+"resources"+fs+"log4j"+fs+"log4j.properties";
		PropertyConfigurator.configure(log4jConfigPath);
		try {
			String getEnv=CommonUtilities.getEnvironment();
			//String getEnv=System.getProperty("env","uat");
			    prop = new Properties();
			if(getEnv.equals("dev")) {
			FileInputStream file = new FileInputStream(System.getProperty("user.dir")
					+fs+"src"+fs+"main"+fs+"resources"+fs+"config"+fs+"dev"+"_config.properties");
			    prop.load(file);
			}else if(getEnv.equals("uat")) {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir")
					+fs+ "src"+fs+"main"+fs+"resources"+fs+"config"+fs+"uat"+"_config.properties");
				prop.load(file);
			}else if(getEnv.equals("newdc")) {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir")
					+fs+ "src"+fs+"main"+fs+"resources"+fs+"config"+fs+"newdc"+"_config.properties");
				prop.load(file);
			}else if(getEnv.equals("demo")) {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir")
					+fs+ "src"+fs+"main"+fs+"resources"+fs+"config"+fs+"demo"+"_config.properties");
				prop.load(file);
			}
		} catch (FileNotFoundException f) {
			f.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	public void browserOpen() {
		String browsername=CommonUtilities.selectBrowser();
		if (browsername.equals("chrome")) {
		//	WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> pref=new HashMap<>();
            pref.put("download.default_directory", downloadPath);
            options.setExperimentalOption("prefs", pref);
			driver = new ChromeDriver(options);
			logg.info("Launching Chrome Browser");
		} else if (browsername.equals("firefox")) {
		//	WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			logg.info("Launching FireFoxBrowser");
		} else if (browsername.equals("msedge")) {
		//	driver = WebDriverManager.edgedriver().create();
			logg.info("Launching Microsoft Edge Browser");
		} else if (browsername.equals("headless")) {
			logg.info("Launching HeadLess ChromeBrowser");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-extensions");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            Map<String, Object> pref=new HashMap<>();
            pref.put("download.default_directory", downloadPath);   
            options.setExperimentalOption("prefs", pref);
			driver = new ChromeDriver(options);
		}else{
			System.out.println("Please go to config property File and check whether you have given proper browser name or not");
			logg.error("The browser name is not specified correctly");
		}
		edriver = new EventFiringWebDriver(driver);
		eventlistner = new WebEventListner();
		edriver.register(eventlistner);
		driver = edriver;
		driver.manage().window().setSize(new Dimension(1440, 900));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(CommonUtilities.PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(CommonUtilities.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		logg.info("Launching "+CommonUtilities.getEnvironment()+" Url");
	}
	
	public static File destPath=new File(System.getProperty("user.dir")+fs+"test-output" + fs +"htmlReport"+ fs +"screenshot");
	public static File log4jPath=new File(System.getProperty("user.dir")+fs+"test-output" + fs +"log4j");
	public  String takeScreenshot(String testName, WebDriver driver) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		if (!destPath.exists()) destPath.mkdirs();
		File destFile=new File(destPath,testName+".png");	
		try {
			FileHandler.copy(scrFile,destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.getAbsolutePath();
	}	
}