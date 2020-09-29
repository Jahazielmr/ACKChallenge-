package com.qualitystream.tutorial;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ParkingCalculatorTests {

	private WebDriver driver;
	
	@Before
	public void setUp(){
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.shino.de/parkcalc/");
		
	}
	
	
	//StartInputs only
	@Test
	public void testStartDateInput() throws InterruptedException {
		//StartDateInputs and submit button
		WebElement startingDate_box = driver.findElement(By.name("StartingDate"));
		startingDate_box.clear();
		
		WebElement startingTime_box = driver.findElement(By.name("StartingTime"));
		startingTime_box.clear();
		
		WebElement radioPM = driver.findElement(By.xpath("//input[@value='PM']"));// To test and select the radio button setting up to PM, using xpath
		
		WebElement calculate_bt = driver.findElement(By.name("Submit"));
		
		
		//---------------------------------------------------------------------------------------
		
		
		startingDate_box.sendKeys("8/1/2020");
		startingTime_box.sendKeys("10:00");
		radioPM.click();
		
		Thread.sleep(6000);
		
		calculate_bt.click();
		
		Thread.sleep(6000);
		
		List<WebElement> fonts = driver.findElements(By.className("SubHead"));//Because I need to get the text "ERROR! ENTER A CORRECTLY FORMATTED DATE" from the page, but this text doesn't have a tagname or idname, I needed to got a list of all the subheads, this is the second one from the list.  
		
		if(fonts.get(1).getText().equals("ERROR! ENTER A CORRECTLY FORMATTED DATE")) {
			
			System.out.print("Expected Error");
			
		} else {
			System.out.print("We need to show an error message telling the user thathe needs to enter a correctly formatted date");
		}
		
		//assertNotEquals("ERROR! ENTER A CORRECTLY FORMATTED DATE", fonts.get(1).getText());
		assertEquals("ERROR! ENTER A CORRECTLY FORMATTED DATE", fonts.get(1).getText());
		
	}
	
	
	//Leaving Date Inputs only
	
	@Test
	public void testEndDateInput() throws InterruptedException {
		//getting EndDate inputs and submit button
	
		WebElement endingDate_box = driver.findElement(By.name("LeavingDate"));
		endingDate_box.clear();
		
		WebElement endingTime_box = driver.findElement(By.name("LeavingTime"));
		endingTime_box.clear();
		
		WebElement calculate_bt = driver.findElement(By.name("Submit"));
		
		//------------------------------------------------------------------------------
		
		endingDate_box.sendKeys("12/10/2019");
		endingTime_box.sendKeys("8:00");
		
		Thread.sleep(6000);
		
		calculate_bt.click();
		
		List<WebElement> fonts = driver.findElements(By.className("SubHead"));// getting the error message using an array list of the subheads.
		
		if(fonts.get(1).getText().equals("ERROR! ENTER A CORRECTLY FORMATTED DATE")) {
			
			System.out.print("Expected Error");
			
		} else {
			
			System.out.print("We need to tell the user that he needs to nter a correctly formatted date");
		
		}
		
		assertEquals("ERROR! ENTER A CORRECTLY FORMATTED DATE", fonts.get(1).getText());
		
		Thread.sleep(6000);
		
	}
	
	
	//Complete test and check functionality & calculation on Parking Lot
	
	@Test
	public void completeTestGoodone() throws InterruptedException {
		//All inputs
		
		WebElement startingDate_box = driver.findElement(By.name("StartingDate"));
		startingDate_box.clear();
		
		WebElement startingTime_box = driver.findElement(By.name("StartingTime"));
		startingTime_box.clear();
		
		//WebElement radioPM = driver.findElement(By.xpath("//input[@value='PM']"));
		
		WebElement endingDate_box = driver.findElement(By.name("LeavingDate"));
		endingDate_box.clear();
		
		WebElement endingTime_box = driver.findElement(By.name("LeavingTime"));
		endingTime_box.clear();
		
		WebElement selectParking = driver.findElement(By.name("ParkingLot"));
		Select s_Parking = new Select(selectParking);
		
		WebElement calculate_bt = driver.findElement(By.name("Submit"));
		
		//----------------------------------------------------------------------------------------
		
		startingDate_box.sendKeys("10/11/2020");
		startingTime_box.sendKeys("10:00");
		//
		
		endingDate_box.sendKeys("10/18/2020");
		endingTime_box.sendKeys("10:00");
		
		s_Parking.selectByIndex(2);// We will select the Economy lot for this one. we will check if the user is getting the 7th day free.
		
		Thread.sleep(6000);
		
		calculate_bt.click();
		
		//He should be charged with 54$ about a 7 days. 7th day is for free.
		
		List<WebElement> fonts = driver.findElements(By.className("SubHead"));//Getting the calculation result.
		
		int charge=0;
		for(int i=0; i<7; i++) {
			
			int total=0;
			total=total+(9*i);
			
			charge=total;
			
		}
		
		String charget="$ "+charge+".00";
		
		if(fonts.get(1).getText().equals(charget)) {
			
			System.out.print("Correct Calculation");
			
		} else {
			
			System.out.print("Wrong calculation");
		
		}
		
		Thread.sleep(6000);
		
		assertEquals(charget, fonts.get(1).getText());
		
	}
	
	
	// Bad one 
	
	@Test
	public void completeTestBadone() throws InterruptedException {
		//All inputs 
		
		WebElement startingDate_box = driver.findElement(By.name("StartingDate"));
		startingDate_box.clear();
		
		WebElement startingTime_box = driver.findElement(By.name("StartingTime"));
		startingTime_box.clear();
		
		WebElement radioPM = driver.findElement(By.xpath("//input[@value='PM']")); 
		
		WebElement endingDate_box = driver.findElement(By.name("LeavingDate"));
		endingDate_box.clear();
		
		WebElement endingTime_box = driver.findElement(By.name("LeavingTime"));
		endingTime_box.clear();
				
		WebElement selectParking = driver.findElement(By.name("ParkingLot"));
		Select s_Parking = new Select(selectParking);
				
		WebElement calculate_bt = driver.findElement(By.name("Submit"));
				
		//------------------------------------------------------------------------------
		
		startingDate_box.sendKeys("10/18/2020");
		startingTime_box.sendKeys("10:00");
		radioPM.click();
		
		endingDate_box.sendKeys("10/11/2020");
		endingTime_box.sendKeys("10:00");
		
		s_Parking.selectByIndex(2);//We will select the Short-Term Parkin on this one.
		
		Thread.sleep(6000);
		
		calculate_bt.click();
		
		List<WebElement> fonts = driver.findElements(By.className("SubHead"));
		
		if(fonts.get(1).getText().equals("ERROR! YOUR LEAVING DATE OR TIME IS BEFORE YOUR STARTING DATE OR TIME")) {
			System.out.print("Expected error");
		} else {
			System.out.print("We need to show a message telling the user that his leaving date or time is before the starting date or time");
		}
		
		Thread.sleep(6000);
		
		assertEquals("ERROR! YOUR LEAVING DATE OR TIME IS BEFORE YOUR STARTING DATE OR TIME", fonts.get(1).getText());
		
		
		
	}
	
	
	@After
	public void tearDown(){
		driver.quit();
	}

	

}
