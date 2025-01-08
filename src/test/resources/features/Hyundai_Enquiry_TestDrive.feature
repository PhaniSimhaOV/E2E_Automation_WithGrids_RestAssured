@mobile
Feature: Enquiry Test Drive

  @StartTestDriveAndAddFollowUp
  Scenario: Launch the HSmart Application and Complete Test Drive

    Then enter the user credentials
    And click on Login
    Then Create Test Drive and add followup


  @CompleteTestDrive
  Scenario: Launch the HSmart Application and Complete Test Drive

    Then enter the user credentials
    And click on Login
    Then Complete Test Drive