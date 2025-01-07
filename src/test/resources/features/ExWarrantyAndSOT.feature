Feature: Extended Warranty Punching and SOT

  @ExtendedWarranty
  Scenario: User tries to verify Extended Warranty Submit functionality
   Given launch browser and enter url
    When user enters a valid username
    And user enter a valid password
    And clicks on Send OTP
    And user enters a valid OTP
    And clicks on login
    And User reads data from the Excel sheet regarding ExWarranty feature
    And User processes the ExWarranty for all rows from the Excel sheet of sheet Name ExWarranty Leads for Exwarranty

  @SOT
  Scenario: User tries to verify SOT Submit functionality
    Given launch browser and enter url
    When user enters a valid username
    And user enter a valid password
    And clicks on Send OTP
    And user enters a valid OTP
    And clicks on login
    And User reads data from the Excel sheet regarding SOT feature
    And User processes the SOT for all rows from the Excel sheet of sheet Name SOT Leads for SOT
