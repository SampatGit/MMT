package MMT;

import org.testng.annotations.Test;

import Pages.FlightSelection;
import Resources.TestManager;

/*
 * Given I want to Book tickets from Bangalore to Goa
 * And I select One way
 * And I select Departure date 10 Apr 2020
 * And I select the cheapest flight
 * When I select Book Now
 * Then Review your booking page should display
*/


public class TestCaseOne extends TestManager {
   
	FlightSelection flightSelection = new FlightSelection();
	
	public void testCaseOne() throws InterruptedException {
		//Launch URL
		flightSelection.launchUrl();
		
		//Select Departure City Bangalore
		flightSelection.selectDeparture();
		flightSelection.selectDepatureCity();
		
		//Select Arrival City GOA
		flightSelection.selectArrival();
		
		//Select Date
		flightSelection.selectDate();
		
		//Search Flights
		flightSelection.clickSearch();
		
		//Select Cheapest File
		flightSelection.flightDisplay();
		flightSelection.selectLowFare();
		
		//Verify Header
		flightSelection.verifyReviewBooking();
		
		
	}
	
	
}
