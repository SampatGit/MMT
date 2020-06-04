package Utils;

import java.io.File;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.service.DriverService;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.MediaType;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.TestAttributeTestContext;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;
import Resources.DriverSource;
import Utils.DynamicData.setExtentTest;

public class Reporting extends DriverSource {
	
	
		
	public static ExtentReports extent;
		
	public static final Reporting INSTANCE = new Reporting();
	private static ThreadLocal<Media> media;
	DynamicData dynamicData = new DynamicData();
	public static ExtentTest extentTest;
	
	
	@BeforeTest
	public ExtentReports setExtentConfig() {
		
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
		
		return extent;
		
	}
	
	@AfterTest
	public void flushExtent() {
		System.out.println("flush code");
		extent.flush();
	}
	
//	public void tearDown(ITestResult result) {
//		
//		if(result.getStatus() == ITestResult.FAILURE) {
//			test.log(Status.FAIL, "Test case Failed"+result.getTestName());
//			test.log(Status.FAIL, "Error is"+result.getThrowable());
//			
//		}
//		if(result.getStatus() == ITestResult.SUCCESS) {
//			test.log(Status.PASS, "Test case pass"+result.getMethod());
//			
//		}
//	}
	
	public static String capture(String screenshotName) throws IOException {
		String fileName = new SimpleDateFormat("MMddHHmm'.png'").format(new Date());
		TakesScreenshot scrcaptr = (TakesScreenshot)driverInstance;
		File srcfile = scrcaptr.getScreenshotAs(OutputType.FILE);
		String imgPath = "D:\\Screenshots\\"+fileName;
		
		String dest = "D:\\Screenshots\\"+fileName;
		
	
		
		
		File destfile = new File(dest);
		
		FileHandler.copy(srcfile, destfile);
		return imgPath;
		
	}
	
	public static Reporting getInstance() {
		return Reporting.INSTANCE;
	}
	
	public static Reporting screenCapture() throws IOException {
		ScreenCapture sc = new ScreenCapture();
		sc.setMediaType(MediaType.IMG);
		//sc.setPath(capture());
		//sc.setName(title);
		media.set(sc);
		return getInstance();
		
	}
	
	
	
	
	
	

}
