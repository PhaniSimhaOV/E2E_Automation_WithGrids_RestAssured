package com.autogrid.steps;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;

public class SOTPage {
	private static final Logger logger = LoggerFactory.getLogger(SOTPage.class);
    private final CommonActions commonActions;
    
    
    public SOTPage(WebDriver driver){
        this.commonActions = new CommonActions(driver);
        PageFactory.initElements(driver, this);
    }
    
    
    
    @FindBy(xpath="//*[text()='Service']")
    private WebElement ServiceIcon;
    
    @FindBy(xpath="//*[text()='Extended Warranty']")
    private WebElement ExtendedWarrantySubMenu ;
    
    @FindBy(xpath="//a[text()='Hyundai Shield of Trust Package Register']") 
    private WebElement HyundaiShieldOfTrustPackageRegLink;            
    
    @FindBy(xpath="//span[text()='Hyundai Shield of Trust Package Register']") 
    private WebElement HyundaiSOTPackageRegisterHeader;
    
    @FindBy(xpath="//button[@id='btnInquire']")
    private WebElement InquireIcon;
    
    @FindBy(xpath="//button[@id='btnClear']")
    private WebElement ClearBtn;
    
    @FindBy(xpath="//*[@id='frm1']/div[2]/dl[8]/dd[1]/span/span")  
    private WebElement EmployeeNameDrpDwn;
    
   @FindBy(xpath="//*[@id='grid']/div[2]/table/tbody/tr[4]/td[2]")
   private WebElement RequiredSOTScheme;
   
   @FindBy(xpath="//*[@id='frm1']/div[2]/dl[9]/dd[3]/span/span")
   private WebElement PlaceOfSupplyDrpDwn;
    
    @FindBy(xpath="//button[@id='btnSubmit']")
    private WebElement SubmitBtn;
    
    @FindBy(xpath="//input[@class='k-formatted-value form_numeric ar k-input']")
    private WebElement OdometerReading;
    
    @FindBy(xpath="//input[@id='sKeyword']")
    private WebElement VIN;
    
    @FindBy(xpath="//*[@src='/ser/serf/selectHyundaiKeralaRRSchemeRegisterMain.dms']")
    private WebElement SOTIframe;
    
    @FindBy(xpath="//button[text()='close']")
    private WebElement close;
   
    public void clickServiceIcon()
    {
    try {
		ServiceIcon.click();
		
	} catch (Exception e) {
		System.err.println("Failed to click Service Icon"+e.getMessage());
	}	
    }
	
public void clickExtWarrantySubMenu() {
	try {
		commonActions.explicitWait("//*[text()='Extended Warranty']");
		ExtendedWarrantySubMenu.click();
	} catch (Exception e) {
		System.err.println("Failed to click Extended Warranty Sub Menu"+e.getMessage());
	}
}
    
    public void  clickHyundaiShieldOfTrustPackageRegisterLink() {
    	try {
    		//commonActions.explicitWait("(//a[text()='Hyundai Shield of Trust Package Register'])");
    		//HyundaiShieldOfTrustPackageRegLink.click();
    		
    		JavascriptExecutor js = (JavascriptExecutor) LaunchDriver.getDriver();
                 js.executeScript("arguments[0].click();", HyundaiShieldOfTrustPackageRegLink);
            
		} catch (Exception e) {
			System.err.println("Failed to click HyundaiShieldOfTrustPackageRegLink "+e.getMessage());
		}
		
	}
  //method to check if Hyundai SOT Package Register Header is displayed
    public boolean HyundaiSOTPackageRegisterHeaderisDisplayed()
    {
    try {
    	return HyundaiSOTPackageRegisterHeader.isDisplayed();
    	
    } catch (Exception e) {
    	
    	return false;
    }
    }
    
    public void interactWithIframeSOT() {
    	
    	try {
       		LaunchDriver.getDriver().switchTo().frame(SOTIframe);
    		System.out.println("Successfully interacted with iFrame of SOTPage");
    	} catch (Exception e) {

    System.err.println("Error in interacting with iFrame of ExWarranty"+e.getMessage());
    	}


    }
    
    public void enterVIN(String vin)
    {
    	try {
    		commonActions.explicitWait("//input[@id='sKeyword']");
    		VIN.clear();
    		VIN.sendKeys(vin);
    	} catch (Exception e) {
    	System.err.println("Error in entering VIN "+e.getMessage());	
    	}
    }
    	
    	
    	public void clickOnInquire()
    	{
    		try {
    			InquireIcon.click();
    			
    		} catch (Exception e) {
    		System.err.println("Error in clicking Inquire icon" +e.getMessage());
    		}
    	}
    	
    	public void enterCurrentOdoMtrReading( String odoMtrReading)
    	{
    		try {
    			commonActions.explicitWait("//input[@class='k-formatted-value form_numeric ar k-input']");
    			if (OdometerReading.isDisplayed()) {

    				  JavascriptExecutor js = (JavascriptExecutor) LaunchDriver.getDriver();
    		          js.executeScript("arguments[0].value='" + odoMtrReading + "';", OdometerReading);
    			} else {
    			    System.out.println(OdometerReading+"Element is not visible");
    			}
    		} catch (Exception e) {
    		System.err.println("Error in entering OdomtrReading"+e.getMessage());
    		}
    	}
    	
    	public void selectEmployeeName(String employeeName) {
    		EmployeeNameDrpDwn.click();
    		commonActions.explicitWait("//ul[@id='hssdEmpNo_listbox']//li");
    		
    		 WebDriverWait wait = new WebDriverWait(LaunchDriver.getDriver(), Duration.ofSeconds(10));
             List<WebElement> employeeList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='hssdEmpNo_listbox']//li")));
    		try {
    			for (WebElement empName : employeeList) {
    	        	 if (empName.getText().equals(employeeName)) {
    	        		 empName.click();
    	                 break;
    	             }
    			}
    			
    			//JavascriptExecutor js = (JavascriptExecutor) LaunchDriver.getDriver();
    	        //js.executeScript("arguments[0].value='" + employeeName + "';", EmployeeNameDrpDwn);
    		} catch (Exception e) {
    			 System.err.println("Error in selecting employee Name"+e.getMessage());
    		}
    	}
    	 public void selectPlaceOfSupply(String placeOfSupply) {
    		 PlaceOfSupplyDrpDwn.click();
 			commonActions.explicitWait("//ul[@id='placeOfSupply_listbox']//li");
 			
 			 WebDriverWait wait = new WebDriverWait(LaunchDriver.getDriver(), Duration.ofSeconds(10));
 	         List<WebElement> SupplyStates = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='placeOfSupply_listbox']//li")));
 			try {
 				for (WebElement stateName : SupplyStates) {
 		        	 if (stateName.getText().equals(placeOfSupply)) {
 		        		 stateName.click();
 		                 break;
 		             }
 				}
 				
 				//JavascriptExecutor js = (JavascriptExecutor) LaunchDriver.getDriver();
 		        //js.executeScript("arguments[0].value='" + placeOfSupply + "';", PlaceOfSupplyDrpDwn);
 				
 			} catch (Exception e) {
 				 System.err.println("Error in selecting State Name"+e.getMessage());
 			}
    	 }
       public void setRequiredSOTSchemeType() {
    	   try {
    		RequiredSOTScheme.click();
    	} catch (Exception e) {
         System.err.println("Error in selecting Required Extd warranty Type" +e.getMessage());
    	}
       }

       public void clickSubmitBtn()
       {
    	   try {
    		SubmitBtn.click();
    	} catch (Exception e) {
    	System.err.println("Error in clicking Submit Button"+e.getMessage());
    	}
       }
       
       public void clickClearBtn() {
    		try {
    			ClearBtn.click();
    		} catch (Exception e) {
    			System.err.println("Error in clicking clear button" +e.getMessage());
    		}
    	}

       public void closeSOTTab() {
   		try {
   			Thread.sleep(2000);
   			LaunchDriver.getDriver().switchTo().defaultContent();
   			close.click();
   		} catch (Exception e) {
   			System.err.println("Error in clicking close button" +e.getMessage());
   		}
   	}
}
