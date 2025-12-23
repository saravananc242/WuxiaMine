package SrvnTestAuto.reusables;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import SrvnTestAuto.pageObjects.LoginPage;

public class ReusableComponents {
	WebDriver driver;
	Wait<WebDriver> wait;
	Logger log = LogManager.getLogger(ReusableComponents.class.getName());
	
	public ReusableComponents(WebDriver driver) {
		this.driver=driver;
		wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
	}

	public List<WebElement> waitforAllElementsToBeVisible(By findBy) {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}
	
	public WebElement waitForElementToBeVisible(By findBy) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public WebElement waitForElementToBeInvisible(By findBy) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
		return null;
	}
	
	public WebElement waitForElementToBeVisible(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public WebElement waitForElementToBeInvisible(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
		return element;
	}
	
	public WebElement waitForElementToBeClickable(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public WebElement waitForElementToBeClickable(By findBy) {
		return wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	
	public WebElement waitForElementToBeRefreshed(WebElement element) {
		return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
	}
	
	public void scrollToPageEnd() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
	}
	
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);"+"window.scrollBy(0,-500);", element);
	}
	
	public int getRandomNumber(int start, int end) {
		Random random = new Random();
		return random.ints(start, end).findFirst().getAsInt();
	}
	
	public boolean clickOnElement(WebElement element) {
		boolean result = false;
		try {
			scrollToElement(element);
			waitForElementToBeClickable(element);
			element.click();
			log.info("Click on element: "+element+" passed");
			result = true;
		} catch (Exception e) {
			result = false;
			log.error("Click on element: "+element+" failed");
		}
		return result;
	}
	
	public boolean clickOnElementAfterRefresh(WebElement element) {
		boolean result = false;
		try {
			scrollToElement(element);
			waitForElementToBeClickable(waitForElementToBeRefreshed(element));
			element.click();
			log.info("Click on element: "+element+" after refresh passed");
			result = true;
		} catch (Exception e) {
			result = false;
			log.error("Click on element: "+element+" after refresh failed");
		}
		return result;
	}
	
	public boolean sendKeysInElement(WebElement element, String text) {
		boolean result = false;
		try {
			scrollToElement(element);
			waitForElementToBeClickable(element);
			element.clear();
			element.sendKeys(text);
			log.info(text+" is typed in element: "+element+" successfully");
			result = true;
		} catch (Exception e) {
			result = false;
			log.error("Send keys to element: "+element+" failed");
		}
		return result;
		
	}
	
	public boolean sendKeysInElementAfterRefresh(WebElement element, String text) {
		boolean result = false;
		try {
			scrollToElement(element);
			waitForElementToBeClickable(waitForElementToBeRefreshed(element));
			element.clear();
			element.sendKeys(text);
			log.info(text+" is typed in element: "+element+" after refresh successfully");
			result = true;
		} catch (Exception e) {
			result = false;
			log.error("Send keys to element: "+element+" after refresh failed");
		}
		return result;
		
	}
	
	public boolean doesElementExist(WebElement element) {
		boolean result = false;
		try {
			element.isDisplayed();
			result = true;
			log.info(element+" exists in page");
		} catch (Exception e) {
			result = false;
			log.error(element+" does not exist in page");
		}
		return result;
	}

}
;