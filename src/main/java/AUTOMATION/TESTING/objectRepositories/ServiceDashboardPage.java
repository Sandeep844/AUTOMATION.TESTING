package AUTOMATION.TESTING.objectRepositories;



import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;

import AUTOMATION.TESTING.testBase.TestBase;
import AUTOMATION.TESTING.utility.CommonUtilities;

import java.util.Random;


public class ServiceDashboardPage extends TestBase {
	public static final Logger logg = Logger.getLogger(ServiceDashboardPage.class.getName());
	private static String portName;

	@FindBy(xpath = "//div[@data-type='loader-animation'][text()='Fetching Data...']//img")
	private WebElement dashboardReloadIcon;

	@FindBy(xpath = "(//div[@class='MuiListItemIcon-root css-qwko1u'])[1]")
	private WebElement services;

	@FindBy(xpath = "//table[@class='MuiTable-root css-1is2ke3']/tbody/tr/td[2]")
	public List<WebElement> getAllPortName;

	@FindBy(xpath = "//table[@class='MuiTable-root css-1is2ke3']/tbody/tr/td[3]")
	private List<WebElement> getAllPortType;

	@FindBy(xpath = "//table[@class='MuiTable-root css-1is2ke3']/tbody/tr/td[8]")
	public List<WebElement> getAllPortStatus;

	@FindBy(xpath = "//table[@class='MuiTable-root css-1is2ke3']/tbody/tr/td[9]")
	public List<WebElement> getAllVirtualRouterStatus;

	@FindBy(xpath = "//table[@class='MuiTable-root css-1is2ke3']/tbody/tr/td[7]")
	public List<WebElement> getAllVirtualConnectionStatus;

	@FindBy(xpath = "//table[@class='MuiTable-root css-1is2ke3']//tbody/tr/td[6]")
	public List<WebElement> allPortandVirtualRouterCreatedDate;

	@FindBy(xpath = "//table[@class='MuiTable-root css-1is2ke3']/tbody/tr/td[5]")
	public List<WebElement> allVirtualConnectionCreatedDate;

	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-nzhjjn']")
	private List<WebElement> threeDotMenuforVR;

	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-nzhjjn']")
	private List<WebElement> clickthreeDotButton;

	@FindBy(xpath = "//ul[@class='MuiList-root MuiList-padding MuiMenu-list css-fcktsg']//child::li")
	private List<WebElement> afterClickThreeDotMenu;

	@FindBy(xpath = "//div[@class='MuiBox-root css-1q37r7t']//following-sibling::input[@id]")
	private WebElement searchBoxtext;

	@FindBy(xpath = "(//div[@class='MuiSelect-select MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input css-13fl7lf'])[2]")
	private WebElement clickOnLocationDropdown;

	@FindBy(xpath = "//button[contains(@class,'MuiButtonBase-root MuiTab-root MuiTab-textColorPrimary')]")
	private List<WebElement> clickServicesTab;

	@FindBy(xpath = "//li[@class='MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters css-1hdmjwj']")
	private List<WebElement> selectLocation;

	@FindBy(xpath = "//div[@class='css-1ou91yh']//following-sibling::p")
	private WebElement getNoOfLAG;

	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-lgzbvc']")
	private List<WebElement> nameOfLAGPort;

	@FindBy(xpath = "//div[@class='MuiCardContent-root css-om8fsr']//child::button")
	private WebElement clickOnThreeDotPortDeatils;

	@FindBy(xpath = "//ul[@class='MuiList-root MuiList-padding MuiMenu-list css-fcktsg']//child::li")
	private List<WebElement> clickOnLagMenu;

	@FindBy(xpath = "//div[@class='css-1pa0f36']//preceding-sibling::button")
	private WebElement clickOnDiscartButton;

	@FindBy(xpath = "//div[@class='css-12199pk']//preceding-sibling::button")
	private WebElement discardButton;

	@FindBy(xpath = "//div[@class='css-1pa0f36']//following-sibling::button")
	private WebElement clickOnDeleteButton;

	@FindBy(xpath = "//input[@class='MuiInputBase-input css-mnn31']")
	private WebElement enterTerminateIntoTextBox;

	@FindBy(xpath = "//div[@class='css-xtuzo5']//following-sibling::button")
	private WebElement deleteButton;
	@FindBy(xpath = "//p[text()='Quick Actions']/..//button[text()='Delete']")
	private WebElement deleteQuickActionButton;
	@FindBy(xpath = "//p[text()='Quick Actions']/..//button[text()='Download LOA']")
	private WebElement downloadLOAButton;
	@FindBy(xpath = "//p[text()='Quick Actions']/..//button[text()='Update']")
	private WebElement updateButton;
	@FindBy(xpath = "//h4[text()='Generate LOA']")
	private WebElement downloadloaheader;

	@FindBy(xpath = "//button[text()='Generate']")
	private WebElement generateButton;
	@FindBy(id = "tags-standard")
	private WebElement emailLOATextbox;
	@FindBy(xpath = "//div[@role='dialog']//button[text()='Delete']")
	private WebElement deleteDialogButton;

	@FindBy(xpath = "//div[@class='css-yn103w']//child::button")
	private WebElement clickOnCancelButton;

	@FindBy(xpath = "//button[normalize-space()='Terminate Subscription']")
	private WebElement terminateSubscriptionTermButton;

	@FindBy(xpath = "//div[@class='css-cxpo3t']//child::h3")
	private WebElement getTextFromDeletePopUp;

	@FindBy(xpath = "//div[text()='Ready To Patch']")
	private List<WebElement> checkStatusForLagPorts;

	@FindBy(xpath = "(//div[@class='MuiBox-root css-xzg0k8']//p)[2]")
	private WebElement getVirtualRouterName;

	@FindBy(xpath = "//li[text()='Request Cross-Connect']")
	private WebElement requestCrossConnectMenu;

	@FindBy(xpath = "(//input[@name='demarc'])[2]")
	private WebElement dmarcNumber;

	@FindBy(xpath = "//button[normalize-space()='Request Cross Connect']")
	private WebElement requestCrossConnectButton;

	@FindBy(xpath = "//span[text()='Request for Quote']")
	private WebElement crossConnectStatus;

	@FindBy(xpath = "//span[text()='Your data center rack location details']//following-sibling::span")
	private WebElement dataCenterRackLocationDetails;

	@FindBy(xpath = "//div[@class='css-1nwth26']//following-sibling::h4")
	private WebElement portNameFromCrossConnectDetailsPage;

	@FindBy(xpath = "//button[text()='Clear']")
	private WebElement clearbutton;

	@FindBy(xpath = "//button[@aria-label='Subscription filter']")
	private WebElement subscriptionfilterbutton;
	@FindBy(xpath = "//button[@aria-label='Status filter']")
	private WebElement statusfilterbutton;
	@FindBy(xpath = "//li[@role='menuitem']")
	private List<WebElement> filtermenulist;
	@FindBy(xpath = "//button[@aria-label='Update']")
	private List<WebElement> updatebuttonlist;
	@FindBy(xpath = "//button[text()='Update']")
	private WebElement updatebutton;
	@FindBy(xpath = "//button[text()='Order']")
	private WebElement orderbutton;

	@FindBy(xpath = "//h4[text()='Update']")
	private WebElement updateheader;
	@FindBy(xpath = "//h4[text()='Add-on Rate Limit']")
	private WebElement addonratelimitheader;

	@FindBy(xpath = "//p[text()='Current Rate Limit']/../p[2]")
	private WebElement currentratelimit;

	@FindBy(name = "rateLimit")
	private WebElement newratelimitTextbox;

	@FindBy(name = "speed")
	private WebElement newspeedratelimitTextbox;

	@FindBy(xpath = "//h5[text()='Generate LOA']/../span")
	private WebElement generateLOAcheckbox;

	@FindBy(name = "name")
	private WebElement servicename;
	@FindBy(xpath = "//table[@class='MuiTable-root css-tbo57s']/thead/tr/th")
	private List<WebElement> tableheaderlist;
	@FindBy(xpath = "//table[@class='MuiTable-root css-tbo57s']")
	private WebElement portdashboardtable;

	@FindBy(xpath = "//input[@placeholder='Search by Port Name']")
	private WebElement SeartchPortTextbox;

	@FindBy(xpath = "//ul[@class='MuiList-root MuiList-padding css-1x07uve']/li/button")
	private List<WebElement> navigationbuttonlist;

	@FindBy(xpath = "//div[@id='mui-component-select-size']")
	private WebElement pageselectiondropdown;

	@FindBy(xpath = "//ul[@role='listbox']/li")
	private List<WebElement> pagespertableList;

	@FindBy(xpath = "//button[text()='Request Cross Connect']")
	private WebElement requestcrossconnectButton;

	@FindBy(xpath = "//label[text()='Your data center rack location details']/..//input[@name='demarc']")
	private WebElement demarclocTextbox;

	@FindBy(xpath = "//h4[text()='Request Cross Connect']")
	private WebElement requestcrossconnectHeader;

	@FindBy(xpath = "//p[text()='Select Port (You can select multiple ports):']/..//input")
	private List<WebElement> portcheckboxlist;

	@FindBy(xpath = "//div[@class='css-yn103w']/button[text()='Close']")
	private WebElement closeButton;

	@FindBy(xpath = "//div[@class='css-1f0ncqk']/button")
	private List<WebElement> viewicondetailsButton;

	@FindBy(xpath = "//h3[text()='Are you sure you want to delete this port?']")
	private WebElement deleteportconfirmationHeader;

	@FindBy(xpath = "//h4[text()='Are you sure you want to terminate this subscription?']")
	private WebElement terminatepopupheader;

	@FindBy(xpath = "//input[@placeholder='Type TERMINATE here....']")
	private WebElement TerminateInput;

	@FindBy(xpath = "//button[text()='Terminate Subscription']")
	private WebElement terminatesubscriptionButton;

	@FindBy(xpath = "//button[text()='Performance Metrics']")
	private WebElement performancemetricsButton;

	@FindBy(xpath = "//div[@class='MuiCardHeader-content css-11qjisw']")
	private List<WebElement> performancemetricstableList;

	@FindBy(xpath = "//h5[text()='Add more ports']")
	private WebElement addmoreportsButton;

	@FindBy(xpath = "//h4[text()='Update Lag']")
	private WebElement updatelagheader;

	@FindBy(id = "mui-component-select-numberOfPorts")
	private WebElement nooflagportstextbox;

	@FindBy(xpath = "//ul[@role='listbox']/li")
	private List<WebElement> updateportcountList;

	@FindBy(xpath = "//label[text()='Subscription Term:']/..//button")
	private List<WebElement> subscriptiontermlist;

	@FindBy(xpath = "//label[text()='Select Payment Type:']/..//button")
	private List<WebElement> paymenttypelist;
	@FindBy(xpath = "//div[@id='__next']/div[@role='presentation']")
	private WebElement SuccessNotification;

	@FindBy(xpath = "//h2[text()='Service deleted!']")
	private WebElement deleteNotification;

	@FindBy(xpath = "//input[@placeholder='Search by Virtual Connection Name or Service Id']")
	private WebElement vcSearchTextbox;

	@FindBy(xpath = "//input[@placeholder='Search by Virtual Router Name or Service Id']")
	private WebElement vrSearchTextbox;
	@FindBy(xpath = "//input[@placeholder='Search by Port Name or Service ID']")
	private WebElement portSearchTextbox;

	@FindBy(xpath = "//button[text()='Enable Add on Rate Limit']")
	private WebElement enableAddonratelimitButton;

	
	@FindBy(xpath = "//ul[@id='tags-standard-listbox']/li")
	public List<WebElement> emailLOAList;
	
	public ServiceDashboardPage() {
		PageFactory.initElements(driver, this);
	}

	public void serachDataInToSearchBar(String serchText) {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CommonUtilities.sendkeys(searchBoxtext, serchText);
	}

	public void serachDataInToSearchBar() {
		ArrayList<String> allservice = new ArrayList<>();
		for (WebElement element : getAllPortName) {
			WebElement elementt = CommonUtilities.elementToBeVisible(element, 15);
			allservice.add(elementt.getText());
		}
		logg.info("All Services List = " + allservice);
		logg.info("Total Services number = " + getAllPortName.size());
		Random rand = new Random();
		int serviceIndex = rand.nextInt(getAllPortName.size());
		logg.info("Select Service Index = " + serviceIndex);
		String selectedText = allservice.get(serviceIndex);
		logg.info("Selected service name = " + selectedText);
		CommonUtilities.sendkeys(searchBoxtext, selectedText);
	}

	public boolean verifyTheSearchServiceIsableToFind() {
		for (int i = 0; i <= getAllPortName.size() - 1; i++) {
			String allsub = getAllPortName.get(i).getText();
			logg.info("Service Name = " + allsub);
			if (i == 0) {
				logg.info("Search string found in the first row " + i);
				return true;
				// break; // If found, you can break out of the loop or perform further actions
			}
		}
		return false;
	}

	public void getTheAllActionsPerformAfterClickThreeDot(String selectAction) {
		for (WebElement allActions : afterClickThreeDotMenu) {
			String actions = allActions.getText();
			logg.info("Action Performed = " + actions);
			if (actions.equals(selectAction)) {
				WebElement allfunction = CommonUtilities.elementToBeClickable(allActions, 10);
				CommonUtilities.JSClick(allfunction);
				break;
			} else {
				logg.info("Please Give the Proper Name");
			}
		}
	}

	public void selectTheServices(String selectService) {
//		CommonUtilities.moveToSpecificElement(porttabbutton);
//		CommonUtilities.threadSleep(1000);
		WebDriverWait waitForServiceMenu = new WebDriverWait(driver, Duration.ofSeconds(10));
		waitForServiceMenu.until(ExpectedConditions.visibilityOfAllElements(clickServicesTab));
		for (WebElement tab : clickServicesTab) {
			logg.info("Clicking on " + tab.getText() + " Tab");
			if (tab.getText().equals(selectService)) {
				logg.info("Clicking on " + tab.getText() + " Tab");
				CommonUtilities.moveToElement(tab);
				CommonUtilities.JSClick(tab);
				CommonUtilities.threadSleep(2000);
				break;
			} else {
				logg.info("Services already Selected");
			}
		}
	}

	public void searchByperticularLocation(String sorting) {
		clickOnLocationDropdown.click();
		for (WebElement allSorting : selectLocation) {
			String sort = allSorting.getText();
			if (sort.equals(sorting)) {
				allSorting.click();
				break;
			}
			logg.info(sort);
		}
	}

	public void checkLAGCreatedDetails(String field) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		String formattedCurrentDate = dateFormat.format(currentDate);
		// System.out.println("Current Date = "+formattedCurrentDate);
		int lagindex = 0;
		for (int i = 0; i < getAllPortType.size(); i++) {
			logg.info(getAllPortType.get(i).getText());
			if ("LAG".equalsIgnoreCase(getAllPortType.get(i).getText())) {
				lagindex = i;
				// System.out.println("Created Date = "+allPortCreatedDate.get(i).getText());
				// if("22/09/2023".equals(allPortCreatedDate.get(i).getText())) {
				if (formattedCurrentDate.equals(allPortandVirtualRouterCreatedDate.get(i).getText())) {
					clickthreeDotButton.get(i).click();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					getTheAllActionsPerformAfterClickThreeDot(field);
					logg.info(lagindex);
					break;
				}
			}
		}
	}

	public String getNoofLags() {
		CommonUtilities.elementToBeVisible(getNoOfLAG, 20);
		return getNoOfLAG.getText();
	}

	public void clickOnServiceMenu() {
		WebElement servicemenu = CommonUtilities.elementToBeClickable(services, 20);
		servicemenu.click();
	}

	public ArrayList<String> selectTheNameandRelatedOptions() {
		ArrayList<String> lagPortLists = new ArrayList<>();
		for (WebElement allSorting : nameOfLAGPort) {
			lagPortLists.add(allSorting.getText());
		}
		return lagPortLists;
	}

	public void longestPortNameClickOnThreeDot() {
		String longestName = "";
		int maxLength = 0;
		for (WebElement nameElement : nameOfLAGPort) {
			String name = nameElement.getText();
			if (name.length() > maxLength) {
				maxLength = name.length();
				longestName = name;
			}
		}
		for (WebElement element : nameOfLAGPort) {
			if (element.getText().equals(longestName)) {
				CommonUtilities.JSClick(clickOnThreeDotPortDeatils);
				break;
			}
		}
	}

	public void clickOnGivenMenu(String listelement) {
		WebDriverWait waitForForMenu = new WebDriverWait(driver, Duration.ofSeconds(5));
		waitForForMenu.until(ExpectedConditions.visibilityOfAllElements(clickOnLagMenu));
		for (WebElement element : clickOnLagMenu) {
			if (element.getText().equals(listelement)) {
				CommonUtilities.JSClick(element);
			}
		}
	}

	public void clickOnDeleteBtn() {
		WebElement clickonDelButton = CommonUtilities.elementToBeClickable(clickOnDeleteButton, 10);
		CommonUtilities.JSClick(clickonDelButton);
	}

	public void clickOnDiscartBtn() {
		WebElement discartbutton = CommonUtilities.elementToBeClickable(clickOnDiscartButton, 10);
		CommonUtilities.JSClick(discartbutton);
	}

	public void clickOnCloseButton() {
		CommonUtilities.JSClick(clickOnCancelButton);
	}

	public String getTextFromDeletePopUp() {
		WebElement element = CommonUtilities.elementToBeVisible(getTextFromDeletePopUp, 50);
		if (element.isDisplayed()) {
			return element.getText();
		} else {
			return "Delete Pop Up is Not Launch";
		}
	}

	public void clickOnThreeDot(String vrname) {
		for (int i = 0; i < getAllPortName.size() - 1; i++) {
			String port = getAllPortName.get(i).getText();
			if (port.equals(vrname)) {
				threeDotMenuforVR.get(i).click();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				getTheAllActionsPerformAfterClickThreeDot("Add Connection");
				break;
			}
		}
	}

	public String getVirtualRouterNamefromString() {
		String inputString = getVirtualRouterName.getText();
		// Find the index of hyphen "-" and comma ","
		int hyphenIndex = inputString.indexOf(":");
		int commaIndex = inputString.indexOf(",");
		// Extract the desired substring
		if (hyphenIndex != -1 && commaIndex != -1) {
			return inputString.substring(hyphenIndex + 2, commaIndex).trim();
		}
		return "Not Able to Find Such String";
	}

	public boolean verifyIfThePortIsAvailableOnTheServicePage(String field) {
		if (!getAllPortName.isEmpty()) {
			return true;
		}
		return false;
	}

	public void clickOnCrossConnectOrder() {
		for (int i = 0; i < Math.min(5, getAllPortType.size()); i++) {
			if ("Port".equalsIgnoreCase(getAllPortType.get(i).getText())
					&& "Ready To Patch".equalsIgnoreCase(getAllPortStatus.get(i).getText())) {
				CommonUtilities.scrollByVisibilityOfElements(300);
				setPortName(getAllPortName.get(i).getText());
				logg.info("Port Name = " + getPortName());
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.elementToBeClickable(clickthreeDotButton.get(i)));
				CommonUtilities.threadSleep(1500);
				try {
					clickthreeDotButton.get(i).click();
				} catch (ElementClickInterceptedException e) {
					logg.info("ElementClickInterceptedException: " + e.getMessage());
				}
				try {
					CommonUtilities.threadSleep(1500);
					requestCrossConnectMenu.click();
					break;
				} catch (NoSuchElementException ele) {
					// If Cross-Connect option is not available, continue to the next port
					driver.navigate().refresh();
					logg.info("Cross-Connect option not available. Trying the next port.");
				}
			}
		}
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String port) {
		portName = port;
	}

	public void enterTheDataCenterRackLocationDetails() {
		CommonUtilities.sendkeys(dmarcNumber, "1" + CommonUtilities.generateRandomNumericValue(3));
	}

	public void clickOnRequestCrossConnectButton() {
		WebElement requestCrossConnectBtn = CommonUtilities.elementToBeClickable(requestCrossConnectButton, 10);
		CommonUtilities.JSClick(requestCrossConnectBtn);
	}

	public String getTheCrossConnectStatus() {
		WebElement crossConnectStatusOnViewPage = CommonUtilities.elementToBeVisible(crossConnectStatus, 20);
		return crossConnectStatusOnViewPage.getText();
	}

	public String getTheDataRackNumberOnViewDetailsPage() {
		WebElement getDetailsOFDmarckRack = CommonUtilities.elementToBeVisible(dataCenterRackLocationDetails, 10);
		return getDetailsOFDmarckRack.getText();
	}

	public void searchCrossConectPortAndClick() {
		for (int i = 0; i < getAllPortName.size() - 1; i++) {
			String port = getAllPortName.get(i).getText();
			if (port.equals(getPortName())) {
				logg.info("Port Name  = " + getPortName());
				CommonUtilities.threadSleep(2000);
				threeDotMenuforVR.get(i).click();
				CommonUtilities.threadSleep(2000);
				getTheAllActionsPerformAfterClickThreeDot("Cross-Connect Details");
				break;
			}
		}
	}

	public String getPortNameFromCrossConnectDetailsPage() {
		WebElement getNameCrossConnectDetails = CommonUtilities.elementToBeVisible(portNameFromCrossConnectDetailsPage,
				10);
		return getNameCrossConnectDetails.getText();
	}

	public void discardButtonClick() {
		WebElement discartbutton = CommonUtilities.elementToBeClickable(discardButton, 10);
		CommonUtilities.JSClick(discartbutton);
		logg.info("Clicking on Discard Button");
	}

	public void clickOnDeleteButton() {
		WebElement deleteButtonele = CommonUtilities.elementToBeClickable(terminateSubscriptionTermButton, 10);
		CommonUtilities.JSClick(deleteButtonele);
	}

	public void enterTERMINATE_TextIntoTextBox() {
		CommonUtilities.sendkeys(enterTerminateIntoTextBox, "TERMINATE");
	}

	public void clickOnThreeDotAdPerformAction(String serviceName, String actionPerform) {
		int serviceindex = 0;
		for (int i = 0; i < getAllPortName.size(); i++) {
			logg.info("Service Name = " + getAllPortName.get(i).getText());
			if (getAllPortName.get(i).getText().equalsIgnoreCase(serviceName)) {
				serviceindex = i;
				clickthreeDotButton.get(i).click();
				CommonUtilities.threadSleep(3000);
				getTheAllActionsPerformAfterClickThreeDot(actionPerform);
				logg.info("Services Index Number = " + serviceindex);
				break;
			}
		}
	}

	public void checkStatusOfConnections(String serviceName, int rowNum) {
		for (int i = 0; i < getAllPortName.size(); i++) {
			String port = getAllPortName.get(i).getText();
			logg.info(port);
			if (port.equals(serviceName)) {
				String status = driver
						.findElements(
								By.xpath("//table[@class='MuiTable-root css-1is2ke3']/tbody/tr/td[" + rowNum + "]"))
						.get(i).getText();
				logg.info(port + " **************=************* " + status);
				break;
			}
		}
	}

	public void clickOnClearButton() {
		CommonUtilities.moveToElement(clearbutton);
		if (clearbutton.isEnabled()) {
			CommonUtilities.clickOnWebElement(clearbutton);
			CommonUtilities.threadSleep(2000);
		}
	}

	public void clickOnStatusFilterButton() {
		CommonUtilities.clickOnWebElement(statusfilterbutton);
		CommonUtilities.threadSleep(1000);
	}

	public void clickOnTermFilterButton() {
		WebElement termfilterele = CommonUtilities.elementToBeClickable(subscriptionfilterbutton, 10);
		termfilterele.click();
		CommonUtilities.threadSleep(1000);
	}

	public boolean filterbyMenuStatus(String status) {
		boolean statusFound = false;
		int menucount = filtermenulist.size();
		if (menucount > 0) {
			for (WebElement routerstatusele : filtermenulist) {
				String routerstatus = routerstatusele.getText();
				System.out.println(routerstatus);
				if (routerstatus.equalsIgnoreCase(status)) {
					routerstatusele.click();
					CommonUtilities.threadSleep(2000);
					statusFound = true;
					break;
				}
			}
		}
		return statusFound;
	}

	public void filterbyRouterTerm(String term) {
		clickOnTermFilterButton();
		CommonUtilities.threadSleep(1000);
		int menucount = filtermenulist.size();
		if (menucount > 0) {
			for (WebElement routerstatusele : filtermenulist) {
				String routerstatus = routerstatusele.getText();
				System.out.println(routerstatus);
				if (routerstatus.equalsIgnoreCase(term)) {
					routerstatusele.click();
					CommonUtilities.threadSleep(2000);
					break;
				}
			}
		}
	}

	public void clickonUpdateButton() {
		CommonUtilities.threadSleep(1000);
		int updatecount = updatebuttonlist.size();
		if (updatecount > 0) {
			Random rand = new Random();
			int randnumber = rand.nextInt(updatecount);
			WebElement updateBtnele = CommonUtilities.elementToBeClickable(updatebuttonlist.get(randnumber), 10);
			updateBtnele.click();
			CommonUtilities.threadSleep(1000);
		} else {
			throw new SkipException("Update Records are not available for the provided data.");
		}
	}

	public boolean verifyUpdateHeader() {
		try {
			WebElement updatewebElement = CommonUtilities.elementToBeVisible(updateheader, 5);
			return (updatewebElement.getText().equals("Update"));
		} catch (Exception e) {
			return false;
		}
	}

	public boolean verifyAddonRatelimitHeader() {
		try {
			WebElement updatewebElement = CommonUtilities.elementToBeVisible(addonratelimitheader, 5);
			return (updatewebElement.getText().equals("Add-on Rate Limit"));
		} catch (Exception e) {
			return false;
		}
	}

	public String getCurrentRateLimit() {
		WebElement ratelimitElement = CommonUtilities.elementToBeVisible(currentratelimit, 5);
		return (ratelimitElement.getText());
	}

	public void enterTheNewRateLimit(String rateSpeed) {
		CommonUtilities.sendkeys(newratelimitTextbox, rateSpeed);
		CommonUtilities.threadSleep(1000);
	}

	/*
	 * public void enterTheNewSpeedRateLimit(String rateSpeed) {
	 * CommonUtilities.enterTextboxValue(newspeedratelimitTextbox, rateSpeed);
	 * CommonUtilities.threadSleep(1000); }
	 */
	public boolean isUpdateBtnEnabled() {
		try {
			WebElement updateelement = CommonUtilities.elementToBeClickable(updatebutton, 5);
			return (updateelement.isEnabled()) ? true : false;
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnUpdateButton() {
		CommonUtilities.clickOnWebElement(updatebutton);
		CommonUtilities.threadSleep(2000);
	}

	public String getservicename() {
		WebElement servicenameElement = CommonUtilities.elementToBeVisible(servicename, 5);
		return (servicenameElement.getAttribute("value"));
	}

	/*
	 * public List<String> getTableHeaderColumnNumber() {
	 * CommonUtilities.moveToSpecificElement(portdashboardtable); int
	 * tablecolumncount = tableheaderlist.size(); int incrementindex = 0;
	 * List<String> tablecolumnheaders = new ArrayList<>(); if (tablecolumncount >
	 * 0) { for (WebElement columnheader : tableheaderlist) {
	 * tablecolumnheaders.add(incrementindex, columnheader.getText());
	 * logg.info("Clumn Name: " + columnheader.getText() + " and index is " +
	 * incrementindex); incrementindex = incrementindex + 1; } } else { throw new
	 * SkipException("Port DashTable is not found"); } return tablecolumnheaders; }
	 */
	public void searchByPortName(String portname) {
		WebElement searchportele = CommonUtilities.elementToBeClickable(SeartchPortTextbox, 10);
		searchportele.sendKeys(portname);
		CommonUtilities.threadSleep(2000);
	}

	/*
	 * public void performCrossConnectForLAGPort(String porttype) { Boolean
	 * isDataavailable = false; List<String> tableHeaderslist =
	 * getTableHeaderColumnNumber(); int TypeColumn =
	 * tableHeaderslist.indexOf("Type"); int nameColumn =
	 * tableHeaderslist.indexOf("Name");
	 * 
	 * List<WebElement> tablerows =
	 * portdashboardtable.findElements(By.xpath("//tbody/tr")); if (tablerows.size()
	 * > 0) { WebElement firstRow = tablerows.get(0);
	 * CommonUtilities.scrollToVisibilityOfLocation(firstRow.getLocation().getX(),
	 * firstRow.getLocation().getY() - 65); String vcName =
	 * firstRow.findElement(By.xpath("td[" + (nameColumn + 1) + "]")).getText();
	 * logg.info("port Status is " + vcName); if
	 * (vcName.contains("No Port Created Yet!")) { logg.
	 * info("Data Not available for the provided condition , PLease provide/create the valid data."
	 * ); throw new
	 * SkipException("Data Not available for the provided condition , PLease provide/create the valid data."
	 * ); } pageselectiondropdown.click(); int pagetypecount =
	 * pagespertableList.size(); pagespertableList.get(pagetypecount - 1).click();
	 * CommonUtilities.threadSleep(2000); tablerows =
	 * portdashboardtable.findElements(By.xpath("//tbody/tr")); int tablePages =
	 * Integer.parseInt(navigationbuttonlist.get((navigationbuttonlist.size() -
	 * 2)).getText()); WebElement nextPageNavButton =
	 * navigationbuttonlist.get((navigationbuttonlist.size() - 1));
	 * 
	 * logg.info("No of Pages in Table data is  " + tablePages); for (int i = 0; i <
	 * tablePages; i++) { tablerows =
	 * portdashboardtable.findElements(By.xpath("//tbody/tr")); for (WebElement rows
	 * : tablerows) {
	 * CommonUtilities.scrollToVisibilityOfLocation(rows.getLocation().getX(),
	 * rows.getLocation().getY() - 65); String porttypename =
	 * rows.findElement(By.xpath("td[" + (TypeColumn + 1) + "]")).getText(); String
	 * portname = rows.findElement(By.xpath("td[" + (nameColumn + 1) +
	 * "]")).getText(); logg.info("Port type is " + porttypename);
	 * logg.info("Port Name is " + rows.findElement(By.xpath("td[" + (nameColumn +
	 * 1) + "]")).getText()); if (porttypename.equalsIgnoreCase(porttype)) {
	 * CommonUtilities.JSClick(rows); CommonUtilities.threadSleep(2000); if
	 * (!requestcrossconnectButton.isEnabled()) {
	 * CommonUtilities.clickOnWebElement(closeButton);
	 * CommonUtilities.threadSleep(1000); } else {
	 * clickonRequestCrossConnectButton(); CommonUtilities.threadSleep(2000);
	 * boolean reqeustcrossconnectheaderflag = verifyRequestCrossConnectHeader();
	 * logg.info("Request Cross connect header is available = " +
	 * reqeustcrossconnectheaderflag);
	 * Assert.assertTrue(reqeustcrossconnectheaderflag,
	 * "Request Cross connect header is not displayed."); //
	 * enterDemarcLocation(StringWords.enterTestData("ActivePortLocationForLAG"));
	 * if (porttypename.equalsIgnoreCase("LAG")) {
	 * CommonUtilities.threadSleep(2000); if (portcheckboxlist.size() <= 0) { throw
	 * new
	 * SkipException("Ports are not available for selecting to perform Request Cross connect"
	 * ); } else { Boolean isportcheckboxselected = false; for (WebElement checkbox
	 * : portcheckboxlist) { if (checkbox.isEnabled()) {
	 * CommonUtilities.selectCheckbox(checkbox, "ON"); isportcheckboxselected =
	 * true; break; } } if (!isportcheckboxselected) throw new
	 * SkipException("Ports checkbox are not enabled to perform Request Cross connect"
	 * ); } } CommonUtilities.threadSleep(1000);
	 * enterDemarcLocation(StringWords.enterTestData("ActivePortLocationForLAG"));
	 * CommonUtilities.threadSleep(1000); clickonRequestCrossConnectButton();
	 * CommonUtilities.threadSleep(2000); // Boolean Cancelbuttonflag =
	 * CommonUtilities //
	 * .isElementNotDisplayed(By.xpath("//button[text()='Cancel']"), 10); //
	 * logg.info("Cancel Button is not displayed = " + Cancelbuttonflag);
	 * isDataavailable = true; // Assert.assertTrue(Cancelbuttonflag,
	 * "Request Cross Connect page is still displayed");
	 * CommonUtilities.threadSleep(2000); break; }
	 * 
	 * } } if (!isDataavailable && nextPageNavButton.isEnabled()) {
	 * CommonUtilities.clickOnWebElement(nextPageNavButton);
	 * CommonUtilities.threadSleep(2000); } if (isDataavailable &&
	 * nextPageNavButton.isEnabled()) break; } if (!isDataavailable) {
	 * logg.info("Table data is not available for " + porttype +
	 * " type port for the provided Selection"); throw new
	 * SkipException("Table data is not available for " + porttype +
	 * " type port for the provided Selection"); } } else { throw new
	 * SkipException("Port Table data is not available for the provided Selection");
	 * }
	 * 
	 * }
	 * 
	 * public void clickonRequestCrossConnectButton() { WebElement
	 * requestcrossconnectele =
	 * CommonUtilities.elementToBeClickable(requestcrossconnectButton, 10);
	 * requestcrossconnectele.click(); CommonUtilities.threadSleep(1000); }
	 * 
	 * public void DeletePortsInsideLAGPort(String porttype) { Boolean
	 * isLAGPortDeleted = false; List<String> tableHeaderslist =
	 * getTableHeaderColumnNumber(); int TypeColumn =
	 * tableHeaderslist.indexOf("Type"); int nameColumn =
	 * tableHeaderslist.indexOf("Name");
	 * 
	 * List<WebElement> tablerows =
	 * portdashboardtable.findElements(By.xpath("//tbody/tr")); if (tablerows.size()
	 * > 0) { WebElement firstRow = tablerows.get(0);
	 * CommonUtilities.scrollToVisibilityOfLocation(firstRow.getLocation().getX(),
	 * firstRow.getLocation().getY() - 65); String vcName =
	 * firstRow.findElement(By.xpath("td[" + (nameColumn + 1) + "]")).getText();
	 * logg.info("VC Status is " + vcName); if
	 * (vcName.contains("No Port Created Yet!")) { logg.
	 * info("Data Not available for the provided condition , PLease provide/create the valid data."
	 * ); throw new
	 * SkipException("Data Not available for the provided condition , PLease provide/create the valid data."
	 * ); } pageselectiondropdown.click(); int pagetypecount =
	 * pagespertableList.size(); pagespertableList.get(pagetypecount - 1).click();
	 * CommonUtilities.threadSleep(2000); tablerows =
	 * portdashboardtable.findElements(By.xpath("//tbody/tr")); int tablePages =
	 * Integer.parseInt(navigationbuttonlist.get((navigationbuttonlist.size() -
	 * 2)).getText()); WebElement nextPageNavButton =
	 * navigationbuttonlist.get((navigationbuttonlist.size() - 1));
	 * 
	 * logg.info("No of Pages in Table data is  " + tablePages); for (int i = 0; i <
	 * tablePages; i++) { tablerows =
	 * portdashboardtable.findElements(By.xpath("//tbody/tr")); for (WebElement rows
	 * : tablerows) {
	 * CommonUtilities.scrollToVisibilityOfLocation(rows.getLocation().getX(),
	 * rows.getLocation().getY() - 65); String porttypename =
	 * rows.findElement(By.xpath("td[" + (TypeColumn + 1) + "]")).getText();
	 * logg.info("Port type is " + porttypename); logg.info("Port Name is " +
	 * rows.findElement(By.xpath("td[" + (nameColumn + 1) + "]")).getText()); if
	 * (porttypename.equalsIgnoreCase("LAG")) { CommonUtilities.JSClick(rows);
	 * CommonUtilities.threadSleep(2000); if (viewicondetailsButton.size() > 1) {
	 * for (WebElement viewButton : viewicondetailsButton) {
	 * CommonUtilities.clickOnWebElement(viewButton); boolean filterFlag =
	 * filterbyMenuStatus("Delete"); if (filterFlag) { boolean
	 * deleteportconfirmheaderflag = verifyDeletePortConfirmationHeader();
	 * logg.info("Delete Port confirmation header is available = " +
	 * deleteportconfirmheaderflag); Assert.assertTrue(deleteportconfirmheaderflag,
	 * "Delete Port confirmation header is not displayed.");
	 * CommonUtilities.clickOnWebElement(deleteDialogButton);
	 * CommonUtilities.threadSleep(1000); isLAGPortDeleted = true; break; } } } else
	 * { Actions action = new Actions(driver);
	 * action.sendKeys(Keys.ESCAPE).build().perform();
	 * CommonUtilities.threadSleep(1000); } } if (isLAGPortDeleted) { i =
	 * tablePages; logg.info("Inside Ports are deleted for LAG Successfully");
	 * break; } } if (!isLAGPortDeleted && nextPageNavButton.isEnabled()) {
	 * nextPageNavButton.click(); CommonUtilities.threadSleep(2000); } } } else {
	 * throw new
	 * SkipException("Port Table data is not available for the provided Selection");
	 * } if (!isLAGPortDeleted) { logg.
	 * info("Ports are not available inside LAG to perform Delete functionality");
	 * throw new
	 * SkipException("Ports are not available inside LAG to perform Delete functionality"
	 * ); } }
	 */

	public void enterDemarcLocation(String demarclocation) {
		if (demarclocTextbox.isEnabled())
			CommonUtilities.sendkeys(demarclocTextbox, demarclocation);
	}

	public boolean verifyRequestCrossConnectHeader() {
		try {
			WebElement requestcrossconnectHeaderele = CommonUtilities.elementToBeVisible(requestcrossconnectHeader, 10);
			return (requestcrossconnectHeaderele.getText().equals("Request Cross Connect"));
		} catch (Exception e) {
			return false;
		}
	}

	public boolean verifyUpdateLAGHeader() {
		try {
			WebElement updatelagHeaderele = CommonUtilities.elementToBeVisible(updatelagheader, 10);
			return (updatelagHeaderele.getText().equals("Update Lag"));
		} catch (Exception e) {
			return false;
		}
	}

	public boolean verifyDeletePortConfirmationHeader() {
		try {
			WebElement deleteportconfirmHeaderele = CommonUtilities.elementToBeVisible(deleteportconfirmationHeader,
					10);
			return (deleteportconfirmHeaderele.getText().equals("Are you sure you want to delete this port?"));
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * public void downloadLOA() { List<String> tableHeaderslist =
	 * getTableHeaderColumnNumber(); int nameColumn =
	 * tableHeaderslist.indexOf("Name"); List<WebElement> tablerows =
	 * portdashboardtable.findElements(By.xpath("//tbody/tr")); if (tablerows.size()
	 * > 0) { WebElement rowCell = tablerows.get(0);
	 * CommonUtilities.scrollToVisibilityOfLocation(rowCell.getLocation().getX(),
	 * rowCell.getLocation().getY() - 65);
	 * CommonUtilities.scrollToVisibilityOfLocation(rowCell.getLocation().getX(),
	 * rowCell.getLocation().getY() - 65); String vcName =
	 * rowCell.findElement(By.xpath("td[" + (nameColumn + 1) + "]")).getText();
	 * logg.info("VC Status is " + vcName); if
	 * (vcName.contains("No Port Created Yet!")) { logg.
	 * info("Data Not available for the provided condition , PLease provide/create the valid data."
	 * ); throw new
	 * SkipException("Data Not available for the provided condition , PLease provide/create the valid data."
	 * ); } CommonUtilities.threadSleep(1000); CommonUtilities.JSClick(rowCell); //
	 * rowCell.click(); CommonUtilities.threadSleep(2000);
	 * CommonUtilities.clickOnWebElement(downloadLOAButton);
	 * CommonUtilities.threadSleep(2000); boolean downloadheaderflag =
	 * verifydownloadLOAHeader(); logg.info("Download LOA header is available = " +
	 * downloadheaderflag); Assert.assertTrue(downloadheaderflag,
	 * "Download LOA header is not displayed.");
	 * CommonUtilities.clickOnWebElement(generateLOAcheckbox);
	 * CommonUtilities.enterTextboxValue(emailLOATextbox,
	 * prop.getProperty("loginUsername")); int EmailLOASearchCount =
	 * emailLOAList.size(); logg.info("Email LOA Search Result " +
	 * EmailLOASearchCount); if (EmailLOASearchCount > 0)
	 * emailLOAList.get(0).click(); else
	 * Assert.fail("Email LOA list not available for the provided Email " +
	 * prop.getProperty("loginUsername")); CommonUtilities.threadSleep(1000);
	 * CommonUtilities.clickOnWebElement(generateButton); } else { throw new
	 * SkipException("Port Table data is not available for the provided Selection");
	 * }
	 * 
	 * }
	 * 
	 * public void DeleteServiceRecord(String serviceName) { List<String>
	 * tableHeaderslist = getTableHeaderColumnNumber(); int nameColumn =
	 * tableHeaderslist.indexOf("Name");
	 * 
	 * List<WebElement> tablerows =
	 * portdashboardtable.findElements(By.xpath("//tbody/tr")); if (tablerows.size()
	 * > 0) { WebElement firstRow = tablerows.get(0);
	 * CommonUtilities.scrollToVisibilityOfLocation(firstRow.getLocation().getX(),
	 * firstRow.getLocation().getY() - 65); String vcName =
	 * firstRow.findElement(By.xpath("td[" + (nameColumn + 1) + "]")).getText();
	 * logg.info("VC Status is " + vcName); if (vcName.contains("No " + serviceName
	 * + " Created Yet!")) { logg.
	 * info("Data Not available for the provided condition , PLease provide/create the valid data."
	 * ); throw new
	 * SkipException("Data Not available for the provided condition , PLease provide/create the valid data."
	 * ); } pageselectiondropdown.click(); int pagetypecount =
	 * pagespertableList.size(); pagespertableList.get(pagetypecount - 1).click();
	 * CommonUtilities.threadSleep(2000); tablerows =
	 * portdashboardtable.findElements(By.xpath("//tbody/tr")); int tablePages =
	 * Integer.parseInt(navigationbuttonlist.get((navigationbuttonlist.size() -
	 * 2)).getText()); WebElement nextPageNavButton =
	 * navigationbuttonlist.get((navigationbuttonlist.size() - 1));
	 * 
	 * logg.info("No of Pages in Table data is  " + tablePages); for (int i = 0; i <
	 * tablePages; i++) { tablerows =
	 * portdashboardtable.findElements(By.xpath("//tbody/tr")); for (WebElement rows
	 * : tablerows) {
	 * CommonUtilities.scrollToVisibilityOfLocation(rows.getLocation().getX(),
	 * rows.getLocation().getY() - 65); vcName = rows.findElement(By.xpath("td[" +
	 * (nameColumn + 1) + "]")).getText(); logg.info("VC Status is " + vcName);
	 * CommonUtilities.JSClick(rows); CommonUtilities.threadSleep(2000);
	 * CommonUtilities.clickOnWebElement(deleteQuickActionButton);
	 * CommonUtilities.threadSleep(2000); boolean terminateFlag =
	 * verifyterminateSubscriptionHeader(); if (terminateFlag) {
	 * TerminateInput.sendKeys("TERMINATE"); CommonUtilities.threadSleep(1000);
	 * WebElement terminateBtnEle =
	 * CommonUtilities.elementToBeClickable(terminatesubscriptionButton, 20);
	 * CommonUtilities.clickOnWebElement(terminateBtnEle);
	 * CommonUtilities.threadSleep(2000);
	 * logg.info("Subscription Invoice terminated Successfully"); break; } } } if
	 * (nextPageNavButton.isEnabled()) { nextPageNavButton.click();
	 * CommonUtilities.threadSleep(2000); } } else { throw new
	 * SkipException("Port Table data is not available for the provided Selection");
	 * }
	 * 
	 * }
	 */
	public boolean verifyterminateSubscriptionHeader() {
		try {
			WebElement webElement = CommonUtilities.elementToBeVisible(terminatepopupheader, 5);
			return (webElement.getText().equals("Are you sure you want to terminate this subscription?"));
		} catch (Exception e) {
			return false;
		}

	}

	public boolean verifydownloadLOAHeader() {
		try {
			WebElement webElement = CommonUtilities.elementToBeVisible(downloadloaheader, 5);
			return (webElement.getText().equals("Generate LOA"));
		} catch (Exception e) {
			return false;
		}

	}

	/*
	 * public void verifyPerfromanceMetricsContent(String servicetype) { // boolean
	 * terminatesubScription = false; CommonUtilities.threadSleep(2000);
	 * List<String> tableHeaderslist = getTableHeaderColumnNumber(); int nameColumn
	 * = tableHeaderslist.indexOf("Name"); List<WebElement> tablerows =
	 * portdashboardtable.findElements(By.xpath("//tbody/tr")); if (tablerows.size()
	 * > 0) { WebElement tablerow = tablerows.get(0); String portName =
	 * tablerow.findElement(By.xpath("td[" + (nameColumn + 1) + "]")).getText();
	 * logg.info("Port Status is " + portName); if
	 * (servicetype.equalsIgnoreCase("Port") &&
	 * portName.contains("No Port Created Yet!")) { logg.
	 * info("Port Data Not available for the provided Condition , PLease provide/create the valid data."
	 * ); throw new
	 * SkipException("Port Data Not available for the provided condition , PLease provide/create the valid data."
	 * ); } if (servicetype.equalsIgnoreCase("Virtual Connection") &&
	 * portName.contains("No Connection Created Yet!")) { logg.
	 * info("Port Data Not available for the provided Condition , PLease provide/create the valid data."
	 * ); throw new
	 * SkipException("Port Data Not available for the provided condition , PLease provide/create the valid data."
	 * ); }
	 * CommonUtilities.scrollToVisibilityOfLocation(tablerow.getLocation().getX(),
	 * tablerow.getLocation().getY() - 65); CommonUtilities.JSClick(tablerow);
	 * CommonUtilities.threadSleep(1000);
	 * CommonUtilities.clickOnWebElement(performancemetricsButton);
	 * CommonUtilities.threadSleep(1000); int tablecount =
	 * performancemetricstableList.size(); if (tablecount > 0) { for (WebElement
	 * tablele : performancemetricstableList) { logg.info("Table name is displayed "
	 * + tablele.getText()); } } else {
	 * logg.info("Performance matrics table content is not displayed"); throw new
	 * SkipException("Performance matrics table content is not displayed"); }
	 * 
	 * } else { throw new
	 * SkipException("Port Table data is not available for the provided Selection");
	 * } }
	 * 
	 * public void AddPortstoExistingLAGport() { boolean isPortsAddedtoLAG = false;
	 * List<String> tableHeaderslist = getTableHeaderColumnNumber(); int TypeColumn
	 * = tableHeaderslist.indexOf("Type"); int nameColumn =
	 * tableHeaderslist.indexOf("Name");
	 * 
	 * List<WebElement> tablerows =
	 * portdashboardtable.findElements(By.xpath("//tbody/tr")); if (tablerows.size()
	 * > 0) { WebElement tablerow = tablerows.get(0);
	 * CommonUtilities.scrollToVisibilityOfLocation(tablerow.getLocation().getX(),
	 * tablerow.getLocation().getY() - 65); String vcName =
	 * tablerow.findElement(By.xpath("td[" + (nameColumn + 1) + "]")).getText();
	 * logg.info("Port Status is " + vcName); if
	 * (vcName.contains("No Port Created Yet!")) { logg.
	 * info("Data Not available for the provided condition , PLease provide/create the valid data."
	 * ); throw new
	 * SkipException("Data Not available for the provided condition , PLease provide/create the valid data."
	 * ); } pageselectiondropdown.click(); int pagetypecount =
	 * pagespertableList.size(); pagespertableList.get(pagetypecount - 1).click();
	 * CommonUtilities.threadSleep(2000); tablerows =
	 * portdashboardtable.findElements(By.xpath("//tbody/tr")); int tablePages =
	 * Integer.parseInt(navigationbuttonlist.get((navigationbuttonlist.size() -
	 * 2)).getText()); WebElement nextPageNavButton =
	 * navigationbuttonlist.get((navigationbuttonlist.size() - 1));
	 * 
	 * logg.info("No of Pages in Table data is   " + tablePages); for (int i = 0; i
	 * < tablePages; i++) { tablerows =
	 * portdashboardtable.findElements(By.xpath("//tbody/tr")); for (WebElement rows
	 * : tablerows) {
	 * CommonUtilities.scrollToVisibilityOfLocation(rows.getLocation().getX(),
	 * rows.getLocation().getY() - 65); String porttypename =
	 * rows.findElement(By.xpath("td[" + (TypeColumn + 1) + "]")).getText();
	 * logg.info("Port type is " + porttypename); logg.info("Port Name is " +
	 * rows.findElement(By.xpath("td[" + (nameColumn + 1) + "]")).getText()); if
	 * (porttypename.equalsIgnoreCase("LAG")) { CommonUtilities.JSClick(rows);
	 * CommonUtilities.threadSleep(2000); WebElement addmoreportsele =
	 * CommonUtilities.elementToBeClickable(addmoreportsButton, 10);
	 * CommonUtilities.JSClick(addmoreportsele); boolean updatelagtheaderflag =
	 * verifyUpdateLAGHeader(); logg.info("Update Lag header is available = " +
	 * updatelagtheaderflag); Assert.assertTrue(updatelagtheaderflag,
	 * "Update Lag header is not displayed.");
	 * CommonUtilities.clickOnWebElement(nooflagportstextbox);
	 * CommonUtilities.threadSleep(1000);
	 * CommonUtilities.clickOnWebElement(updateportcountList.get(0));
	 * CommonUtilities.threadSleep(1000);
	 * CommonUtilities.clickOnWebElement(subscriptiontermlist.get(0));
	 * CommonUtilities.threadSleep(1000); if (paymenttypelist.size() > 0) {
	 * CommonUtilities.clickOnWebElement(paymenttypelist.get(0));
	 * CommonUtilities.threadSleep(1000); } boolean updatebuttonflag =
	 * isUpdateBtnEnabled(); logg.info("Update Button is enabled = " +
	 * updatebuttonflag); Assert.assertTrue(updatebuttonflag,
	 * "Update Button is not enabled."); clickOnUpdateButton();
	 * CommonUtilities.threadSleep(2000); isPortsAddedtoLAG = true; updatebuttonflag
	 * =
	 * CommonUtilities.isElementNotDisplayed(By.xpath("//button[text()='Update']"),
	 * 10); logg.info("Update Button is displayed = " + updatebuttonflag);
	 * Assert.assertTrue(updatebuttonflag,
	 * "Update Button is still displayed after Update."); break; } } if
	 * (!isPortsAddedtoLAG && nextPageNavButton.isEnabled()) {
	 * nextPageNavButton.click(); CommonUtilities.threadSleep(2000); } if
	 * (isPortsAddedtoLAG) break; } } else { throw new
	 * SkipException("Port Table data is not available for the provided Selection");
	 * } }
	 */

	public String getSuccessNotificationMessage() {
		try {
			WebElement statusElement = CommonUtilities.elementToBeVisible(SuccessNotification, 10);
			CommonUtilities.threadSleep(1000);
			logg.info(statusElement.getText());
			return statusElement.getText();
		} catch (Exception e) {
			logg.info(e);
			return "Exception Occured" + e;
		}
	}

	public String getDeleteSuccessMessage() {
		try {
			WebElement statusElement = CommonUtilities.elementToBeVisible(deleteNotification, 10);
			CommonUtilities.threadSleep(1000);
			logg.info(statusElement.getText());
			return statusElement.getText();
		} catch (Exception e) {
			logg.info(e);
			return "Exception Occured" + e;
		}
	}

//	public String getDeleteSuccessMessage(String notification ) {
//		try {
//			Boolean iseledisplayed  = CommonUtilities.elementToBeVisible(By.xpath("//h2[text()='Service deleted!']"),notification, 10);
//			CommonUtilities.threadSleep(1000);
//			return (iseledisplayed) ? SuccessNotification.getText() : "";
//		} catch (Exception e) {
//			return "";
//		}
//	}

	/*
	 * public void enterVCSearchValue(String name) {
	 * CommonUtilities.enterTextboxValue(vcSearchTextbox, name); }
	 * 
	 * public void enterVRSearchValue(String name) {
	 * CommonUtilities.enterTextboxValue(vrSearchTextbox, name); }
	 * 
	 * public void enterPortSearchValue(String name) {
	 * CommonUtilities.enterTextboxValue(portSearchTextbox, name); }
	 * 
	 * public String getServiceStatus() { String status = ""; List<String>
	 * tableHeaderslist = getTableHeaderColumnNumber(); int statusColumn =
	 * tableHeaderslist.indexOf("Status"); List<WebElement> tablerows =
	 * portdashboardtable.findElements(By.xpath("//tbody/tr")); if (tablerows.size()
	 * > 0) { status = tablerows.get(0).findElement(By.xpath("td[" + (statusColumn +
	 * 1) + "]")).getText(); logg.info("Port Status is " + status); } else { throw
	 * new SkipException("Table data is not available for the provided service"); }
	 * return status; }
	 * 
	 * public void upgradeVCBrandwidth() { List<String> tableHeaderslist =
	 * getTableHeaderColumnNumber(); int nameColumn =
	 * tableHeaderslist.indexOf("Name"); int rateLimitcolumn =
	 * tableHeaderslist.indexOf("Rate Limit"); List<WebElement> tablerows =
	 * portdashboardtable.findElements(By.xpath("//tbody/tr")); if (tablerows.size()
	 * > 0) { WebElement tablerow = tablerows.get(0);
	 * CommonUtilities.scrollToVisibilityOfLocation(tablerow.getLocation().getX(),
	 * tablerow.getLocation().getY() - 65); String vcName =
	 * tablerow.findElement(By.xpath("td[" + (nameColumn + 1) + "]")).getText();
	 * logg.info("Port Status is " + vcName); if
	 * (vcName.contains("No Connection Created Yet!")) { logg.
	 * info("Data Not available for the provided condition , PLease provide/create the valid data."
	 * ); throw new
	 * SkipException("Data Not available for the provided condition , PLease provide/create the valid data."
	 * ); } String oldratelimit = tablerow.findElement(By.xpath("td[" +
	 * (rateLimitcolumn + 1) + "]")).getText(); oldratelimit =
	 * oldratelimit.split(" ")[0]; logg.info("rate Limit is " + oldratelimit);
	 * CommonUtilities.JSClick(tablerow); Boolean updateBtnflag =
	 * CommonUtilities.isElementClickable(updateButton, 10);
	 * logg.info("Update Button is available = " + updateBtnflag);
	 * Assert.assertTrue(updateBtnflag, "Update Button is not displayed.");
	 * CommonUtilities.clickOnWebElement(updateButton); Boolean updateHeaderflag =
	 * verifyUpdateHeader(); logg.info("Update Header is available = " +
	 * updateHeaderflag); Assert.assertTrue(updateHeaderflag,
	 * "Update Header is not displayed."); int newratelimit =
	 * Integer.parseInt(oldratelimit) + 10; logg.info("new rate Limit is " +
	 * newratelimit); enterTheNewSpeedRateLimit(Integer.toString(newratelimit));
	 * 
	 * } else { throw new
	 * SkipException("Table data is not available for the provided service"); } }
	 * 
	 * public void addonVCRatelimit() { List<String> tableHeaderslist =
	 * getTableHeaderColumnNumber(); int nameColumn =
	 * tableHeaderslist.indexOf("Name"); int rateLimitcolumn =
	 * tableHeaderslist.indexOf("Rate Limit"); List<WebElement> tablerows =
	 * portdashboardtable.findElements(By.xpath("//tbody/tr")); if (tablerows.size()
	 * > 0) { WebElement tablerow = tablerows.get(0);
	 * CommonUtilities.scrollToVisibilityOfLocation(tablerow.getLocation().getX(),
	 * tablerow.getLocation().getY() - 65); String vcName =
	 * tablerow.findElement(By.xpath("td[" + (nameColumn + 1) + "]")).getText();
	 * logg.info("Port Status is " + vcName); if
	 * (vcName.contains("No Connection Created Yet!")) { logg.
	 * info("Data Not available for the provided condition , PLease provide/create the valid data."
	 * ); throw new
	 * SkipException("Data Not available for the provided condition , PLease provide/create the valid data."
	 * ); } String oldratelimit = tablerow.findElement(By.xpath("td[" +
	 * (rateLimitcolumn + 1) + "]")).getText(); oldratelimit =
	 * oldratelimit.split(" ")[0]; logg.info("rate Limit is " + oldratelimit);
	 * CommonUtilities.JSClick(tablerow); Boolean addonRatelimitBtnflag =
	 * CommonUtilities.isElementClickable(enableAddonratelimitButton, 10);
	 * logg.info("Addon Rate Limit Button is available = " + addonRatelimitBtnflag);
	 * Assert.assertTrue(addonRatelimitBtnflag,
	 * "Addon Rate Limit Button is not displayed.");
	 * CommonUtilities.clickOnWebElement(enableAddonratelimitButton); Boolean
	 * addonratelimitHeaderflag = verifyAddonRatelimitHeader();
	 * logg.info("Addon Rate Limit Header is available = " +
	 * addonratelimitHeaderflag); Assert.assertTrue(addonratelimitHeaderflag,
	 * "Addon Rate Limit Header is not displayed."); int newratelimit = 10;
	 * logg.info("Addon rate Limit is " + newratelimit);
	 * enterTheNewSpeedRateLimit(Integer.toString(newratelimit)); Boolean
	 * orderBtnflag = CommonUtilities.isElementClickable(orderbutton, 10);
	 * logg.info("Order Button is enabled = " + orderBtnflag);
	 * Assert.assertTrue(orderBtnflag, "Order Button is not enabled."); //
	 * CommonUtilities.clickOnWebElement(orderbutton);
	 * 
	 * } else { throw new
	 * SkipException("Table data is not available for the provided service"); } }
	 * 
	 * public String getServiceRecordFieldValue(String servicetype, String
	 * servicename, String columnName) { String status = "";
	 * selectTheServices(servicetype); CommonUtilities.threadSleep(2000);
	 * List<String> tableHeaderslist = getTableHeaderColumnNumber(); int columnIndex
	 * = tableHeaderslist.indexOf(columnName); int nameColumn =
	 * tableHeaderslist.indexOf("Name"); clickOnClearButton(); if
	 * (servicetype.equalsIgnoreCase("Ports")) enterPortSearchValue(servicename);
	 * else if (servicetype.equalsIgnoreCase("Virtual Routers"))
	 * enterVRSearchValue(servicename); else if
	 * (servicetype.equalsIgnoreCase("Virtual Connections"))
	 * enterVCSearchValue(servicename); CommonUtilities.threadSleep(5000); boolean
	 * reloadIconFlag = CommonUtilities.isElementNotDisplayed(dashboardReloadIcon,
	 * 15); if (!reloadIconFlag) CommonUtilities.threadSleep(5000);
	 * CommonUtilities.threadSleep(5000); List<WebElement> tablerows =
	 * portdashboardtable.findElements(By.xpath("//tbody/tr")); if (tablerows.size()
	 * > 0) { WebElement firstRow = tablerows.get(0);
	 * CommonUtilities.scrollToVisibilityOfLocation(firstRow.getLocation().getX(),
	 * firstRow.getLocation().getY() - 65); String ServiceName =
	 * firstRow.findElement(By.xpath("td[" + (nameColumn + 1) + "]")).getText();
	 * logg.info("ServiceName Status is " + ServiceName); if
	 * (ServiceName.contains("Created Yet!") && ServiceName.startsWith("No")) {
	 * logg.
	 * info("Data Not available for the provided condition , Please provide/create the valid data."
	 * ); status = firstRow.findElement(By.xpath("td[" + (nameColumn + 1) +
	 * "]")).getText(); } else status = firstRow.findElement(By.xpath("td[" +
	 * (columnIndex + 1) + "]")).getText(); } return status; }
	 * 
	 * public void waittillServiceDBPageLoading() {
	 * CommonUtilities.threadSleep(2000); boolean reloadIconFlag =
	 * CommonUtilities.isElementNotDisplayed(dashboardReloadIcon, 15); if
	 * (!reloadIconFlag) CommonUtilities.threadSleep(5000);
	 * CommonUtilities.threadSleep(2000); }
	 */
	/*
	 * public String verifyServiceStatus(String serviceType, String serviceName,
	 * String expectedStatus, int waittime) { String status; Duration timeElapsed;
	 * Instant start = Instant.now(); do { CommonUtilities.refreshPage();
	 * CommonUtilities.threadSleep(5000); try { status =
	 * getServiceRecordFieldValue(serviceType, serviceName, "Status"); } catch
	 * (StaleElementReferenceException e) { status =
	 * getServiceRecordFieldValue(serviceType, serviceName, "Status"); }
	 * logg.info(serviceType + " service status is:= " + status);
	 * CommonUtilities.threadSleep(5000); Instant end = Instant.now(); timeElapsed =
	 * Duration.between(start, end); } while (timeElapsed.toMinutes() < waittime &&
	 * (!status.equalsIgnoreCase(expectedStatus)) &&
	 * (!status.equalsIgnoreCase("Failed")) && (!status.equalsIgnoreCase("Live")));
	 * return status; }
	 */
}
