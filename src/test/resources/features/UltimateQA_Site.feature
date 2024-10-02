Feature: Navigate to Ultimate QA Site

  @SmokeTest
Scenario Outline: Navigate to Ultimate QA Site and verify the presence of Logo
  Given I am on the Ultimate QA site "<url>"
  Then Verify the presence of the Site Logo
    Examples:
      | url                               |  |
      | https://ultimateqa.com/automation |  |