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
	
	
	@After
	public void tearDown(){
		driver.quit();
	}

	

}
