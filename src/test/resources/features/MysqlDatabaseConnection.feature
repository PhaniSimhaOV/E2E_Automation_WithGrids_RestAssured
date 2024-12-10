Feature: MySQL Database Connection

  Scenario: Connect to MySQL Workbench Database
    Given User connects to MySQL Workbench database
    When User executes a query to fetch data
    Then User closes the database connection
