Feature: User tries to verify the New Enquiry functionality through Web Application

  @web @test @WebEnquiry
  Scenario: User tries to verify the New Enquiry functionality through Web Application
    Given launch browser and enter url
    When user enters a valid username
    And user enter a valid password
    And clicks on Send OTP
    And user enters a valid OTP
    And clicks on login
    And User reads data from the Excel sheet regarding New Enquiry(Web) feature
    And User processes the New Enquiry(Web) for all rows from the Excel sheet of sheet Name Enquiry Lead Creation