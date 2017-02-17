@all
Feature: Driving Theory Test Booking
As a UK citizen
I want to book my driving theory test
So that I can then book my driving test

	Background:
		Given I open the Home Office Book Theory Test Page


	Scenario Outline: Applicant successfully navigates to theory test booking page using correct details
		When I input the surname '<Surname>' and I input the forename '<Forename>'
		And I input '<LicenseNumber>' as my license number
		And I input my birthdate '<Day>'/'<Month>'/'<Year>'
		And I select the test '<Test>'
		And I select the language '<Language>'
		And I respond '<SupportResponse>' to needing extra support
		And I enter the postcode '<PostCode>' and select the first center
		Then I reach the '<EndPage>' page
		
		Examples:
		| Surname	| Forename	| LicenseNumber		| Day	| Month	| Year	| Test							| Language	| SupportResponse	| PostCode	| EndPage				|
		| Tucker	| Julia		| TUCKE954114JC9YW	| 11	| April	| 1994	| Motorcycle (Category A/P/AM)	| English	| No				| GL50 2LW	| Choose appointment	|
		
	
	Scenario Outline: Applicant fails to navigate to theory test booking page due to missing information
		When I input the surname '<Surname>' and I input the forename '<Forename>'
		And I input '<LicenseNumber>' as my license number
		And I input my birthdate '<Day>'/'<Month>'/'<Year>'
		Then The error message is '<ErrorMessage>'
		
		Examples:
		| Surname	| Forename	| LicenseNumber		| Day	| Month	| Year	| ErrorMessage				|
		| 			| Julia		| TUCKE954114JC9YW	| 11	| April	| 1994	| Surname					|
		| Tucker	| Julia		| TUCKE954117JC9YW	| 11	| April	| 1994	| Driving licence number	|

