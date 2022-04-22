package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClsBrowser extends ClsWebElements {

	public static String BrowserName;
	public static WebDriver objDriver;
	public static WebDriverWait wait;

	private String strResourcePath = "./src/test/resources/selenium_driver";

	public ClsBrowser() {
		BrowserName = "Chrome";
	}

	/**
	 * Method to setup a {@link WebDriver} and Open it
	 *
	 * if Host machine is using liunux OS, {@link WebDriver} is expected to be on
	 * the $PATH enviroment variable
	 */
	public WebDriver OpenBrowser() {
		switch (BrowserName.toUpperCase()) {
			case "CHROME":
				if (!isUsingLinux())
					System.setProperty("webdriver.chrome.driver", strResourcePath + "/chromedriver.exe");
				objDriver = new ChromeDriver();
				break;
			case "EDGE":
				if (!isUsingLinux())
					System.setProperty("webdriver.edge.driver", strResourcePath + "/msedgedriver.exe");
				objDriver = new EdgeDriver();
				break;
			case "FIREFOX":
				if (!isUsingLinux())
					System.setProperty("webdriver.geckodriver.driver", strResourcePath + "/gecko.exe");
				objDriver = new FirefoxDriver();
				break;
			default:
				System.out.println("The browser is " + BrowserName + " not supported.");
				break;
		}

		objDriver.manage().window().maximize();
		objDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		objDriver.manage().deleteAllCookies();
		return objDriver;
	}

	/**
	 * Function to Close the {@link WebDriver}
	 */
	public void CloseBrowser() {
		objDriver.close();
		objDriver.quit();
	}

	/**
	 * Function to send the Driver to a sepecified Url
	 *
	 * @param Url Direction to be send to
	 */
	public void NavigateToUrl(String pstrUrl) {
		objDriver.get(pstrUrl);
	}

	/**
	 * Uitlity function to verify if the test are runned on linux
	 */
	public boolean isUsingLinux() {
		return System.getProperty("os.name").equalsIgnoreCase("linux");
	}

}
