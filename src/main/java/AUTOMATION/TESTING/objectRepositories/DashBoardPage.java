package AUTOMATION.TESTING.objectRepositories;



import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import AUTOMATION.TESTING.testBase.TestBase;
import AUTOMATION.TESTING.utility.CommonUtilities;


public class DashBoardPage extends TestBase {
	public static final Logger logg = Logger.getLogger(DashBoardPage.class.getName());
	WebDriverWait wait;
	/****************************************************/
	@FindBy(xpath = "//button[@aria-label='Your Profile']/div/div/p")
	private WebElement userNameLabel;
	/***************************
	 * SideBarNavigation
	 *****************************************************/
	@FindBy(xpath = "(//div[@class='MuiListItemIcon-root css-qwko1u'])[5]")
	private WebElement keepMenuOpen;
	@FindBy(xpath = "//div[@class='MuiBox-root css-15illud']")
	private WebElement sideBarNavigationCloseBtn;
	@FindBy(xpath = "//button[@aria-label='Your Profile']")
	private WebElement userProfile;
	@FindBy(xpath = "//p[text()='Sign Out']//ancestor::li")
	private WebElement logoutButton;

	@FindBy(xpath = "(//*[@class='MuiTypography-root MuiTypography-body1 css-92pcgx'])[1]")
	private WebElement personalDetails;
	@FindBy(xpath = "(//div[@class='MuiListItemIcon-root css-qwko1u'])[4]")
	private WebElement billingMainEle;
	@FindBy(xpath = "(//*[contains(text(),'Billing')])[2]")
	private WebElement billingChildEle;
	@FindBy(xpath = "(//div[@class='MuiListItemIcon-root css-qwko1u'])[3]")
	private WebElement organizationProfile;
	@FindBy(xpath = "//*[contains(text(),'User Management')]")
	private WebElement userManagement;
	@FindBy(xpath = "(//*[@class='MuiTypography-root MuiTypography-body1 MuiListItemText-primary css-1i73w6w'])[1]")
	private WebElement organizationProfilechildtab;
	@FindBy(xpath = "(//div[@class='MuiListItemIcon-root css-qwko1u'])[2]")
	private WebElement activeLogs;
	@FindBy(xpath = "(//div[@class='MuiListItemIcon-root css-qwko1u'])[1]")
	private WebElement services;
	@FindBy(xpath = "//span[text()='Subscriptions & Invoices']")
	private WebElement childSubscriptionsAndInvoicesBtn;
//----------------------------------------------------------------------------------------------------------
	@FindBy(xpath = "//button[@aria-label= 'Notifications']")
	private WebElement notificationButton;
	@FindBy(xpath = "//h3[text()='Notifications']")
	private WebElement notificationHeader;
	@FindBy(xpath = "//p[text()='Please review and place the order.']/../preceding-sibling::div[@class='css-2jr6w']/p")
	private WebElement NotificationList;
	@FindBy(xpath = "//h3[text()='Notifications']/following::button")
	private WebElement notificationCloseButton;
	@FindBy(xpath = "//div[@class='css-17d4jz7']//img[@alt='Logo']")
	private WebElement polarionLogo;

	@FindBy(xpath = "//div[@class='MuiListItemText-root css-tuasyg']")
	private List<WebElement> NavigationChildList;

	@FindBy(xpath = "//button[@aria-label='Notifications']/div/p")
	private WebElement LatestNotications;

	@FindBy(xpath = "//div[contains(text(),'All')]")
	private WebElement ShowAllNotificationsCount;

	@FindBy(xpath = "//div[contains(text(),'Read')]")
	private WebElement ShowReadNotificationsCount;

	@FindBy(xpath = "//li[contains(text(),'All')]")
	private WebElement list_All_NotificationsCount;

	@FindBy(xpath = "//li[contains(text(),'Read')]")
	private WebElement list_Read_NotificationsCount;

	@FindBy(xpath = "//li[contains(text(),'Unread')]")
	private WebElement list_Unread_NotificationsCount;

	@FindBy(xpath = "//p[text()='Older']/following::div[starts-with(@class,'MuiAvatar-root MuiAvatar-circular MuiAvatar')]")
	private WebElement NotificationsCount;

	@FindBy(xpath = "//button[@aria-label='Help']")
	private WebElement help_Icon;

	@FindBy(xpath = "//button[text()='Knowledge Base']")
	private WebElement knowlegeBaseLink_HelpMenu;

	@FindBy(xpath = "//button[text()='Raise an Issue']")
	private WebElement raiseIssueLink_HelpMenu;

	public DashBoardPage() {
		PageFactory.initElements(driver, this);
	}

	public String DashboardPageTitle() {
		return driver.getTitle();
	}

	public boolean verifiedCorrectUserName() {
		return userNameLabel.isDisplayed();
	}

	public void keepMenuBarOpen() {
		WebElement keepSideMunuButton = CommonUtilities.elementToBeClickable(keepMenuOpen, 15);
		keepSideMunuButton.click();

	}

	/*
	 * public UserProfilePage clickOnUserMyProfileBtn() throws InterruptedException
	 * { WebElement userprofile = CommonUtilities.elementToBeClickable(userProfile,
	 * 20); userprofile.click(); Thread.sleep(500); // personalDetails.click();
	 * return new UserProfilePage(); }
	 */

	/*
	 * public KnowlegeBasePage clickOnHelpAndSupport() {
	 * 
	 * WebElement helpAndSupport = CommonUtilities.elementToBeClickable(help_Icon,
	 * 20); helpAndSupport.click(); CommonUtilities.threadSleep(500); return new
	 * KnowlegeBasePage(); }
	 */

	public boolean clickOnRaiseAnIssueLink() {
		WebElement KnowlegeBaseLink = CommonUtilities.elementToBeClickable(raiseIssueLink_HelpMenu, 20);
		KnowlegeBaseLink.click();
		CommonUtilities.threadSleep(500);
		return true;
	}

	public boolean clickOnKnowledgeBaseLink() {
		WebElement KnowlegeBaseLink = CommonUtilities.elementToBeClickable(knowlegeBaseLink_HelpMenu, 20);
		KnowlegeBaseLink.click();
		CommonUtilities.threadSleep(500);
		return true;
	}

	/*
	 * public OrganizationProfilePage clickOnOrganizationBtn() { WebElement
	 * organizationmaintab =
	 * CommonUtilities.elementToBeClickable(organizationProfile, 20);
	 * organizationmaintab.click(); CommonUtilities.threadSleep(1000); WebElement
	 * organizationprofilechildtab =
	 * CommonUtilities.elementToBeClickable(organizationProfilechildtab, 20);
	 * organizationprofilechildtab.click(); CommonUtilities.threadSleep(1000);
	 * logg.info("click on Organization Profile"); return new
	 * OrganizationProfilePage(); }
	 * 
	 * public UserManagementPage clickOnUserManagementBtn() { WebElement
	 * organizationprof = CommonUtilities.elementToBeClickable(organizationProfile,
	 * 20); organizationprof.click(); WebElement usermanagement =
	 * CommonUtilities.elementToBeClickable(userManagement, 20);
	 * usermanagement.click(); return new UserManagementPage(); }
	 * 
	 * public BillingPage clickOnBillingBtn() throws InterruptedException {
	 * WebElement mainBillingElement =
	 * CommonUtilities.elementToBeClickable(billingMainEle, 20);
	 * logg.info("Label name := " + mainBillingElement.getText());
	 * mainBillingElement.click(); Thread.sleep(1000);
	 */

//		if (NavigationChildList.size() > 0) {
//			logg.info("click on Billing" + NavigationChildList.get(1).getText());
//			NavigationChildList.get(1).click();
//
//		}
	/*
	 * WebElement billingChildElement =
	 * CommonUtilities.elementToBeClickable(billingChildEle, 20);
	 * billingChildElement.click(); logg.info("click on Billing"); return new
	 * BillingPage(); }
	 */

	public void clickOnBillingBtn(String childItem) {
		WebElement mainBillingElement = CommonUtilities.elementToBeClickable(billingMainEle, 20);
		logg.info("Label name := " + mainBillingElement.getText());
		mainBillingElement.click();
		CommonUtilities.threadSleep(1000);

		for (WebElement element : NavigationChildList) {
			if (element.getText().equals(childItem)) {
				logg.info("click on Billing child item " + element.getText() + " Successfully");
				element.click();
				CommonUtilities.threadSleep(1000);
				break;
			}
		}

//		WebElement billingChildElement = CommonUtilities.elementToBeClickable(billingChildEle, 20);
//		billingChildElement.click();
//		logg.info("click on Billing");
	}

	/*
	 * public SubscriptionsAndInvoicesPage clickOnSubscriptionAndInvoiceBtn() throws
	 * InterruptedException { WebElement billingMainElement =
	 * CommonUtilities.elementToBeClickable(billingMainEle, 20);
	 * billingMainElement.click(); Thread.sleep(500); WebElement
	 * childSubscriptionAndInvoiceBtn = CommonUtilities
	 * .elementToBeClickable(childSubscriptionsAndInvoicesBtn, 20);
	 * childSubscriptionAndInvoiceBtn.click(); return new
	 * SubscriptionsAndInvoicesPage(); }
	 */
	public CreatePortPage clickOnServicesForPort() {
		WebElement serviceport = CommonUtilities.elementToBeClickable(services, 25);
		serviceport.click();
		logg.info("click on Services Dashboard");
		CommonUtilities.threadSleep(2000);
		return new CreatePortPage();
	}

	/*
	 * public ServicesViewDraftPage clickOnServiceviewDraftPage() { //
	 * keepMenuBarOpen(); try { Thread.sleep(1000); } catch (InterruptedException e)
	 * { // TODO Auto-generated catch block e.printStackTrace(); } WebElement
	 * servicesviewDraftpage = CommonUtilities.elementToBeClickable(services, 20);
	 * servicesviewDraftpage.click(); logg.info("click on Services Dashboard");
	 * return new ServicesViewDraftPage(); }
	 */

	public ServiceDashboardPage clickOnServicePage() {
		WebElement servicespage = CommonUtilities.elementToBeClickable(services, 20);
		servicespage.click();
		logg.info("click on Services Dashboard");
		return new ServiceDashboardPage();
	}

	/*
	 * public CreateVirtualRouterPage clickOnServicespageForVirtualRouter() {
	 * WebElement virtualrouterpg = CommonUtilities.elementToBeClickable(services,
	 * 30); virtualrouterpg.click(); logg.info("click on Services Dashboard");
	 * return new CreateVirtualRouterPage(); }
	 * 
	 * public CreateVirtualConnectionPortToPortPage
	 * clickOnServicePageForVertualConnection() { WebElement virtualconnection =
	 * CommonUtilities.elementToBeClickable(services, 20);
	 * virtualconnection.click(); logg.info("click on Services Dashboard"); return
	 * new CreateVirtualConnectionPortToPortPage(); }
	 */
	public ServiceCreationPage clickOnServicePageForShowAllServiceCreation() {
		WebElement showAllServicePage = CommonUtilities.elementToBeClickable(services, 20);
		showAllServicePage.click();
		logg.info("click on Services Dashboard");
		return new ServiceCreationPage();
	}

	public boolean isOnHomePage() {
		try {
			clickOnPolarinLogo();
			WebElement userProfileImage = CommonUtilities.elementToBeVisible(userProfile, 5);
			return (userProfileImage.isDisplayed()) ? true : false;

		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnPolarinLogo() {
		CommonUtilities.clickOnWebElement(polarionLogo);
		logg.info("click on Polarin Logo Link");
		CommonUtilities.threadSleep(1000);
	}

	public boolean verifyNotificationHeader() {
		try {
			WebElement notificationhed = CommonUtilities.elementToBeVisible(notificationHeader, 5);
			return (notificationhed.isDisplayed()) ? true : false;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean verifyNotificationButton() {
		try {
			WebElement notificationbtn = CommonUtilities.elementToBeVisible(notificationButton, 5);
			return (notificationbtn.isDisplayed()) ? true : false;

		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnNotificationButton() {
		WebElement notificationelement = CommonUtilities.elementToBeClickable(notificationButton, 20);
		notificationelement.click();
		logg.info("click on notification Button");
		CommonUtilities.threadSleep(1000);
	}

	public boolean verifyPortOrderNotificationList(String ordername) {
		List<WebElement> elements = driver.findElements(By.xpath(
				"//p[text()='Please review and place the order.']/../preceding-sibling::div[@class='css-2jr6w']/p"));
		for (WebElement element : elements) {
			String name = element.getText();
			if (name.contains(ordername)) {
				System.out.println("Paragraph text:" + element.getText());
				return true;
			}
			System.out.println("Paragraph text:" + element.getText());

		}
		return false;

	}

	public void logoutPortal() {
		CommonUtilities.clickOnWebElement(userProfile);
		CommonUtilities.threadSleep(1000);
		CommonUtilities.clickOnWebElement(logoutButton);
	}

	public int getLatestNotificationsCount() {
		try {
			if (LatestNotications.isDisplayed()) {
				String count = LatestNotications.getText();
				int LatestNotificationCount = Integer.parseInt(count);
				logg.info("Latest Notifications count is : " + LatestNotificationCount);
				return LatestNotificationCount;
			} else {
				logg.info("You have no notifications right now. Come back later!");
				return 0;
			}
		} catch (Exception e) {
			logg.info("You have no latest notifications right now. Come back later!");
			return 0;
		}
	}

	public void clickShowAllNotifications() {
		CommonUtilities.elementToBeVisible(ShowAllNotificationsCount, 5);
		ShowAllNotificationsCount.click();
		CommonUtilities.threadSleep(1000);
	}

	public void clickShowReadNotifications() {
		CommonUtilities.elementToBeVisible(ShowReadNotificationsCount, 5);
		ShowReadNotificationsCount.click();
		CommonUtilities.threadSleep(1000);
	}

	public int getAllNotificationsCount_Navigate() {
		String count = list_All_NotificationsCount.getText();

		String AllNotifications = count.substring(5, count.length() - 1);
		int AllNotificationCount = Integer.parseInt(AllNotifications);
		list_All_NotificationsCount.click();
		CommonUtilities.threadSleep(2000);
		return AllNotificationCount;
	}

	public int getReadNotificationsCount_Navigate() {
		String count = list_Read_NotificationsCount.getText();

		String ReadNotifications = count.substring(6, count.length() - 1);
		int ReadNotificationCount = Integer.parseInt(ReadNotifications);
		list_Read_NotificationsCount.click();
		CommonUtilities.threadSleep(2000);
		return ReadNotificationCount;
	}

	public int getUnreadNotificationsCount_Navigate() {
		String count = list_Unread_NotificationsCount.getText();
		String UnreadNotifications = count.substring(8, count.length() - 1);
		int UnreadNotificationCount = Integer.parseInt(UnreadNotifications);
		list_Unread_NotificationsCount.click();
		CommonUtilities.threadSleep(2000);
		return UnreadNotificationCount;

	}

	public int getNotificationsCount() {
		CommonUtilities.elementToBeVisible(NotificationsCount, 5);

		List<WebElement> l = driver.findElements(By.xpath(
				"//p[text()='Older']/following::div[starts-with(@class,'MuiAvatar-root MuiAvatar-circular MuiAvatar')]"));
		logg.info("------------count------ :" + l.size());
		return l.size();

	}

	public void closeNotifications() {
		CommonUtilities.elementToBeClickable(notificationCloseButton, 5);
//		CommonUtilities.threadSleep(2000);
		notificationCloseButton.click();
	}
	
	/*
	 * public ActivityLogPage clickOnActivityLogs() { WebElement activitylogsLink =
	 * CommonUtilities.elementToBeClickable(activeLogs, 20);
	 * activitylogsLink.click(); CommonUtilities.threadSleep(1000); return new
	 * ActivityLogPage(); }
	 */
}
