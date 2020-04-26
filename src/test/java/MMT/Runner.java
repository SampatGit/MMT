package MMT;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Resources.DriverSource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Runner extends DriverSource {
	
	WebDriver driver = initiateDriverInstance();
	
	@Given("I have the URL for MMT website")
	public void getUrl()
	{
		System.out.println("Launching MMT website");
	}
	
	@When("I launch it using Chrome Browser")
	public void launchUrl()
	{
		driver.get("https://www.makemytrip.com/");
	}
	
	@Then("The website should load successfully")
	public void verifyWebsiteLoad() {
		Assert.assertTrue(driver.getTitle().contains("MakeMyTrip"));
	}

}
