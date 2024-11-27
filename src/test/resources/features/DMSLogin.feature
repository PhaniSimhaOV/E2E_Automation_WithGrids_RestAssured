Feature: User tries to verify the Login functionality

  Scenario: DMS_Login_User tries to verify the DMS navigation
    Given launch browser and enter url
    Then user navigate to login page

  @test
  Scenario: DMS_Login_Validate the forgot password link
    Given launch browser and enter url
    And user is on login page
    Then user should be able to see forgot password link on login screen

  Scenario: DMS_Login_User tries to click on forgot password link
    Given launch browser and enter url
    And user is on login page
    And user clicks on forgot password link
    Then user should be able to see Forgot Password Popup

  Scenario: DMS_Login_Validate the Save ID Check Box
    Given launch browser and enter url
    And user is on login page
    Then user should be able to see Save ID Check Box on login screen

  Scenario: DMS_Login_User tries to checks on Save ID Check Box
    Given launch browser and enter url
    And user is on login page
    When user checks the Save ID checkbox
    Then the Save ID checkbox should be checked

  Scenario: DMS_Login_User tries to click on login button without entering username
    Given launch browser and enter url
    And user is on login page
    When clicks on login
    Then user able to see validation message for UserID

  Scenario: DMS_Login_User tries to click on login button without entering username & password
    Given launch browser and enter url
    When clicks on login
    Then user able to see validation message for UserID

  Scenario: DMS_Login_User tries to click on login button without entering password
    Given launch browser and enter url
    When user enters username
    And user enter username without entering password
    And clicks on login
    Then user able to see validation message for password

  Scenario: DMS_Login_User tries to click on login button without entering OTP
    Given launch browser and enter url
    When user enter username and password without entering OTP
    And clicks on login
    Then user able to see validation message for OTP

  Scenario: DMS_Login_User tries to enter invalid username and valid password
    Given launch browser and enter url
    When user enters invalid username
    And user enter a valid password
    And clicks on Send OTP
    Then user should be able to see validation message as Please Check User ID or Password

  Scenario: DMS_Login_User tries to enter valid username and invalid password
    Given launch browser and enter url
    When user enters a valid username
    And user enters a invalid password
    And clicks on Send OTP
    Then user should be able to see validation message as Please Check User ID or Password

  Scenario: DMS_Login_User tries to enter valid username and valid password and Invalid OTP
    Given launch browser and enter url
    When user enters a valid username
    And user enter a valid password
    And clicks on Send OTP
    And user enters a invalid OTP
    And clicks on login
    Then user should be able to see validation message as Incorrect OTP Or Time Exceeded

  Scenario: DMS_Login_User tries to verify with invalid credentials
    Given launch browser and enter url
    When user enters invalid username which is not registered
    And user enter invalid password
    And clicks on Send OTP
    Then user should be able to see validation message as Please Check User ID or Password

  Scenario: DMS_Login_User tries to verify with valid credentials
    Given launch browser and enter url
    When user enters a valid username
    And user enter a valid password
    And clicks on Send OTP
    And user enters a valid OTP
    And clicks on login
    Then user should be able to see Home Icon on the dashboard
