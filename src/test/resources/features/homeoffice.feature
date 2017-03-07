@regression
Feature: Homeoffice navigation
As a student
I want to navigate to the Government student finance page
So that I can enter my credentials
	Scenario: Credentials are entered when the student finance page loads
		Given I open Homeoffice
		When I look for 'finance'
		When I select link 'student finance login'
		When I select 'start now'
		When I fill in username 'saif'
		Then I fill in password 'is the best'