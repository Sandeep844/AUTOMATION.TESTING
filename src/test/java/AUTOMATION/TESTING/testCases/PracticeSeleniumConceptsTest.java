package AUTOMATION.TESTING.testCases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import AUTOMATION.TESTING.objectRepositories.LoginPage;
import AUTOMATION.TESTING.testBase.TestBase;
import AUTOMATION.TESTING.utility.CommonUtilities;

public class PracticeSeleniumConceptsTest extends TestBase{
	
		 public static final Logger logg=Logger.getLogger(PracticeSeleniumConceptsTest.class.getName());
		  public AUTOMATION.TESTING.objectRepositories.prcaticeSeleniumConcepts prcaticeSeleniumConcepts;
		//  public DashBoardPage dashboard;
			
			public PracticeSeleniumConceptsTest() {
				super();	
			}
			@BeforeClass
			public void setUp() {
				browserOpen();
				prcaticeSeleniumConcepts=new AUTOMATION.TESTING.objectRepositories.prcaticeSeleniumConcepts();
				}
			
			@Test(priority = 1)
			public void TC_01_Verify_dynamic_DropDown()
			{
//				logg.info("*********** Start Polarin Login Functionality *************");
//				loginpage.blankCreadencialEnter();
//				logg.info("Entered blank Username and password for login");
//				loginpage.clickOnSignInButton();
//				logg.info("Click On Sign In Button");
//				String blankEmailErrMsg=loginpage.blankEmailErrorMsg();
//				Assert.assertEquals(blankEmailErrMsg, "Email cannot be blank");
//				logg.error("message should be displayed = "+blankEmailErrMsg);
//				String blankPasswordErrMsg=loginpage.blankPasswordErrorMsg();
//				Assert.assertEquals(blankPasswordErrMsg, "Invalid password");
//				logg.error("Error message should be displayed = "+blankPasswordErrMsg);
				
				
			}
			
			
			  
			@AfterClass
			public void close_browser() {
				logg.info("Closing Browser");
				logg.info("*********** End Polarin Login Functionality *************");
			    driver.close();	
			}
	}


