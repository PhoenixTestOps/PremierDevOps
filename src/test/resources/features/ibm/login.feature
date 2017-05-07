Feature: Login Page User story:
 As a user
 I want to be able to login
 To the system home page and enter my credentials

 Scenario Outline: Search Page
  Given I open the system home page
   When I provide the username "<username>"
   And I provide the password "<password>"
   Then I can access the SearchPage "<searchpageurl>"
   Examples:
     | username | password | searchpageurl |
     | steve | steve | searchpageurl |

 Scenario Outline: Invalid Username
   Given I open the system home page
   When I provide the username "<invalidusername>"
   And I provide the password "<password>"
   Then I receive an error message "<errormessage>"
   Examples:
     | invalidusername | password | errormessage |
     | invalidusername | steve | errormessage |


  Scenario Outline: Invalid Password
   Given I open the system home page
   When I provide the username "<username>"
   And I provide the password "<invalidpassword>"
   Then I receive an error message "<errormessage>"
    Examples:
      | username | invalidpassword | errormessage |
      | steve | invalidpassword | errormessage |