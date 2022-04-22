package POM.guru99;

import java.util.List;
import java.util.Random;

import com.aventstack.extentreports.Status;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import selenium.ClsBrowser;
import selenium.ClsReport;

public class Guru99InputData extends ClsBrowser {

	private String firstName = "Jhon";
	private String lastName = "Doe";
	private String company = "AT";
	private String email = "jhondoe"+(int)((Math.random()+1)*10000)+"@email.com";
	private String addressline1 = "Calle Importante No.1 Del. Centro";
	private String country = "Mexico";
	private String city = "City";
	private String postalCode = "76269";
	private String telephone = "5543889618";
	private String password = "1234SuperSecure";

	/**
	 * iT fills the input for the billing form
	*/
	public void inputOrderDetails(){
		getGetWebElement("//input[@id='billing:firstname']").sendKeys(firstName);
		getGetWebElement("//input[@id='billing:lastname']").sendKeys(lastName);
		getGetWebElement("//input[@id='billing:company']").sendKeys(company);
		ClsReport.fnLog(Status.INFO, "Data - PsudoRandomized Email: "+email, false);
		getGetWebElement("//input[@id='billing:email']").sendKeys(email);
		getGetWebElement("//input[@id='billing:street1']").sendKeys(addressline1);
		SelectItem(By.xpath("//select[@id='billing:country_id']"), "bytext", country);
		getGetWebElement("//input[@id='billing:city']").sendKeys(city);
		getGetWebElement("//input[@id='billing:postcode']").sendKeys(postalCode);
		getGetWebElement("//input[@id='billing:telephone']").sendKeys(telephone);
		ClsReport.fnLog(Status.PASS, "Step - Billing information Inputted", true);
		Click("//button[@title='Continue']");
	}

	/**
	 * iT fills the input for the new user form
	*/
	public void inputNewUserInfo(){
		getGetWebElement("//input[@id='firstname']").sendKeys(firstName);
		getGetWebElement("//input[@id='lastname']").sendKeys(lastName);
		ClsReport.fnLog(Status.INFO, "Data - PsudoRandomized Email: "+email, false);
		getGetWebElement("//input[@id='email_address']").sendKeys(email);
		getGetWebElement("//input[@id='password']").sendKeys(password);
		getGetWebElement("//input[@id='confirmation']").sendKeys(password);
		ClsReport.fnLog(Status.PASS, "Step - Registration Information Inputted", true);
		Click("//button[@title='Register']");
	}


}
