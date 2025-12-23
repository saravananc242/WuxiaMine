package SrvnTestAuto.pageObjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class NovelPage extends BasePage{

	WebDriver driver;
	Logger log = LogManager.getLogger(LoginPage.class.getName());
	public NovelPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}
	
	@FindBy(xpath = "//div[text()='START READING']")
	WebElement StartReadingButton;
	
	@FindBy(xpath = "//span[text()='Chapters']")
	WebElement chaptersTab;
	
	@FindBy(xpath = "//button//div[text()='Newest']")
	WebElement filterWithValueNewest;
	
	@FindBy(xpath = "//button//div[text()='Oldest']")
	WebElement filterWithValueOldest;
	
	@FindBy(xpath = "//div[text()='Oldest']")
	WebElement oldestOption;
	
	@FindBy(xpath = "//span[text()='About']")
	WebElement aboutTab;
	
	@FindBy(xpath = "//div[text()='CONTINUE READING']/..")
	WebElement continueReadingButton;
	
	By ChapterSectionsListBy = By.cssSelector("div section.items-center");
	
	By chaptersListBy = By.cssSelector(".MuiAccordion-region a");
	
	By lockedChapterBy = By.cssSelector(" svg");
	
	private void validateUserInNovel() {
		waitForElementToBeClickable(aboutTab);
	}
	
	public void moveToChaptersTab() {
		validateUserInNovel();
		clickOnElement(chaptersTab);
		waitforAllElementsToBeVisible(ChapterSectionsListBy);
	}
	
	private void filterByOldest() {
		clickOnElement(filterWithValueNewest);
		clickOnElement(oldestOption);
		waitForElementToBeClickable(filterWithValueOldest);
	}
	
	public void selectOldestSection() {
		filterByOldest();
		clickOnElement(waitforAllElementsToBeVisible(ChapterSectionsListBy).get(0));
	}
	
	public boolean isKarmaRequiredForChapter(WebElement element, int index) {
		boolean result = false;
		try {
			element.findElement(lockedChapterBy).isDisplayed();
			result = true;
			log.info("Karma is required for chapter 20");
		} catch (Exception e) {
			result = false;
			log.info("Karma is not required for chapter 20");
		}
		return result;
	}
	
	public ChapterPage selectChapterByIndex(int index) {
		List<WebElement> chapters = waitforAllElementsToBeVisible(chaptersListBy);
		if (chapters.size()>20 && !isKarmaRequiredForChapter(chapters.get(20),index)) {
			clickOnElement(chapters.get(index));
		} else if (chapters.size()>5) {
			if (doesElementExist(StartReadingButton)) {
				clickOnElement(StartReadingButton);
			} else {
				clickOnElement(continueReadingButton);
			}
			
			
		}
		
		return new ChapterPage(driver);
	}
	

}
