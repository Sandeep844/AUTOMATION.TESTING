package kuby.web.testBase;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import kuby.web.utility.CommonUtilities;
import kuby.web.utility.WebEventListner;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

@SuppressWarnings("deprecation")
public class TestBase {
	public static String suiteStarttime;
	public static String suiteEndtime;
	public static WebDriver driver;
	public static boolean screenshotRequired;
	protected static HashMap<String, String> signUpMap;
	public static Properties prop;
	public static EventFiringWebDriver edriver;
	public static WebEventListner eventlistner;
	public static final Logger logg = Logger.getLogger(TestBase.class.getName());
	public static String fs = FileSystems.getDefault().getSeparator();

	static {
		signUpMap = new HashMap<>();
	}

	public TestBase() {
		String log4jConfigPath = System.getProperty("user.dir") + fs + "src" + fs + "main" + fs + "resources" + fs
				+ "log4j" + fs + "log4j.properties";
		PropertyConfigurator.configure(log4jConfigPath);
		try {
			String getEnv = CommonUtilities.getEnvironment();
			// String getEnv=System.getProperty("env","uat");
			prop = new Properties();
			if (getEnv.equals("dev")) {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir") + fs + "src" + fs + "main"
						+ fs + "resources" + fs + "config" + fs + "dev" + "_config.properties");
				prop.load(file);
			} else if (getEnv.equals("uat")) {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir") + fs + "src" + fs + "main"
						+ fs + "resources" + fs + "config" + fs + "uat" + "_config.properties");
				prop.load(file);
			} else if (getEnv.equals("newdc")) {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir") + fs + "src" + fs + "main"
						+ fs + "resources" + fs + "config" + fs + "newdc" + "_config.properties");
				prop.load(file);
			} else if (getEnv.equals("demo")) {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir") + fs + "src" + fs + "main"
						+ fs + "resources" + fs + "config" + fs + "demo" + "_config.properties");
				prop.load(file);
			} else if (getEnv.equals("prod")) {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir") + fs + "src" + fs + "main"
						+ fs + "resources" + fs + "config" + fs + "prod" + "_config.properties");
				prop.load(file);
			}
		} catch (FileNotFoundException f) {
			f.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public void browserOpen(String appURL) {
		String browsername = CommonUtilities.selectBrowser();
		if (browsername.equals("chrome")) {
			driver = new ChromeDriver();
			logg.info("Launching Chrome Browser");
		} else if (browsername.equals("firefox")) {
			driver = new FirefoxDriver();
			logg.info("Launching FireFoxBrowser");
		} else if (browsername.equals("msedge")) {
			logg.info("Launching Microsoft Edge Browser");
		} else if (browsername.equals("headless")) {
			logg.info("Launching HeadLess ChromeBrowser");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
		} else {
			System.out.println(
					"Please go to config property File and check whether you have given proper browser name or not");
			logg.error("The browser name is not specified correctly");
		}
		edriver = new EventFiringWebDriver(driver);
		eventlistner = new WebEventListner();
		edriver.register(eventlistner);
		driver = edriver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(CommonUtilities.PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(CommonUtilities.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(appURL);
		logg.info("Launching " + CommonUtilities.getEnvironment() + " Url");
		CommonUtilities.threadSleep(2000);
	}

	public String LaunchURL_NewTab(String appurl) {
		((JavascriptExecutor) driver).executeScript("window.open('')");
		driver.getWindowHandles().forEach(tab -> driver.switchTo().window(tab));
//		driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
		driver.get(appurl);
		logg.info("Launching " + CommonUtilities.getEnvironment() + " Url");
		CommonUtilities.threadSleep(2000);
		driver.manage().timeouts().pageLoadTimeout(CommonUtilities.PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(CommonUtilities.IMPLICIT_WAIT, TimeUnit.SECONDS);
		CommonUtilities.threadSleep(2000);
		return driver.getWindowHandle();
	}

	public String LaunchURL(String appurl) {
		driver.get(appurl);
		logg.info("Launching " + CommonUtilities.getEnvironment() + " Url");
		CommonUtilities.threadSleep(2000);
		driver.manage().timeouts().pageLoadTimeout(CommonUtilities.PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(CommonUtilities.IMPLICIT_WAIT, TimeUnit.SECONDS);
		return driver.getWindowHandle();
	}

	public void initializeBrowser() {
		String browsername = CommonUtilities.selectBrowser();
		if (browsername.equals("chrome")) {
			driver = new ChromeDriver();
			logg.info("Launching " + browsername + " Browser");
		} else if (browsername.equals("firefox")) {
			driver = new FirefoxDriver();
			logg.info("Launching " + browsername + " Browser");
		} else if (browsername.equals("msedge")) {
			// driver = WebDriverManager.edgedriver().create();
			logg.info("Launching " + browsername + " Browser");
		} else if (browsername.equals("headless")) {
			logg.info("Launching " + browsername + " Browser");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
		} else {
			System.out.println(
					"Please go to config property File and check whether you have given proper browser name or not");
			logg.error("The browser name is not specified correctly");
		}
		edriver = new EventFiringWebDriver(driver);
		eventlistner = new WebEventListner();
		edriver.register(eventlistner);
		driver = edriver;
		driver.manage().window().setSize(new org.openqa.selenium.Dimension(1440, 900));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(CommonUtilities.PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(CommonUtilities.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

	public void switchTo_Tab(String nameHandle) {
		driver.switchTo().window(nameHandle);
		CommonUtilities.threadSleep(1000);
	}

	public void browserOpen() {
		String browsername = CommonUtilities.selectBrowser();
		if (browsername.equals("chrome")) {
			// WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			logg.info("Launching Chrome Browser");
		} else if (browsername.equals("firefox")) {
			// WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			logg.info("Launching FireFoxBrowser");
		} else if (browsername.equals("msedge")) {
			// driver = WebDriverManager.edgedriver().create();
			logg.info("Launching Microsoft Edge Browser");
		} else if (browsername.equals("headless")) {
			logg.info("Launching HeadLess ChromeBrowser");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--remote-allow-origins=*");
			// WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		} else {
			System.out.println(
					"Please go to config property File and check whether you have given proper browser name or not");
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
		logg.info("Launching " + CommonUtilities.getEnvironment() + " Url");
		CommonUtilities.threadSleep(2000);
	}

	public static File destPath = new File(
			System.getProperty("user.dir") + fs + "test-output" + fs + "htmlReport" + fs + "screenshot");
	public static File log4jPath = new File(System.getProperty("user.dir") + fs + "test-output" + fs + "log4j");

//	public static String takeScreenshot(String testName) {
//		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		if (!destPath.exists())
//			destPath.mkdirs();
//		File destFile = new File(destPath, testName + ".png");
//		try {
//			FileHandler.copy(scrFile, destFile);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return destFile.getAbsolutePath();
//	}

	public static String takeScreenshot(String testName)
	{
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			if (!destPath.exists()) destPath.mkdirs();
			File destFile = new File(destPath, testName + ".png");
			try { FileHandler.copy(scrFile, destFile);
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		return destFile.getAbsolutePath();
	}
}
