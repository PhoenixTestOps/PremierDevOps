@component:ui
@version:Release-1
@version:Sprint-1.1
Feature: Add an item to the shopping basket
  As a customer
  I want to be able to purchase items online
  So that they can be delivered to me

  Scenario: Add item to cart
    Given I have searched for 'cotton'
    When I add item 4 to the basket
    Then the item should appear in the basket
    