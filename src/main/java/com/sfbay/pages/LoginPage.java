package com.sfbay.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfbay.uibase.TestBase;

public class LoginPage extends TestBase {

	@FindBy(xpath = "//a[normalize-space()='my account']")
	@CacheLookup
	private WebElement lnkMyAccount;

	@FindBy(xpath = "//button[normalize-space()='Log in']")
	@CacheLookup
	private WebElement btnLogin;

	@FindBy(id = "inputEmailHandle")
	@CacheLookup
	private WebElement txtEmail;

	@FindBy(id = "inputPassword")
	@CacheLookup
	private WebElement txtPassword;
	
	@FindBy(xpath = "//h1[contains(text(),'Log in')]/parent::div//form//p")
	@CacheLookup
	private WebElement authMessage;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public LoginPage clickAccountLnk() {
		lnkMyAccount.click();
		return new LoginPage();

	}
	
	public String authMessage() {
		return authMessage.getText();

	}
	
	public boolean loginBtnDisplayed() {
		return btnLogin.isDisplayed();

	}

	public HomePage loginApp(String userName, String Password) {
		txtEmail.clear();
		txtEmail.sendKeys(userName);
		txtPassword.clear();
		txtPassword.sendKeys(Password);
		btnLogin.click();
		return new HomePage();
	}

	public LoginPage loginAppFailure(String userName, String Password) {
		txtEmail.click();
		txtEmail.clear();
		txtEmail.sendKeys(userName);
		txtPassword.click();
		txtPassword.clear();
		txtPassword.sendKeys(Password);
		btnLogin.click();
		return new LoginPage();
	}

}
