package MMT;

import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.HotelSelection;
import Resources.TestManager;

/*
 * Given I want to Book a hotel in GOA
 * And Check-in date is 29 Jun
 * And Check-out date is 05 Jul
 * And  need to book two rooms
 * And Room 1 has 1 Adult
 * And Room 2 has 1 Adult and 1 Child with age 5
 * And Select travel reason as leisure
 * And I want my hotel in the vicinity of North Goa
 * And Hotel rating should be 4 or above stars
 * And Property type should be Apartments
 * And Price Range should be 20000 or less
 * And I select the best hotel
 * When I click on Book this Now
 * Then I verify Review Your Booking page
 * And I verify the Net Payable amount and Hotel name
 */

/*
 * Scripting started on: 27-Apr-2020
 */

public class TestCaseThree extends TestManager {
	
	BasePage basePage = new BasePage();
	HotelSelection hotelSelection = new HotelSelection();
     
	@Test
	public void testCaseThree() throws InterruptedException {
		
		
		setTestName("TEST CASE THREE");
		//Launch URL
		basePage.launchUrl();
		
		//Select Hotel Menu Option
		hotelSelection.selectHotels();
		
		//Select City
		hotelSelection.selectStayCity("Goa");
		
		hotelSelection.selectCheckin();
		
	}
}
