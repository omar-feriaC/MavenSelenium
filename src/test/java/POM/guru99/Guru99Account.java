package POM.guru99;

import java.util.List;

import com.aventstack.extentreports.Status;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import selenium.ClsBrowser;
import selenium.ClsReport;

public class Guru99Account extends ClsBrowser {

	public By accountButton = By.xpath("//a[@data-target-element='#header-account']");
	public By registerButton = By.xpath("//a[@title='Register']");

	/**
	 * The step to go to the new user page
	 */
	public void goToRegister() {
		ClsReport.fnLog(Status.INFO, "Step - Click on account button", false);
		Click(accountButton);
		ClsReport.fnLog(Status.INFO, "Step - Going to Register New User page", false);
		Click(registerButton);
	}

}
