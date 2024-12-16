package com.autogrid.steps;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;

public class CustBookingmgmtPage {

	private final CommonActions commonActions;
	private static final Logger logger = LoggerFactory.getLogger(CustBookingmgmtPage.class);
	public CustBookingmgmtPage (WebDriver driver){
        this.commonActions = new CommonActions(driver);
        PageFactory.initElements(driver, this);
    }

    public void launchDMSSite() throws InterruptedException {
        LaunchDriver.launchSite();
    }
	

	@FindBy(xpath = "//span[contains(text(),'Customer Booking Mgt List')]")
	private WebElement CustomerBookingMgmtListTab;

	@FindBy(xpath = "//a[normalize-space()='Customer Booking Mgt']")
	private WebElement CustomerBookingMgt;

	@FindBy(xpath = "//button[@id='btnDwnldDigitalDocket']")
	private WebElement DwnldDigitalDocket;

	@FindBy(xpath = "//button[@id='btnActiveVariant']")
	private WebElement ActiveVariant;

	@FindBy(xpath = "//button[@id='btnCtb']")
	private WebElement ClickToBuy;

	@FindBy(xpath = "//button[@id='btnEnquiryCancel']")
	private WebElement EnquiryCancellation;

	@FindBy(xpath = " //input[@id='dotYn']")
	private WebElement Checkboxdot;

	@FindBy(xpath = " //input[@id='smsYn']")
	private WebElement Checkboxsms;

	// Basic Info WebElements

	@FindBy(xpath = "//input[@id='custNo']")
	private WebElement CustomerIdBasic;

	@FindBy(xpath = "//span[@aria-owns='custType_listbox']//span[@class='k-input']")
	private WebElement CustomerTypeBasic;

	@FindBy(xpath = "//input[@id='custName']")
	private WebElement CustomerNameBasic;

	@FindBy(xpath = "//input[@id='custBrtDate']")
	private WebElement CustomerDOBBasic;

	@FindBy(xpath = "//span[@aria-owns='gender_listbox']//span[@class='k-input']")
	private WebElement GenderBasic;

	@FindBy(xpath = "//input[@id='custGstNo']")
	private WebElement CustomerGSTNoBasic;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/section[@id='basicInfo']/div[2]/dl[2]/dd[3]/span[1]/span[1]/span[2]/span[1]")
	private WebElement OccupationBasic;

	@FindBy(xpath = "//input[@id='eMail']")
	private WebElement EMailBasic;

	@FindBy(xpath = "//input[@id='addr']")
	private WebElement AddressBasic;

	@FindBy(xpath = "//input[@id='state']")
	private WebElement StateBasic;

	@FindBy(xpath = " //input[@id='city']")
	private WebElement CityTownBasic;

	@FindBy(xpath = "//input[@id='tehsilCd']")
	private WebElement DistrictBasic;

	@FindBy(xpath = "//input[@id='villageNm']")
	private WebElement VillageBasic;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/section[@id='basicInfo']/div[2]/dl[4]/dd[3]/span[1]/span[1]/span[2]/span[1]")
	private WebElement LocationBasic;

	@FindBy(xpath = "//input[@id='pinCd']")
	private WebElement PinBasic;

	@FindBy(xpath = "//input[@id='contactNo']")
	private WebElement ContactNoBasic;

	@FindBy(xpath = " //input[@id='phonNo']")
	private WebElement PhoneNoBasic;

	@FindBy(xpath = "//input[@id='addAltNo']")
	private WebElement AlternateNoBasic;

	@FindBy(xpath = "//input[@id='DlrNm']")
	private WebElement DealerNameBasic;

	@FindBy(xpath = "//span[@aria-owns='enSourceCd_listbox']//span[@class='k-input']")
	private WebElement EnquirySourceBasic;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/section[@id='basicInfo']/div[3]/dl[1]/dd[3]/span[1]/span[1]/span[2]/span[1]")
	private WebElement EnquirySubSourceBasic;

	@FindBy(xpath = "//input[@id='enquiryDate']")
	private WebElement EnquiryDateBasic;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/section[@id='basicInfo']/div[3]/dl[2]/dd[1]/span[1]/span[1]/span[2]/span[1]")
	private WebElement EnquiryTypeDrpdwnBasic;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/section[@id='basicInfo']/div[3]/dl[2]/dd[2]/span[1]/span[1]/span[2]/span[1]")
	private WebElement EnquiryCategorydrpDwnBasicInfo;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/section[@id='basicInfo']/div[3]/dl[2]/dd[3]/span[1]/span[1]/span[2]/span[1]")
	private WebElement SalesConsultantDrpDwnBasicInfo;

	@FindBy(xpath = "//input[@id='eqryRfrlBy']")
	private WebElement ReferredByBasicInfo;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/section[@id='basicInfo']/div[4]/dl[1]/dd[1]/span[1]/span[1]/span[2]/span[1]")
	private WebElement FinanceReqDrpDwnBasic;

	@FindBy(xpath = "//input[@id='eqryCorpName']")
	private WebElement CorpNameBasic;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/section[@id='basicInfo']/div[4]/dl[2]/dd[1]/span[1]/span[1]/span[2]/span[1]")
	private WebElement ModelBasic;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/section[@id='basicInfo']/div[4]/dl[2]/dd[1]/span[1]/span[1]/span[2]/span[1]")
	private WebElement FuelTypeBasic;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/section[@id='basicInfo']/div[4]/dl[2]/dd[3]/span[1]/span[1]/span[2]/span[1]")
	private WebElement VariantDrpDwnBasic;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/section[@id='basicInfo']/div[4]/dl[2]/dd[4]/span[1]/span[1]/span[2]/span[1]")
	private WebElement SubVariantDrpDwnBasic;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/section[@id='basicInfo']/div[4]/dl[3]/dd[1]/span[1]/span[1]/span[2]/span[1]")
	private WebElement ExtColorDrpDwnBasic;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/section[@id='basicInfo']/div[4]/dl[3]/dd[2]/span[1]/span[1]/span[2]/span[1]")
	private WebElement IntColorDrpDwnBasic;

	@FindBy(xpath = "//input[@id='promoCode']")
	private WebElement PromoCodeBasic;

	@FindBy(xpath = "//input[@id='testDriveTakenFlag']")
	private WebElement TestDriveBasic;

	/// Booking Info WebElements

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/div[1]/div[1]/div[2]/div[2]/section[1]/div[2]/dl[2]/dd[1]/span[1]/span[1]/span[2]/span[1]")
	private WebElement ModeOfPurchaseDrpDwnBooking;

	@FindBy(xpath = "//input[@id='dsaNm']")
	private WebElement DSANameBooking;

	@FindBy(xpath = "//input[@id='dsaNm']")
	private WebElement RevisedDeliveryDateBooking;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/div[1]/div[1]/div[2]/div[2]/section[1]/div[2]/dl[9]/dd[1]/span[1]/span[1]/span[2]/span[1]")
	private WebElement ActionTakenDrpDwnBooking;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/div[1]/div[1]/div[2]/div[2]/section[1]/div[2]/dl[9]/dl[2]/dd[1]/span[1]/span[1]/span[2]/span[1]")
	private WebElement ReasonForCancellationDRpDwnBooking;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/div[1]/div[1]/div[2]/div[2]/section[1]/div[2]/dl[9]/dl[2]/dd[2]/span[1]/span[1]/input[1]")
	private WebElement ApprovedLoanAmountBooking;

	/// Registration Info WebElement

	@FindBy(xpath = "//button[@id='btnBookingRegister']")
	private WebElement RegisterButtonInBookingPage;

	@FindBy(xpath = "//input[@id='registrationNm']")
	private WebElement RegistrationNameReg;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/div[1]/div[1]/div[2]/div[2]/section[2]/div[2]/dl[1]/dd[2]/span[1]/span[1]/span[2]/span[1]")
	private WebElement CustIncomeDrpDwnReg;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/div[1]/div[1]/div[2]/div[2]/section[2]/div[2]/dl[2]/dd[1]/span[1]/span[1]/span[2]/span[1]")
	private WebElement TitleDrpDwnReg;

	@FindBy(xpath = "//button[@id='btnMainModify']")
	private WebElement ModifyBtnInBookingPage;

	@FindBy(xpath = "//button[@id='btnEnquiryCancel']")
	private WebElement EnquiryCancellationBtnInBookingPage;

	@FindBy(xpath = "//button[@id='btnBookingRegister']")
	private WebElement BookingRegisterBtnInBookingPage;

	@FindBy(xpath = "//input[@id='pan']")
	private WebElement PANReg;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/div[1]/div[1]/div[2]/div[2]/section[2]/div[2]/dl[2]/dd[3]/span[1]/span[1]/span[2]/span[1]")
	private WebElement Form60collectedDrpDwnReg;

	@FindBy(xpath = "//input[@id='addressTab']")
	private WebElement AddressInRegInfo;

	@FindBy(xpath = "//input[@id='stateTab']")
	private WebElement StateInRegInfo;

	@FindBy(xpath = "//input[@id='cityTab']")
	private WebElement CityTownInRegInfo;

	@FindBy(xpath = "//input[@id='teshilTab']")
	private WebElement DistrictInRegInfo;

	@FindBy(xpath = "//input[@id='villageTab']")
	private WebElement VillageReg;

	// pin code search page
	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/div[1]/div[1]/div[2]/div[2]/section[2]/div[2]/dl[6]/dd[1]/div[1]/a[1]")
	private WebElement PinSearchIconReg;

	@FindBy(xpath = "//body/div[@id='popDiv']/section[@id='window']/div[2]/dl[1]/dd[1]/span[1]/span[1]/span[2]/span[1]")
	private WebElement StateDrpDwnInPoPUpReg;

	@FindBy(xpath = "//body/div[@id='popDiv']/section[@id='window']/div[2]/dl[1]/dd[2]/span[1]/span[1]/span[2]/span[1]")
	private WebElement DistrictDrpDwnInPopREg;

	@FindBy(xpath = "//body/div[@id='popDiv']/section[@id='window']/div[2]/dl[1]/dd[2]/span[1]/span[1]/span[2]/span[1]")
	private WebElement Taluka_TehsilDrpDwnInPopReg;

	@FindBy(xpath = "//input[@id='sPostOfceName']")
	private WebElement PostOfficeNameInPopReg;

	@FindBy(xpath = "//button[@id='btnAddSelected']")
	private WebElement AddSelectedBtnInPopReg;

	@FindBy(xpath = "#btnSearch")
	private WebElement SearchButtonInPopREg;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/div[1]/div[1]/div[2]/div[2]/section[2]/div[2]/dl[7]/dd[1]/span[1]/span[1]/span[2]/span[1]")
	private WebElement PreferredNoPlateDrpDwn;

	@FindBy(xpath = "//input[@id='preferredNo']")
	private WebElement PrefereredNoREg;

	@FindBy(xpath = "//input[@id='bkngTcsFlag']")
	private WebElement TCSFlagbtn;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/div[1]/div[1]/div[2]/div[2]/section[2]/div[2]/dl[8]/dd[1]/span[1]/span[1]/span[2]/span[1]")
	private WebElement W_S_D_O_DropDwn;

	@FindBy(xpath = "//input[@id='name']")
	private WebElement NameRegInfo;

	@FindBy(xpath = "//*[@id='grid']/div[2]/table/tbody/tr[1]")
	private WebElement LocatedPinInPinSearchtable;

	@FindBy(xpath = "//body/div[118]/div[2]/p[2]/button[1]")
	private WebElement iFrameElementCofirmBtn;

	@FindBy(xpath = "//span[@id='pinCodeSearchPopup_wnd_title']")
	private static WebElement PinCodeSearchScreenHeader;

	@FindBy(xpath = "//span[contains(text(),'Notice')]")
	private static WebElement ModifyPopUpHeader;

	@FindBy(xpath = "//body/div[118]/div[1]/div[1]/a[1]/span[1]")
	private static WebElement crossIconInModifyPopScreen;

	@FindBy(xpath = "//body/section[@id='content']/div[@id='resizableContainer']/section[@id='content']/div[1]/div[1]/div[2]/div[2]/section[1]/div[2]/dl[2]/dd[1]/span[1]/span[1]/span[2]/span[1]")
	private static WebElement ModeOfPurchaseDrpdwn;

	public void ClickBookingRegiterButton() {
		try {
			BookingRegisterBtnInBookingPage.click();
		} catch (Exception e) {
			System.err.println("Error clicking BookingRegisterButton " + e.getMessage());
			throw e;
		}
	}

	public void ClickCheckboxsms() {
		try {
			Checkboxsms.click();
		} catch (Exception e) {
			System.err.println("Error clicking smscheckbox " + e.getMessage());
			throw e;
		}
	}

	// method to select a enquiryType from dropdown in Basic Info
	public void BasicInfoEnquiryTypeDrpdwn(String enquiryType) {
		try {
			Select dropdown = new Select(EnquiryTypeDrpdwnBasic);
			dropdown.selectByVisibleText(enquiryType);
		} catch (Exception e) {
			System.err.println("Error during selecting EnquiryType in Basic Info" + e.getMessage());
		}
	}

	// method to select a enquiryCategory from drop down
	public void BasicInfoEnquiryCategorydrpDwn(String enquiryCat) {
		try {
			Select dropdown = new Select(EnquiryCategorydrpDwnBasicInfo);
			dropdown.selectByVisibleText(enquiryCat);

		} catch (Exception e) {
			System.err.println("Error during selecting EnquiryCategory in Basic Info" + e.getMessage());
		}
	}

	// method to select a Sales Consultant from drop down
	public void selectSalesConsultant(String salesConsultant) {
		try {
			Select dropdown = new Select(SalesConsultantDrpDwnBasicInfo);
			dropdown.selectByVisibleText(salesConsultant);
		} catch (Exception e) {
			System.err.println("Error during selecting salesConsultant in Basic Info" + e.getMessage());
		}
	}

	// method to Enter Referred by
	public void enterReferredBy(String referredBy) {
		try {
			ReferredByBasicInfo.clear();
			ReferredByBasicInfo.sendKeys(referredBy);
		} catch (Exception e) {
			System.err.println("Error entering referredByname : " + e.getMessage());
			throw e;
		}
	}

	// method to select a Finance req from drop down
	public void selectFinanceReq(String financeReq) {
		try {
			Select dropdown = new Select(FinanceReqDrpDwnBasic);
			dropdown.selectByVisibleText(financeReq);
		} catch (Exception e) {
			System.out.println("Error during selecting FinanceReq " + e.getMessage());
		}
	}

	// method to select a fuel type from dropdown

	public void selectFuelType(String fueltype) {
		try {
			Select dropdown = new Select(FuelTypeBasic);
			dropdown.selectByVisibleText(fueltype);
		} catch (Exception e) {
			System.err.println("Error during selecting fueltype" + e.getMessage());
		}
	}

	// method to select a Variant from drop down
	public void selectVariant(String variant) {
		try {
			Select dropdown = new Select(VariantDrpDwnBasic);
			dropdown.selectByVisibleText(variant);
		} catch (Exception e) {
			System.err.println("Error during selecting Variant " + e.getMessage());
		}
	}

	// method to select a sub Variant from drop down
	public void selectSubVariant(String subvariant) {
		try {
			Select dropdown = new Select(SubVariantDrpDwnBasic);
			dropdown.selectByVisibleText(subvariant);
		} catch (Exception e) {
			System.err.println("Error during selecting SubVariant" + e.getMessage());
		}
	}

	// method to select a Ext Color from dropdown
	public void selectExtColor(String extColor) {
		try {
			Select dropdown = new Select(ExtColorDrpDwnBasic);
			dropdown.selectByVisibleText(extColor);
		} catch (Exception e) {
			System.out.println("Error during selecting ExtColor" + e.getMessage());
		}
	}

	// method to select a Int Color from dropdown
	public void selectIntColor(String intcolor) {
		try {
			Select dropdown = new Select(IntColorDrpDwnBasic);
			dropdown.selectByVisibleText(intcolor);
		} catch (Exception e) {
			System.err.println("Error during selecting intcolor " + e.getMessage());
		}
	}

	// method to select a mode of Purchase from drop down
	public void selectModeOfPurchase(String modeOfPurchase) {
		try {
			Select dropdown = new Select(ModeOfPurchaseDrpdwn);
			dropdown.selectByVisibleText(modeOfPurchase);

		} catch (Exception e) {
			System.err.println("Error during selecting mode of Purchase in booking Info" + e.getMessage());
		}
	}

//method to enter valid DSA Name
	public void enterValidDSAName(String dsaName) {
		try {
			DSANameBooking.clear();
			DSANameBooking.sendKeys(dsaName);
		} catch (Exception e) {
			System.err.println("Error entering dsa name : " + e.getMessage());
			throw e;
		}
	}

	// method to select valid reason for cancellation
	public void selectValidReasonForCancellation(String ReasonOfCancellation) {
		try {
			Select dropdwn = new Select(ReasonForCancellationDRpDwnBooking);
			dropdwn.selectByVisibleText(ReasonOfCancellation);

		} catch (Exception e) {
			System.err.println("Error selecting reason of cancellation : " + e.getMessage());
			throw e;
		}
	}

	// enter Registration Name in in registration info
	public void enterValidRegistrationName(String registrationName) {
		try {
			RegisterButtonInBookingPage.clear();
			RegisterButtonInBookingPage.sendKeys(registrationName);

		} catch (Exception e) {
			System.err.println("Error entering registration name : " + e.getMessage());
			throw e;
		}
	}

	// select valid Customer income
	public void selectCustIncome(String custIncome) {
		try {
			Select dropdwn = new Select(CustIncomeDrpDwnReg);
			dropdwn.selectByVisibleText(custIncome);

		} catch (Exception e) {
			System.err.println("Error selecting cust Income : " + e.getMessage());
			throw e;
		}
	}

	// select valid title in registration info
	public void selectTitleInRegInfo(String title) {
		try {
			Select dropdwn = new Select(TitleDrpDwnReg);
			dropdwn.selectByVisibleText(title);

		} catch (Exception e) {
			System.err.println("Error selecting cust Income : " + e.getMessage());
			throw e;
		}

	}

	// enter valid PAN in registration info
	public void enterValidPan(String pan) {
		try {
			PANReg.clear();
			PANReg.sendKeys(pan);
		} catch (Exception e) {
			System.err.println("Error entering pan: " + e.getMessage());
			throw e;
		}
	}

	// select valid form60 collected in registration info
	public void selectForm60Collected(String form60Selected) {
		try {
			Select dropdwn = new Select(Form60collectedDrpDwnReg);
			dropdwn.selectByVisibleText(form60Selected);

		} catch (Exception e) {
			System.err.println("Error selecting form60Selected : " + e.getMessage());
			throw e;
		}

	}

	// enter valid Address in registration info
	public void AddressInRegInfo(String address) {
		try {
			AddressInRegInfo.clear();
			AddressInRegInfo.sendKeys(address);
		} catch (Exception e) {
			System.err.println("Error entering address: " + e.getMessage());
			throw e;
		}
	}

	/// Methods for setting PIN In in registration info

	public void ClickOnPincodeSearchIcon() {
		try {
			PinSearchIconReg.click();
			Thread.sleep(3000);

		} catch (Exception e) {
			System.err.println("Error clicking Pincode Search Icon click: " + e.getMessage());
		}

	}

	public static boolean isPinSearchScreenDisplayed() {
		try {
			return PinCodeSearchScreenHeader.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error to see Pin Code Search Screen: " + e.getMessage());
			return false;
		}
	}

	public void validatedPinCodeScreen() {
		try {
			isPinSearchScreenDisplayed();

			Assert.assertTrue(isPinSearchScreenDisplayed());
			;
			System.out.println("Pincode search Screen is displayed.");
		} catch (Exception e) {

			Assert.fail("Pincode search Screen not diplayed.");
		}
	}

	public void enterPincodeInPincodeField() {

		try {
			Thread.sleep(2000);
			String pincode = "500050";
			PinBasic.clear();
			PinBasic.sendKeys(pincode);
			System.out.println("Entered Pincode: " + pincode);
		} catch (Exception e) {
			System.err.println("Error entering Pincode: " + e.getMessage());
		}
	}

	public void clicksOnSearchButtonInPinCodeSearchScreen()

	{
		try {
			Thread.sleep(2000);
			SearchButtonInPopREg.click();
			System.out.println(" error to click on PinCode Search Button ");

		} catch (Exception e) {
			System.err.println("Error while clicking Search:" + e.getMessage());
		}
	}

	public void selectOnePincodeFromList() {
		try {
			Thread.sleep(2000);
			LocatedPinInPinSearchtable.click();
			System.out.println("Pin entered is found in table");
		} catch (Exception e) {
			System.err.println("Pin not found in table");
		}
	}

	public void clickOnAddSelectedButtonInPincodeSearchScreen() throws InterruptedException {
		try {
			AddSelectedBtnInPopReg.click();
			System.out.println("Pin code added successfully");
		} catch (Exception e) {
			System.err.println("Pin code not added");
		}

		Thread.sleep(2000);
	}

	// enter valid village in registration info
	public void enterVillageInRegInfo(String village) {
		try {
			VillageReg.clear();
			VillageReg.sendKeys(village);
		} catch (Exception e) {
			System.err.println(" Error entering :" + e.getMessage());
		}

	}

	// select valid Preferred no plate in registration info
	public void selectPrefferedNoPlateInReg(String prefferedNoPlate) {
		try {

			Select drpdwn = new Select(PreferredNoPlateDrpDwn);
			drpdwn.selectByVisibleText(prefferedNoPlate);

		}

		catch (Exception e) {
			System.err.println(" Error in selecting Preffrered No plate selected:" + e.getMessage());
		}
	}

	// select valid W/O S/O D/O
	public void select_W_S_D_O(String wsdo) {
		try {

			Select drpdwn = new Select(W_S_D_O_DropDwn);
			drpdwn.selectByVisibleText(wsdo);
		} catch (Exception e) {
			System.err.println("");

		}

	}

	// enter valid relationship name
	public void enterRelationshipName(String name) {
		try {
			NameRegInfo.clear();
			NameRegInfo.sendKeys(name);
		} catch (Exception e) {
			System.err.println("Error while entering name:" + e.getMessage());
		}
	}

	// click on Register Button
	public void clickOnRegisterBtnInBookingPage() throws InterruptedException {
		try {
			RegisterButtonInBookingPage.click();
		} catch (Exception e) {
			System.err.println("Error while Clicking Register button in booking page");
		}
		Thread.sleep(1000);
	}

	// Pop Message "" appear asking for confirmation /////confirm from saif xpath of
	// iframe here
	public void clickConfirmBtnToRegisterInPopWindow() {
		try {

			LaunchDriver.getDriver().switchTo().frame(iFrameElementCofirmBtn);
			iFrameElementCofirmBtn.click();

		} catch (Exception e) {
			System.err.println("Error while clicking confirm btn to register");
		}
	}

	// Toast msg Box appear to register without filling mandatory field

	public String getRegisterToastMsg() {
	        try {
	            return "";//.getText();
	        } catch (Exception e) {
	            System.err.println("Error : " + e.getMessage());
	            throw e;
	        }
	    }

	// Methods after clicking Modify Button
	public void clickModifyButton() {
		ModifyBtnInBookingPage.click();
	}

	// method to check if modify pop screen displayed

	public boolean isModifyPopScreenDisplayed() {

		try {
			return ModifyPopUpHeader.isDisplayed();
		} catch (Exception e) {
			System.err.println("Error to see Modify pop screen: " + e.getMessage());
			return false;
		}
	}

	// method to close PoP Screen after modify button clicked
	public void clickCrossSignInModifyPopUp() {
		crossIconInModifyPopScreen.click();

	}

}
