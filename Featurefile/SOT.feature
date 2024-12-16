Feature: Shield Of Trust

  Scenario: User tries to verify Shield of trust functionality
    Given launch browser and enter url
    When user enters a valid username
    And user enter a valid password
    And clicks on Send OTP
    And user enters a valid OTP
    And clicks on login
    When User clicks on Service Icon
    And User clicks on Extended Warranty Sub Menu
    And User clicks on Hyundai Shield of Trust Package Register link
    Then User should be able to navigate to Hyundai Shield ot Trust Package Register Screen
    When User enter VIN number in SOT
    And User clicks on Inquire in SOT
    And User enter Current Odometer reading in SOT
    And User select Employee Name in SOT
    And User select Place Of Supply in SOT
    And User select required SOT Scheme type in SOT
    Then User Clicks on clear button in SOT
#When User clicks on Submit in SOT
#Then SOT will generate 
