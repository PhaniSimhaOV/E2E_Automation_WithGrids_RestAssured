package com.autogrid.stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.autogrid.steps.DMSLoginPage;
import com.autogrid.steps.ExWarrantyPage;
import com.autogrid.steps.SOTPage;
import com.autogrid.utils.LaunchDriver;

import io.cucumber.java.en.*;

public class ExWarrantyAndSOTStepDefinition {
	
	DMSLoginPage  dmsLogin;
	ExWarrantyPage exWarranty;
	SOTPage sot;
	
	public ExWarrantyAndSOTStepDefinition() {
		WebDriver driver= LaunchDriver.getDriver();
		this.exWarranty=new ExWarrantyPage(driver);
		PageFactory.initElements(driver, exWarranty);
		this.sot=new SOTPage(driver);
		PageFactory.initElements(driver, sot);
	}

	@When("User clicks on Service Icon")
	public void user_clicks_on_service_icon() {
	   try {
		   Thread.sleep(2000);
		   exWarranty.clickServiceIcon();
		   System.out.println("Service Icon clicked ");
			
		} catch (Exception e) {
		System.err.println("Error to click Service Icon " +e.getMessage());
		}
		  
		}

	@When("User clicks on Extended Warranty Sub Menu")
	public void user_clicks_on_extended_warranty_sub_menu() {
		try {
			Thread.sleep(2000);
			exWarranty.clickExtWarrantySubMenu();
			System.out.println("Extended Warranty Sub Menu clicked");
			
		} catch (Exception e) {
		System.err.println("Error in clicking Extended Warranty Sub Menu ");
		}
	    
	}

	@When("User clicks on Extended Warranty Submit Link")
	public void user_clicks_on_extended_warranty_submit_link() {
		try {
			Thread.sleep(2000);
			exWarranty.clickOnExtdWarrantySubmitLink();
			System.out.println("Extended Warranty Submit Link clicked");
		} catch (Exception e) {
			System.err.println("Error in clicking Extended Warranty Submit Link");
		}
	
	 
	}

	@Then("User should be able to navigate to Extended Warranty Submit Screen")
	public void user_should_be_able_to_navigate_to_extended_warranty_submit_screen() {
		try {
			Assert.assertTrue(exWarranty.extdWarrantySbtHeaderisDisplayed());
			System.out.println("Extended Warranty Submit Screen is displayed");
			
			
		} catch (Exception e) {
			System.err.println("Error to see Extended Warranty Submit Screen");
			Assert.fail("Extended Warranty Submit Screen is not displayed");
			
		}
	  
	
	}

	@When("User enters VIN number")
	public void user_enters_vin_number() {
		try {
			Thread.sleep(2000);
			exWarranty.interactWithIframeExtW();
			String vin="MALPC812TRM101723";
			exWarranty.enterVIN(vin);
			System.out.println("Entered VIN " +vin);
		} catch (Exception e) {
			System.err.println("Error in entering VIN "+e.getMessage());

		}
	   

	}

	@When("User clicks on Inquire")
	public void user_clicks_on_inquire() {
		try {
			Thread.sleep(2000);
			exWarranty.clickOnInquire();
			System.out.println("Clicked Inquire button");
		} catch (Exception e) {
		System.err.println("Error in clicking Inquire button "+e.getMessage());
		}
	    
	 
	}

	@When("User enter Current Odometer reading")
	public void user_enter_current_odometer_reading() {
		try {
			String odoMtrReading= "90";
			exWarranty.enterCurrentOdoMtrReading(odoMtrReading);
			System.out.println("Entered Current Odometer Reading" + odoMtrReading);
		} catch (Exception e) {
			System.err.println("Error in Entering Current OdoMeter Reading" +e.getMessage());
			
		}
	   
	   
	}

	@When("User select Employee Name")
	public void user_select_employee_name() {
		try {
			String employeeName="K BICHAPPA";
			exWarranty.selectEmployeeName(employeeName);
			System.out.println("Selected Employee Name " +employeeName);
		} catch (Exception e) {
			System.err.println("Error in Selecting Employee Name " +e.getMessage());
		}
	   
	  
	}

	@When("User select Place Of Supply")
	public void user_select_place_of_supply() {
		try {
			String placeOfSupply ="TELANGANA";
			exWarranty.selectPlaceOfSupply(placeOfSupply);
			System.out.println("Selected place of supply");
			
		} catch (Exception e) {
			System.err.println("Error in selecting place of supply");
			
		}
	    
	   
	}

	@When("User select required extented Warranty type")
	public void user_select_required_extented_warranty_type() {
		try {
			exWarranty.setExtdWarrantyType();
			System.out.println("Extended Warranty type selected");
		} catch (Exception e) {
		System.err.println("Error in setting Warranty type " +e.getMessage());
		}
	    
	    
	}

	@Then("User Clicks on clear button")
	public void user_clicks_on_clear_button() throws Throwable {
		try {
			Thread.sleep(4000);
			exWarranty.clickClearBtn();
			System.out.println("Clicked clear button");
			exWarranty.closeTab();
			System.out.println("Clicked close tab button");
		} catch (Exception e) {
		System.err.println("Error in clicking clear button "+e.getMessage());
		}
	   
	   
	}

	
	/*
	 public void user_clicks_on_submit_button()
	{
		try {
		    Thread.sleep(2000);
			exWarranty.clickSubmitBtn();
			System.out.println("Clicked Submit Button");
		} catch (Exception e) {
			System.err.println("Error in clicking submit button "+e.getMessage());
		}
		
	}
	
	*/
	

	@When("User clicks on Hyundai Shield of Trust Package Register link")
	public void user_clicks_on_hyundai_shield_of_trust_package_register_link() {
		
	  try {
		  Thread.sleep(3000);
		sot.clickHyundaiShieldOfTrustPackageRegisterLink();
		System.out.println("Clicked Hyundai SOT Package Register Link ");
	} catch (Exception e) {
	System.err.println("Error in clicking Hyundai SOT Package Register Link " +e.getMessage());
	}
		
	}

	@Then("User should be able to navigate to Hyundai Shield ot Trust Package Register Screen")
	public void user_should_be_able_to_navigate_to_hyundai_shield_ot_trust_package_register_screen() {
		try {
			Assert.assertTrue(sot.HyundaiSOTPackageRegisterHeaderisDisplayed());
			System.out.println("Hyundai SOT Package Register Screen is displayed ");
		} catch (Exception e) {
			System.err.println(" Hyundai SOT Package Register Screen is not displayed"+e.getMessage());
			
		}
	   
	}

	@When("User enter VIN number in SOT")
	public void user_enter_vin_number_in_sot() {
		try {
			Thread.sleep(2000);
			sot.interactWithIframeSOT();
			String vin="MALPC812TRM101723";
			sot.enterVIN(vin);
			System.out.println("Entered VIN in SOT "+vin);
		} catch (Exception e) {
			System.err.println("Error in entering VIN in SOT"+e.getMessage());
		}
	   
	}

	@When("User clicks on Inquire in SOT")
	public void user_clicks_on_inquire_in_sot() {
	   try {
		sot.clickOnInquire();
		System.out.println("Clicked Inquire btn in SOT");
	} catch (Exception e) {
		System.err.println("Error while Clicking Inquire button in SOT "+e.getMessage());
	}
	}

	@When("User enter Current Odometer reading in SOT")
	public void user_enter_current_odometer_reading_in_sot() throws Throwable {
		Thread.sleep(2000);
		try {
			String odoMtrReading= "90";
			sot.enterCurrentOdoMtrReading(odoMtrReading);
			System.out.println("Entered Current Odometer reading in SOT "+odoMtrReading);
		} catch (Exception e) {
			System.err.println("Error in entering current odometer reading "+e.getMessage());
			
		}
	    
	}

	@When("User select Employee Name in SOT")
	public void user_select_employee_name_in_sot() throws Throwable {
		Thread.sleep(2000);
		try {
			String employeeName="K BICHAPPA";
			sot.selectEmployeeName(employeeName);
			System.out.println("Employee Name selected in SOT "+employeeName);
		} catch (Exception e) {
			System.err.println("Error in Selecting Employee Name "+e.getMessage());
			
		}
	  
	}

	@When("User select Place Of Supply in SOT")
	public void user_select_place_of_supply_in_sot() throws Throwable {
		Thread.sleep(2000);
		try {
			String placeOfSupply="TELANGANA";
			sot.selectPlaceOfSupply(placeOfSupply);
			System.out.println("Selected Place of Supply "+placeOfSupply);
		} catch (Exception e) {
			System.err.println("Error in Selecting place of supply "+e.getMessage());
		}
	   
	}

	@When("User select required SOT Scheme type in SOT")
	public void user_select_required_extented_warranty_type_in_sot() {
		try {
			sot.setRequiredSOTSchemeType();
			System.out.println("Selected required SOT Scheme type in SOT ");
		} catch (Exception e) {
			System.err.println("Error in selecting SOT Scheme "+e.getMessage());
			
		}
	 
	}

	@Then("User Clicks on clear button in SOT")
	public void user_clicks_on_clear_button_in_sot() {
		try {
			Thread.sleep(2000);
			sot.clickClearBtn();
			System.out.println("Clicked clear button");
			sot.closeTab();
			System.out.println("Clicked close tab button");
		} catch (Exception e) {
			System.err.println("Error while clicking clear button in SOT");
			
		}
	   
	}

	/*
	public void  User_clicks_on_Submit_in_sot(){
		try {
			sot.clickSubmitBtn();
			System.out.println("Clicked Submit button in SOT");
			
		} catch (Exception e) {
			System.err.println("Error in Clicking submit button in SOT"+e.getMessage());
			
		}
	}
	
	*/
	
}
