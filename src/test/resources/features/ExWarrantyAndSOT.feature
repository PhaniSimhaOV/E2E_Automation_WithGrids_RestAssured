Feature: Extended Warranty Punching and SOT

  @test
  Scenario: User tries to verify Extended Warranty and SOT Submit functionality
    Given launch browser and enter url
    When user enters a valid username
    And user enter a valid password
    # And clicks on Send OTP
    #And user enters a valid OTP
    And clicks on login
    And User reads data from the Excel sheet regarding ExWarranty and SOT feature
    And User processes the ExWarranty and SOT for all rows from the Excel sheet of sheet Name ExWarranty & SOT Leads
