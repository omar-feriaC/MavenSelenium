package TestCases;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.BeforeClass;
import org.junit.rules.TestName;

import com.aventstack.extentreports.Status;
import selenium.ClsBrowser;
import selenium.ClsReport;

import POM.guru99.*;

public class Guru99TestCase extends ClsBrowser {

	private Guru99TvPage objGuruPage;
	private Guru99CartPage objGuruCart;
	private Guru99InputData objGuruInput;
	private Guru99Checkout objGuruCheckout;
	private Guru99Account objGuruAccount;

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
		URL = "http://live.guru99.com/index.php/tv.html";
		NavigateToUrl(URL);
		objGuruPage = new Guru99TvPage();
		objGuruCart = new Guru99CartPage();
		objGuruInput = new Guru99InputData();
		objGuruCheckout = new Guru99Checkout();
		objGuruAccount = new Guru99Account();
	}

	@Test
	public void AddLGToCart() {
		try {
			ClsReport.objTest = ClsReport.objExtent.createTest("Guru99 Add LG TV to Cart");
			// before step: Open "http://live.guru99.com/index.php/tv.html"
			String lgTv = "LG LCD";
			// Step: click on the LG LCD on the add button
			// Assert LG LCD is present
			String lgTvLocator = objGuruPage.getTvElement(lgTv);
			ClsReport.fnLog(Status.PASS, "Step - " + lgTv + " Found", true);
			// Assert LG LCD has add to cart button
			objGuruPage.addToCart(lgTvLocator);
			if (!objGuruCart.isOnCart())
				ClsReport.fnLog(Status.FAIL, "FAIL - Cart didn't load", true);
			ClsReport.fnLog(Status.INFO, "Step - Assert if "+lgTv+" is on the cart", false);
			objGuruCart.isElementOnCart(lgTv);
			ClsReport.fnLog(Status.PASS, "Final - " + lgTv + " added to the cart", true);
		} catch (Exception e) {
			ClsReport.fnLog(Status.FAIL,
					"Test:" + TC_Name.getMethodName() + "had an exception. \n Exception: " + e.getMessage(), false);
		}
	}

	@Test
	public void ShopSamsung() {
		try {
			ClsReport.objTest = ClsReport.objExtent.createTest("Guru99 Shop for Samsung Tv");
			// before step: Open "http://live.guru99.com/index.php/tv.htmdata-target-element="#header-account"l"
			String smTv = "Samsung LCD";
			// Step: click on the LG LCD on the add button
			// Assert LG LCD is present
			String smTvLocator = objGuruPage.getTvElement(smTv);
			ClsReport.fnLog(Status.PASS, "Step - " + smTv + " Found", true);
			// Assert LG LCD has add to cart button
			objGuruPage.addToCart(smTvLocator);
			if (!objGuruCart.isOnCart())
				ClsReport.fnLog(Status.FAIL, "FAIL - Cart didn't load", true);
			ClsReport.fnLog(Status.INFO, "Step - Assert if "+smTv+" is on the cart", false);
			if(!objGuruCart.isElementOnCart(smTv))
 				ClsReport.fnLog(Status.FAIL, "FAIL - Cart has wrong element", true);
			else
				ClsReport.fnLog(Status.PASS, "Step - " + smTv + " added to the cart", false);
			objGuruCart.goToCheckout();
			objGuruInput.inputOrderDetails();
			ClsReport.fnLog(Status.INFO, "Step - Continue CheckOut", false);
			objGuruCheckout.finishCheckout();
			objGuruCheckout.isCheckoutSuccesfull();
			ClsReport.fnLog(Status.PASS, "Final - CheckOut Done", true);

		} catch (Exception e) {
			ClsReport.fnLog(Status.FAIL,
					"Test:" + TC_Name.getMethodName() + "had an exception. \n Exception: " + e.getMessage(), false);
		}
	}

	@Test
	public void CreateAccount() {
		try {
			ClsReport.objTest = ClsReport.objExtent.createTest("Guru99 Register New User");
			// before step: Open "http://live.gAccounturu99.com/index.php/tv.html"
			// go to register
			objGuruAccount.goToRegister();
			WaitForLoad();
			ClsReport.fnLog(Status.PASS, "Step - Register User page loaded", true);
			// Input data
			objGuruInput.inputNewUserInfo();
			WaitForLoad();
			ClsReport.fnLog(Status.PASS, "Final New User Created", true);

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
