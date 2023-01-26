package com.google.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class forachieverer {
	WebDriver driver=null;
	
	@BeforeClass
	public void beforeClass()
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.manage().window().maximize();
		driver.get("https://4achievers.com/");
	}
	@Test
public void forAchiever()
{
		driver.findElement(By.xpath("//img[@data-w-id=\"2cc8e716-88b7-f1d1-3b1b-cd68128cf656\"]")).click();
	    driver.findElement(By.linkText("Enroll Now")).click();
}
}
