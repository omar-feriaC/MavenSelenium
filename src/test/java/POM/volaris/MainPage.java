package POM.volaris;

import com.aventstack.extentreports.Status;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import selenium.ClsBrowser;
import selenium.ClsReport;

public class MainPage extends ClsBrowser {

	// Locators
	public By currencyButton = By.xpath("//li[@id='mcp']/a");
	public By currencySelect = By.xpath("//select[@id='mat-input-8']");
	public By currencyApply = By.xpath("//mat-dialog-container//button");

	public By roundTripCheck = By.xpath("//button[@id='mat-button-toggle-1-button']");

	public By searchButton = By
			.xpath("//button[@class='btn btn-large col-12 search-btn mat-flat-button mat-button-base mat-primary']");
	public By origButton = By.xpath("//div[@class='col btnSearch SearchOrigin']/a");
	public By origSearch = By.xpath("//input[@id='fnameOrigin']");
	public By destButton = By.xpath("//div[@class='col btnSearch SearchDestination']/a");
	public By destSearch = By.xpath("//input[@id='fnameDestination']");
	public By searchOptions = By.xpath("//mat-dialog-container//ul/li");
	public By closeSearchBox = By.xpath("//mat-icon");

	public By datePanel = By.xpath("//mat-dialog-container");
	public By dateMontSelector = By.xpath("//select[@class='monthselect'][1]");
	public By dateYearSelector = By.xpath("//select[@class='yearselect'][1]");
	public String dateSelectorPrefix = "//td[contains(concat(' ',normalize-space(@class),' '),'";
	public String dateSelectorPosfix = "')]";
	public String dateSelected = "//td[contains(concat(' ',normalize-space(@class),' '),'active','start-date')]";
	public By confirmDate = By
			.xpath("//button[@class='btn-calendar d-none d-md-block mat-flat-button mat-button-base mat-secondary']");

	public By searchFlight = By.xpath("  //div[@class='col searchFlight']/button ");

	/**
	 * Wait for main page to load
	 */
	public void waitMainPage() {
		WaitForLoad();
		WaitForElementClickable(searchButton);
	}

	/**
	 * Set the currency for the search and the page
	 *
	 * @param currencyIndex Index given by the order of currencySelect 0: MXN, 1:
	 *                      USD, 2: CRC, 3: AUD, 4: CAD, 5: EUR ... etc.
	 */
	public void setCurrency(int currencyIndex) {
		Click(currencyButton);
		SelectItem(currencySelect, "byindex", String.valueOf(currencyIndex));
		String currencyLegend = getGetWebElement(currencySelect).getText();
		Click(currencyApply);
		ClsReport.fnLog(Status.INFO, "Step: set currency to: " + currencyLegend, false);
	}

	/**
	 * Click on the round-trip button
	 */
	public void checkRoundTrip() {
		Click(roundTripCheck);
		ClsReport.fnLog(Status.INFO, "Step: set round-trip", false);
	}

	/**
	 * Given an origin and a destination select them from the menus
	 * 
	 * @param orig Origin airport to fly from
	 * @param dest Destination Airport to fly to
	 * @throws InterruptedException
	 */
	public void selectDestination(String orig, String dest) throws InterruptedException {
		Click(origButton);
		// waitForList();
		threadWait(1);
		SendKeys(origSearch, orig);
		ClsReport.fnLog(Status.INFO, "Step - Origin: " + orig + " searched", true);
		Click(searchOptions);
		SendKeys(destSearch, dest);
		ClsReport.fnLog(Status.INFO, "Step - Destination: " + dest + " searched", true);
		Click(searchOptions);
	}

	/**
	 * Given a start date and a return date select it from the menus
	 * 
	 * @param dStart Day to start from
	 * @param mStart Month to start from
	 * @param yStart Year to start from
	 * @param dEnd   day of the return
	 * @param mEnd   mont of the resturn
	 * @param yEnd   year of the return
	 * @throws InterruptedException
	 */
	public void selectDates(int dStart, int mStart, int yStart, int dEnd, int mEnd, int yEnd)
			throws InterruptedException {
		// start
		String startDateLocator = String.format("%s%s%d%d%02d%s", dateSelectorPrefix, "datecell-", yStart, mStart, dStart,
				dateSelectorPosfix);
		WaitForElement(datePanel);
		SelectItem(dateMontSelector, "BYVALUE", String.valueOf(mStart - 1));
		ClsReport.fnLog(Status.INFO, "Step - Select Start date", true);
		Click(startDateLocator);
		// Return
		String endDateLocator = String.format("%s%s%d%d%02d%s", dateSelectorPrefix, "datecell-", yEnd, mEnd, dEnd,
				dateSelectorPosfix);
		SelectItem(dateMontSelector, "BYVALUE", String.valueOf(mEnd - 1));
		ClsReport.fnLog(Status.INFO, "Step - Select End date", true);
		Click(endDateLocator);
		// Confirm
		Click(confirmDate);
	}

	/**
	 * Given the inputed data press the search button
	 */
	public void clickSearch() {
		Click(searchFlight);
		ClsReport.fnLog(Status.INFO, "Step - Search Flights", false);
	}

	public void waitForList() throws InterruptedException {
		// TODO implement better wait
		// WebDriverWait loading = new WebDriverWait(objDriver, 10);
		// loading.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchOptions,
		// 0));
	}

	public void threadWait(int time) throws InterruptedException {
		// TODO bad wait
		Thread.sleep(1000 * time);
	}

}
