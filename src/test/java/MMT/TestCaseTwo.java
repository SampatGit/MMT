package MMT;

import org.testng.annotations.Test;

import Pages.FlightSelection;
import Resources.TestManager;

/*
 * Given I want to book a Round Trip from Bangalore to Goa
 * And I want the travel date after June 1
 * And I want to return After Jul 1
 * And When I select search
 * And I will be travelling with 1 Adult and 1 Children
 * And I prefer to travel after 6 PM from Bengaluru
 * When I select the cheapest combination of Tickets
 * And Click Book Now
 * Then I should be able to view Review Your Booking page
 * Test case start date: 22-April
 * Test Case id: 02
 */

public class TestCaseTwo extends TestManager {
	FlightSelection flightSelection = new FlightSelection();

	@Test
	public void testCaseTwo() throws InterruptedException {

		//Launch application URL
		flightSelection.launchUrl();

		//Select Round trip
		flightSelection.selectRoundTrip();

		//Select Departure city
		flightSelection.selectDeparture();

		//Seelct Arrival City
		flightSelection.selectArrival();

		//Select Departure date
		flightSelection.selectDate();

		//Select Arrival Date
		flightSelection.selectArrivalDate();

		//Select Travellers
		flightSelection.addTravelleres();

		//Search Flights
		flightSelection.clickSearch();
		
		//Select preferred time
		flightSelection.selectPreferredTime("After 6PM");
		
		//Verify Sort by Price
		flightSelection.verifyPriceSort();
		
		//Click Book Now
		flightSelection.selectBookNow();
		
		//Continue Booking
		flightSelection.continueBooking();
		
		//Verify Header
		flightSelection.verifyReviewBooking();
		
		


	}


}
