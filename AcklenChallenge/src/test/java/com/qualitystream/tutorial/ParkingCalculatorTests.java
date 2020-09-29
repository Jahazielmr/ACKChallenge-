package com.qualitystream.tutorial;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
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

	@Test
	public void test() {
		
	}
	
	
	@After
	public void tearDown() throws Exception {
	}

	

}
