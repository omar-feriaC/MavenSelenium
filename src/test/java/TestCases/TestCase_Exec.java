package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import selenium.ClsBrowser;

public class TestCase_Exec extends ClsBrowser
{
	public String URL; 
	
	@Before
	public void setup() 
	{
		URL = "https://demoqa.com/";
		ClsBrowser.BrowserName = "Edge";
		OpenBrowser();
	} 
	
	/*
	@Test
	public void DataDriverTest() 
	{
		try {
			String SetTemp = "2";
			String TempValues = "";
			int intCount = 2;
            String rutaArchivoExcel = "./src/test/resources/Driver/DataDriver.xlsx";
            FileInputStream inputStream = new FileInputStream(new File(rutaArchivoExcel));
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator iterator = firstSheet.iterator();
            
            DataFormatter formatter = new DataFormatter();
            while (iterator.hasNext()) {
                Row nextRow = (Row) iterator.next();
                Iterator cellIterator = nextRow.cellIterator();
                while(cellIterator.hasNext()) {
                	Cell cell = (Cell) cellIterator.next();
                    String contenidoCelda = formatter.formatCellValue(cell);
                    System.out.println("celda: " + contenidoCelda);
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	*/
	
	/*
	@Test
	public void FillElementsInput()
	{
		//Go to URL QA DEMO
		NavigateToUrl(URL);
		WaitForLoad();
		
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
	*/
	
	@Test
	public void VerifyLoginAndAddBook()
	{
		NavigateToUrl("https://demoqa.com/login");
		WaitForLoad();
		
		//Login
		WaitForElement("//input[@id='userName']");
		SendKeys("//input[@id='userName']", "oferia");
		WaitForElement("//input[@id='password']");
		SendKeys("//input[@id='password']", "P@ssw0rd!123");
		Click("//button[@id='login']");
		
		//Go to books
		WaitForLoad();
		WaitForElement("//div[@class='ReactTable -striped -highlight']");
		Click("//button[@id='gotoStore']");
		
		//Select First Book
		WaitForElement("//a[text()='Git Pocket Guide']");
		LinkText("Git Pocket Guide");
		WaitForLoad();
		WaitForElement("//div[@class='profile-wrapper']");
		
		//Add book
		WaitForElement("//button[@id='addNewRecordButton']");
		Click("(//button[@id='addNewRecordButton'])[2]");
		Assert.assertEquals("Book added to your collection.", GetAlertText());
		AcceptAlert();
		
		//Go to Profile
		NavigateToUrl("https://demoqa.com/profile");
		WaitForLoad();
		WaitForElement("//div[@class='profile-wrapper']");
		
		//Get Web Elements
		String bookTitle = "Git Pocket Guide"; 
		List<WebElement> lsBooks = ClsBrowser.objDriver.findElements(By.xpath("//div[@class='action-buttons']//a"));
		if(lsBooks.size() > 0) 
		{ 
			for (WebElement book : lsBooks) {
				if(book.getText().equalsIgnoreCase(bookTitle)) 
				{ 
					System.out.println("The book: " + bookTitle + " was found as expected in the profile.");
				}
			}
		}
		else
		{ 
			System.out.println("The Profile did not contains any book stored with name: " + bookTitle);
			Assert.fail();
		}
		
		//Delete Book
		WaitForLoad();
		WaitForElement("(//button[text()='Delete All Books'])[1]");
		Click("(//button[text()='Delete All Books'])[1]");
		WaitForElement("//button[@id='closeSmallModal-ok']");
		Click("//button[@id='closeSmallModal-ok']");
		Assert.assertEquals("All Books deleted.", GetAlertText());
		AcceptAlert();
	}
	
	
	@After
	public void tearDown() 
	{
		CloseBrowser();
	}
	
	

}
