package Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import Utils.Reporting;

public class TestManager extends Reporting{

	public static Properties appData = new Properties();
	
	@BeforeClass
	
	public static void readProperties() throws IOException {
		
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\data.properties");
		appData.load(file);		
	}
	
}
