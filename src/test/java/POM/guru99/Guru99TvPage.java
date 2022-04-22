package POM.guru99;

import java.util.List;

import com.aventstack.extentreports.Status;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import selenium.ClsBrowser;
import selenium.ClsReport;

public class Guru99TvPage extends ClsBrowser {

	public String tvListing = "//ul[contains(concat(' ',normalize-space(@class),' '),'products-grid')]//li[@class='item last']";
	public By tvName = By.xpath(".//h2[@class='product-name']");
	public String tvAddCart = "//div[@class='actions']/button";

	/**
	 * Given an tv title (listing name) searches for it and returns its xpathLocator
	 *
	 * @param tvTitle Title of the tv to be searched
	 * @return XPath String to the Tv webElement
	 */
	public String getTvElement(String tvTitle) {
		List<WebElement> tvs = getWebList(tvListing);
		int counter = 1;
		for (WebElement tv : tvs) {
			WebElement title = tv.findElement(tvName);
			if (title.getText().equalsIgnoreCase(tvTitle)) {
				ClsReport.fnLog(Status.INFO, "Step - Element: \"" + tvTitle + "\" found", false);
				return tvListing + "[" + counter + "]";
			}
			counter++;
		}
		return null;
	}

	/**
	 * Given a webElement locator for a tv element it clicks on the add cart
	 *
	 * @param tvLocator Xpath to the Tv Element
	 */
	public void addToCart(String tvLocator) {
		/*
		 * TODO verify it selects the appropiate tv
		 */
		WebElement tvAddButton = getGetWebElement(tvLocator + tvAddCart);
		if (tvAddButton.getText().equalsIgnoreCase("ADD TO CART")) {
			ClsReport.fnLog(Status.INFO, "Step - Element: tvElement has 'Add to Cart' Button", false);
			Click(tvAddCart);
			ClsReport.fnLog(Status.PASS, "Step - Element: 'Add to Cart' Button Succesfully clicked", false);
		}
	}

}
