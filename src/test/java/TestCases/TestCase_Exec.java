package TestCases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;

import POM.AtLoginPage;
import POM.BookStorePage;
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
	
	
	@Test
	public void FillElementsInput()
	{
		String DemoSite = "https://demoqa.com/login";
		String PositionsURL = "https://positionsapp-uat.azurewebsites.net/#";
		
		NavigateToUrl(PositionsURL);
		WaitForLoad();
		
		AtLoginPage objLogin = new AtLoginPage();
		objLogin.enterCredential();
		objLogin.startSession();
		objLogin.keepSessionDialog();
		objLogin.verifyActiveSession();
		
		/*
		BookStorePage objBook = new BookStorePage();
		objBook.LoginBookStore("oferia", "P@ssw0rd!123");
		objBook.GoToBookStore();
		objBook.SelectFirstBook();
		objBook.AddFirstBook();
		objBook.GoToProfilePage();
		objBook.VerifyBooksAdded();
		objBook.DeleteAllBooks();
		
		
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Report\\Spark.html");
		spark.config(
				  ExtentSparkReporterConfig.builder()
				    .theme(Theme.STANDARD)
				    .documentTitle("My Selenium Training Report")
				    .build()
				);
		extent.attachReporter(spark);
		ExtentTest testReport = extent.createTest("MyFirstTest");
		testReport.log(Status.PASS, "This is a PASS message.");
		testReport.log(Status.FAIL, "This is a FAIL message.");
		testReport.log(Status.WARNING, "This is a WARNING message.");
		testReport.log(Status.INFO, "This is a INFO message.");
		testReport.log(Status.SKIP, "This is a SKIP message.");
		
		extent.flush();
		*/
		
		
		/*
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
		*/
		
	}
	
	
	
	
	@After
	public void tearDown() 
	{
		CloseBrowser();
	}
	
	

}
