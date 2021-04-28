package testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.Home;
import pages.Login;
import pages.Pages;
import pages.ProductManagement;
import properties.TestingProperties;

public class AdminUserTests extends TestCase {

	private Home homePage;
	private Login loginPage;
	private ProductManagement prodMgmt;

	public void initiateAdminUserTests() throws Throwable {
		if (System.getProperty("os.name").equalsIgnoreCase("Windows 10")) {
			initiateDriver();
			driver.get(TestingProperties.getWebsiteURL());
			Pages.waitForLoadingSymbolCompletion(driver,
					Pages.dynamicElementLocator(driver, By.className("se-pre-con")));
		} else {
			System.out.println(System.getProperty("os.name"));
			throw new SkipException("Admin User Add Product details test has been skipped since the "
					+ "current test scripts will only complete if executed on a x64 based Windows 10 machine"
					+ "due to the existence of an AutoIT Executable for file upload purposes.");
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void adminUserLogin(String usid, String pswd, ExtentReports extent, ExtentTest logger, ExtentTest node) {
		try {
			node = extent.startTest("Initiate Admin user site login with : " + usid);
			homePage = new Home(driver);
			homePage.loginLinkClick();
			loginPage = new Login(driver);
			loginPage.enterLoginCredentials(usid, pswd);
			Pages.takeSS(driver, "AdminUserTests_credentialsEntered");
			loginPage.getLoginButton().click();
			if (driver.getTitle().equalsIgnoreCase("Medicare - Home")) {
				Pages.waitForLoadingSymbolCompletion(driver,
						Pages.dynamicElementLocator(driver, By.className("se-pre-con")));
				if (homePage.getManageProdLink().isDisplayed()) {
					node.log(LogStatus.PASS, "Admin user site login was successful via : " + usid);
					extent.endTest(node);
					logger.appendChild(node);
					Assert.assertTrue(true, "Admin user site login was successful via : " + usid);
					Pages.takeSS(driver, "AdminUserTests_AddProduct_SuccessfulLogin");
				} else {
					throw new SkipException("Login was successful; however, the account is not an admin account");
				}
			} else {
				Assert.assertTrue(false, "Login failed with supplied credentials");
			}
		} catch (AssertionError ae) {
			node.log(LogStatus.FAIL, ae.getMessage());
			extent.endTest(node);
			logger.appendChild(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw ae;
		} catch (NoSuchElementException ne) {
			node.log(LogStatus.SKIP, "Account is not an admin account. Exception: " + ne.getMessage());
			extent.endTest(node);
			logger.appendChild(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw new SkipException("Login was successful; however, the account is not an admin account");
		} catch (SkipException se) {
			node.log(LogStatus.SKIP, se.getMessage());
			extent.endTest(node);
			logger.appendChild(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw se;
		} catch (Exception e) {
			node.log(LogStatus.ERROR, e.getMessage());
			extent.endTest(node);
			logger.appendChild(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw e;
		}
	}

	public void enterProdDetails(HashMap<String, String> prodDetails, ExtentReports extent, ExtentTest logger,
			ExtentTest node) throws Throwable {
		try {
			node = extent.startTest("Enter product details on website");
			if (!System.getProperty("os.name").equalsIgnoreCase("Windows 10")) {
				System.out.println("IF entered + " + System.getProperty("os.name"));
				throw new SkipException("Product details test has been skipped since the "
						+ "current test scripts will only complete if executed on a x64 based Windows 10 machine "
						+ "due to the existence of an AutoIT Executable for file upload purposes.");
			} else {
				String prodCategory = prodDetails.get("Category");
				if (!prodCategory.equals("Antipyretics") && !prodCategory.equals("Analgesics")
						&& !prodCategory.equals("Antibiotics")) {
					throw new RuntimeException("Category test data is not one of available categories. "
							+ "Please consult the available application categories and try again");
				} else {
					homePage.getManageProdLink().click();
					Pages.waitForLoadingSymbolCompletion(driver,
							Pages.dynamicElementLocator(driver, By.className("se-pre-con")));
					prodMgmt = new ProductManagement(driver);
					prodMgmt.getProdNameTF().clear();
					prodMgmt.getProdNameTF().sendKeys(prodDetails.get("ProdName"));
					prodMgmt.getBrandNameTF().clear();
					prodMgmt.getBrandNameTF().sendKeys(prodDetails.get("BrandName"));
					prodMgmt.getDescriptionTextArea().clear();
					prodMgmt.getDescriptionTextArea().sendKeys(prodDetails.get("Description"));
					prodMgmt.getUnitPriceTF().clear();
					prodMgmt.getUnitPriceTF().sendKeys(prodDetails.get("Price"));
					prodMgmt.getQuantityTF().clear();
					prodMgmt.getQuantityTF().sendKeys(prodDetails.get("Quantity"));
					prodMgmt.getCategoryIdDropDown().sendKeys(prodDetails.get("Category"));
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", prodMgmt.getFileUpload());
					if (TestingProperties.getAutoITScript() != null) {
						Thread.sleep(3000);
						Runtime.getRuntime().exec(TestingProperties.getAutoITScript());
						Thread.sleep(3000);
					} else {
						throw new RuntimeException("Failed to run upload script");
					}
					Pages.takeSS(driver, "AdminUserTests_AddProduct_prodDetailsEntered");
					prodMgmt.getSubmitButton().click();
					node.log(LogStatus.PASS, "Product details for '" + prodDetails.get("ProdName")
							+ "' was successfully entered on site");
					extent.endTest(node);
					logger.appendChild(node);
					Assert.assertTrue(true, "Product details for '" + prodDetails.get("ProdName")
							+ "' was successfully entered on site");
				}
			}
		} catch (AssertionError ae) {
			node.log(LogStatus.FAIL, ae.getMessage());
			extent.endTest(node);
			logger.appendChild(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw ae;
		} catch (NoSuchElementException ne) {
			node.log(LogStatus.FAIL, ne.getMessage());
			extent.endTest(node);
			logger.appendChild(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw ne;
		} catch (Exception e) {
			node.log(LogStatus.ERROR, e.getMessage());
			extent.endTest(node);
			logger.appendChild(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw e;
		}
	}

	public void submitProdDetails(String prodName, ExtentReports extent, ExtentTest logger, ExtentTest node)
			throws Throwable {
		try {
			Pages.waitForLoadingSymbolCompletion(driver,
					Pages.dynamicElementLocator(driver, By.className("se-pre-con")));
			node = extent.startTest("Submit product details.");
			if (!checkVerificationMessage()) {
				node.log(LogStatus.PASS, "Product '" + prodName + "' has been successfully added to the product list");
				logger.appendChild(node);
				Assert.assertTrue(true, "Product '" + prodName + "' has been successfully added to the product list");
			} else {
				Pages.takeSS(driver, "AdminUserTests_AddProduct_VerificationError");
				Assert.assertTrue(false, "Adding Product '" + prodName + "' failed");
			}
		} catch (AssertionError ae) {
			node.log(LogStatus.FAIL, ae.getMessage());
			extent.endTest(node);
			logger.appendChild(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw ae;
		} catch (NoSuchElementException ne) {
			node.log(LogStatus.FAIL, ne.getMessage());
			extent.endTest(node);
			logger.appendChild(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw ne;
		} catch (Exception e) {
			node.log(LogStatus.ERROR, e.getMessage());
			extent.endTest(node);
			logger.appendChild(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw e;
		}
	}

	private boolean checkVerificationMessage() {
		List<String> errMsgID = new ArrayList<String>();
		errMsgID.add("name.errors");
		errMsgID.add("brand.errors");
		errMsgID.add("description.errors");
		errMsgID.add("unitPrice.errors");
		errMsgID.add("file.errors");
		Iterator<String> i = errMsgID.iterator();
		boolean msgDisplayed = false;
		while (i.hasNext()) {
			try {
				if (prodMgmt.getProductVerificationErrorMessage(By.id(i.next())).isDisplayed()) {
					msgDisplayed = true;
				}
			} catch (Exception e) {
			}
		}
		return msgDisplayed;
	}
}
