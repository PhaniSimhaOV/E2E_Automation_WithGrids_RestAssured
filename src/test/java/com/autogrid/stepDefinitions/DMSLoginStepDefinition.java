package com.autogrid.stepDefinitions;

import com.autogrid.steps.GoogleSitePage;
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

public class DMSLoginStepDefinition {
	CommonActions commonActions;
	
	DMSLoginPage dMSLoginPage;

    public DMSLoginStepDefinition(){
		WebDriver driver = LaunchDriver.getDriver();
		this.dMSLoginPage = new DMSLoginPage(driver);
		PageFactory.initElements(driver, dMSLoginPage);
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
            String expectedTitle = "GDMS2.0";
            String actualTitle = dMSLoginPage.getPageTitle();
            Assert.assertEquals(actualTitle,expectedTitle, "Login page title does not match!");
            System.out.println("Login page is displayed.");
		} catch (Exception e) {
			throw new Exception("Error occurred while validating the DMS login page header :" + e.getMessage());
		}
	}

	@When("clicks on login")
	public void clicks_on_login() {
		try {
            // Action to click the login button
			dMSLoginPage.clickLoginButton();
            System.out.println("Login button clicked.");
        } catch (Exception e) {
            System.err.println("Error during login button click: " + e.getMessage());
        }
    }

	@Then("user able to see validation message for UserID")
	public void user_able_to_see_validation_message_for_userid() throws Exception {
		try {
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
			String password = "S523700";
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

	@Then("user should be able to see validation message for username")
	public void user_should_be_able_to_see_validation_message_for_username() throws Exception {
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
            String username = "S5";
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
			Thread.sleep(5000);
			String otp = "S523700";
            dMSLoginPage.enterOTP(otp);
            System.out.println("Entered otp: " + otp);
        } catch (Exception e) {
            System.err.println("Error during entering otp: " + e.getMessage());
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
	
	@Then("user able to see validation message")
	public void user_able_to_see_validation_message() throws Exception {
		try {
            String expectedMessage = " Please Check User ID or Password.";
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
            String username = "S5";
            dMSLoginPage.enterUsername(username);
            System.out.println("Entered username: " + username);
        } catch (Exception e) {
            System.err.println("Error during entering username: " + e.getMessage());
        }
	}

	@When("user enter a valid password")
	public void user_enter_a_valid_password() {
		try {
			String password = "S523700";
            dMSLoginPage.enterPassword(password);
            System.out.println("Entered password: " + password);
        } catch (Exception e) {
            System.err.println("Error during entering username: " + e.getMessage());
        }
	}

	@Then("user should be able to navigate to the dashboard screen")
	public void user_should_be_able_to_navigate_to_the_dashboard_screen() throws Exception {
		try {
            String expectedTitle = "dashboard";
            String actualTitle = dMSLoginPage.getPageTitle();
            Assert.assertEquals("dashboard page title does not match!",actualTitle,expectedTitle);
            System.out.println("dashboard page is displayed.");
        } catch (Exception e) {
			throw new Exception("Error occurred while navigating to dashboard : " + e.getMessage());
        }       
    }

}
