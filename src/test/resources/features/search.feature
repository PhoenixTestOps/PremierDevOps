@component:ui
@version:Release-2
Feature: Search by keyword
  As a retailer
  I want customers to be able to search for products using keywords
  So that they can find what they are looking for more efficiently


  Scenario: Search for products by keyword
    Given I want to buy a cotton shirt
    When I search for 'cotton'
    Then I should see only products related to 'cotton'


  Scenario Outline: Search for various products by keyword
    Given I would like to buy <article>
    When I search for '<article>'
    Then I should see only products related to '<keyword>'
  Examples:
    | article         | keyword  |
    | cashmere jumper | cashmere |
    | khakhi trousers | khakh    |
