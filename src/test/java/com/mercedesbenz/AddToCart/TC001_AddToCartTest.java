package com.mercedesbenz.AddToCart;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mercedesbenz.baseproperties.BaseClass;
import com.mercedesbenz.pageobjects.AddtoCart;

public class TC001_AddToCartTest extends BaseClass {

	public static Logger log = LogManager.getLogger(TC001_AddToCartTest.class.getName());
	AddtoCart add;

	@BeforeTest
	public void BrowserSetUp() throws IOException {
		InvokeBrowser();
		getUrl();
		log.info("https://shop.mercedes-benz.com/en-gb/collection/ - Launched successfully");
	}

	@Test // TC
	public void AddToCart() throws InterruptedException {
		add = new AddtoCart(driver);
		log.info("Test case execution started");
		add.verifyAddToCart();
		log.info("Test case execution completed");
	}

	@AfterTest
	public void tearDown() {
		driver.close();
		log.info("Browser is closed");
	}
}
