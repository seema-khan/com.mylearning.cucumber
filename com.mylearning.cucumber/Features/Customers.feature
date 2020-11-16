Feature: Customers
Background: Below are the common steps for each scenario
	Given User launch chrome browser
	When user opens url "http://admin-demo.nopcommerce.com/login"
	And user enters email as "admin@yourstore.com" and password as "admin"
	And click on login
	Then User can view dashboard

@sanity
Scenario: Add a new Customer
	When User click on customers Menu
	And click on customers Menu Item
	And click on Add new Button
	Then User can view add new Customer Page
	When User enter customer info
	And click on Save Button
	Then User can view Confirmation message "The new customer has been added successfully"
	And close browser

@regression
Scenario: Search Customer by emailID
	When User click on customers Menu
	And click on customers Menu Item
	And Enter Customer Email
	When click on search button
	Then user should find email in the search table
	And close browser

@regression
Scenario: Search Customer by name
	When User click on customers Menu
	And click on customers Menu Item
	And Enter customer FirstName
	And Enter customer LastName
	When click on search button
	Then User should find Name in the search table
	And close browser