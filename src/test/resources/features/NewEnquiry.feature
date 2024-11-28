Feature: User tries to verify the Add New Enquiry functionality

  Scenario: User tries to navigate to the Customer Enquiry Screen
    Given launch browser and enter url
    When user enters a valid username
    And user enter a valid password
    And clicks on Send OTP
    And user enters a valid OTP
    And clicks on login
    When User clicks Sales Menu Item
    And User clicks on the Customer Enquiry Sub Menu Item
    And User clicks on the Customer Enquiry link
    Then User should be able to navigate to the Customer Enquiry screen

  Scenario: User tries to clicks on New button in Customer Enquiry screen
    When User clicks on New button
    Then User should be able to navigate Sales Customer Enquiry Pop-up
    And User should be able to see the UI with Header as Sales Customer Enquiry

  Scenario: User tries to click on Close icon in Sales Customer Enquiry Pop-up
    When User clicks on New button
    And User clicks on Close icon
    Then User should be able to navigate to the Customer Enquiry screen

  Scenario: User tries to Download Promotions Excel in Sales Customer Enquiry Pop-up
    When User should be able to view the Cross Mark in Sales Customer Enquiry Pop-up
    And User clicks on the Cross Mark in Sales Customer Enquiry Pop-up
    Then User should be able to Downloaded and navigate to Sales Customer Enquiry Pop-up

  Scenario: User tries to select Pincode from PIN using pincode search in Sales Customer Enquiry Pop-up
    When User clicks on New button
    And User clicks on Pincode Search Icon from PIN field
    Then User should be able to navigate to the Pincode search Screen
    And User tries to enter Pincode in pincode field
    And User tries to clicks on search button in Pincode search Screen
    And User tries to select one pincode from the list
    And User tries to clicks on Add Selected button in Pincode search Screen
    Then User should be able to see Selected Pincode displayed in the Pincode Field

  Scenario: User tries to select Pincode from filters in Sales Customer Enquiry Pop-up
    When User clicks on New button
    And User clicks on Pincode Seach from PIN field
    Then User should be able to navigate to the Pin code search Screen
    And User tries to selects State from state field
    And User tries to selects District from District field
    And User tries to selects Taluka or Tehsil from Taluka or Tehsil field
    And User tries to enters Post Office Name from Post Office Name field
    And User tries to clicks on search button in Pincode search Screen
    And User tries to select one pincode from the list
    And User tries to clicks on Add Selected button in Pincode search Screen
    Then User should be able to see Selected Pincode displayed in the Pincode Field

  Scenario: User tries to click on Save button without entering Mandatory fields
    When User clicks on New button
    And User click on Save button without entering mandatory fields
    Then User should be able to see toast message

  Scenario: User tries to click on Save button by entering mobile number consists of less than nine digits
    When User tries to enters mobile number consists of less than nine digits
    And User clicks on Save button
    Then User should be able to see toast message to enter valid Mobile Number

  Scenario: User tries to click on Save button by entering mobile number Which was already Registered
    When User tries to enters mobile number Which was already Registered
    And User clicks on Save button
    Then User should be able to navigate to the Find A Customer Info Screen

  Scenario: User tries to click on Save button by entering mobile number Which Not Registered
    When User tries to enters mobile number Which Not Registered
    And User clicks on Save button
    Then User should be able to see toast message to Create a new Enquiry

  Scenario: User tries to click on Save button by entering Invalid Email id
    When User tries to enters Invalid Email id
    And User clicks on Save button
    Then User should be able to see toast message to enter the valid email id

  Scenario: Submit a valid enquiry
    Given user is in Sales Customer Enquiry Pop-up
    And I enters valid data in Mobile No
    And I enters valid data in E-mail
    And I selects valid data in Cust. Type
    And I enters valid data in Cust. Name
    And I enters valid data in Residence Phone No.
    And I enters valid data in WhatsApp ID
    And I selects valid data Gender
    And I selects valid data in PIN
    And I enters valid data in Address
    And I enters valid data in Village
    And I selects valid data in Location
    And I selects valid data Enquiry Source
    And I selects valid data Enquiry Sub Source
    And I selects valid data Enquiry Category
    And I selects valid data Person In Charge
    And I selects valid data in Model
    And I selects valid data Fuel Type
    And I selects valid data in Variant
    And I selects valid data in Sub Variant
    And I selects valid data in Ext Color
    And I selects valid data in Int Color
    And I selects valid data in Sales Consultant
    When I click on the Save button
    Then the enquiry is successfully submitted
