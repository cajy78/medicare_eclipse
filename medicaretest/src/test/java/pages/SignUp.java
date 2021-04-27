package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import properties.TestingProperties;

public class SignUp extends Pages {

	private WebDriver driver;

	@FindBy(how = How.ID_OR_NAME, using = "firstName")
	private WebElement firstNameTF;

	@FindBy(how = How.ID_OR_NAME, using = "lastName")
	private WebElement lastNameTF;

	@FindBy(how = How.ID_OR_NAME, using = "email")
	private WebElement emailTF;

	@FindBy(how = How.ID_OR_NAME, using = "contactNumber")
	private WebElement contactNumberTF;

	@FindBy(how = How.ID_OR_NAME, using = "password")
	private WebElement passwordTF;

	@FindBy(how = How.ID_OR_NAME, using = "confirmPassword")
	private WebElement confirmPasswordTF;

	@FindBy(how = How.NAME, using = "role")
	private WebElement userRoleRadio;

	@FindBy(how = How.NAME, using = "_eventId_billing")
	private WebElement submitPageOne;

	// Page Two
	@FindBy(how = How.ID_OR_NAME, using = "addressLineOne")
	private WebElement addressLineOneTF;

	@FindBy(how = How.ID_OR_NAME, using = "addressLineTwo")
	private WebElement addressLineTwoTF;

	@FindBy(how = How.ID_OR_NAME, using = "city")
	private WebElement cityTF;

	@FindBy(how = How.ID_OR_NAME, using = "postalCode")
	private WebElement postalCodeTF;

	@FindBy(how = How.ID_OR_NAME, using = "state")
	private WebElement stateTF;

	@FindBy(how = How.ID_OR_NAME, using = "country")
	private WebElement countryTF;

	@FindBy(how = How.NAME, using = "_eventId_confirm")
	private WebElement submitPageTwo;

	// Page Three

	@FindBy(how = How.LINK_TEXT, using = "Confirm")
	private WebElement confirmPageThree;

	// Page Four

	@FindBy(how = How.LINK_TEXT, using = "Login Here")
	private WebElement loginHerePageFour;

	public SignUp(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(
				new AjaxElementLocatorFactory(driver, Integer.parseInt(TestingProperties.getLoadAndWaitTimeout())),
				this);
	}

	public WebElement getFirstNameTF() {
		return firstNameTF;
	}

	public WebElement getLastNameTF() {
		return lastNameTF;
	}

	public WebElement getEmailTF() {
		return emailTF;
	}

	public WebElement getContactNumberTF() {
		return contactNumberTF;
	}

	public WebElement getPasswordTF() {
		return passwordTF;
	}

	public WebElement getConfirmPasswordTF() {
		return confirmPasswordTF;
	}

	public WebElement getUserRoleRadio() {
		return userRoleRadio;
	}

	public WebElement getSubmitPageOneButton() {
		return submitPageOne;
	}

	// Page Two

	public WebElement getAddressLineOneTF() {
		return addressLineOneTF;
	}

	public WebElement getAddressLineTwoTF() {
		return addressLineTwoTF;
	}

	public WebElement getCityTF() {
		return cityTF;
	}

	public WebElement getPostalCodeTF() {
		return postalCodeTF;
	}

	public WebElement getStateTF() {
		return stateTF;
	}

	public WebElement getCountryTF() {
		return countryTF;
	}

	public WebElement getSubmitPageTwoButton() {
		return submitPageTwo;
	}

	public WebElement getConfirmPageThreeButton() {
		return confirmPageThree;
	}

	public WebElement getLoginPageFourButton() {
		return loginHerePageFour;
	}

	public WebElement getVerificationErrorMessage(By by) {
		return driver.findElement(by);
	}
}
