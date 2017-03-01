@issue:SJI-11
Feature: Update JIRA
As a JIRA user
I want to update issues
So that I can manage stories

	Scenario Outline: User is able to CREATE a story
		Given I am on the JIRA web page as 'admin'
		And I have permission to 'CREATE_ISSUE'
		When I create a new user story based on '<JSONFile>'
		Then I receive status code '<Response Code>'
		
		Examples:
		| JSONFile		| Response Code	|
		| New178.json	| 201			|
		
		
	Scenario Outline: User is able to ASSIGN a story to another user
		Given I am on the JIRA web page as 'admin'
		And I have permission to 'CREATE_ISSUE'
		And I have permission to 'ASSIGN_ISSUE'
		When I create a new user story based on '<JSONFile>'
		And I assign user '<User>' to the new user story
		Then I receive status code '<Response Code>'
		
		Examples:
		| JSONFile		| User	| Response Code	|
		| New178.json	| admin	| 204			|


	Scenario Outline: User is able to DELETE a story
		Given I am on the JIRA web page as 'admin'
		And I have permission to 'CREATE_ISSUE'
		And I have permission to 'DELETE_ISSUE'
		When I create a new user story based on '<JSONFile>'
		And I delete the new user story
		Then I receive status code '204'
		
		Examples:
		| JSONFile		|
		| New178.json	|


	Scenario Outline: User is able to TRANSITION the state of a story
		Given I am on the JIRA web page as '<User>'
		And I have permission to 'CREATE_ISSUE'
		And I have permission to 'CLOSE_ISSUE'
		When I create a new user story based on '<JSONFile>'
		And I request to transition the new story to '<Transition>'
		Then I receive status code '204'
		
		Examples:
		| JSONFile		| Transition	| User	|
		| New178.json	| In Progress	| admin	|
		| New178.json	| Done			| admin	|


	Scenario Outline: User is able to UPDATE a story
		Given I am on the JIRA web page as 'admin'
		And I have permission to 'CREATE_ISSUE'
		And I have permission to 'EDIT_ISSUE'
		When I create a new user story based on '<JSONFile>'
		And I request to update the new story with details from '<newJSONFile>'
		Then I receive status code '204'
		
		Examples:
		| JSONFile		| newJSONFile	|
		| New178.json	| New179.json	|
