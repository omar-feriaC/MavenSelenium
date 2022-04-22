package POM.guru99;

import java.util.List;

import com.aventstack.extentreports.Status;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import selenium.ClsBrowser;
import selenium.ClsReport;

public class Guru99Checkout extends ClsBrowser {

	public By continueShippingRateBtn = By.xpath("//form[@id='co-shipping-method-form']//button");
	public By radioCheckMoney = By.xpath("//input[@id='p_method_checkmo']");
	public By savePaymentBtn = By.xpath("//button[@onclick='payment.save()']");
	public By finishCheckoutBtn = By.xpath("//button[@title='Place Order']");

	/**
	 * Step to got through the checkout buttons
	 */
	public void finishCheckout() {
		ClsReport.fnLog(Status.INFO, "Step - Wait for shipping rate", true);
		Click(continueShippingRateBtn);
		ClsReport.fnLog(Status.INFO, "Step - Select Check/Money ", false);
		Click(radioCheckMoney);
		ClsReport.fnLog(Status.INFO, "Step - Save Payment", true);
		Click(savePaymentBtn);
		WaitForElementClickable(finishCheckoutBtn);
		ClsReport.fnLog(Status.INFO, "Step - Place Order", true);
		Click(finishCheckoutBtn);
	}

	/**
	 * Waits for the H1 element of confirmation to be loaded
	 */
	public void isCheckoutSuccesfull() {
		ClsReport.fnLog(Status.INFO, "Step - Wait For Succes Message", false);
		GetFluentWait("//h1[text()='Your order has been received.']");
	}

}
