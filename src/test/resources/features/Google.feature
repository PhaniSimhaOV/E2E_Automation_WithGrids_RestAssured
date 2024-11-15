Feature: Launch Google and Search for Whatever


  @SmokeTest
  Scenario: Verify you are able to search whatever on google site
    Given I am on the Google site
    Then in the search field enter Whatever
    And click on Search Button