package Utils;

import java.util.concurrent.ConcurrentHashMap;

import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentTest;

import Resources.DriverSource;

public class DynamicData {
        
	public class setExtentTest {

	}

	private static ConcurrentHashMap<Long, ExtentTest> extentMap = new ConcurrentHashMap<Long, ExtentTest>();
	
	
	@BeforeTest
	public void setExtentTest(Long key, ExtentTest  extentTest) {
	    extentMap.put(key, extentTest);
	}
	
	public ExtentTest getExtentTest() {
		return extentMap.get(Thread.currentThread().getId());
	}
      	
	
}
