Feature: Search by Gender - Mule API
As a user
I want to do a team search by Gender
So that I can view gender records

  Scenario Outline: User is able to search by gender
    Given I am on the mule web page as user
    When I submit a search request based on "<jsonfile>"
    Then I receive status code "<responsecode>"
    Examples:
      | jsonfile | responsecode |
	  | gender-search_1.json | 200 |