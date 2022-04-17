package TestCases;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.BeforeClass;
import org.junit.rules.TestName;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import selenium.ClsBrowser;
import selenium.ClsReport;

import POM.amazon.*;

public class AmazonTestCase extends ClsBrowser {
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
		OpenBrowser();
	}

	@Test
	public void PromotionsTC() {
		try {
			// Go to https://www.amazon.com.mx/ and start a new valid session.
			ClsReport.objTest = ClsReport.objExtent.createTest("Third Test");
			URL = "https://www.amazon.com.mx/";
			NavigateToUrl(URL);
			AmazonPromotionsPage objAmzPage = new AmazonPromotionsPage();
			// Go to Promotion/Promociones Link and make sure Promotion/Promociones page is
			// loaded as expected.
			objAmzPage.goToPromotions();
			// * wait for first product to load
			WaitForElementClickable(objAmzPage.productImage);
			ClsReport.fnLog(Status.PASS, "Promotions Page loaded", true);
			// Select the flash Deal /"Oferta Relámpago" and wait to apply the filter.
			objAmzPage.goTolightningDeals();
			// * wait for first product to load
			WaitForElementClickable(objAmzPage.productImage);
			ClsReport.fnLog(Status.PASS, "Flash Deals loaded", true);
			// Obtain a list with all the products provided by Amazon and print it in the
			// report.
			// Make sure to print only the product name.
			String strProducts = "<ol>\n";
			for (int i = 0; i < objAmzPage.getNumberOfPages(); i++) {
				WaitForElementClickable(objAmzPage.productImage);
				strProducts += objAmzPage.getProductTitles(objAmzPage.getWebList(objAmzPage.productText));
				objAmzPage.clickNextPagination();
			}
			strProducts +="</ol>";
			ClsReport.fnLog(Status.PASS, "Listed all 'Promotions'/'FlashDeal' Products\n\n"+strProducts, true);
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
