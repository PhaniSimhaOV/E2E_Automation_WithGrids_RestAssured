Feature: Extended Warranty Punching

  Scenario: User tries to verify Extended Warranty Submit functionality
    
    Given: User is on Home page of GDMS Web Application

    When User clicks on Service Icon
    And User clicks on Extended Warranty Sub Menu
    And User clicks on Extended Warranty Submit Link
    Then User should be able to navigate to Extended Warranty Submit Screen
    When User enters VIN number
    And User clicks on Inquire
    Then Extended Warranty types displayed in the table based on Vehicle
    And User enter Current Odometer reading
    And User select Employee Name
    And User select Place Of Supply
    And User select required extented Warranty type
    Then User Clicks on clear button
    
#When User clicks on Submit 
#Then Extended Warranty will generate
    
