package Utils;

import java.util.List;

import org.openqa.selenium.WebElement;

public class CommonFunctionality {

	//This Method will take the city name as parameter and select it
	public void citySelector(String cityName, List<WebElement> cityElements) {
		for(WebElement city: cityElements) {
			    if(city.getText().trim().contains(cityName)) {
			    	city.click();
			    }
			}
		}
	
}
