package AUTOMATION.TESTING.testCases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import AUTOMATION.TESTING.objectRepositories.LoginPage;
import AUTOMATION.TESTING.testBase.TestBase;
import AUTOMATION.TESTING.utility.CommonUtilities;

public class PolarinLoginTestCases extends TestBase {
	 public static final Logger logg=Logger.getLogger(PolarinLoginTestCases.class.getName());
	  public LoginPage loginpage;
	//  public DashBoardPage dashboard;
		
		public PolarinLoginTestCases() {
			super();	
		}
		@BeforeClass
		public void setUp() {
			browserOpen();
			loginpage=new LoginPage();
			}
		
		@Test(priority = 1)
		public void TC_01_Verify_that_the_User_is_not_able_to_log_in_with_a_blank_Username_or_Password()
		{
			logg.info("*********** Start Polarin Login Functionality *************");
			loginpage.blankCreadencialEnter();
			logg.info("Entered blank Username and password for login");
			loginpage.clickOnSignInButton();
			logg.info("Click On Sign In Button");
			String blankEmailErrMsg=loginpage.blankEmailErrorMsg();
			Assert.assertEquals(blankEmailErrMsg, "Email cannot be blank");
			logg.error("message should be displayed = "+blankEmailErrMsg);
			String blankPasswordErrMsg=loginpage.blankPasswordErrorMsg();
			Assert.assertEquals(blankPasswordErrMsg, "Invalid password");
			logg.error("Error message should be displayed = "+blankPasswordErrMsg);
		}
		
		
		  @Test(priority = 2)
		  public void TC_02_Verify_that_the_User_is_not_able_to_Login_with_an_invalid_Username_and_invalid_Password()
		  {
			  loginpage.inValid_Credentials_login("sandeep@gmail.com", "Password@123");
		  logg.info("Entered InValid Username and password for login");
		  loginpage.clickOnSignInButton(); 
		  logg.info("Click On Sign In Button"); 
		  String errormsg=loginpage.errormessage();
		  Assert.assertEquals(errormsg,"User email or password is incorrect");
		  logg.error("This error message should be displayed = "+errormsg);
		  loginpage.refreshBrowser(); 
		 
		  }
		  @Test(priority=3)
		  public void TC4_Verify_forgot_password_link_and_landing_page_correct()
		  {
		  loginpage.clickOnRequestResetPasswordLink();
		  logg.info("clicking on reset password link");
		  String landingpageforgot=loginpage.ForgotPasswordPage();
		  logg.info("Landing page name="+landingpageforgot);
	       Assert.assertEquals(landingpageforgot,"Forgot password", "user is on Forgotpassword page");
	       CommonUtilities.threadSleep(2000); 
			 loginpage.goBackToSignInPage();
			 logg.info("Clicking on back to sign in button"); 
	     
		  }
		 	// any method can be used above
			/*
			 * @Test(priority=4) public void
			 * TC5_Verify_forgot_password_Redirected_to_Forgotpassword_page_correct() {
			 * 
			 * // Step 1: Verify user is on Login Page
			 * 
			 * if (loginpage.isOnLoginPage()) { logg.info("✅ User is on Login Page");
			 * 
			 * // Step 2: Click on Resetpassword link
			 * loginpage.clickOnRequestResetPasswordLink();
			 * logg.info("clicking on reset password link");
			 * CommonUtilities.threadSleep(2000);
			 * 
			 * // Step 3: Verify redirection to on Forgotpassword page boolean
			 * isOnForgotPasswordPage= loginpage.isOnForgotPasswordPage();
			 * 
			 * logg.info("Forgotpassword Page is displayed = " + isOnForgotPasswordPage);
			 * 
			 * Assert.assertTrue(isOnForgotPasswordPage,
			 * "User is redirected to forgot password Page."); } else { logg.
			 * error("❌ User is not on Login forgot password page, cannot perform redirection validation."
			 * ); Assert.fail("Not on forgot password page"); } }
			 */
			/*
			 * @Test(priority = 6) public void
			 * TC_06_Verify_that_the_user_can_enter_the_email_address_in_the_email_text_box
			 * () {
			 * loginpage.enterEmailID(CommonUtilities.generateUniqueAlpahabeticalString(15))
			 * ; String invalidEmailerrorMessage=loginpage.blankEmailErrorMsg();
			 * Assert.assertEquals(invalidEmailerrorMessage, "Invalid email");
			 * logg.error("message should be displayed = "+invalidEmailerrorMessage);
			 * CommonUtilities.threadSleep(2000); loginpage.goBackToSignInPage();
			 * logg.info("Clicking on back to sign in button"); }
			 */

		  @Test(priority = 7)
		  public void TC_07_Verify_that_the_user_should_be_redirected_to_the_sign_up_page_after_clicking_the_sign_up_link() 
		  {
			  CommonUtilities.threadSleep(2000);
			  if(loginpage.isOnLoginPage()==false)
		  { 
				  boolean signuppage=loginpage.isOnLoginPage();
		  logg.info("Sign Up Page Link click = "+signuppage);
		  loginpage.goBackToSignInPage();
		  logg.info("Clicking on back to sign in button"); 
		  }
		  loginpage.clickOnSignUpButton();
		  loginpage.goBackToSignInPage();
		  logg.info("Clicking on back to sign in button"); 
		  }
		  
		  @Test(priority = 8)
		 public void TC_08_Verify_if_a_user_can_not_login_with_a_invalid_username_and_valid_password() throws InterruptedException 
		  { 
			  CommonUtilities.threadSleep(2000);
		  if(loginpage.isOnLoginPage()==false) 
		  { 
			  boolean abcd=loginpage.isOnLoginPage();
		  System.out.println("***Login Page *****"+abcd);
		  loginpage.goBackToSignInPage();
		  logg.info("Clicking on back to sign in button"); 
		  }
		  CommonUtilities.threadSleep(2000);
		 // loginpage.inValid_Credentials_login(CommonUtilities.generateFirstName()+"@lightstorm.com",prop.getProperty("loginPassword"));
		  loginpage.inValid_Credentials_login("naspoorisandeep@gmail.com", "Password@123");
		  logg.info("Entered InValid Username and password for login");
		  loginpage.clickOnSignInButton(); 
		  logg.info("Click On Sign In Button");
		  String errormsg=loginpage.errormessage(); 
		  Assert.assertEquals(errormsg,"User email or password is incorrect");
		  logg.error("This error message should be displayed = "+errormsg);
		  loginpage.refreshBrowser();
		  }
		  
		  @Test(priority = 9)
		  public void TC_09_Verify_if_a_user_can_not_login_with_a_valid_username_and_an_invalid_password() throws InterruptedException
		  { 
			  CommonUtilities.threadSleep(2000);
		  loginpage.inValid_Credentials_login(prop.getProperty("loginUsername"),CommonUtilities.generateUniqueAlpahabeticalString(4)+"@"+CommonUtilities.
		  generateRandomNumericValue(3));
		  logg.info("Entered InValid Username and password for login");
		  loginpage.clickOnSignInButton();
		  logg.info("Click On Sign In Button"); 
		  String errormsg=loginpage.errormessage();
		  Assert.assertEquals(errormsg, "User email or password is incorrect");
		  logg.error("This error message should be displayed = "+errormsg);
		  loginpage.refreshBrowser(); }
		  
		  
		  //Positive Scenarios
		  @Test (priority = 10)
		 public void TC_10_Verify_that_the_User_is_able_to_Login_with_Valid_Username_and_Password() throws InterruptedException
		  {
			  CommonUtilities.threadSleep(2000);
			  loginpage.valid_Credentials_login(prop.getProperty("loginUsername"), prop.getProperty("loginPassword"));
			  logg.info("Entered Valid Username and Valid password for Login");
			  loginpage.clickOnSignInButton();
			  logg.info("Click on Sign In Button");
			  CommonUtilities.threadSleep(2000);
			  String loginmessage =loginpage.afterloginPage();
			  Assert.assertEquals(loginmessage, "Let’s get you started on Polarin");
			  logg.info("Login page agter valid login="+loginmessage);
		  }
	
		 
		@AfterClass
		public void close_browser() {
			logg.info("Closing Browser");
			logg.info("*********** End Polarin Login Functionality *************");
		    driver.close();	
		}
}
