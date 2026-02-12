/*package AUTOMATION.TESTING.testCases;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



public class CreatePortTestCases extends TestBase {
	public static final Logger logg = Logger.getLogger(CreatePortTestCases.class.getName());
	String subTotalMonthWise, diccountonPayment, totalAmountafterDiscount, upfrontPayment, youPayAmountMonthWise,
			totalAmountbeforDiscount, totalAmountOnCheckOutPage, getPortNameFromConfigurePage;
	LoginPage loginpage;
	LoginACPPage acploginpage;
	LoginCRMPage crmloginpage;
	LoginAdminPortalPage adminportalloginpage;
	CRMHomePage crmHomePage;
	OrdersCRMPage crmOrdersPage;
	OpportunitiesCRMPage crmoptyPage;
	DashBoardPage dashboard;
	CreatePortPage portcreation;
	CreateVirtualRouterPage virtualrouter;
	ServicesDashboardPage servicedashboard;
	ServiceCreationPage servicecreation;
	BillingPage billingpage;
	CreateVirtualConnectionPortToPortPage portToPortVcConnection;

	String feMainTab, crmMainTab, acpMainTab, adminMainTab;

	String portName = "AUTPort21NovHwGIu";
	String subscriptionTerm, paymentType, totalamount;
	String upfrontPaymentPrice, monthlyPaymentPrice;
	String orderNumber = "pmbnt210g2747";
	String serviceIDNumber = "ppnst110g1317";
	String salesAssist_serviceIDNumber = "pmbnt210g2747";
	String salesAssist_orderNumber = "pmbnt210g2729";
	String AdminStartDate = "17 Oct 2024";
	String AdminEndDate = "17 Oct 2024";
	String AdminPONumber = "6545776";
	String AdminStartBillingDate = "17 Oct 2024";

	Map<String, String> servicehm = new HashMap<String, String>();

	public CreatePortTestCase() {
		super();
	}

	@BeforeClass(alwaysRun = true)
	public void setUp() {
		initializeBrowser();
//		String feMainTab = LaunchURL(prop.getProperty("url"));
//		String crmMainTab = LaunchURL_NewTab("https://www.browserstack.com/guide/handle-multiple-windows-in-selenium");
//		String acpMainTab = LaunchURL_NewTab(
//				"https://stackoverflow.com/questions/34829329/how-to-open-a-link-in-new-tab-chrome-using-selenium-webdriver");
//		logg.info("feMainTab:- " + feMainTab + "crmMainTab:- " + crmMainTab + "acpMainTab:- " + acpMainTab);
//		switchTo_Tab(feMainTab);
		loginpage = new LoginPage();
		acploginpage = new LoginACPPage();
		adminportalloginpage = new LoginAdminPortalPage();
		crmloginpage = new LoginCRMPage();
		crmHomePage = new CRMHomePage();
		crmOrdersPage = new OrdersCRMPage();
		crmoptyPage = new OpportunitiesCRMPage();
		virtualrouter = new CreateVirtualRouterPage();
		servicedashboard = new ServicesDashboardPage();
		servicecreation = new ServiceCreationPage();
		billingpage = new BillingPage();
		portToPortVcConnection = new CreateVirtualConnectionPortToPortPage();

		servicehm.put("country", "India");
		servicehm.put("Location", "Equinix MB1 (GPX1)");
		servicehm.put("subscriptionTerm", "24 Months");
		servicehm.put("paymentType", "Partial Upfront");
		servicehm.put("PortSpeed", "10 GE");
		servicehm.put("optyName", "Automation");
	}

	@Test(priority = 1, groups = { "Customer Portal" })
	public void TC_01_Verify_that_Create_a_Port_option_should_be_there_under_Create_dropdown_on_the_Services_page() {
		try {
			feMainTab = LaunchURL(prop.getProperty("url"));
			CommonUtilities.threadSleep(1000);
			boolean Welcometextflag = loginpage.isOnLoginPage();
			logg.info("login title is displayed = " + Welcometextflag);
			Assert.assertTrue(Welcometextflag, "Welcome text is not displayed in login Page");
			dashboard = loginpage.valid_Credentials_login(prop.getProperty("loginUsername"),
					prop.getProperty("loginPassword"));
			CommonUtilities.threadSleep(2000);

			boolean userprofileflag = dashboard.isOnHomePage();
			logg.info("login is successful = " + userprofileflag);
			Assert.assertTrue(userprofileflag, "User profile is displayed and login is successful.");
			portcreation = dashboard.clickOnServicesForPort();
			boolean portcreationservice = servicecreation.verifyservicebutondisplayed("Port");
			logg.info("Is the Port Creation Available? = " + portcreationservice);
			Assert.assertTrue(portcreationservice, "Create Port Button is not displayed");

		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 2, groups = { "Customer Portal" })
	public void TC_02_Verify_the_functionality_when_user_clicks_on_Create_a_Port_option() {
		try {
			servicecreation.clickonService("Port");
			boolean createportHeader = portcreation.verifyCreatePortHeader();
			logg.info("Create Port Header displayed = " + createportHeader);
			Assert.assertTrue(createportHeader, "Create Port header is not displayed");
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 3, groups = { "Customer Portal" })
	public void TC_03_Verify_that_user_should_not_able_to_navigate_to_Configure_Port_or_Checkout_section_until_in_Port_location_is_selected() {
		try {
			String configurePortstage = portcreation.verifyPortCreationStep("Configure Port");
			String checkoutstage = portcreation.verifyPortCreationStep("Checkout");
			Boolean IsNextButonEnabled = portcreation.isNextButtonEnabled();
			logg.info("Is the Next button enabled? = " + IsNextButonEnabled);
			Assert.assertFalse(IsNextButonEnabled, "Next button is enabled without selecting the portlocation");
			Assert.assertEquals("Configure Port section is enabled: " + configurePortstage,
					"Configure Port section is enabled: false");
			Assert.assertEquals("Checkout Port section is enabled: " + checkoutstage,
					"Checkout Port section is enabled: false");
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 4, groups = { "Customer Portal" })
	public void TC_04_Verify_that_user_should_be_able_to_select_location_and_Next_button_should_be_enabled_immediately()
			throws IllegalAccessException {
		try {
			String country = "India";
			servicehm.put("country", country);
			portcreation.selectTheCountryfromDropDown(country);
			logg.info("Select country from dropdown");
			String selectedActualCountry = portcreation.getcountrydropdownvalue();
			boolean countryDropdownvalueflag = selectedActualCountry.equalsIgnoreCase(country);
			logg.info("Country value is successfully selected to  = " + country + " - " + countryDropdownvalueflag);
			Assert.assertTrue(countryDropdownvalueflag, "Country value is not selected to" + country);

			portcreation.selectPortSpeedEnabledLocation(StringWords.enterTestData("ActivePortLocation"), "NA");
			portcreation.clickOnBackButton();
			CommonUtilities.threadSleep(1000);
			servicehm.put("Location", portcreation.getDataLocationValue());
			boolean nextbuttonflag = portcreation.isNextButtonEnabled();
			logg.info("next button is enabled = " + nextbuttonflag);
			Assert.assertTrue(nextbuttonflag, "next button is not enabled.");
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}

	}

	@Test(priority = 5, groups = { "Customer Portal" })
	public void TC_05_Verify_the_functionality_when_user_click_on_Next_button_in_the_Port_location_section() {
		try {
			CommonUtilities.threadSleep(4000);
			portcreation.clickNextButton();
			logg.info("click on Next button");
			String configurePortstage = portcreation.verifyPortCreationStep("Configure Port");
			Assert.assertEquals("Configure Port section is enabled: " + configurePortstage,
					"Configure Port section is enabled: true");
			String checkoutstage = portcreation.verifyPortCreationStep("Checkout");
			Assert.assertEquals("Checkout Port section is enabled: " + checkoutstage,
					"Checkout Port section is enabled: false");
			boolean nextbuttonflag = portcreation.isNextButtonEnabled();
			logg.info("next button is enabled = " + nextbuttonflag);
			Assert.assertFalse(nextbuttonflag, "next button is enabled with out selecting Port Details.");
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 6, groups = { "Customer Portal" })
	public void TC_06_Verify_price_summary_text__on_the_right_side_of_the_page_before_Port_name_is_selected() {
		try {
			String firstInfoMessage = portcreation.getInfoMessageFromPriceSummary();
			logg.info("FirstInfoMessage = " + firstInfoMessage);

			Assert.assertEquals(firstInfoMessage,
					"Please choose your preferred Name, subscription term and payment option to view pricing summary");

		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 7, groups = { "Customer Portal" })
	public void TC_07_Verify_that_user_should_be_able_to_enter_uniqe_name_and_verify_price_summary_text_after_name_is_enter() {
		try {
			portName = "AUTPort" + CommonUtilities.currentDateStringFormat()
					+ CommonUtilities.generateUniqueAlpahabeticalString(5);
			portcreation.enterPortNameinTextBox(portName);
			logg.info("Enter the Port Name");
			CommonUtilities.threadSleep(1000);
			String secondInfoMessage = portcreation.getInfoMessageFromPriceSummary();
			logg.info("SecondInfoMessage = " + secondInfoMessage);
			Assert.assertEquals(secondInfoMessage,
					"Please choose your preferred subscription term and payment option to view pricing summary");
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 8, groups = { "Customer Portal" })
	public void TC_08_Verify_that_user_should_be_able_to_select_Subscription_Term_and_verify_the_price_summary_text()
			throws IllegalAccessException {
		try {
			subscriptionTerm = StringWords.enterTestData("SubscriptionTermMonth");
			servicehm.put("subscriptionTerm", subscriptionTerm);
			portcreation.selectSubscriptionterm(subscriptionTerm);
			logg.info("select Subscription Terms and Click on it");
			CommonUtilities.threadSleep(1000);
			String thirdInfoMessage = portcreation.getInfoMessageFromPriceSummary();
			logg.info("ThirdInfoMessage = " + thirdInfoMessage);
			CommonUtilities.threadSleep(1000);
			Assert.assertEquals(thirdInfoMessage,
					"Please choose your preferred payment option to view pricing summary");

		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 9, groups = { "Customer Portal" })
	public void TC_09_Verify_that_user_should_be_able_to_select_Payment_Type_and_verify_the_price_summary_text()
			throws IllegalAccessException {
		try {
			paymentType = StringWords.enterTestData("PaymentType");
			servicehm.put("paymentType", paymentType);
			portcreation.selectPayment(paymentType);
			logg.info("Select Payment method");

			String selectedPaymentType = portcreation.getSelectedPaymentType();
			logg.info("Selected Payment Type is ? = " + selectedPaymentType);
			Assert.assertEquals("Payment Type is selected: " + selectedPaymentType,
					"Payment Type is selected: " + paymentType);

		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 10, groups = { "Customer Portal" })
	public void TC_10_Verify_that_by_default_10_GE_should_be_preselected_in_the_Configure_Port_section()
			throws IllegalAccessException {
		try {
			boolean isPortSpeedSelected = portcreation.selectPort_Speed();
			logg.info("Is the port Speed is enabled? = " + isPortSpeedSelected);
			Assert.assertTrue(isPortSpeedSelected, "Port speed is not Enabled for provided Location.");
			String selectedPortSpeed = portcreation.getSelectedPort("10 GE");
			servicehm.put("PortSpeed", selectedPortSpeed);
			if (selectedPortSpeed.equals("10 GE"))
				Assert.assertEquals("Default Payment Speed is selected to : " + selectedPortSpeed,
						"Default Payment Speed is selected to : 10 GE");
			CommonUtilities.threadSleep(5000);
			logg.info("selected port speed is " + selectedPortSpeed);
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 11, groups = { "Customer Portal" })
	public void TC_11_Verify_that_price_summary_should_be_displayed_on_the_right_side_of_the_page_depending_on_the_selection() {
		try {
			String subtotalprice = portcreation
					.getPriceSummaryPriceValueItem("Subtotal for  " + subscriptionTerm + ":");
			logg.info("Sub Total Price = " + subtotalprice);
			totalamount = portcreation.getPriceSummaryPriceValueItem("Total amount to be paid: ");
			logg.info("TotalAmount Price = " + totalamount);
			if ((paymentType.equals("All Upfront") || paymentType.equals("Partial Upfront"))) {
				upfrontPaymentPrice = portcreation.getPriceSummaryPriceValueItem("Upfront Payment: ");
				logg.info("upfromt Payment Price = " + upfrontPaymentPrice);
			}
			if ((paymentType.equals("No Upfront") || paymentType.equals("Partial Upfront"))) {
				monthlyPaymentPrice = portcreation.getPriceSummaryPriceValueItem("Monthly Payment:");
				logg.info("Monthly Payment Price = " + monthlyPaymentPrice);
			}
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 12, groups = { "Customer Portal" })
	public void TC_12_Verify_that_user_should_be_navigated_to_Checkout_page_once_the_Port_details_are_filled_and_click_on_Next_button() {
		try {
			boolean nextbuttonflag = portcreation.isNextButtonEnabled();
			logg.info("next button is enabled = " + nextbuttonflag);
			Assert.assertTrue(nextbuttonflag, "next button is not enabled.");
			portcreation.clickNextButton();
			logg.info("click on Next button");
			CommonUtilities.threadSleep(5000);
			boolean revieworderdetailsFlag = portcreation.isOnReviewOrderDetailsHeaderPage();
			logg.info("Is review order details header displayed? = " + revieworderdetailsFlag);
			Assert.assertTrue(revieworderdetailsFlag, "review order details header  is not displayed");
			String checkoutstage = portcreation.verifyPortCreationStep("Checkout");
			Assert.assertEquals(checkoutstage, "true");
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 13, groups = { "Customer Portal" })
	public void TC_13_Verify_that_user_should_be_able_to_select_existing_Billing_profile() {
		try {
			logg.info("Landing on CheckOut Page");
			portcreation.selectBillingProfile();
			logg.info("select the existing Billing Profile");
			Boolean isBillingSelected = portcreation.verifyBillingProfileSelected();
			logg.info("Is Billing Profile is selected? = " + isBillingSelected);
			Assert.assertTrue(isBillingSelected, "Billing Profile is not selected for one of the service.");
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 14, groups = { "Customer Portal" })
	public void TC_14_Verify_that_user_should_be_able_to_Click_on_new_create_Billing_profile() {
		try {
			portcreation.clickonAddBillingProfileButton();
			boolean createbillingprofheaderflag = billingpage.verifyCreateBillingProfileHeader();
			logg.info("Create Billing Profile Header is displayed = " + createbillingprofheaderflag);
			Assert.assertTrue(createbillingprofheaderflag, "Create  Billing Profile Header is not displayed");
			CommonUtilities.threadSleep(1000);
			portcreation.clickOnBackButton();
			CommonUtilities.threadSleep(1000);
			boolean revieworderdetailsFlag = portcreation.isOnReviewOrderDetailsHeaderPage();
			logg.info("Is review order details header displayed? = " + revieworderdetailsFlag);
			Assert.assertTrue(revieworderdetailsFlag, "review order details header  is not displayed");

		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 15, groups = { "Customer Portal" })
	public void TC_15_Verify_that_Port_details_along_with_Total_cost_should_be_displayed_in_the_Checkout_page_of_Port_create_flow() {
		try {
			String totalAmountOnCheckOutPage = portcreation.totalAmountOnCheckOutPage();
			logg.info("CheckOut Amount = " + totalAmountOnCheckOutPage);
			logg.info("Total Amount = " + totalamount);

			String portNameFromConfigureSection = portcreation.getPortNameFromConfigurePage();
			logg.info("Port Name Displayed on the configure Port Section = " + portNameFromConfigureSection);
			Assert.assertEquals("Port name is equal to:= " + portNameFromConfigureSection,
					"Port name is equal to:= " + portName);
			boolean amountFlag = totalAmountOnCheckOutPage.contains(totalamount);
//			Assert.assertEquals(amountFlag, true);
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 16, groups = { "Customer Portal" })
	public void TC_16_Verify_that_user_should_accept_the_Master_Service_Agreement_for_creating_Port_order() {
		try {
			boolean isMSAselected = virtualrouter.verifyServiceAggrementSelected();
			logg.info("verify the MS Agreement checkbox  selected  Before Click: =" + isMSAselected);
			Assert.assertEquals("Port name is equal to:= " + isMSAselected, "Port name is equal to:= false");
			portcreation.clickOnIAggreementBtn();
			logg.info("Select Master Services Agreement check Box");
			CommonUtilities.threadSleep(1000);
			isMSAselected = virtualrouter.verifyServiceAggrementSelected();
			logg.info("verify the MS Agreement checkbox  selected  After Click: =" + isMSAselected);
			Assert.assertEquals("Port name is equal to:= " + isMSAselected, "Port name is equal to:= true");
			boolean orderbuttonflag = portcreation.isOrderBtnEnabled();
			logg.info("order button is enabled = " + orderbuttonflag);
			Assert.assertTrue(orderbuttonflag, "order button is not enabled.");
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 17, groups = { "Customer Portal" })
	public void TC_17_Verify_that_user_should_be_able_to_create_new_port_from_Checkout_Page() {
		try {
			portcreation.clickOnorderBtutton();
			logg.info("click On Order Button");
			String notificationmessage = portcreation.getSuccessNotificationMessage();
			logg.info("Displayed Notification message is   = " + notificationmessage);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(notificationmessage, "Port created successfully!");
			logg.info("Port creation successful message");
			CommonUtilities.threadSleep(2000);
			if (portcreation.isOrderBtnEnabled()) {
				portcreation.clickOnorderBtutton();
				CommonUtilities.threadSleep(2000);
				notificationmessage = portcreation.getErrorNotificationMessage();
				Assert.assertEquals(notificationmessage, "Port created successfully!");
			}
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 18, groups = { "Customer Portal", "Active Port" })
	public void TC_18_Verify_that_Port_status_should_go_to_Ready_To_Patch() {
		try {
//			portName = "AUTPort17JulaAoRi";
			portcreation = dashboard.clickOnServicesForPort();
			logg.info("Landing On Services Dashboard");
			boolean serviceheaderflag = servicecreation.isOnSevicePage();
			logg.info("service header is available = " + serviceheaderflag);
			Assert.assertTrue(serviceheaderflag, "service header is not displayed.");
			CommonUtilities.threadSleep(2000);

			String status = servicedashboard.verifyServiceStatus("Ports", portName, "Ready To Patch", 5);
			orderNumber = servicedashboard.getServiceRecordFieldValue("Ports", portName, "Service ID");
			logg.info("Order Number of the Port is:- " + orderNumber);
			Assert.assertEquals("Port status is := " + status, "Port status is := Ready To Patch");
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}

	}

	@Test(priority = 19, groups = { "Customer Portal" })
	public void TC_19_Verify_that_service_id_is_not_generated_for_the_port_with_design_status_in_the_platform() {
		try {
			boolean userprofileflag = dashboard.isOnHomePage();
			logg.info("login is successful = " + userprofileflag);
			Assert.assertTrue(userprofileflag, "User profile is displayed and login is successful.");
			portcreation = dashboard.clickOnServicesForPort();

			logg.info("**************** Start New Port Creation *********************");
			boolean portcreationservice = servicecreation.verifyservicebutondisplayed("Port");
			logg.info("Is the Port Creation Available? = " + portcreationservice);
			Assert.assertTrue(portcreationservice, "Create Port Button is not displayed");

			servicecreation.clickonService("Port");

			boolean createPortHeaderflag = virtualrouter.verifyCreatePortHeader();
			logg.info("Create Port Header is available = " + createPortHeaderflag);
			Assert.assertTrue(createPortHeaderflag, "Create Port Header is not available.");

			boolean portLocationInputFlag = portcreation.isPortLocationEnabled();
			logg.info("Port Location textbox is enabled? = " + portLocationInputFlag);
			Assert.assertTrue(portLocationInputFlag, "Port Location textbox is disabled to enter Location");
			String country = "India";
			portcreation.selectTheCountryfromDropDown(country);
			logg.info("Select country from dropdown");
			String selectedActualCountry = portcreation.getcountrydropdownvalue();
			boolean countryDropdownvalueflag = selectedActualCountry.equalsIgnoreCase(country);
			logg.info("Country value is successfully selected to  = " + country + " - " + countryDropdownvalueflag);
			Assert.assertTrue(countryDropdownvalueflag, "Country value is not selected to" + country);

			portcreation.selectPortSpeedEnabledLocation(StringWords.enterTestData("ActivePortLocation"), "NA");
			CommonUtilities.threadSleep(2000);
			boolean portConfigureStageFlag = portcreation.isPortConfigureStageEnabled();
			logg.info("Port Location textbox is enabled? = " + portConfigureStageFlag);
			Assert.assertTrue(portConfigureStageFlag, "Port Configure stage is disabled to Create Port");

			String portNameDesign = "TestPortDesign" + CommonUtilities.currentDateStringFormat()
					+ CommonUtilities.generateUniqueAlpahabeticalString(3);
			portcreation.enterPortNameinTextBox(portNameDesign);
			portcreation.selectSubscriptionterm(StringWords.enterTestData("SubscriptionTermMonth"));
			portcreation.selectPayment(StringWords.enterTestData("PaymentType"));
			boolean isPortSpeedSelected = portcreation.selectPort_Speed();
			logg.info("Is the port Speed is enabled? = " + isPortSpeedSelected);
			Assert.assertTrue(isPortSpeedSelected, "Port speed is not Enabled for provided Location.");
			CommonUtilities.threadSleep(5000);

			boolean configPortNextButtonFlag = virtualrouter.isNextButtonVisible();
			logg.info("Is the Next button visible? = " + configPortNextButtonFlag);
			Assert.assertTrue(configPortNextButtonFlag, "Next Button is not Enabled after Data selected.");

			virtualrouter.clickOnSaveAndNextBtn();
			CommonUtilities.threadSleep(2000);

			boolean revieworderdetailsFlag = portcreation.isOnReviewOrderDetailsHeaderPage();
			logg.info("Is review order details header displayed? = " + revieworderdetailsFlag);
			Assert.assertTrue(isPortSpeedSelected, "review order details header  is not displayed");

			portcreation.selectBillingProfile();
			CommonUtilities.scrollByVisibilityOfElements(400);
			portcreation.clickOnIAggreementBtn();

			boolean orderbuttonflag = virtualrouter.isOrderBtnEnabled();
			logg.info("order button is enabled = " + orderbuttonflag);
			Assert.assertTrue(orderbuttonflag, "order button is not enabled.");

			portToPortVcConnection.clickonBackIconheaderButton("Port");
			CommonUtilities.threadSleep(1000);
			portcreation = dashboard.clickOnServicesForPort();
			logg.info("Landing On Services Dashboard");
			boolean serviceheaderflag = servicecreation.isOnSevicePage();
			logg.info("service header is available = " + serviceheaderflag);
			Assert.assertTrue(serviceheaderflag, "service header is not displayed.");
			CommonUtilities.threadSleep(2000);

			String status = servicedashboard.verifyServiceStatus("Ports", portNameDesign, "Design", 5);
			Assert.assertEquals("Port status is := " + status, "Port status is := Design");
			serviceIDNumber = servicedashboard.getServiceRecordFieldValue("Ports", portName, "Service ID");
			logg.info("Service id is set to " + serviceIDNumber);
//		# serviceID.isEmpty()
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

//
	@Test(priority = 20, dependsOnMethods = {
			"TC_17_Verify_that_user_should_be_able_to_create_new_port_from_Checkout_Page" }, groups = {
					"Customer Portal" })
	public void TC_20_Verify_that_duplicate_port_names_are_not_allowed_during_port_creation() {
		try {
			boolean userprofileflag = dashboard.isOnHomePage();
			logg.info("login is successful = " + userprofileflag);
			Assert.assertTrue(userprofileflag, "User profile is displayed and login is successful.");
			portcreation = dashboard.clickOnServicesForPort();

			logg.info("**************** Start New Port Creation ******************");
			boolean portcreationservice = servicecreation.verifyservicebutondisplayed("Port");
			logg.info("Is the Port Creation Available? = " + portcreationservice);
			Assert.assertTrue(portcreationservice, "Create Port Button is not displayed");

			servicecreation.clickonService("Port");
			boolean createportHeader = portcreation.verifyCreatePortHeader();
			logg.info("Create Port Header displayed = " + createportHeader);
			Assert.assertTrue(createportHeader, "Create Port header is not displayed");

			String country = "India";
			portcreation.selectTheCountryfromDropDown(country);
			logg.info("Select country from dropdown");
			String selectedActualCountry = portcreation.getcountrydropdownvalue();
			boolean countryDropdownvalueflag = selectedActualCountry.equalsIgnoreCase(country);
			logg.info("Country value is successfully selected to  = " + country + " - " + countryDropdownvalueflag);
			Assert.assertTrue(countryDropdownvalueflag, "Country value is not selected to" + country);

			portcreation.clickOnSearchLocationDropdown();
			logg.info("Search DC Location from given list");
			portcreation.clickOnSearchResult(StringWords.enterTestData("ActivePortLocation"));
			logg.info("Select any Location from Given List");
			boolean nextbuttonflag = portcreation.isNextButtonEnabled();
			logg.info("next button is enabled = " + nextbuttonflag);
			Assert.assertTrue(nextbuttonflag, "next button is not enabled.");
			portcreation.clickNextButton();
			logg.info("click on Next button");
			CommonUtilities.threadSleep(1000);

			String duplicateportName = portName;
			portcreation.enterPortNameinTextBox(duplicateportName);
			CommonUtilities.threadSleep(1000);
			String errormessage = portcreation.getportnameErrorMessage();
			logg.info("Error Message when duplicate name entered= " + errormessage);
			Assert.assertEquals(errormessage, "Port Name already in use. Please use a different name.");
			portcreation.enterPortNameinTextBox("TestPort" + CommonUtilities.currentDateStringFormat()
					+ CommonUtilities.generateUniqueAlpahabeticalString(30));
			CommonUtilities.threadSleep(1000);
			errormessage = portcreation.getportnameErrorMessage();
			logg.info("Error Message when exceeds Characters entered= " + errormessage);
			Assert.assertEquals(errormessage, "Port name must be at most 32 characters");

		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}

	}

	@Test(priority = 21, dependsOnMethods = { "TC_18_Verify_that_Port_status_should_go_to_Ready_To_Patch" }, groups = {
			"Customer Portal" })
	public void TC_21_Verify_Request_cross_connect_functionality() {
		try {
			dashboard.clickOnPolarinLogo();
			CommonUtilities.threadSleep(1000);
			boolean userprofileflag = dashboard.isOnHomePage();
			logg.info("login is successful = " + userprofileflag);
			Assert.assertTrue(userprofileflag, "User profile is displayed and login is successful.");
			portcreation = dashboard.clickOnServicesForPort();
			boolean serviceheaderflag = servicecreation.isOnSevicePage();
			logg.info("service header is available = " + serviceheaderflag);
			Assert.assertTrue(serviceheaderflag, "service header is not displayed.");

			servicedashboard.selectTheServices("Ports");
			servicedashboard.clickOnClearButton();
			servicedashboard.clickOnStatusFilterButton();
			servicedashboard.filterbyMenuStatus("Ready To Patch");
			CommonUtilities.threadSleep(1000);
			servicedashboard.enterPortSearchValue(portName);
			servicedashboard.waittillServiceDBPageLoading();

			servicedashboard.performCrossConnectForLAGPort("Port");
			String notificationmessage = servicedashboard.getSuccessNotificationMessage();
			logg.info("Displayed Notification message is   = " + notificationmessage);
			Assert.assertEquals(notificationmessage, "Cross connect request placed successfully");
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}

	}

	@Test(priority = 22, dependsOnMethods = { "TC_18_Verify_that_Port_status_should_go_to_Ready_To_Patch" }, groups = {
			"Customer Portal" })
	public void TC_22_Verify_the_functionality_of_Download_LOA_option() {
		try {
			CommonUtilities.refreshPage();
			CommonUtilities.threadSleep(500);
			dashboard.clickOnPolarinLogo();
			CommonUtilities.threadSleep(1000);
			boolean userprofileflag = dashboard.isOnHomePage();
			logg.info("login is successful = " + userprofileflag);
			Assert.assertTrue(userprofileflag, "User profile is displayed and login is successful.");
			dashboard.clickOnServicesForPort();
			boolean serviceheaderflag = servicecreation.isOnSevicePage();
			logg.info("service header is available = " + serviceheaderflag);
			Assert.assertTrue(serviceheaderflag, "service header is not displayed.");

			servicedashboard.selectTheServices("Ports");
			servicedashboard.clickOnClearButton();
			servicedashboard.clickOnStatusFilterButton();
			CommonUtilities.threadSleep(1000);
			servicedashboard.filterbyMenuStatus("Ready To Patch");
			CommonUtilities.threadSleep(1000);
			servicedashboard.enterPortSearchValue(portName);
			servicedashboard.waittillServiceDBPageLoading();
			servicedashboard.downloadLOA();
			CommonUtilities.threadSleep(2000);
			String notificationmessage = servicedashboard.getSuccessNotificationMessage();
			logg.info("Displayed Notification message is   = " + notificationmessage);
			Assert.assertEquals(notificationmessage, "Email sent successfully.");
			CommonUtilities.threadSleep(1000);
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}

	}

//Admin Portal Starts Here
	@Test(priority = 23, groups = { "AdminPortal" })
	public void TC_01_Verify_that_Port_details_in_Admin_Portal_for_Ready_To_Patch() {
		adminMainTab = LaunchURL_NewTab(prop.getProperty("adminportalurl"));
		boolean adminportalhomeflag = adminportalloginpage.isOnAdminPortalHomePage();
		logg.info("Organisation Profile header is displayed = " + adminportalhomeflag);
		Assert.assertTrue(adminportalhomeflag, "Organisation Profile header is not displayed in login page");

		adminportalloginpage.clickonServiceLink();
		boolean adminportalserviceflag = adminportalloginpage.isOnAdminPortalServicePage();
		logg.info("service header is displayed = " + adminportalserviceflag);
		Assert.assertTrue(adminportalserviceflag, "service header is not displayed in login page");

		serviceIDNumber = "pbgnx11g2357";
		adminportalloginpage.enterServiceID(serviceIDNumber);
		CommonUtilities.threadSleep(1000);

		// TC01 verify ready2 Patch status functionality
		String admin_serviceStatus = adminportalloginpage.getColumnFieldValue("Status");
		logg.info("Admin Portal Port Status Value is " + admin_serviceStatus);
//		Assert.assertEquals("Admin Portal Port Status should be set to: " + admin_serviceStatus,
//				"Admin Portal Port Status should be set to: Ready To Patch");

		// TC02 verify Service details anf verify functionality

		String admin_portName = adminportalloginpage.getColumnFieldValue("Name");
		String admin_ServiceID = adminportalloginpage.getColumnFieldValue("Service ID");
		String admin_term = adminportalloginpage.getColumnFieldValue("Term");
		String admin_CAFNumber = adminportalloginpage.getColumnFieldValue("CAF");

		logg.info("Admin Portal Port Name: " + admin_portName + " and Service id: " + admin_portName
				+ " and Service Term: " + admin_term + " and CAF Number: " + admin_CAFNumber);

		orderNumber = adminportalloginpage.getColumnFieldValue("Order");

		// TC03 Export Functionality
		adminportalloginpage.clickonExportButton();
		String home = System.getProperty("user.home");
		logg.info(" User Home path is " + home);
		String downloadDir = home + "\\Downloads";
		String fileName = "Port-" + CommonUtilities.DateIncrementDecrement("", 0, "yyyy-MM-dd");
		adminportalloginpage.validateExcelFile(downloadDir, fileName);

		// TC04 Delete functionality
//		perform delete and verify status to Deleted.

		// TC04 verify Enablement of button
		adminportalloginpage.clickonServiceRecord("Port");
		CommonUtilities.threadSleep(500);
		boolean updateBillingSubscriptionFlag = adminportalloginpage.isUpdateBillingSubscriptionButton_Enabled();
		logg.info("Is the update Billing Subscription  button visible? = " + updateBillingSubscriptionFlag);
		Assert.assertTrue(updateBillingSubscriptionFlag,
				"update Billing Subscription  Button is not Enabled after Data selected.");

		// TC05 Enter all mandatory Details
		adminportalloginpage.clickonupdateBillingandSubscriptionDetButton();
		boolean updateBillSubsDetailFlag = adminportalloginpage.isOnUpdateBillingSubsDetailsHeaderPage();
		logg.info("Is the update Billing Subscription  Header visible? = " + updateBillSubsDetailFlag);
		Assert.assertTrue(updateBillingSubscriptionFlag,
				"update Billing Subscription  Header is not Enabled after Data selected.");
		String admin_billingStartDate = CommonUtilities.DateIncrementDecrement("", 0, "dd-MM-yyyy");
		String admin_poStartDate = CommonUtilities.DateIncrementDecrement("", 0, "dd-MM-yyyy");
		String admin_poEndDate = CommonUtilities.DateIncrementDecrement("", 2, "dd-MM-yyyy");
		String admin_poNumber = CommonUtilities.generateRandomNumericValue(5);

		String poDocument = "gstCertificate.jpg";
		boolean updateBillSubs_updateButtonFlag = adminportalloginpage.isupdateBillSubsDet_updateButton_Enabled();
		logg.info("Is the update Billing Subscription details Update button visible? = "
				+ updateBillSubs_updateButtonFlag);
		Assert.assertFalse(updateBillSubs_updateButtonFlag,
				"update Billing Subscription details Update button is Enabled before Data selected.");

		adminportalloginpage.enterBillingStartDate(admin_billingStartDate);
		adminportalloginpage.enterPONumber(admin_poNumber);
		adminportalloginpage.enterPOStartDate(admin_poStartDate);
		adminportalloginpage.enterPOEndDate(admin_poEndDate);
		CommonUtilities.threadSleep(1000);

		CommonUtilities.uploadDocument("poDocument",
				System.getProperty("user.dir") + fs + "TestDataDocs" + fs + poDocument);
		CommonUtilities.threadSleep(1000);

		logg.info("Admin Portal StartDate : " + admin_poStartDate + " End Date : " + admin_poEndDate
				+ " Billing Start Date : " + admin_billingStartDate + " Po Number : " + admin_poNumber
				+ " Po Document : " + poDocument);
		CommonUtilities.threadSleep(1000);
		updateBillSubs_updateButtonFlag = adminportalloginpage.isupdateBillSubsDet_updateButton_Enabled();
		logg.info("Is the update Billing Subscription details Update button visible? = "
				+ updateBillSubs_updateButtonFlag);
		Assert.assertTrue(updateBillSubs_updateButtonFlag,
				"update Billing Subscription details Update button is not Enabled after Data selected.");
		adminportalloginpage.clickonBillSubDet_updateButton();
		CommonUtilities.threadSleep(1000);
//		String billingUpdate_Notification = portcreation.getSuccessNotificationMessage();
//		logg.info("Billing Updated notication message is displayed" + billingUpdate_Notification);
		// TC06 verify the Billing details for after Update
		CommonUtilities.threadSleep(1000);
		String poStartDate_output = adminportalloginpage.getpoStartDate();
		String poEndDate_output = adminportalloginpage.getpoEndDate();
		String BillingStartDate_output = adminportalloginpage.getbillingStartDate();
		String poNumber_output = adminportalloginpage.getpoNumber();
		String poDocument_output = adminportalloginpage.getpoDocument();
		logg.info("Admin Portal StartDate Output: " + poStartDate_output + " End Date Output: " + poEndDate_output
				+ " Billing Start Date Output: " + BillingStartDate_output + " Po Number Output: " + poNumber_output
				+ " Po Document Output: " + poDocument_output);

//		Assert.assertEquals(
//				"Admin Portal StartDate Output: " + poStartDate_output + " End Date Output: " + poEndDate_output
//						+ " Billing Start Date Output: " + BillingStartDate_output + " Po Number Output: "
//						+ poNumber_output + " Po Document Output: " + poDocument_output,
//				"Admin Portal StartDate Output: " + admin_poStartDate + " End Date Output: " + admin_poEndDate
//						+ " Billing Start Date Output: " + admin_billingStartDate + " Po Number Output: "
//						+ admin_poNumber + " Po Document Output: " + poDocument);
		// TC07 verify the Billing details for after Update Service DashBoard
		CommonUtilities.refreshPage();
		adminportalloginpage.enterServiceID(serviceIDNumber);
		CommonUtilities.threadSleep(1000);
		String adminService_billingStartDate = adminportalloginpage.getColumnFieldValue("Billing Start Date");
		String adminService_poNumber = adminportalloginpage.getColumnFieldValue("PO Number");
		String adminService_billingStatus = adminportalloginpage.getColumnFieldValue("Billing Status");
		logg.info("Billing Start Date " + adminService_billingStartDate + " PO Number" + adminService_poNumber
				+ " Billing Status: " + adminService_billingStatus);
		CommonUtilities.threadSleep(1000);
		// TC08_ Start Billing
//		verify status to started state
	}

//	@Test(priority = 24, groups = { "AdminPortal" })
//	public void TC_02_Verify_that_all_ports_records_are_displayed_in_Admin_portal_service_page_with_correct_details_as_per_the_platform() {
//		try {
//		} catch (Exception e) {
//			Assert.fail("Testcase failed with Exception Error", e);
//		}
//	}
//
//	@Test(priority = 25, groups = { "AdminPortal" })
//	public void TC_03_Export_functionality_check_in_Admin_portal_for_newly_created_port_and_validate_the_data_in_the_excel_sheet() {
//		try {
//		} catch (Exception e) {
//			Assert.fail("Testcase failed with Exception Error", e);
//		}
//	}
//
//	@Test(priority = 26, groups = { "AdminPortal" })
//	public void TC_04_Verify_that_user_is_able_to_delete_the_newly_created_port_from_Admin_portal() {
//		try {
//		} catch (Exception e) {
//			Assert.fail("Testcase failed with Exception Error", e);
//		}
//	}
//
//	@Test(priority = 27, groups = { "AdminPortal" })
//	public void TC_05_Verify_that_user_not_able_to_delete_the_connected_port_ports_included_in_connections__from_Admin_portal() {
//		try {
//		} catch (Exception e) {
//			Assert.fail("Testcase failed with Exception Error", e);
//		}
//	}
//
//	@Test(priority = 29, groups = { "AdminPortal" })
//	public void TC_06_Verify_that_port_status_shows_Live_in_Platform_Admin_portal() {
//		try {
//		} catch (Exception e) {
//			Assert.fail("Testcase failed with Exception Error", e);
//		}
//	}
//
//	@Test(priority = 30, groups = { "AdminPortal" })
//	public void TC_07_Verify_that_user_is_able_to_start_the_billing_from_Admin_portal_for_the_Ready_to_patch_live_port() {
//		try {
//		} catch (Exception e) {
//			Assert.fail("Testcase failed with Exception Error", e);
//		}
//	}
//
//	@Test(priority = 31, groups = { "AdminPortal" })
//	public void TC_08_Verify_that_user_is_able_to_update_the_billing_profile_details_like_PO_number_PO_Start_date_PO_end_date_etc() {
//		try {
//		} catch (Exception e) {
//			Assert.fail("Testcase failed with Exception Error", e);
//		}
//	}
//
//	@Test(priority = 32, groups = { "AdminPortal" })
//	public void TC_09_Verify_that_use_is_able_to_validate_the_updated_billing_profile_details_like_PO_number_PO_Start_date_PO_end_date_etc_on_ports_dashboard() {
//		try {
//		} catch (Exception e) {
//			Assert.fail("Testcase failed with Exception Error", e);
//		}
//	}
//
//	@Test(priority = 33, groups = { "AdminPortal" })
//	public void TC_10_Verify_user_is_able_to_Update_the_billing_details_in_admin_portal() {
//		try {
//		} catch (Exception e) {
//			Assert.fail("Testcase failed with Exception Error", e);
//		}
//	}
//
//	@Test(priority = 34, groups = { "AdminPortal" })
//	public void TC_11_Verify_user_is_able_to_Start_the_billing_in_admin_portal() {
//		try {
//		} catch (Exception e) {
//			Assert.fail("Testcase failed with Exception Error", e);
//		}
//	}
//
//	@Test(priority = 35, groups = { "AdminPortal" })
//	public void TC_12_Verify_Invoice_in_admin_portal_once_billing_is_started_and_invoice_is_generaeted_from_BRM() {
//		try {
//		} catch (Exception e) {
//			Assert.fail("Testcase failed with Exception Error", e);
//		}
//	}
//
//	@Test(priority = 36, groups = { "AdminPortal" })
//	public void TC_13_Verify_Invoice_in_admin_portal_once_billing_is_started_and_invoice_is_generaeted_from_BRM_for_Sales_assist_order() {
//		try {
//		} catch (Exception e) {
//			Assert.fail("Testcase failed with Exception Error", e);
//		}
//	}

//	Active Port Starts Here
	@Test(priority = 37, dependsOnMethods = { "TC_18_Verify_that_Port_status_should_go_to_Ready_To_Patch" }, groups = {
			"Active Port" })

	public void TC_01_Verify_that_NTU_is_created_in_in_Active_Port_for_Ready_To_Patch_port() {
		try {
			acpMainTab = LaunchURL_NewTab(prop.getProperty("activeporturl"));
			boolean acploginflag = acploginpage.isOnACPLoginPage();
			logg.info("login title is displayed = " + acploginflag);
			Assert.assertTrue(acploginflag, "Lightstorm activeport Login page is not displayed");
			acploginpage.enter_acploginCredentials(prop.getProperty("activeportUsername"),
					prop.getProperty("activeportPassword"));
			CommonUtilities.threadSleep(2000);
			acploginpage.clickOnACPSignInButton();

			CommonUtilities.threadSleep(5000);
			boolean userprofileflag = acploginpage.isOnACPHomePage();
			logg.info("Active Port login is successful = " + userprofileflag);
			Assert.assertTrue(userprofileflag, "Active port home page is not displayed and login is successful.");

			CommonUtilities.threadSleep(2000);
			acploginpage.selectandSelectOrgName(prop.getProperty("organisationName"));
			CommonUtilities.threadSleep(2000);
			acploginpage.searchNTUName(portName);

			boolean networkTilesViewflag = acploginpage.isOnNetworkTilesViewPage();
			logg.info("Network Tiles View Header is displayed = " + networkTilesViewflag);
			Assert.assertTrue(networkTilesViewflag, "Network Tiles View Header is not displayed.");

			CommonUtilities.threadSleep(1000);
			String ntustatus = acploginpage.getNTUServiceStatus("NTU Status");
			logg.info("Ntu status of the service = " + portName + " is " + ntustatus);
			Assert.assertEquals("Ntu status of the service = " + portName + " is " + ntustatus,
					"Ntu status of the service = " + portName + " is Active");

		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 38, dependsOnMethods = { "TC_18_Verify_that_Port_status_should_go_to_Ready_To_Patch" }, groups = {
			"Active Port" })
	public void TC_02_Verify_that_NTU_is_Available_in_Dashboard_drop_down() {
		try {
			acploginpage.NavigateNTUMenu();
			CommonUtilities.threadSleep(1000);
			boolean ntuHeaderflag = acploginpage.isOnNTUsHeaderPage();
			logg.info("NTUs Header is displayed = " + ntuHeaderflag);
			Assert.assertTrue(ntuHeaderflag, "NTUs Header is not displayed.");
			CommonUtilities.threadSleep(1000);
			boolean serviceRecordFlag = acploginpage.verifyNTUServiceName(portName);
			logg.info("Service name is displayed in NTUs Table= " + serviceRecordFlag);
			Assert.assertTrue(serviceRecordFlag, "Service name is not displayed in NTUs Table.");
			CommonUtilities.threadSleep(1000);
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

//	CRM Code Starts Here
	@Test(priority = 39, groups = { "CRM Application" })
	public void TC_01_Verify_that_updated_details_from_Admin_portal_are_reflected_correctly_in_CRM() {
		try {
			crmMainTab = LaunchURL_NewTab(prop.getProperty("crmurl"));
			boolean crmloginflag = crmloginpage.isOnCRMLoginPage();
			logg.info("CRM login header is displayed = " + crmloginflag);
			Assert.assertTrue(crmloginflag, "CRM Login page is not displayed");
			crmloginpage.enter_logincrmCredentials(prop.getProperty("crmUsername"), prop.getProperty("crmPassword"));
			CommonUtilities.threadSleep(2000);
			crmloginpage.clickOnCRMSignInButton();
			CommonUtilities.threadSleep(2000);

			boolean crmHomePageflag = crmHomePage.isOnCRMHomePage();
			logg.info("CRM Home Page is displayed = " + crmHomePageflag);
			Assert.assertTrue(crmHomePageflag, "CRM Home Page is not displayed");

			orderNumber = "00011179";
			crmHomePage.clickonMainTab("Orders");
			crmOrdersPage.ClickOrderRecord(orderNumber, "Order Number");
			crmOrdersPage.clickonDetailsButton();
			String detailsTabFlag = crmOrdersPage.isDetailsPageSelected();
			logg.info("Details Tab is selected in Orders Page");
			Assert.assertEquals("Details Tab is selected: " + detailsTabFlag, "Details Tab is selected: true");
			String crmpoNumber = crmOrdersPage.getPONumber();
			String crmStartBillingDate = crmOrdersPage.getStartBillingDate();
			String crmStartDate = crmOrdersPage.getPODate();
			String crmEndDate = crmOrdersPage.getPOEndDate();

			logg.info("PO Number" + crmpoNumber + "poDate" + crmStartDate + "poEndDate" + crmEndDate
					+ "Billing Start Date" + crmStartBillingDate);
//			Assert.assertEquals(
//					"PO Number" + crmpoNumber + "poDate" + crmStartDate + "poEndDate" + crmEndDate
//							+ "Billing Start Date" + crmStartBillingDate,
//					"PO Number" + AdminPONumber + "poDate" + AdminStartDate + "poEndDate" + AdminEndDate
//							+ "Billing Start Date" + AdminStartBillingDate);

//			crmoptyPage.createNewOpty(servicehm.get("optyName").toString());
//			crmoptyPage.createProductinCRM("Port", servicehm);
//			String quoteStatus = crmoptyPage.verifyPrintQuoteStatus();
////
//			logg.info("Print Quote Status is: =" + quoteStatus);
//			logg.info("Service Name is : "+servicehm.get("crmServiceName").toString());
//			logg.info("Service Name is : "+servicehm.get("crmServiceName").toString());
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 40, groups = { "CRM Application" })
	public void TC_02_Verify_the_Port_details_in_CRM_order_for_Ready_to_patch_ports() {
		try {
			String detailsTabFlag = crmOrdersPage.isDetailsPageSelected();
			logg.info("Details Tab is selected in Orders Page");
			Assert.assertEquals("Details Tab is selected: " + detailsTabFlag, "Details Tab is selected: true");
			String crmOrderNumber = crmOrdersPage.getOrderNumber();
			String crmAccountName = crmOrdersPage.getAccountName();
			String crmOrchPlan = crmOrdersPage.getOptyName();
			String crmOptyName = crmOrdersPage.getOrchestrationPlan();
			logg.info("Order Number" + crmOrderNumber + "Account Name" + crmAccountName + "Orch Plan" + crmOrchPlan
					+ "Opty Name" + crmOptyName);
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 41, groups = { "CRM Application" })
	public void TC_03_Verify_the_order_products_in_CRM_for_port_product() {
		try {
			crmOrdersPage.clickonRelatedButton();
			String relatedTabFlag = crmOrdersPage.isRelatedPageSelected();
			logg.info("Related Tab is selected in Orders Page");
			Assert.assertEquals("Related Tab is selected: " + relatedTabFlag, "Related Tab is selected: true");
			crmOrdersPage.verifyPortProduct();
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 42, groups = { "CRM Application" })
	public void TC_04_CRM_Product_type_Verify_the_Port_Details() {
		try {
			crmOrdersPage.clickonRelatedButton();
			String relatedTabFlag = crmOrdersPage.isRelatedPageSelected();
			logg.info("Related Tab is selected in Orders Page");
			Assert.assertEquals("Related Tab is selected: " + relatedTabFlag, "Related Tab is selected: true");
			crmOrdersPage.verifyPortProductDetails("Port");
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 43, groups = { "CRM Application" })
	public void TC_76_Verify_Order_is_created_in_CRM_for_PORT_product_ordered_from_Portal() {
		try {
			boolean crmHomePageflag = crmHomePage.isOnCRMHomePage();
			logg.info("CRM Home Page is displayed = " + crmHomePageflag);
			Assert.assertTrue(crmHomePageflag, "CRM Home Page is not displayed");

			crmHomePage.clickonMainTab("Orders");
			crmOrdersPage.ClickOrderRecord(orderNumber, "Order Number");
			crmOrdersPage.clickonDetailsButton();
			CommonUtilities.threadSleep(1000);
			String detailsTabFlag = crmOrdersPage.isDetailsPageSelected();
			logg.info("Details Tab is selected in Orders Page");
			Assert.assertEquals("Details Tab is selected: " + detailsTabFlag, "Details Tab is selected: true");
			String crmOrderNumber = crmOrdersPage.getOrderNumber();
			String crmOrderPathState = crmOrdersPage.getOrderPathActiveState();
			logg.info("Order Number:- " + crmOrderNumber + " Path Active state is " + crmOrderPathState);
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 44, groups = { "CRM Application" })
	public void Verify_CAF_is_created_in_CRM_for_PORT_product_ordered_from_Portal() {
		try {
			boolean crmHomePageflag = crmHomePage.isOnCRMHomePage();
			logg.info("CRM Home Page is displayed = " + crmHomePageflag);
			Assert.assertTrue(crmHomePageflag, "CRM Home Page is not displayed");

			crmHomePage.clickonMainTab("Orders");
			crmOrdersPage.ClickOrderRecord(orderNumber, "Order Number");
			crmOrdersPage.clickonDetailsButton();
			CommonUtilities.threadSleep(1000);
			String detailsTabFlag = crmOrdersPage.isDetailsPageSelected();
			logg.info("Details Tab is selected in Orders Page");
			Assert.assertEquals("Details Tab is selected: " + detailsTabFlag, "Details Tab is selected: true");
			String crmOrderCAFName = crmOrdersPage.getOrderCAFName();
			logg.info("CAF is generated " + crmOrderCAFName);
			if (!crmOrderCAFName.isEmpty()) {
				crmOrdersPage.clickonOrderLinkCAFName();
				String crmCAFDetailsNumber = crmOrdersPage.getCAFDetailsNumber();
				logg.info(" CAF Details number is " + crmCAFDetailsNumber);
			} else {
				Assert.fail("Opportunity is not generated for the Order");
			}
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 45, groups = { "CRM Application" })
	public void TC_76_Verify_Opty_is_created_in_CRM_for_PORT_product_ordered_from_Portal() {
		try {
			boolean crmHomePageflag = crmHomePage.isOnCRMHomePage();
			logg.info("CRM Home Page is displayed = " + crmHomePageflag);
			Assert.assertTrue(crmHomePageflag, "CRM Home Page is not displayed");

			crmHomePage.clickonMainTab("Orders");
			crmOrdersPage.ClickOrderRecord(orderNumber, "Order Number");
			crmOrdersPage.clickonDetailsButton();
			CommonUtilities.threadSleep(1000);
			String detailsTabFlag = crmOrdersPage.isDetailsPageSelected();
			logg.info("Details Tab is selected in Orders Page");
			Assert.assertEquals("Details Tab is selected: " + detailsTabFlag, "Details Tab is selected: true");
			String crmOptyName = crmOrdersPage.getOptyName();
			logg.info("Opty is generated " + crmOptyName);
			if (!crmOptyName.isEmpty()) {
				crmOrdersPage.clickonOrderLinkOptyName();
				String crmOptyPathState = crmOrdersPage.getOrderPathSelectedState();
				logg.info(" Opportunity Path Active state is " + crmOptyPathState);
			} else {
				Assert.fail("Opportunity is not generated for the Order");
			}
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}

	}

	@Test(priority = 46, groups = { "CRM Application" })
	public void TC_76_Verify_Quote_is_created_in_CRM_for_PORT_product_ordered_from_Portal() {
		try {
			boolean crmHomePageflag = crmHomePage.isOnCRMHomePage();
			logg.info("CRM Home Page is displayed = " + crmHomePageflag);
			Assert.assertTrue(crmHomePageflag, "CRM Home Page is not displayed");

			crmHomePage.clickonMainTab("Orders");
			crmOrdersPage.ClickOrderRecord(orderNumber, "Order Number");
			crmOrdersPage.clickonDetailsButton();
			CommonUtilities.threadSleep(1000);
			String detailsTabFlag = crmOrdersPage.isDetailsPageSelected();
			logg.info("Details Tab is selected in Orders Page");
			Assert.assertEquals("Details Tab is selected: " + detailsTabFlag, "Details Tab is selected: true");
			String crmOptyName = crmOrdersPage.getOptyName();
			logg.info("Opty is generated " + crmOptyName);
			if (!crmOptyName.isEmpty()) {
				crmOrdersPage.clickonOrderLinkOptyName();
				crmOrdersPage.clickOnheaderCardLink("Quotes");
				crmOrdersPage.verifyQuotesDetails();
			} else {
				Assert.fail("Opportunity is not generated for the Order");
			}
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 47, groups = { "CRM Application" })
	public void Verify_Contract_is_created_in_CRM_for_PORT_product_ordered_from_Portal() {
		try {
			boolean crmHomePageflag = crmHomePage.isOnCRMHomePage();
			logg.info("CRM Home Page is displayed = " + crmHomePageflag);
			Assert.assertTrue(crmHomePageflag, "CRM Home Page is not displayed");

			crmHomePage.clickonMainTab("Orders");
			crmOrdersPage.ClickOrderRecord(orderNumber, "Order Number");
			crmOrdersPage.clickonDetailsButton();
			CommonUtilities.threadSleep(1000);
			String detailsTabFlag = crmOrdersPage.isDetailsPageSelected();
			logg.info("Details Tab is selected in Orders Page");
			Assert.assertEquals("Details Tab is selected: " + detailsTabFlag, "Details Tab is selected: true");
			String crmOptyName = crmOrdersPage.getOptyName();
			logg.info("Opty is generated " + crmOptyName);
			if (!crmOptyName.isEmpty()) {
				crmOrdersPage.clickonOrderLinkOptyName();
				crmOrdersPage.clickOnheaderCardLink("Contracts");
				crmOrdersPage.verifyContractsDetails();
			} else {
				Assert.fail("Opportunity is not generated for the Order");
			}
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 48, groups = { "CRM Application" })
	public void TC_05_CreateNewOpportunity() {
		try {
			crmoptyPage.createNewOpty(servicehm.get("optyName").toString());
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 49, groups = { "CRM Application" })
	public void TC_06_CreateOrderinCRMAPPLications() {
		try {
			crmoptyPage.createProductinCRM("Port", servicehm);
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 50, groups = { "CRM Application" })
	public void TC_06_Verify_Product_Quote_Status_to_OrderActivated_Status() {
		try {
			String quoteStatus = crmoptyPage.verifyPrintQuoteStatus();

			logg.info("Print Quote Status is: =" + quoteStatus);
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 51, groups = { "CRM Application" })
	public void TC_76_Sales_Assist_for_port_to_verify_the_price_summary() {
		try {
			driver.switchTo().window(feMainTab);
			CommonUtilities.refreshPage();
			CommonUtilities.threadSleep(500);
			dashboard.clickOnPolarinLogo();
			CommonUtilities.threadSleep(1000);
			boolean userprofileflag = dashboard.isOnHomePage();
			logg.info("login is successful = " + userprofileflag);
			Assert.assertTrue(userprofileflag, "User profile is displayed and login is successful.");
			portcreation = dashboard.clickOnServicesForPort();

			logg.info("**************** Start New Port Creation *********************");
			boolean portcreationservice = servicecreation.verifyservicebutondisplayed("Port");
			logg.info("Is the Port Creation Available? = " + portcreationservice);
			Assert.assertTrue(portcreationservice, "Create Port Button is not displayed");

			servicecreation.clickonService("Port");

			boolean createPortHeaderflag = virtualrouter.verifyCreatePortHeader();
			logg.info("Create Port Header is available = " + createPortHeaderflag);
			Assert.assertTrue(createPortHeaderflag, "Create Port Header is not available.");

			boolean portLocationInputFlag = portcreation.isPortLocationEnabled();
			logg.info("Port Location textbox is enabled? = " + portLocationInputFlag);
			Assert.assertTrue(portLocationInputFlag, "Port Location textbox is disabled to enter Location");
			String country = servicehm.get("country").toString();
			portcreation.selectTheCountryfromDropDown(country);
			logg.info("Select country from dropdown");
			String selectedActualCountry = portcreation.getcountrydropdownvalue();
			boolean countryDropdownvalueflag = selectedActualCountry.equalsIgnoreCase(country);
			logg.info("Country value is successfully selected to  = " + country + " - " + countryDropdownvalueflag);
			Assert.assertTrue(countryDropdownvalueflag, "Country value is not selected to" + country);

			portcreation.selectPortSpeedEnabledLocation(servicehm.get("Location").toString(), "NA");
			CommonUtilities.threadSleep(2000);
			boolean portConfigureStageFlag = portcreation.isPortConfigureStageEnabled();
			logg.info("Port Location textbox is enabled? = " + portConfigureStageFlag);
			Assert.assertTrue(portConfigureStageFlag, "Port Configure stage is disabled to Create Port");

			portName = servicehm.get("crmServiceName").toString();
			portcreation.enterPortNameinTextBox(portName);
			portcreation.selectSubscriptionterm(servicehm.get("subscriptionTerm").toString());
			portcreation.selectPayment(servicehm.get("paymentType").toString());
			boolean isPortSpeedSelected = portcreation.selectPort_Speed();
			logg.info("Is the port Speed is enabled? = " + isPortSpeedSelected);
			Assert.assertTrue(isPortSpeedSelected, "Port speed is not Enabled for provided Location.");
			CommonUtilities.threadSleep(5000);
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 52, groups = { "CRM Application" })
	public void TC_76_Verify_user_is_able_to_order_the_port_for_Sales_Assist() {
		try {
			boolean configPortNextButtonFlag = virtualrouter.isNextButtonVisible();
			logg.info("Is the Next button visible? = " + configPortNextButtonFlag);
			Assert.assertTrue(configPortNextButtonFlag, "Next Button is not Enabled after Data selected.");

			virtualrouter.clickOnSaveAndNextBtn();
			CommonUtilities.threadSleep(2000);

			boolean revieworderdetailsFlag = portcreation.isOnReviewOrderDetailsHeaderPage();
			logg.info("Is review order details header displayed? = " + revieworderdetailsFlag);
			Assert.assertTrue(revieworderdetailsFlag, "review order details header  is not displayed");

			portcreation.selectBillingProfile();
			CommonUtilities.scrollByVisibilityOfElements(400);
			portcreation.clickOnIAggreementBtn();

			boolean orderbuttonflag = virtualrouter.isOrderBtnEnabled();
			logg.info("order button is enabled = " + orderbuttonflag);
			Assert.assertTrue(orderbuttonflag, "order button is not enabled.");

			portcreation.clickOnorderBtutton();
			logg.info("click On Order Button");
			String notificationmessage = portcreation.getSuccessNotificationMessage();
			logg.info("Displayed Notification message is   = " + notificationmessage);
			CommonUtilities.threadSleep(5000);
			if (notificationmessage.contains("Exception Occured")) {
				if (portcreation.isOrderBtnEnabled()) {
					portcreation.clickOnorderBtutton();
					notificationmessage = portcreation.getErrorNotificationMessage();
					Assert.assertEquals(notificationmessage, "Port created successfully!");
				}
			}

			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(notificationmessage, "Port created successfully!");
			CommonUtilities.threadSleep(1000);

			portcreation = dashboard.clickOnServicesForPort();
			logg.info("Landing On Services Dashboard");
			boolean serviceheaderflag = servicecreation.isOnSevicePage();
			logg.info("service header is available = " + serviceheaderflag);
			Assert.assertTrue(serviceheaderflag, "service header is not displayed.");
			CommonUtilities.threadSleep(2000);

			String status = servicedashboard.verifyServiceStatus("Ports", portName, "Ready To Patch", 5);
			salesAssist_serviceIDNumber = servicedashboard.getServiceRecordFieldValue("Ports", portName, "Service ID");
			logg.info("Order Number of the Port is:- " + orderNumber);
			Assert.assertNotEquals("Port status is := " + status, "Port status is := Failed");
			Assert.assertEquals("Port status is := " + status, "Port status is := Ready To Patch");

			driver.switchTo().window(adminMainTab);
			adminportalloginpage.enterServiceID(salesAssist_serviceIDNumber);
			CommonUtilities.threadSleep(1000);
			int orderNumberCOl = adminportalloginpage.getColumnNumber("Order");
			logg.info("Order Number Column is " + orderNumberCOl);
			salesAssist_orderNumber = adminportalloginpage.getColumnFieldValue("Order");
			logg.info("Order Number Value is " + orderNumber);
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 53, groups = { "CRM Application" })
	public void TC_76_Verify_Order_is_created_in_CRM_for_PORT_product_ordered_from_Portal_SalesAssist() {
		try {
			driver.switchTo().window(crmMainTab);
			boolean crmHomePageflag = crmHomePage.isOnCRMHomePage();
			logg.info("CRM Home Page is displayed = " + crmHomePageflag);
			Assert.assertTrue(crmHomePageflag, "CRM Home Page is not displayed");

			crmHomePage.clickonMainTab("Orders");
			crmOrdersPage.ClickOrderRecord(salesAssist_orderNumber, "Order Number");
			crmOrdersPage.clickonDetailsButton();
			String detailsTabFlag = crmOrdersPage.isDetailsPageSelected();
			logg.info("Details Tab is selected in Orders Page");
			Assert.assertEquals("Details Tab is selected: " + detailsTabFlag, "Details Tab is selected: true");
			String crmOrderNumber = crmOrdersPage.getOrderNumber();
			String crmOrderPathState = crmOrdersPage.getOrderPathActiveState();
			logg.info("Order Number:- " + crmOrderNumber + " Path Active state is " + crmOrderPathState);
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 54, groups = { "CRM Application" })
	public void Verify_CAF_is_created_in_CRM_for_PORT_product_ordered_from_Portal_SalesAssist() {
		try {
			boolean crmHomePageflag = crmHomePage.isOnCRMHomePage();
			logg.info("CRM Home Page is displayed = " + crmHomePageflag);
			Assert.assertTrue(crmHomePageflag, "CRM Home Page is not displayed");

			crmHomePage.clickonMainTab("Orders");
			crmOrdersPage.ClickOrderRecord(salesAssist_orderNumber, "Order Number");
			crmOrdersPage.clickonDetailsButton();
			String detailsTabFlag = crmOrdersPage.isDetailsPageSelected();
			logg.info("Details Tab is selected in Orders Page");
			Assert.assertEquals("Details Tab is selected: " + detailsTabFlag, "Details Tab is selected: true");
			String crmOrderCAFName = crmOrdersPage.getOrderCAFName();
			logg.info("CAF is generated " + crmOrderCAFName);
			if (!crmOrderCAFName.isEmpty()) {
				crmOrdersPage.clickonOrderLinkCAFName();
				String crmCAFDetailsNumber = crmOrdersPage.getCAFDetailsNumber();
				logg.info(" CAF Details number is " + crmCAFDetailsNumber);
			} else {
				Assert.fail("Opportunity is not generated for the Order");
			}
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 55, groups = { "CRM Application" })
	public void TC_76_Verify_Opty_is_created_in_CRM_for_PORT_product_ordered_from_Portal_SalesAssist() {
		try {
			boolean crmHomePageflag = crmHomePage.isOnCRMHomePage();
			logg.info("CRM Home Page is displayed = " + crmHomePageflag);
			Assert.assertTrue(crmHomePageflag, "CRM Home Page is not displayed");

			crmHomePage.clickonMainTab("Orders");
			crmOrdersPage.ClickOrderRecord(salesAssist_orderNumber, "Order Number");
			crmOrdersPage.clickonDetailsButton();
			CommonUtilities.threadSleep(1000);
			String detailsTabFlag = crmOrdersPage.isDetailsPageSelected();
			logg.info("Details Tab is selected in Orders Page");
			Assert.assertEquals("Details Tab is selected: " + detailsTabFlag, "Details Tab is selected: true");
			String crmOptyName = crmOrdersPage.getOptyName();
			logg.info("Opty is generated " + crmOptyName);
			if (!crmOptyName.isEmpty()) {
				crmOrdersPage.clickonOrderLinkOptyName();
				String crmOptyPathState = crmOrdersPage.getOrderPathSelectedState();
				logg.info(" Opportunity Path Active state is " + crmOptyPathState);
			} else {
				Assert.fail("Opportunity is not generated for the Order");
			}
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 56, groups = { "CRM Application" })
	public void TC_76_Verify_Quote_is_created_in_CRM_for_PORT_product_ordered_from_Portal_SalesAssist() {
		try {
			boolean crmHomePageflag = crmHomePage.isOnCRMHomePage();
			logg.info("CRM Home Page is displayed = " + crmHomePageflag);
			Assert.assertTrue(crmHomePageflag, "CRM Home Page is not displayed");

			crmHomePage.clickonMainTab("Orders");
			crmOrdersPage.ClickOrderRecord(salesAssist_orderNumber, "Order Number");
			crmOrdersPage.clickonDetailsButton();
			CommonUtilities.threadSleep(1000);
			String detailsTabFlag = crmOrdersPage.isDetailsPageSelected();
			logg.info("Details Tab is selected in Orders Page");
			Assert.assertEquals("Details Tab is selected: " + detailsTabFlag, "Details Tab is selected: true");
			String crmOptyName = crmOrdersPage.getOptyName();
			logg.info("Opty is generated " + crmOptyName);
			if (!crmOptyName.isEmpty()) {
				crmOrdersPage.clickonOrderLinkOptyName();
				crmOrdersPage.clickOnheaderCardLink("Quotes");
				crmOrdersPage.verifyQuotesDetails();
			} else {
				Assert.fail("Opportunity is not generated for the Order");
			}
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@Test(priority = 57, groups = { "CRM Application" })
	public void Verify_Contract_is_created_in_CRM_for_PORT_product_ordered_from_Portal_SalesAssist() {
		try {
			boolean crmHomePageflag = crmHomePage.isOnCRMHomePage();
			logg.info("CRM Home Page is displayed = " + crmHomePageflag);
			Assert.assertTrue(crmHomePageflag, "CRM Home Page is not displayed");

			crmHomePage.clickonMainTab("Orders");
			crmOrdersPage.ClickOrderRecord(salesAssist_orderNumber, "Order Number");
			crmOrdersPage.clickonDetailsButton();
			CommonUtilities.threadSleep(1000);
			String detailsTabFlag = crmOrdersPage.isDetailsPageSelected();
			logg.info("Details Tab is selected in Orders Page");
			Assert.assertEquals("Details Tab is selected: " + detailsTabFlag, "Details Tab is selected: true");
			String crmOptyName = crmOrdersPage.getOptyName();
			logg.info("Opty is generated " + crmOptyName);
			if (!crmOptyName.isEmpty()) {
				crmOrdersPage.clickonOrderLinkOptyName();
				crmOrdersPage.clickOnheaderCardLink("Contracts");
				crmOrdersPage.verifyContractsDetails();
			} else {
				Assert.fail("Opportunity is not generated for the Order");
			}
		} catch (Exception e) {
			Assert.fail("Testcase failed with Exception Error", e);
		}
	}

	@AfterClass
	public void closeBrowser() {

		logg.info("Close Browser");
		logg.info("************* End New Port Creation *******************");
		Set<String> allWindows = driver.getWindowHandles();
		for (String Wh : allWindows) {
			driver.switchTo().window(Wh);
			driver.close();
		}
	}
}
*/