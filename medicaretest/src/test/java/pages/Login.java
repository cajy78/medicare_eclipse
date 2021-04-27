package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import properties.TestingProperties;

public class Login extends Pages {

	private WebDriver driver;

	@FindBy(how = How.ID, using = "username")
	private WebElement userName;

	@FindBy(how = How.ID, using = "password")
	private WebElement pwd;

	@FindBy(how = How.XPATH, using = "//input[@type=\"submit\"]")
	private WebElement loginButton;

	@FindBy(how = How.XPATH, using = "/html/body/div/div[1]/div/div[1]/div/div") // "/html/body/div/div[1]/div/div[1]/div/div"
	private WebElement loginErrorMsg;

	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(
				new AjaxElementLocatorFactory(driver, Integer.parseInt(TestingProperties.getLoadAndWaitTimeout())),
				this);
	}

	public WebElement getUsernameElement() {
		return userName;
	}

	public WebElement getPwdElement() {
		return pwd;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public WebElement getLoginErrorMsg() {
		return loginErrorMsg;
	}

	public void enterLoginCredentials(String username, String password) {
		// waitForVisibleElement(driver, userName);
		userName.sendKeys(username);
		// waitForVisibleElement(driver, pwd);
		pwd.sendKeys(password);
	}
}
