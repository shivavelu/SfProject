package com.sfbay.uibase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.Loader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.sfbay.api.PropertyFileUtil;
import com.sfbay.util.TestUtil;

public class TestBase extends PropertyFileUtil {

	protected static WebDriver driver;
	protected static Properties prop;
	protected static Properties ntprop;
	protected static EventFiringWebDriver e_driver;
	protected static Logger log;

	public TestBase() {
		log = Logger.getLogger(this.getClass());
		try {
			prop = new Properties();
			File f = new File("./resources/config.properties");
			FileInputStream ip = new FileInputStream(f);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			log.info("config property missing");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		

	}

	public static void initialization()
	{
		String browserName = prop.getProperty("browser");

		try {
			if (browserName.equals("chrome")) 
			{
				
				String os= System.getProperty("os.name").toLowerCase();				
				if(os.contains("mac"))
				{
				System.setProperty("webdriver.chrome.driver", "./resources/chromedriver");
				}
				else
				{
					System.setProperty("webdriver.chrome.driver", "./resources/chromedriver.exe");
				}
				
				driver = new ChromeDriver();
						
			} 
			else if (browserName.equals("FF")) {
				System.setProperty("webdriver.firefox.marionette", "./resources/geckodriver.exe");
				driver = new FirefoxDriver();
			}
		} catch (Exception e) {
			log.info("driver is not launched, file or settings issue");
			e.printStackTrace();
			
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

	}

	
}