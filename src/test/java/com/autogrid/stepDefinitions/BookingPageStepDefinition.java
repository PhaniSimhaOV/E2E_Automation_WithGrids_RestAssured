package com.autogrid.stepDefinitions;

import org.testng.Assert;
import com.autogrid.steps.CustBookingmgmtPage;
import com.autogrid.steps.SearchCustBookingMgmtListPage;
import com.autogrid.utils.LaunchDriver;

import io.cucumber.java.en.*;

public class BookingPageStepDefinition {

	SearchCustBookingMgmtListPage cbmlp;// = new SearchCustBookingMgmtListPage(null);

	CustBookingmgmtPage cbp;// = new CustBookingmgmtPage(null);

	@When("User clicks Sales Icon on the left")
	public void user_clicks_sales_icon_on_the_left() {

		try {
			cbmlp.clickOnSalesIcon();
			System.out.println("User Clicked Sales Icon");
		} catch (Exception e) {
			System.err.println("Error while clicking Sales Icon" + e.getMessage());
			throw new io.cucumber.java.PendingException();
		}
	}

	@When("User clicks on the  Sales Operation Sub Menu Item")
	public void user_clicks_on_the_sales_operation_sub_menu_item() {
		try {
			cbmlp.clickOnSalesOperation();
			System.out.println("User Clicked Sales Operation Sub Menu Item");
		} catch (Exception e) {
			System.err.println("Error while clicking Sales Operation Sub Menu Item" + e.getMessage());
			throw new io.cucumber.java.PendingException();
		}
	}

	@When("User clicks on the  Customer Booking Mgt List link")
	public void user_clicks_on_the_customer_booking_mgt_list_link() {

		try {
			cbmlp.CustomerBookingMgmtLink();
			System.out.println("User Clicked Customer Booking Mgt List link");
		} catch (Exception e) {
			System.err.println("Error while clicking Customer Booking Mgt List link" + e.getMessage());
			throw new io.cucumber.java.PendingException();
		}
	}

	@When("User click save button in  Customer Booking Mananagement List Screen")
	public void User_click_save_button_in_Customer_Booking_Mananagement_List_Screen() {
		try {
			cbmlp.clickSaveButton();
			System.out.println("User Clicked save button in  Customer Booking Mananagement List Screen ");
		} catch (Exception e) {
			System.err.println("Error while clicking save button in  Customer Booking Mananagement List Screen" + e.getMessage());
					throw new io.cucumber.java.PendingException();
		}
	}

	@Then("User get toast msg when clicked save button in Customer Booking Mananagement List Screen")
	public void User_get_toast_msg_when_clicked_save_button_in_Customer_Booking_Mananagement_List_Screen() {

		try {
			String actualMessage = cbmlp.getToastMsgWhileSavingWithoutInput();
			String expectedMessage = "There is not any changed information.";
			Assert.assertEquals(actualMessage, expectedMessage, "There is not any changed information.");
			System.out.println("Toast msg matched:" + actualMessage);

		} catch (Exception e) {
			System.err.println("Toast msg unmatched:" + e.getMessage());
		}
	}

	@Then("User should be able to navigate to the Booking Mgt List screen")
	public void user_should_be_able_to_navigate_to_the_booking_mgt_list_screen() {

		try {
			Assert.assertTrue(cbmlp.isCustBookingMgmtListheaderDisplayed());

			System.out.println("Cust Booking mgt list screen is displayed");

		} catch (Exception e) {
			System.err.println("Error while navigating to Cust Booking mgt list screen " + e.getMessage());
			Assert.fail("Cust Booking mgt list screen verification failed.");

		}

	}

	@When("User should be able to navigate Customer Booking Management Screen")
	public void user_should_be_able_to_navigate_customer_booking_management_screen() {

		try {
			Assert.assertTrue(cbmlp.isCustBookingPageHeaderDisplayed());

			System.out.println("Cust Booking screen is displayed");

		} catch (Exception e) {
			System.err.println("Error while navigating to Cust Booking screen " + e.getMessage());
			Assert.fail("Cust Booking mgmt screen verification failed.");
		}

	}

	@When("User click on Register Button")
	public void user_click_on_register_button() {
		try {
			cbp.ClickBookingRegiterButton();
			System.out.println("User Clicked _Register_Button ");
		} catch (Exception e) {
			System.err.println("Error while clicking Register Button" + e.getMessage());

		}
	}

	@And(" User start entering valid Basic info and checks SMS option")
	public void CheckSMS() {
		try {

			cbp.ClickCheckboxsms();
			System.out.println("Clicked SMS Box");
		} catch (Exception e) {
			System.err.println("Error while clicking SMS Box" + e.getMessage());

		}
	}

	@When("User set Date Of option")
	public void user_set_date_of_as() {
		try {
			String dateOf = "Enquiry";
			cbmlp.setDateOf(dateOf);
			System.out.println("Successfully set Date Of Option");
		} catch (Exception e) {
			System.err.println("Error while setting Date Of option" + e.getMessage());

		}
	}

	@And("User set Based On option")
	public void user_set_based_on_as() throws InterruptedException {
		try {
			String BasedOn = "Mobile no";
			cbmlp.setBasedOn(BasedOn);
			System.out.println("Successfully set Based On Option");
		} catch (Exception e) {
			System.err.println("Error while setting Based On option" + e.getMessage());

		}
		Thread.sleep(3000);

	}

	@And("Enter mobile no. which is already taken in enquiry stage")

	public void enter_mobileNo_taken_in_enquiry() {

		try {
			String Phoneno = "7489954647";
			cbmlp.typePhoneNo(Phoneno);

			System.out.println("Entered Phone No which is taken in Enquiry stage");
		} catch (Exception e) {
			System.err.println("Entered Phone No is not in Enquiry stage" + e.getMessage());

		}
	}

	@Then("Lead should display in table") ////// need to review

	public void details_of_customer_should_display_in_table() {
		try {
			boolean status = cbmlp.searchCustomerByPhoneNo("7489954647");

			Assert.assertEquals(status, true);
			System.out.println("Lead appear in table");
		} catch (Exception e) {
			System.err.println("Lead not there in table" + e.getMessage());

		}

	}

	@When("User double click on that lead")
	public void user_double_click_on_that_lead() throws InterruptedException {
		try {
			cbmlp.RequiredRowInTable();
			System.out.println("Successfully double clicked lead");
		} catch (Exception e) {
			System.out.println("Error in double clickin lead:" + e.getMessage());
		}

		// cbmlp.SearchedRow();
	}

	@Then("Alert info window appear and dont allow to register") ///// left
	public void alert_info_window_appear_and_dont_allow_to_register() {

		throw new io.cucumber.java.PendingException();
	}

	@Given("User select valid Enquiry Category")

	public void user_select_valid_enquiry_Category() {
		try {

			String enquiryCat = "Individual";
			cbp.BasicInfoEnquiryTypeDrpdwn(enquiryCat);
			System.out.println("EnquiryType Successfully selected:" + enquiryCat);
		} catch (Exception e) {
			System.out.println("Error during EnquiryType selection:" + e.getMessage());
		}
	}

	@Given("User start entering valid Basic info and checks SMS option")
	public void user_start_entering_valid_basic_info_and_checks_sms_option() {
		try {
			cbp.ClickCheckboxsms();
			System.out.println("Checked sms successfully");
		} catch (Exception e) {
			System.out.println("Error in selecting sms" + e.getMessage());

		}
	}

	@Given("User select valid Enquiry Type")
	public void user_select_valid_enquiry_type() {
		try {

			String enquiryType = "HOT";
			cbp.BasicInfoEnquiryTypeDrpdwn(enquiryType);
			System.out.println("EnquiryType Successfully selected:" + enquiryType);
		} catch (Exception e) {
			System.out.println("Error during EnquiryType selection:" + e.getMessage());
		}
	}

	@And("User select valid Sales Consultant")
	public void user_select_sales_consultant_based_on_enquiry() {

		try {
			String salesConsultant = "CH AMBADAS [ES52370439]";

			cbp.selectSalesConsultant(salesConsultant);
			System.out.println("ESales Consultant Successfully selected:" + salesConsultant);

		} catch (Exception e) {
			System.out.println("Error during EnquiryType selection:" + e.getMessage());
		}
	}

	@When("Click on Search")
	public void click_on_search() {

		try {
			cbmlp.clickSearchBtn();
			System.out.println(" Successfully clicked search button");
		} catch (Exception e) {
			System.out.println(" Error in clicking search button" + e.getMessage());
		}
		throw new io.cucumber.java.PendingException();
	}

	@Given("User select a valid Enquiry Type")
	public void user_select_a_valid_enquiry_type() {
		try {
			String enquiryType = "HOT";
			cbp.BasicInfoEnquiryTypeDrpdwn(enquiryType);
			System.out.println("Successfully selected Enquiry type:" + enquiryType);
		} catch (Exception e) {
			System.out.println("Error in Selecting Enquiry type" + e.getMessage());
		}

		throw new io.cucumber.java.PendingException();
	}

	@Given("User select a valid Enquiry Category")
	public void user_select_a_valid_enquiry_category() {
		try {
			String enquiryCat = "Individual";
			cbp.BasicInfoEnquiryCategorydrpDwn(enquiryCat);
			System.out.println("Successfully selected Enquiry Category :" + enquiryCat);
		} catch (Exception e) {
			System.out.println("Error in Selecting Enquiry Category" + e.getMessage());
		}

		throw new io.cucumber.java.PendingException();

	}

	@Given("User select a valid Sales Consultant")
	public void user_select_a_valid_sales_consultant() {
		try {
			String salesConsultant = "CH AMBADAS [ES52370439]";
			cbp.selectSalesConsultant(salesConsultant);
			System.out.println("Successfully selected Sales Consultant:" + salesConsultant);
		} catch (Exception e) {
			System.out.println("Error in Selecting Sales Consultant:" + e.getMessage());
		}

		throw new io.cucumber.java.PendingException();
	}

	@Given("User Enter a valid Referred by")
	public void user_enter_a_valid_referred_by() {
		try {
			String referredBy = "Kumar";
			cbp.enterReferredBy(referredBy);
			System.out.println("Successfully selected Referred by:" + referredBy);
		} catch (Exception e) {
			System.out.println("Error in Selecting Referred by:" + e.getMessage());
		}
		throw new io.cucumber.java.PendingException();
	}

	@Given("User select a valid Finance req")
	public void user_select_a_valid_finance_req() {
		try {
			String financeReq = "NO";
			cbp.selectFinanceReq(financeReq);
			System.out.println("Successfully selected FinanceReq:" + financeReq);
		} catch (Exception e) {
			System.out.println("Error in Selecting FinanceReq:" + e.getMessage());

		}
		throw new io.cucumber.java.PendingException();
	}

	@Given("User select a valid fuel type")
	public void user_select_a_valid_fuel_type() {
		try {
			String fueltype = "Petrol";
			cbp.selectFuelType(fueltype);
			System.out.println("Successfully selected Fuel type:" + fueltype);
		} catch (Exception e) {
			System.out.println("Error in Selecting Fuel type" + e.getMessage());
		}
		throw new io.cucumber.java.PendingException();
	}

	@Given("User select a valid Variant")
	public void user_select_a_valid_variant() {
		try {
			String variant = "GL";
			cbp.selectVariant(variant);
			System.out.println("Successfully selected Variant" + variant);
		} catch (Exception e) {
			System.out.println("Error in Selecting " + e.getMessage());
			throw new io.cucumber.java.PendingException();
		}
	}

	@Given("User select a valid sub Variant")
	public void user_select_a_valid_sub_variant() {
		try {
			String subvariant = "Creta N Line N8 1.5 Turbo DCT(FHW5K8G1U D10M)";
			cbp.selectSubVariant(subvariant);
			System.out.println("Successfully selected subVariant" + subvariant);
		} catch (Exception e) {
			System.out.println("Error in Selecting subVariant" + e.getMessage());

		}

		throw new io.cucumber.java.PendingException();
	}

	@Given("User select a valid Ext Color")
	public void user_select_a_valid_ext_color() {
		try {
			String extColor = "ATLAS WHITE";
			cbp.selectExtColor(extColor);
			System.out.println("Successfully selected ExtColor" + extColor);
		} catch (Exception e) {
			System.out.println("Error in Selecting ExtColor " + e.getMessage());
		}

		throw new io.cucumber.java.PendingException();
	}

	@Given("User select a valid Int Color")
	public void user_select_a_valid_int_color() {
		try {
			String intcolor = "String intcolor";
			cbp.selectIntColor(intcolor);
			System.out.println("Successfully selected intColor" + intcolor);
		} catch (Exception e) {
			System.out.println("Error in Selecting intColor" + e.getMessage());
		}
		throw new io.cucumber.java.PendingException();
	}

	@Given("User enter a valid Registration name")
	public void user_enter_a_valid_registration_name() {
		try {
			String registrationName = "Kavita Sahu";
			cbp.enterValidRegistrationName(registrationName);
			System.out.println("Successfully entered Registration Name" + registrationName);
		} catch (Exception e) {

			System.out.println("Error in Entering Registration name" + e.getMessage());
		}
		throw new io.cucumber.java.PendingException();
	}

	@Given("User select a valid cust income")
	public void user_select_a_valid_cust_income() {
		try {
			String custIncome = "3 - 5 Lacs";
			cbp.selectCustIncome(custIncome);
			System.out.println("Successfully selected Valid Cust Income" + custIncome);
		} catch (Exception e) {
			System.out.println("Error in Selecting cust income" + e.getMessage());
		}
		throw new io.cucumber.java.PendingException();
	}

	@Given("User select a valid title")
	public void user_select_a_valid_title() {
		try {
			String title = "Mrs.";
			cbp.selectTitleInRegInfo(title);
			System.out.println("Successfully selected title" + title);
		} catch (Exception e) {

			System.out.println("Error in Selecting title" + e.getMessage());
		}
		throw new io.cucumber.java.PendingException();
	}

	@Given("User enter a valid PAN")
	public void user_enter_a_valid_pan() {
		try {
			String pan = "DVPPS7919H";
			cbp.enterValidPan(pan);
			System.out.println("Successfully entered PAN" + pan);
		} catch (Exception e) {
			System.out.println("Error in entering PAN" + e.getMessage());
		}
		throw new io.cucumber.java.PendingException();
	}

	@Given("User enter a valid Address in regInfo")
	public void user_enter_a_valid_address_in_reg_info() {
		try {
			String address = "Bowrampet";
			cbp.AddressInRegInfo(address);
			System.out.println("Successfully entered address" + address);

			throw new io.cucumber.java.PendingException();
		} catch (Exception e) {
			System.out.println("Error in entering address" + e.getMessage());
		}

	}
//////left iframe concept here

	@Given("User Set a Pin by filtering based on PIN in PIN Code Search")
	public void user_set_a_pin_by_filtering_based_on_pin_in_pin_code_search() {

		try {

			cbp.ClickOnPincodeSearchIcon();
			cbp.validatedPinCodeScreen();
			cbp.enterPincodeInPincodeField();
			cbp.clicksOnSearchButtonInPinCodeSearchScreen();
			cbp.selectOnePincodeFromList();
			cbp.clickOnAddSelectedButtonInPincodeSearchScreen();
			System.out.println("Successfully selected Pin by filtering");
		} catch (Exception e) {
			System.out.println("Fail to select" + e.getMessage());
		}
		throw new io.cucumber.java.PendingException();
	}

	@Given("User enter a valid village")
	public void user_enter_a_valid_village() {
		try {
			String village = "Chandanagar";
			cbp.enterVillageInRegInfo(village);
			System.out.println("Successfully entered village" + village);
		} catch (Exception e) {
			System.out.println("Error in entering" + e.getMessage());
		}
		throw new io.cucumber.java.PendingException();
	}

	@Then("Booking No should generate") /// left
	public void booking_no_should_generate() {
		try {

		} catch (Exception e) {

		}
	}

	@When("User tries to click on Modify button")
	public void user_tries_to_click_on_modify_button() {
		try {
			cbp.clickModifyButton();
			System.out.println("Successfully clicked Modify button");
		} catch (Exception e) {
			System.out.println("Error to click Modify Button" + e.getMessage());
		}
	}

	@Then("User should be able to see modify pop up screen asking for confirmation")
	public void user_should_be_able_to_see_modify_pop_up_screen_asking_for_confirmation() {

		try {
			Assert.assertTrue(cbp.isModifyPopScreenDisplayed(), "Modify pop screen ");
			System.out.println("Modify pop up screen is displayed.");
		} catch (Exception e) {
			System.err.println("Error to see modify pop-up screen: " + e.getMessage());
			Assert.fail("Failed to see modify pop screen ");
		}

		throw new io.cucumber.java.PendingException();
	}

	@Then("User close modify pop screen")
	public void user_close_modify_pop_screen() {
		try {
			cbp.clickCrossSignInModifyPopUp();
			System.out.println("successfully Closed modify pop screen");
		} catch (Exception e) {
			System.out.println("Error to close modify pop screen " + e.getMessage());
		}

		throw new io.cucumber.java.PendingException();
	}

	@When("User tries to click on Register Button") /// left
	public void user_tries_to_click_on_register_button() {

		throw new io.cucumber.java.PendingException();
	}

	@Given("User select mode Of purchase")
	public void user_select_mode_of_purchase() {
		try {
			String modeOfPurchase = "cash";
			cbp.selectModeOfPurchase(modeOfPurchase);
			System.out.println("Mode of Purchase Selected is:" + modeOfPurchase);
		} catch (Exception e) {
			System.out.println("Error in selecting mode of purchase");

		}
	}

	@Given("User select Cust Income")
	public void user_select_cust_income() {
		try {
			String custIncome = "3 - 5 Lacs";
			cbp.selectCustIncome(custIncome);
			System.out.println("Cust Income selected is:" + custIncome);
		} catch (Exception e) {
			System.out.println("Error in selecting Cust Income");
		}
		throw new io.cucumber.java.PendingException();
	}

	@Given("User select title")
	public void user_select_title() {
		try {
			String title = "Mrs";
			cbp.selectTitleInRegInfo(title);
			System.out.println("Title selected is :" + title);

		} catch (Exception e) {
			System.out.println("Error in selecting title");
		}

	}

	@Given("User select Preferred No Plate")
	public void user_select_preferred_no_plate() {
		try {
			String prefferedNoPlate = "Individual";
			cbp.selectPrefferedNoPlateInReg(prefferedNoPlate);
			System.out.println("Preferred no plate selected: +prefferedNoPlate");

		} catch (Exception e) {
			System.out.println("Error in selecting Preffred no plate");
		}

		throw new io.cucumber.java.PendingException();
	}

	@Then("User confirm to register") // left
	public void user_confirm_to_register() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

}
