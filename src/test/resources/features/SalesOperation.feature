Feature: Sales Operation 



Scenario: Successful Searching of customer in Customer Booking Mgt List
Given User Launch Chrome Browser
When User Opens URL "https://ndms.hmil.net"
Then User should see Login Page
And User enter UserId as "S523700"
And User enter Password as "Hyundai@2024"
And User click Login
Then Home page should be displayed
When User click on Sales Menu Icon
And User click on Sales Operation
And User click on Customer Booking Mgt List link
Then "Customer Booking Mgt List" Page should display
When User click on Date Of as "Enquiry" 
And User Click on Based On as "Mobile No."
And Enter mobile no.as "7489954647"
And Click on Search 
Then Details of Customer should display in table


Scenario: 
 
