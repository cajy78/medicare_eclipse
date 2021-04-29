package testcases;

import org.openqa.selenium.WebDriver;

import properties.SeleniumProperties;
import properties.TestEnums.BrowserType;
import properties.TestingProperties;

public class TestCase {

	public WebDriver driver;

	public void initiateDriver() throws Throwable {

		switch (TestingProperties.getDesignatedBrowser()) {
		case "chrome":
			driver = SeleniumProperties.getBrowser(BrowserType.CHROME);
			break;
		case "firefox":
			driver = SeleniumProperties.getBrowser(BrowserType.FIREFOX);
			break;
		default:
			throw new RuntimeException("Browser type entered in properties does not match the allowed pattern");
		}
	}

	public void reinitiateDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void exit() {
		driver.quit();
	}
}