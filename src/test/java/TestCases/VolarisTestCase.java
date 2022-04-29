package TestCases;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.rules.TestName;

import com.aventstack.extentreports.Status;
import selenium.ClsBrowser;
import selenium.ClsReport;

import POM.volaris.*;

public class VolarisTestCase extends ClsBrowser {

	// Pages
	private MainPage mPage;
	private FlightsPage fPage;

	@Rule
	public TestName TC_Name = new TestName();
	public String URL;

	@BeforeClass
	public static void beforeClass() {
		ClsReport.fnSetupReport();
	}

	@Before
	public void setup() {
		ClsBrowser.BrowserName = "Chrome";
		// Fake windows for The domain 
		ClsBrowser.chOpt.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.26 Safari/537.36");
		mPage = new MainPage();
		fPage = new FlightsPage();
		OpenBrowser();
	}

	@Test
	public void FlightGDLtoCUN() {
		try {
			ClsReport.objTest = ClsReport.objExtent.createTest("Book flight");
			// step: Open "Go to volaris.com"
			NavigateToUrl("https://www.volaris.com");
			mPage.waitMainPage();
			ClsReport.fnLog(Status.PASS, "Step - Main page Loaded", true);
			// step: Config currency and round trip 
			mPage.checkRoundTrip();
			mPage.setCurrency(0);
			// step: Search flight (GDL to CUN)
				// step: Select origin dest
			mPage.selectDestination("GDL", "CUN");
				// step: Select dates
			mPage.selectDates(29, 10, 2022, 4, 11, 2022);
				// step: Select round trip
			ClsReport.fnLog(Status.PASS, "Step - Trip selected", true);
				// step: Search
			mPage.clickSearch();
			// To flights page
			fPage.waitFlightsPage();
			ClsReport.fnLog(Status.PASS, "Step - Flights page loaded", true);
			fPage.assertFlights();
			ClsReport.fnLog(Status.PASS, "Step - Flights present", true);
			// Assert if flight loaded correspond to flights selected
			fPage.verifyFlight("Guadalajara a Cancún");
			// Choose cheepest flight if any
			fPage.selectCheepestFlight();
			ClsReport.fnLog(Status.PASS, "Step - Selected cheepest departure", true);
			// Wait
			fPage.threadWait(3);
			// Continue to return flight
			fPage.verifyFlight("Cancún a Guadalajara");
			// Choose cheepest flight if any
			fPage.selectCheepestFlight();
			ClsReport.fnLog(Status.PASS, "Step - Selected cheepest return", true);
			// Finish selection 
			fPage.continueLuggage(false);
			fPage.checkPrice(1500);
			ClsReport.fnLog(Status.PASS, "Final - Flight booked", true);
		} catch (Exception e) {
			ClsReport.fnLog(Status.FAIL,
					"Test:" + TC_Name.getMethodName() + "had an exception. \n Exception: " + e.getMessage(), false);
		} 
	}

	@After
	public void tearDown() {
		CloseBrowser();
	}

	@AfterClass
	public static void afterClass() {
		ClsReport.fnCloseReport();
	}
}
