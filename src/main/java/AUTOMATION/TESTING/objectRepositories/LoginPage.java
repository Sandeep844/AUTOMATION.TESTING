package AUTOMATION.TESTING.objectRepositories;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AUTOMATION.TESTING.testBase.TestBase;
import AUTOMATION.TESTING.utility.CommonUtilities;



public class LoginPage extends TestBase {
	 public static final Logger logg=Logger.getLogger(LoginPage.class.getName());
	/*All Object Repository*/
	
	@FindBy(name="email")
	private WebElement username;
	
	@FindBy(name="password")
	private WebElement password;
	
	@FindBy(xpath="//button[@type='submit']")//*[contains(text(),'Sign In')]
	private WebElement SinInBtn;
	
	@FindBy(xpath="//div[@class='MuiAlert-message css-1xsto0d']")
	private WebElement error_message;
	
	@FindBy(xpath="//p[@class='MuiTypography-root MuiTypography-body1 css-jc35um']")
	private WebElement usernameErrorMsg;
	
	@FindBy(xpath="//p[@class='MuiTypography-root MuiTypography-body1 css-u5q6fm-MuiTypography-root']")
	private WebElement passwordErrorMessage;
	
	@FindBy(xpath = "(//*[@class='MuiTypography-root MuiTypography-body1 css-jc35um'])[1]")
	private WebElement blankEmailIderror;
	
	@FindBy(xpath = "//*[contains(text(),'Invalid password')]")
	private WebElement blankPasswordError;
	
	@FindBy(xpath="//a[normalize-space()='Request Reset Link']")
	private WebElement forgetPasswordLink;
	
	//@FindBy(xpath="//h1[normalize-space()='Forgot password']")
	//private WebElement forgetPasswordPage;
	
	@FindBy(xpath="//span[normalize-space()='Sign In']")
	private WebElement clickOnSignInButton;
	
	@FindBy(xpath="//h1[normalize-space()='Welcome']")
	private WebElement loginWelcomePage;
	
	@FindBy(xpath="//p[normalize-space()='Let’s get you started on Polarin']")
	private WebElement loginPage_afterlogin;
	
	@FindBy(xpath="//span[normalize-space()='Sign Up']")
	private WebElement signUpButton;
	
	@FindBy(xpath="//div[@class='css-1tc234f-MuiStack-root']//child::span")
	private WebElement attemptsRemainingCount;
	
	//Forgotpassword page xpaths storing in login page as of now
	@FindBy(xpath="(//*[contains(text(),'Forgot password')])[1]")
	private WebElement ForgotPasswordpage;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	public void inValid_Credentials_login(String invaliduser, String invalidpassw) {
		CommonUtilities.sendkeys(username, invaliduser);
		logg.info("Invalid Username = "+invaliduser);
		CommonUtilities.threadSleep(2000);
		CommonUtilities.sendkeys(password, invalidpassw);
		logg.info("Invalid Password = "+invalidpassw);	
	}
	public void valid_Credentials_login(String validuser, String validpassw) {
		CommonUtilities.sendkeys(username, validuser);
		logg.info("Invalid Username = "+validuser);
		CommonUtilities.threadSleep(2000);
		CommonUtilities.sendkeys(password, validpassw);
		logg.info("Invalid Password = "+validpassw);	
	}
	
	/*
	 * public void valid_Credentials_login(String validuser,String validpass ) {
	 * username.clear(); username.sendKeys(validuser);
	 * logg.info("Valid UserName = "+validuser); password.clear();
	 * password.sendKeys(validpass); logg.info("Valid Password = "+validpass); try {
	 * Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
	 * SinInBtn.click(); return valid_Credentials_login(); }
	 */
	 
	
	public String errormessage() {
		return error_message.getText();
	}
	public String userNameErrorMsg() {
		return usernameErrorMsg.getText();
	}
	public String userPasswordErrorMsg() {
		return passwordErrorMessage.getText();
	}
	public String blankEmailErrorMsg() {
		return blankEmailIderror.getText();
	}
	public String blankPasswordErrorMsg() {
		return blankPasswordError.getText();
	} 
	public void clickOnSignInButton() {
		SinInBtn.click();	
	}
	public void blankCreadencialEnter() {
		username.sendKeys("");
		password.sendKeys("");	
	}
	public void refreshBrowser() {
		driver.navigate().refresh();
	}
	public String attemptsRemaining() {
	return	attemptsRemainingCount.getText();
	}
	//public String forgetPasswordPage() {
	//	return forgetPasswordPage.getText();
	//}
	public String afterloginPage() {
			return loginPage_afterlogin.getText();
		}
	
	//newly created
	public String ForgotPasswordPage()
	{
		return ForgotPasswordpage.getText();
	}
	
	public void enterEmailID(String emailid) {
		username.click();
		CommonUtilities.threadSleep(1000);
		CommonUtilities.sendkeys(username, emailid);
		//username.sendKeys(emailid);
	}
	
	public void goBackToSignInPage() {
		clickOnSignInButton.click();
	}
	public void clickOnRequestResetPasswordLink() {
		forgetPasswordLink.click();
	}
	
	public boolean isOnLoginPage() {
		try {
			WebElement welcomeheadleine=CommonUtilities.elementToBeVisible(loginWelcomePage, 5);
			welcomeheadleine.getText().equals("Welcome");
	        return true;
		}catch(Exception e ) {
	        return false;	
		}
	}
	public void clickOnSignUpButton() {
		signUpButton.click();
	}
	//newly created
	public boolean isOnForgotPasswordPage()
	{
		try
		{
			WebElement forgotpasswordheadline=CommonUtilities.elementToBeVisible(ForgotPasswordpage, 5);
			forgotpasswordheadline.getText().equals("Forgot password");
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
}

