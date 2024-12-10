package com.autogrid.steps;

import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvoicePage {

	private static final Logger logger = LoggerFactory.getLogger(InvoicePage.class);
	private final CommonActions commonActions;

	@FindBy(xpath = "//*[@id='gnb']/li[3]/a")
	private WebElement SalesMenu;

	@FindBy(xpath = "//*[@id='gnb']/li[3]/div/ul/li[3]/a")
	private WebElement SalesOperationSubmenu;

	@FindBy(xpath = "//li[@class='active']//ul//li//a[@class='menuItem'][normalize-space()='Customer Booking Mgt List']")
	private WebElement CustomerBookingMgtListLink;

	@FindBy(xpath = "//span[contains(text(), 'Customer Booking Mgt List')]")
	private WebElement CustomerBookingMgtListScreenHeader;

	@FindBy(xpath = "//*[@id='sStartDt']")
	private WebElement EnquiryStartDateField;

	@FindBy(xpath = "//*[@id='content']/section[1]/div[2]/dl[2]/dd[1]/span")
	private WebElement BasedOnDropDown;

	@FindBy(xpath = "//*[@id='baseTxt'][@type='text']")
	private WebElement BasedOnField;

	@FindBy(xpath = "//*[@id='btnSearch']")
	private WebElement CustomerBookingMgtListSearch;

	@FindBy(xpath = "//*[@id='mainGrid']/div[3]/table[1]/tbody[1]/tr[1]/td[2]")
	private WebElement SelectEnquiryFromCustomerBookingMgtList;

	@FindBy(xpath = "//*[@class='k-item k-state-default k-last k-tab-on-top k-state-active']/span[2]")
	private WebElement CustomerBookingMgtScreenHeader;

	@FindBy(xpath = "//li[@id='invoiceTab']")
	private WebElement InvoiceTab;

	@FindBy(xpath = "//*[@id='btnInvScheme']")
	private WebElement SchemeButton;

	@FindBy(xpath = "//span[contains(text(), 'Scheme Popup')]")
	private WebElement SchemePopupHeader;

	@FindBy(xpath = "/html/body/div[1]/section/div[3]/div/div[2]/table/tbody/tr/td[5]")
	private WebElement PayableByDealerAmountField;

	@FindBy(xpath = "/html/body/div[1]/section/div[3]/div/div[2]/table/tbody/tr/td[7]")
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

	@FindBy(xpath = "//*[@id='grid_active_cell']/input")
	private WebElement PromotionCheckBoxAll;

	@FindBy(xpath = "//*[@id='btnAddSelected']")
	private WebElement PromotionAddSelectedButton;

	@FindBy(xpath = "//*[@id='btnMainModify']")
	private WebElement CustomerBookingMgtModifyButton;

	@FindBy(xpath = "//div[@class='k-window-titlebar k-header']//span[normalize-space()='Notice']")
	private WebElement CustomerBookingMgtModifyConfirmationPopup;

	@FindBy(xpath = "//*[@class='confirm-yes k-button']")
	private WebElement CustomerBookingMgtModifyConfirmationPopupConfirmButton;

	@FindBy(xpath = "//*[@id='invoiceInfo']/section[1]/div[2]/dl[5]/dd[1]/span")
	private WebElement VehicleUsageTypeField;

	@FindBy(xpath = "//*[@id='btnInvRegister']")
	private WebElement RegisterButton;

	@FindBy(xpath = "//span[normalize-space()='Notice']")
	private WebElement RegisterConfirmationPopup;

	@FindBy(xpath = "//*[@class='confirm-yes k-button']")
	private WebElement RegisterConfirmationPopupConfirmButton;

	@FindBy(xpath = "//*[@id='btnInvModify']")
	private WebElement InvoiceModifyButton;

	@FindBy(xpath = "//span[normalize-space()='Notice']")
	private WebElement InvoiceModifyConfirmationPopup;

	@FindBy(xpath = "//*[@class='confirm-yes k-button']")
	private WebElement InvoiceModifyConfirmationPopupConfirmButton;

	@FindBy(xpath = "//*[@id='btnInvoiceConfirm']")
	private WebElement InvoiceConfirmButton;

	@FindBy(xpath = "//span[normalize-space()='Notice']")
	private WebElement InvoiceConfirmConfirmationPopup;

	@FindBy(xpath = "//*[@class='confirm-yes k-button']")
	private WebElement InvoiceConfirmConfirmationPopupConfirmButton;

	@FindBy(xpath = "//*[@id='gnb']/li[5]/a")
	private WebElement ServiceMenu;

	@FindBy(xpath = "//*[@id='gnb']/li[5]/div/ul/li[1]/a")
	private WebElement BasicInfoSubmenu;

	@FindBy(xpath = "//*[@id='gnb']/li[5]/div/ul/li[1]/ul/li[2]/a")
	private WebElement VehicleMgtLink;

	@FindBy(xpath = "//span[contains(text(), 'Vehicle Mgt')]")
	private WebElement VehicleMgtScreenHeader;

	@FindBy(xpath = "//*[@id='sVinNo']")
	private WebElement VinField;

	@FindBy(xpath = "/html/body/section/div[1]/div/section[1]/div[1]/div/button")
	private WebElement VehicleMgtSearchButton;

	@FindBy(xpath = "//*[@id='gridVin']/div[3]/table/tbody/tr[1]/td[2]")
	private WebElement SelectRecordInVehicleMgt;

	@FindBy(xpath = "//*[@id='saleDate']")
	private WebElement DeliveryDateField;

	@FindBy(xpath = "//*[@id='rgstnNo']")
	private WebElement RegNoField;

	@FindBy(xpath = "//*[@id='btnModify']")
	private WebElement VehicleMgtModifyButton;

	@FindBy(xpath = "//span[normalize-space()='Notice']")
	private WebElement VehicleMgtModifyConfirmationPopup;

	@FindBy(xpath = "//*[@class='confirm-yes k-button']")
	private WebElement VehicleMgtModifyConfirmationPopupConfirmButton;

	// Locator for iframe
	@FindBy(xpath = "(//*[starts-with(@id, 'tabMenuFrame')])[2]")
	private WebElement iframe1;

	@FindBy(xpath = "(//*[starts-with(@id, 'tabMenuFrame')])[3]")
	private WebElement iframe2;

	@FindBy(xpath = "//iframe[contains(@class, 'k-content-frame')]")
	private WebElement iframe3;

	public InvoicePage(WebDriver driver) {
		this.commonActions = new CommonActions(driver);
		PageFactory.initElements(driver, this);
	}

	public void interactWithIframeElement1() {
		try {
			LaunchDriver.getDriver().switchTo().defaultContent();
			LaunchDriver.getDriver().switchTo().frame(iframe1);
			System.out.println("Successfully interacted with the iframe1.");
		} catch (Exception e) {
			System.err.println("Error interacting with iframe1: " + e.getMessage());
		}
	}

	public void interactWithIframeElement2() {
		try {
			LaunchDriver.getDriver().switchTo().frame(iframe2);
			System.out.println("Successfully interacted with the iframe2.");
		} catch (Exception e) {
			System.err.println("Error interacting with iframe2: " + e.getMessage());
		}
	}

	public void interactWithIframeElement3() {
		try {
			LaunchDriver.getDriver().switchTo().frame(iframe3);
			System.out.println("Successfully interacted with the iframe2.");
		} catch (Exception e) {
			System.err.println("Error interacting with iframe3: " + e.getMessage());
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
			BasedOnDropDown.click();
			Thread.sleep(2000);
			BasedOnDropDown.sendKeys(basedOnName);
			BasedOnDropDown.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Based On: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Mobile Number in Filter
	public void enterBasedOnField(String basedOnField) {
		try {
			BasedOnField.sendKeys(basedOnField);
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

	public void doubleClickOnEnquiry() {
		try {
			// Perform double-click action
			Actions actions = new Actions(LaunchDriver.getDriver());
			actions.doubleClick(SelectEnquiryFromCustomerBookingMgtList).perform();

			System.out.println("Successfully double-clicked on the enquiry.");
		} catch (Exception e) {
			System.err.println("Error performing double-click on the enquiry: " + e.getMessage());
			throw new RuntimeException("Failed to double-click on the enquiry.", e);
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
			System.err.println(
					"Error clicking Customer Booking Mgt Modify Confirmation Popup Confirm Button: " + e.getMessage());
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
			System.err.println(
					"Error checking Customer Booking Mgt Modify Confirmation Popup visibility: " + e.getMessage());
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

	// Action to enter Payable By Dealer Amount
	public void enterPayableByDealerAmountField(String PayableByDealerAmount) throws Exception {
		try {
        	Thread.sleep(2000);
            Actions actions = new Actions(LaunchDriver.getDriver());
            actions.click(PayableByDealerAmountField).sendKeys(PayableByDealerAmount).build().perform();
            Thread.sleep(2000);
            System.out.println("Successfully entered text: " + PayableByDealerAmount);
        } catch (Exception e) {
            System.err.println("Error while entering text using Actions: " + e.getMessage());
            throw new RuntimeException("Failed to enter text using Actions.", e);
        }
    }
	
	public void enterAdjustmentCreditNoteField(String AdjustmentCreditNoteAmount) {
        try {
        	Thread.sleep(2000);
            Actions actions = new Actions(LaunchDriver.getDriver());
            actions.click(AdjustmentCreditNoteAmountField).sendKeys(AdjustmentCreditNoteAmount).build().perform();
            Thread.sleep(2000);
            System.out.println("Successfully entered text: " + AdjustmentCreditNoteAmount);
        } catch (Exception e) {
            System.err.println("Error while entering text using Actions: " + e.getMessage());
            throw new RuntimeException("Failed to enter text using Actions.", e);
        }
    }

	// Action to enter Basic Insurance Amount
	public void enterBasicInsuranceAmountField(String BasicInsuranceAmount) throws Exception {
		try {
        	Thread.sleep(2000);
            Actions actions = new Actions(LaunchDriver.getDriver());
            actions.click(BasicInsuranceAmountField).sendKeys(BasicInsuranceAmount).build().perform();
            System.out.println("Successfully entered text: " + BasicInsuranceAmount);
        } catch (Exception e) {
            System.err.println("Error while entering text using Actions: " + e.getMessage());
            throw new RuntimeException("Failed to enter text using Actions.", e);
        }
    }

	// Action to enter RTO Amount
	public void enterRTOAmountField(String RTOAmount) throws Exception {
		try {
        	Thread.sleep(2000);
            Actions actions = new Actions(LaunchDriver.getDriver());
            actions.click(RTOAmountField).sendKeys(RTOAmount).build().perform();
            System.out.println("Successfully entered text: " + RTOAmount);
        } catch (Exception e) {
            System.err.println("Error while entering text using Actions: " + e.getMessage());
            throw new RuntimeException("Failed to enter text using Actions.", e);
        }
    }

	// Action to enter Road Tax Amount
	public void enterRoadTaxAmountField(String RoadTaxAmount) throws Exception {
		try {
        	Thread.sleep(2000);
            Actions actions = new Actions(LaunchDriver.getDriver());
            actions.click(RoadTaxAmountField).sendKeys(RoadTaxAmount).build().perform();
            System.out.println("Successfully entered text: " + RoadTaxAmount);
        } catch (Exception e) {
            System.err.println("Error while entering text using Actions: " + e.getMessage());
            throw new RuntimeException("Failed to enter text using Actions.", e);
        }
    }

	// Action to enter Other Charges Amount
	public void enterOtherChargesAmountField(String OtherChargesAmount) throws Exception {
		try {
        	Thread.sleep(2000);
            Actions actions = new Actions(LaunchDriver.getDriver());
            actions.click(OtherChargesAmountField).sendKeys(OtherChargesAmount).build().perform();
            System.out.println("Successfully entered text: " + OtherChargesAmount);
        } catch (Exception e) {
            System.err.println("Error while entering text using Actions: " + e.getMessage());
            throw new RuntimeException("Failed to enter text using Actions.", e);
        }
    }

	// Action to enter Life Tax Amount
	public void enterLifeTaxAmountField(String LifeTaxAmount) throws Exception {
		try {
        	Thread.sleep(2000);
            Actions actions = new Actions(LaunchDriver.getDriver());
            actions.click(LifeTaxAmountField).sendKeys(LifeTaxAmount).build().perform();
            System.out.println("Successfully entered text: " + LifeTaxAmount);
        } catch (Exception e) {
            System.err.println("Error while entering text using Actions: " + e.getMessage());
            throw new RuntimeException("Failed to enter text using Actions.", e);
        }
    }

	public void selectVehicleUsageType(String VehicleUsageType) throws Exception {
		try {
			VehicleUsageTypeField.click();
			Thread.sleep(5000);
			VehicleUsageTypeField.sendKeys(VehicleUsageType); // Enter the desired VehicleUsageType
			VehicleUsageTypeField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Vehicle Usage Type: " + e.getMessage());
			throw e;
		}
	}

	public void clickRegisterButton() {
		try {
			RegisterButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Register Button: " + e.getMessage());
			throw e;
		}
	}

	public boolean isRegisterConfirmationPopupDisplayed() {
		try {
			return RegisterConfirmationPopup.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Register Confirmation Popup visibility: " + e.getMessage());
			return false;
		}
	}

	public void clickRegisterConfirmationPopupConfirmButton() {
		try {
			RegisterConfirmationPopupConfirmButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Register Confirmation Popup Confirm Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickInvoiceModifyButton() {
		try {
			InvoiceModifyButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Invoice Modify Button: " + e.getMessage());
			throw e;
		}
	}

	public boolean isInvoiceModifyConfirmationPopupDisplayed() {
		try {
			return InvoiceModifyConfirmationPopup.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Invoice Modify Confirmation Popup visibility: " + e.getMessage());
			return false;
		}
	}

	public void clickInvoiceModifyConfirmationPopupConfirmButton() {
		try {
			InvoiceModifyConfirmationPopupConfirmButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Invoice Modify Confirmation Popup Confirm Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickInvoiceConfirmButton() {
		try {
			InvoiceConfirmButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Invoice Confirm Button: " + e.getMessage());
			throw e;
		}
	}

	public boolean isInvoiceConfirmConfirmationPopupDisplayed() {
		try {
			return InvoiceConfirmConfirmationPopup.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Invoice Confirm Confirmation Popup visibility: " + e.getMessage());
			return false;
		}
	}

	public void clickInvoiceConfirmConfirmationPopupConfirmButton() {
		try {
			InvoiceConfirmConfirmationPopupConfirmButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Invoice Confirm Confirmation Popup Confirm Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickServiceMenu() {
		try {
			ServiceMenu.click();
		} catch (Exception e) {
			System.err.println("Error clicking Service Menu: " + e.getMessage());
			throw e;
		}
	}

	public void clickBasicInfoSubmenu() {
		try {
			BasicInfoSubmenu.click();
		} catch (Exception e) {
			System.err.println("Error clicking Basic Info Sub-menu: " + e.getMessage());
			throw e;
		}
	}

	public void clickVehicleMgtLink() {
		try {
			VehicleMgtLink.click();
		} catch (Exception e) {
			System.err.println("Error clicking Vehicle Mgt Link: " + e.getMessage());
			throw e;
		}
	}

	// Method to check if Vehicle Mgt screen is displayed
	public boolean isVehicleMgtScreenDisplayed() {
		try {
			return VehicleMgtScreenHeader.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Vehicle Mgt screen visibility: " + e.getMessage());
			return false;
		}
	}

	public void enterVinField(String vinField) {
		try {
			VinField.sendKeys(vinField);
		} catch (Exception e) {
			System.err.println("Error entering Vin Field: " + e.getMessage());
			throw e;
		}
	}

	public void clickVehicleMgtSearchButton() {
		try {
			VehicleMgtSearchButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Vehicle Mgt Search Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickSelectRecordInVehicleMgt() {
		try {
			SelectRecordInVehicleMgt.click();
		} catch (Exception e) {
			System.err.println("Error clicking Select Record In Vehicle Mgt: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Delivery Date
	public void enterDeliveryDateField(String deliveryDate) {
		try {
			DeliveryDateField.sendKeys(deliveryDate);
		} catch (Exception e) {
			System.err.println("Error entering Delivery Date: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Enquiry Start Date
	public void enterEnquiryStartDateField(String enquiryStartDateField) {
		try {
			EnquiryStartDateField.clear();
			EnquiryStartDateField.sendKeys(enquiryStartDateField);
		} catch (Exception e) {
			System.err.println("Error entering Enquiry Start Date Field: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Reg. No
	public void enterRegNoField(String RegNo) {
		try {
			RegNoField.sendKeys(RegNo);
		} catch (Exception e) {
			System.err.println("Error entering Reg. No: " + e.getMessage());
			throw e;
		}
	}

	public void clickVehicleMgtModifyButton() {
		try {
			VehicleMgtModifyButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Vehicle Mgt Modify Button: " + e.getMessage());
			throw e;
		}
	}

	public boolean isVehicleMgtModifyConfirmationPopupDisplayed() {
		try {
			return VehicleMgtModifyConfirmationPopup.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Vehicle Mgt Modify Confirmation Popup visibility: " + e.getMessage());
			return false;
		}
	}

	public void clickVehicleMgtModifyConfirmationPopupConfirmButton() {
		try {
			VehicleMgtModifyConfirmationPopupConfirmButton.click();
		} catch (Exception e) {
			System.err
					.println("Error clicking Vehicle Mgt Modify Confirmation Popup Confirm Button: " + e.getMessage());
			throw e;
		}
	}
}
