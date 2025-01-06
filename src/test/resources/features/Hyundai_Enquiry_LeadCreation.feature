@mobile
Feature: Enquiry - Lead Creation

  @CreateNewLead
  Scenario: Launch the HSmart Application and Create a New Lead

    Then enter the user credentials
    And click on Login
    Then Create the Lead and get the Enquiry No in the Excel
      | Excel        | Location       | Sheet    |
      | output.xlsx| C:\\Users\\mdsai\\IdeaProjects\\test\\E2E_Automation_WithGrids_RestAssured\\output.xlsx | Enquiry Lead Creation  |