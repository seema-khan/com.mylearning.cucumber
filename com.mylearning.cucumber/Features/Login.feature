Feature: Login

@sanity
Scenario: Successful Login with valid Credentials
	Given User launch chrome browser
	When user opens url "http://admin-demo.nopcommerce.com/login"
	And user enters email as "admin@yourstore.com" and password as "admin"
	And click on login
	Then Page title should be "Dashboard / nopCommerce administration"
	When user click on log out link
	Then Page title should be "Your store. Login"
	And close browser
	
@regression
Scenario Outline: Login data Driven
	Given User launch chrome browser
	When user opens url "http://admin-demo.nopcommerce.com/login"
	And user enters email as "<email>" and password as "<password>"
	And click on login
	Then Page title should be "Dashboard / nopCommerce administration"
	When user click on log out link
	Then Page title should be "Your store. Login"
	And close browser
	
	Examples:
	| email | password |
	| admin@yourstore.com | admin |
	| admin1@yourstore.com | admin123 |