package testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.Home;
import pages.Login;
import pages.Pages;
import pages.SignUp;
import properties.TestEnums.AccountTestType;
import properties.TestEnums.SignUpPage;
import properties.TestingProperties;

public class AccountTests extends TestCase {

	private Home homePage;
	private Login loginPage;
	private SignUp signUpPage;
	private String uName, pswd;

	public void initiateAccountTests(AccountTestType testType) throws Throwable {
		try {
			initiateDriver();
			driver.get(TestingProperties.getWebsiteURL());
			Pages.waitForLoadingSymbolCompletion(driver,
					Pages.dynamicElementLocator(driver, By.className("se-pre-con")));
			homePage = new Home(driver);
			switch (testType) {
			case LOGIN:
				homePage.loginLinkClick();
				break;
			case SIGNUP:
				homePage.signUpLinkClick();
				break;
			default:
				throw new RuntimeException("Inccorect Testype passed to initiate Account Tests");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initiateAccountLoginTest(String username, String pwd) {
		try {
			this.uName = username;
			this.pswd = pwd;
			loginPage = new Login(driver);
			loginPage.enterLoginCredentials(username, pwd);
			Pages.takeSS(driver, "LoginTests_credentialsEntered");
			loginPage.getLoginButton().click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateAccountLogin(ExtentReports extent, ExtentTest logger, ExtentTest node) {
		String pageTitle = driver.getTitle();
		ExtentTest errorNode = extent.startTest("Check login error message");
		try {
			if (pageTitle.equals("Medicare - Login")) {
				Pages.takeSS(driver, "LoginTests_LoginUnSuccessful");
				// Validate login error test - Error message: Username and Password is invalid!
				node.log(LogStatus.ERROR,
						"Login has failed with supplied credentials. Username: " + uName + " Password: " + pswd);
				extent.endTest(node);
				logger.appendChild(node);
				loginPage.waitForVisibleElement(driver, loginPage.getLoginErrorMsg());
				String loginMsg = loginPage.getLoginErrorMsg().getText();
				errorNode.log(LogStatus.PASS, "Login error message was successfully displayed");
				extent.endTest(errorNode);
				logger.appendChild(errorNode);
				Assert.assertEquals(loginMsg, "Username and Password is invalid!");
			} else if (pageTitle.equals("Medicare - Home")) {
				Pages.waitForLoadingSymbolCompletion(driver,
						Pages.dynamicElementLocator(driver, By.className("se-pre-con")));
				Pages.takeSS(driver, "LoginTests_LoginSuccessful");
				errorNode.log(LogStatus.INFO, "Error test skipped since login was successful");
				extent.endTest(errorNode);
				node.log(LogStatus.PASS,
						"Login successful with supplied credentials. Username: " + uName + " Password: " + pswd);
				extent.endTest(node);
				logger.appendChild(node);
				logger.appendChild(errorNode);
				Assert.assertTrue(true,
						"Login successful with supplied credentials. Username: " + uName + " Password: " + pswd);
			}
		} catch (AssertionError ae) {
			node.log(LogStatus.FAIL, "The required Login Error message was not displayed " + ae.getMessage());
			logger.appendChild(node);
			extent.endTest(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			Assert.assertTrue(false, "The required Login Error message was not displayed " + ae.getMessage());
		} catch (NoSuchElementException ne) {
			node.log(LogStatus.FAIL, "Login Error message not displayed " + ne.getMessage());
			logger.appendChild(node);
			extent.endTest(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			Assert.assertTrue(false, "Login Error message not displayed " + ne.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			errorNode.log(LogStatus.ERROR, "Exception caught at validateAccountLogin : " + e.getMessage());
			extent.endTest(errorNode);
			logger.appendChild(errorNode);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			Assert.assertTrue(false, "Exception caught at validateAccountLogin : " + e.getMessage());
		}
	}

	public void initiateAccountSignupTest(HashMap<String, String> userDetails, SignUpPage pageNum, ExtentReports extent,
			ExtentTest logger, ExtentTest node) throws Throwable {
		signUpPage = new SignUp(driver);
		Pages.waitForLoadingSymbolCompletion(driver, Pages.dynamicElementLocator(driver, By.className("se-pre-con")));
		try {
			if (pageNum.equals(SignUpPage.PAGEONE)) {
				node = extent.startTest("Account Sign up Personal Details Test for : " + userDetails.get("Email"));
				signUpPage.getFirstNameTF().sendKeys(userDetails.get("First Name"));
				signUpPage.getLastNameTF().sendKeys(userDetails.get("Last Name"));
				signUpPage.getEmailTF().sendKeys(userDetails.get("Email"));
				signUpPage.getContactNumberTF().sendKeys(userDetails.get("Contact Number"));
				signUpPage.getPasswordTF().sendKeys(userDetails.get("Password"));
				signUpPage.getConfirmPasswordTF().sendKeys(userDetails.get("Password"));
				Pages.takeSS(driver, "SignupTests_PersonalDetailsEntered");
				signUpPage.getSubmitPageOneButton().click();
				Pages.waitForLoadingSymbolCompletion(driver,
						Pages.dynamicElementLocator(driver, By.className("se-pre-con")));
				if (checkVerificationMessage()) {
					Pages.takeSS(driver, "SignupTests_PersonalDetails_VerificationError");
					throw new RuntimeException("Personal details were successfully entered and submitted; however, "
							+ "A data entry verification error message was displayed while trying to submit the entered values.");
				} else {
					node.log(LogStatus.PASS, "Personal details for user " + userDetails.get("Email")
							+ " has been successfully entered and submitted");
					extent.endTest(node);
					logger.appendChild(node);
					Assert.assertTrue(true, "Personal details for user " + userDetails.get("Email")
							+ " has been successfully entered and submitted");
				}
			} else if (pageNum.equals(SignUpPage.PAGETWO)) {
				node = extent.startTest("Account Sign up Address details Test for : " + userDetails.get("Email"));
				signUpPage.getAddressLineOneTF().sendKeys(userDetails.get("AddressL1"));
				signUpPage.getAddressLineTwoTF().sendKeys(userDetails.get("AddressL2"));
				signUpPage.getCityTF().sendKeys(userDetails.get("City"));
				signUpPage.getPostalCodeTF().sendKeys(userDetails.get("Postal Code"));
				signUpPage.getStateTF().sendKeys(userDetails.get("State"));
				signUpPage.getCountryTF().sendKeys(userDetails.get("Country"));
				Pages.takeSS(driver, "SignupTests_AddressDetailsEntered");
				signUpPage.getSubmitPageTwoButton().click();
				Pages.waitForLoadingSymbolCompletion(driver,
						Pages.dynamicElementLocator(driver, By.className("se-pre-con")));
				if (checkVerificationMessage()) {
					Pages.takeSS(driver, "SignupTests_AddressDetails_VerificationError");
					throw new RuntimeException("Address details were successfully entered submitted; however, "
							+ "A data entry verification error message was displayed while trying to submit the entered values.");
				} else {
					node.log(LogStatus.PASS, "Address and contact details for user " + userDetails.get("Email")
							+ " has been successfully entered and submitted");
					extent.endTest(node);
					logger.appendChild(node);
					Assert.assertTrue(true, "Address and contact details for user " + userDetails.get("Email")
							+ " has been successfully entered and submitted");
				}
			} else if (pageNum.equals(SignUpPage.PAGETHREE)) {
				node = extent.startTest("Account Sign up confirm details Test for : " + userDetails.get("Email"));
				Pages.takeSS(driver, "SignupTests_ConfirmDetailsPage");
				signUpPage.getConfirmPageThreeButton().click();
				Pages.waitForLoadingSymbolCompletion(driver,
						Pages.dynamicElementLocator(driver, By.className("se-pre-con")));
				node.log(LogStatus.PASS, "Account Sign up confirm details Test for : " + userDetails.get("Email")
						+ " has been successfully completed");
				extent.endTest(node);
				logger.appendChild(node);
				Assert.assertTrue(true, "Account Sign up confirm details Test for : " + userDetails.get("Email")
						+ " has been successfully completed");
			} else if (pageNum.equals(SignUpPage.PAGEFOUR)) {
				node = extent.startTest("Navigate to login page");
				Pages.takeSS(driver, "SignupTests_Completed_LoginButton");
				signUpPage.getLoginPageFourButton().click();
				System.out.println(driver.getTitle());
				if (driver.getTitle().equals("Medicare - Login")) {
					node.log(LogStatus.PASS, "Navigate to login page has been successfully completed");
					Assert.assertTrue(true, "Navigate to login page has been successfully completed");
				} else {
					Assert.assertTrue(false, "Navigate to login page has been failed");
				}
				extent.endTest(node);
				logger.appendChild(node);
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
			node.log(LogStatus.INFO, re.getMessage());
			extent.endTest(node);
			logger.appendChild(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw re;

		} catch (AssertionError ae) {
			node.log(LogStatus.FAIL, ae.getMessage());
			extent.endTest(node);
			logger.appendChild(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			throw ae;
		} catch (Exception e) {
			e.printStackTrace();
			node.log(LogStatus.ERROR, "Exception caught at validateAccountSignup : " + e.getMessage());
			extent.endTest(node);
			logger.appendChild(node);
			extent.endTest(logger);
			extent.flush();
			extent.close();
			exit();
			Assert.assertTrue(false, "Exception caught at validateAccountSignup : " + e.getMessage());
		}
	}

	private boolean checkVerificationMessage() {
		List<String> errMsgID = new ArrayList<String>();
		errMsgID.add("firstName.errors");
		errMsgID.add("lastName.errors");
		errMsgID.add("email.errors");
		errMsgID.add("contactNumber.errors");
		errMsgID.add("password.errors");
		errMsgID.add("confirmPassword.errors");
		errMsgID.add("addressLineOne.errors");
		errMsgID.add("addressLineTwo.errors");
		errMsgID.add("city.errors");
		errMsgID.add("postalCode.errors");
		errMsgID.add("state.errors");
		errMsgID.add("country.errors");
		Iterator<String> i = errMsgID.iterator();
		boolean msgDisplayed = false;
		while (i.hasNext()) {
			try {
				if (signUpPage.getVerificationErrorMessage(By.id(i.next())).isDisplayed()) {
					msgDisplayed = true;
				}
			} catch (Exception e) {
			}
		}
		return msgDisplayed;
	}
}
