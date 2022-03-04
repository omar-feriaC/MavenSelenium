package selenium;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClsWebElements 
{
	
	public static Wait<WebDriver> objFluentWait;
    public static WebDriverWait objExplicitWait;
    public static String strAction = "";
	
    
    public WebElement getGetWebElement(String pstrLocator)
    {
        try
        {
            WebElement pobjElement = ClsBrowser.objDriver.findElement(By.xpath(pstrLocator));
            return pobjElement;
        }
        catch (Exception pobjException)
        {
        	System.out.println("The element was ("+ pstrLocator +") not located in the page");
           return null;
        }
    }
	
	
    public List<WebElement> getWebList(String pstrLocator)
    {
        try
        {
            List<WebElement> pobjElement = ClsBrowser.objDriver.findElements(By.xpath(pstrLocator));
            return pobjElement;
        }
        catch (Exception pobjException)
        {
        	System.out.println("The element was ("+ pstrLocator +") not located in the page");
            return null;
        }
    }
    
    
    public Object GetFluentWait(final String pstrLocator) 
    {
    	try 
    	{
    		// Waiting 30 seconds for an element to be present on the page, checking
    	 	   // for its presence once every 5 seconds.
    	    	objFluentWait = new FluentWait<WebDriver>(ClsBrowser.objDriver)
    	 	       .withTimeout(Duration.ofSeconds(30L))
    	 	       .pollingEvery(Duration.ofSeconds(3L))
    	 	       .ignoring(NoSuchElementException.class);
    	 	       
    	    	//Get Web Element and perform action
    	    	WebElement objElement = objFluentWait.until(new Function<WebDriver, WebElement>() {
    	   	     public WebElement apply(WebDriver driver) {
    	   	       return driver.findElement(By.xpath(pstrLocator));
    	   	     }
    	   	   });
    	    	
    	    	return objElement;
    	}
    	catch(Exception e) 
    	{
    		System.out.println("The element was ("+ pstrLocator +") not located in the page");
            return null;
    	}
    }
    
    
    public boolean Click(final String pstrLocator) 
	{
		WebElement objElement = (WebElement) GetFluentWait(pstrLocator);
		objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
		objExplicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(pstrLocator)));
		objElement.click();
		return false;
	}
    
    
    public boolean SendKeys(final String pstrLocator, String pValue) 
	{
		WebElement objElement = (WebElement) GetFluentWait(pstrLocator);
		objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
		objExplicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pstrLocator)));
		objElement.clear();
		objElement.sendKeys(pValue);
		return true;
	}
    
    
    public void WaitForElement(final String pstrLocator) 
    {
    	objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
    	objExplicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pstrLocator)));
    }
    
    public void WaitForElementClickable(final String pstrLocator) 
    {
    	objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
    	objExplicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pstrLocator)));
    	objExplicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(pstrLocator)));
    }
    
    
    
}
