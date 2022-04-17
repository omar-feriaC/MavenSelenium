package POM.amazon;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import selenium.ClsBrowser;

public class AmazonPromotionsPage extends ClsBrowser {
	// Locators
	/*
	 * @FindBy(xpath =
	 * '//a[@class="LinkFilterOption-module__linkFilterOption_k3Xp4bgxEaBmcgTA0UAxc"
	 * ]/span') public WebElement ElementUno;
	 */
	public By productImage = By.xpath(
			"//*[@class='DealGridItem-module__dealItem_2X_WLYkJ3-dM0LtXI9THcu DealGridItem-module__withBorders_2jNNLI6U1oDls7Ten3Dttl DealGridItem-module__withoutActionButton_2OI8DAanWNRCagYDL2iIqN']//img");

	public By productText = By.xpath("//*[@class='DealContent-module__truncate_sWbxETx42ZPStTc9jwySW']");

	public By nextPagination = By.className("a-last");

	public By pagination = By.className("a-pagination");

	// Methods

	/**
	 * Go to the Promotions page
	 */
	public void goToPromotions() {
		WaitForLoad();
		LinkText("Promociones");
	}

	/**
	 * Go to the Lightning deals
	 */
	public void goTolightningDeals() {
		WaitForLoad();
		LinkText("Oferta relámpago");
	}

	/**
	 * On the Promotions page, click the "next" button at the pagination
	 */
	public void clickNextPagination() {
		WaitForElementClickable(nextPagination);
		getGetWebElement(nextPagination).click();
	}

	/**
	* Get the number of diferent pages 
	* @return The number of the last page available
	*/
	public int getNumberOfPages() {
		String pages = "";
		List<WebElement> pButtons = getGetWebElement(pagination).findElements(By.tagName("li"));
		pages = pButtons.get(pButtons.size() - 2).getText();
		return Integer.parseInt(pages);
	}

	/**
	 * Given a list of web Elements return their contents wihtin a
	 * <li>htmlElement
	 * 
	 * @param products {@link List} of {@link WebElement} to get titles from
	 * @return String of Titltes wrapped inside a
	 *         <li>htmlElement
	 */
	public String getProductTitles(List<WebElement> products) {
		String strProducts = "";
		for (WebElement product : products) {
			strProducts += "<li>" + product.getText() + "</li>\n";
		}
		return strProducts;
	}
}
