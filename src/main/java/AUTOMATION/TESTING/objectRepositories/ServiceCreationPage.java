package AUTOMATION.TESTING.objectRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AUTOMATION.TESTING.testBase.TestBase;
import AUTOMATION.TESTING.utility.CommonUtilities;

public class ServiceCreationPage extends TestBase {
	public static final Logger logg = Logger.getLogger(ServiceCreationPage.class.getName());
	ServiceDashboardPage serviceDash = new ServiceDashboardPage();

	@FindBy(xpath = "//h5[text()='Start With A Use-case']")
	private WebElement newuserServicePage;

	@FindBy(xpath = "//button[text()='Create']")
	private WebElement CreateButton;

	@FindBy(xpath = "//p[text()='Show all services']")
	private WebElement selectShowAllServices;

	@FindBy(xpath = "//div/span[@class='MuiTypography-root MuiTypography-body2 MuiCardHeader-title css-1ib10wz']")
	private List<WebElement> allServiceCreationList;

	@FindBy(xpath = "(//button[text()='Get Started'])[3]")
	private WebElement getStartButton;

	@FindBy(xpath = "(//button[text()='Cancel'])[5]")
	private WebElement clickOnCancelButton;

	@FindBy(xpath = "//input[@placeholder='Search virtual router']")
	private WebElement searchVirtualRouterAEndPlaceHolder;

	@FindBy(xpath = "//input[@placeholder='Search port']")
	private WebElement getPortNameFromAEnd;

	@FindBy(xpath = "//h4[text()='All Services']")
	private WebElement allservicesheader;

	@FindBy(xpath = "//h5[text()='Start With A Use-case']")
	private WebElement newuserServiceUsecasePage;
	/*****************************************************************
	 * Add LAG Port
	 ***************************************************************/
	@FindBy(id = "mui-component-select-numberOfPorts")
	private WebElement update_LAG_Num_Of_LAG_Ports;

	@FindBy(xpath = "//ul[@class='MuiList-root MuiList-padding MuiMenu-list css-r8u8y9']//li")
	private List<WebElement> list_of_lag_port_selection_from_list;

	@FindBy(xpath = "//button[normalize-space()='Update']")
	private WebElement lag_Update_Button;

	@FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-nzhjjn'])[2]")
	private WebElement click_on_Back_Button;

	/*****************************************************************
	 * Update Virtual Router
	 ***************************************************************/
	@FindBy(xpath = "//p[normalize-space()='Current Rate Limit']//following-sibling::p")
	private WebElement current_Rate_Limit_On_VR_UpdatePage;

	@FindBy(xpath = "//p[normalize-space()='Subscription Term']//following-sibling::p")
	private WebElement subscription_Term_On_VR_Update_Page;

	@FindBy(xpath = "//p[normalize-space()='Payment Option']//following-sibling::p")
	private WebElement payment_Option_on_VR_Update_Page;

	@FindBy(name = "rateLimit")
	private WebElement new_Rate_Limit_Text_Box_On_VR_UpdatePage;

	@FindBy(xpath = "//label[normalize-space()='Virtual Router Name :']//following::input[1]")
	private WebElement virtual_Router_Name_Update_Page;

	@FindBy(xpath = "//div[@id='mui-component-select-bandwidthType']//following::p[1]")
	private WebElement new_rate_limit_instructions;

	@FindBy(xpath = "//h1[text()='Services']")
	private WebElement serviceHeader;

	// h1[text()='Services']

	public ServiceCreationPage() {
		PageFactory.initElements(driver, this);
	}

	public void selectShowAllService() {
		CommonUtilities.elementToBeClickable(selectShowAllServices, 10).click();
	}

	public void selectServices(String sel) {
		List<WebElement> allText = allServiceCreationList;
		for (WebElement text : allText) {
			if (text.getText().equals(sel)) {
				text.click();
				break;
			}
			logg.info("Select Services = " + allText);
		}
	}

	public boolean servicesLandingPageAddProductAndUseCase(int number) {
		try {
			WebElement serviceCreationOnLandingPage = driver
					.findElement(By.xpath("(//button[text()='Create'])[" + number + "]"));
			if (serviceCreationOnLandingPage.isEnabled()) {
				serviceCreationOnLandingPage.click();
				return true;
			}
		} catch (NoSuchElementException ele) {
			return false;
		}
		return false;
	}

	public boolean verifystartUsecaseCreationPage() {
		try {
			WebElement createrouterHeaderele = CommonUtilities.elementToBeVisible(newuserServiceUsecasePage, 10);
			return (createrouterHeaderele.getText().equals("Start With A Use-case"));
		} catch (Exception e) {
			return false;
		}

	}

	/*
	 * public String getRandomVirtualConnectionOnServicePage() { int vrCount = 0;
	 * ArrayList<String> allservice = new ArrayList<>(); for (WebElement element :
	 * serviceDash.getAllPortName) { WebElement elementt =
	 * CommonUtilities.elementToBeVisible(element, 15); if
	 * (serviceDash.getAllVirtualRouterStatus.get(vrCount).getText().
	 * equalsIgnoreCase("Design") ||
	 * serviceDash.getAllVirtualRouterStatus.get(vrCount).getText().equalsIgnoreCase
	 * ("Live")) { allservice.add(elementt.getText()); } vrCount++; }
	 * logg.info("All Virtual Routers = " + allservice); Random rand = new Random();
	 * int serviceIndex = rand.nextInt(allservice.size()); return
	 * allservice.get(serviceIndex); }
	 * 
	 * public String getRandomPortNameFromServicePage() { int vrCount = 0;
	 * ArrayList<String> allservice = new ArrayList<String>(); for (WebElement
	 * element : serviceDash.getAllPortName) { if
	 * (serviceDash.getAllPortStatus.get(vrCount).getText().equalsIgnoreCase(
	 * "Design") || serviceDash.getAllPortStatus.get(vrCount).getText().
	 * equalsIgnoreCase("Ready To Patch")) { allservice.add(element.getText()); }
	 * vrCount++; } logg.info("All Ports Name  = " + allservice); Random rand = new
	 * Random(); int serviceIndex = rand.nextInt(allservice.size()); return
	 * allservice.get(serviceIndex); }
	 * 
	 * public String virtualRouterNameAtAEnd() {
	 * CommonUtilities.elementToBeVisible(searchVirtualRouterAEndPlaceHolder, 10);
	 * return searchVirtualRouterAEndPlaceHolder.getAttribute("value"); }
	 * 
	 * public String portNameAtAEnd() {
	 * CommonUtilities.elementToBeVisible(getPortNameFromAEnd, 10); return
	 * getPortNameFromAEnd.getAttribute("value"); }
	 * 
	 * public boolean numberOfLAGPortsUpdateLAG() { WebElement lagPort =
	 * CommonUtilities.elementToBeClickable(update_LAG_Num_Of_LAG_Ports, 10); try {
	 * lagPort.click(); return true; } catch (ElementClickInterceptedException |
	 * StaleElementReferenceException | NoSuchElementException ele) { try {
	 * CommonUtilities.JSClick(lagPort); return true; } catch (Exception el) {
	 * return false; } } catch (Exception e) { // For any other exception, return
	 * false return false; } }
	 * 
	 * public void selectLagPort(String selectaddport) { for (WebElement element :
	 * list_of_lag_port_selection_from_list) { if
	 * (element.getText().contains(selectaddport)) {
	 * logg.info("Clicking on port number = " + element.getText()); element.click();
	 * } } }
	 * 
	 * public void lagUpdateButtonClick() { WebElement updateElementClick =
	 * CommonUtilities.elementToBeClickable(lag_Update_Button, 10);
	 * updateElementClick.click(); }
	 * 
	 * public void clickOnBackButton() { click_on_Back_Button.click(); }
	 * 
	 *//*****************************************************************
		 * Update Virtual Router Methods
		 ***************************************************************//*
																			 * public int
																			 * getTheIntegerCurrentRateLimitFromUpdatePage
																			 * () { String ratelimit =
																			 * current_Rate_Limit_On_VR_UpdatePage.
																			 * getText().replaceAll("[^0-9]", ""); int
																			 * speed = Integer.parseInt(ratelimit);
																			 * return speed; }
																			 * 
																			 * public String
																			 * getStringTheCurrentRateLimitFromUpdatePage
																			 * () { return
																			 * current_Rate_Limit_On_VR_UpdatePage.
																			 * getText(); }
																			 * 
																			 * public String
																			 * getTheSubscriptionTermfromUpdatePage() {
																			 * return
																			 * subscription_Term_On_VR_Update_Page.
																			 * getText(); }
																			 * 
																			 * public String
																			 * getThePaymentOptionFromUpdatePage() {
																			 * return
																			 * payment_Option_on_VR_Update_Page.getText(
																			 * ); }
																			 * 
																			 * public void
																			 * enterTheNewRateLimitOnUpdatePage(String
																			 * ratelimit) { CommonUtilities.sendkeys(
																			 * new_Rate_Limit_Text_Box_On_VR_UpdatePage,
																			 * ratelimit); }
																			 * 
																			 * public String
																			 * newRateLimitInstructionsText() { return
																			 * new_rate_limit_instructions.getText(); }
																			 * 
																			 * public boolean isOnSevicePage() { try {
																			 * WebElement serviceHeaderele =
																			 * CommonUtilities.elementToBeVisible(
																			 * serviceHeader, 5);
																			 * serviceHeaderele.getText().equals(
																			 * "Services"); return true; } catch
																			 * (Exception e) { return false; } }
																			 * 
																			 * public boolean
																			 * isOnAllServicesHeaderPage() { try {
																			 * WebElement createrouterHeaderele =
																			 * CommonUtilities.elementToBeVisible(
																			 * allservicesheader, 5); return
																			 * (createrouterHeaderele.getText().
																			 * equals("All Services")); } catch
																			 * (Exception e) { return false; } }
																			 * 
																			 * public void clickonGetstartedButton() {
																			 * CommonUtilities.clickOnWebElement(
																			 * getStartButton); } public void
																			 * clickonService(String serviceName) {
																			 * WebElement createButtonElement; try {
																			 * Boolean newuserServicePageFlag =
																			 * CommonUtilities.isElementDisplayed(
																			 * newuserServicePage, 10); if
																			 * (newuserServicePageFlag)
																			 * createButtonElement = driver
																			 * .findElement(By.xpath(
																			 * "//div[@data-label='" + serviceName +
																			 * "']//button[text()='Create']")); else {
																			 * if
																			 * (CreateButton.getAttribute("data-open").
																			 * equals("false")) {
																			 * CommonUtilities.clickOnWebElement(
																			 * CreateButton);
																			 * CommonUtilities.threadSleep(1000); }
																			 * String serviceDetailName =
																			 * (serviceName.equals("Port") ||
																			 * serviceName.equals("Virtual Router") ||
																			 * serviceName.equals("Virtual Connection"))
																			 * ? "Create a " + serviceName :
																			 * "Show all services"; createButtonElement
																			 * = driver
																			 * .findElement(By.xpath("//p[text()='" +
																			 * serviceDetailName + "']/ancestor::li"));
																			 * } CommonUtilities.clickOnWebElement(
																			 * createButtonElement); } catch (Exception
																			 * e) { e.printStackTrace(); } }
																			 * 
																			 * public boolean
																			 * verifyservicebutondisplayed(String
																			 * serviceName) { boolean serviceDisplayed =
																			 * false; try { WebElement
																			 * createButtonElement; Boolean
																			 * newuserServicePageFlag =
																			 * CommonUtilities.isElementDisplayed(
																			 * newuserServicePage, 10); if
																			 * (newuserServicePageFlag)
																			 * createButtonElement = driver
																			 * .findElement(By.xpath(
																			 * "//div[@data-label='" + serviceName +
																			 * "']//button[text()='Create']")); else {
																			 * CommonUtilities.clickOnWebElement(
																			 * CreateButton);
																			 * CommonUtilities.threadSleep(1000); String
																			 * serviceDetailName =
																			 * (serviceName.equals("Port") ||
																			 * serviceName.equals("Virtual Router") ||
																			 * serviceName.equals("Virtual Connection"))
																			 * ? "Create a " + serviceName :
																			 * "Show all services"; createButtonElement
																			 * = driver
																			 * .findElement(By.xpath("//p[text()='" +
																			 * serviceDetailName + "']/ancestor::li"));
																			 * } Boolean createButtonDisplayed =
																			 * CommonUtilities.isElementDisplayed(
																			 * createButtonElement, 10); if
																			 * (createButtonDisplayed) {
																			 * serviceDisplayed = true; } } catch
																			 * (Exception e) { e.printStackTrace(); }
																			 * return serviceDisplayed;
																			 * 
																			 * }
																			 * 
																			 * public boolean
																			 * verifyLearnMoreDisplayed(String
																			 * servicename) { WebElement
																			 * e=driver.findElement(By.xpath(
																			 * "//button[@data-label='"+servicename+
																			 * "']/following::h5[1]"));
																			 * CommonUtilities.elementToBeVisible(e,
																			 * 20); return (e.isDisplayed())?true:false;
																			 * }
																			 * 
																			 * public void clickOnLearnMore(String
																			 * servicename) { WebElement
																			 * e=driver.findElement(By.xpath(
																			 * "//button[@data-label='"+servicename+
																			 * "']/following::h5[1]")); String
																			 * servicenames="Cloud to Cloud DC to Cloud Connection Data Centre Interconnect"
																			 * ; if(!servicenames.contains(servicename))
																			 * {
																			 * CommonUtilities.moveToSpecificElement(e);
																			 * CommonUtilities.threadSleep(500); }
																			 * 
																			 * CommonUtilities.elementToBeClickable(e,
																			 * 10).click(); }
																			 */

}
