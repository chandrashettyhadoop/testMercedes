package com.mercedesbenz.baseproperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseClass {

	public static WebDriver driver = null;
	public Properties prop = new Properties();

	/*
	 * This function reads the browser name from configuration file and based on the
	 * browser create the instance of it
	 */

	public void InvokeBrowser() throws IOException {
		FileInputStream fis = new FileInputStream(
				"src/main/java/com/MercedesBenz/configuration/ConfigurationFile.properties");
		prop.load(fis);

		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/main/java/com/MercedesBenz/WebDrivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src/main/java/com/MercedesBenz/WebDrivers/geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			System.setProperty("webdriver.ie.driver", "src/main/java/com/MercedesBenz/WebDrivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
	}

	/*
	 * This function gets the url "https://shop.mercedes-benz.com/en-gb/collection"
	 * from configuration file and launches it
	 */

	public void getUrl() {
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

}
