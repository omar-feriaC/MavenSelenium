package selenium;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

public class ClsWebElements {
    private int DefaultTimeout = 10;
    public static Wait<WebDriver> objFluentWait;
    public static WebDriverWait objExplicitWait;
    public static String strAction = "";

    /**
     * Get a {@link WebElement} from a {@link By} object
     *
     * @param by element to be selected
     * @return the selected {@link WebElement}
     */
    public WebElement getGetWebElement(By by) {
        try {
            ClsReport.fnLog(Status.INFO, "Step - Get Web Element: " + by.toString(), false);
            WebElement pobjElement = ClsBrowser.objDriver.findElement(by);
            ClsReport.fnLog(Status.PASS, "Step - The Web Element: " + by.toString() + " was found as expected.", false);
            return pobjElement;
        } catch (NoSuchElementException pobjException) {
            ClsReport.fnLog(Status.FAIL, "Step - The Web Element: " + by.toString()
                    + " was not found as expected. Exception: " + pobjException.getStackTrace(), true);
            return null;
        }
    }

    /**
     * Get a {@link WebElement} using a Xpath String
     * 
     * @param pstrLocator to the element to be selected
     * @return the selected {@link WebElement}
     */
    public WebElement getGetWebElement(String pstrLocator) {
        return getGetWebElement(By.xpath(pstrLocator));
    }

    /**
     * Get a List of elements given a {@link By} object
     * 
     * @param by elements to be selected
     * @return the List of {@link WebElement} or Null if element not found
     */
    public List<WebElement> getWebList(By by) {
        try {
            ClsReport.fnLog(Status.INFO, "Step - Get Web Elements: " + by.toString(), false);
            List<WebElement> pobjElement = ClsBrowser.objDriver.findElements(by);
            ClsReport.fnLog(Status.PASS, "Step - The Web Elements: " + by.toString() + " were found as expected.", false);
            return pobjElement;
        } catch (Exception pobjException) {
            ClsReport.fnLog(Status.FAIL, "Step - The Web Elements: " + by.toString()
                    + " was not found as expected. Exception: " + pobjException.getStackTrace(), true);
            return null;
        }
    }

    /**
     * Get a list of {@link WebElement} given a Xpath String
     * 
     * @param pstrLocator Xpath for the elements to be selected
     * @return The List of {@link WebElement}
     */
    public List<WebElement> getWebList(String pstrLocator) {
        return getWebList(By.xpath(pstrLocator));
    }

    /**
     * Get a WebElement using a Xpath String after it loads waiting for 30s and
     * checking in intervals of 5 seconds
     * 
     * @param pstrLocator Xpath for the element to wait on
     * @return {@link WebElement} or Null if element not found
     */
    public WebElement GetFluentWait(final String pstrLocator) {
        return GetFluentWait(By.xpath(pstrLocator));
    }

    /**
     * Get a WebElement after it loads waiting for 30s and checking in intervals of
     * 5 seconds
     * 
     * @param by {@link By} object to get the WebElement
     * @return {@link WebElement} selected or NULL if not found/loaded
     */
    public WebElement GetFluentWait(final By by) {
        try {
            // Waiting 30 seconds for an element to be present on the page, checking
            // for its presence once every 5 seconds.
            objFluentWait = new FluentWait<WebDriver>(ClsBrowser.objDriver).withTimeout(Duration.ofSeconds(30L))
                    .pollingEvery(Duration.ofSeconds(3L)).ignoring(NoSuchElementException.class);

            // Get Web Element and perform action
            WebElement objElement = objFluentWait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(by);
                }
            });

            return objElement;
        } catch (Exception e) {
            System.out.println("The element was (" + by.toString() + ") not located in the page");
            return null;
        }
    }

    /**
     * Performs the action of click on a specified {@link WebElement}
     * 
     * @param by {@link By} objecto to select the element to click
     * @return Boolean if errors did not happened
     */
    public boolean Click(final By by) {
        try {
            WebElement objElement = (WebElement) GetFluentWait(by);
            objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, DefaultTimeout);
            objExplicitWait.until(ExpectedConditions.elementToBeClickable(by));
            objElement.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Performs the action of click on a specified {@link WebElement} using an Xpath
     * String
     * 
     * @param pstrLocator Xpath String to select the WebElement to click on
     * @return Boolean if it clicks
     */
    public boolean Click(final String pstrLocator) {
        return Click(By.xpath(pstrLocator));
    }

    /**
     * Send the input to the selected {@link WebElement}
     * 
     * @param by     {@link By} object to select the WebElement
     * @param pValue String to be send to the selected WebElement
     * @return Boolean if errors did not happened
     */
    public boolean SendKeys(final By by, String pValue) {
        try {
            WebElement objElement = (WebElement) GetFluentWait(by);
            objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, DefaultTimeout);
            objExplicitWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            objElement.clear();
            objElement.sendKeys(pValue);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Send the input to the selected {@link WebElement} using a Xpath String
     * 
     * @param pstrLocator Xpath String to select the WebElement
     * @param pValue      String to be send to the selected WebElement
     * @return Boolean if errors did not happened
     */
    public boolean SendKeys(final String pstrLocator, String pValue) {
        return SendKeys(By.xpath(pstrLocator), pValue);
    }

    /**
     * Select an option within a select html object
     *
     * @param by      {@link By} object to the select {@link WebElement}
     * @param pMethod ByValue | ByIndex | ByText : methods to select the option
     * @param pValue  Value to be used for the selection of the option element
     * @return Boolean if errors did not happened
     */
    public boolean SelectItem(final By by, String pMethod, String pValue) {
        try {
            WebElement objElement = (WebElement) GetFluentWait(by);
            objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
            objExplicitWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            Select selectObject = new Select(objElement);

            switch (pMethod.toUpperCase()) {
                case "BYVALUE":
                    selectObject.selectByValue(pValue);
                    break;
                case "BYINDEX":
                    selectObject.selectByIndex(Integer.parseInt(pValue));
                    break;
                case "BYTEXT":
                    selectObject.selectByVisibleText(pValue);
                    break;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Select an option within a select html object using Xpath to locate the
     * element
     *
     * @param pstrLocator Xpath String to select the {@link WebElement}
     * @param pMethod     ByValue | ByIndex | ByText : methods to select the option
     * @param pValue      Value to be used for the selection of the option element
     * @return Boolean if errors did not happened
     */
    public boolean SelectItem(final String pstrLocator, String pMethod, String pValue) {
        return SelectItem(By.xpath(pstrLocator), pMethod, pValue);
    }

    /**
     * Wait for element to load
     * 
     * @param pstrLocator Xpath String to select the element to be waited
     */
    public void WaitForElement(final String pstrLocator) {
        objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
        objExplicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pstrLocator)));
    }

    /**
     * Wait for element to be Clickable
     * 
     * @param by {@link By} object to select the element to be waited
     */
    public void WaitForElementClickable(final By by) {
        objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
        objExplicitWait.until(ExpectedConditions.presenceOfElementLocated(by));
        objExplicitWait.until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * Wait for element to be Clickable
     * 
     * @param pstrLocator Xpath String to the element to be waited
     */
    public void WaitForElementClickable(final String pstrLocator) {
        WaitForElementClickable(By.xpath(pstrLocator));
    }

    /**
     * Wait for the page to load
     */
    public void WaitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(ClsBrowser.objDriver, 30);
        wait.until(pageLoadCondition);
    }

    /**
     * Click on a link using the link's text to select it
     * 
     * @param pstrLocator Lin's text to be selected and clicked
     */
    public void LinkText(final String pstrLocator) {
        WebElement objElement = ClsBrowser.objDriver.findElement(By.linkText(pstrLocator));
        objElement.click();

    }

    /**
     * Accept the appearing alert
     */
    public void AcceptAlert() {
        WebDriverWait wait = new WebDriverWait(ClsBrowser.objDriver, 3000);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = ClsBrowser.objDriver.switchTo().alert();
        alert.accept();
    }

    /**
     * On an alert, get the text from the alert
     * 
     * @return String text from the Alert
     */
    public String GetAlertText() {
        WebDriverWait wait = new WebDriverWait(ClsBrowser.objDriver, 3000);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = ClsBrowser.objDriver.switchTo().alert();
        String alertMessage = alert.getText();
        return alertMessage;
    }

}
