package AUTOMATION.TESTING.objectRepositories;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Random;
import org.apache.log4j.Logger;

import org.testng.Assert;

import AUTOMATION.TESTING.utility.CommonUtilities;
import AUTOMATION.TESTING.utility.StringWords;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreatePortPage {

	public static final Logger logg = Logger.getLogger(CreatePortPage.class.getName());

	private static final SearchContext driver = null;

	/*****************************************************************************
	 * Port Location New
	 *****************************************************************************/

	@FindBy(xpath = "//h1[text()='Create Port']")
	private WebElement createportheader;

	@FindBy(xpath = "//input[@placeholder='Enter port name']/../following-sibling::p")
	private WebElement portNameErrorMessage;

	@FindBy(xpath = "//button[text()='Add Billing Profile']")
	private WebElement addBillingProfileButton;
	/*****************************************************************************
	 * Port Location
	 *****************************************************************************/

	@FindBy(xpath = "//input[@placeholder='Select billing profile']")
	private List<WebElement> BillingProfileList;

	@FindBy(xpath = "//button[text()='Create']")
	private WebElement serviceCreateButton;

	@FindBy(xpath = "//p[contains(text(),'Create a Port')]")
	private WebElement clickoncreatePortLink;

	@FindBy(xpath = "//input[@class='MuiInputBase-input MuiOutlinedInput-input css-1x5jdmq']")
	private WebElement selectCountryfrmDropDown;

	@FindBy(xpath = "//input[@class='MuiInputBase-input MuiOutlinedInput-input css-1x5jdmq']//../following-sibling::div/p")
	private WebElement selectedCountryfromvalue;

	@FindBy(xpath = "//input[@placeholder='Search Location']")
	private WebElement searchLocation;

	@FindBy(xpath = "//*[@class='MuiTypography-root MuiTypography-body2 MuiListItemText-primary css-u12yly']")
	private List<WebElement> allPort;

	@FindBy(xpath = "//button[text()='Save & Next']")
	private WebElement saveAndNextBtn;

	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	private WebElement cancelBtn;

	@FindBy(xpath = "(//button[@type='button'])[7]")
	private WebElement crossCancelButton;

	@FindBy(xpath = "//div[@class='css-b32f14']//descendant::input")
	private WebElement countryDropDownOnLocationPage;

	@FindBy(xpath = "//input[@placeholder='Search Country']")
	private WebElement countryTextBoxOnLocationPage;

	@FindBy(xpath = "//ul[@id='use-autocomplete-demo-listbox']/li")
	private List<WebElement> portlocationlist;
	@FindBy(xpath = "//button[text()='Next']")
	private WebElement nextButton;

	/*********************************************************************************
	 * Configure Port
	 *******************************************************************************/
	@FindBy(name = "portName")
	private WebElement portNameTextBox;

	@FindBy(xpath = "//*[contains(text(),'name must be unique')]")
	private WebElement portNameEnterSameErrorMsg;

	@FindBy(xpath = "//div[@class='MuiFormControl-root css-tzsjye']//following-sibling::p")
	private WebElement portNameExceedLengthError;

	@FindBy(xpath = "//span[@name='isLacpEnabled']/input[@type='checkbox']")
	private WebElement lagToggleButtonClick;

	@FindBy(xpath = "//div[@class='MuiBox-root css-1fhgjcy']//child::p")
	private WebElement helpTextBelowLAGToggle;

	@FindBy(id = "mui-component-select-numberOfPortsInLag")
	private WebElement clickOnSelectLAG;

	@FindBy(xpath = "//ul[@class='MuiList-root MuiList-padding MuiMenu-list css-r8u8y9']//li")
	private List<WebElement> selectLAGfromlist;

	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiTab-root MuiTab-textColorPrimary css-1edfv2d']")
	private WebElement portSpeedInGBBtn;

	@FindBy(xpath = "//button[contains(text(),'GE')]")
	private List<WebElement> portSpeedSelection;

	@FindBy(xpath = "//button[text()='1 GE']")
	private WebElement selectOneGSpeed;

	@FindBy(xpath = "//button[text()='10 GE']")
	private WebElement selectTenGSpeed;

	@FindBy(xpath = "//button[text()='100 GE']")
	private WebElement selectHundradeGSpeed;

	@FindBy(xpath = "//p[text()='Port Location']//child::span")
	private WebElement portLocationEditButton;

	@FindBy(xpath = "//p[text()='Configure Port']//following-sibling::p")
	private WebElement getPortNameFromConfigurePage;

	@FindBy(xpath = "//div[@name='term']/button")
	private List<WebElement> subscriptionselection;

	@FindBy(xpath = "//div[@name='upfrontMonths']/button")
	private List<WebElement> paymentModeselection;

	@FindBy(xpath = "(//div[@class='css-9jay18'])[1]")
	private WebElement subTotalForMonth;

	@FindBy(xpath = "(//*[@class='MuiTypography-root MuiTypography-h5 css-g1mnfr'])[2]")
	private WebElement subTotalAmountWithOutDicounted;

	@FindBy(xpath = "//p[text()='Discount: ']//following-sibling::p")
	private WebElement discountInPercentage;

	@FindBy(xpath = "//h5[text()='Total amount to be paid: ']//following-sibling::h5")
	private WebElement totalAmmount;

	@FindBy(xpath = "//p[text()='Upfront Payment: ']//following-sibling::p")
	private WebElement upfrontPayment;

	@FindBy(xpath = "(//p[@class='MuiTypography-root MuiTypography-body2 css-cnyufv'])[4]")
	private WebElement youPayAmounteverymonth;

	@FindBy(xpath = "//div[@class='MuiAlert-message css-1xsto0d']")
	private WebElement errorMessagePortAlreadyExit;

	/***************************************************************
	 * New Price Summary Xpath
	 **************************************************************/
	@FindBy(xpath = "//div[@class='css-1mdvt5f']//following-sibling::p")
	private WebElement blankInfoMessage;

	@FindBy(xpath = "//h5[contains(text(),'Subtotal for')]")
	private WebElement subtotalforMonthsOnPriceSummary;

	@FindBy(xpath = "//h5[normalize-space()='Total amount to be paid:']//following-sibling::h5")
	private WebElement totalAmountToBePaidOnPriceSummary;

	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body2 css-cnyufv']//following-sibling::p")
	private WebElement monthlyPaymentOnPriceSummary;

	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body2 css-9ri8fx']")
	private WebElement discountAmountOnPriceSummary;

	@FindBy(xpath = "//h5[normalize-space()='Per hour:']//following-sibling::h5")
	private WebElement perHourAmountOnPriceSummaryPAYG;

	@FindBy(xpath = "//h5[normalize-space()='You pay per hour:']//following-sibling::h5")
	private WebElement youPayPerHourOnPriceSummaryPAYG;

	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body2 css-cnyufv']//following-sibling::p")
	private WebElement estimatedMonthlyPaymentOnPriceSummaryPAYG;
	/***************************************************************************************
	 * CheckOut Page
	 ***************************************************************************************/
	@FindBy(xpath = "//input[@class='MuiInputBase-input MuiInputBase-inputAdornedEnd css-mnn31']")
	private WebElement billingSearchButton;

	@FindBy(xpath = "//input[@class='MuiInputBase-input MuiInputBase-inputAdornedStart MuiAutocomplete-input MuiAutocomplete-inputFocused css-mnn31']")
	private WebElement searchBillingProfile;

	// @FindBy(xpath="//div[@class='MuiAutocomplete-paper MuiBox-root
	// css-hkv3bg']//following-sibling::li")
	// private List<WebElement> allBillingProfiles;

	@FindBy(xpath = "//div[@class='MuiAutocomplete-paper MuiBox-root css-hkv3bg']//li")
	private List<WebElement> allBillingProfiles;
	//all billing profiles

	@FindBy(xpath = "//*[contains(text(),'Port Details')]")
	private WebElement clickOnPortDetails;

	@FindBy(xpath = "//h6[text()='Price Summary']/../following-sibling::h5")
	private WebElement checkoutpageTotalAmount;

	@FindBy(xpath = "//button[contains(text(),'Order')]")
	private WebElement clickOnOrderPlace;

	@FindBy(xpath = "//input[@class='PrivateSwitchBase-input css-1m9pwf3']")
	private WebElement clickOnCheckBoxForiAgreeServices;

	@FindBy(xpath = "//button[contains(text(),'Back to Services screen')]")
	private WebElement portCreatedPopUpMessage;

	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-1pmu3vr']")
	private WebElement defaultCountryName;

	@FindBy(xpath = "//div[@class='css-11x7pjz']//h1")
	private WebElement verifyAfterClickCreatePort;

	@FindBy(xpath = "//div[@class='css-17d4jz7']")
	private WebElement polarinLogo;

	@FindBy(xpath = "//p[text()='Configure Port'][1]")
	private WebElement configurePortLabel;

	@FindBy(xpath = "//div[@class='MuiTabs-flexContainer css-k008qs']/button")
	private List<WebElement> portSpeedActiveList;

	@FindBy(xpath = "//h5[text()='Review Order Details:']")
	private WebElement revieworderheader;

	@FindBy(xpath = "//div[@id='__next']/div[@role='presentation']")
	private WebElement SuccessNotification;

	@FindBy(xpath = "//button[text()='Back']")
	private WebElement backButton;

	public CreatePortPage() {
		PageFactory.initElements(driver, this);
	}

	/****************************************************************************
	 * Port Creation & Location Selection
	 ****************************************************************************/
	public void clickOnCreateButton() {
		try {
			CommonUtilities.elementToBeClickable(serviceCreateButton, 30).click();
		} catch (StaleElementReferenceException element) {
			CommonUtilities.elementToBeClickable(serviceCreateButton, 30).click();
		}
	}

	public void selectCreatePortLink() {
		CommonUtilities.elementToBeClickable(clickoncreatePortLink, 30).click();
	}

	public String getSelectedCountryNameDropDown() {
		CommonUtilities.elementToBeVisible(defaultCountryName, 20);
		return defaultCountryName.getText();
	}

	public void selectTheCountryfromDropDown(String selectCountry) {
		try {
			String defaultCountry = getSelectedCountryNameDropDown();
			if (!defaultCountry.equals(selectCountry)) {
				logg.info("Select The Country");
				WebElement clickOnCountryDropDown = CommonUtilities.elementToBeClickable(selectCountryfrmDropDown, 10);
				clickOnCountryDropDown.click();
				WebElement countryPlaceHolder = CommonUtilities.elementToBeVisible(countryTextBoxOnLocationPage, 10);
				countryPlaceHolder.sendKeys(Keys.CONTROL + "a");
				logg.info("If country is already selected press Ctrl + 'A' to deselect the country");
				countryPlaceHolder.sendKeys(Keys.DELETE);
				logg.info("deleting Existing Country");

				clickOnCountryDropDown.click();
				CommonUtilities.threadSleep(1000);
				countryPlaceHolder.sendKeys(selectCountry);
				CommonUtilities.threadSleep(1000);

				List<WebElement> countryList = driver
						.findElements(By.xpath("//ul[@class='MuiAutocomplete-listbox css-1eul6y3']/li/span/p"));
				for (int i = 0; i < countryList.size(); i++) {
					String tempName = countryList.get(i).getText();
					logg.info("Country Name " + tempName);
					if (tempName.equalsIgnoreCase(selectCountry)) {
						countryList.get(i).click();
						break;
					}
				}
				CommonUtilities.threadSleep(2000);
			}
			logg.info("Country Already Selected");
		} catch (Exception e) {
			Assert.fail("Test Case failed to select country with exception error := ", e);
		}
	}

	public void clickOnSearchLocationDropdown() {
		CommonUtilities.elementToBeClickable(searchLocation, 20).click();
	}

	public String getDataLocationValue() {
		return searchLocation.getAttribute("value");
	}

	public void clickOnSearchResult(String clickAllPortText) {
		Boolean isresultFound = false;
		List<WebElement> allText = allPort;
		for (WebElement text : allText) {
			if (text.getText().equalsIgnoreCase(clickAllPortText)) {
				WebElement textlocation = CommonUtilities.elementToBeClickable(text, 15);
				CommonUtilities.JSClick(textlocation);
				isresultFound = true;
				break;
			}
			logg.info(text);
		}
		if (!isresultFound)
			CommonUtilities.JSClick(allText.get(0));

	}

	/*
	 * public void selectPortSpeedEnabledLocation(String portlocation, String
	 * portType) { Boolean isPortLocspeedEnabled = false;
	 * clickOnSearchLocationDropdown();
	 * logg.info("Search DC Location from given list");
	 * clickOnSearchResult(portlocation);
	 * logg.info("Select any Location from Given List"); boolean nextbuttonflag =
	 * isNextButtonEnabled(); logg.info("next button is enabled = " +
	 * nextbuttonflag); Assert.assertTrue(nextbuttonflag,
	 * "next button is not enabled."); clickNextButton();
	 * logg.info("click on Next button"); CommonUtilities.threadSleep(1000); if
	 * (portType.equalsIgnoreCase("LAG")) { enableLAGoption();
	 * logg.info("Enable The LAG options"); boolean lagToggleFlag =
	 * isToggleButtonOn(); logg.info("Is the toggle button on after clicking? = " +
	 * lagToggleFlag); Assert.assertTrue(lagToggleFlag,
	 * "Lag Toggle option is not enabled after selecting"); clickOnNumberPortLAG();
	 * logg.info("click On Lag Number Port");
	 * selectLAGNumberfromDropDown(StringWords.enterTestData("PortLAGNumber"));
	 * logg.info("Select the number of ports you want to create"); } boolean
	 * isPortSpeedSelected = selectPort_Speed(); if (!isPortSpeedSelected) {
	 * CommonUtilities.clickOnWebElement(backButton);
	 * CommonUtilities.threadSleep(1000); String dataLocationPipe =
	 * StringWords.enterTestData("ActivePortLocation");//getTestData(
	 * "ActivePortLocation"); String[] dataLocList = dataLocationPipe.split(",");
	 * int loccount = dataLocList.length; // int loccount = portlocationlist.size();
	 * if (loccount > 0) { for (int i = 0; i < loccount; i++) { try {
	 * clickOnSearchResult(dataLocList[i]); WebElement datalocationEle =
	 * driver.findElement(By.xpath(
	 * "//ul[@id='use-autocomplete-demo-listbox']/li//span[text()='" +
	 * dataLocList[i] + "']")); CommonUtilities.moveToElement(datalocationEle);
	 * logg.info("Select any Location from Given List" + dataLocList[i]);
	 * nextbuttonflag = isNextButtonEnabled(); logg.info("next button is enabled = "
	 * + nextbuttonflag); Assert.assertTrue(nextbuttonflag,
	 * "next button is not enabled.");
	 * CommonUtilities.clickOnWebElement(nextButton);
	 * CommonUtilities.threadSleep(1000); if (portType.equalsIgnoreCase("LAG")) {
	 * enableLAGoption(); logg.info("Enable The LAG options"); boolean lagToggleFlag
	 * = isToggleButtonOn(); logg.info("Is the toggle button on after clicking? = "
	 * + lagToggleFlag); Assert.assertTrue(lagToggleFlag,
	 * "Lag Toggle option is not enabled after selecting"); clickOnNumberPortLAG();
	 * logg.info("click On Lag Number Port");
	 * selectLAGNumberfromDropDown(StringWords.enterTestData("PortLAGNumber"));
	 * logg.info("Select the number of ports you want to create"); }
	 * isPortSpeedSelected = selectPort_Speed(); if (isPortSpeedSelected) {
	 * isPortLocspeedEnabled = true; break; }
	 * CommonUtilities.clickOnWebElement(backButton);
	 * CommonUtilities.threadSleep(1000); } catch (Exception e) {
	 * logg.info("Exception occured: "); e.printStackTrace(); } } if
	 * (!isPortLocspeedEnabled) {
	 * Assert.fail("Port Speed is not enabled for all Port Locations"); } } else {
	 * Assert.fail("Port Locations are not availble for the selected country"); } }
	 */
//		if (isPortSpeedSelected && portType.equalsIgnoreCase("LAG")) {
//			clickOnNumberPortLAG();
//			logg.info("click On Lag Number Port");
//			selectLAGNumberfromDropDown(StringWords.enterTestData("PortLAGNumber"));
//			logg.info("Select the number of ports you want to create");
//		}

	public void selectPortorRouterLocation() {
		int loccount = portlocationlist.size();
		if (loccount > 0) {
			for (int i = 0; i < loccount; i++) {
				WebElement locelement = portlocationlist.get(i);
				try {
					CommonUtilities.moveToElement(locelement);
					CommonUtilities.JSClick(locelement);
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			CommonUtilities.threadSleep(1000);
		} else {
			Assert.fail("Port/router Locations are not availble for the selected country");
		}

	}

	public void clickOnSaveAndNextBtn() {
		try {
			CommonUtilities.clickOnWebElement(saveAndNextBtn);
			CommonUtilities.threadSleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickNextButton() {
		CommonUtilities.clickOnWebElement(nextButton);
		CommonUtilities.threadSleep(2000);
	}

	/******************************************************
	 * Configure Port
	 *******************************************************/
	public void clickOnCancelBtn() {
		cancelBtn.click();
	}

	public void enterPortNameinTextBox(String portname) {
		CommonUtilities.sendkeys(portNameTextBox, portname);
	}

	/*
	 * public void enableLAGoption() {
	 * CommonUtilities.selectCheckbox(lagToggleButtonClick, "ON");
	 * 
	 * }
	 */

	public boolean isToggleButtonOn() {
		return lagToggleButtonClick.isSelected();
	}

	public String getHelpTextAboutToggleButtonInfo() {
		return helpTextBelowLAGToggle.getText();
	}

	public void clickOnNumberPortLAG() {
		CommonUtilities.clickOnWebElement(clickOnSelectLAG);
	}

	public void selectLAGNumberfromDropDown(String number) {
		CommonUtilities.selectOptionFromList(selectLAGfromlist, number);
	}

	public String getUniqueNameErrorMessage() {
		return portNameEnterSameErrorMsg.getText();
	}

	public void clickOnEditBtn() {
		portLocationEditButton.click();
	}

	public void selectPortSpeed() throws IllegalAccessException {
		boolean success = false;
		int maxAttempts = 3;
		int currentAttempt = 1;
		int turn = 0;
		while (!success && currentAttempt <= maxAttempts) {
			try {
				if (turn > 2) {
					break;
				}
				if (currentAttempt == 1) {
					selectOneGSpeed.click();
				} else if (currentAttempt == 2) {
					selectTenGSpeed.click();
				} else if (currentAttempt == 3) {
					selectHundradeGSpeed.click();
				}
				// If execution reaches here, the button was clicked successfully
				success = true;
				logg.info("Button clicked successfully!");
			} catch (NoSuchElementException | ElementNotInteractableException | StaleElementReferenceException e) {
				// Handle exceptions if the button was not found or not clickable
				logg.info("Exception encountered: " + e.getMessage());
				if (currentAttempt == maxAttempts) {
					// Code change location here
					clickOnEditBtn();
					CommonUtilities.threadSleep(2000);
					clickOnSearchResult(StringWords.enterTestData("ActivePortLocation"));
					CommonUtilities.threadSleep(2000);
					clickOnSaveAndNextBtn();
					CommonUtilities.threadSleep(3000);
					logg.info("Changing location and trying again...");
					currentAttempt = 1;
					turn++;
				} else {
					currentAttempt++;
				}
			}
		}
	}

	public void selectSubscriptionterm(String subscription) {
		boolean isSubscriptionSelected = false;
		CommonUtilities.threadSleep(1000);
		for (WebElement allSubscriptionPort : subscriptionselection) {
			String subscriptionport = allSubscriptionPort.getText();
			if (subscriptionport.equals(subscription)) {
				CommonUtilities.clickOnWebElement(allSubscriptionPort);
//				CommonUtilities.moveToSpecificElement(allSubscriptionPort);
//				CommonUtilities.JSClick(allSubscriptionPort);
				isSubscriptionSelected = true;
				logg.info("Subscription Term = " + subscriptionport);
				break;
			}
		}
		if (!isSubscriptionSelected) {
			Assert.fail("Provided Subscription is not available in the application");
		}
	}

	public void selectPayment(String payment) {
		boolean isPaymentSelected = false;
		for (WebElement allPaymentOption : paymentModeselection) {
			String paymentPort = allPaymentOption.getText();
			if (paymentPort.equals(payment)) {
				CommonUtilities.clickOnWebElement(allPaymentOption);
//				CommonUtilities.moveToSpecificElement(allPaymentOption);
//				CommonUtilities.JSClick(allPaymentOption);
				isPaymentSelected = true;
				// allPaymentOption.click();
				break;
			}
			logg.info("payment = " + paymentPort);
		}
		if (!isPaymentSelected) {
			Assert.fail("Provided payment type is not available in the application");
		}
	}

	public String getSelectedPaymentType() {
		String selectedpaymentType = "";
		for (WebElement allPaymentOption : paymentModeselection) {
			String paymentTypeFlag = allPaymentOption.getAttribute("aria-pressed");
			if (paymentTypeFlag.equalsIgnoreCase("True")) {
				logg.info("Selected payment Type is = " + allPaymentOption.getText());
				selectedpaymentType = allPaymentOption.getText();
				break;
			}
		}
		return selectedpaymentType;
	}

	/*
	 * public String subTotalFromMonthWise() { WebDriverWait wait = new
	 * WebDriverWait(driver, Duration.ofSeconds(10));
	 * wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement
	 * (subTotalForMonth, ""))); String subtotal = subTotalForMonth.getText();
	 * return subtotal.replaceAll(subtotal, subtotal); }
	 * 
	 * public String totalAmountWithOutDiscounted() { WebDriverWait wait = new
	 * WebDriverWait(driver, Duration.ofSeconds(20)); wait.until(ExpectedConditions
	 * .not(ExpectedConditions.textToBePresentInElement(
	 * subTotalAmountWithOutDicounted, "₹ 0.00"))); return
	 * subTotalAmountWithOutDicounted.getText(); }
	 * 
	 * public String paymentDiscount() { WebDriverWait wait = new
	 * WebDriverWait(driver, Duration.ofSeconds(20));
	 * wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement
	 * (discountInPercentage, ""))); return discountInPercentage.getText(); }
	 * 
	 * public String totalAmountAfterDiscount() { WebDriverWait wait = new
	 * WebDriverWait(driver, Duration.ofSeconds(20));
	 * wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement
	 * (totalAmmount, "₹ 0.00"))); return totalAmmount.getText(); }
	 * 
	 * public String upfrontPayment() { WebDriverWait wait = new
	 * WebDriverWait(driver, Duration.ofSeconds(20));
	 * wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement
	 * (upfrontPayment, "₹ NaN"))); return upfrontPayment.getText(); }
	 * 
	 * public String youPayAmountEveryMonth() { WebDriverWait wait = new
	 * WebDriverWait(driver, Duration.ofSeconds(20)); wait.until(
	 * ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(
	 * youPayAmounteverymonth, "₹ NaN"))); return youPayAmounteverymonth.getText();
	 * }
	 * 
	 *//************************************************
		 * New Price Summary
		 ********************************************//*
														 * public String priceSummaryInfoMessage() { WebDriverWait wait
														 * = new WebDriverWait(driver, Duration.ofSeconds(20));
														 * wait.until(ExpectedConditions.not(ExpectedConditions.
														 * textToBePresentInElement(blankInfoMessage, "₹ NaN"))); return
														 * blankInfoMessage.getText(); }
														 * 
														 * public String subtotalForMonthsOnPriceSummary() {
														 * WebDriverWait wait = new WebDriverWait(driver,
														 * Duration.ofSeconds(20)); wait.until(ExpectedConditions
														 * .not(ExpectedConditions.textToBePresentInElement(
														 * subtotalforMonthsOnPriceSummary, "₹ NaN"))); return
														 * subtotalforMonthsOnPriceSummary.getText(); }
														 * 
														 * public String totalAmountToBePaidOnPriceSummary() {
														 * WebDriverWait wait = new WebDriverWait(driver,
														 * Duration.ofSeconds(20)); wait.until(ExpectedConditions
														 * .not(ExpectedConditions.textToBePresentInElement(
														 * totalAmountToBePaidOnPriceSummary, "₹ NaN"))); return
														 * totalAmountToBePaidOnPriceSummary.getText(); }
														 * 
														 * public String monthlyPaymentOnPriceSummary() { WebDriverWait
														 * wait = new WebDriverWait(driver, Duration.ofSeconds(20));
														 * wait.until(ExpectedConditions
														 * .not(ExpectedConditions.textToBePresentInElement(
														 * monthlyPaymentOnPriceSummary, "₹ NaN"))); return
														 * monthlyPaymentOnPriceSummary.getText(); }
														 * 
														 * public String discountAmountOnPriceSummery() { WebDriverWait
														 * wait = new WebDriverWait(driver, Duration.ofSeconds(20));
														 * wait.until(ExpectedConditions
														 * .not(ExpectedConditions.textToBePresentInElement(
														 * discountAmountOnPriceSummary, "₹ NaN"))); return
														 * discountAmountOnPriceSummary.getText(); }
														 */
	public String getErrorIfPortIsAlreadyExit() {
		return errorMessagePortAlreadyExit.getText();
	}

	public String getPortNameFromConfigurePage() {
		String str = getPortNameFromConfigurePage.getText();
		String[] parts = str.split(",");
		if (parts.length > 0) {
			return parts[0].trim();
		} else {
			logg.info("Text Is not Present ");
		}
		return str;
	}

	public void clickOnErrorMessageCancelBtn() {
		WebElement crossbtn = CommonUtilities.elementToBeClickable(crossCancelButton, 20);
		CommonUtilities.JSClick(crossbtn);
	}

	/***********************************************************
	 * CheckOut
	 ***********************************************************/
	public boolean verifyBillingProfileSelected() {
		boolean isbillingSelected = true;
		int billingProfileCount = BillingProfileList.size();
		if (billingProfileCount > 0) {
			for (WebElement billingProfile : BillingProfileList) {
				String billingName = billingProfile.getAttribute("value");
				if (billingName.isBlank())
					isbillingSelected = false;
			}
		} else {
			isbillingSelected = false;
		}
		return isbillingSelected;
	}

	public void selectBillingProfile() {
		int billingProfileCount = BillingProfileList.size();
		for (int i = 0; i < billingProfileCount; i++) {
			logg.info("Billing Profile Index = " + i);
			WebElement billingProfilename = BillingProfileList.get(i);
			String billingprofile = billingProfilename.getAttribute("value");
			logg.info("Billing Value is Present and name is  = " + billingprofile);
			if (billingprofile.isEmpty()) {
				CommonUtilities.clickOnWebElement(billingProfilename);
				CommonUtilities.threadSleep(2000);
				List<WebElement> BillingList = driver
						.findElements(By.xpath("//ul[@class='MuiAutocomplete-listbox css-1eul6y3']/li/span/p"));

				int billingListCount = BillingList.size();
				logg.info("Billing List available is = " + billingListCount);
				if (billingListCount == 0)
					Assert.fail("Billing profile not available to select for billing");
				Random rand = new Random();
				int selectedIndex = rand.nextInt(BillingList.size());
				logg.info("Select Billing Index = " + selectedIndex);

				String selectBillingName = BillingList.get(selectedIndex).getText();
				for (int j = 0; j < BillingList.size(); j++) {
					String tempName = BillingList.get(j).getText();
					logg.info("BIlling Name " + tempName);
					if (tempName.equalsIgnoreCase(selectBillingName)) {
						BillingList.get(j).click();
						i = -1;
						break;
					}
				}
			}
			logg.info("Billing Profile not exist for the Service to Order");
		}
	}

	public void clickOnPortDetails() {
		clickOnPortDetails.click();
	}

	public String totalAmountOnCheckOutPage() {
		WebElement totalAmountElement = CommonUtilities.elementToBeVisible(checkoutpageTotalAmount, 10);
		return totalAmountElement.getText();
	}

	public void clickOnorderBtutton() {

		CommonUtilities.clickOnWebElement(clickOnOrderPlace);
	}

	public void portCreationSuccesfullmessagePopUp() {
		portCreatedPopUpMessage.click();
	}

	/*
	 * public void clickOnIAggreementBtn() { CommonUtilities.threadSleep(1000);
	 * CommonUtilities.moveToSpecificElement(clickOnCheckBoxForiAgreeServices); if
	 * (!clickOnCheckBoxForiAgreeServices.isSelected()) {
	 * clickOnCheckBoxForiAgreeServices.click(); //
	 * CommonUtilities.clickOnWebElement(clickOnCheckBoxForiAgreeServices); } else {
	 * logg.info("Service Agreement checkBox Is not selected"); } }
	 * 
	 * public void refreshPage() { driver.navigate().refresh(); }
	 */

	public String portNameIsExceedErrorMessage() {
		WebElement errorMessage = CommonUtilities.elementToBeVisible(portNameExceedLengthError, 5);
		return errorMessage.getText();
	}

	public boolean verifyAfterClickCreatePort() {
		try {
			WebElement afterclickCreatePortVerify = CommonUtilities.elementToBeVisible(verifyAfterClickCreatePort, 20);
			return afterclickCreatePortVerify.isDisplayed();
		} catch (NoSuchElementException ele) {
			return false;
		}
	}

	public void clickOnPolarinLogo() {
		WebElement polarineLogoHomePage = CommonUtilities.elementToBeClickable(polarinLogo, 10);
		polarineLogoHomePage.click();
	}

	/*
	 * public String getInfoMessageFromPriceSummary() { WebDriverWait wait = new
	 * WebDriverWait(driver, Duration.ofSeconds(20));
	 * wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement
	 * (blankInfoMessage, "₹ NaN"))); return blankInfoMessage.getText(); }
	 */

	public boolean selectPort_Speed() {
		boolean isSpeedSelected = false;
		int SpeedPortCount = portSpeedActiveList.size();
		System.out.print(SpeedPortCount);
		for (WebElement speedPort : portSpeedActiveList) {
			String speedportFlag = speedPort.getAttribute("aria-selected");
			if (speedportFlag.equalsIgnoreCase("True")) {
				isSpeedSelected = true;
				break;
			}
		}
		return isSpeedSelected;
	}

	public String getSelectedPort(String portSpeed) {
		String selectedPortSpeed = "";
		int SpeedPortCount = portSpeedActiveList.size();
		System.out.print(SpeedPortCount);
		try {
			WebElement portSpeedElement = driver.findElement(
					By.xpath("//div[@class='MuiTabs-flexContainer css-k008qs']/button[text()='" + portSpeed + "']"));
			String speedportFlag = portSpeedElement.getAttribute("aria-selected");
			if (speedportFlag.equalsIgnoreCase("True")) {
				selectedPortSpeed = portSpeedElement.getText();
			}
		} catch (NoSuchElementException e) {
			for (WebElement speedPort : portSpeedActiveList) {
				String speedportFlag = speedPort.getAttribute("aria-selected");
				if (speedportFlag.equalsIgnoreCase("True")) {
					selectedPortSpeed = speedPort.getText();
					break;
				}
			}
		}

		return selectedPortSpeed;
	}

	public boolean isPortConfigureStageEnabled() {
		return (configurePortLabel.isEnabled()) ? true : false;
	}

	public boolean isPortLocationEnabled() {
		return (searchLocation.isEnabled()) ? true : false;
	}

	public String getcountrydropdownvalue() {
		WebElement afterclickCreatePortVerify = CommonUtilities.elementToBeVisible(selectedCountryfromvalue, 20);
		return selectedCountryfromvalue.getText();
	}

	public boolean isNextButtonEnabled() {
		try {
			WebElement nextButtonele = CommonUtilities.elementToBeClickable(nextButton, 10);
			return (nextButtonele.isEnabled()) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean isOnReviewOrderDetailsHeaderPage() {
		try {
			WebElement revieworderHeaderele = CommonUtilities.elementToBeVisible(revieworderheader, 10);
			return (revieworderHeaderele.getText().equals("Review Order Details:"));
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * public String getSuccessNotificationMessage() { try { WebElement
	 * statusElement = CommonUtilities.elementToBeVisible( By.xpath(
	 * "//div[@id='__next']/div[@role='presentation']//span[@class='notification-success-text']"
	 * ), 10); logg.info(statusElement.getText()); return statusElement.getText(); }
	 * catch (StaleElementReferenceException se) { try {
	 * logg.info("Stale Element Reference Exception occured"); WebElement
	 * statusElement = CommonUtilities.elementToBeVisible(By.xpath(
	 * "//div[@id='__next']/div[@role='presentation']//span[@class='notification-success-text']"
	 * ), 10); logg.info(statusElement.getText()); return statusElement.getText(); }
	 * catch (Exception e) { logg.info(e); return "Exception Occured" + e; } } catch
	 * (Exception e) { logg.info(e); return "Exception Occured" + e; } }
	 */

	/*
	 * public String getErrorNotificationMessage() { try { WebElement statusElement
	 * = CommonUtilities.elementToBeVisible( By.xpath(
	 * "//div[@id='__next']/div[@role='presentation']//span[@class='notification-error-text']"
	 * ), 10); CommonUtilities.threadSleep(1000);
	 * logg.info(statusElement.getText()); return statusElement.getText(); } catch
	 * (Exception e) { logg.info(e); return "Exception Occured " + e; }
	 * 
	 * }
	 */
//	public String getSuccessNotificationMessage(String notification) {
//		try {
//			Boolean iseledisplayed = CommonUtilities.elementToBeVisible(By.xpath("//div[@id='__next']/div[@role='presentation']"), notification,10);
//			CommonUtilities.threadSleep(1000);
//			return (iseledisplayed) ? SuccessNotification.getText() : "";
//		} catch (Exception e) {
//			return "";
//		}
//	}

	public boolean isOrderBtnEnabled() {
		try {
			WebElement clickOrderBtn = CommonUtilities.elementToBeVisible(clickOnOrderPlace, 10);
			return (clickOrderBtn.isEnabled()) ? true : false;
		} catch (Exception e) {
			return false;
		}
	}

	/*****************************************************************************
	 * Methods New
	 *****************************************************************************/
	public boolean verifyCreatePortHeader() {
		try {
			WebElement createportHeaderele = CommonUtilities.elementToBeVisible(createportheader, 5);
			return (createportHeaderele.getText().equals("Create Port"));
		} catch (Exception e) {
			return false;
		}

	}

	public String verifyPortCreationStep(String stepName) {
		try {
			WebElement createportStepEle = driver
					.findElement(By.xpath("//p[text()='" + stepName + "'][contains(@class,'StepTitle')]/ancestor::li"));
			return createportStepEle.getAttribute("data-enabled");
		} catch (Exception e) {
			e.printStackTrace();
			return "Element Not Found, Exception Occured";
		}

	}

	public void clickOnBackButton() {
		CommonUtilities.clickOnWebElement(backButton);
	}

	public String getPriceSummaryPriceValueItem(String dataField) {
		WebElement pricePSElement;
		try {
			pricePSElement = driver.findElement(By.xpath("//h5[@data-for='" + dataField + "']"));
		} catch (NoSuchElementException e) {
			pricePSElement = driver.findElement(By.xpath("//p[@data-for='" + dataField + "']"));
		}
		return pricePSElement.getText();
	}

	public String getportnameErrorMessage() {
		WebElement errorMessage = CommonUtilities.elementToBeVisible(portNameErrorMessage, 5);
		return errorMessage.getText();
	}

	public void clickonAddBillingProfileButton() {
		CommonUtilities.clickOnWebElement(addBillingProfileButton);
	}
	//all the messages i need to go through
}
