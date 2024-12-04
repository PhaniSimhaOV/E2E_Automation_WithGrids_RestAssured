Feature: Shield Of Trust

  Scenario: User tries to verify Shield of trust functionality
    
    Given: User is on Home page of GDMS Web Application

    When User clicks on Service Icon
    And User clicks on Extended Warranty Sub Menu
    And User clicks on Hyundai Shield ot Trust Package Register link
    Then User should be able to navigate to Hyundai Shield ot Trust Package Register Screen
    When User enter VIN number
    And User clicks on Inquire
    Then Scheme types displayed in the table based on Vehicle
    And User enter Current Odometer reading
    And User select Employee Name
    And User select Place Of Supply
    And User select required extented Warranty type
    Then User Clicks on clear button
    
#When User clicks on Submit 
#Then SOT will generate
