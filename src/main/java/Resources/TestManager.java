package Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utils.DynamicData;
import Utils.Reporting;

public class TestManager extends Reporting{

	public static Properties appData = new Properties();
	DynamicData dynamicData = new DynamicData();
	public static ExtentReports extent;
	static Reporting reporting = new Reporting();
	
	@BeforeClass
	
	public static void readProperties() throws IOException {
		
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\data.properties");
		appData.load(file);	
	}
	
	public ExtentReports getExtentInstance() {
		if(extent == null) {
			try {
				extent =  reporting.setExtentConfig();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return extent;
	}
	
	public void setTestName(String testName) {
		Long key = Thread.currentThread().getId();
		dynamicData.setExtentTest(key, getExtentInstance().createTest(testName));
		ExtentTest extentTest = dynamicData.getExtentTest();
		ExtentTest test = extentTest.createNode(testName);
		dynamicData.setExtentTest(key, test);
	}

	
}
