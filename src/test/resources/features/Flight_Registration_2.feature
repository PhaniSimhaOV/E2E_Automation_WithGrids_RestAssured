Feature: Navigate to Ultimate QA Site 2

  @SmokeTest
  Scenario: Verify Customer Registration Feature
    Given I am on the Flight Registration site url
    Then Enter first Name and Last Name
    And Enter Email address and password
    Then Enter address of the customer
    And click on Register