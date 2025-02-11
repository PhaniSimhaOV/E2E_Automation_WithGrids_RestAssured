Feature: User tries to verify the Invoice functionality

  @Invoice
  Scenario: User tries to verify the Invoice functionality
    Given launch browser and enter url
    When user enters a valid username
    And user enter a valid password
    And clicks on Send OTP
    And reads OTP from the database
    And enters the OTP into the OTP field
    And clicks on login
    Then user should be able to see Home Icon on the dashboard
    And User reads data from the Excel sheet regarding Invoice feature
    And User processes the Invoice for all rows from the Excel sheet of sheet Name Invoice Leads
