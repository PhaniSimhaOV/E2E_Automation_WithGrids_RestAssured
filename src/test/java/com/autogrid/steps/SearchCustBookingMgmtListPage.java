package com.autogrid.steps;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;

public class SearchCustBookingMgmtListPage {
	private static final Logger logger = LoggerFactory.getLogger(SearchCustBookingMgmtListPage.class);
	private final CommonActions commonActions;

	public SearchCustBookingMgmtListPage(WebDriver driver) {
		this.commonActions = new CommonActions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy (xpath = "//a[normalize-space()='Sales']")
	private WebElement SalesMenu;

	@FindBy(xpath = "//a[normalize-space()='Sales Operation']")
	private WebElement SalesOperation;

	@FindBy(xpath = "//*[@id=\"gnb\"]/li[3]/div/ul/li[3]/ul/li[1]/a")
	private WebElement CustomerBookingMgmtLink;

	@FindBy(xpath = "//span[contains(text(),'Customer Booking Mgt List')]")
	private WebElement CustomerBookingMgmtListTab;

	@FindBy(xpath = "//*[@id=\"content\"]/section[1]/div[2]/dl[1]/dd[1]/span/span")
	private WebElement drpdwnDateOf;

	@FindBy(xpath = " //li[@id='15b2dbe1-3d28-48fe-ac46-c372f04bcbd8'] ")
	private WebElement Enquiry;

	@FindBy(xpath = "//li[contains(text(),'Booking')]")
	private WebElement Booking;

	@FindBy(xpath = "//li[@id='15b2dbe1-3d28-48fe-ac46-c372f04bcbd8']")
	private WebElement Invoice;

	@FindBy(xpath = "//li[contains(text(),'Invoice Confirm')]")
	private WebElement InvoiceConfirm;
	
	@FindBy(xpath="//*[@id=\"content\"]/section[1]/div[2]/dl[2]/dd[1]/span/span/span[2]/span")
	private WebElement drpdwnBasedOn;
	
	@FindBy(xpath="//li[contains(text(),'Customer Name')]")
	private WebElement CustomerName;
	
	@FindBy(xpath="//li[contains(text(),'Registration Name')]")
	private WebElement RegistrationName ;
	
	@FindBy(xpath="//li[contains(text(),'Customer ID')]")
	private WebElement CustomerID ;
	

	@FindBy(xpath="//li[@id='5dabc9d0-ba91-424d-91d1-cdcc658eb659']")
	private WebElement MobileNo ;
	
	
	@FindBy(xpath="//li[contains(text(),'SR Number')]")
	private WebElement SRNumber ;
	
	
	@FindBy(xpath="//li[contains(text(),'Order Ref No')]")
	private WebElement OrderRefNo  ;
	

	@FindBy(xpath = "//input[@id='sStartDt']")
	private WebElement StartDate;

	@FindBy(xpath = "//input[@id='sEndDt']")
	private WebElement EndDate;

	@FindBy(xpath = "//*[@id=\"content\"]/section[1]/div[2]/dl[1]/dd[3]/span/span/span[2]/span")

	private WebElement DrpdwnModel;

	@FindBy(xpath = "//li[@id='513a70b3-38a1-45d3-afee-9d0f60c728ae']")
	private WebElement EON_IA;

	@FindBy(xpath = "//li[contains(text(),'i10(CM)')]")
	private WebElement i10_CM;

	@FindBy(xpath = "//li[contains(text(),'Grand i10(B4)')]")
	private WebElement Grandi10_B4;

	@FindBy(xpath = "//li[contains(text(),'Xcent(XX)')]")
	private WebElement Xcent_XX;

	@FindBy(xpath = "//li[contains(text(),'i20(CN)')]")
	private WebElement i20_CN;

	@FindBy(xpath = "//li[contains(text(),'New i20(C7)')]")
	private WebElement New_i20_C7;

	@FindBy(xpath = "//li[contains(text(),'i20 Active(YY)')]")
	private WebElement i20_Active_YY;

	@FindBy(xpath = "//li[contains(text(),'Grand i10 NIOS(HQ)')]")
	private WebElement Grand_i10_NIOS_HQ;

	@FindBy(xpath = "//li[contains(text(),'New Tucson(9M)')]")
	private WebElement NewTucson9M;

	@FindBy(xpath = "//li[contains(text(),'New Verna(IL)')]")
	private WebElement NewVernaIL;

	@FindBy(xpath = "//li[contains(text(),'Kona EV(HO)')]")
	private WebElement KonaEV_HO;

	@FindBy(xpath = "//li[contains(text(),'Creta(A0)')]")
	private WebElement CretaA0;

	@FindBy(xpath = "//li[contains(text(),'Ioniq 5(6I)')]")
	private WebElement Ioniq5_6I;

	@FindBy(xpath = "//li[contains(text(),'Next Gen Verna(H6)')]")
	private WebElement NextGenVernaH6;

	@FindBy(xpath = "//li[contains(text(),'Santro(C4)')]")
	private WebElement SantroC4;

	@FindBy(xpath = "//li[contains(text(),'Tucson(HR)')]")
	private WebElement TucsonHR;

	@FindBy(xpath = "//li[contains(text(),'Elantra(S5)')]")
	private WebElement ElantraS5;

	@FindBy(xpath = "//li[contains(text(),'Neo Elantra(EZ)')]")
	private WebElement NeoElantraEZ;

	@FindBy(xpath = "//li[contains(text(),'New Sonata(EV)')]")
	private WebElement NewSonataEV;

	@FindBy(xpath = "//li[contains(text(),'Santa Fe(EW)')]")
	private WebElement SantaFe_EW;

	@FindBy(xpath = "//li[contains(text(),'AURA(ZZ)')]")
	private WebElement AURA_ZZ;

	@FindBy(xpath = "//li[contains(text(),'New Santa Fe (DM)(F7)')]")
	private WebElement NewSanta_Fe_DM_F7;

	
    
    
    
    @FindBy (xpath="//*[@id=\"mainGrid\"]/div[2]/div/table")
    
    ////*[@id="content"]/section[2]/div[@class='table_grid']*/
    private WebElement table;

	@FindBy(xpath = "//*[@id=\"mainGrid\"]/div[3]/table/tbody/tr")
	private List<WebElement> tableRows;

	@FindBy(xpath = "//*[@id=\"mainGrid\"]/div[3]/table/tbody/tr/td")
	private List<WebElement> tableColumns;

	
 
 


    
	  @FindBy(xpath="//*[@id=\"content\"]/section[1]/div[2]/dl[1]/dd[4]/span/span/span[1]")
	    private WebElement EnquiryStatus;

	@FindBy(xpath = "//span[contains(text(),'Mobile No')]")
	private WebElement BasedOn;

	@FindBy(xpath = "//input[@id='baseTxt']")
	private WebElement BaseText;

	@FindBy(xpath = "//*[@id=\"content\"]/section[1]/div[2]/dl[2]/dd[3]/span/span/span[1]")
	private WebElement DeliveryStatus;

	@FindBy(xpath = "//*[@id=\"content\"]/section[1]/div[2]/dl[2]/dd[4]")
	private WebElement TimePeriod;

	@FindBy(xpath = "//*[@id=\"content\"]/section[1]/div[2]/dl[2]/dd[5]/span/span/span[1]")
	private WebElement BookingFIFOSkip;

	@FindBy(xpath = "//button[@id='btnSave']")
	private WebElement SaveBtn;

	@FindBy(xpath = "//button[@id='btnSearch']")
	private WebElement SearchBtn;

	
	
	///Action Methods
	
	
	
	
	public void clickOnSalesMenu() {
		commonActions.clickElement(SalesMenu);
		;
	}

	public void clickOnSalesOperation() {
		commonActions.clickElement(SalesOperation);

	}

	public void CustomerBookingMgmtLink() {
		commonActions.clickElement(CustomerBookingMgmtLink);
	}

	public void CustomerBookingMgmtListTab() {

		commonActions.clickElement(CustomerBookingMgmtListTab);
	}

	// method to set DateOf
	public void DateOf() {
		commonActions.clickElement(drpdwnDateOf);
	}
	
	public void setEnquiry() {
		Select Cat = new Select(drpdwnDateOf);
		Cat.selectByVisibleText("Enquiry");
	}

	public void setBooking() {
		Select Cat = new Select(drpdwnDateOf);
		Cat.selectByVisibleText("Booking");
	}

	public void setInvoice() {
		Select Cat = new Select(drpdwnDateOf);
		Cat.selectByVisibleText("Invoice");
	}

	public void setInvoiceConfirm() {
		Select Cat = new Select(drpdwnDateOf);
		Cat.selectByVisibleText("InvoiceConfirm");
	}

	public void setBasedOn() {
		Select Cat = new Select(drpdwnBasedOn);
		Cat.selectByVisibleText("Mobile No");
	}
	
	public void BaseText(String Phoneno) {
		commonActions.clickElement(BaseText);
		BaseText.clear();
		BaseText.sendKeys(Phoneno);

	}


	// method to set Model

	public void DrpdwnModel() {

	}

	// method to capture  for validate customer

	public void Table() {

		commonActions.clickElement(table);
	}

	public int getNoOfRows() {

		return (tableRows.size());
	}

	public int getNoOfColumns() {

		return (tableColumns.size());

	}
	
public boolean searchCustomerByPhoneNo( String Phoneno) {
		
		boolean flag=false;
		
		for(int i=1;i<=getNoOfRows();i++){
			String phoneno=table.findElement(By.xpath("//*[@id=\"mainGrid\"]/div[3]/table/tbody/tr["+i+"]/td[10]")).getText();
			if(phoneno.equals(Phoneno)) {
				flag=true;
			}
		}
		return flag;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	public void StartDate() {

	}

	public void EndDate() {

	}

	public void EnquiryStatus() {

	}

	

	public void DeliveryStatus() {

	}

	public void TimePeriod() {

	}

	public void BookingFIFOSkip() {

	}

	public void SaveBtn() {
		commonActions.clickElement(SaveBtn);

	}

	public void SearchBtn() {
		commonActions.clickElement(SearchBtn);

	}

	// Method to get the page title
	public String getPageTitle() {
		return LaunchDriver.getDriver().getTitle();
	}

	// Method to verify the page title
	public boolean verifyPageTitle(String expectedTitle) {
		return LaunchDriver.getDriver().getTitle().equals(expectedTitle);
	}

}
