package com.autogrid.stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.autogrid.steps.DMSLoginPage;
import com.autogrid.steps.NewEnquiryPage;
import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

public class NewEnquiryStepDefinition {
	CommonActions commonActions;
	DMSLoginPage dMSLoginPage;
	NewEnquiryPage newenquirypage;

	public NewEnquiryStepDefinition() {
		WebDriver driver = LaunchDriver.getDriver();
		this.newenquirypage = new NewEnquiryPage(driver);
		PageFactory.initElements(driver, newenquirypage);
	}

	@When("User clicks Sales Menu Item")
	public void user_clicks_sales_menu_item() {
		try {
			Thread.sleep(3000);
			newenquirypage.clickSalesMenu();
			System.out.println("Sales Menu clicked.");
		} catch (Exception e) {
			System.err.println("Error during Sales Menu click: " + e.getMessage());
		}
	}

	@When("User clicks on the Customer Enquiry Sub Menu Item")
	public void user_clicks_on_the_customer_enquiry_sub_menu_item() {
		try {
			Thread.sleep(3000);
			newenquirypage.clickCustomerEnquirySubmenu();
			System.out.println("Customer Enquiry Sub Menu Item clicked.");
		} catch (Exception e) {
			System.err.println("Error during Customer Enquiry Sub Menu Item click: " + e.getMessage());
		}
	}

	@When("User clicks on the Customer Enquiry link")
	public void user_clicks_on_the_customer_enquiry_link() {
		try {
			Thread.sleep(3000);
			newenquirypage.clickCustomerEnquiryLink();
			System.out.println("Customer Enquiry Link clicked.");
		} catch (Exception e) {
			System.err.println("Error during Customer Enquiry Link click: " + e.getMessage());
		}
	}

	@Then("User should be able to navigate to the Customer Enquiry screen")
	public void user_should_be_able_to_navigate_to_the_customer_enquiry_screen() {
		try {
			Thread.sleep(3000);
			Assert.assertTrue(newenquirypage.isCustomerEnquiryScreenDisplayed(),
					"Customer Enquiry Screen is not displayed.");
			System.out.println("Customer Enquiry Screen is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Customer Enquiry Screen pop-up: " + e.getMessage());
			Assert.fail("Customer Enquiry Screen verification failed.");
		}
	}

	@And("User clicks on New button")
	public void user_clicks_on_new_button() {
		try {
			Thread.sleep(3000);
			newenquirypage.interactWithIframeElement();
			newenquirypage.clickNewEnquiryButton();
			System.out.println("New Enquiry Button clicked.");
		} catch (Exception e) {
			System.err.println("Error during New Enquiry Button click: " + e.getMessage());
		}
	}

	@Then("User should be able to navigate Sales Customer Enquiry Pop-up")
	public void user_should_be_able_to_navigate_sales_customer_enquiry_pop_up() {
		try {
			Assert.assertTrue(newenquirypage.isSalesCustomerEnquiryPopupDisplayed(),
					"Sales Customer Enquiry pop-up is not displayed.");
			System.out.println("Sales Customer Enquiry pop-up is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Sales Customer Enquiry pop-up: " + e.getMessage());
			Assert.fail("Sales Customer Enquiry pop-up verification failed.");
		}
	}

	@Then("User should be able to see the UI with Header as Sales Customer Enquiry")
	public void user_should_be_able_to_see_the_ui_with_header_as_sales_customer_enquiry() {
		try {
			Assert.assertTrue(newenquirypage.isSalesCustomerEnquiryPopupDisplayed(),
					"Sales Customer Enquiry pop-up is not displayed.");
			System.out.println("Sales Customer Enquiry pop-up is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Sales Customer Enquiry pop-up: " + e.getMessage());
			Assert.fail("Sales Customer Enquiry pop-up verification failed.");
		}
	}
	
	@And("User tries to clicks on New button")
	public void user_tries_to_clicks_on_new_button() {
		try {
			Thread.sleep(3000);
			newenquirypage.interactWithIframeElement();
			newenquirypage.clickNewEnquiryButton();
			System.out.println("New Enquiry Button clicked.");
		} catch (Exception e) {
			System.err.println("Error during New Enquiry Button click: " + e.getMessage());
		}
	}

	@When("User clicks on Close icon")
	public void user_clicks_on_close_icon() {
		try {
			newenquirypage.clickCloseIcon();
			System.out.println("Close icon Button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Close icon click: " + e.getMessage());
		}
	}
	
	@Then("User should be able to navigate back to the Customer Enquiry screen")
	public void user_should_be_able_to_navigate_back_to_the_customer_enquiry_screen() {
		try {
			Thread.sleep(3000);
			LaunchDriver.getDriver().switchTo().defaultContent();
			Assert.assertTrue(newenquirypage.isCustomerEnquiryScreenDisplayed(),
					"Customer Enquiry Screen is not displayed.");
			System.out.println("Customer Enquiry Screen is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Customer Enquiry Screen pop-up: " + e.getMessage());
			Assert.fail("Customer Enquiry Screen verification failed.");
		}
	}

	@When("User clicks on Pincode Search Icon from PIN field")
	public void user_clicks_on_pincode_search_icon_from_pin_field() {
		try {
			newenquirypage.interactWithCustomerEnquiryPopupIframeElement();
			newenquirypage.clickPincodeSearchIcon();
			System.out.println("Pincode Search Icon clicked.");
		} catch (Exception e) {
			System.err.println("Error during Pincode Search Icon click: " + e.getMessage());
		}
	}

	@Then("User should be able to navigate to the Pincode search Screen")
	public void user_should_be_able_to_navigate_to_the_pincode_search_screen() {
		try {
			Thread.sleep(3000);
			newenquirypage.interactWithIframeElement();
			Assert.assertTrue(newenquirypage.isPincodeSearchScreenDisplayed(),
					"Pincode search Screen is not displayed.");
			System.out.println("Pincode search Screen is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Pincode search Screen : " + e.getMessage());
			Assert.fail("Pincode search Screen verification failed.");
		}
	}

	@Then("User tries to enter Pincode in pincode field")
	public void user_tries_to_enter_pincode_in_pincode_field() {
		try {
			Thread.sleep(2000);
			newenquirypage.interactWithincodeSearchIframeElement();
			String pincode = "500018";
			newenquirypage.enterPincode(pincode);
			System.out.println("Entered Pincode: " + pincode);
		} catch (Exception e) {
			System.err.println("Error during entering Pincode: " + e.getMessage());
		}
	}

	@Then("User tries to clicks on search button in Pincode search Screen")
	public void user_tries_to_clicks_on_search_button_in_pincode_search_screen() {
		try {
			newenquirypage.clickPinCodeSearchButton();
			System.out.println("Pincode Search Button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Pincode Search Button click: " + e.getMessage());
		}
	}

	@Then("User tries to select one pincode from the list")
	public void user_tries_to_select_one_pincode_from_the_list() {
		try {
			newenquirypage.clickLocationSelection();
			System.out.println("Location Selection clicked.");
		} catch (Exception e) {
			System.err.println("Error during Location Selection click: " + e.getMessage());
		}
	}

	@Then("User tries to clicks on Add Selected button in Pincode search Screen")
	public void user_tries_to_clicks_on_add_selected_button_in_pincode_search_screen() {
		try {
			newenquirypage.clickAddSelectedButton();
			System.out.println("Add Selected button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Add Selected button click: " + e.getMessage());
		}
	}

	@Then("User should be able to see Selected Pincode displayed in the Pincode Field")
	public void user_should_be_able_to_see_selected_pincode_displayed_in_the_pincode_field() {
		try {
			newenquirypage.interactWithIframeElement();
			newenquirypage.interactWithCustomerEnquiryPopupIframeElement();
			String expectedPincode = "500018";
			String actualPincode = newenquirypage.getPincode();
			Assert.assertEquals(actualPincode, expectedPincode, "Pincode mismatch!");
			System.out.println("Verification successful: Pincode is displayed correctly.");
		} catch (Exception e) {
			System.err.println("An error occurred during Pincode verification: " + e.getMessage());
			e.printStackTrace();
		}LaunchDriver.getDriver().switchTo().defaultContent();
	}
	
	@When("User clicks on Pincode Search Icon beside PIN field")
	public void user_clicks_on_pincode_search_icon_beside_pin_field() {
		try {
			newenquirypage.interactWithIframeElement();
			newenquirypage.interactWithCustomerEnquiryPopupIframeElement();
			newenquirypage.clickPincodeSearchIcon();
			System.out.println("Pincode Search Icon clicked.");
		} catch (Exception e) {
			System.err.println("Error during Pincode Search Icon click: " + e.getMessage());
		}
	}
	
	@When("User clicks on Pincode Search Icon beside the PIN field")
	public void user_clicks_on_pincode_search_icon_beside_the_pin_field() {
		try {
			newenquirypage.clickPincodeSearchIcon();
			System.out.println("Pincode Search Icon clicked.");
		} catch (Exception e) {
			System.err.println("Error during Pincode Search Icon click: " + e.getMessage());
		}
	}
	
	@Then("User should be able to navigate to Pincode Search Screen")
	public void user_should_be_able_to_navigate_to_pincode_search_screen() {
		try {
			Thread.sleep(3000);
			newenquirypage.interactWithIframeElement();
			Assert.assertTrue(newenquirypage.isPincodeSearchScreenDisplayed(),
					"Pincode search Screen is not displayed.");
			System.out.println("Pincode search Screen is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Pincode search Screen : " + e.getMessage());
			Assert.fail("Pincode search Screen verification failed.");
		}
	}

	@Then("User tries to selects State from State field")
	public void user_tries_to_selects_state_from_state_field() {
		try {
			Thread.sleep(4000);
			newenquirypage.interactWithincodeSearchIframeElement();
			newenquirypage.selectState("TELANGANA");
		} catch (Exception e) {
			System.err.println("Error during entering & selecting state Name: " + e.getMessage());
		}
	}
		

	@Then("User tries to selects District from District field")
	public void user_tries_to_selects_district_from_district_field() {
		try {
			Thread.sleep(5000);
			newenquirypage.selectDistrict("K.V.Rangareddy");
		} catch (Exception e) {
			System.err.println("Error during entering & selecting District Name: " + e.getMessage());
		}
	}

	@Then("User tries to selects Taluka or Tehsil from Taluka or Tehsil field")
	public void user_tries_to_selects_taluka_or_tehsil_from_taluka_or_tehsil_field() {
		try {
			Thread.sleep(5000);
			newenquirypage.selectTaluka("Balanagar");
		} catch (Exception e) {
			System.err.println("Error during entering & selecting Taluka Name: " + e.getMessage());
		}
	}

	@Then("User tries to enters Post Office Name from Post Office Name field")
	public void user_tries_to_enters_post_office_name_from_post_office_name_field() {
		try {
			Thread.sleep(5000);
			String postofficename = "Bharat Nagar Colony S.O";
			newenquirypage.enterPostOfficeName(postofficename);
			System.out.println("Entered Post Office Name: " + postofficename);
		} catch (Exception e) {
			System.err.println("Error during entering Post Office Name: " + e.getMessage());
		}
	}
	
	@Then("User should be able to see the Selected Pincode displayed in the Pincode Field")
	public void user_should_be_able_to_see_the_selected_pincode_displayed_in_the_pincode_field() {
		try {
			newenquirypage.interactWithIframeElement();
			newenquirypage.interactWithCustomerEnquiryPopupIframeElement();
			String expectedPincode = "500018";
			String actualPincode = newenquirypage.getPincode();
			Assert.assertEquals(actualPincode, expectedPincode, "Pincode mismatch!");
			System.out.println("Verification successful: Pincode is displayed correctly.");
		} catch (Exception e) {
			System.err.println("An error occurred during Pincode verification: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Then("User tries to select one pincode from the list after applying filters")
	public void user_tries_to_select_one_pincode_from_the_list_after_applying_filters() {
		try {
			Thread.sleep(3000);
			newenquirypage.clickLocationSelectionAfterFilters();
			System.out.println("Location Selection clicked.");
		} catch (Exception e) {
			System.err.println("Error during Location Selection click: " + e.getMessage());
		}
	}

	@When("User click on Save button without entering mandatory fields")
	public void user_click_on_save_button_without_entering_mandatory_fields() {
		try {
			newenquirypage.clickSaveButton();
			System.out.println("Save button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Save button click: " + e.getMessage());
		}
	}

	@Then("User should be able to see toast message in Sales Customer Enquiry Screen")
	public void user_should_be_able_to_see_toast_message_in_sales_customer_enquiry_screen() {
		try {
			String actualMessage = newenquirypage.getEmptyEnquiryToast();
			String expectedMessage = "";
			// Validate that the actual message matches the expected message
			Assert.assertEquals(actualMessage, expectedMessage, "Toast message mismatch!");

			// Log success message
			System.out.println("Toast message validated successfully: " + actualMessage);
		} catch (Exception e) {
			System.err.println("Error during toast message validation: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@When("User tries to enters mobile number consists of less than nine digits")
	public void user_tries_to_enters_mobile_number_consists_of_less_than_nine_digits() {
		try {
			Thread.sleep(2000);
			String mobilenumber = "6786826";
			newenquirypage.enterMobileNumber(mobilenumber);
			System.out.println("Entered Mobile Number: " + mobilenumber);
		} catch (Exception e) {
			System.err.println("Error during entering Mobile Number: " + e.getMessage());
		}
	}

	@When("User clicks on Save button")
	public void user_clicks_on_save_button() {
		try {
			newenquirypage.clickSaveButton();
			System.out.println("Save button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Save button click: " + e.getMessage());
		}
	}

	@Then("User should be able to see toast message to enter valid Mobile Number")
	public void user_should_be_able_to_see_toast_message_to_enter_valid_mobile_number() {
		try {
			String actualMessage = newenquirypage.getInvalidMobileNumberToast();
			String expectedMessage = "1. Mobile No. should be 10 digit\r\n" + "2. Only Numeric digit allow\r\n"
					+ "3. Mobile No. first digit should be greater than 5";
			// Validate that the actual message matches the expected message
			Assert.assertEquals(actualMessage, expectedMessage, "Toast message mismatch!");

			// Log success message
			System.out.println("Toast message validated successfully: " + actualMessage);
		} catch (Exception e) {
			System.err.println("Error during toast message validation: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@When("User tries to enters mobile number Which was already Registered")
	public void user_tries_to_enters_mobile_number_which_was_already_registered() {
		try {
			Thread.sleep(2000);
			newenquirypage.interactWithIframeElement();
			newenquirypage.interactWithCustomerEnquiryPopupIframeElement();
			Thread.sleep(2000);
			String mobilenumber = "9640884870";
			newenquirypage.enterMobileNumber(mobilenumber);
			System.out.println("Entered Mobile Number: " + mobilenumber);
		} catch (Exception e) {
			System.err.println("Error during entering Mobile Number: " + e.getMessage());
		}
	}
	
	@When("User clicks on mobile number search Icon")
	public void user_clicks_on_mobile_number_search_icon() {
		try {
			Thread.sleep(6000);
			newenquirypage.clickMobileSearchIcon();
			System.out.println("Mobile number search Icon clicked.");
		} catch (Exception e) {
			System.err.println("Error during Mobile number search Icon click: " + e.getMessage());
		}
	}
	
	@Then("User should be able to navigate to the Find A Customer Info Screen")
	public void user_should_be_able_to_navigate_to_the_find_a_customer_info_screen() {
		try {
			Thread.sleep(3000);
			LaunchDriver.getDriver().switchTo().defaultContent();
			newenquirypage.interactWithIframeElement();
			Assert.assertTrue(newenquirypage.isFindACustomerInfoPopupDisplayed(),
					"Find A Customer Info Screen is not displayed.");
			System.out.println("Find A Customer Info Screen is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Find A Customer Info Screen Screen : " + e.getMessage());
			Assert.fail("Find A Customer Info Screen verification failed.");
		}
	}
	
	@Given("User tries to clcik on Close icon in the Find A Customer Info Screen")
	public void user_tries_to_click_on_the_close_icon_in_the_find_a_customer_info_screen() {
		try {
			Thread.sleep(3000);
			newenquirypage.clickFindaCustomerCloseIcon();
			System.out.println("Find A Customer Info ScreenClose Icon clicked.");
		} catch (Exception e) {
			System.err.println("Error during Find A Customer Info Screen Close Icon click: " + e.getMessage());
		}
	}

	@When("User tries to enters mobile number Which Not Registered")
	public void user_tries_to_enters_mobile_number_which_not_registered() {
		try {
			Thread.sleep(2000);
			newenquirypage.interactWithIframeElement();
			newenquirypage.interactWithCustomerEnquiryPopupIframeElement();
			String mobilenumber = "9443555576";
			newenquirypage.enterMobileNumber(mobilenumber);
			System.out.println("Entered Mobile Number: " + mobilenumber);
		} catch (Exception e) {
			System.err.println("Error during entering Mobile Number: " + e.getMessage());
		}
	}

	@Then("User should be able to see toast message to Create a new Enquiry")
	public void user_should_be_able_to_see_toast_message_to_create_a_new_enquiry() {
		try {
			String actualMessage = newenquirypage.getNewMobileNumberToast();
			String expectedMessage = "Create a new customer with no phone number verification.";
			// Validate that the actual message matches the expected message
			Assert.assertEquals(actualMessage, expectedMessage, "Toast message mismatch!");

			// Log success message
			System.out.println("Toast message validated successfully: " + actualMessage);
		} catch (Exception e) {
			System.err.println("Error during toast message validation: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@When("I enters valid data in Email id")
	public void i_enters_valid_data_in_email_id() {
		try {
			Thread.sleep(5000);
			String email = "Jeeva99n312@gmail.com";
			newenquirypage.enterEmail(email);
			System.out.println("Entered Email: " + email);
		} catch (Exception e) {
			System.err.println("Error during entering Email: " + e.getMessage());
		}
	}

	

	@Given("user is in Sales Customer Enquiry Pop-up")
	public void user_is_in_sales_customer_enquiry_pop_up() {
		try {
			Assert.assertTrue(newenquirypage.isSalesCustomerEnquiryPopupDisplayed(),
					"Sales Customer Enquiry pop-up is not displayed.");
			System.out.println("Sales Customer Enquiry pop-up is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Sales Customer Enquiry pop-up: " + e.getMessage());
			Assert.fail("Sales Customer Enquiry pop-up verification failed.");
		}
	}

	@Given("I selects valid data in Cust. Type")
	public void i_selects_valid_data_in_cust_type() {
		try {
				Thread.sleep(2000);
				newenquirypage.selectCustTypeField("Individual");
			} catch (Exception e) {
				System.err.println("Error during entering & selecting Cust Type: " + e.getMessage());
			}
		}
			
	@Given("I enters valid data in Cust. Name")
	public void i_enters_valid_data_in_cust_name() {
		try {
			Thread.sleep(2000);
			String custname = "Kumar raju";
			newenquirypage.enterCustName(custname);
			System.out.println("Entered Cust Name: " + custname);
		} catch (Exception e) {
			System.err.println("Error during entering Cust Name: " + e.getMessage());
		}
	}

	@Given("I enters valid data in Residence Phone No")
	public void i_enters_valid_data_in_residence_phone_no() {
		try {
			Thread.sleep(2000);
			String residencephone = "8897239872";
			newenquirypage.enterResidencePhone(residencephone);
			System.out.println("Entered Residence Phone: " + residencephone);
		} catch (Exception e) {
			System.err.println("Error during entering ResidencePhone: " + e.getMessage());
		}
	}

	@Given("I enters valid data in WhatsApp ID")
	public void i_enters_valid_data_in_whats_app_id() {
		try {
			Thread.sleep(2000);
			String whatsappid = "1234566654";
			newenquirypage.enterWhatsAppId(whatsappid);
			System.out.println("Entered WhatsApp Id: " + whatsappid);
		} catch (Exception e) {
			System.err.println("Error during entering WhatsApp Id: " + e.getMessage());
		}
	}

	@Given("I selects valid data Gender")
	public void i_selects_valid_data_gender() {
		try {
			Thread.sleep(2000);
			newenquirypage.selectGender("Male");
		} catch (Exception e) {
			System.err.println("Error during Gender selection: " + e.getMessage());
		}
	}
	
	@Given("I selects valid data in PIN")
	public void i_selects_valid_data_in_pin() {
		try {
			user_clicks_on_pincode_search_icon_beside_the_pin_field();
			user_tries_to_enter_pincode_in_pincode_field();
			user_tries_to_clicks_on_search_button_in_pincode_search_screen();
			user_tries_to_select_one_pincode_from_the_list();
			user_tries_to_clicks_on_add_selected_button_in_pincode_search_screen();
		} catch (Exception e) {
			System.err.println("Error during selecting a Pincode: " + e.getMessage());
		}
	}

	@Given("I enters valid data in Address")
	public void i_enters_valid_data_in_address() {
		try {
			Thread.sleep(2000);
			String address = "Hyderabad";
			newenquirypage.enterAddress(address);
			System.out.println("Entered Address: " + address);
		} catch (Exception e) {
			System.err.println("Error during entering Address: " + e.getMessage());
		}
	}

	@Given("I enters valid data in Village")
	public void i_enters_valid_data_in_village() {
		try {
			Thread.sleep(2000);
			String village = "Ramanthapur";
			newenquirypage.enterVillage(village);
			System.out.println("Entered Village: " + village);
		} catch (Exception e) {
			System.err.println("Error during entering Village: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Location")
	public void i_selects_valid_data_in_location() {
		try {
			Thread.sleep(2000);
			newenquirypage.selectLocation("HYDERABAD");
		} catch (Exception e) {
			System.err.println("Error during Location selection: " + e.getMessage());
		}
	}

	@Given("I selects valid data Enquiry Source")
	public void i_selects_valid_data_enquiry_source() {
		try {
			Thread.sleep(2000);
			newenquirypage.selectEnquirySource("Field Generation");
		} catch (Exception e) {
			System.err.println("Error during Enquiry Source selection: " + e.getMessage());
		}
	}
	
	@Given("I selects valid data Enquiry Sub Source")
	public void i_selects_valid_data_enquiry_sub_source() {
		try {
			Thread.sleep(2000);
			newenquirypage.selectEnquirySubSource("Mega Exchange Camp");
		} catch (Exception e) {
			System.err.println("Error during Enquiry Sub Source selection: " + e.getMessage());
		}
	}

	@Given("I selects valid data Enquiry Category")
	public void i_selects_valid_data_enquiry_category() {
		try {
			Thread.sleep(2000);
			newenquirypage.selectEnquiryCategory("Individual");
		} catch (Exception e) {
			System.err.println("Error during Enquiry Category selection: " + e.getMessage());
		}
	}

	@Given("I selects valid data Person In Charge")
	public void i_selects_valid_data_person_in_charge() {
		try {
			Thread.sleep(2000);
			newenquirypage.selectPersonInCharge("ABHINAY CHARY TANKARA");
		} catch (Exception e) {
			System.err.println("Error during Person In Charge selection: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Model")
	public void i_selects_valid_data_in_model() {
		try {
			Thread.sleep(2000);
			newenquirypage.selectModel("Grand i10 NIOS");
		} catch (Exception e) {
			System.err.println("Error during Model selection: " + e.getMessage());
		}
	}

	@Given("I selects valid data Fuel Type")
	public void i_selects_valid_data_fuel_type() {
		try {
			Thread.sleep(2000);
			newenquirypage.selectFuelType("Petrol");
		} catch (Exception e) {
			System.err.println("Error during Fuel Type selection: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Variant")
	public void i_selects_valid_data_in_variant() {
		try {
			Thread.sleep(2000);
			newenquirypage.selectVariant("GL");
		} catch (Exception e) {
			System.err.println("Error during Variant selection: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Sub Variant")
	public void i_selects_valid_data_in_sub_variant() {
		try {
			Thread.sleep(2000);
			newenquirypage.selectSubVariant("Grand i10 NIOS 1.2AMT Corporate(HQS6K361L D0QL)");
		} catch (Exception e) {
			System.err.println("Error during Sub Variant selection: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Ext Color")
	public void i_selects_valid_data_in_ext_color() {
		try {
			Thread.sleep(2000);
			newenquirypage.selectExtColor("AMAZON GRAY");
		} catch (Exception e) {
			System.err.println("Error during Ext Color selection: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Int Color")
	public void i_selects_valid_data_in_int_color() {
		try {
			Thread.sleep(2000);
			newenquirypage.selectIntColor("LIGHT SHALE GRAY");
		} catch (Exception e) {
			System.err.println("Error during Int Color selection: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Sales Consultant")
	public void i_selects_valid_data_in_sales_consultant() {
		try {
			Thread.sleep(2000);
			newenquirypage.selectSalesConsultant("BEHARA ARUN KUMAR [ES52370151]");
		} catch (Exception e) {
			System.err.println("Error during Sales Consultant selection: " + e.getMessage());
		}
	}

	@When("I click on the Save button")
	public void i_click_on_the_save_button() {
		try {
			newenquirypage.interactWithIframeElement();
			newenquirypage.interactWithCustomerEnquiryPopupIframeElement();
			newenquirypage.clickSaveButton();
			System.out.println("Save button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Save button click: " + e.getMessage());
		}
	}

	@Then("User should be able to see a Toast Message as Successfully reflected Sales Customer Enquiry Popup")
	public void user_should_be_able_to_see_a_toast_message_as_successfully_reflected_sales_customer_enquiry_Popup() {
		try {
			String actualMessage = newenquirypage.getEnquiryAddedToast();
			String expectedMessage = "Successfully reflected.";
			// Validate that the actual message matches the expected message
			Assert.assertEquals(actualMessage, expectedMessage, "Toast message mismatch!");

			// Log success message
			System.out.println("Toast message validated successfully: " + actualMessage);
		} catch (Exception e) {
			System.err.println("Error during toast message validation: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Then("User Should be able to navigate to the Lead Tab in the Sales Customer Enquiry screen")
	public void user_should_be_able_to_navigate_to_the_lead_tab_in_the_sales_customer_enquiry_screen() {
		try {
			Thread.sleep(2000);
			LaunchDriver.getDriver().switchTo().defaultContent();
			Assert.assertTrue(newenquirypage.isCustomerEnquiryScreenDisplayed(), "Lead Tab is not displayed.");
			System.out.println("Lead Tab is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Lead Tab: " + e.getMessage());
			Assert.fail("Lead Tab verification failed.");
		}
	}

	@Given("User is in Lead Tab in the Sales Customer Enquiry screen")
	public void user_is_in_lead_tab_in_the_sales_customer_enquiry_screen() {
		try {
			Assert.assertTrue(newenquirypage.isCustomerEnquiryScreenDisplayed(), "Lead Tab is not displayed.");
			System.out.println("Lead Tab is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Lead Tab: " + e.getMessage());
			Assert.fail("Lead Tab verification failed.");
		}
	}

	@Given("User tries to enters the Customer Name in Customer Name filter")
	public void user_tries_to_enters_the_customer_name_in_customer_name_filter() {
		try {
			Thread.sleep(2000);
			newenquirypage.interactWithIframeElement();
			String custnamefilter = "Kumar raju";
			newenquirypage.enterCustNameFilter(custnamefilter);
			System.out.println("Entered Customer Name filter: " + custnamefilter);
		} catch (Exception e) {
			System.err.println("Error during entering Customer Name filter: " + e.getMessage());
		}
	}

	@Given("User tries to clicks on the search button in Lead in the Sales Customer Enquiry screen")
	public void user_tries_to_clicks_on_the_search_button_in_lead_in_the_sales_customer_enquiry_screen() {
		try {
			Thread.sleep(2000);
			newenquirypage.clickleadTabSearchButton();
			System.out.println("lead Tab Search button clicked.");
		} catch (Exception e) {
			System.err.println("Error during lead Tab Search button click: " + e.getMessage());
		}LaunchDriver.getDriver().switchTo().defaultContent();
	}
	
	@Given("User tries to double click on the Repective Enquiry")
	public void user_tries_to_double_click_on_the_repective_enquiry() {
		try {
			Thread.sleep(2000);
			newenquirypage.interactWithIframeElement();
			newenquirypage.clickEnquiryFollowUpButton();
			System.out.println("Enquiry FollowUp Button clicked.");
		} catch (Exception e) {
			System.err.println("Error in Enquiry FollowUp Button the respective enquiry: " + e.getMessage());
		}
	}

	@Then("User Should be able to navigate to the Basic Info tab in the Sales Customer Enquiry Info screen")
	public void user_should_be_able_to_navigate_to_the_basic_info_tab_in_the_sales_customer_enquiry_info_screen() {
		try {
			Thread.sleep(2000);
			newenquirypage.interactWithIframeElement();
			newenquirypage.interactWithCustomerEnquiryPopupIframeElement();
			Assert.assertTrue(newenquirypage.isBasicInfoTabDisplayed(), "Basic Info tab is not displayed.");
			System.out.println("Basic Info tab is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Basic Info tab: " + e.getMessage());
			Assert.fail("Basic Info tab verification failed.");
		}
	}

	@Then("User tries to selects valid data in TD Offer dropdown")
	public void user_tries_to_selects_valid_data_in_td_offer_dropdown() {
		try {
			Thread.sleep(2000);
			newenquirypage.selectTDOfferField("Yes");
		} catch (Exception e) {
			System.err.println("Error during TD Offer selection: " + e.getMessage());
		}
	}


	@Then("User tries to selects valid data in TD VIN dropdown")
	public void user_tries_to_selects_valid_data_in_td_vin_dropdown() {
		try {
			Thread.sleep(2000);
			newenquirypage.selectTDVINField("MALB551CYPM400978-Grand i10 NIOS");
		} catch (Exception e) {
			System.err.println("Error during TD VIN selection: " + e.getMessage());
		}
	}

	@Then("User tries to clicks on Save button in the Basic Info tab in the Sales Customer Enquiry Info screen")
	public void user_tries_to_clicks_on_save_button_in_the_basic_info_tab_in_the_sales_customer_enquiry_info_screen() {
		try {
			newenquirypage.clickBasicInfoTabSaveButton();
			System.out.println("Basic Info tab Save Button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Basic Info tab Save Button click: " + e.getMessage());
		}
	}

	@Given("User is in the Basic Info tab in the Sales Customer Enquiry Info screen")
	public void user_is_in_the_basic_info_tab_in_the_sales_customer_enquiry_info_screen() {
		try {
			Assert.assertTrue(newenquirypage.isBasicInfoTabDisplayed(), "Basic Info tab is not displayed.");
			System.out.println("Basic Info tab is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Basic Info tab: " + e.getMessage());
			Assert.fail("Basic Info tab verification failed.");
		}
	}

	@Given("User tries to clicks on Test Drive Appointment button in the Basic Info tab in the Sales Customer Enquiry Info screen")
	public void user_tries_to_clicks_on_test_drive_appointment_button_in_the_basic_info_tab_in_the_sales_customer_enquiry_info_screen() {
		try {
			newenquirypage.clickTestDriveAppointmentButton();
			System.out.println("Test Drive Appointment Button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Test Drive Appointment Button click: " + e.getMessage());
		}
	}

	@Then("User Should be able to navigate to the Test Drive Appointment Screen")
	public void user_should_be_able_to_navigate_to_the_test_drive_appointment_screen() {
		try {
			Assert.assertTrue(newenquirypage.isTestDriveAppointmentScreenDisplayed(),
					"Test Drive Appointment Screen is not displayed.");
			System.out.println("Test Drive Appointment Screen is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Test Drive Appointment Screen: " + e.getMessage());
			Assert.fail("Test Drive Appointment Screen verification failed.");
		}
	}

	@Then("User Tries to clicks on the Any one of the Time Slot which was less than Current Time")
	public void user_tries_to_clicks_on_the_any_one_of_the_time_slot_which_was_less_than_current_time() {
		try {
			newenquirypage.clickTestDriveAppointmentOldDateSlot();
			newenquirypage.clickTestDriveAppointmentOldDateSlot();
			System.out.println("Time Slot which was less than Current Time clicked.");
		} catch (Exception e) {
			System.err.println("Error during Time Slot which was less than Current Time click: " + e.getMessage());
		}
	}

	@Then("User should be able to see a Toast Message as Selected time should be greater than current time")
	public void user_should_be_able_to_see_a_toast_message_as_selected_time_should_be_greater_than_current_time() {
		try {
			String actualMessage = newenquirypage.getTestDriveAppointmentInvalidDateToast();
			String expectedMessage = "Selected time should be greater than current time.";
			// Validate that the actual message matches the expected message
			Assert.assertEquals(actualMessage, expectedMessage, "Toast message mismatch!");

			// Log success message
			System.out.println("Toast message validated successfully: " + actualMessage);
		} catch (Exception e) {
			System.err.println("Error during toast message validation: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Given("User is in the Test Drive Appointment Screen")
	public void user_is_in_the_test_drive_appointment_screen() {
		try {
			Assert.assertTrue(newenquirypage.isTestDriveAppointmentScreenDisplayed(),
					"Test Drive Appointment Screen is not displayed.");
			System.out.println("Test Drive Appointment Screen is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Test Drive Appointment Screen: " + e.getMessage());
			Assert.fail("Test Drive Appointment Screen verification failed.");
		}
	}

	@When("User tries to clicks on Save button in the Test Drive Appointment Screen")
	public void user_tries_to_clicks_on_save_button_in_the_test_drive_appointment_screen() {
		try {
			newenquirypage.clickTestDriveAppointmentSaveButton();
			System.out.println("Test Drive Appointment Save Button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Test Drive Appointment Save Button click: " + e.getMessage());
		}
	}

	@Then("User should be able to see a Toast Message as Please select Appointment")
	public void user_should_be_able_to_see_a_toast_message_as_please_select_appointment() {
		try {
			String actualMessage = newenquirypage.getTestDriveAppointmentEmptyDateToast();
			String expectedMessage = "Please select Appointment.";
			// Validate that the actual message matches the expected message
			Assert.assertEquals(actualMessage, expectedMessage, "Toast message mismatch!");

			// Log success message
			System.out.println("Toast message validated successfully: " + actualMessage);
		} catch (Exception e) {
			System.err.println("Error during toast message validation: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@When("User tries to selects A valid Time solt Which was greater than the Current time")
	public void user_tries_to_selects_a_valid_time_solt_which_was_greater_than_the_current_time() {
		try {
			newenquirypage.clickTestDriveAppointmentValidDateSlot();
			newenquirypage.clickTestDriveAppointmentValidDateSlot();
			System.out.println("Valid date Time Slot clicked.");
		} catch (Exception e) {
			System.err.println("Error during Valid date Time Slot click: " + e.getMessage());
		}
	}

	@Then("User should be able to see a Toast Message as Successfully reflected in Test Drive Appointment Screen")
	public void user_should_be_able_to_see_a_toast_message_as_successfully_reflected_in_test_drive_appointment_screen() {
		try {
			String actualMessage = newenquirypage.getTestDriveAppointmentEmptyDateToast();
			String expectedMessage = "Successfully reflected.";
			// Validate that the actual message matches the expected message
			Assert.assertEquals(actualMessage, expectedMessage, "Toast message mismatch!");

			// Log success message
			System.out.println("Toast message validated successfully: " + actualMessage);
		} catch (Exception e) {
			System.err.println("Error during toast message validation: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Given("User tries to clicks on the Close Icon on the Test Drive Appointment Tab")
	public void user_tries_to_clicks_on_the_close_icon_on_the_test_drive_appointment_tab() {
		try {
			newenquirypage.clickTestDriveAppointmentTabCloseIcon();
			System.out.println("Test Drive Appointment Tab Close Icon clicked.");
		} catch (Exception e) {
			System.err.println("Error during Test Drive Appointment Tab Close Icon click: " + e.getMessage());
		}
	}

	@Then("User tries to clicks on the Follow Up tab in the Sales Customer Enquiry Info screen")
	public void user_tries_to_clicks_on_the_follow_up_tab_in_the_sales_customer_enquiry_info_screen() {
		try {
			newenquirypage.clickFollowUpTab();
			System.out.println("Follow Up tab clicked.");
		} catch (Exception e) {
			System.err.println("Error during Follow Up tab click: " + e.getMessage());
		}
	}

	@Then("User Should be able to navigate to the Follow Up tab in the Sales Customer Enquiry Info screen")
	public void user_should_be_able_to_navigate_to_the_follow_up_tab_in_the_sales_customer_enquiry_info_screen() {
		try {
			Assert.assertTrue(newenquirypage.isFollowUpTabScreenDisplayed(), "Follow Up tab is not displayed.");
			System.out.println("Follow Up tab is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Follow Up tab: " + e.getMessage());
			Assert.fail("Follow Up tab verification failed.");
		}
	}

	@Then("User tries to clicks on Save button in the Follow Up tab in the Sales Customer Enquiry Info screen")
	public void user_tries_to_clicks_on_save_button_in_the_follow_up_tab_in_the_sales_customer_enquiry_info_screen() {
		try {
			newenquirypage.clickFollowUpTabSaveButton();
			System.out.println("Follow Up Tab Save Button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Follow Up Tab Save Button click: " + e.getMessage());
		}
	}

	@Then("User should be able to see a Popup Message as Please select Next Follow Up Time")
	public void user_should_be_able_to_see_a_popup_message_as_please_select_next_follow_up_time() {
		try {
			String actualMessage = newenquirypage.getNextFollowUpTimeToast();
			String expectedMessage = "Please select Next Follow Up Time.";
			// Validate that the actual message matches the expected message
			Assert.assertEquals(actualMessage, expectedMessage, "Toast message mismatch!");

			// Log success message
			System.out.println("Toast message validated successfully: " + actualMessage);
		} catch (Exception e) {
			System.err.println("Error during toast message validation: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Then("User tries to selects valid data in Next Follow Up Time")
	public void user_tries_to_selects_valid_data_in_next_follow_up_time() {
		try {
			Thread.sleep(2000);
			String nextfollowupdatetime = "28/12/2024 18:55";
			newenquirypage.enterNextFollowUpDateTime(nextfollowupdatetime);
			System.out.println("Entered Scheme Offered: " + nextfollowupdatetime);
		} catch (Exception e) {
			System.err.println("Error during entering Next Follow Up Time: " + e.getMessage());
		}
	}

	@Then("User should be able to see a Popup Message as Please select Next Follow Up Type")
	public void user_should_be_able_to_see_a_popup_message_as_please_select_next_follow_up_type() {
		try {
			String actualMessage = newenquirypage.getNextFollowUpTypeToast();
			String expectedMessage = "Please select Next Follow Up Type.";
			// Validate that the actual message matches the expected message
			Assert.assertEquals(actualMessage, expectedMessage, "Toast message mismatch!");

			// Log success message
			System.out.println("Toast message validated successfully: " + actualMessage);
		} catch (Exception e) {
			System.err.println("Error during toast message validation: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Then("User tries to selects valid data in Next Follow Up Type")
	public void user_tries_to_selects_valid_data_in_next_follow_up_type() {
		try {
			newenquirypage.selectNextFollowUpType("Phone");
			System.out.println("Next Follow Up Type successfully selected: + Phone");
		} catch (Exception e) {
			System.err.println("Error during Next Follow Up Type selection: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Then("User should be able to see a Popup Message as Please select Enquiry Type")
	public void user_should_be_able_to_see_a_popup_message_as_please_select_enquiry_type() {
		try {
			String actualMessage = newenquirypage.getEnquiryTypeToast();
			String expectedMessage = "Please select Enquiry Type.";
			// Validate that the actual message matches the expected message
			Assert.assertEquals(actualMessage, expectedMessage, "Toast message mismatch!");

			// Log success message
			System.out.println("Toast message validated successfully: " + actualMessage);
		} catch (Exception e) {
			System.err.println("Error during toast message validation: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Then("User tries to selects valid data in Enquiry Type")
	public void user_tries_to_selects_valid_data_in_enquiry_type() {
		try {
			newenquirypage.selectNextFollowUpType("Phone");
			System.out.println("Next Follow Up Type successfully selected: + Phone");
		} catch (Exception e) {
			System.err.println("Error during Next Follow Up Type selection: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Then("User should be able to see a Popup Message as Please select Verification")
	public void user_should_be_able_to_see_a_popup_message_as_please_select_verification() {
		try {
			String actualMessage = newenquirypage.getVerificationToast();
			String expectedMessage = "Please select Verification.";
			// Validate that the actual message matches the expected message
			Assert.assertEquals(actualMessage, expectedMessage, "Toast message mismatch!");

			// Log success message
			System.out.println("Toast message validated successfully: " + actualMessage);
		} catch (Exception e) {
			System.err.println("Error during toast message validation: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Then("User tries to selects valid data in Verification")
	public void user_tries_to_selects_valid_data_in_verification() {
		try {
			newenquirypage.selectVerification("Y");
			System.out.println("Verification successfully selected: + Y");
		} catch (Exception e) {
			System.err.println("Error during Verification selection: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Given("User is in the Follow Up tab with Valid data in the Sales Customer Enquiry Info screen")
	public void user_is_in_the_follow_up_tab_with_valid_data_in_the_sales_customer_enquiry_info_screen() {
		try {
			Assert.assertTrue(newenquirypage.isFollowUpTabScreenDisplayed(), "Follow Up tab is not displayed.");
			System.out.println("Follow Up tab is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Follow Up tab: " + e.getMessage());
			Assert.fail("Follow Up tab verification failed.");
		}
	}

	@When("User tries to enters valid data in Scheme Offered")
	public void user_tries_to_enters_valid_data_in_scheme_offered() {
		try {
			Thread.sleep(2000);
			String schemeoffered = "Bhamaka Bhonanja";
			newenquirypage.enterSchemeOffered(schemeoffered);
			System.out.println("Entered Scheme Offered: " + schemeoffered);
		} catch (Exception e) {
			System.err.println("Error during entering Scheme Offered: " + e.getMessage());
		}
	}

	@When("User tries to enters valid data in Follow Up Remarks")
	public void user_tries_to_enters_valid_data_in_follow_up_remarks() {
		try {
			Thread.sleep(2000);
			String followupremarks = "Everything was fine";
			newenquirypage.enterFollowUpRemarks(followupremarks);
			System.out.println("Entered Follow Up Remarks: " + followupremarks);
		} catch (Exception e) {
			System.err.println("Error during entering Follow Up Remarks: " + e.getMessage());
		}
	}

	@Then("User should be able to see a Toast Message as Successfully reflected in Follow Up tab")
	public void user_should_be_able_to_see_a_toast_message_as_successfully_reflected_in_follow_up_tab() {
		try {
			String actualMessage = newenquirypage.getTestDriveAppointmentEmptyDateToast();
			String expectedMessage = "Successfully reflected.";
			// Validate that the actual message matches the expected message
			Assert.assertEquals(actualMessage, expectedMessage, "Toast message mismatch!");

			// Log success message
			System.out.println("Toast message validated successfully: " + actualMessage);
		} catch (Exception e) {
			System.err.println("Error during toast message validation: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@When("user tries to close the google chrome browser")
	public void user_tries_to_close_the_google_chrome_browser() throws Throwable {
		try {
			LaunchDriver.tearDown();
		} catch (Exception e) {
			throw new Exception("Error occurred while Closing the browser : " + e.getMessage());
		}
	}
}
