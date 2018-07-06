package com.sfbay.uitest;


import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.sfbay.api.PropertyFileUtil;
import com.sfbay.customexception.ElementNotFoundException;
import com.sfbay.pages.HomePage;
import com.sfbay.pages.LoginPage;
import com.sfbay.pages.SearchTabPage;
import com.sfbay.uibase.TestBase;

public class SearchResultTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	SearchTabPage searchPage;

	public SearchResultTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		homePage = new HomePage();
		searchPage = new SearchTabPage();
	}

	@Test
	public void loginPageDisplayTest() {

		try {
			loginPage.clickAccountLnk();
			Assert.assertTrue(loginPage.loginBtnDisplayed());
		} catch (NoSuchElementException e) {

			e.printStackTrace();
			throw new ElementNotFoundException("Login page not loaded");

		}

	}

	@Test
	public void loginPageFunctionTest() {

		homePage = loginPage.loginApp(PropertyFileUtil.propapi.getProperty("username"), PropertyFileUtil.propapi.getProperty("password"));
		try {
			Assert.assertTrue(homePage.tabSearch.isDisplayed());

		} catch (NoSuchElementException e) {

			String s = loginPage.authMessage();
			Assert.assertEquals(s, "Your email address, handle, or password is incorrect. Please try again.");
			e.printStackTrace();
			throw new ElementNotFoundException(s);
		}

	}

	@Test
	public void searchResultTest() {

	
		try {
			searchPage = homePage.clickSearchtab();
			searchPage.editv1().editName(PropertyFileUtil.propapi.getProperty("edit"));
			searchPage.getnameEdit();

			Assert.assertEquals(PropertyFileUtil.propapi.getProperty("edit"), searchPage.getnameEdit());
		} catch (NoSuchElementException e) {

			String s = searchPage.noSrchMsg.getText();
			Assert.assertEquals(s, "You have no saved searches at this time.");
			e.printStackTrace();
		    throw new ElementNotFoundException(s);

		}
	}

	@Test
	public void searchDeleteTest() {
		try {
			searchPage.deleteSearchV1();
			searchPage.getdeleteMsg();
			Assert.assertTrue(searchPage.getdeleteMsg().contains("deleted"));
		} catch (NoSuchElementException e) {

			String s = searchPage.noSrchMsg.getText();
			Assert.assertEquals(s, "You have no saved searches at this time.");
			e.printStackTrace();
			throw new ElementNotFoundException(s);
		}

	}

	@Test
	public void logout() {
		try {
			homePage.logout();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ElementNotFoundException("Logout not possible as user not in homepage");
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
