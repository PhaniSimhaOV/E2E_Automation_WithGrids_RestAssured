Feature: Connecting and Fetching Data From MySQL Database

  Scenario: Fetching data from the database and Save the fetched data into EXCEL File
    Given User connects to MySQL Workbench database
    When User executes a query to fetch data
    And User tries to save the fetched data into the file
    Then User closes the database connection
