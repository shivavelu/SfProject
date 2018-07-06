package com.sfbay.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sfbay.api.PropertyFileUtil;
import com.sfbay.uibase.TestBase;

public class SearchTabPage extends TestBase {
	private String edit;
	String name;
	String editName;
	private String deleteSearch;
	private String prgDelMsg;
	private String editedName;
	private String deletedMsg;

	@FindBy(xpath = "//input[@name='subName']")
	@CacheLookup
	public WebElement txtName;

	@FindBy(xpath = "//button[normalize-space()='save']")
	@CacheLookup
	public WebElement btnSave;

	@FindBy(xpath = "//b[contains(text(),'You have no saved searches at this time.')]")
	@CacheLookup
	public WebElement noSrchMsg;

	public SearchTabPage() {
		PageFactory.initElements(driver, this);
	}

	public SearchTabPage editName(String nameedit) {
		txtName.click();
		txtName.clear();
		txtName.sendKeys(nameedit);
		btnSave.click();
		return new SearchTabPage();
	}

	public SearchTabPage edit() {
		driver.findElement(By.xpath(LocatorHelper.setLocatorEdit(PropertyFileUtil.propapi.getProperty("searchitem")))).click();

		return new SearchTabPage();
	}

	public SearchTabPage editv1() {
		edit = "//a[contains(text(),'" + PropertyFileUtil.propapi.getProperty("searchitem")
				+ "')]/parent::td/following-sibling::td//button[normalize-space()='edit']";
		driver.findElement(By.xpath(edit)).click();
		return new SearchTabPage();
	}

	public String getnameEdit() {
		editName = "//td[contains(text(),'" + PropertyFileUtil.propapi.getProperty("edit") + "')]";
		return editedName = driver.findElement(By.xpath(editName)).getText();
		
	}

	public SearchTabPage deleteSearchV1() {

		deleteSearch = "//td[contains(text(),'" + PropertyFileUtil.propapi.getProperty("edit")
				+ "')]/following-sibling::td//button[contains(text(),'delete')]";
		driver.findElement(By.xpath(deleteSearch)).click();
		return new SearchTabPage();
	}

	public String getdeleteMsg() {

		prgDelMsg = "//p[contains(text(),'" + PropertyFileUtil.propapi.getProperty("edit") + "')]";
		return deletedMsg = driver.findElement(By.xpath(prgDelMsg)).getText();

	}

}
