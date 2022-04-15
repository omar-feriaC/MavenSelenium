package TestCases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.BeforeClass;
import org.junit.rules.TestName;

import com.aventstack.extentreports.Status;
import POM.at.*;
import selenium.ClsBrowser;
import selenium.ClsReport;

public class TestCase_Exec extends ClsBrowser {
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
	public void FirstTC() {
		try {
			ClsReport.objTest = ClsReport.objExtent.createTest("First Test");
			URL = "https://positionsapp-uat.azurewebsites.net/#";
			NavigateToUrl(URL);
			WaitForLoad();
			ClsReport.fnLog(Status.PASS, "Loaded page", false);
			AtLoginPage objLogin = new AtLoginPage();
			objLogin.enterCredential();
			ClsReport.fnLog(Status.PASS, "Entered Credential", false);
			objLogin.startSession();
			ClsReport.fnLog(Status.PASS, "Started Session", false);
			objLogin.keepSessionDialog();
			ClsReport.fnLog(Status.PASS, "Keep session alert", false);
			objLogin.verifyActiveSession();
			WaitForLoad();
			ClsReport.fnLog(Status.PASS, "Logged-in and loaded the page", true);
		} catch (Exception e) {
			ClsReport.fnLog(Status.FAIL,
					"The: " + TC_Name.getMethodName() + " was not executed successfully. \n Exception: " + e.getMessage(), false);
		}
	}

	@Test
	public void SecondTC() {
		try {
			ClsReport.objTest = ClsReport.objExtent.createTest("Second Test");
			URL = "https://positionsapp-uat.azurewebsites.net/#";
			NavigateToUrl(URL);
			WaitForLoad();
			ClsReport.fnLog(Status.PASS, "Loaded page", false);
			AtLoginPage objLogin = new AtLoginPage();
			objLogin.enterCredential();
			ClsReport.fnLog(Status.PASS, "Entered Credential", false);
			objLogin.startSession();
			ClsReport.fnLog(Status.PASS, "Started Session", false);
			objLogin.keepSessionDialog();
			ClsReport.fnLog(Status.PASS, "Keep session alert", false);
			objLogin.verifyActiveSession();
			WaitForLoad();
			ClsReport.fnLog(Status.PASS, "Logged-in and loaded the page", true);
		} catch (Exception e) {
			ClsReport.fnLog(Status.FAIL,
					"The: " + TC_Name.getMethodName() + " was not executed successfully. \n Exception: " + e.getMessage(), false);
		}
	}

	@Test
	public void ThirdTC() {
		try {
			ClsReport.objTest = ClsReport.objExtent.createTest("Third Test");
			URL = "https://positionsapp-uat.azurewebsites.net/#";
			NavigateToUrl(URL);
			WaitForLoad();
			ClsReport.fnLog(Status.PASS, "Loaded page", false);
			AtLoginPage objLogin = new AtLoginPage();
			objLogin.enterCredential();
			ClsReport.fnLog(Status.PASS, "Entered Credential", false);
			objLogin.startSession();
			ClsReport.fnLog(Status.PASS, "Started Session", false);
			objLogin.keepSessionDialog();
			ClsReport.fnLog(Status.PASS, "Keep session alert", false);
			objLogin.verifyActiveSession();
			WaitForLoad();
			ClsReport.fnLog(Status.PASS, "Logged-in and loaded the page", true);
		} catch (Exception e) {
			ClsReport.fnLog(Status.FAIL,
					"The: " + TC_Name.getMethodName() + " was not executed successfully. \n Exception: " + e.getMessage(), false);
		}
	}

	@After
	public void tearDown() {
		CloseBrowser();
		ClsReport.fnCloseReport();
	}

}
