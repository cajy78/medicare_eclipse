package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import properties.TestingProperties;

public class Cart extends Pages {

	private WebDriver driver;

	@FindBy(how = How.XPATH, using = "//table[@id=\"cart\"]/tfoot/tr[2]/td[1]/a")
	private WebElement continueShoppingButton;

	@FindBy(how = How.XPATH, using = "//table[@id=\"cart\"]/tfoot/tr[2]/td[4]/a")
	private WebElement checkoutCartButton;

	@FindBy(how = How.CLASS_NAME, using = "text-center")
	private WebElement cartHeaderMsg;

	@FindBy(how = How.LINK_TEXT, using = "Select")
	private WebElement selectAddress;
	
	@FindBy(how = How.LINK_TEXT, using = "Pay")
	private WebElement payButton;

	public Cart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(
				new AjaxElementLocatorFactory(driver, Integer.parseInt(TestingProperties.getLoadAndWaitTimeout())),
				this);
	}

	public WebElement getContinueShoppingButton() {
		waitForClickableElement(driver, By.xpath("//table[@id=\"cart\"]/tfoot/tr[2]/td[1]/a"));
		return continueShoppingButton;
	}

	public WebElement getCheckoutCartButton() {
		waitForClickableElement(driver, By.xpath("//table[@id=\"cart\"]/tfoot/tr[2]/td[4]/a"));
		return checkoutCartButton;
	}

	public WebElement getCartHeaderMsg() {
		return cartHeaderMsg;
	}

	public WebElement getSelectAddressButton() {
		return selectAddress;
	}
	
	public WebElement getPayButton() {
		return payButton;
	}
}