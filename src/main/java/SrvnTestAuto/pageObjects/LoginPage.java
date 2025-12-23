package SrvnTestAuto.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage{
	WebDriver driver;
	Logger log = LogManager.getLogger(LoginPage.class.getName());
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}
	
	@FindBy(id = "Username")
	WebElement userNameField;

	@FindBy(id = "Password")
	WebElement userPasswordField;

	@FindBy(xpath = "//button[@name='button']")
	WebElement submitLoginButton;

	By UserName = By.id("Username");
	
	public BasePage login(String userName, String password) {
		clickOnElement(userAccountButton);
		clickOnElement(userLoginButton);
		sendKeysInElement(userNameField, userName);
		sendKeysInElement(userPasswordField, password);
		clickOnElement(submitLoginButton);
		return new BasePage(driver);
	}

}
