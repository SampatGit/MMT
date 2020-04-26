package Pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Resources.TestManager;

public class BasePage extends TestManager {

	private String clickNextNavigation = "span[class='DayPicker-NavButton DayPicker-NavButton--next']";
	
	private String adultTravellers = "//li[contains(@data-cy,'adult')]";
    private String childTravellers = "//li[contains(@data-cy,'child')]";
    private String infantTravellers = "//li[contains(@data-cy,'infant')]";


	public WebElement findEle(String Locator, String Value) {
		wait = new WebDriverWait(driverInstance, 20);
		WebElement element = null;
		String LocatorCaseUpper = Locator.toUpperCase();
		switch(LocatorCaseUpper) {
		case "XPATH":
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Value)));
			break;
		case "ID":
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Value)));
			break;
		case "CSS":
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Value)));
			break;
		default:
			System.out.println("No Such Locator available");

		}
		return element;

	}
	
	public void findElementAndClick(String Locator, String Value) {
		WebElement element = findEle(Locator, Value);
		element.click();
		
	}

	public List<WebElement> findElements(String Locator, String Value) throws InterruptedException{
		Thread.sleep(2000);
		int attempts = 0;
		List<WebElement> elements = null;
		String LocatorCaseUpper = Locator.toUpperCase();
		while(attempts<2) {
			try {
				switch(LocatorCaseUpper) {
				case "XPATH":
					elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Value)));
					break;
				case "ID":
					elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(Value)));
					break;
				case "CSS":
					elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(Value)));
					break;
				default:
					System.out.println("No such locator available");

				}
				attempts++;


			}
			catch(StaleElementReferenceException e){
				e.printStackTrace();
			}

		}
		return elements;

	}

	public void datePicker(String Month, int date) throws InterruptedException {
		Thread.sleep(1000);
		List<WebElement> elements = findElements("xpath",appData.getProperty("month"));
		//for(WebElement month: elements) {
		Iterator<WebElement> it = elements.iterator();
		while(it.hasNext()) {
			if(it.next().getText().toString().contains(Month)) {
				String fullMonth = appData.getProperty("month")+"[1]"+"/.//div[@class = 'DayPicker-Day']]/.//p";
				selectDate(date, appData.getProperty("month_body"));
				break;

			}
			else {
				findEle("css", clickNextNavigation).click();
				datePicker(Month, date);
				//String fullMonth = appData.getProperty("month")+"[2]"+"/.//div[@class = 'DayPicker-Day']/.//p";
				//selectDate(date, fullMonth);
				break;

			}  


		}



	}

	public void selectDate(int date, String path) throws InterruptedException {
		List<WebElement> allDates = findElements("xpath", path); ;
		//List<WebElement> dates = findElements("xpath", allDates);

		for(WebElement day: allDates) {
			System.out.println("date is"+ day.getText());
			if(Integer.parseInt(day.getText().trim())== date) {

				day.click();
				break;
			}
		}
	}

	enum travellerType{
		ADULT, CHILDREN, INFANT;

	}

	public void selectTraveller(int travellerNum, travellerType T ) throws InterruptedException {
		try{
            
			
			switch(T) {
			case ADULT:
				selectTravellerTypeNum(findElements("xpath", adultTravellers), travellerNum);
				break;
			case CHILDREN:
				selectTravellerTypeNum(findElements("xpath", childTravellers), travellerNum);
				break;
			case INFANT:
				selectTravellerTypeNum(findElements("xpath", infantTravellers), travellerNum);
			default:
				System.out.println("no case");
				
		}}
		catch(NumberFormatException nfe) {
			nfe.printStackTrace();
		}
	}
	
	public void selectTravellerTypeNum(List<WebElement> travellers, int travellerNumber) {
		
		for(WebElement traveller: travellers) {
			if(Integer.parseInt(traveller.getText().trim())== travellerNumber ) {
				traveller.click();
			}
			
		}
	}


}



