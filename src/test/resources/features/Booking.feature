Feature: User navigates to booking section

  @testBooking
  #Background: Functionality of booking
  @Booking
  Scenario: To validate the Booking section in the Sales Operation
    Given launch browser and enter url
    When user enters a valid username
    And user enter a valid password
    #And clicks on Send OTP
    #And user enters a valid OTP
    And clicks on login
    And User reads data from the Excel sheet regarding Booking Feature
    And User processes the Booking for all rows from the Excel sheet from the sheet Name Booking Leads



#    Given User clicks on the Sales icon
#    Then User selects the Sales Operation tab
#    When User selects Customer Booking Mgt List under sales Operation
#    Then User need to select the enquiry option in the dropdown
#    And User enters the mobile number in the text box
#    And User selects the mobile number option from the dropdown
#    And User passed the start date and end date in the page
#    Then User clicks on the search button
#    When The enquiry will be populated then user as to select it
#    Then User fills the fields in the Customer Booking MGT
#    Then user clicks on "<button>" based on the value
#    And After successful registration user clicks on Quotation
#    Then User clicks on the receipt icon
#
#    Given launch browser and enter url
#    When user enters a valid username for account
#    And user enter a valid password for account
#    #And clicks on Send OTP
#    And clicks on login
#    Given User clicks on the Sales icon
#    Then User selects the Sales Operation tab
#    When User selects Customer Booking Mgt List under sales Operation in accounts
#    Then User need to select the enquiry option in the dropdown
#    And User enters the mobile number in the text box
#    And User selects the mobile number option from the dropdown
#    And User passed the start date and end date in the page
#    Then User clicks on the search button
#    When The enquiry will be populated then user as to select it
#    Then User clicks on the receipt icon for account
#    And User as to add the amount in the receipt section
#    Then User clicks on Send Customer consent link
#
#    Given launch browser and enter url
#    When user enters a valid username
#    And user enter a valid password
#    #And clicks on Send OTP
#    #And user enters a valid OTP
#    And clicks on login
#    Given User clicks on the Sales icon
#    Then User clicks on Order and stock
#    Then user selects Dealer Vechile Stock MGT
#    And User passes the VIN number into the field
#    And Searches for the Vin number
#    When Verify the data in the table with the customer booking values
#    Then verifies the value from the customer link
#
#    Examples:
#      | button |
#      | modify |









