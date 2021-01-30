package com.mercedesbenz.pageobjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/* This function creates object repository by locating elements either by xpath or cssSelector using @Findby annotation*/

public class AddtoCart {

	public static Logger log = LogManager.getLogger(AddtoCart.class.getName());
	WebDriver driver;

	public AddtoCart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(text(),'Close')]")
	WebElement Closebtn;

	@FindBy(xpath = "//a[@class='dcp-nav__link ng-binding']")
	WebElement Collectionandaccessories;

	@FindBy(xpath = "//a[contains(text(),'All products')]")
	WebElement Products;

	@FindBy(xpath = "(//*[contains(text(),'Model cars')])[1]")
	WebElement Modelcars;

	@FindBy(xpath = "(//div[@class='utils-product-tile-image-inner'])[2]")
	WebElement SLCoupeW;

	@FindBy(xpath = "//*[contains(text(),'Add to basket')]")
	WebElement AddTobasket;

	@FindBy(xpath = "//button[contains(text(),' Continue shopping')]")
	WebElement ContinueShopping;

	@FindBy(xpath = "//button[contains(text(),' Go to shopping basket')]")
	WebElement GoToBasket;

	@FindBy(xpath = "//button[contains(text(),'Continue to address and delivery')]")
	WebElement ContinueAddressandDelivery;

	@FindBy(xpath = "//input[@name='userEmail']")
	WebElement Username;

	@FindBy(xpath = "//button[contains(text(),'Place order as guest')]")
	WebElement Guest;

	// Delivery Address

	@FindBy(xpath = "//*[contains(text(),'Mr')]")
	WebElement Salutation;

	@FindBy(xpath = "//input[@name='co_payment_address-firstName']")
	WebElement FirstName;

	@FindBy(xpath = "//input[@name='co_payment_address-lastName']")
	WebElement LastName;

	@FindBy(xpath = "//input[@name='co_payment_address-line2']")
	WebElement Number;

	@FindBy(xpath = "//input[@name='co_payment_address-line1']")
	WebElement Street;

	@FindBy(xpath = "//input[@name='co_payment_address-town']")
	WebElement TownORCity;

	@FindBy(xpath = "//input[@name='co_payment_address-postalCode']")
	WebElement PostalCode;

	@FindBy(xpath = "(//div[@class='wb-e-radio-1__wrapper'])[1]")
	WebElement deliveryaddress;

	@FindBy(xpath = "//button[contains(text(),'Continue to payment type')]")
	WebElement ContinuePayment;

	// Payment methods
	@FindBy(xpath = ".//*[@id='dcp-co-payment-modes-container']/div[1]/div/label")
	WebElement CreditCard;

	@FindBy(xpath = ".//*[@id='dcp-co-payment-modes-container']/div[2]/div/label")
	WebElement Paypal;

	@FindBys(@FindBy(xpath = "//div[@class='wb-e-radio-1__wrapper dcp-co-payment-modes__sub-items-radio ng-scope']/label"))
	List<WebElement> Cards;

	@FindBy(xpath = "//button[contains(text(),'Continue to verification and order placement')]")
	WebElement OrderPlacement;

	@FindBy(css = "label[class='wb-e-check-1__label']")
	WebElement TermsAndConditions;

	@FindBy(css = "button[class='wb-e-btn-1 dcp-co-func-footer__cta-primary ng-binding ng-scope']")

	WebElement OrderNow;

	/* This function operates on web-elements based on the functionality */

	public void verifyAddToCart() throws InterruptedException {
		try {
			Closebtn.click(); // closes cookies window
			waitForVisibility(Collectionandaccessories);
			Collectionandaccessories.click();
			Products.click();
			log.info("All Product displayed");
			waitForVisibility(Modelcars);
			getScroll(600);
			Modelcars.click();
			log.info("All Modelcars displayed ");
			waitForVisibility(SLCoupeW);
			SLCoupeW.click();
			log.info("SLCoupe W Model selected successfully");
			getScroll(600);
			AddTobasket.click();
			log.info("SLCoupe W Model added to basket successfully");
			waitForVisibility(GoToBasket);
			GoToBasket.click();
			waitForVisibility(ContinueAddressandDelivery);
			ContinueAddressandDelivery.click();
			waitForVisibility(Username);
			Username.click();
			Username.sendKeys("chandrashetty@gmail.com");
			Guest.click();
			waitForVisibility(Salutation);
			Salutation.click();
			FirstName.click();
			FirstName.sendKeys("Chandra");
			LastName.sendKeys("Shetty");
			Number.click();
			Number.sendKeys("7709825048");
			Street.sendKeys("OakleyRoad, Salisbury");
			TownORCity.sendKeys("Wiltshire");
			PostalCode.sendKeys("SP2 0FL");
			log.info("User details entered successfully");
			Thread.sleep(3000);
			getScroll(600);
			ContinuePayment.click();
			waitForVisibility(CreditCard);
			CreditCard.click();
			CardSelection();
			log.info("Payment details entered successfully");
			waitForVisibility(OrderPlacement);
			OrderPlacement.click();
			waitForVisibility(TermsAndConditions);
			TermsAndConditions.click();
			waitForVisibility(OrderNow);
			OrderNow.click();
			log.info("Order placed successfully");
		} catch (Exception e) {
			log.error(e);
		}

	}

	// Explicit wait for synchronize the script execution with application under
	// test

	private void waitForVisibility(WebElement element) throws Error {
		new WebDriverWait(driver, 3000).until(ExpectedConditions.visibilityOf(element));
	}

	// Function to scroll the web page
	public void getScroll(int scroll) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,scroll)");
	}

	// Card selection functionality
	public void CardSelection() {

		List<WebElement> PaymentCards = Cards;
		int count = PaymentCards.size();
		for (int i = 0; i < count; i++) {
			String text = PaymentCards.get(i).getText();
			if (text.equalsIgnoreCase("VISA")) {
				PaymentCards.get(i).click();
				break;
			}
		}
	}
}
