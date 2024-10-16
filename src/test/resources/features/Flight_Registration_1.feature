Feature: Navigate to Ultimate QA Site 1

  @SmokeTest
  Scenario: Verify Book Your Flight site UI
    Given I am on the Flight Registration site url
    Then Verify the presence of the Site Logo
    And Verify site Header is present
    And Verify site description is present