package POM;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import selenium.ClsBrowser;

public class BookStorePage extends ClsBrowser {
	
	//Locators
	String UserNameTxt = "//input[@id='userName']";
	String PasswordTxt = "//input[@id='password']";
	String LoginBtn = "//button[@id='login']";
	String EmptyBookTable = "//div[@class='ReactTable -striped -highlight']";
	String GoToStoreBtn = "//button[@id='gotoStore']";
	String FirstBookName = "Git Pocket Guide";
	String FirstBookLink = "//a[text()='"+ FirstBookName +"']";
	String WrapperProfileTable = "//div[@class='profile-wrapper']";
	String AddBookBtn = "(//button[@id='addNewRecordButton'])[2]";
	String BooksAddedList = "//div[@class='action-buttons']//a";
	String DeleteAllBooksBtn = "(//button[text()='Delete All Books'])[1]";
	String ModalOKButton = "//button[@id='closeSmallModal-ok']";
	
	
	
	public void LoginBookStore(String pUserName, String pPassword) 
	{
		WaitForElement(UserNameTxt);
		SendKeys(UserNameTxt, pUserName);
		WaitForElement(PasswordTxt);
		SendKeys(PasswordTxt, pPassword);
		Click(LoginBtn);
	}
	
	public void GoToBookStore() 
	{
		WaitForLoad();
		WaitForElement(EmptyBookTable);
		Click(GoToStoreBtn);
	}
	
	public void SelectFirstBook() 
	{
		WaitForElement(FirstBookLink);
		LinkText(FirstBookName);
		WaitForLoad();
		WaitForElement(WrapperProfileTable);
	}
	
	public void AddFirstBook()
	{
		WaitForElement(AddBookBtn);
		Click(AddBookBtn);
		Assert.assertEquals("Book added to your collection.", GetAlertText());
		AcceptAlert();
	}
	
	public void GoToProfilePage()
	{
		NavigateToUrl("https://demoqa.com/profile");
		WaitForLoad();
		WaitForElement(WrapperProfileTable);
	}
	
	public void VerifyBooksAdded() 
	{
		List<WebElement> lsBooks = ClsBrowser.objDriver.findElements(By.xpath(BooksAddedList));
		if(lsBooks.size() > 0) 
		{ 
			for (WebElement book : lsBooks) {
				if(book.getText().equalsIgnoreCase(FirstBookName)) 
				{ 
					System.out.println("The book: " + FirstBookName + " was found as expected in the profile.");
				}
			}
		}
		else
		{ 
			System.out.println("The Profile did not contains any book stored with name: " + FirstBookName);
			Assert.fail();
		}
	}
	
	public void DeleteAllBooks() 
	{
		WaitForLoad();
		WaitForElement(DeleteAllBooksBtn);
		Click(DeleteAllBooksBtn);
		WaitForElement(ModalOKButton);
		Click(ModalOKButton);
		Assert.assertEquals("All Books deleted.", GetAlertText());
		AcceptAlert();
	}
	
}
