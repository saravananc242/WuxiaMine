package SrvnTestAuto.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import SrvnTestAuto.reusables.ReusableComponents;

public class BasePage extends ReusableComponents {
	WebDriver driver;

	public BasePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}

	@FindBy(css = "[data-cy='header-button-user-anonymous']")
	WebElement userAccountButton;

	@FindBy(css = "[data-cy='header-button-login']")
	WebElement userLoginButton;

	@FindBy(xpath = "//button[@aria-label='VIP']")
	WebElement vipButton;
	
	@FindBy(css = "[aria-label='profile nav']")
	WebElement profileButton;

	@FindBy(css = "div.mt-\\[11px\\] button")
	WebElement karmaByLoginBtn;

	@FindBy(xpath = "//button[text()='COMPLETED']")
	WebElement karmaByLoginConfirmation;

	@FindBy(xpath = "//a[text()='Series']")
	WebElement seriesPageLink;
	
	@FindBy(xpath = "//p[text()='Daily rewards']")
	WebElement dailyRewardsPopupTitle;
	
	@FindBy(xpath = "//button[text()='Continue Reading']")
	WebElement continueReadingButton;

	public String getTextOfKarmaByLoginBtn() {
		return waitForElementToBeRefreshed(karmaByLoginBtn).getText();
	}
	public void mineKarmaByLogin() {
//		clickOnElement(vipButton);
//		clickOnElement(profileButton);
//		clickOnElement(vipButton);
//		if (waitForElementToBeRefreshed(karmaByLoginBtn).isEnabled()) {
//			clickOnElement(karmaByLoginBtn);
//		}
//		waitForElementToBeRefreshed(karmaByLoginConfirmation);
		
		if (waitForElementToBeVisible(dailyRewardsPopupTitle).isDisplayed()) {
			clickOnElement(continueReadingButton);
			
			waitForElementToBeInvisible(continueReadingButton);
		}
	}

	public SeriesPage navigateToSeriesPage() {
		clickOnElement(seriesPageLink);
		return new SeriesPage(driver);
	}

}
