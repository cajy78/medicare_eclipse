package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import properties.TestingProperties;

public class Pages {

	public static void waitForLoadingSymbolCompletion(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(TestingProperties.getLoadAndWaitTimeout()));
//		while(loading.isEnabled())
//		{
//			System.out.println("Loading is enabled");
//		}
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitForClickableElement(WebDriver driver, By by) {
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(TestingProperties.getLoadAndWaitTimeout()));
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public void waitForVisibleElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(TestingProperties.getLoadAndWaitTimeout()));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForPresenceOfElement(WebDriver driver, By by) {
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(TestingProperties.getLoadAndWaitTimeout()));
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public static WebElement dynamicElementLocator(WebDriver driver, By by) {
		return driver.findElement(by);
	}

	public static void waitForPageLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	public void waitForInvisbilityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(TestingProperties.getLoadAndWaitTimeout()));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
}
