package POM.volaris;

import java.util.List;

import com.aventstack.extentreports.Status;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.ClsBrowser;
import selenium.ClsReport;

public class FlightsPage extends ClsBrowser {

	// Locators
	public By flightsTitle = By.xpath("//h1[text()='Elige tu vuelo de salida de']");
	public By flightLocator = By.xpath("//div[contains(concat(' ',normalize-space(@class),' '),'flightItem')]");

	public By orderFilter = By.xpath("//div[@class='sorting']//select");
	public By flightRegularPrice = By.xpath(".//a[@class='panel-open ng-star-inserted']");
	public By firstFlightPackage = By.xpath("//mbs-flight-fares//mat-card");

	public By flightLegendBanner = By.xpath("//h4[@class='route-info-heading']");

	public By extraLuggageCheck = By
			.xpath("//div[@class='baggage-modal-container ng-star-inserted']//input[@id='mat-checkbox-2-input']");
	public By confirmLuggage = By.xpath(
			"//div[@class='baggage-modal-container ng-star-inserted']//button[@class='btn btn-large mat-flat-button mat-button-base mat-primary']");

	/**
	 * Wait for the FLights page to load
	 */
	public void waitFlightsPage() {
		GetFluentWait(flightsTitle);
		WaitForLoad();
	}

	/**
	 * Assert if flights load
	 */
	public void assertFlights() {
		List<WebElement> flights = getWebList(flightLocator);
		if (flights.isEmpty()) {
			ClsReport.fnLog(Status.FAIL, "No flights found", true);
			throw new ElementNotVisibleException("No flights were found");
		}
	}

	/**
	 * From the displayed flights filtter by cheepest and select it
	 */
	public void selectCheepestFlight() {
		SelectItem(orderFilter, "BYVALUE", "PriceLowToHigh");
		WebElement flight = getGetWebElement(flightLocator);
		flight.findElement(flightRegularPrice).click();
		Click(firstFlightPackage);
	}

	/**
	 * Given a string made from "origin to destination" verify if displayed flights
	 * correspond
	 * 
	 * @param flightLegend String made "origin to destination" for the given flight
	 *                     (Language deppendent)
	 * @throws Exception Exception if incorrect flight os found
	 */
	public void verifyFlight(String flightLegend) throws Exception {
		String flightHint = getGetWebElement(flightLegendBanner).getText().strip().replace("\n", " ");
		System.out.println(flightHint);
		if (!flightLegend.equalsIgnoreCase(flightHint)) {
			ClsReport.fnLog(Status.FAIL, "Incorrect Flight", true);
			throw new Exception("Incorrect Flight");
		}
	}

	/**
	 * At the luggage modal if ExtraLuggage check and continue or otherwise
	 * 
	 * @param isExtraLuggage if add the extra luggage
	 */
	public void continueLuggage(boolean isExtraLuggage) {
		if (isExtraLuggage) {
			Click(extraLuggageCheck);
			ClsReport.fnLog(Status.INFO, "Step - Added extra luggage", true);
		}
		ClsReport.fnLog(Status.INFO, "Step - Continue from extra luggage screen", false);
		Click(confirmLuggage);
	}

	public void threadWait(int time) throws InterruptedException {
		// TODO bad wait
		Thread.sleep(1000 * time);
	}

}
