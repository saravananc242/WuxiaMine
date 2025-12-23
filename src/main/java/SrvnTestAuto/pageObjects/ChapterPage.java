package SrvnTestAuto.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ChapterPage extends BasePage{

	WebDriver driver;
	public ChapterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}
	
	@FindBy(css = ".chapter-content p")
	List<WebElement> chaptercontent;
	
	@FindBy(xpath = "//textarea[@aria-label='comment']")
	WebElement commentField;
	
	@FindBy(xpath = "//span[text()='Submit']/..")
	WebElement commentSubmitButton;
	
	@FindBy(xpath = "//button[text()='NEXT CHAPTER']")
	WebElement nextChapterButton;
	
	@FindBy(css = "h4 span")
	WebElement chapterTitleText;
	
	By chapterContentListBy = By.cssSelector(".chapter-content p");
	By commentFieldBy = By.xpath("//textarea[@aria-label='comment']");
	By commentSubmitButtonBy = By.xpath("//span[text()='Submit']/..");
	By nextChapterButtonBy = By.xpath("//button[text()='NEXT CHAPTER']");	
	By chapterTitleTextBy = By.cssSelector("h4 span");
	
	public void addComments(String comment) {
		sendKeysInElementAfterRefresh(commentField, comment);
		System.out.println("Comment is typed inside field");
		clickOnElementAfterRefresh(commentSubmitButton);
		waitForElementToBeClickable(waitForElementToBeRefreshed(commentSubmitButton));
		System.out.println("submit is ready for click");
	}
	
	public String getChapterTitle() {
		clickOnElement(chapterTitleText);
		return chapterTitleText.getText();
	}
	
	public void moveToNextChapter(String name) {
		clickOnElementAfterRefresh(nextChapterButton);
		System.out.println("Next chapter is clicked");
		waitForElementToBeClickable(waitForElementToBeRefreshed(nextChapterButton));
	}

}
