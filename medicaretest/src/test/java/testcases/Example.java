package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.Home;
import pages.Products;
import properties.TestingProperties;

public class Example extends TestCase {

	@BeforeTest
	public void initiate() throws Throwable {
//		initiateDriver();
//		driver.get(TestingProperties.getWebsiteURL());
	}

	@Test
	public void runTest() {
//		Home homePage = new Home(driver);
//		homePage.viewProductsList();
//		Products prod = new Products(driver);
//		prod.checkProductList();
		try
		{
			Assert.assertTrue(false);
		}
		catch(AssertionError ae)
		{
			System.out.println("Assertion error caught in AE : " + ae.getMessage());
			throw ae;
		}
		catch(Exception e)
		{
			System.out.println("Exception: Assertion error caught in Exception : " + e.getMessage());
		}
	}

//	@AfterTest
//	public void exit() throws Throwable
//	{
//		Thread.sleep(5000);
//		driver.quit();
//	}
}
