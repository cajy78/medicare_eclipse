package pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import properties.TestingProperties;

public class Products extends Pages {
	WebDriver driver;

	@FindBy(how = How.XPATH, using = "//*[@class=\"even\"]/td[2]")
	private List<WebElement> evenProductList = new ArrayList<WebElement>();

	@FindBy(how = How.XPATH, using = "//*[@class=\"odd\"]/td[2]")
	private List<WebElement> oddProductList = new ArrayList<WebElement>();

	@FindBy(how = How.XPATH, using = "//*[@class=\"odd\"]/td[2]")
	private WebElement searchProductResult;

	@FindBy(how = How.XPATH, using = "//input[@type=\"search\"]")
	private WebElement searchBox;

	@FindBy(how = How.CLASS_NAME, using = "dataTables_empty")
	private WebElement prodNotFoundMsg;

	@FindBy(how = How.XPATH, using = "//tr[@class=\"odd\"]/td[6]/a[2]")
	private WebElement addSearchedProdToCart;

	public Products(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(
				new AjaxElementLocatorFactory(driver, Integer.parseInt(TestingProperties.getLoadAndWaitTimeout())),
				this);
	}

	public void checkProductList() {
		Iterator<WebElement> evenI = evenProductList.iterator();
		Iterator<WebElement> oddI = oddProductList.iterator();
		while (evenI.hasNext())
			System.out.println("Product Name: " + evenI.next().getText());
		while (oddI.hasNext())
			System.out.println("Product Name: " + oddI.next().getText());
		System.out.println("even list size is: " + evenProductList.size());
		System.out.println("odd list size is: " + oddProductList.size());
	}

	public List<String> getProductLists() {
		Iterator<WebElement> evenI = evenProductList.iterator();
		Iterator<WebElement> oddI = oddProductList.iterator();
		List<String> productSearchListResult = new ArrayList<String>();
		while (evenI.hasNext())
			productSearchListResult.add(evenI.next().getText());
		while (oddI.hasNext())
			productSearchListResult.add(oddI.next().getText());
		return productSearchListResult;
	}

	public void enterProductName(String prodName) {
		searchBox.sendKeys(prodName);
	}

	public WebElement getProductNameSearchResult() {
		return searchProductResult;
	}

	public WebElement getProdtNotFoundElement() {
		return prodNotFoundMsg;
	}

	public WebElement getAddSearchedProdToCartButton() {
		return addSearchedProdToCart;
	}
}
