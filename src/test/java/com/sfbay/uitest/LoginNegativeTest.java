package com.sfbay.uitest;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.sfbay.api.PropertyFileUtil;
import com.sfbay.pages.LoginPage;
import com.sfbay.uibase.TestBase;
import com.sfbay.util.TestUtil;

public class LoginNegativeTest extends TestBase {

	LoginPage loginPage;

	public LoginNegativeTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new LoginPage();

	}

	@Test
	public void loginNegativeTest1() {

		loginPage = loginPage.loginAppFailure(PropertyFileUtil.propapi.getProperty("invalidUsername"),
				PropertyFileUtil.propapi.getProperty("password"));
		String s = loginPage.authMessage();
		try {

			
			Assert.assertEquals(s, PropertyFileUtil.propapi.getProperty("loginfailsU"));
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Test
	public void loginNegativeTest2() {

		loginPage = loginPage.loginAppFailure(PropertyFileUtil.propapi.getProperty("username"),
				PropertyFileUtil.propapi.getProperty("invalidPassword"));
		String s = loginPage.authMessage();
		try {

			
			Assert.assertEquals(s, PropertyFileUtil.propapi.getProperty("loginfailure"));
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Test
	public void loginNegativeTest3() {

		loginPage = loginPage.loginAppFailure(PropertyFileUtil.propapi.getProperty("invalidUsername"),
				PropertyFileUtil.propapi.getProperty("invalidPassword"));
		try {

			String s = loginPage.authMessage();
			Assert.assertEquals(s, PropertyFileUtil.propapi.getProperty("loginfailsU"));
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@BeforeMethod
	public void loginPage()
	{
		TestUtil.browserBack(PropertyFileUtil.propapi.getProperty("loginPage"));
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
