package POM.at;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.ClsBrowser;

public class AtLoginFactory extends ClsBrowser {

	// Constant
	String Email = "iss@agilethought.com";
	String Password = "NewPassword!";
	String Title = "//title";

	@FindBy(xpath = "//input[@name='loginfmt']")
	public WebElement UserNameTxt;

	@FindBy(xpath = "//input[@id='passwordInput']")
	public WebElement PasswordTxt;

	@FindBy(xpath = "//input[starts-with(@id, 'idSIButton')]")
	public WebElement NextBtn;

	@FindBy(xpath = "//span[@id='submitButton']")
	public WebElement StartSessionBtn;

	@FindBy(xpath = "//div[@id='lightbox']")
	public WebElement KeepSessionDialog;

	@FindBy(xpath = "//input[starts-with(@id, 'idSIButton')]")
	public WebElement KeepSessionYesBtn;

	// Methods

	/**
	 * enter the ms email and go to next screen.
	 */
	public void enterCredential() {
		WaitForLoad();
		UserNameTxt.sendKeys(Email);
		new WebDriverWait(ClsBrowser.objDriver, 10).until(ExpectedConditions.elementToBeClickable(NextBtn));
		NextBtn.click();
	}

	/**
	 * enter ms email and password, then go to next screen.
	 */
	public void startSession() {
		WaitForLoad();
		new WebDriverWait(ClsBrowser.objDriver, 10).until(ExpectedConditions.elementToBeClickable(StartSessionBtn));
		StartSessionBtn.sendKeys(Password);
		StartSessionBtn.click();
	}

	/**
	 * wait for keep session dialog and click on Yes.
	 */
	public void keepSessionDialog() {
		WaitForLoad();
		KeepSessionYesBtn.click();
	}

	/**
	 * Assert if the page has logged in succesfully
	 */
	public void verifyActiveSession() {
		WaitForLoad();
		WaitForElement(Title);
		WebElement objTitle = getGetWebElement(Title);
		String currentTitle = objTitle.getAttribute("innerText");
		Assert.assertEquals("UAT URGENT:  Positions", currentTitle);
	}

}
