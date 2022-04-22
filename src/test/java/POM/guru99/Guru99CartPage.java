package POM.guru99;

import java.util.List;

import com.aventstack.extentreports.Status;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import selenium.ClsBrowser;
import selenium.ClsReport;

public class Guru99CartPage extends ClsBrowser {

	public String cartElements = "//table[@id='shopping-cart-table']//td[@class='product-cart-info']/h2";
	public By checkOutButton = By.xpath("//button[@title='Proceed to Checkout']");
	public By continueAsGuestBtn = By.xpath("//button[@id='onepage-guest-register-button']");

	/**
	 * Waits for page load and checks if current page is cart page
	 * 
	 * @return Boolean If the current page is the cart page
	 */
	public boolean isOnCart() {
		ClsReport.fnLog(Status.INFO, "Step - Assert if Cart has loaded", false);
		WaitForLoad();
		if (getGetWebElement(By.xpath("//h1")).getText().equalsIgnoreCase("shopping cart")) {
			ClsReport.fnLog(Status.PASS, "Step - Cart is loaded", false);
			return true;
		}
		return false;
	}

	/**
	 * Gets the element on the cart and chcks if the given element is present
	 * 
	 * @param element Element to search on the cart
	 * @return Boolean If the element is present
	 */
	public boolean isElementOnCart(String element) {
		List<WebElement> cartListing = getWebList(cartElements);
		for (WebElement cartElement : cartListing) {
			if (cartElement.getText().equalsIgnoreCase(element))
				return true;
		}
		return false;
	}

	/**
	 * Step to go to the checkout page
	 */
	public void goToCheckout() {
		ClsReport.fnLog(Status.INFO, "Step - Click on checkout", false);
		Click(checkOutButton);
		ClsReport.fnLog(Status.INFO, "Step - Click On continue as a Guest", true);
		Click(continueAsGuestBtn);
	}

}
