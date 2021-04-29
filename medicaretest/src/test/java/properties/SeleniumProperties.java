package properties;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import properties.TestEnums.BrowserType;

public class SeleniumProperties {
	private static WebDriver driver;

	public static WebDriver getBrowser(BrowserType browserType) throws Throwable {

		if (TestingProperties.remoteDriverEnabled()) {
			switch (browserType) {
			case CHROME:
				return getRemoteChromeBrowser();
			case FIREFOX:
				return getRemoteFirefoxBrowser();
			default:
				throw new RuntimeException("Incorrect browser type selected");
			}
		} else {
			switch (browserType) {
			case CHROME:
				return getLocalChromeBrowser();
			case FIREFOX:
				return getLocalFirefoxBrowser();
			default:
				throw new RuntimeException("Incorrect browser type selected");
			}
		}
	}

	private static WebDriver getLocalChromeBrowser() {
		System.setProperty("webdriver.chrome.driver", TestingProperties.getWinDriverLocation() + "/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	private static WebDriver getLocalFirefoxBrowser() {
		System.setProperty("webdriver.gecko.driver", TestingProperties.getWinDriverLocation() + "/geckodriver.exe");
		driver = new FirefoxDriver();
		//driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Long.parseLong(TestingProperties.getLoadAndWaitTimeout()), TimeUnit.SECONDS);
		return driver;
	}

	private static WebDriver getRemoteChromeBrowser() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName("chrome");
		cap.setPlatform(Platform.ANY);
		String hubURL = TestingProperties.getSeleniumHubURL();
		driver = new RemoteWebDriver(new URL(hubURL), cap);
		return driver;
	}

	private static WebDriver getRemoteFirefoxBrowser() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName("firefox");
		cap.setPlatform(Platform.ANY);
		String hubURL = TestingProperties.getSeleniumHubURL();
		driver = new RemoteWebDriver(new URL(hubURL), cap);
		return driver;
	}
}
