Feature: User tries to verify the Invoice functionality

@test
  Scenario: User tries to verify the Invoice functionality
    Given launch browser and enter url
    When user enters a valid username
    And user enter a valid password
    #And clicks on Send OTP
    #And user enters a valid OTP
    And clicks on login
    Then user should be able to see Home Icon on the dashboard
    And User clicks Sales Menu Item
    And User clicks on the Sales Operation Sub Menu Item
    And User clicks on the Customer Booking Mgt List link
    Then User should be able to navigate to the Customer Booking Mgt List Screen
    
    When User tries to Selects Mobile Number in the Based On Auto-Suggestion in Customer Booking Mgt List Screen
    And User tries to enters Lead Mobile Number in the Based On Field
    And User tries to clicks on the Search button in Customer Booking Mgt List Screen
    And User tries to select Enquiry from the list after applying filters in Customer Booking Mgt List Screen
    Then User should be able to navigate to the Customer Booking Management Screen
    
    When User tries to clicks on the Invoice Tab in the Customer Booking Management Screen
    And User tries to clicks on Scheme button in the Invoice Tab in the Customer Booking Management Screen
    Then User should be able to navigate to the Scheme Popup screen
    And User tries to enters valid data in the Payable By Dealer Amount in TAX Adjustment Allowed Table
    And User tries to enters valid data in the Adjustment Credit Note Amount in TAX Adjustment Allowed Table
    And User tries to enters valid data in the Basic Insurance Amount in Chargeable/Sharing Table
    And User tries to enters valid data in the RTO Amount in Chargeable/Sharing Table
    And User tries to enters valid data in the Road Tax Amount in Chargeable/Sharing Table
    And User tries to enters valid data in the Other Charges Amount in Chargeable/Sharing Table
    And User tries to enters valid data in the Life Tax Amount in Chargeable/Sharing Table
    And User tries to clicks on Save button in Scheme Popup screen
    Then User should be able to see Do you want to save it? Popup
    And User tries to clicks on Confirm button in Do you want to save it? Popup 
    And User tries to clicks on Close button in Scheme Popup screen
    Then User should be able to navigate to the Customer Booking Management Screen
    
    When User tries to clicks on the More Promotions button in Basic info Section in the Customer Booking Management Screen
    Then User should be able to see Promotions Section in the Customer Booking Management Screen
    And User tries to clicks on the Plus icon in Promotions Section in the Customer Booking Management Screen
    Then User should be able to navigate to the Promotion Pop-up
    And User tries to Checks the All the promotions from promotions table in the Promotion Pop-up
    And User tries to clicks on Add Selected button in the Promotion Pop-up
    When User tries to click on the Modify button in Basic info Section in the Customer Booking Management Screen
    And User should be able to see Do you want to Modify it? Popup
    And User tries to clicks on Confirm button in Do you want to Modify it? Popup 
    
    When User tries to selects valid data in Vehicle usage Type field in customer info Section in the Customer Booking Management Screen 
    And User tries to clicks on the Register button in invoice tab in the Customer Booking Management Screen
    And User should be able to see Do you want to register? Popup
    And User tries to clicks on Confirm button in Do you want to save it? Popup in the Customer Booking Management Screen 
    
    When User tries to click on the Modify button in invoice tab in the Customer Booking Management Screen
    And User should be able to see Do you want to Modify it? Popup in the Customer Booking Management Screen 
    And User tries to clicks on Confirm button in Do you want to Modify it? Popup in the Customer Booking Management Screen 
    
    When User tries to clicks on Invoice Confirm button in invoice tab in the Customer Booking Management Screen
    And User should be able to see Do you want to confirm it? Popup
    And User tries to clicks on Confirm button in Do you want to confirm it? Popup 
   
    Given user tries to close the chrome browser