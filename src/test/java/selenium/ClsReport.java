package selenium;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ClsReport {
	// public static String reportLocation = "C:\\Report\\Spark.html";
	public static String reportLocation = getStringPath("./Reports/");
	public static ExtentReports objExtent;
	public static ExtentSparkReporter objSpark;
	public static ExtentTest objTest;

	/**
	 * Setup the Report in the path specified with default title
	 */
	public static void fnSetupReport() {
		fnSetupReport("Selenium Report");
	}

	/**
	 * Set up report in the path specified
	 * 
	 * @param title Report title to be showned
	 */
	public static void fnSetupReport(String title) {
		// Set up dir for the reports
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy_hh-mm");
		Date date = new Date(System.currentTimeMillis());
		reportLocation = reportLocation + getStringPath(title + "_" + formatter.format(date) + "/");
		File reportDir = new File(reportLocation);
		if (!reportDir.mkdirs())
			System.out.println("Report Directory made at" + reportLocation);
		// Start the report
		objExtent = new ExtentReports();
		objSpark = new ExtentSparkReporter(reportLocation);
		objSpark.config(ExtentSparkReporterConfig.builder().theme(Theme.DARK).documentTitle(title).build());
		objExtent.attachReporter(objSpark);
	}

	/**
	 * Close the Report Generated
	 */
	public static void fnCloseReport() {
		objExtent.flush();
	}

	/**
	 * Log the steps during execution time
	 * 
	 * @param status         Status to be logged
	 * @param description    What happened on the step
	 * @param takeScreenshot Boolean if to take a screenshot of the step
	 */
	public static void fnLog(Status status, String description, Boolean takeScreenshot) {
		if (takeScreenshot) {
			objTest.log(status, description, MediaEntityBuilder.createScreenCaptureFromPath(fnScreenshot()).build());
		} else {
			objTest.log(status, description);
		}
	}

	/**
	 * Takes an screenshot of the execution step
	 * 
	 * @return String to the image file
	 */
	private static String fnScreenshot() {
		String strFileLocation;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy_hhmmss");
			Date date = new Date(System.currentTimeMillis());
			String strSSName = "SS_" + formatter.format(date);
			File scrFile = ((TakesScreenshot) ClsBrowser.objDriver).getScreenshotAs(OutputType.FILE);
			strFileLocation = reportLocation + "images/" + strSSName.toString() + ".png";
			strFileLocation = getStringPath(strFileLocation);
			File savedImage = new File(strFileLocation);
			FileUtils.copyFile(scrFile, savedImage);
			return savedImage.getAbsolutePath();
		} catch (Exception e) {
			strFileLocation = "";
		}
		return strFileLocation;
	}

	private static String getStringPath(String path) {
		return path.replaceAll("/", File.separator);
	}

}
