package com.autogrid.stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.autogrid.steps.DMSLoginPage;
import com.autogrid.steps.InvoicePage;
import com.autogrid.steps.NewEnquiryPage;
import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;

public class InvoiceStepDefinition {
	CommonActions commonActions;
	DMSLoginPage dMSLoginPage;
	NewEnquiryPage newenquirypage;
	InvoicePage invoicepage;
	
	public InvoiceStepDefinition() {
		WebDriver driver = LaunchDriver.getDriver();
		this.invoicepage = new InvoicePage(driver);
		PageFactory.initElements(driver, invoicepage);
	}
	@Then("user should be able to navigate to the dashboard")
	public void user_should_be_able_to_navigate_to_the_dashboard() {
		try {
            Assert.assertTrue(dMSLoginPage.isHomepageIconDisplayed(), "Home Icon is not displayed on the dashboard.");
            System.out.println("Home Icon is displayed on the dashboard.");
        } catch (Exception e) {
            System.err.println("Error verifying Home Icon: " + e.getMessage());
            Assert.fail("Home Icon verification failed.");
        }
    }

	@Then("User clicks on the Sales Operation Sub Menu Item")
	public void user_clicks_on_the_sales_operation_sub_menu_item() {
		try {
			Thread.sleep(3000);
			invoicepage.clickSalesOperationSubmenu();
			System.out.println("Sales Operation Sub Menu Item clicked.");
		} catch (Exception e) {
			System.err.println("Error during Sales Operation Sub Menu Item click: " + e.getMessage());
		}
	}

	@Then("User clicks on the Customer Booking Mgt List link")
	public void user_clicks_on_the_customer_booking_mgt_list_link() {
		try {
			Thread.sleep(3000);
			invoicepage.clickCustomerBookingMgtListLink();
			System.out.println("Customer Booking Mgt List Link clicked.");
		} catch (Exception e) {
			System.err.println("Error during Customer Booking Mgt List Link click: " + e.getMessage());
		}
	}

	@Then("User should be able to navigate to the Customer Booking Mgt List Screen")
	public void user_should_be_able_to_navigate_to_the_customer_booking_mgt_list_screen() {
		try {
			Thread.sleep(3000);
			Assert.assertTrue(invoicepage.isCustomerBookingMgtListScreenDisplayed(),
					"Customer Booking Mgt List Screen is not displayed.");
			System.out.println("Customer Booking Mgt List Screen is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Customer Booking Mgt List Screen pop-up: " + e.getMessage());
			Assert.fail("Customer Booking Mgt List Screen verification failed.");
		}
	}

	@When("User tries to Selects Mobile Number in the Based On Auto-Suggestion in Customer Booking Mgt List Screen")
	public void user_tries_to_selects_mobile_number_in_the_based_on_auto_suggestion_in_customer_booking_mgt_list_screen() {
		try {
			Thread.sleep(3000);
			invoicepage.selectBasedOn("Mobile No");
		} catch (Exception e) {
			System.err.println("Error during entering & selecting Based On Name: " + e.getMessage());
		}
	}

	@When("User tries to enters Lead Mobile Number in the Based On Field")
	public void user_tries_to_enters_lead_mobile_number_in_the_based_on_field() {
		try {
			Thread.sleep(2000);
			String mobilefilter = "500018";
			invoicepage.enterMobileFilter(mobilefilter);
			System.out.println("Entered Lead Mobile Number: " + mobilefilter);
		} catch (Exception e) {
			System.err.println("Error during entering Lead Mobile Number: " + e.getMessage());
		}
	}

	@When("User tries to clicks on the Search button in Customer Booking Mgt List Screen")
	public void user_tries_to_clicks_on_the_search_button_in_customer_booking_mgt_list_screen() {
		try {
			Thread.sleep(2000);
			invoicepage.clickCustomerBookingMgtListSearch();
			System.out.println("Customer Booking Mgt List Search Button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Customer Booking Mgt List Search Button click: " + e.getMessage());
		}
	}

	@When("User tries to select Enquiry from the list after applying filters in Customer Booking Mgt List Screen")
	public void user_tries_to_select_enquiry_from_the_list_after_applying_filters_in_customer_booking_mgt_list_screen() {
		try {
			Thread.sleep(2000);
			invoicepage.clickEnquiryFromBookingMgtList();
			invoicepage.clickEnquiryFromBookingMgtList();
			System.out.println("Enquiry From Customer Booking Mgt List clicked.");
		} catch (Exception e) {
			System.err.println("Error during Enquiry From Customer Booking Mgt List click: " + e.getMessage());
		}
	}

	@Then("User should be able to navigate to the Customer Booking Management Screen")
	public void user_should_be_able_to_navigate_to_the_customer_booking_management_screen() {
		try {
			Thread.sleep(3000);
			Assert.assertTrue(invoicepage.isCustomerBookingMgtScreenDisplayed(),
					"Customer Booking Mgt Screen is not displayed.");
			System.out.println("Customer Booking Mgt Screen is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Customer Booking Mgt Screen pop-up: " + e.getMessage());
			Assert.fail("Customer Booking Mgt Screen verification failed.");
		}
	}
	
	@When("User tries to clicks on the Invoice Tab in the Customer Booking Management Screen")
	public void user_tries_to_clicks_on_the_invoice_tab_in_the_customer_booking_management_screen() {
		try {
			Thread.sleep(2000);
			invoicepage.clickInvoiceTab();
			System.out.println("Invoice Tab clicked.");
		} catch (Exception e) {
			System.err.println("Error during Invoice Tab click: " + e.getMessage());
		}
	}

	@When("User tries to clicks on Scheme button in the Invoice Tab in the Customer Booking Management Screen")
	public void user_tries_to_clicks_on_scheme_button_in_the_invoice_tab_in_the_customer_booking_management_screen() {
		try {
			Thread.sleep(2000);
			invoicepage.clickSchemeButton();
			System.out.println("Scheme button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Scheme button click: " + e.getMessage());
		}
	}

	@Then("User should be able to navigate to the Scheme Popup screen")
	public void user_should_be_able_to_navigate_to_the_scheme_popup_screen() {
		try {
			Thread.sleep(3000);
			Assert.assertTrue(invoicepage.isSchemePopupDisplayed(),
					"Scheme Popup is not displayed.");
			System.out.println("Scheme Popup is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Scheme Popup : " + e.getMessage());
			Assert.fail("Scheme Popup verification failed.");
		}
	}	

	@Then("User tries to enters valid data in the Payable By Dealer Amount in TAX Adjustment Allowed Table")
	public void user_tries_to_enters_valid_data_in_the_payable_by_dealer_amount_in_tax_adjustment_allowed_table() {
		try {
			Thread.sleep(2000);
			String PayableByDealerAmount = "500018";
			invoicepage.enterPayableByDealerAmountField(PayableByDealerAmount);
			System.out.println("Entered Payable By Dealer Amount: " + PayableByDealerAmount);
		} catch (Exception e) {
			System.err.println("Error during entering Payable By Dealer Amount: " + e.getMessage());
		}
	}

	@Then("User tries to enters valid data in the Adjustment Credit Note Amount in TAX Adjustment Allowed Table")
	public void user_tries_to_enters_valid_data_in_the_adjustment_credit_note_amount_in_tax_adjustment_allowed_table() {
		try {
			Thread.sleep(2000);
			String AdjustmentCreditNoteAmount = "500018";
			invoicepage.enterAdjustmentCreditNoteField(AdjustmentCreditNoteAmount);
			System.out.println("Entered Adjustment Credit Note Amount : " + AdjustmentCreditNoteAmount);
		} catch (Exception e) {
			System.err.println("Error during entering Adjustment Credit Note Amount : " + e.getMessage());
		}
	}

	@Then("User tries to enters valid data in the Basic Insurance Amount in Chargeable\\/Sharing Table")
	public void user_tries_to_enters_valid_data_in_the_basic_insurance_amount_in_chargeable_sharing_table() {
		try {
			Thread.sleep(2000);
			String BasicInsuranceAmount = "500018";
			invoicepage.enterBasicInsuranceAmountField(BasicInsuranceAmount);
			System.out.println("Entered Basic Insurance Amount: " + BasicInsuranceAmount);
		} catch (Exception e) {
			System.err.println("Error during entering Basic Insurance Amount: " + e.getMessage());
		}
	}

	@Then("User tries to enters valid data in the RTO Amount in Chargeable\\/Sharing Table")
	public void user_tries_to_enters_valid_data_in_the_rto_amount_in_chargeable_sharing_table() {
		try {
			Thread.sleep(2000);
			String RTOAmount = "500018";
			invoicepage.enterRTOAmountField(RTOAmount);
			System.out.println("Entered RTO Amount: " + RTOAmount);
		} catch (Exception e) {
			System.err.println("Error during entering RTO Amount: " + e.getMessage());
		}
	}

	@Then("User tries to enters valid data in the Road Tax Amount in Chargeable\\/Sharing Table")
	public void user_tries_to_enters_valid_data_in_the_road_tax_amount_in_chargeable_sharing_table() {
		try {
			Thread.sleep(2000);
			String RoadTaxAmount = "500018";
			invoicepage.enterRoadTaxAmountField(RoadTaxAmount);
			System.out.println("Entered Road Tax Amount: " + RoadTaxAmount);
		} catch (Exception e) {
			System.err.println("Error during entering Road Tax Amount: " + e.getMessage());
		}
	}

	@Then("User tries to enters valid data in the Other Charges Amount in Chargeable\\/Sharing Table")
	public void user_tries_to_enters_valid_data_in_the_other_charges_amount_in_chargeable_sharing_table() {
		try {
			Thread.sleep(2000);
			String OtherChargesAmount = "500018";
			invoicepage.enterOtherChargesAmountField(OtherChargesAmount);
			System.out.println("Entered Other Charges Amount: " + OtherChargesAmount);
		} catch (Exception e) {
			System.err.println("Error during entering Other Charges Amount: " + e.getMessage());
		}
	}

	@Then("User tries to enters valid data in the Life Tax Amount in Chargeable\\/Sharing Table")
	public void user_tries_to_enters_valid_data_in_the_life_tax_amount_in_chargeable_sharing_table() {
		try {
			Thread.sleep(2000);
			String LifeTaxAmount = "500018";
			invoicepage.enterLifeTaxAmountField(LifeTaxAmount);
			System.out.println("Entered Life Tax Amount: " + LifeTaxAmount);
		} catch (Exception e) {
			System.err.println("Error during entering Life Tax Amount: " + e.getMessage());
		}
	}

	@Then("User tries to clicks on Save button in Scheme Popup screen")
	public void user_tries_to_clicks_on_save_button_in_scheme_popup_screen() {
		try {
			Thread.sleep(2000);
			invoicepage.clickSchemePopupSaveButton();
			System.out.println("Save button in Scheme Popup screen clicked.");
		} catch (Exception e) {
			System.err.println("Error during Save button in Scheme Popup screen click: " + e.getMessage());
		}
	}

	@Then("User should be able to see Do you want to save it? Popup")
	public void user_should_be_able_to_see_do_you_want_to_save_it_popup() {
		try {
			Thread.sleep(3000);
			Assert.assertTrue(invoicepage.isSchemeSaveConfirmationPopupDisplayed(),
					"Do you want to save it? Popup is not displayed.");
			System.out.println("Do you want to save it? Popup is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Do you want to save it? Popup : " + e.getMessage());
			Assert.fail("Do you want to save it? Popup verification failed.");
		}
	}	

	@Then("User tries to clicks on Confirm button in Do you want to save it? Popup")
	public void user_tries_to_clicks_on_confirm_button_in_do_you_want_to_save_it_popup() {
		try {
			Thread.sleep(2000);
			invoicepage.clickSchemeSaveConfirmationConfirmButton();
			System.out.println("Confirm button in Do you want to save it? Popup screen clicked.");
		} catch (Exception e) {
			System.err.println("Error during Confirm button in Do you want to save it? Popup screen click: " + e.getMessage());
		}
	}

	@Then("User tries to clicks on Close button in Scheme Popup screen")
	public void user_tries_to_clicks_on_close_button_in_scheme_popup_screen() {
		try {
			Thread.sleep(2000);
			invoicepage.clickSchemePopupCloseButton();
			System.out.println("Close button in Scheme Popup screen clicked.");
		} catch (Exception e) {
			System.err.println("Error during Close button in Scheme Popup screen click: " + e.getMessage());
		}
	}

	@When("User tries to clicks on the More Promotions button in Basic info Section in the Customer Booking Management Screen")
	public void user_tries_to_clicks_on_the_more_promotions_button_in_basic_info_section_in_the_customer_booking_management_screen() {
		try {
			Thread.sleep(2000);
			invoicepage.clickMorePromotionsButton();
			System.out.println("More Promotions button clicked.");
		} catch (Exception e) {
			System.err.println("Error during More Promotions button click: " + e.getMessage());
		}
	}

	@Then("User should be able to see Promotions Section in the Customer Booking Management Screen")
	public void user_should_be_able_to_see_promotions_section_in_the_customer_booking_management_screen() {
		try {
			Thread.sleep(3000);
			Assert.assertTrue(invoicepage.isPromotionsSectionDisplayed(),
					"Promotions Section is not displayed.");
			System.out.println("Promotions Section is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Promotions Section : " + e.getMessage());
			Assert.fail("Promotions Section verification failed.");
		}
	}	
	
	@Then("User tries to clicks on the Plus icon in Promotions Section in the Customer Booking Management Screen")
	public void user_tries_to_clicks_on_the_plus_icon_in_promotions_section_in_the_customer_booking_management_screen() {
		try {
			Thread.sleep(2000);
			invoicepage.clickPromotionsSectionPlusIcon();
			System.out.println("Plus icon in Promotions Section clicked.");
		} catch (Exception e) {
			System.err.println("Error during Plus icon in Promotions Section click: " + e.getMessage());
		}
	}

	@Then("User should be able to navigate to the Promotion Pop-up")
	public void user_should_be_able_to_navigate_to_the_promotion_pop_up() {
		try {
			Thread.sleep(3000);
			Assert.assertTrue(invoicepage.isPromotionsPopupDisplayed(),
					"Promotion Pop-up is not displayed.");
			System.out.println("Promotion Pop-up is displayed.");
		} catch (Exception e) {
			System.err.println("Error verifying Promotion Pop-up : " + e.getMessage());
			Assert.fail("Promotion Pop-up verification failed.");
		}
	}	

	@Then("User tries to Checks the All the promotions from promotions table in the Promotion Pop-up")
	public void user_tries_to_checks_the_all_the_promotions_from_promotions_table_in_the_promotion_pop_up() {
		try {
			Thread.sleep(2000);
			invoicepage.clickPromotionCheckBoxAll();
			System.out.println("Checks the All the promotions clicked.");
		} catch (Exception e) {
			System.err.println("Error during Checks the All the promotions click: " + e.getMessage());
		}
	}
	
	@Then("User tries to clicks on Add Selected button in the Promotion Pop-up")
	public void user_tries_to_clicks_on_add_selected_button_in_the_promotion_pop_up() {
		try {
			Thread.sleep(2000);
			invoicepage.clickPromotionAddSelectedButton();
			System.out.println("Add Selected button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Add Selected button click: " + e.getMessage());
		}
	}

	@When("User tries to click on the Modify button in Basic info Section in the Customer Booking Management Screen")
	public void user_tries_to_click_on_the_modify_button_in_basic_info_section_in_the_customer_booking_management_screen() {
		try {
			Thread.sleep(2000);
			invoicepage.clickCustomerBookingMgtModifyButton();
			System.out.println("Modify button in Basic info Section clicked.");
		} catch (Exception e) {
			System.err.println("Error during Modify button in Basic info Sectionclick: " + e.getMessage());
		}
	}

	@When("User should be able to see Do you want to Modify it? Popup")
	public void user_should_be_able_to_see_do_you_want_to_modify_it_popup() {
		try {
			Thread.sleep(3000);
			Assert.assertTrue(invoicepage.isCustomerBookingMgtModifyConfirmationPopupDisplayed(),
					"Do you want to Modify it? Popup is not displayed.");
			System.out.println("Do you want to Modify it? Popup is displayed.");
		} catch (Exception e) {
			System.err.println("Do you want to Modify it? Popup : " + e.getMessage());
			Assert.fail("Do you want to Modify it? Popup verification failed.");
		}
	}	

	@When("User tries to clicks on Confirm button in Do you want to Modify it? Popup")
	public void user_tries_to_clicks_on_confirm_button_in_do_you_want_to_modify_it_popup() {
		try {
			Thread.sleep(2000);
			invoicepage.clickCustomerBookingMgtModifyConfirmationPopupConfirmButton();
			System.out.println("Confirm button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Confirm button click: " + e.getMessage());
		}
	}

	@When("User tries to selects valid data in Vehicle usage Type field in customer info Section in the Customer Booking Management Screen")
	public void user_tries_to_selects_valid_data_in_vehicle_usage_type_field_in_customer_info_section_in_the_customer_booking_management_screen() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User tries to clicks on the Register button in invoice tab in the Customer Booking Management Screen")
	public void user_tries_to_clicks_on_the_register_button_in_invoice_tab_in_the_customer_booking_management_screen() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User should be able to see Do you want to register? Popup")
	public void user_should_be_able_to_see_do_you_want_to_register_popup() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User tries to clicks on Confirm button in Do you want to save it? Popup in the Customer Booking Management Screen")
	public void user_tries_to_clicks_on_confirm_button_in_do_you_want_to_save_it_popup_in_the_customer_booking_management_screen() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User tries to click on the Modify button in invoice tab in the Customer Booking Management Screen")
	public void user_tries_to_click_on_the_modify_button_in_invoice_tab_in_the_customer_booking_management_screen() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User should be able to see Do you want to Modify it? Popup in the Customer Booking Management Screen")
	public void user_should_be_able_to_see_do_you_want_to_modify_it_popup_in_the_customer_booking_management_screen() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User tries to clicks on Confirm button in Do you want to Modify it? Popup in the Customer Booking Management Screen")
	public void user_tries_to_clicks_on_confirm_button_in_do_you_want_to_modify_it_popup_in_the_customer_booking_management_screen() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User tries to clicks on Invoice Confirm button in Do you want to save it? Popup")
	public void user_tries_to_clicks_on_invoice_confirm_button_in_do_you_want_to_save_it_popup() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User should be able to see Do you want to confirm it? Popup")
	public void user_should_be_able_to_see_do_you_want_to_confirm_it_popup() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User tries to clicks on Confirm button in Do you want to confirm it? Popup")
	public void user_tries_to_clicks_on_confirm_button_in_do_you_want_to_confirm_it_popup() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User clicks Service Menu Item")
	public void user_clicks_service_menu_item() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User clicks on the Basic Info Sub Menu Item")
	public void user_clicks_on_the_basic_info_sub_menu_item() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User clicks on the Vehicle Mgt link")
	public void user_clicks_on_the_vehicle_mgt_link() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User should be able to navigate to the Vehicle Mgt Screen")
	public void user_should_be_able_to_navigate_to_the_vehicle_mgt_screen() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User tries to enters valid data in VIN field")
	public void user_tries_to_enters_valid_data_in_vin_field() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User tries to clicks on the Search button in Vehicle Mgt Screen")
	public void user_tries_to_clicks_on_the_search_button_in_vehicle_mgt_screen() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User tries to clicks on the respective record in the table in Vehicle Mgt Screen")
	public void user_tries_to_clicks_on_the_respective_record_in_the_table_in_vehicle_mgt_screen() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User tries to selects valid data in the delivery date field in Vehicle Mgt Screen")
	public void user_tries_to_selects_valid_data_in_the_delivery_date_field_in_vehicle_mgt_screen() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User tries to enters valid data in the Reg. No field in Vehicle Mgt Screen")
	public void user_tries_to_enters_valid_data_in_the_reg_no_field_in_vehicle_mgt_screen() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User tries to click on the Modify button in Vehicle Mgt Screen")
	public void user_tries_to_click_on_the_modify_button_in_vehicle_mgt_screen() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User should be able to see Do you want to Modify it? Popup in Vehicle Mgt Screen")
	public void user_should_be_able_to_see_do_you_want_to_modify_it_popup_in_vehicle_mgt_screen() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User tries to clicks on Confirm button in Do you want to Modify it? Popup in Vehicle Mgt Screen")
	public void user_tries_to_clicks_on_confirm_button_in_do_you_want_to_modify_it_popup_in_vehicle_mgt_screen() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User should be able to see a Toast Message as Successfully reflected in Vehicle Mgt Screen")
	public void user_should_be_able_to_see_a_toast_message_as_successfully_reflected_in_vehicle_mgt_screen() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("user tries to close the chrome browser")
	public void user_tries_to_close_the_chrome_browser() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}



}
