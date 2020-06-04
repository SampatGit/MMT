package Pages;


import java.util.Collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Pages.BasePage.travellerType;
import Resources.DriverSource;
import Resources.TestManager;
import Utils.DynamicData;

public class FlightSelection extends TestManager {


	BasePage basePage = new BasePage();
	private String reviewBookingHeader = "//h4";
	private String expectedHeader = "Review your booking";
	private String roundTripCss = "li[data-cy='roundTrip']";
	private String travellerSelection = "//span[@data-cy='travellerText']";
	private String departureTimeid = "departure_group";
	private String before6AM = "//*[@id = 'departure_group']/*//span[contains(text(),'Before')]";  //xPath
	private String preNoon = "//*[@id = 'departure_group']/*//span[contains(text(),'6AM-12 Noon')";
	private String postNoon = "//*[@id = 'departure_group']/*//span[contains(text(),'12 Noon-6PM')";
	private String after6PM = "//*[@id = 'departure_group']/*//span[contains(text(),'After')]";
	private String applyTraveller = "//button[contains(@class,'btnApply')]";
	private String sortField = "//span[contains(@class, 'dropdown')]";
	private String buttonBookNow = "//button[contains(@class,'fli_primary')]";
	private String buttonContinueBooking = "//button[contains(@class,'btn fli_primary_btn')]";
	private String lowFarebtn = "//button[@class ='btn fli_primary_btn text-uppercase ']";
	public DynamicData dynamicData = new DynamicData();
	public static ExtentTest test;
	
	




	public void launchUrl(){
		test = dynamicData.getExtentTest();
		test.log(Status.INFO, "Launching URL");
		driverInstance.get(appData.getProperty("appUrl"));
		driverInstance.manage().window().maximize();
		if(driverInstance.getTitle().contains("MakeMyTrip")) {
			basePage.report("pass", "URL launched successfully");
		}
		else {
			basePage.report("fail","URL launch failed");
		}

	}


	//This method will select the departure city
	public void selectDeparture() throws InterruptedException {

		test.log(Status.INFO, "Selecting Departure Airport");
		WebElement depElement = basePage.findEle("id", appData.getProperty("depCityId")); 
		Thread.sleep(1000);
		depElement.sendKeys(appData.getProperty("fromCity"));
		selectDepatureCity();

	}


	public void selectDepatureCity() throws InterruptedException {

		test.log(Status.INFO, "Selecting Bangalore from AutoSuggest");
		List<WebElement> fromCity = basePage.findElements("xpath",appData.getProperty("fromCities"));
		for(WebElement options: fromCity) {
			System.out.println("text"+options.getText());
			if(options.getText().trim().contains(appData.getProperty("fromCityCode"))) {
				options.click();
				basePage.report("pass", "City selected");
				break;
			}
			else
				System.out.print("City doesn't exist");

		}


	}

	public void selectArrival() throws InterruptedException {
		WebElement depElement = basePage.findEle("id", appData.getProperty("arrvCityId")); 
		depElement.sendKeys(appData.getProperty("toCity"));
		test.log(Status.INFO, "Selecting GOA from AutoSuggest");
		List<WebElement> fromCity = basePage.findElements("xpath",appData.getProperty("toCities"));
		for(WebElement options: fromCity) {
			System.out.println("text"+options.getText());
			if(options.getText().trim().contains(appData.getProperty("toCityCode"))) {
				options.click();
				break;
			}

		}}

	public void selectDate() throws InterruptedException {

		test.log(Status.INFO, "Selecting date of Travel");
		basePage.datePicker("Jun", 28);
	}

	public void selectArrivalDate() throws InterruptedException {

		test.log(Status.INFO, "Selecting date of Travel");
		basePage.datePicker("Jul", 05);
	}

	public void clickSearch() throws InterruptedException {
		Thread.sleep(3000);
		WebElement searchFlight = basePage.findEle("xpath", appData.getProperty("Search"));
		searchFlight.click();
	}

	public void flightDisplay() throws InterruptedException {
		test.log(Status.INFO, "View Fares of selected flight");
		List<WebElement> allFlights = basePage.findElements("css", appData.getProperty("allFlights"));
		String finalCss = appData.getProperty("allFlights")+" "+appData.getProperty("individualPrice");
		List<WebElement> flights = basePage.findElements("css", finalCss);
		int minPrice = selectCheapestFlight(finalCss);
		System.out.println("min"+minPrice);
		Thread.sleep(2000);
		for(WebElement flight: allFlights) {

			WebElement viewFares = flight.findElement(By.cssSelector(appData.getProperty("individualPrice")));
			String priceIntCompare = viewFares.getText().replaceAll(",", "");
			String finalPriceInt = priceIntCompare.substring(2);
			if(Integer.parseInt(finalPriceInt)== minPrice) {
				flight.findElement(By.cssSelector(" button")).click();
				break;

			}
		}
	}

	public void selectLowFare() throws InterruptedException {
		test.log(Status.INFO, "Selecting flight with lowest fare");

		//	WebElement lowFare = basePage.findEle("xpath", "bookNow");
		basePage.findElementAndClick("xpath", lowFarebtn);
		Thread.sleep(5000);

	}	

	public void verifyPageHeader(String header) {
		//Set<String> Windows = driverInstance.getWindowHandles();
		driverInstance.getPageSource();
		Assert.assertTrue(driverInstance.getPageSource().contains(header));
	}




	public Integer selectCheapestFlight(String priceCss) throws InterruptedException {

		List<WebElement> allFlights = basePage.findElements("css", priceCss);
		HashSet<Integer> flightPrices = new HashSet<>();
		for(WebElement eachFlight: allFlights) {
			String priceInt = eachFlight.getText().replaceAll(",", "");
			String newPriceInt = priceInt.substring(2);
			flightPrices.add(Integer.parseInt(newPriceInt));
		}
		return Collections.min(flightPrices);

	}

	public void verifyReviewBooking() throws InterruptedException {
		//		
		test.log(Status.INFO, "Verifying Review your booking message");
		Thread.sleep(3000);
		Set <String> handles =driverInstance.getWindowHandles();
		Iterator<String> it = handles.iterator();
		//iterate through your windows
		while (it.hasNext()){
			String parent = it.next();
			String newwin = it.next();
			driverInstance.switchTo().window(newwin);

			WebElement header = basePage.findEle("xpath", reviewBookingHeader);
			Assert.assertTrue(header.getText().toString().contains(expectedHeader));

			driverInstance.close();
			driverInstance.switchTo().window(parent);
		}

	}

	public void selectRoundTrip() {
		WebElement roundTripRadioButton = basePage.findEle("css", roundTripCss);
		roundTripRadioButton.click();


	}

	public void addTravelleres() throws InterruptedException {
		basePage.findEle("xpath", travellerSelection).click();
		basePage.selectTraveller(2, travellerType.ADULT);
		basePage.selectTraveller(1, travellerType.CHILDREN);
		basePage.findElementAndClick("xpath", applyTraveller);
	}

	public void selectPreferredTime(String preferredTime) {
		WebElement depElement = basePage.findEle("id", departureTimeid);
		switch(preferredTime) {
		case "Before 6AM":
			
			depElement.findElement(By.xpath(before6AM)).click();
			break;
		case "6AM-12 Noon":
			depElement.findElement(By.xpath(preNoon)).click();
			break;
		case "12 Noon-6PM":
			depElement.findElement(By.xpath(postNoon)).click();
			break;
		case "After 6PM":
			depElement.findElement(By.xpath(after6PM)).click();
			break;
		default:
			System.out.println("No such time");

		}
		}
	
	public void verifyPriceSort() throws InterruptedException {
		Exception ex = new Exception();
		
			List<WebElement> webElements = basePage.findElements("xpath", sortField);
		
		for (WebElement element: webElements) {
			Assert.assertTrue(element.getText().contains("Price"));
			System.out.println("Assertion successfull");
		}
	}
	
	public void selectBookNow() {
		basePage.findElementAndClick("xpath", buttonBookNow);
	}
	
	public void continueBooking() {
		basePage.findElementAndClick("xpath", buttonContinueBooking);
	}
}


