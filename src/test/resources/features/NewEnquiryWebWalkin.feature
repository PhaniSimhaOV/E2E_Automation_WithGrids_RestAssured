Feature: User tries to verify the New Enquiry functionality through Web Application

  @WebWalkinEnquiry
  Scenario: User tries to verify the New Enquiry functionality through Web Application
    Given launch browser and enter url
    When user enters a valid username
    And user enter a valid password
    And clicks on Send OTP
    And reads OTP from the database
    And enters the OTP into the OTP field
    And clicks on login
    And User reads data from the Excel sheet regarding New Enquiry Walkin(Web) feature
    And User processes the New Enquiry Walkin(Web) for all rows from the Excel sheet of sheet Name Enquiry Lead Creation
    And user tries to fetch and Print the Enquiry Id In Excel Sheet
    And update the gdms_stage and enquiry number columns data in the database based on the result