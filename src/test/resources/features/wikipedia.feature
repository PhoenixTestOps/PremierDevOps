@regression @issue:SJI-12
Feature: Wikipedia searching
As a user
I want to use Wikipedia
So that I find information on topics of interest

	Scenario Outline: Results are returned from Wikipedia when a search term is provided
		Given the Wikipedia site is accessed
		When I search for lists '<Item>'
		Then I check results
		
		Examples:
		| Item		|
		| Computing	|
		| Apple		|