Feature: Enquiry

  @CreateNewLead
  Scenario: Launch the HSmart Application and Create a New Lead

    Given I am on the HSmart Application
    Then enter the user credentials
    And click on Login
    Then Create the Lead and get the Enquiry No in the Excel
      | Excel             | Location                         | Sheet |
      | EnquiryDB_dump.xlsx | C:\\Users\\mdsai\\eclipse-workspace\\testPlace\\E2E_MobileAutomation\\EnquiryDB_dump.xlsx | Enquiry  |

  @CompleteTestDrive
  Scenario: Launch the HSmart Application and Complete Test Drive

    Given I am on the HSmart Application
    Then enter the user credentials
    And click on Login
    Then Complete the Test Drive
      | Excel             | Location                         | Sheet |
      | EnquiryDB_dump.xlsx | C:\\Users\\mdsai\\eclipse-workspace\\testPlace\\E2E_MobileAutomation\\EnquiryDB_dump.xlsx | Enquiry  |