package testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.Cart;
import pages.Home;
import pages.Login;
import pages.Pages;
import pages.Products;
import properties.TestingProperties;

public class PurchaseTest extends TestCase {
	private Home homePage;
	private Login loginPage;
	private Cart cartPage;
	private String user;

	public void initiatePurchaseTest() throws Throwable {
		initiateDriver();
		driver.get(TestingProperties.getWebsiteURL());
		Pages.waitForLoadingSymbolCompletion(driver, Pages.dynamicElementLocator(driver, By.className("se-pre-con")));
	}

	public void registedUserLogin(String uname, String pwd, ExtentReports extent, ExtentTest logger, ExtentTest node) {
		try {
			node = extent.startTest("Initiate site login with : " + uname);
			this.user = uname;
			homePage = new Home(driver);
			homePage.loginLinkClick();
			loginPage = new Login(driver);
			loginPage.enterLoginCredentials(uname, pwd);
			Pages.takeSS(driver, "PurchaseTests_credentialsEntered");
			loginPage.getLoginButton().click();
			if (driver.getTitle().equalsIgnoreCase("Medicare - Home")) {
				node.log(LogStatus.PASS, "Site login was successful via : " + uname);
				extent.endTest(node);
				logger.appendChild(node);
				Assert.assertTrue(true, "Site login was successful via : " + uname);
				Pages.waitForLoadingSymbolCompletion(driver,
						Pages.dynamicElementLocator(driver, By.className("se-pre-con")));
				homePage.viewProductsList();
			} else {
				Assert.assertTrue(false, "Login failed with supplied credentials");
			}
		} catch (AssertionError ae) {
			node.log(LogStatus.FAIL, "Login failed. " + ae.getMessage());
			extent.endTest(node);
			logger.appendChild(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw ae;
		} catch (Exception e) {
			node.log(LogStatus.ERROR, "Login failed due to an exception " + e.getMessage());
			extent.endTest(node);
			logger.appendChild(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw e;
		}
	}

	public void addToCart(List<String> prods, ExtentReports extent, ExtentTest logger, ExtentTest node)
			throws Throwable {
		try {
			for (int i = 1; i < prods.size(); i++) {
				node = extent.startTest("Add product to Cart");
				Pages.waitForLoadingSymbolCompletion(driver,
						Pages.dynamicElementLocator(driver, By.className("se-pre-con")));
				Products prodPage_local = new Products(driver);
				prodPage_local.enterProductName(prods.get(i));
				Pages.waitForPageLoad(driver);
				prodPage_local.getAddSearchedProdToCartButton().click();
				if (i < prods.size() - 1) {
					Cart cartLocal = new Cart(driver);
					cartLocal.waitForInvisbilityOfElement(driver, cartLocal.getCartHeaderMsg());
					Pages.waitForPageLoad(driver);
					Pages.takeSS(driver, "PurchaseTests_CartProd"+i+"_Added");
					cartLocal.getContinueShoppingButton().click();
				}
				node.log(LogStatus.PASS, "Adding product : " + prods.get(i) + " was successful");
				extent.endTest(node);
				logger.appendChild(node);
			}
			Assert.assertTrue(true, "Adding products to cart completed successfully");
		} catch (Exception e) {
			node.log(LogStatus.ERROR, "Add to cart due to an exception : " + e.getMessage());
			extent.endTest(node);
			logger.appendChild(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			Assert.assertTrue(false, "Add to cart due to an exception : " + e.getMessage());
		}
	}

	public void cartCheckOut(ExtentReports extent, ExtentTest logger, ExtentTest node) {
		try {
			node = extent.startTest("Cart checkout");
			cartPage = new Cart(driver);
			cartPage.waitForInvisbilityOfElement(driver, cartPage.getCartHeaderMsg());
			cartPage.getCheckoutCartButton().click();
			Pages.waitForLoadingSymbolCompletion(driver,
					Pages.dynamicElementLocator(driver, By.className("se-pre-con")));
			Pages.takeSS(driver, "PurchaseTests_CartProd_AddressPage");
			cartPage.getSelectAddressButton().click();
			Pages.waitForLoadingSymbolCompletion(driver,
					Pages.dynamicElementLocator(driver, By.className("se-pre-con")));
			Pages.takeSS(driver, "PurchaseTests_CartProd_PaymentPage");
			cartPage.getPayButton().click();
			if (cartPage.getCartHeaderMsg().getText().equalsIgnoreCase("Your Order is Confirmed!!")) {
				Pages.takeSS(driver, "PurchaseTests_OrderConfirmed");
				node.log(LogStatus.PASS, "Successfully completed checking out products in the card");
				extent.endTest(node);
				logger.appendChild(node);
				Assert.assertTrue(true, "\"Successfully completed checking out products in the card\"");
			} else {
				Pages.takeSS(driver, "PurchaseTests_OrderFailed");
				Assert.assertTrue(false, "Product checkout has failed");
			}
		} catch (AssertionError ae) {
			node.log(LogStatus.FAIL, "Checkout to purchase prdocuts failed : " + ae.getMessage());
			extent.endTest(node);
			logger.appendChild(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw ae;
		} catch (Exception e) {
			node.log(LogStatus.ERROR, "Checkout of products failed due to Exception : " + e.getMessage());
			extent.endTest(node);
			logger.appendChild(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw e;
		}
	}
}
