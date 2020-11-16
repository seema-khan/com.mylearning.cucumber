package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {
	@Before
	public void setup() throws IOException {
		prop = new Properties();
		FileInputStream file = new FileInputStream("config.properties");
		prop.load(file);

		logger = Logger.getLogger("nopCommerce");
		PropertyConfigurator.configure("log4j.properties");

		String browser = prop.getProperty("browser");
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", prop.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", prop.getProperty("iepath"));
			driver = new InternetExplorerDriver();
		}
		logger.info("Launching Browser");
	}

	@Given("User launch chrome browser")
	public void user_launch_chrome_browser() {
		lp = new LoginPage(driver);
	}

	@When("user opens url {string}")
	public void user_opens_url(String url) {
		logger.info("Launching URL");
		driver.manage().window().maximize();
		driver.get(url);

	}

	@When("user enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("Providing username and password");
		lp.setUserName(email);
		lp.setPassword(password);
	}

	@When("click on login")
	public void click_on_login() {
		logger.info("Login started");
		lp.clickLogin();
	}

	@Then("Page title should be {string}")
	public void page_title_should_be(String title) {
		if (driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			logger.info("Login successful");
			Assert.assertTrue(false);
		} else
			logger.info("Login failed");
		Assert.assertEquals(title, driver.getTitle());
	}

	@When("user click on log out link")
	public void user_click_on_log_out_link() throws InterruptedException {
		logger.info("Logout");
		lp.clickLogout();
		Thread.sleep(3000);
	}

	@Then("close browser")
	public void close_browser() {
		logger.info("Closing Browser");
		driver.quit();
	}

	// Customers.Feature
	@Then("User can view dashboard")
	public void user_can_view_dashboard() {
		addCust = new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(2000);
		addCust.clickOnCustomersMenu();
	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(2000);
		addCust.clickOnCustomersMenuItem();
	}

	@When("click on Add new Button")
	public void click_on_add_new_button() throws InterruptedException {
		addCust.clickOnAddnew();
		Thread.sleep(2000);
	}

	@Then("User can view add new Customer Page")
	public void user_can_view_add_new_customer_page() {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		logger.info("Adding new Customer");
		String email = randomString() + "@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		addCust.setFirstName("Pavan");
		addCust.setLastName("Kumar");
		addCust.setGender("Male");
		addCust.setDob("07/05/1985");
		addCust.setCompanyName("busyQA");
		// registered-default. the customer cannot be in both guests and registered
		// customer
		addCust.setCustomerRoles("Guest");
		// Thread.sleep(1000);
		addCust.setManagerOfVendor("Vendor 2");
		addCust.setAdminContent("This is for testing........");
	}

	@When("click on Save Button")
	public void click_on_save_button() throws InterruptedException {
		logger.info("Saving Customer data");
		addCust.clickOnSave();
		Thread.sleep(2000);
	}

	@Then("User can view Confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));
	}

//Steps for searching a customer using email
	@When("Enter Customer Email")
	public void enter_customer_email() {
		logger.info("Searching Customer by using Email");
		searchCust = new SearchCustomerPage(driver);
		searchCust.setEmail("victoria_victoria@nopCommerce.com");
	}

	@When("click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickSearch();
		Thread.sleep(3000);
	}

	@Then("user should find email in the search table")
	public void user_should_find_email_in_the_search_table() {
		boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
	}

	// Scenario search customer by Name
	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		logger.info("Searching Customer by using Name");
		searchCust = new SearchCustomerPage(driver);
		searchCust.setFirstName("Victoria");
	}

	@When("Enter customer LastName")
	public void enter_customer_last_name() {
		searchCust.setLastName("Terces");
	}

	@Then("User should find Name in the search table")
	public void user_should_find_name_in_the_search_table() {
		boolean status = searchCust.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);
	}
}
