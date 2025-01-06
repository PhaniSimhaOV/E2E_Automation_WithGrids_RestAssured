@web
Feature: User tries to verify the New Enquiry functionality

@test
  Scenario: User tries to verify the New Enquiry functionality
    Given launch browser and enter url
    When user enters a valid username
    And user enter a valid password
    #And clicks on Send OTP
    #And user enters a valid OTP
    And clicks on login
    When User clicks Sales Menu Item
    And User clicks on the Customer Enquiry Sub Menu Item
    And User clicks on the Customer Enquiry link
    Then User should be able to navigate to the Customer Enquiry screen

    When User clicks on New button
    Then User should be able to navigate Sales Customer Enquiry Pop-up
    And User should be able to see the UI with Header as Sales Customer Enquiry
    
    When user is in Sales Customer Enquiry Pop-up
    And User clicks on Close icon
    Then User should be able to navigate back to the Customer Enquiry screen
    
    When User tries to clicks on New button
    And User clicks on Pincode Search Icon from PIN field
    Then User should be able to navigate to the Pincode search Screen
    And User tries to enter Pincode in pincode field
    And User tries to clicks on search button in Pincode search Screen
    And User tries to select one pincode from the list
    And User tries to clicks on Add Selected button in Pincode search Screen
    Then User should be able to see Selected Pincode displayed in the Pincode Field

    When User clicks on Pincode Search Icon beside PIN field
    Then User should be able to navigate to Pincode Search Screen
    And User tries to selects State from State field
    And User tries to selects District from District field
    And User tries to selects Taluka or Tehsil from Taluka or Tehsil field
    And User tries to enters Post Office Name from Post Office Name field
    And User tries to clicks on search button in Pincode search Screen
    And User tries to select one pincode from the list after applying filters
    And User tries to clicks on Add Selected button in Pincode search Screen
    Then User should be able to see the Selected Pincode displayed in the Pincode Field

    When User tries to enters mobile number Which was already Registered
    And User clicks on mobile number search Icon
    Then User should be able to navigate to the Find A Customer Info Screen
    And User tries to clcik on Close icon in the Find A Customer Info Screen

    Given user is in Sales Customer Enquiry Pop-up
    And User tries to enters mobile number Which Not Registered
    And User clicks on mobile number search Icon
    And I enters valid data in Email id
    And I selects valid data in Cust. Type
    And I enters valid data in Cust. Name
    And I enters valid data in Residence Phone No
    And I enters valid data in WhatsApp ID
    And I selects valid data Gender
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
    And User clicks on Pincode Search Icon from PIN field
    Then User should be able to navigate to the Pincode search Screen
    And User tries to enter Pincode in pincode field
    And User tries to clicks on search button in Pincode search Screen
    And User tries to select one pincode from the list
    And User tries to clicks on Add Selected button in Pincode search Screen
    And I click on the Save button
    Then User Should be able to navigate to the Lead Tab in the Sales Customer Enquiry screen

    #Given User is in Lead Tab in the Sales Customer Enquiry screen
    #And User tries to enters the Customer Name in Customer Name filter
    #And User tries to clicks on the search button in Lead in the Sales Customer Enquiry screen
    #And User tries to double click on the Repective Enquiry
    #Then User Should be able to navigate to the Basic Info tab in the Sales Customer Enquiry Info screen
    #And User tries to selects valid data in TD Offer dropdown
    #And User tries to selects valid data in TD VIN dropdown
    #And User tries to clicks on Save button in the Basic Info tab in the Sales Customer Enquiry Info screen
#
    #Given User is in the Test Drive Appointment Screen
    #When User tries to selects A valid Time solt Which was greater than the Current time
    #And User tries to clicks on Save button in the Test Drive Appointment Screen
    #Then User should be able to see a Toast Message as Successfully reflected in Test Drive Appointment Screen
#
    #Given User is in the Test Drive Appointment Screen
    #And User tries to clicks on the Close Icon on the Test Drive Appointment Tab
    #Then User Should be able to navigate to the Basic Info tab in the Sales Customer Enquiry Info screen
    #And User tries to clicks on the Follow Up tab in the Sales Customer Enquiry Info screen
    #Then User Should be able to navigate to the Follow Up tab in the Sales Customer Enquiry Info screen
    #And User tries to selects valid data in Next Follow Up Time
    #And User tries to selects valid data in Next Follow Up Type
    #And User tries to selects valid data in Enquiry Type
    #And User tries to selects valid data in Verification
    #And User tries to enters valid data in Scheme Offered
    #And User tries to enters valid data in Follow Up Remarks
    #And User tries to clicks on Save button in the Follow Up tab in the Sales Customer Enquiry Info screen
    #Then User should be able to see a Toast Message as Successfully reflected in Follow Up tab

    Given user tries to close the google chrome browser
