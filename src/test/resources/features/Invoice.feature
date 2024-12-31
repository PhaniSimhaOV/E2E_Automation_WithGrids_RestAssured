Feature: User tries to verify the Invoice functionality

@test
  @invoice
  Scenario: User tries to verify the Invoice functionality
    Given launch browser and enter url
    When user enters a valid username
    And user enter a valid password
    #And clicks on Send OTP
    #And user enters a valid OTP
    And clicks on login
    Then user should be able to see Home Icon on the dashboard
    And User reads data from the Excel sheet regarding Invoice feature
    And User processes all rows from the Excel sheet of sheet Name Invoice Leads
    