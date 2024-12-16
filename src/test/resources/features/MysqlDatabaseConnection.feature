Feature: MySQL Database Connection

  Scenario: Fetching data from the database
    Given User connects to MySQL Workbench database
    When User executes a query to fetch data
    Then User closes the database connection