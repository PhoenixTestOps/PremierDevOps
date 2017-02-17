@all
Feature: Belly and cukes
As a Belly user
I want to know when I am hungry
So that I can eat

	@growl
	Scenario: Do not growl when I am full
		Given I have '40' cukes in my belly
		When I wait '1' hour
		Then my belly should 'silent'
	@silent
	Scenario: Growl when I am hungry
		Given I have '20' cukes in my belly
		When I wait '1' hour
		Then my belly should 'growl'