package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.Home;
import pages.Pages;
import pages.Products;
import properties.TestEnums.TestStatus;
import properties.TestingProperties;

public class SearchTests extends TestCase {
	private Home homePage;
	private Products prod;
	private String searchVal;

	public void initiate() throws Throwable {
		initiateDriver();
		driver.get(TestingProperties.getWebsiteURL());
		Pages.waitForLoadingSymbolCompletion(driver, Pages.dynamicElementLocator(driver, By.className("se-pre-con")));
	}

	public void enterSearchValue(String searchValue, ExtentReports extent, ExtentTest logger, ExtentTest node) {
		try {
			this.searchVal = searchValue;
			homePage = new Home(driver);
			homePage.viewProductsList();
			prod = new Products(driver);
			Pages.waitForLoadingSymbolCompletion(driver,
					Pages.dynamicElementLocator(driver, By.className("se-pre-con")));
			prod.enterProductName(searchValue);
		} catch (Exception e) {
			node.log(LogStatus.ERROR,
					"Search by Product Name failed due to an exception in the execution. " + e.getMessage());
			logger.appendChild(node);
			extent.endTest(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			Assert.assertTrue(false,
					"Search by Product Name failed due to an exception in the execution. " + e.getMessage());
		}
	}

	// Products search by Product Name validation methods
	public TestStatus validateSearchByProductNameTest(ExtentReports extent, ExtentTest logger, ExtentTest node) {
		TestStatus status;
		try {
			Pages.takeSS(driver, "SearchByProdName_searchValueEntered");
			Assert.assertEquals(prod.getProductNameSearchResult().getText(), searchVal);
			status = TestStatus.TEST_PASSED;
			node.log(LogStatus.PASS, "Successfully found product '" + searchVal + "' during search tests");
			logger.appendChild(node);
			extent.endTest(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
		} catch (AssertionError ae) {
			status = TestStatus.TEST_PASSED;
			node.log(LogStatus.ERROR, "Search test found product '" + searchVal
					+ "' by name; however, the product was not an exact match. " + ae.getMessage());
			logger.appendChild(node);
			extent.endTest(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw ae;
		} catch (NoSuchElementException e) {
			status = TestStatus.TEST_FAILED;
			node.log(LogStatus.ERROR,
					"Search test failed to find product '" + searchVal + "' by name. " + e.getMessage());
			logger.appendChild(node);
			extent.endTest(node);
		} catch (Exception e) {
			status = TestStatus.TEST_FAILED;
			node.log(LogStatus.ERROR,
					"Search by Product Name failed due to an exception in the execution. " + e.getMessage());
			logger.appendChild(node);
			extent.endTest(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw e;
		}
		return status;
	}

	public void validateProdNotFoundMsg(ExtentReports extent, ExtentTest logger, ExtentTest node) {
		try {
			Assert.assertEquals(prod.getProdtNotFoundElement().getText(), "No matching records found");
			node.log(LogStatus.PASS, "Product '" + searchVal
					+ "' not found during search tests; however, error message was successfully displayed to the end user");
			logger.appendChild(node);
		} catch (AssertionError ae) {
			node.log(LogStatus.FAIL,
					"Product '" + searchVal
							+ "' not found during search tests and error message was not displayed to the end user"
							+ ae.getMessage());
			logger.appendChild(node);
			extent.endTest(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			Assert.assertTrue(false,
					"Product '" + searchVal
							+ "' not found during search tests and error message was not displayed to the end user"
							+ ae.getMessage());
		} catch (NoSuchElementException e) {
			node.log(LogStatus.FAIL,
					"Product '" + searchVal
							+ "' not found during search tests and error message was not displayed to the end user"
							+ e.getMessage());
			logger.appendChild(node);
			extent.endTest(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			Assert.assertTrue(false,
					"Product '" + searchVal
							+ "' not found during search tests and error message was not displayed to the end user"
							+ e.getMessage());
		} catch (Exception e) {
			node.log(LogStatus.ERROR,
					"Search by Product Name failed due to an exception in the execution. " + e.getMessage());
			logger.appendChild(node);
			extent.endTest(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw e;
		}
	}

	// Products search by Manufacturer validation methods
	public TestStatus validateSearchByManufacturerTest(ExtentReports extent, ExtentTest logger, ExtentTest node) {
		TestStatus status;
		try {
			Pages.takeSS(driver, "SearchByManufacturerName_searchValueEntered");
			if (prod.getProductLists().size() > 0) {
				status = TestStatus.TEST_PASSED;
				node.log(LogStatus.PASS,
						"Successfully found products by manufacturer '" + searchVal + "' during search tests");
				logger.appendChild(node);
				extent.endTest(node);
				extent.endTest(logger);
				extent.flush();
				extent.close();
				Assert.assertTrue(true,
						"Successfully found products by manufacturer '" + searchVal + "' during search tests");
			} else {
				status = TestStatus.TEST_FAILED;
				Assert.assertTrue(false, "Unable to locate elements searched by manufacturer " + searchVal + ". ");
			}
		} catch (AssertionError e) {
			status = TestStatus.TEST_FAILED;
			node.log(LogStatus.ERROR,
					"Search test failed to find products by manufacturer '" + searchVal + "'. " + e.getMessage());
			logger.appendChild(node);
			extent.endTest(node);
		} catch (Exception e) {
			status = TestStatus.TEST_FAILED;
			node.log(LogStatus.ERROR,
					"Search by Manufacturer failed due to an exception in the execution. " + e.getMessage());
			logger.appendChild(node);
			extent.endTest(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw e;
		}
		return status;
	}

	public void validateProdByManufacturerNotFoundMsg(ExtentReports extent, ExtentTest logger, ExtentTest node) {
		try {
			Assert.assertEquals(prod.getProdtNotFoundElement().getText(), "No matching records found");
			node.log(LogStatus.PASS, "Product Manufacturer '" + searchVal
					+ "' not found during search tests; however, error message was successfully displayed to the end user");
			logger.appendChild(node);
		} catch (AssertionError ae) {
			node.log(LogStatus.FAIL, "Product Manufacturer '" + searchVal
					+ "' not found during search tests and required error message was not displayed to the end user"
					+ ae.getMessage());
			logger.appendChild(node);
			extent.endTest(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw ae;
		} catch (NoSuchElementException e) {
			node.log(LogStatus.FAIL,
					"Product Manufacturer '" + searchVal
							+ "' not found during search tests and error message was not displayed to the end user"
							+ e.getMessage());
			logger.appendChild(node);
			extent.endTest(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw e;
		} catch (Exception e) {
			node.log(LogStatus.ERROR,
					"Search by Product Name failed due to an exception in the execution. " + e.getMessage());
			logger.appendChild(node);
			extent.endTest(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw e;
		}
	}
}
