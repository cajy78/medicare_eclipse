package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import properties.TestingProperties;

public class Home extends Pages {
	private WebDriver driver;

	@FindBy(how = How.LINK_TEXT, using = "View Products")
	private WebElement viewProducts;

	@FindBy(how = How.ID, using = "login")
	private WebElement loginLink;

	@FindBy(how = How.ID, using = "signup")
	private WebElement signupLink;
	
	@FindBy(how = How.ID, using = "manageProduct")
	private WebElement manageProdLink;

	public Home(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(
				new AjaxElementLocatorFactory(driver, Integer.parseInt(TestingProperties.getLoadAndWaitTimeout())),
				this);
	}

	public void viewProductsList() {
		waitForClickableElement(driver, By.linkText("View Products"));
		viewProducts.click();
	}

	public void loginLinkClick() {
		waitForClickableElement(driver, By.linkText("Login"));
		loginLink.click();
	}

	public void signUpLinkClick() {
		waitForClickableElement(driver, By.linkText("Sign Up"));
		signupLink.click();
	}
	
	public WebElement getManageProdLink() {
		return manageProdLink;
	}
}
