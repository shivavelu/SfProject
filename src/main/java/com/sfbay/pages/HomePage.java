package com.sfbay.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfbay.uibase.TestBase;
import com.sfbay.util.TestUtil;

public class HomePage extends TestBase {

	@FindBy(xpath = "//a[contains(text(),'log out')]")
	@CacheLookup
	private WebElement lnkLogOut;

	@FindBy(xpath = "//a[normalize-space()='searches']")
	@CacheLookup
	public WebElement tabSearch;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public SearchTabPage clickSearchtab() {
		tabSearch.click();
		return new SearchTabPage();

	}

	public LoginPage logout() {
		TestUtil.javaScriptElementClick(lnkLogOut);
		return new LoginPage();
	}

/*	public SearchTabPage editsearchName() {

		return new SearchTabPage();

	}*/

}
