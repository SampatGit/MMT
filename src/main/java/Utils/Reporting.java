package Utils;

import java.io.File;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import Resources.DriverSource;

public class Reporting extends DriverSource {
	
	
		
	public static ExtentReports extent;
	public static ExtentTest test;	
	
	
	@BeforeTest
	public void setExtentConfig() {
		
		File outputFile = new File(System.getProperty("user.dir")+"\\test-output");
		//HtmlReporter is used to set configuration of the extent report	
		ExtentHtmlReporter HtmlReporter;
		HtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\test-output\\myReport.html");
		HtmlReporter.config().setReportName("Auto Report Name");
		HtmlReporter.config().setDocumentTitle("My Title");
		HtmlReporter.config().setTheme(Theme.DARK);
		
		//Attach HtmlReporte to ExtentReports object
		
		extent = new ExtentReports();
		extent.attachReporter(HtmlReporter);
		extent.setSystemInfo("Environment", "Automation Testing");
		
	}
	
	@AfterTest
	public void flushExtent() {
		System.out.println("flush code");
		extent.flush();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		
		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test case Failed"+result.getTestName());
			test.log(Status.FAIL, "Error is"+result.getThrowable());
			
		}
		if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test case pass"+result.getMethod());
			
		}
	}
	
	public String capture(WebDriver driver, String name) throws IOException {
		String fileName = new SimpleDateFormat("MMddHHmm'.png'").format(new Date());
		String dest = System.getProperty("user.dir")+"\\test-output\\Screenshots\\"+name+".png";
		TakesScreenshot scrcaptr = (TakesScreenshot)driver;
		File srcfile = scrcaptr.getScreenshotAs(OutputType.FILE);
		File destfile = new File(dest);
		Files.copy(srcfile, destfile);
		return	 dest;
	}
	
	
	
	

}
