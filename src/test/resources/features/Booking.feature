Feature: User tries to register Customer after Enquiry Stage

  Scenario: User tries to navigate to the Customer Booking Mananagement List Screen
    Given launch browser and enter url
    When user enters a valid username
    And user enter a valid password
    And clicks on Send OTP
    And user enters a valid OTP
    And clicks on login
    When User clicks Sales Icon on the left
    And User clicks on the  Sales Operation Sub Menu Item
    And User clicks on the  Customer Booking Mgt List link
    Then User should be able to navigate to the Booking Mgt List screen

  Scenario: User tries to click save without entering any data in Customer Booking Mananagement List Screen
    When User click save button in  Customer Booking Mananagement List Screen
    Then User get toast msg when clicked save button in Customer Booking Mananagement List Screen

  Scenario: User tries to navigate to Customer Booking Management Screen
    When User should be able to navigate Customer Booking Management Screen
    When User set Date Of option
    And User set Based On option
    And Enter mobile no. which is already taken in enquiry stage
    And Click on Search
    Then Lead should display in table
    When User double click on that lead

  Scenario: User tries to click on Modify button in Customer Booking Management Screen
    When User tries to click on Modify button
    Then User should be able to see modify pop up screen asking for confirmation
    Then User close modify pop screen

  Scenario: User tries to click on Enquiry cancellation button in Customer Booking Management Screen
    When User tries to click on Enquiry cancellation button
    Then User should be able to see Sales Customer Enquiry Info
    Then User close Sales Customer Enquiry Info

  Scenario: User tries to register without adding mandatory field Customer Booking Management Screen
    When User tries to click on Register Button
    Then Alert info window appear and dont allow to register

  Scenario: User tries to register by adding all mandatory field
    #BasicInfo
    And User start entering valid Basic info and checks SMS option
    And User select a valid Enquiry Type
    And User select a valid Enquiry Category
    And User select a valid Sales Consultant
    And User Enter a valid Referred by
    And User select a valid Finance req
    And User select a valid fuel type
    And User select a valid Variant
    And User select a valid sub Variant
    And User select a valid Ext Color
    And User select a valid Int Color
    #Bookinginfo
    And User select a valid booking date
    And User enter a valid DSA Name
    And User enter a valid approved loan amount
    #Reginfo
    And User enter a valid Registration name
    And User select a valid cust income
    And User select a valid title
    And User enter a valid PAN
    And User enter a valid Address in regInfo
    And User Set a Pin by filtering based on PIN in PIN Code Search
    And User enter a valid village
    And User select a valid W/O S/O D/O
    When User click on Register Button
    Then Pop Message " appear asking for confirmation"
    When Click confirm
    Then Booking No should generate
