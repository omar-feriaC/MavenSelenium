package TestCases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import selenium.ClsBrowser;

public class TestCase_Exec extends ClsBrowser
{
	public String URL; 
	
	@Before
	public void setup() 
	{
		URL = "https://demoqa.com/";
		ClsBrowser.BrowserName = "Chrome";
		OpenBrowser();
	} 
	
	@Test
	public void FillElementsInput()
	{
		//Go to URL QA DEMO
		NavigateToUrl(URL);
		
		//Go to Elements Menu
		WaitForElement("//h5[text()='Elements']");
		WaitForElementClickable("//h5[text()='Elements']");
		Click("//h5[text()='Elements']");
		
		//Wait for Elements Page
		WaitForElement("//div[@class='main-header' and text()='Elements']");
		
		//Click TextBoxes
		WaitForElement("//li[span[text()='Text Box']]");
		Click("//li[span[text()='Text Box']]");
		
		//SendKeys to inputs
		WaitForElement("//input[@id='userName']");
		SendKeys("//input[@id='userName']", "Test UserName");
		
	}
	
	@After
	public void tearDown() 
	{
		CloseBrowser();
	}
	
	

}
