package com.autogrid.steps;

import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvoicePage {

	private static final Logger logger = LoggerFactory.getLogger(InvoicePage.class);
	private final CommonActions commonActions;
	private WebDriver driver;

	@FindBy(xpath = "//*[@id='gnb']/li[3]/a")
	private WebElement SalesMenu;

	@FindBy(xpath = "//*[@id='gnb']/li[3]/div/ul/li[3]/a")
	private WebElement SalesOperationSubmenu;

	@FindBy(xpath = "//li[@class='active']//ul//li//a[@class='menuItem'][normalize-space()='Customer Booking Mgt List']")
	private WebElement CustomerBookingMgtListLink;

	@FindBy(xpath = "//span[contains(text(), 'Customer Booking Mgt List')]")
	private WebElement CustomerBookingMgtListScreenHeader;

	@FindBy(xpath = "//*[@id='content']/section[1]/div[2]/dl[2]/dd[1]/span")
	private WebElement BasedOnField;

	@FindBy(xpath = "//*[@id='baseTxt'][@type='text']")
	private WebElement MobileFilter;

	@FindBy(xpath = "//*[@id='btnSearch']")
	private WebElement CustomerBookingMgtListSearch;

	@FindBy(xpath = "//*[@id='mainGrid']/div[3]/table[1]/tbody[1]/tr[1]/td[2]")
	private WebElement SelectEnquiryFromCustomerBookingMgtList;

	@FindBy(xpath = "//span[normalize-space()='Customer Booking Mgt']")
	private WebElement CustomerBookingMgtScreenHeader;

	@FindBy(xpath = "//li[@id='invoiceTab']")
	private WebElement InvoiceTab;

	@FindBy(xpath = "//*[@id='btnInvScheme']")
	private WebElement SchemeButton;

	@FindBy(xpath = "//*[@id='bookingSchemePopup_wnd_title']")
	private WebElement SchemePopupHeader;

	@FindBy(xpath = "//*[@id=gridFir']/div[2]/table/tbody/tr/td[5]")
	private WebElement PayableByDealerAmountField;

	@FindBy(xpath = "//*[@id=gridFir']/div[2]/table[1]/tbody[1]/tr[1]/td[7]")
	private WebElement AdjustmentCreditNoteAmountField;

	@FindBy(xpath = "//*[@class='table_grid no_thead']/div/div[2]/table/tbody/tr[2]/td[7]")
	private WebElement BasicInsuranceAmountField;

	@FindBy(xpath = "//*[@class='table_grid no_thead']/div/div[2]/table/tbody/tr[9]/td[7]")
	private WebElement RTOAmountField;

	@FindBy(xpath = "//*[@class='table_grid no_thead']/div/div[2]/table/tbody/tr[10]/td[7]")
	private WebElement RoadTaxAmountField;

	@FindBy(xpath = "//*[@class='table_grid no_thead']/div/div[2]/table/tbody/tr[8]/td[7]")
	private WebElement OtherChargesAmountField;

	@FindBy(xpath = "//*[@class='table_grid no_thead']/div/div[2]/table/tbody/tr[7]/td[7]")
	private WebElement LifeTaxAmountField;

	@FindBy(xpath = "//*[@id='btnSave'][@type='button']")
	private WebElement SchemePopupSaveButton;
	
	@FindBy(xpath = "//span[normalize-space()='Notice']")
	private WebElement SchemeSaveConfirmationPopup;
	
	@FindBy(xpath = "//button[@class='confirm-yes k-button'][normalize-space()='Confirm']")
	private WebElement SchemeSaveConfirmationConfirmButton;
	
	@FindBy(xpath = "//*[@id='btnClose'][@class='btn_m btn_close k-button']")
	private WebElement SchemePopupCloseButton;
	
	@FindBy(xpath = "//*[@id='btnHidden']")
	private WebElement MorePromotionsButton;
	
	@FindBy(xpath = "//*[@id='basicInfo']/section")
	private WebElement PromotionsSection;
	
	@FindBy(xpath = "//*[@id='salesPromotionSearchPopup_wnd_title']")
	private WebElement PromotionsPopupHeader;
	
	@FindBy(xpath = "//*[@id='btnPromoAdd']")
	private WebElement PromotionsSectionPlusIcon;
	
	@FindBy(xpath = "//*[@id='grid_active_cell']/input[@class='checkAll']")
	private WebElement PromotionCheckBoxAll;
	
	@FindBy(xpath = "//*[@id='btnAddSelected']")
	private WebElement PromotionAddSelectedButton;
	
	@FindBy(xpath = "//*[@id='btnMainModify']")
	private WebElement CustomerBookingMgtModifyButton;
	
	@FindBy(xpath = "//div[@class='k-window-titlebar k-header']//span[normalize-space()='Notice']")
	private WebElement CustomerBookingMgtModifyConfirmationPopup;
	
	@FindBy(xpath = "//*[@class='confirm-yes k-button']")
	private WebElement CustomerBookingMgtModifyConfirmationPopupConfirmButton;


	public InvoicePage(WebDriver driver) {
		this.commonActions = new CommonActions(driver);
		PageFactory.initElements(driver, this);
	}

	public void interactWithIframeElement() {
		try {
			LaunchDriver.getDriver().switchTo().frame(iframe);
			System.out.println("Successfully interacted with the element inside the iframe.");
		} catch (Exception e) {
			System.err.println("Error interacting with iframe: " + e.getMessage());
		}
	}

	public void clickSalesMenu() {
		try {
			SalesMenu.click();
		} catch (Exception e) {
			System.err.println("Error clicking Sales Menu: " + e.getMessage());
			throw e;
		}
	}

	public void clickSalesOperationSubmenu() {
		try {
			SalesOperationSubmenu.click();
		} catch (Exception e) {
			System.err.println("Error clicking Sales Operation Sub-menu: " + e.getMessage());
			throw e;
		}
	}

	public void clickCustomerBookingMgtListLink() {
		try {
			CustomerBookingMgtListLink.click();
		} catch (Exception e) {
			System.err.println("Error clicking Customer Booking Mgt List Link: " + e.getMessage());
			throw e;
		}
	}

	// Method to check if Customer Booking Mgt List screen is displayed
	public boolean isCustomerBookingMgtListScreenDisplayed() {
		try {
			return CustomerBookingMgtListScreenHeader.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Customer Booking Mgt List screen visibility: " + e.getMessage());
			return false;
		}
	}

	// Method to select a Based On from the Field
	public void selectBasedOn(String basedOnName) throws Exception {
		try {
			BasedOnField.click();
			Thread.sleep(5000);
			BasedOnField.sendKeys(basedOnName);
			BasedOnField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Based On: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Mobile Number in Filter
	public void enterMobileFilter(String mobilefilter) {
		try {
			MobileFilter.clear(); // Clear the field if necessary
			MobileFilter.sendKeys(mobilefilter);
		} catch (Exception e) {
			System.err.println("Error entering Mobile in Filter: " + e.getMessage());
			throw e;
		}
	}

	public void clickCustomerBookingMgtListSearch() {
		try {
			CustomerBookingMgtListSearch.click();
		} catch (Exception e) {
			System.err.println("Error clicking Customer Booking Mgt List Search: " + e.getMessage());
			throw e;
		}
	}

	public void clickEnquiryFromBookingMgtList() {
		try {
			SelectEnquiryFromCustomerBookingMgtList.click();
		} catch (Exception e) {
			System.err.println("Error clicking Enquiry From Customer Booking Mgt List: " + e.getMessage());
			throw e;
		}
	}

	// Method to check if Customer Booking Mgt screen is displayed
	public boolean isCustomerBookingMgtScreenDisplayed() {
		try {
			return CustomerBookingMgtScreenHeader.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Customer Booking Mgt screen visibility: " + e.getMessage());
			return false;
		}
	}

	public void clickInvoiceTab() {
		try {
			InvoiceTab.click();
		} catch (Exception e) {
			System.err.println("Error clicking Enquiry From Customer Booking Mgt List: " + e.getMessage());
			throw e;
		}
	}

	public void clickSchemeButton() {
		try {
			SchemeButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Scheme Button : " + e.getMessage());
			throw e;
		}
	}

	public void clickSchemePopupSaveButton() {
		try {
			SchemePopupSaveButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Scheme Popup Save Button: " + e.getMessage());
			throw e;
		}
	}
	
	public void clickSchemePopupCloseButton() {
		try {
			SchemePopupCloseButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Scheme Popup Close Button: " + e.getMessage());
			throw e;
		}
	}

	public boolean isSchemePopupDisplayed() {
		try {
			return SchemePopupHeader.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Scheme Pop-up visibility: " + e.getMessage());
			return false;
		}
	}
	
	public boolean isSchemeSaveConfirmationPopupDisplayed() {
		try {
			return SchemeSaveConfirmationPopup.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Scheme Save Confirmation Popup visibility: " + e.getMessage());
			return false;
		}
	}
	
	public void clickSchemeSaveConfirmationConfirmButton() {
		try {
			SchemeSaveConfirmationConfirmButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Scheme Save Confirmation Confirm Button: " + e.getMessage());
			throw e;
		}
	}
	
	public void clickMorePromotionsButton() {
		try {
			MorePromotionsButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking More Promotions Button: " + e.getMessage());
			throw e;
		}
	}
	
	public void clickPromotionsSectionPlusIcon() {
		try {
			PromotionsSectionPlusIcon.click();
		} catch (Exception e) {
			System.err.println("Error clicking Promotions Section Plus Icon: " + e.getMessage());
			throw e;
		}
	}
	
	public void clickPromotionCheckBoxAll() {
		try {
			PromotionCheckBoxAll.click();
		} catch (Exception e) {
			System.err.println("Error clicking Promotion CheckBox All: " + e.getMessage());
			throw e;
		}
	}
	
	public void clickPromotionAddSelectedButton() {
		try {
			PromotionAddSelectedButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Promotions AddSelected Button: " + e.getMessage());
			throw e;
		}
	}
	
	public void clickCustomerBookingMgtModifyButton() {
		try {
			CustomerBookingMgtModifyButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Customer Booking Mgt Modify Button: " + e.getMessage());
			throw e;
		}
	}
	
	public void clickCustomerBookingMgtModifyConfirmationPopupConfirmButton() {
		try {
			CustomerBookingMgtModifyConfirmationPopupConfirmButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Customer Booking Mgt Modify Confirmation Popup Confirm Button: " + e.getMessage());
			throw e;
		}
	}
	
	public boolean isPromotionsSectionDisplayed() {
		try {
			return PromotionsSection.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Promotions Section visibility: " + e.getMessage());
			return false;
		}
	}
	
	public boolean isCustomerBookingMgtModifyConfirmationPopupDisplayed() {
		try {
			return CustomerBookingMgtModifyConfirmationPopup.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Customer Booking Mgt Modify Confirmation Popup visibility: " + e.getMessage());
			return false;
		}
	}
	public boolean isPromotionsPopupDisplayed() {
		try {
			return PromotionsPopupHeader.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Promotions Popup visibility: " + e.getMessage());
			return false;
		}
	}

//		public void clickPayableByDealerAmountField() {
//			try {
//				SchemeButton.click();
//			} catch (Exception e) {
//				System.err.println("Error clicking Scheme Button : " + e.getMessage());
//				throw e;
//			}
//		}
//		
//		public void clickAdjustmentCreditNoteField() {
//			try {
//				SchemeButton.click();
//			} catch (Exception e) {
//				System.err.println("Error clicking Scheme Button : " + e.getMessage());
//				throw e;
//			}
//		}

	// Action to enter Payable By Dealer Amount
	public void enterPayableByDealerAmountField(String PayableByDealerAmount) {
		try {
			PayableByDealerAmountField.sendKeys(PayableByDealerAmount);
		} catch (Exception e) {
			System.err.println("Error entering Payable By Dealer Amount: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Adjustment Credit Note Amount
	public void enterAdjustmentCreditNoteField(String AdjustmentCreditNoteAmount) {
		try {
			AdjustmentCreditNoteAmountField.sendKeys(AdjustmentCreditNoteAmount);
		} catch (Exception e) {
			System.err.println("Error entering Adjustment Credit Note Amount: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Basic Insurance Amount
	public void enterBasicInsuranceAmountField(String BasicInsuranceAmount) {
		try {
			BasicInsuranceAmountField.sendKeys(BasicInsuranceAmount);
		} catch (Exception e) {
			System.err.println("Error entering Basic Insurance Amount: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter RTO Amount
	public void enterRTOAmountField(String RTOAmount) {
		try {
			RTOAmountField.sendKeys(RTOAmount);
		} catch (Exception e) {
			System.err.println("Error entering RTO Amount: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Payable By Road Tax Amount
	public void enterRoadTaxAmountField(String RoadTaxAmount) {
		try {
			RoadTaxAmountField.sendKeys(RoadTaxAmount);
		} catch (Exception e) {
			System.err.println("Error entering Payable By Road Tax Amount: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Other Charges Amount
	public void enterOtherChargesAmountField(String OtherChargesAmount) {
		try {
			OtherChargesAmountField.sendKeys(OtherChargesAmount);
		} catch (Exception e) {
			System.err.println("Error entering Other Charges Amount: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Life Tax Amount
	public void enterLifeTaxAmountField(String LifeTaxAmount) {
		try {
			LifeTaxAmountField.sendKeys(LifeTaxAmount);
		} catch (Exception e) {
			System.err.println("Error entering Life Tax Amount: " + e.getMessage());
			throw e;
		}
	}

}
