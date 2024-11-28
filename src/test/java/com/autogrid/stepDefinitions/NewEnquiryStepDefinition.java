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
import java.util.Scanner;

public class NewEnquiryStepDefinition {
CommonActions commonActions;
	
	DMSLoginPage dMSLoginPage;
	NewEnquiryPage newenquirypage;

    public NewEnquiryStepDefinition(){
		WebDriver driver = LaunchDriver.getDriver();
		this.newenquirypage = new NewEnquiryPage(driver);
		PageFactory.initElements(driver, newenquirypage);
    }

	@When("User clicks Sales Menu Item")
	public void user_clicks_sales_menu_item() {
		try {
			newenquirypage.clickSalesMenu();
            System.out.println("Sales Menu clicked.");
        } catch (Exception e) {
            System.err.println("Error during Sales Menu click: " + e.getMessage());
        }
    }

	@When("User clicks on the Customer Enquiry Sub Menu Item")
	public void user_clicks_on_the_customer_enquiry_sub_menu_item() {
		try {
			newenquirypage.clickCustomerEnquirySubmenu();
            System.out.println("Customer Enquiry Sub Menu Item clicked.");
        } catch (Exception e) {
            System.err.println("Error during Customer Enquiry Sub Menu Item click: " + e.getMessage());
        }
    }

	@When("User clicks on the Customer Enquiry link")
	public void user_clicks_on_the_customer_enquiry_link() {
		try {
			newenquirypage.clickCustomerEnquiryLink();
            System.out.println("Customer Enquiry Link clicked.");
        } catch (Exception e) {
            System.err.println("Error during Customer Enquiry Link click: " + e.getMessage());
        }
    }

	@When("User clicks on New button")
	public void user_clicks_on_new_button() {
		try {
			newenquirypage.clickNewEnquiryButton();
            System.out.println("New Enquiry Button clicked.");
        } catch (Exception e) {
            System.err.println("Error during New Enquiry Button click: " + e.getMessage());
        }
    }

	@Then("User should be able to navigate Sales Customer Enquiry Pop-up")
	public void user_should_be_able_to_navigate_sales_customer_enquiry_pop_up() {
		try {
            Assert.assertTrue(newenquirypage.isSalesCustomerEnquiryPopupDisplayed(), "Sales Customer Enquiry pop-up is not displayed.");
            System.out.println("Sales Customer Enquiry pop-up is displayed.");
        } catch (Exception e) {
            System.err.println("Error verifying Sales Customer Enquiry pop-up: " + e.getMessage());
            Assert.fail("Sales Customer Enquiry pop-up verification failed.");
        }
    }

	@Then("User should be able to see the UI with Header as Sales Customer Enquiry")
	public void user_should_be_able_to_see_the_ui_with_header_as_sales_customer_enquiry() {
		try {
            Assert.assertTrue(newenquirypage.isSalesCustomerEnquiryPopupDisplayed(), "Sales Customer Enquiry pop-up is not displayed.");
            System.out.println("Sales Customer Enquiry pop-up is displayed.");
        } catch (Exception e) {
            System.err.println("Error verifying Sales Customer Enquiry pop-up: " + e.getMessage());
            Assert.fail("Sales Customer Enquiry pop-up verification failed.");
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

	@Then("User should be able to navigate to the Customer Enquiry screen")
	public void user_should_be_able_to_navigate_to_the_customer_enquiry_screen() {
		try {
            Assert.assertTrue(newenquirypage.isCustomerEnquiryScreenDisplayed(), "Customer Enquiry Screen is not displayed.");
            System.out.println("Customer Enquiry Screen is displayed.");
        } catch (Exception e) {
            System.err.println("Error verifying Customer Enquiry Screen pop-up: " + e.getMessage());
            Assert.fail("Customer Enquiry Screen verification failed.");
        }
    }

	@When("User should be able to view the Cross Mark in Sales Customer Enquiry Pop-up")
	public void user_should_be_able_to_view_the_cross_mark_in_sales_customer_enquiry_pop_up() {
		try {
            Assert.assertTrue(newenquirypage.isPromotionCrossIconDisplayed(), "Promotion Cross Icon is not displayed.");
            System.out.println("Promotion Cross Icon is displayed.");
        } catch (Exception e) {
            System.err.println("Error verifying Promotion Cross Icon : " + e.getMessage());
            Assert.fail("Promotion Cross Icon verification failed.");
        }
    }

	@When("User clicks on the Cross Mark in Sales Customer Enquiry Pop-up")
	public void user_clicks_on_the_cross_mark_in_sales_customer_enquiry_pop_up() {
		try {
			newenquirypage.clickPromotionCrossIcon();
            System.out.println("Promotion Cross Icon clicked.");
        } catch (Exception e) {
            System.err.println("Error during Promotion Cross Icon click: " + e.getMessage());
        }
    }

	@Then("User should be able to Downloaded and navigate to Sales Customer Enquiry Pop-up")
	public void user_should_be_able_to_downloaded_and_navigate_to_sales_customer_enquiry_pop_up() {
		try {
            Assert.assertTrue(newenquirypage.isSalesCustomerEnquiryPopupDisplayed(), "Sales Customer Enquiry pop-up is not displayed.");
            System.out.println("Sales Customer Enquiry pop-up is displayed.");
        } catch (Exception e) {
            System.err.println("Error verifying Sales Customer Enquiry pop-up: " + e.getMessage());
            Assert.fail("Sales Customer Enquiry pop-up verification failed.");
        }
    }

	@When("User clicks on Pincode Search Icon from PIN field")
	public void user_clicks_on_pincode_search_icon_from_pin_field() {
		try {
			newenquirypage.clickPincodeSearchIcon();
            System.out.println("Pincode Search Icon clicked.");
        } catch (Exception e) {
            System.err.println("Error during Pincode Search Icon click: " + e.getMessage());
        }
    }

	@Then("User should be able to navigate to the Pincode search Screen")
	public void user_should_be_able_to_navigate_to_the_pincode_search_screen() {
		try {
            Assert.assertTrue(newenquirypage.isPincodeSearchScreenDisplayed(), "Promotion Cross Icon is not displayed.");
            System.out.println("Pincode search Screen is displayed.");
        } catch (Exception e) {
            System.err.println("Error verifying Pincode search Screen : " + e.getMessage());
            Assert.fail("Pincode search Screen verification failed.");
        }
    }

	@Then("User tries to enter Pincode in pin code field")
	public void user_tries_to_enter_pincode_in_pincode_field() {
		try {
			Thread.sleep(2000);
            String pincode = "500018";
            newenquirypage.enterPincode(pincode);
            System.out.println("Entered Pincode: " + pincode);
        } catch (Exception e) {
            System.err.println("Error during entering Pincode: " + e.getMessage());
        }
	}
	
	@Then("User tries to clicks on search button in Pin code search Screen")
	public void user_tries_to_clicks_on_search_button_in_pin_code_search_screen() {
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
	public void user_tries_to_clicks_on_ads_selected_button_in_pincode_search_screen() {
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
            String expectedPincode = "500018";
            String actualPincode = newenquirypage.getPincode();
            Assert.assertEquals(actualPincode, expectedPincode, "Pincode mismatch!");
            System.out.println("Verification successful: Pincode is displayed correctly.");
        } catch (Exception e) {
            System.err.println("An error occurred during Pincode verification: " + e.getMessage());
            e.printStackTrace();
        }
    }

	@Then("User tries to selects State from state field")
	public void user_tries_to_selects_state_from_state_field() {
		try {
			newenquirypage.selectState("TELANGANA");
            System.out.println("state successfully selected: + TELANGANA");
        } catch (Exception e) {
            System.err.println("Error during state selection: " + e.getMessage());
            e.printStackTrace();
        }
    }

	@Then("User tries to selects District from District field")
	public void user_tries_to_selects_district_from_district_field() {
		try {
			newenquirypage.selectDistrict("K.V.Rangareddy");
            System.out.println("District successfully selected: + K.V.Rangareddy");
        } catch (Exception e) {
            System.err.println("Error during District selection: " + e.getMessage());
            e.printStackTrace();
        }
    }

	@Then("User tries to selects Taluka or Tehsil from Taluka or Tehsil field")
	public void user_tries_to_selects_taluka_or_tehsil_from_taluka_or_tehsil_field() {
		try {
			newenquirypage.selectTaluka("Ameerpet");
            System.out.println("Taluka or Tehsil successfully selected: + Ameerpet");
        } catch (Exception e) {
            System.err.println("Error during Taluka or Tehsil selection: " + e.getMessage());
            e.printStackTrace();
        }
    }
	
	@Then("User tries to enters Post Office Name from Post Office Name field")
	public void user_tries_to_enters_post_office_name_from_post_office_name_field() {
		try {
			Thread.sleep(2000);
            String postofficename = "Bharat Nagar Colony S.O";
            newenquirypage.enterPincode(postofficename);
            System.out.println("Entered Post Office Name: " + postofficename);
        } catch (Exception e) {
            System.err.println("Error during entering Post Office Name: " + e.getMessage());
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

	@Then("User should be able to see toast message")
	public void user_should_be_able_to_see_toast_message() {
		try {
            String actualMessage = newenquirypage.getToastMessage();
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
            String expectedMessage = "1. Mobile No. should be 10 digit\r\n"
            		+ "2. Only Numeric digit allow\r\n"
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
            String mobilenumber = "9640884870";
            newenquirypage.enterMobileNumber(mobilenumber);
            System.out.println("Entered Mobile Number: " + mobilenumber);
        } catch (Exception e) {
            System.err.println("Error during entering Mobile Number: " + e.getMessage());
        }
	}

	@Then("User should be able to navigate to the Find A Customer Info Screen")
	public void user_should_be_able_to_navigate_to_the_find_a_customer_info_screen() {
		try {
            Assert.assertTrue(newenquirypage.isPincodeSearchScreenDisplayed(), "Promotion Cross Icon is not displayed.");
            System.out.println("Pincode search Screen is displayed.");
        } catch (Exception e) {
            System.err.println("Error verifying Pincode search Screen : " + e.getMessage());
            Assert.fail("Pincode search Screen verification failed.");
        }
    }

	@When("User tries to enters mobile number Which Not Registered")
	public void user_tries_to_enters_mobile_number_which_not_registered() {
		try {
			Thread.sleep(2000);
            String mobilenumber = "9640885655";
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

	@When("User tries to enters Invalid Email id")
	public void user_tries_to_enters_invalid_email_id() {
	    try {
			Thread.sleep(2000);
            String email = "9640885655";
            newenquirypage.enterEmail(email);
            System.out.println("Entered Email: " + email);
        } catch (Exception e) {
            System.err.println("Error during entering Email: " + e.getMessage());
        }
	}

	@Then("User should be able to see toast message to enter the valid email id")
	public void user_should_be_able_to_see_toast_message_to_enter_the_valid_email_id() {
		try {
            String actualMessage = newenquirypage.getNewMobileNumberToast();
            String expectedMessage = "This email id is invalid";
            // Validate that the actual message matches the expected message
            Assert.assertEquals(actualMessage, expectedMessage, "Toast message mismatch!");

            // Log success message
            System.out.println("Toast message validated successfully: " + actualMessage);
        } catch (Exception e) {
            System.err.println("Error during toast message validation: " + e.getMessage());
            e.printStackTrace();
        }
    }

	@Given("user is in Sales Customer Enquiry Pop-up")
	public void user_is_in_sales_customer_enquiry_pop_up() {
		try {
            Assert.assertTrue(newenquirypage.isSalesCustomerEnquiryPopupDisplayed(), "Sales Customer Enquiry pop-up is not displayed.");
            System.out.println("Sales Customer Enquiry pop-up is displayed.");
        } catch (Exception e) {
            System.err.println("Error verifying Sales Customer Enquiry pop-up: " + e.getMessage());
            Assert.fail("Sales Customer Enquiry pop-up verification failed.");
        }
    }

	@Given("I enters valid data in Mobile No.")
	public void i_enters_valid_data_in_mobile_no() {
		try {
			Thread.sleep(2000);
            String mobilenumber = "9640866655";
            newenquirypage.enterMobileNumber(mobilenumber);
            System.out.println("Entered Mobile Number: " + mobilenumber);
        } catch (Exception e) {
            System.err.println("Error during entering Mobile Number: " + e.getMessage());
        }
	}

	@Given("I enters valid data in E-mail")
	public void i_enters_valid_data_in_e_mail() {
		try {
			Thread.sleep(2000);
            String email = "Jeevan@gmail.com";
            newenquirypage.enterEmail(email);
            System.out.println("Entered Email: " + email);
        } catch (Exception e) {
            System.err.println("Error during entering Email: " + e.getMessage());
        }
	}

	@Given("I selects valid data in Cust. Type")
	public void i_selects_valid_data_in_cust_type() {
		// Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("I enters valid data in Cust. Name")
	public void i_enters_valid_data_in_cust_name() {
		try {
			Thread.sleep(2000);
            String custname = "Jeevan";
            newenquirypage.enterCustName(custname);
            System.out.println("Entered Cust Name: " + custname);
        } catch (Exception e) {
            System.err.println("Error during entering Cust Name: " + e.getMessage());
        }
	}

	@Given("I enters valid data in Residence Phone No.")
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
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("I selects valid data in PIN")
	public void i_selects_valid_data_in_pin() {
		try {
			user_clicks_on_pincode_search_icon_from_pin_field();
			user_tries_to_enter_pincode_in_pincode_field();
			user_tries_to_clicks_on_search_button_in_pin_code_search_screen();
			user_tries_to_select_one_pincode_from_the_list();
			user_tries_to_clicks_on_ads_selected_button_in_pincode_search_screen();
        } catch (Exception e) {
            System.err.println("Error during entering Pincode: " + e.getMessage());
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
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("I selects valid data Enquiry Source")
	public void i_selects_valid_data_enquiry_source() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("I selects valid data Enquiry Sub Source")
	public void i_selects_valid_data_enquiry_sub_source() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("I selects valid data Enquiry Category")
	public void i_selects_valid_data_enquiry_category() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("I selects valid data Person In Charge")
	public void i_selects_valid_data_person_in_charge() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("I selects valid data in Model")
	public void i_selects_valid_data_in_model() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("I selects valid data Fuel Type")
	public void i_selects_valid_data_fuel_type() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("I selects valid data in Variant")
	public void i_selects_valid_data_in_variant() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("I selects valid data in Sub Variant")
	public void i_selects_valid_data_in_sub_variant() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("I selects valid data in Ext Color")
	public void i_selects_valid_data_in_ext_color() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("I selects valid data in Int Color")
	public void i_selects_valid_data_in_int_color() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("I selects valid data in Sales Consultant")
	public void i_selects_valid_data_in_sales_consultant() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("I click on the Save button")
	public void i_click_on_the_save_button() {
		try {
			newenquirypage.clickSaveButton();
            System.out.println("Save button clicked.");
        } catch (Exception e) {
            System.err.println("Error during Save button click: " + e.getMessage());
        }
    }

	@Then("the enquiry is successfully submitted")
	public void the_enquiry_is_successfully_submitted() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
