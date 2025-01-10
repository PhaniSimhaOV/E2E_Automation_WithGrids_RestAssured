package com.autogrid.stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.autogrid.steps.DMSLoginPage;
import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import java.util.Scanner;


public class DMSLoginStepDefinition {
	CommonActions commonActions;
	
	DMSLoginPage dMSLoginPage;
	String testCase;
    public DMSLoginStepDefinition(){
		WebDriver driver = LaunchDriver.getDriver();
		this.dMSLoginPage = new DMSLoginPage(driver);
		PageFactory.initElements(driver, dMSLoginPage);
		this.testCase = = System.getProperty("testCase", "defaultTestCase");
		System.out.println("Login page is displayed."+ testCase);
    }
	
	@Given("launch browser and enter url")
	public void launch_browser_and_enter_url() throws Throwable {
		try {
			dMSLoginPage.launchDMSSite();
		} catch (Exception e) {
			throw new Exception("Error occurred while Launching DMS application : " + e.getMessage());
		}
	}

	@Then("user navigate to login page")
	public void user_navigate_to_login_page() throws Throwable {
		try {
			Thread.sleep(2000);
            String expectedTitle = "GDMS2.0";
            String actualTitle = dMSLoginPage.getPageTitle();
            Assert.assertEquals(actualTitle,expectedTitle,"Login page title does not match!");
            System.out.println("Login page is displayed.");
        } catch (Exception e) {
			throw new Exception("Error occurred while navigating to DMS login page : " + e.getMessage());
        }       
    }

	@Given("user is on login page")
	public void user_is_on_login_page() throws Exception {
		try {
			Thread.sleep(2000);
            String expectedTitle = "GDMS2.0";
            String actualTitle = dMSLoginPage.getPageTitle();
            Assert.assertEquals(actualTitle,expectedTitle, "Login page title does not match!");
            System.out.println("Login page is displayed.");
		} catch (Exception e) {
			throw new Exception("Error occurred while validating the DMS login page header :" + e.getMessage());
		}
	}
	
	@Then("user should be able to see forgot password link on login screen")
    public void user_should_be_able_to_see_forgot_password_link_on_login_screen() {
        try {
            Assert.assertTrue(dMSLoginPage.isForgotPasswordLinkDisplayed(), "forgot password link is not displayed on the Login screen.");
            System.out.println("forgot password link is displayed on the Login screen.");
        } catch (Exception e) {
            System.err.println("Error verifying forgot password link: " + e.getMessage());
            Assert.fail("forgot password link verification failed.");
        }
    }
	
	@And("user clicks on forgot password link")
	public void user_clicks_on_forgot_password_link() {
		try {
			dMSLoginPage.ClicksForgotPasswordLink();
            System.out.println("forgot password link clicked.");
        } catch (Exception e) {
            System.err.println("Error during forgot password link click: " + e.getMessage());
        }
    }
	
	@Then("user should be able to see Forgot Password Popup")
    public void user_should_be_able_to_see_forgot_password_popup() {
		try {
            Assert.assertTrue(dMSLoginPage.isForgotPasswordPopupDisplayed(), "Forgot Password pop-up is not displayed.");
            System.out.println("Forgot Password pop-up is displayed.");
        } catch (Exception e) {
            System.err.println("Error verifying Forgot Password pop-up: " + e.getMessage());
            Assert.fail("Forgot Password pop-up verification failed.");
        }
    }
	
	@And("user checks the Save ID checkbox")
	public void user_checks_the_save_id_checkbox() {
		try {
			dMSLoginPage.checkSaveIDCheckbox();
            System.out.println("Checked the Save ID checkbox successfully.");
        } catch (Exception e) {
            System.err.println("Error checking the Save ID checkbox: " + e.getMessage());
            Assert.fail("Unable to check Save ID checkbox.");
        }
    }
	
	@Then("the Save ID checkbox should be checked")
    public void the_save_id_checkbox_should_be_checked() {
		try {
            Assert.assertTrue(dMSLoginPage.isSaveIDCheckboxChecked(), "Save ID checkbox is not checked.");
            System.out.println("Save ID checkbox is verified to be checked.");
        } catch (Exception e) {
            System.err.println("Error verifying Save ID checkbox: " + e.getMessage());
            Assert.fail("Save ID checkbox verification failed.");
        }
    }
	
	@Then("user should be able to see Save ID Check Box on login screen")
    public void user_should_be_able_to_see_save_id_check_box_on_login_screen() {
        try {
            Assert.assertTrue(dMSLoginPage.isSaveIDCheckBoxDisplayed(), "save ID check box is not displayed on the Login screen.");
            System.out.println("save ID check box is displayed on the Login screen.");
        } catch (Exception e) {
            System.err.println("Error verifying save ID check box: " + e.getMessage());
            Assert.fail("save ID check box verification failed.");
        }
    }

	@When("clicks on login")
	public void clicks_on_login() {
		try {
			Thread.sleep(2000);
			dMSLoginPage.clickLoginButton();
            System.out.println("Login button clicked.");
        } catch (Exception e) {
            System.err.println("Error during login button click: " + e.getMessage());
        }
    }

	@Then("user able to see validation message for UserID")
	public void user_able_to_see_validation_message_for_userid() throws Exception {
		try {
			Thread.sleep(2000);
            String expectedMessage = "Please input User ID.";
            String actualMessage = dMSLoginPage.getUsernameValidationMessage();
            Assert.assertEquals(actualMessage, expectedMessage, "Validation message does not match!");
            System.out.println("Validation message for username displayed successfully.");
		} catch (Exception e) {
			throw new Exception("Error occurred while validating the validation message for UserID :" + e.getMessage());
		}
	}

	@When("user enters username")
	public void user_enters_username() {
		try {
			Thread.sleep(2000);
            String username = "S523700";
            dMSLoginPage.enterUsername(username);
            System.out.println("Entered username: " + username);
        } catch (Exception e) {
            System.err.println("Error during entering username: " + e.getMessage());
        }
	}
	
	@When("user enter username without entering password")
	public void user_enter_username_without_entering_password(){
		try {
			Thread.sleep(2000);
            String username = "S523700";
            dMSLoginPage.enterUsername(username);
            System.out.println("Entered username: " + username);
        } catch (Exception e) {
            System.err.println("Error during entering username: " + e.getMessage());
        }
	}

	@Then("user able to see validation message for password")
	public void user_able_to_see_validation_message_for_password() throws Exception {
		try {
			Thread.sleep(3000);
            String expectedMessage = "Please input Password.";
            String actualMessage = dMSLoginPage.getPasswordValidationMessage();
            Assert.assertEquals(actualMessage, expectedMessage, "Validation message does not match!");
            System.out.println("Validation message for password displayed successfully.");
		} catch (Exception e) {
			throw new Exception("Error occurred while validating the validation message for Password :" + e.getMessage());
		}
	}

	@When("user enter username and password without entering OTP")
	public void user_enter_username_and_password_without_entering_otp() {
		try {
			Thread.sleep(3000);
            String username = "S523700";
            dMSLoginPage.enterUsername(username);
            System.out.println("Entered username: " + username);
            String password = "S523700";
            dMSLoginPage.enterPassword(password);
            System.out.println("Entered password: " + password);
        } catch (Exception e) {
            System.err.println("Error during entering username and password: " + e.getMessage());
        }
	}
	
	@Then("user able to see validation message for OTP")
	public void user_able_to_see_validation_message_for_otp() throws Exception {
		try {
            String expectedMessage = "Enter OTP value.";
            String actualMessage = dMSLoginPage.getEnterOTPValidationMessage();
            Assert.assertEquals(actualMessage, expectedMessage, "Validation message does not match!");
            System.out.println("Validation message To Enter OTP displayed successfully.");
		} catch (Exception e) {
			throw new Exception("Error occurred while validating the validation message To enter OTP :" + e.getMessage());
		}
	}

	@When("user enters invalid username")
	public void user_enters_invalid_username() {
		try {
            String username = "S5";
            dMSLoginPage.enterUsername(username);
            System.out.println("Entered username: " + username);
        } catch (Exception e) {
            System.err.println("Error during entering username: " + e.getMessage());
        }
	}

	@When("user enters valid password")
	public void user_enters_valid_password() {
		try {
			String password = "Hyundai@2024";
            dMSLoginPage.enterPassword(password);
            System.out.println("Entered password: " + password);
        } catch (Exception e) {
            System.err.println("Error during entering username: " + e.getMessage());
        }
	}

	@When("clicks on Send OTP")
	public void clicks_on_send_otp() {
		try {
			dMSLoginPage.clickSendOTPButton();
            System.out.println("Send OTP button clicked.");
        } catch (Exception e) {
            System.err.println("Error during Send OTP button click: " + e.getMessage());
        }
    }

	@Then("user able to see validation message for username")
	public void user_able_to_see_validation_message_for_username() throws Exception {
		try {
            String expectedMessage = "Please input User ID.";
            String actualMessage = dMSLoginPage.getUsernameValidationMessage();
            Assert.assertEquals(actualMessage, expectedMessage, "Validation message does not match!");
            System.out.println("Validation message for username displayed successfully.");
		} catch (Exception e) {
			throw new Exception("Error occurred while validating the validation message for UserID :" + e.getMessage());
		}
	}

	@When("user enters a valid username")
	public void user_enters_a_valid_username() {
		try {
            String username = "S523700";
            dMSLoginPage.enterUsername(username);
            System.out.println("Entered username: " + username);
        } catch (Exception e) {
            System.err.println("Error during entering username: " + e.getMessage());
        }
	}

	@And("user enters a invalid password")
	public void user_enters_a_invalid_password(){
		try {
			String password = "S523700";
            dMSLoginPage.enterPassword(password);
            System.out.println("Entered password: " + password);
        } catch (Exception e) {
            System.err.println("Error during entering username: " + e.getMessage());
        }
	}

	@Then("user should be able to see validation message for password")
	public void user_should_be_able_to_see_validation_message_for_password() throws Exception {
		try {
            String expectedMessage = "Please input Password.";
            String actualMessage = dMSLoginPage.getPasswordValidationMessage();
            Assert.assertEquals(actualMessage, expectedMessage, "Validation message does not match!");
            System.out.println("Validation message for password displayed successfully.");
		} catch (Exception e) {
			throw new Exception("Error occurred while validating the validation message for Password :" + e.getMessage());
		}
	}

	@When("user enters a invalid OTP")
	public void user_enters_a_invalid_otp() {
		try {
			Thread.sleep(10000);
			String otp = "S523700";
            dMSLoginPage.enterOTP(otp);
            System.out.println("Entered otp: " + otp);
        } catch (Exception e) {
            System.err.println("Error during entering otp: " + e.getMessage());
        }
	}

	@Then("user should be able to see validation message for OTP")
	public void user_should_be_able_to_see_validation_message_for_otp() throws Exception {
		try {
            String expectedMessage = "Enter OTP value.";
            String actualMessage = dMSLoginPage.getEnterOTPValidationMessage();
            Assert.assertEquals(actualMessage, expectedMessage, "Validation message does not match!");
            System.out.println("Validation message To Enter OTP displayed successfully.");
		} catch (Exception e) {
			throw new Exception("Error occurred while validating the validation message To enter OTP :" + e.getMessage());
		}
	}

	@When("user enters a valid OTP")
	public void user_enters_a_valid_otp() {
		try {
	            Thread.sleep(3000); // Temporary sleep to allow the pop-up to load; replace with WebDriverWait for better reliability
	            System.out.println("Enter the OTP received (displayed on the pop-up): ");
		        Scanner scanner = new Scanner(System.in);
		        String otp = scanner.nextLine();

		        // Step 4: Enter OTP in the pop-up and submit
		        dMSLoginPage.enterOTP(otp);
	            System.out.println("Entered otp: " + otp);    
		} catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	}

	@When("user enters invalid username which is not registered")
	public void user_enters_invalid_username_which_is_not_registered() {
		try {
            String username = "S5";
            dMSLoginPage.enterUsername(username);
            System.out.println("Entered username: " + username);
        } catch (Exception e) {
            System.err.println("Error during entering username: " + e.getMessage());
        }
	}

	@When("user enter invalid password")
	public void user_enter_invalid_password() {
		try {
			String password = "S523700";
            dMSLoginPage.enterPassword(password);
            System.out.println("Entered password: " + password);
        } catch (Exception e) {
            System.err.println("Error during entering username: " + e.getMessage());
        }
	}
	
	@Then("user should be able to see validation message as Please Check User ID or Password")
	public void user_should_be_able_to_see_validation_message_as_please_check_user_id_or_password() throws Exception {
		try {
            String expectedMessage = "Please Check User ID or Password.";
            String actualMessage = dMSLoginPage.getUsernameValidationMessage();
            Assert.assertEquals(actualMessage, expectedMessage, "Validation message does not match!");
            System.out.println("Validation message displayed successfully.");
		} catch (Exception e) {
			throw new Exception("Error occurred while validating the validation message:" + e.getMessage());
		}
	}

	@When("user enter a valid username")
	public void user_enter_a_valid_username() {
		try {
            String username = "S523700";
            dMSLoginPage.enterUsername(username);
            System.out.println("Entered username: " + username);
        } catch (Exception e) {
            System.err.println("Error during entering username: " + e.getMessage());
        }
	}
	
	@Then("user should be able to see validation message as Incorrect OTP Or Time Exceeded")
	public void user_should_be_able_to_see_validation_message_as_incorrect_otp_or_time_exceeded() throws Exception {
		try {
            String expectedMessage = "Incorrect OTP Or Time Exceeded.";
            String actualMessage = dMSLoginPage.getUsernameValidationMessage();
            Assert.assertEquals(actualMessage, expectedMessage, "Validation message does not match!");
            System.out.println("Validation message displayed successfully.");
		} catch (Exception e) {
			throw new Exception("Error occurred while validating the validation message:" + e.getMessage());
		}
	}

	@When("user enter a valid password")
	public void user_enter_a_valid_password() {
		try {
			String password = "Hyundai@2024";
            dMSLoginPage.enterPassword(password);
            System.out.println("Entered password: " + password);
        } catch (Exception e) {
            System.err.println("Error during entering username: " + e.getMessage());
        }
	}

	@Then("user should be able to see Home Icon on the dashboard")
    public void user_should_be_able_to_see_home_icon_on_dashboard() {
        try {
            Assert.assertTrue(dMSLoginPage.isHomepageIconDisplayed(), "Home Icon is not displayed on the dashboard.");
            System.out.println("Home Icon is displayed on the dashboard.");
        } catch (Exception e) {
            System.err.println("Error verifying Home Icon: " + e.getMessage());
            Assert.fail("Home Icon verification failed.");
        }
    }
	
	@When("user try to close the google chrome browser")
	public void user_try_to_close_the_google_chrome_browser() throws Throwable {
	try {
		LaunchDriver.tearDown();
	} catch (Exception e) {
		throw new Exception("Error occurred while Closing the browser : " + e.getMessage());
	}
}	

}
