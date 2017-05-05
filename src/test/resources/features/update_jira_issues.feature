@jira
@component:rest
Feature: Update JIRA
As a Product Owner
I want to be able to update issues
So that I can manage the project backlog

	Scenario Outline: PO is able to CREATE a story
		Given I am logged into JIRA as '<User>'
		And I have permission to 'CREATE_ISSUE'
		When I create a new user story
		Then I receive the response code '<Response Code>'
		
		Examples:
		| User	| Response Code	|
		| admin	| 201			|
		
		
	Scenario Outline: PO is able to ASSIGN a story
		Given I am logged into JIRA as '<User>'
		And I have permission to 'CREATE_ISSUE'
		And I have permission to 'ASSIGN_ISSUE'
		When I create a new user story
		And I assign the story to '<Assignee>'
		Then I receive the response code '<Response Code>'
		
		Examples:
		| User	| Response Code	| Assignee	|
		| admin	| 204			| admin		|	


	Scenario Outline: PO is able to DELETE a story
		Given I am logged into JIRA as '<User>'
		And I have permission to 'CREATE_ISSUE'
		And I have permission to 'DELETE_ISSUE'
		When I create a new user story
		And I delete the new user story
		Then I receive the response code '204'
		
		Examples:
		| User	| Response Code	|
		| admin	| 204			|
		

	Scenario Outline: PO is able to TRANSITION the state of a story
		Given I am logged into JIRA as '<User>'
		And I have permission to 'CREATE_ISSUE'
		And I have permission to 'CLOSE_ISSUE'
		When I create a new user story
		And I request to transition the new story to '<State>'
		Then I receive the response code '204'
		
		Examples:
		| User	| State			| User	|
		| admin	| IN PROGRESS	| admin	|
		| admin	| DONE			| admin	|


	Scenario Outline: PO is able to UPDATE a story
		Given I am logged into JIRA as '<User>'
		And I have permission to 'CREATE_ISSUE'
		And I have permission to 'EDIT_ISSUE'
		When I create a new user story
		And I request to update the new story with details from '<UpdateStory>'
		Then I receive the response code '204'
		
		Examples:
		| User	| UpdateStory		|
		| admin	| update-issue.json	|
		