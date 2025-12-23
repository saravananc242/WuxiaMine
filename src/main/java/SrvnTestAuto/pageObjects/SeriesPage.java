package SrvnTestAuto.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SeriesPage extends BasePage {

	WebDriver driver;

	public SeriesPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}

	@FindBy(xpath = "//span[text()='Completed']")
	WebElement completedFilter;
	
	@FindBy(xpath = "//span[text()='Chapters']")
	WebElement ChaptersFilter;

	By ListOfNovelsBy = By.cssSelector("div.infinite-scroll-component__outerdiv div.justify-start>a:nth-child(1)>div");
	@FindBy(css = "div.infinite-scroll-component__outerdiv div.justify-start>a:nth-child(1)>div")
	List<WebElement> listOfCompletedNovelsInPage;

	public void validateUserInSeriesPage() {
		waitForElementToBeClickable(completedFilter);
	}

	public void selectCompletedFilter() {
		clickOnElement(completedFilter);
	}
	
	public void selectChaptersFilter() {
		clickOnElement(ChaptersFilter);
	}
	
	public void waitForNovelsToBeListed() {
		waitforAllElementsToBeVisible(ListOfNovelsBy);
	}
	
	public int getCountOfNovelsListed() {
		return listOfCompletedNovelsInPage.size();
	}
	
	public NovelPage selectSeries(int random) {
		clickOnElement(listOfCompletedNovelsInPage.get(random));
		return new NovelPage(driver);
	}

}
