Feature: User tries to verify the Signin functionality


  Scenario: DMS_Login_User tries to verify the DMS navigation
    Given launch browser and enter url
    Then user navigate to login page

  @test
  Scenario: DMS_Login_User tries to click on login button without entering username
    Given launch browser and enter url
    Given user is on login page
    When clicks on login
    Then user able to see validation message for UserID

  Scenario: DMS_Login_User tries to click on login button without entering username & password
    When clicks on login
    Then user able to see validation message for username

  Scenario: DMS_Login_User tries to click on login button without entering password
    When user enters username
    And user enter username without entering password
    And clicks on login
    Then user able to see validation message for password

  Scenario: DMS_Login_User tries to click on login button without entering OTP
    When user enter username and password without entering OTP
    And clicks on login
    Then user able to see validation message for OTP

  Scenario: DMS_Login_User tries to enter invalid username and valid password
    When user enters invalid username
    And user enters valid password
    And clicks on Send OTP
    Then user should be able to see validation message

  Scenario: DMS_Login_User tries to enter valid username and invalid password
    When user enters a valid username
    And user enters a invalid password
    And clicks on Send OTP
    Then user should be able to see validation message

  Scenario: DMS_Login_User tries to enter valid username and valid password and Invalid OTP
    When user enters a valid username
    And user enters a invalid password
    And clicks on Send OTP
    And user enters a invalid OTP
    And clicks on login
    Then user should be able to see validation message

  Scenario: DMS_Login_User tries to enter valid username and invalid password and valid OTP
    When user enters a valid username
    And user enters a invalid password
    And clicks on Send OTP
    And user enters a valid OTP
    And clicks on login
    Then user should be able to see validation message

  Scenario: DMS_Login_User tries to verify with invalid credentials
    When user enters invalid username which is not registered
    And user enter invalid password
    And clicks on Send OTP
    Then user should be able to see validation message

  Scenario: DMS_Login_User tries to verify with valid credentials
    When user enter a valid username
    And user enter a valid password
    And clicks on Send OTP
    And user enters a valid OTP
    And clicks on login
    Then user should be able to navigate to the dashboard screen
