package com.autogrid.stepDefinitions;

import org.testng.Assert;
import com.autogrid.steps.SearchCustBookingMgmtListPage;
import com.autogrid.utils.CommonActions;

import io.cucumber.java.en.*;




public class SearchCustInBookingMgtListStepDef {
	
	  public CommonActions commonActions;

	  public SearchCustBookingMgmtListPage cbmlp;
		
	
@When("User click on Sales Menu Icon")
public void user_click_on_sales_menu_icon() {
	
	cbmlp.clickOnSalesMenu();
  
    throw new io.cucumber.java.PendingException();
}

@And("User click on Sales Operation")
public void user_click_on_sales_operation()
{
	
	cbmlp.clickOnSalesOperation();
	}
@When("User click on Customer Booking Mgt List link")
public void user_click_on_customer_booking_mgt_list_link() {
    
	cbmlp.CustomerBookingMgmtLink();
    throw new io.cucumber.java.PendingException();
}

@Then("Customer Booking Mgt List Page should display")
public void customer_booking_mgt_list_page_should_display() {
	try {
		String expectedTitle="Customer Booking Mgt List";
		String actualTitle=cbmlp.getPageTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("Search customer list page displayed");
		
	}
	catch(Exception e) {
		e.getMessage();

	}
	
   throw new io.cucumber.java.PendingException();
}

@When("User click on Date Of as {string}")
public void user_click_on_date_of_as(String string) {


    throw new io.cucumber.java.PendingException();
}

@When("User Click on Based On as {string}")
public void user_click_on_based_on_as(String string) {
	cbmlp.setBasedOn();

    throw new io.cucumber.java.PendingException();
}

@When("Enter mobile no.as {string}")
public void enter_mobile_no_as(String string) {

	cbmlp.BaseText("7489954647");
    throw new io.cucumber.java.PendingException();
}

@When("Click on Search")
public void click_on_search() throws InterruptedException {

	cbmlp.SearchBtn();
	Thread.sleep(3000);

    throw new io.cucumber.java.PendingException();
}

@Then("Details of Customer should display in table")
public void details_of_customer_should_display_in_table() {

	boolean status=cbmlp.searchCustomerByPhoneNo("7489954647");
	
	Assert.assertEquals(status,true);

    throw new io.cucumber.java.PendingException();
}

}
