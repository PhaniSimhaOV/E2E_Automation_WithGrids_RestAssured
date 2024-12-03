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

public class NewEnquiryPage {
	private static final Logger logger = LoggerFactory.getLogger(NewEnquiryPage.class);
	private final CommonActions commonActions;
	private WebDriver driver;

	@FindBy(xpath = "//*[@id='gnb']/li[3]/a")
	private WebElement SalesMenu;

	@FindBy(xpath = "//*[@id='gnb']/li[3]/div/ul/li[2]/a")
	private WebElement CustomerEnquirySubmenu;

	@FindBy(xpath = "//li[@class='active']//ul//li//a[@class='menuItem'][normalize-space()='Customer Enquiry']")
	private WebElement CustomerEnquiryLink;

	@FindBy(xpath = "//span[contains(text(), 'Customer Enquiry')]")
	private WebElement CustomerEnquiryscreenHeader;

	@FindBy(xpath = "//span[@id='selectCustomerMeetingPopup_wnd_title']")
	private WebElement SalesCustomerEnquiryPopupHeader;

	@FindBy(xpath = "//button[contains(text(), 'New')]")
	private WebElement NewEnquiry;

	@FindBy(xpath = "//span[normalize-space()='Basic Info.']")
	private WebElement BasicInfoTab;

	@FindBy(xpath = "//div[@class=\"form_search form_search\"]//span//span//input[1]")
	private WebElement MobileNumber;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement InvalidMobileNumberToast;

	@FindBy(xpath = "//a[@id='searchCustomer']")
	private WebElement MobileSearchIcon;

	@FindBy(xpath = "//*[@id='template']/div/div/p")
	private WebElement NewMobileToast;

	@FindBy(xpath = "//*[@id=\"enquiry_info\"]/div[2]/dl[1]/dd[1]/span")
	private WebElement CustTypeField;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement CustTypeValidation;

	@FindBy(xpath = "//*[@id='custName'][@data-name='Cust. Name']")
	private WebElement CustName;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement CustNameValidation;

	@FindBy(xpath = "//*[@id='resdcPhoneNo']")
	private WebElement ResidencePhone;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement ResidencePhoneValidation;

	@FindBy(xpath = "//*[@id='whatsAppID']")
	private WebElement WhatsAppId;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement WhatsAppIdValidation;

	@FindBy(xpath = "//*[@id='corp5']/span")
	private WebElement GenderField;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement GenderValidation;

	@FindBy(xpath = "//*[@id='email']")
	private WebElement Email;

	@FindBy(xpath = "///*[@id='template']")
	private WebElement InvalidEmailToast;

	@FindBy(xpath = "//*[@id=\"enquiry_info\"]/div[2]/dl[3]/dd[1]/span")
	private WebElement LocationField;

	@FindBy(xpath = "//input[@id='pin']")
	private WebElement DisabledPincodeField;

	@FindBy(xpath = "//a[@id='btnPinN']")
	private WebElement PinCodeSearchIcon;

	@FindBy(xpath = "//span[contains(text(), 'Pin Code Search')]")
	private WebElement PincodesearchScreenHeader;

	@FindBy(xpath = "//input[contains(@id, 'sPinCode')]")
	private WebElement Pincode;

	@FindBy(xpath = "//*[@id='btnSearch']")
	private WebElement PinCodeSearchButton;
	
	@FindBy(xpath = "//input[contains(@id, 'searchCustomer')]")
	private WebElement SearchIcon;

	@FindBy(xpath = "//*[@id='grid']/div[2]/table/tbody/tr[1]")
	private WebElement LocationSelection;
	
	@FindBy(xpath = "//*[@id='grid']/div[2]/table/tbody/tr")
	private WebElement LocationSelection2;

	@FindBy(xpath = "//button[@id='btnAddSelected']")
	private WebElement AddSelectedButton;

	@FindBy(xpath = "//*[@id='window']/div[2]/dl[1]/dd[1]/span")
	private WebElement StateField;

	@FindBy(xpath = "//*[@id='window']/div[2]/dl[1]/dd[2]/span")
	private WebElement DistrictField;

	@FindBy(xpath = "//*[@id='window']/div[2]/dl[1]/dd[3]/span")
	private WebElement TalukaField;

	@FindBy(xpath = "//*[@id='sPostOfceName']")
	private WebElement PostOfficeName;

	@FindBy(xpath = "//*[@id='villName']")
	private WebElement Village;

	@FindBy(xpath = "//*[@id='addr']")
	private WebElement Address;

	@FindBy(xpath = "//*[@id=\"enquiry_info\"]/div[4]/dl[1]/dd[1]/span")
	private WebElement EnquirySourceField;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement EnquirySourceValidation;

	@FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[1]/dd[2]/span")
	private WebElement EnquirySubSourceField;
	
	@FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[1]/dd[3]/span")
	private WebElement EnquiryCategoryField;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement EnquiryCategoryValidation;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement EnquirySubSourceValidation;

	@FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[1]/dd[4]/span")
	private WebElement PersonInChargeField;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement PersonInChargeValidation;

	@FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[2]/dd[1]/span")
	private WebElement ModelField;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement ModelValidation;

	@FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[2]/dd[2]/span")
	private WebElement FuelTypeField;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement FuelTypeValidation;

	@FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[2]/dd[3]/span")
	private WebElement VariantField;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement VariantValidation;

	@FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[2]/dd[4]/span")
	private WebElement SubVariantField;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement VariantSubTypeValidation;

	@FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[3]/dd[1]/span")
	private WebElement ExtColorField;

	@FindBy(xpath = "///*[@id='template']")
	private WebElement ExtColorValidation;

	@FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[3]/dd[2]/span")
	private WebElement IntColorField;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement IntColorValidation;

	@FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[3]/dd[4]/span")
	private WebElement SalesConsultantField;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement SalesConsultantValidation;

	@FindBy(xpath = "//*[@id='associate']")
	private WebElement Associate;

	@FindBy(xpath = "//*[@id='corpName']")
	private WebElement RegCrop;

	@FindBy(xpath = "//*[@id='btnSave']")
	private WebElement SaveButton;

	@FindBy(xpath = "//a[@role='button'][@aria-label='Close']")
	private WebElement CloseIcon;

	@FindBy(xpath = "//*[@id='enquiry_info']/div[1]/div")
	private WebElement SMSOpt;

	@FindBy(xpath = "//*[@id='enquiry_info']/div[1]/div/label")
	private WebElement SMSOptCheckBox;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement EnquirySuccessToast;

	@FindBy(xpath = "//*[@id='grid_active_cell']/input")
	private WebElement SelectEnquiry;

	@FindBy(xpath = "//*[@id='custName'][@data-name='Cust. Name']")
	private WebElement CompanyName;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement CompanyNameValidation;

	@FindBy(xpath = "//*[@id='activityName']")
	private WebElement Activity;

	@FindBy(xpath = "//span[normalize-space()='Follow Up']")
	private WebElement FollowUpTab;

	@FindBy(xpath = "//*[@id='followUpInfoForm']")
	private WebElement FollowUpTabScreen;

	@FindBy(xpath = "//*[@id='eqryForm']/div/dl[4]/dd[1]/span/span/span[1]")
	private WebElement FinanceReqYN;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement FinanceReqYNValidation;

	@FindBy(xpath = "//*[@id='eqryForm']/div/dl[4]/dd[2]/span/span/span[1]")
	private WebElement Financier;

	@FindBy(xpath = "//input[@id='loanAmount']")
	private WebElement LoanAmount;

	@FindBy(xpath = "//*[@id='basicInfoForm']/div[5]/dl[6]/dd[1]/span/span/span[1]")
	private WebElement TDOfferField;

	@FindBy(xpath = "//*[@id='basicInfoForm']/div[5]/dl[6]/dd[2]/span/span/span[1]")
	private WebElement TDVINField;

	@FindBy(xpath = "//*[@id='eqryForm']/div/dl[6]/dd[3]/span/span/span[1]")
	private WebElement CftOfDeposit;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement CftOfDepositValidation;

	@FindBy(xpath = "//*[@id='eqryForm']/div/dl[7]/dd[1]/span/span/span[1]")
	private WebElement FinanceOptions;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement FinanceOptionsValidation;

	@FindBy(xpath = "//*[@id='eqryForm']/div/dl[7]/dd[2]/span/span/span[1]")
	private WebElement Expplan;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement ExpplanValidation;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement EmptyEnquiryToast;

	@FindBy(xpath = "//*[@id='eqryForm']/div/dl[7]/dd[4]/span/span/span[1]")
	private WebElement VisitedFamily;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement VisitedFamilyValidation;

	@FindBy(xpath = "//*[@id='eqryForm']/div/dl[8]/dd/span/span/span[1]")
	private WebElement ImmediateBook;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement ImmediateBookValidation;

	@FindBy(xpath = "//*[@id='followUpInfoForm']/div[2]/dl[1]/dd[2]/span/span/span[1]")
	private WebElement FollowUpTypeSelection;

	@FindBy(xpath = "//textarea[@id='eqfuSchmeRmrks']")
	private WebElement SchemeOffered;

	@FindBy(xpath = "//textarea[@id='eqfuRmrks']")
	private WebElement FollowUpRemarks;

	@FindBy(xpath = "//input[@id='nextFollowUpDate']")
	private WebElement NextFollowUpDateTime;

	@FindBy(id = "//*[@id='template']/div/div/p")
	private WebElement NextFollowUpTimeToast;

	@FindBy(xpath = "//*[@id='followUpInfoForm']/div[2]/dl[4]/dd[2]/span/span/span[1]")
	private WebElement NextFollowUpTypeDropDown;

	@FindBy(id = "//*[@id='template']/div/div/p")
	private WebElement NextFollowUpTypeToast;

	@FindBy(id = "//*[@id='followUpInfoForm']/div[2]/dl[5]/dd[1]/span/span/span[1]")
	private WebElement EnquiryTypeDropDown;

	@FindBy(id = "//*[@id='template']/div/div/p")
	private WebElement EnquiryTypeToast;

	@FindBy(xpath = "//*[@id='followUpInfoForm']/div[2]/dl[5]/dd[2]/span/span/span[1]")
	private WebElement VerificationDropDown;

	@FindBy(id = "//*[@id='template']/div/div/p")
	private WebElement VerificationToast;

	@FindBy(xpath = "//button[@id='btnFollowUpSave']")
	private WebElement FollowUpTabSaveButton;

	@FindBy(xpath = "//*[@id='btnBasicSave']")
	private WebElement BasicInfoTabSaveButton;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement FollowUpSuccessToast;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement FollowUpValidation;

	@FindBy(xpath = "//*[@id='template']")
	private WebElement EnquiryAddedToast;

	@FindBy(xpath = "//*[@id='promotionGrid']//a[@role='button']")
	private WebElement PromotionCrossIcon;

	@FindBy(xpath = "//*[@id='cfd3d01a-750b-4e20-8c66-632854aae203']/div[1]")
	private WebElement leadTab;

	@FindBy(xpath = "//*[@id='btnSearch']")
	private WebElement leadTabSearchButton;

	@FindBy(xpath = "//*[@id='btnSearch']")
	private WebElement leadTabAllocateButton;

	@FindBy(xpath = "//*[@id='custName']")
	private WebElement CustNameFilter;

	@FindBy(xpath = "//*[@id='grid']/div[2]/table/tbody/tr[1]/td[2]")
	private WebElement leadEnquiryTable;

	@FindBy(xpath = "//*[@class='checkAll']")
	private WebElement leadEnquiryCheckBox;

	@FindBy(xpath = "//*[@class='checkAll']")
	private WebElement AllocatePopupHeader;
	
	@FindBy(xpath = "//span[@class=\"k-window-title\"][@id=\"customerInfoSearchPopupWin_wnd_title\"]")
	private WebElement FindACustomerPopupHeader;
	
	@FindBy(xpath = "/html/body/div[14]/div[1]/div/a/span")
	private WebElement FindACustomerPopupCloseIcon;

	@FindBy(xpath = "//*[@class='checkAll']")
	private WebElement AllocateSalesConsultantDropdown;

	@FindBy(xpath = "//*[@id='allocateBtn']")
	private WebElement AllocatePopupAllocateButton;

	@FindBy(xpath = "//*[@id='btnTD']")
	private WebElement TestDriveAppointmentButton;

	@FindBy(xpath = "//h1[@class='title_basic']")
	private WebElement TestDriveAppointmentHeader;

	@FindBy(xpath = "//*[@id='btnSave']")
	private WebElement TestDriveAppointmentSaveButton;

	@FindBy(xpath = "//*[@id='tabMenu']/ul/li[3]/button")
	private WebElement TestDriveAppointmentTabCloseIcon;

	@FindBy(xpath = "//*[@id='schedulerIn']/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td[1]")
	private WebElement TestDriveAppointmentOldDateSlot;

	@FindBy(xpath = "//a[@title='Next']")
	private WebElement TestDriveAppointmentNextdateIcon;

	@FindBy(xpath = "//*[@id='schedulerIn']/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td[1]")
	private WebElement TestDriveAppointmentValidDateSlot;

	@FindBy(xpath = "//*[@id='template']/div/div/p")
	private WebElement TestDriveAppointmentInvalidDateToast;

	@FindBy(xpath = "//*[@id='template']/div/div/p")
	private WebElement TestDriveAppointmentSuccessToast;

	@FindBy(xpath = "//*[@id='template']/div/div/p")
	private WebElement TestDriveAppointmentEmptyDateToast;

	// Locator for iframe
	@FindBy(xpath = "(//*[starts-with(@id, 'tabMenuFrame')])[2]")
	private WebElement NewEnquiryiframe;

	@FindBy(xpath = "//iframe[contains(@class, 'k-content-frame')]")
	private WebElement CustomerEnquiryPopupiframe;

	@FindBy(xpath = "//iframe[contains(@title, 'Pin Code Search')]")
	private WebElement PincodeSearchiframe;

	public NewEnquiryPage(WebDriver driver) {
		this.commonActions = new CommonActions(driver);
		PageFactory.initElements(driver, this);
	}

	public void interactWithincodeSearchIframeElement() {
		try {
			LaunchDriver.getDriver().switchTo().frame(PincodeSearchiframe);
			System.out.println("Successfully interacted with the element inside the Pincode Search iframe.");
		} catch (Exception e) {
			System.err.println("Error interacting with Pincode Search iframe: " + e.getMessage());
		}
	}

	public void interactWithIframeElement() {
		try {
			LaunchDriver.getDriver().switchTo().defaultContent();
			LaunchDriver.getDriver().switchTo().frame(NewEnquiryiframe);
			System.out.println("Successfully interacted with the element inside the iframe.");
		} catch (Exception e) {
			System.err.println("Error interacting with iframe: " + e.getMessage());
		}
	}

	public void interactWithCustomerEnquiryPopupIframeElement() {
		try {
			LaunchDriver.getDriver().switchTo().frame(CustomerEnquiryPopupiframe);
			System.out.println("Successfully interacted with the element inside the iframe in Customer Enquiry Popup.");
		} catch (Exception e) {
			System.err.println("Error interacting with iframe in Customer Enquiry Popup: " + e.getMessage());
		}
	}

	public void doubleClickOnEnquiry() throws Exception {
		try {
			Actions actions = new Actions(driver);
			actions.doubleClick(SelectEnquiry).perform();
			System.out.println("Successfully double-clicked on the respective enquiry.");
		} catch (Exception e) {
			System.err.println("Error performing double-click on the respective enquiry: " + e.getMessage());
			throw new Exception("Failed to double-click on the respective enquiry.", e);
		}
	}

	public String getNextFollowUpTimeToast() {
		try {
			return NextFollowUpTimeToast.getText();
		} catch (Exception e) {
			System.err.println("Error fetching toast message: " + e.getMessage());
			throw e;
		}
	}

	public String getNextFollowUpTypeToast() {
		try {
			return NextFollowUpTypeToast.getText();
		} catch (Exception e) {
			System.err.println("Error fetching toast message: " + e.getMessage());
			throw e;
		}
	}

	public String getEnquiryTypeToast() {
		try {
			return EnquiryTypeToast.getText();
		} catch (Exception e) {
			System.err.println("Error fetching toast message: " + e.getMessage());
			throw e;
		}
	}

	public String getVerificationToast() {
		try {
			return VerificationToast.getText();
		} catch (Exception e) {
			System.err.println("Error fetching toast message: " + e.getMessage());
			throw e;
		}
	}

	public void clickTestDriveAppointmentOldDateSlot() {
		try {
			TestDriveAppointmentOldDateSlot.click();
		} catch (Exception e) {
			System.err.println("Error clicking Test Drive Appointment Old Date slot: " + e.getMessage());
			throw e;
		}
	}
	
	public void clickMobileSearchIcon() {
		try {
			MobileSearchIcon.click();
		} catch (Exception e) {
			System.err.println("Error clicking Mobile Search Icon: " + e.getMessage());
			throw e;
		}
	}

	public void clickTestDriveAppointmentValidDateSlot() {
		try {
			TestDriveAppointmentValidDateSlot.click();
		} catch (Exception e) {
			System.err.println("Error clicking Test Drive Appointment Valid Date slot: " + e.getMessage());
			throw e;
		}
	}

	public void clickTestDriveAppointmentNextDateIcon() {
		try {
			TestDriveAppointmentNextdateIcon.click();
		} catch (Exception e) {
			System.err.println("Error clicking Test Drive Appointment Next date Icon: " + e.getMessage());
			throw e;
		}
	}

	public void clickAllocatePopupAllocateButton() {
		try {
			AllocatePopupAllocateButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Allocate Popup Allocate Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickTestDriveAppointmentTabCloseIcon() {
		try {
			TestDriveAppointmentTabCloseIcon.click();
		} catch (Exception e) {
			System.err.println("Error clicking Test Drive Appointment Tab Close Icon: " + e.getMessage());
			throw e;
		}
	}

	public String getTestDriveAppointmentInvalidDateToast() {
		try {
			return TestDriveAppointmentInvalidDateToast.getText();
		} catch (Exception e) {
			System.err.println("Error retrieving Test Drive Appointment Invalid Date Toast message: " + e.getMessage());
			throw e;
		}
	}

	public String getTestDriveAppointmentSuccessToast() {
		try {
			return TestDriveAppointmentSuccessToast.getText();
		} catch (Exception e) {
			System.err.println("Error retrieving Test Drive Appointment Success Toast message: " + e.getMessage());
			throw e;
		}
	}

	public String getTestDriveAppointmentEmptyDateToast() {
		try {
			return TestDriveAppointmentEmptyDateToast.getText();
		} catch (Exception e) {
			System.err.println("Error retrieving Test Drive Appointment Empty Date Toast message: " + e.getMessage());
			throw e;
		}
	}

	public void clickleadEnquiryCheckBox() {
		try {
			leadEnquiryCheckBox.click();
		} catch (Exception e) {
			System.err.println("Error clicking lead Enquiry CheckBox: " + e.getMessage());
			throw e;
		}
	}

	public void clickleadTabAllocateButton() {
		try {
			leadTabAllocateButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking lead Tab Allocate Button: " + e.getMessage());
			throw e;
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

	public void clickCustomerEnquirySubmenu() {
		try {
			CustomerEnquirySubmenu.click();
		} catch (Exception e) {
			System.err.println("Error clicking Customer Enquiry Sub-menu: " + e.getMessage());
			throw e;
		}
	}

	public void clickCustomerEnquiryLink() {
		try {
			CustomerEnquiryLink.click();
		} catch (Exception e) {
			System.err.println("Error clicking Customer Enquiry Link: " + e.getMessage());
			throw e;
		}
	}

	public void clickNewEnquiryButton() {
		try {
			NewEnquiry.click();
		} catch (Exception e) {
			System.err.println("Error clicking New Enquiry button: " + e.getMessage());
			throw e;
		}
	}

	// Method to check if Sales Customer Enquiry Pop-up is displayed
	public boolean isSalesCustomerEnquiryPopupDisplayed() {
		try {
			return SalesCustomerEnquiryPopupHeader.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Sales Customer Enquiry Pop-up visibility: " + e.getMessage());
			return false;
		}
	}
	
	// Method to check if Find A customer Info Pop-up is displayed
		public boolean isFindACustomerInfoPopupDisplayed() {
			try {
				return FindACustomerPopupHeader.isDisplayed();
			} catch (Exception e) {
				System.err.println("Error checking Find A customer Info Pop-up visibility: " + e.getMessage());
				return false;
			}
		}

	// Method to check if Follow Up Tab Screen is displayed
	public boolean isFollowUpTabScreenDisplayed() {
		try {
			return FollowUpTabScreen.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Follow Up Tab Screen visibility: " + e.getMessage());
			return false;
		}
	}

	// Method to check if Basic Info Tab is displayed
	public boolean isBasicInfoTabDisplayed() {
		try {
			return BasicInfoTab.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Basic InfoTab visibility: " + e.getMessage());
			return false;
		}
	}

	// Method to check if Allocate Pop-up is displayed
	public boolean isAllocatePopUpDisplayed() {
		try {
			return AllocatePopupHeader.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Allocate Pop-up visibility: " + e.getMessage());
			return false;
		}
	}

	// Method to check if Lead Enquiry Table is displayed
	public boolean isleadEnquiryTableDisplayed() {
		try {
			return leadEnquiryTable.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Lead Enquiry Table visibility: " + e.getMessage());
			return false;
		}
	}

	public void clickCloseIcon() {
		try {
			CloseIcon.click();
		} catch (Exception e) {
			System.err.println("Error clicking Close Icon: " + e.getMessage());
			throw e;
		}
	}
	public void clickFindaCustomerCloseIcon() {
		try {
			FindACustomerPopupCloseIcon.click();
		} catch (Exception e) {
			System.err.println("Error clicking Close Icon: " + e.getMessage());
			throw e;
		}
	}

	// Method to check if Customer Enquiry screen is displayed
	public boolean isCustomerEnquiryScreenDisplayed() {
		try {
			return CustomerEnquiryscreenHeader.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Customer Enquiry Screen visibility: " + e.getMessage());
			return false;
		}
	}

	// Method to check if Promotion Cross Icon is displayed
	public boolean isPromotionCrossIconDisplayed() {
		try {
			return PromotionCrossIcon.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Promotion Cross Icon visibility: " + e.getMessage());
			return false;
		}
	}

	public void clickPromotionCrossIcon() {
		try {
			PromotionCrossIcon.click();
		} catch (Exception e) {
			System.err.println("Error clicking Promotion Cross Icon: " + e.getMessage());
			throw e;
		}
	}

	public void clickPincodeSearchIcon() {
		try {
			PinCodeSearchIcon.click();
		} catch (Exception e) {
			System.err.println("Error clicking PinCode Search Icon: " + e.getMessage());
			throw e;
		}
	}

	// Method to check if Pincode search Screen is displayed
	public boolean isPincodeSearchScreenDisplayed() {
		try {
			return PincodesearchScreenHeader.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Pincode search Screen visibility: " + e.getMessage());
			return false;
		}
	}

	// Method to check if Test Drive Appointment screen Header is displayed
	public boolean isTestDriveAppointmentScreenDisplayed() {
		try {
			return TestDriveAppointmentHeader.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Test Drive Appointment screen visibility: " + e.getMessage());
			return false;
		}
	}

	public void clickTestDriveAppointmentSaveButton() {
		try {
			TestDriveAppointmentSaveButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Test Drive Appointment Save Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickPinCodeSearchButton() {
		try {
			PinCodeSearchButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking PinCode Search Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickLocationSelection() {
		try {
			LocationSelection.click();
		} catch (Exception e) {
			System.err.println("Error clicking Location Selection from Pincodes List : " + e.getMessage());
			throw e;
		}
	}
	
	public void clickLocationSelectionAfterFilters() {
		try {
			LocationSelection2.click();
		} catch (Exception e) {
			System.err.println("Error clicking Location Selection from Pincodes List : " + e.getMessage());
			throw e;
		}
	}

	public void clickleadTabSearchButton() {
		try {
			leadTabSearchButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking lead Tab Search Button : " + e.getMessage());
			throw e;
		}
	}

	public void clickFollowUpTab() {
		try {
			FollowUpTab.click();
		} catch (Exception e) {
			System.err.println("Error clicking Follow Up Tab : " + e.getMessage());
			throw e;
		}
	}

	public void clickAddSelectedButton() {
		try {
			AddSelectedButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Add Selected Button: " + e.getMessage());
			throw e;
		}
	}

	// Method to retrieve the text or value from the Pincode field
	public String getPincode() {
		try {
			return DisabledPincodeField.getAttribute("value");
		} catch (Exception e) {
			System.err.println("Error retrieving Pincode field value: " + e.getMessage());
			throw e;
		}
	}

	// Method to select a state from the Field
	public void selectState(String stateName) throws Exception {
		try {
			StateField.click();
			Thread.sleep(5000);
			StateField.sendKeys(stateName); // Enter the desired state name
			StateField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting state: " + e.getMessage());
			throw e;
		}
	}

	// Method to select a Next Follow Up Type from the dropdown
	public void selectNextFollowUpType(String nextfollowuptype) {
		try {
			Select dropdown = new Select(NextFollowUpTypeDropDown);
			dropdown.selectByVisibleText(nextfollowuptype);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Next Follow Up Type: " + e.getMessage());
			throw e;
		}
	}

	// Method to select a Verification from the dropdown
	public void selectVerification(String verificationdropdown) {
		try {
			Select dropdown = new Select(VerificationDropDown);
			dropdown.selectByVisibleText(verificationdropdown);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Verification: " + e.getMessage());
			throw e;
		}
	}

	// Method to select a Sales Consultant from the Sales Consultant dropdown
	public void selectAllocateSalesConsultant(String salesconsultant) {
		try {
			Select dropdown = new Select(AllocateSalesConsultantDropdown);
			dropdown.selectByVisibleText(salesconsultant);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Sales Consultant: " + e.getMessage());
			throw e;
		}
	}

	// Method to select a TD Offer from the TD Offer dropdown
	public void selectTDOfferField(String TDOffer) {
		try {
			TDOfferField.click();
			Thread.sleep(5000);
			TDOfferField.sendKeys(TDOffer); // Enter the desired TD Offer
			TDOfferField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting TD Offer: " + e.getMessage());
		}
	}

	// Method to select a TD VIN from the TD VIN dropdown
	public void selectTDVINField(String TDVIN) {
		try {
			TDVINField.click();
			Thread.sleep(5000);
			TDVINField.sendKeys(TDVIN); // Enter the desired TD VIN
			TDVINField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting TD VIN: " + e.getMessage());
		}
	}

	// Method to select a District from the Field
	public void selectDistrict(String DistrictName) throws Exception {
		try {
			DistrictField.click();
			Thread.sleep(5000);
			DistrictField.sendKeys(DistrictName); // Enter the desired state name
			DistrictField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting District: " + e.getMessage());
			throw e;
		}
	}

	// Method to select a Taluka from the Field
	public void selectTaluka(String TalukaName) throws Exception {
		try {
			TalukaField.click();
			Thread.sleep(5000);
			TalukaField.sendKeys(TalukaName); // Enter the desired state name
			TalukaField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Taluka: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Post Office Name
	public void enterPostOfficeName(String postofficename) {
		try {
			PostOfficeName.clear(); // Clear the field if necessary
			PostOfficeName.sendKeys(postofficename);
		} catch (Exception e) {
			System.err.println("Error entering Post Office Name: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Next Follow Up Date Time
	public void enterNextFollowUpDateTime(String nextfollowupdatetime) {
		try {
			NextFollowUpDateTime.clear(); // Clear the field if necessary
			NextFollowUpDateTime.sendKeys(nextfollowupdatetime);
		} catch (Exception e) {
			System.err.println("Error entering Next Follow Up Date Time: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Scheme Offered
	public void enterSchemeOffered(String schemeoffered) {
		try {
			SchemeOffered.clear(); // Clear the field if necessary
			SchemeOffered.sendKeys(schemeoffered);
		} catch (Exception e) {
			System.err.println("Error entering Scheme Offered: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Follow Up Remarks
	public void enterFollowUpRemarks(String followupremarks) {
		try {
			FollowUpRemarks.clear(); // Clear the field if necessary
			FollowUpRemarks.sendKeys(followupremarks);
		} catch (Exception e) {
			System.err.println("Error entering Follow Up Remarks: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Pincode
	public void enterPincode(String pincode) {
		try {
			Pincode.clear(); // Clear the field if necessary
			Pincode.sendKeys(pincode);
		} catch (Exception e) {
			System.err.println("Error entering Pincode: " + e.getMessage());
			throw e;
		}
	}

	public String getEnquiryAddedToast() {
		try {
			return EnquiryAddedToast.getText();
		} catch (Exception e) {
			System.err.println("Error fetching toast message: " + e.getMessage());
			throw e;
		}
	}

	public String getEmptyEnquiryToast() {
		try {
			return EmptyEnquiryToast.getText();
		} catch (Exception e) {
			System.err.println("Error fetching toast message: " + e.getMessage());
			throw e;
		}
	}

	public String getInvalidMobileNumberToast() {
		try {
			return InvalidMobileNumberToast.getText();
		} catch (Exception e) {
			System.err.println("Error fetching Invalid Mobile Number toast message: " + e.getMessage());
			throw e;
		}
	}

	public String getNewMobileNumberToast() {
		try {
			return NewMobileToast.getText();
		} catch (Exception e) {
			System.err.println("Error fetching New Mobile Number toast message: " + e.getMessage());
			throw e;
		}
	}

	public String getInvalidEmailToast() {
		try {
			return InvalidEmailToast.getText();
		} catch (Exception e) {
			System.err.println("Error fetching Invalid Email Toast message: " + e.getMessage());
			throw e;
		}
	}

	 // Action to enter Mobile Number
	public void enterMobileNumber(String mobilenumber) throws Exception {
		try {
			Thread.sleep(3000);		
			MobileNumber.sendKeys(mobilenumber);
		} catch (Exception e) {
			System.err.println("Error entering MobileNumber: " + e.getMessage());
			throw e;
		}
	}
	
	// Action to enter Email Id
	public void enterEmail(String email) throws Exception {
		try {
			Thread.sleep(5000);
			Email.sendKeys(email);
		} catch (Exception e) {
			System.err.println("Error entering Email: " + e.getMessage());
			throw e;
		}
	}

	public void clickSaveButton() {
		try {
			SaveButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Save Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickFollowUpTabSaveButton() {
		try {
			FollowUpTabSaveButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Follow Up Tab Save Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickBasicInfoTabSaveButton() {
		try {
			BasicInfoTabSaveButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Basic Info Tab Save Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickTestDriveAppointmentButton() {
		try {
			TestDriveAppointmentButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Test Drive Appointment Button: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Cust Name
	public void enterCustName(String custname) {
		try {
			CustName.clear();
			CustName.sendKeys(custname);
		} catch (Exception e) {
			System.err.println("Error entering Cust Name: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Residence Phone
	public void enterResidencePhone(String residencephone) {
		try {
			ResidencePhone.clear();
			ResidencePhone.sendKeys(residencephone);
		} catch (Exception e) {
			System.err.println("Error entering Residence Phone: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter WhatsApp Id
	public void enterWhatsAppId(String whatsappid) {
		try {
			WhatsAppId.clear();
			WhatsAppId.sendKeys(whatsappid);
		} catch (Exception e) {
			System.err.println("Error entering WhatsApp Id: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Address
	public void enterAddress(String address) {
		try {
			Address.clear();
			Address.sendKeys(address);
		} catch (Exception e) {
			System.err.println("Error entering Address: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Village
	public void enterVillage(String village) {
		try {
			Village.clear();
			Village.sendKeys(village);
		} catch (Exception e) {
			System.err.println("Error entering Village: " + e.getMessage());
			throw e;
		}
	}

	public void selectCustTypeField(String CustType) throws Exception {
			try {
				CustTypeField.click();
				Thread.sleep(5000);
				CustTypeField.sendKeys(CustType); // Enter the desired Cust Type
				CustTypeField.sendKeys(Keys.ENTER);
			} catch (Exception e) {
				System.err.println("An error occurred while selecting Cust Type: " + e.getMessage());
				throw e;
			}
		}

	public void selectGender(String Gender) throws Exception {
		try {
			    GenderField.click();
				Thread.sleep(5000);
				GenderField.sendKeys(Gender); // Enter the desired Gender
				GenderField.sendKeys(Keys.ENTER);
			} catch (Exception e) {
				System.err.println("An error occurred while selecting Gender: " + e.getMessage());
				throw e;
			}
		}


	public void selectLocation(String Location) throws Exception {
		try {
			LocationField.click();
			Thread.sleep(5000);
			LocationField.sendKeys(Location); // Enter the desired Location
			LocationField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Gender: " + e.getMessage());
			throw e;
		}
	}

	public void selectEnquirySource(String EnquirySource) throws Exception {
		try {
			EnquirySourceField.click();
			Thread.sleep(5000);
			EnquirySourceField.sendKeys(EnquirySource); // Enter the desired Enquiry Source
			EnquirySourceField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Enquiry Source: " + e.getMessage());
			throw e;
		}
	}

	public void selectEnquirySubSource(String EnquirySubSource) throws Exception {
		try {
			EnquirySubSourceField.click();
			Thread.sleep(5000);
			EnquirySubSourceField.sendKeys(EnquirySubSource); // Enter the desired Enquiry Sub Source
			EnquirySubSourceField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Enquiry Sub Source: " + e.getMessage());
			throw e;
		}
	}

	public void selectEnquiryCategory(String EnquiryCategory) throws Exception {
		try {
			EnquiryCategoryField.click();
			Thread.sleep(5000);
			EnquiryCategoryField.sendKeys(EnquiryCategory); // Enter the desired Enquiry Category
			EnquiryCategoryField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Enquiry Category: " + e.getMessage());
			throw e;
		}
	}

	public void selectPersonInCharge(String PersonInCharge) throws Exception {
		try {
			PersonInChargeField.click();
			Thread.sleep(5000);
			PersonInChargeField.sendKeys(PersonInCharge); // Enter the desired Person In Charge
			PersonInChargeField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Person In Charge: " + e.getMessage());
			throw e;
		}
	}

	public void selectModel(String Model) throws Exception {
		try {
			ModelField.click();
			Thread.sleep(5000);
			ModelField.sendKeys(Model); // Enter the desired Model
			ModelField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Model: " + e.getMessage());
			throw e;
		}
	}

	public void selectFuelType(String FuelType) throws Exception {
		try {
			FuelTypeField.click();
			Thread.sleep(5000);
			FuelTypeField.sendKeys(FuelType); 
			FuelTypeField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Fuel Type: " + e.getMessage());
			throw e;
		}
	}

	public void selectVariant(String Variant) throws Exception {
		try {
			VariantField.click();
			Thread.sleep(5000);
			VariantField.sendKeys(Variant); 
			VariantField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Variant: " + e.getMessage());
			throw e;
		}
	}
	
	public void selectSubVariant(String SubVariant) throws Exception {
		try {
			SubVariantField.click();
			Thread.sleep(5000);
			SubVariantField.sendKeys(SubVariant); 
			SubVariantField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Sub Variant: " + e.getMessage());
			throw e;
		}
	}

	public void selectExtColor(String ExtColor) throws Exception {
		try {
			ExtColorField.click();
			Thread.sleep(5000);
			ExtColorField.sendKeys(ExtColor); 
			ExtColorField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting ExtColor: " + e.getMessage());
			throw e;
		}
	}

	public void selectIntColor(String IntColor) throws Exception {
		try {
			IntColorField.click();
			Thread.sleep(5000);
			IntColorField.sendKeys(IntColor); 
			IntColorField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Int Color: " + e.getMessage());
			throw e;
		}
	}
	
	public void selectSalesConsultant(String SalesConsultant) throws Exception {
		try {
			SalesConsultantField.click();
			Thread.sleep(5000);
			SalesConsultantField.sendKeys(SalesConsultant); 
			SalesConsultantField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Sales Consultant: " + e.getMessage());
			throw e;
		}
	}

	// Method to verify the user is in the Lead Tab
	public boolean isInLeadTabDisplayed() throws Exception {
		try {
			// Check if the Lead Tab is displayed
			return leadTab.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error verifying Lead Tab presence: " + e.getMessage());
			throw new Exception("Failed to verify Lead Tab.", e);
		}
	}

	// Action to enter Cust Name Filter
	public void enterCustNameFilter(String custnamefilter) {
		try {
			CustNameFilter.clear(); // Clear the field if necessary
			CustNameFilter.sendKeys(custnamefilter);
		} catch (Exception e) {
			System.err.println("Error entering Cust Name Filter: " + e.getMessage());
			throw e;
		}
	}

}
