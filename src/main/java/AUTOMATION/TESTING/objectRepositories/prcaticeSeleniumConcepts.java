package AUTOMATION.TESTING.objectRepositories;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AUTOMATION.TESTING.testBase.TestBase;

public class prcaticeSeleniumConcepts extends TestBase{
	
	 public static final Logger logg=Logger.getLogger(prcaticeSeleniumConcepts.class.getName());
	/*All Object Repository*/
	
	@FindBy(name="email")
	private WebElement username;
	
	
	public prcaticeSeleniumConcepts() {
		PageFactory.initElements(driver, this);
	}
	
}
