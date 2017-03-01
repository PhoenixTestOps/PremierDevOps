@issue:SJI-8
Feature: Driving License Application
As a UK citizen
I want to apply for my provisional driving license
So that I can begin learning to drive	
		
	Scenario Outline: Application is accepted when correct details are provided
		Given I open the Home Office Driving License Application Page
		When I select the title '<Title>'
		When I input the surname '<Surname>' and then I input the forename '<Forename>'
		And I select the gender '<Gender>'
		And I input my date of birth '<Day>'/'<Month>'/'<Year>'
		And I select the country '<Country>'
		And I submit my details
		And I enter my House Number '<HouseNumber>' and Postcode '<PostCode>'
		And I enter how long I have lived there '<YearsLived>' years and '<MonthsLived>' months
		Then I then reach the '<EndPage>' page
		
		Examples:
		| Title	| Forename	| Surname	| Gender	| Day	| Month	| Year	| Country			| HouseNumber	| PostCode	| YearsLived	| MonthsLived	| EndPage			|
		| Miss	| Julia		| Tucker	| Female	| 11	| Apr	| 1994	| United Kingdom	| 19			| GL50 2LW	| 6				| 2				| Security details	|


	Scenario Outline: Application fails when when incomplete input is provided
		Given I open the Home Office Driving License Application Page
		When I select the title '<Title>'
		When I input the surname '<Surname>' and then I input the forename '<Forename>'
		And I select the gender '<Gender>'
		And I input my date of birth '<Day>'/'<Month>'/'<Year>'
		And I select the country '<Country>'
		And I submit my details		
		Then I get a hazard message
		
		Examples:
		| Title	| Forename	| Surname	| Gender	| Day	| Month	| Year	| Country			|
		| 		| Julia		| Tucker	| Female	| 11	| Apr	| 1994	| United Kingdom	|
		| Miss	|			| Tucker	| Female	| 11	| Apr	| 1994	| United Kingdom	|
		
	
	Scenario Outline: Application fails when applicant is underage
		Given I open the Home Office Driving License Application Page
		When I select the title '<Title>'
		When I input the surname '<Surname>' and then I input the forename '<Forename>'
		And I select the gender '<Gender>'
		And I input my date of birth '<Day>'/'<Month>'/'<Year>'
		And I select the country '<Country>'
		And I submit my details		
		Then My application ends because I am too young
		
		Examples:
		| Title	| Forename	| Surname	| Gender	| Day	| Month	| Year	| Country			|
		| Miss	| Julia		| Tucker	| Female	| 11	| Apr	| 2004	| United Kingdom	|