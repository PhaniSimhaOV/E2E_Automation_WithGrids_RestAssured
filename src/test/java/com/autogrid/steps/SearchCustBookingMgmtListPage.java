package com.autogrid.steps;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;

public class SearchCustBookingMgmtListPage {
	WebDriver driver;
	private static final Logger logger = LoggerFactory.getLogger(SearchCustBookingMgmtListPage.class);
	private final CommonActions commonActions;

	
	public SearchCustBookingMgmtListPage(WebDriver driver){
        this.commonActions = new CommonActions(driver);
        PageFactory.initElements(driver, this);
    }

    public void launchDMSSite() throws InterruptedException {
        LaunchDriver.launchSite();
    }
	
	

    @FindBy(xpath = "//a[normalize-space()='Sales']")
    private WebElement SalesMenu1;

	@FindBy(xpath = "//header/div[@id='sidebar']/div[1]/ul[1]/li[3]/div[1]/ul[1]/li[3]/a[1]")
	private WebElement SalesOperation;

	@FindBy(xpath = "//header/div[@id='sidebar']/div[1]/ul[1]/li[3]/div[1]/ul[1]/li[3]/ul[1]/li[1]/a[1]")
	private WebElement CustomerBookingMgmtLink;

	@FindBy(xpath = "//span[contains(text(),'Customer Booking Mgt List')]")
	private WebElement CustomerBookingMgmtListTab;
	
	@FindBy(xpath = "//body/div[@id='wrap']/div[@id='container']/div[1]/div[1]/ul[1]/li[3]/span[2]")
	private WebElement CustBookingMgmtHeader;

	@FindBy(xpath = "//*[@id=\"content\"]/section[1]/div[2]/dl[1]/dd[1]/span/span")
	private WebElement drpdwnDateOf;

	@FindBy(xpath = "//*[@id=\"content\"]/section[1]/div[2]/dl[2]/dd[1]/span/span/span[2]/span")
	private WebElement drpdwnBasedOn;

	@FindBy(xpath = "//li[contains(text(),'Customer Name')]")
	private WebElement CustomerName;

	@FindBy(xpath = "//li[contains(text(),'Registration Name')]")
	private WebElement RegistrationName;

	@FindBy(xpath = "//li[contains(text(),'Customer ID')]")
	private WebElement CustomerID;

	@FindBy(xpath = "//li[@id='5dabc9d0-ba91-424d-91d1-cdcc658eb659']")
	private WebElement MobileNo;

	@FindBy(xpath = "//li[contains(text(),'SR Number')]")
	private WebElement SRNumber;

	@FindBy(xpath = "//li[contains(text(),'Order Ref No')]")
	private WebElement OrderRefNo;

	@FindBy(xpath = "//input[@id='sStartDt']")
	private WebElement StartDate;

	@FindBy(xpath = "//input[@id='sEndDt']")
	private WebElement EndDate;

	@FindBy(xpath = "//*[@id=\"content\"]/section[1]/div[2]/dl[1]/dd[3]/span/span/span[2]/span")

	private WebElement DrpdwnModel;

	

	@FindBy(xpath = "//*[@id=\"mainGrid\"]/div[2]/div/table")

	//// *[@id="content"]/section[2]/div[@class='table_grid']*/
	private WebElement table;

	@FindBy(xpath = "//*[@id=\"mainGrid\"]/div[3]/table/tbody/tr")
	private List<WebElement> tableRows;

	@FindBy(xpath = "//*[@id=\"mainGrid\"]/div[3]/table/tbody/tr/td")
	private List<WebElement> tableColumns;

	@FindBy(xpath = "//*[@id=\"content\"]/section[1]/div[2]/dl[1]/dd[4]/span/span/span[1]")
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
	
	@FindBy(xpath = "//*[@id='template']")
	private WebElement SaveWithoutInputValidation;
	
	@FindBy(xpath="\"//*[@id=\\\"mainGrid\\\"]/div[3]/table/tbody/tr[1]")
	private WebElement RowOfSelectedPhoneNoInTable;
	
	
	

	/// Action Methods

	public void clickOnSalesIcon() {
		try {
		SalesMenu1.click();
		System.out.println("Sales Icon is clicked");
		}
		catch (Exception e) {
		System.err.println("Error in clicking Sales menu icon ");
		}

	}

	public void clickOnSalesOperation() {
		SalesOperation.click();;

	}

	public void CustomerBookingMgmtLink() {
		CustomerBookingMgmtLink.click();;
	}

	public boolean isCustBookingMgmtListheaderDisplayed() {
		try {

		return CustomerBookingMgmtListTab.isDisplayed();
		
		}
		catch(Exception e) {
			System.err.println("Error to see CustBooking mgmt link header ");
		return false;
		}
	}
	
	
	
	
	public boolean isCustBookingPageHeaderDisplayed()
	{
		try {
			return CustBookingMgmtHeader.isDisplayed();
		}
		catch(Exception e) {
			System.err.println("Error to see CustBooking header ");
		return false;
		}
	}	
	
	
	public String getToastMsgWhileSavingWithoutInput()
	{
		 try {
	            return SaveWithoutInputValidation.getText();
	        } catch (Exception e) {
	            System.err.println("Error to get toast message while clicking save button: " + e.getMessage());
	            throw e;
	        }
	    }
	    
	
	public void clickSaveButton()
	{
		SaveBtn.click();
	}
	
    
public void setDateOf(String dateOf) {
		
		
		Select drpdwn= new Select(drpdwnDateOf);
		drpdwn.selectByVisibleText(dateOf);
	}

public void setBasedOn(String basedOn) {
	
	Select drpdwn = new Select(drpdwnBasedOn);
	drpdwn.selectByVisibleText("basedOn");
}


public void typePhoneNo(String Phoneno) {
	commonActions.clickElement(BaseText);
	BaseText.clear();
	BaseText.sendKeys(Phoneno);

}
// method to capture for validate customer

public void Table() {

	commonActions.clickElement(table);
}

public int getNoOfRows() {

	return (tableRows.size());
}

public int getNoOfColumns() {

	return (tableColumns.size());

}

public boolean searchCustomerByPhoneNo(String Phoneno) {

	boolean flag = false;
	try
	{	for (int i = 1; i <= getNoOfRows(); i++) {
		String phoneno = table.findElement(By.xpath("//*[@id=\"mainGrid\"]/div[3]/table/tbody/tr[" + i + "]/td[10]")).getText();
		if (phoneno.equals(Phoneno)) {
			flag = true;
			System.out.println("Searched Customer successfully ");
		}}}
		catch(Exception e) {
			System.out.println("Error while searching customer by Phone no:" +e.getMessage());
		}
	
	return flag;
}


public void SearchedRow() throws InterruptedException {
	
	
	for (int i = 1; i <= getNoOfRows(); i++) {
		Actions act =new Actions(LaunchDriver.getDriver());
		WebElement row = table.findElement(By.xpath("//*[@id=\"mainGrid\"]/div[3]/table/tbody/tr[" + i + "]/td[contains(text(),'Phoneno')]"));
		act.doubleClick(row);
		commonActions.clickElement(row);
		}
	Thread.sleep(3000);
	
}


public void RequiredRowInTable() throws InterruptedException
{
	Actions act =new Actions(LaunchDriver.getDriver());
	act.doubleClick(RowOfSelectedPhoneNoInTable);
	Thread.sleep(3000);
}

 
public void clickSaveBtn() {
	commonActions.clickElement(SaveBtn);

}

public void clickSearchBtn() {
	commonActions.clickElement(SearchBtn);

}
}



	
