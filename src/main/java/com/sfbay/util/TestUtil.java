package com.sfbay.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sfbay.uibase.TestBase;

abstract public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	public static String TESTDATA_SHEET_PATH = "/Users/naveenkhunteta/Documents/workspace"
			+ "/FreeCRMTest/src/main/java/com/crm/qa/testdata/FreeCrmTestData.xlsx";

	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

	public static void waitforElementVisable(WebElement element, int timeLimitSec) {

		WebDriverWait wait = new WebDriverWait(driver, timeLimitSec);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public static void waitforElementClickable(WebElement element, int timeLimitSec) {

		WebDriverWait wait = new WebDriverWait(driver, timeLimitSec);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitforAlertPresent(int timeLimitSec) {

		WebDriverWait wait = new WebDriverWait(driver, timeLimitSec);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public static void javaScriptElementClick(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public static void javaScriptElementScroll(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public static void browserBack(String url)
	{
		driver.navigate().to(url);
	}

}
