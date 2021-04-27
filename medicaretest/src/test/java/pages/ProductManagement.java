package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import properties.TestingProperties;

public class ProductManagement {

	private WebDriver driver;

	@FindBy(how = How.ID_OR_NAME, using = "name")
	private WebElement prodNameTF;

	@FindBy(how = How.ID_OR_NAME, using = "brand")
	private WebElement brandNameTF;

	@FindBy(how = How.ID_OR_NAME, using = "description")
	private WebElement descriptionTextArea;

	@FindBy(how = How.ID_OR_NAME, using = "unitPrice")
	private WebElement unitPriceTF;

	@FindBy(how = How.ID_OR_NAME, using = "quantity")
	private WebElement quantityTF;

	@FindBy(how = How.ID_OR_NAME, using = "file")
	private WebElement fileUpload;

	@FindBy(how = How.ID_OR_NAME, using = "categoryId")
	private WebElement categoryIdDropDown;
	
	@FindBy(how = How.NAME, using = "submit")
	private WebElement submitButton;
	
	@FindBy(how = How.XPATH, using = "//*[@text=\"Product submitted successfully!\"]")
	private WebElement prodSuccessMsg;

	public ProductManagement(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(
				new AjaxElementLocatorFactory(driver, Integer.parseInt(TestingProperties.getLoadAndWaitTimeout())),
				this);
	}

	public WebElement getProdNameTF() {
		return prodNameTF;
	}

	public WebElement getBrandNameTF() {
		return brandNameTF;
	}

	public WebElement getDescriptionTextArea() {
		return descriptionTextArea;
	}

	public WebElement getUnitPriceTF() {
		return unitPriceTF;
	}

	public WebElement getQuantityTF() {
		return quantityTF;
	}

	public WebElement getFileUpload() {
		return fileUpload;
	}

	public WebElement getCategoryIdDropDown() {
		return categoryIdDropDown;
	}
	
	public WebElement getSubmitButton() {
		return submitButton;
	}
	
	public WebElement getProdSuccessMsg() {
		return prodSuccessMsg;
	}
	
	public WebElement getProductVerificationErrorMessage(By by) {
		return driver.findElement(by);
	}
}
