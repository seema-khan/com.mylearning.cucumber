package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public WebDriver ldriver;

	public LoginPage(WebDriver rdriver) {
		this.ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(id = "Email")
	WebElement txtEmail;

	@FindBy(id = "Password")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@value='Log in']")
	WebElement btnLogin;

	@FindBy(linkText = "Logout")
	WebElement lnkLogout;

	public void setUserName(String name) {
		txtEmail.clear();
		txtEmail.sendKeys(name);
	}

	public void setPassword(String pwd) {
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
	}

	public void clickLogin() {
		btnLogin.click();

	}

	public void clickLogout() {
		lnkLogout.click();

	}
}
