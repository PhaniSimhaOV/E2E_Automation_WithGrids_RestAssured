Feature: Navigate to Ultimate QA Site 1


  @SmokeTest
  Scenario: Verify Book Your Flight site UI
    Given I am on the Flight Registration site url
    Then Verify the presence of the Site Logo
    And Verify site Header is present
    And Verify site description is present

  @BrokenTest
  Scenario: Verify Customer Registration Feature
    Given I am on the Flight Registration site url
    Then Enter first Name and Last Name
    And Enter Email address and password
    Then Enter address of the customer
    And click on Register