package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Resources.TestManager;
import Utils.CommonFunctionality;

public class HotelSelection extends TestManager {
	
	private String hotelMenu = "//span[contains(@class,'chHotels')]";
	private String stayInCity = "//following-sibling::span";
	private String sendCity = "//input[contains(@class,'suggest')]";
	
	
	BasePage basePage = new BasePage();
	FlightSelection flightSelection = new FlightSelection();
	CommonFunctionality cf = new CommonFunctionality();
	
	//This method will select the Hotels option in Home Page
	public void selectHotels() {
	  basePage.findElementAndClick("xpath", hotelMenu);
	}
	
	public void selectCheckinDate() {
		
	}

	public void selectStayCity(String stayCity) throws InterruptedException {
		
		WebElement sendCityValue = basePage.findEle("id", "city");
		
		sendCityValue.click();
		driverInstance.findElement(By.xpath(sendCity)).sendKeys(stayCity);
		
		//basePage.findElementSendText("xpath", sendCity, stayCity);
		
		List<WebElement> cities = basePage.findElements("xpath", appData.getProperty("toCities"));
		for(WebElement city: cities) {
			System.out.println("city value"+city.findElement(By.xpath(stayInCity)).getText());
			
			if(city.findElement(By.xpath(stayInCity)).getText().trim().contains("City")) {
				city.click();
			}	
			else
				System.out.println("No such element");}
	}
	public void selectCheckin() throws InterruptedException {
		basePage.datePicker("jul", 10);
	}
}
