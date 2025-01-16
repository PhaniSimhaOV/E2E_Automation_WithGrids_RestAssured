Feature: User performs test drive for Enqiury
  
  @TestDriveEnquiryWalkIn
   Scenario: To Validate the test drive appointment for the given enquiry
    Given launch browser and enter url
    When user enters a valid username
    And user enter a valid password
    And clicks on Send OTP for booking
   #And user enters a valid OTP
   #And clicks on login
    And User reads data from the Excel sheet regarding TestDrive Appointment
    And User processes the TestDrive appointment for walk-in Enquiry for all rows from the Excel sheet

  @TestDriveEnquiryLead
  Scenario: To Validate the test drive appointment for the given enquiry
    Given launch browser and enter url
    When user enters a valid username
    And user enter a valid password
    And clicks on Send OTP for booking
    #And user enters a valid OTP
    #And clicks on login
    And User reads data from the Excel sheet regarding TestDrive Appointment for leads
    And User processes the TestDrive appointment for Leads Enquiry for all rows from the Excel sheet