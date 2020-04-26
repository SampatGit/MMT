package Resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class DriverSource {
	
	public static WebDriver driverInstance;
	public static WebDriverWait wait;

	@BeforeTest
	public void initiateDriverInstance() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\eclipse-workspace\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();
	    driverInstance = driver;
	}
	
	public WebDriver getDriverInstance() {
		return driverInstance;
	}
	
	@AfterTest
	public void closeResources() {
		
		driverInstance.quit();
	
	}
	
	
	

}
