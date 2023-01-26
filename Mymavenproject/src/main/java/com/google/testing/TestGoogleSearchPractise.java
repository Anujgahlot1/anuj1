package com.google.testing;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestGoogleSearchPractise {
	WebDriver driver = null;
	WebDriverWait wait = null;
	@BeforeClass
public void beforeClass()
{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	
		driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
	
}
	@Test(priority=1)
	public void amazonLogin() throws InterruptedException
	{
		driver.findElement(By.id("nav-link-accountList")).click();
		driver.findElement(By.id("ap_email")).sendKeys("8755128631");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys("Ankit@111");
		
		driver.findElement(By.id("signInSubmit")).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-xshop")));
		
		//String url = driver.getCurrentUrl();
		//assertEquals(url, "https://www.amazon.in/?ref_=nav_ya_signin");
		
	}
	// FOR DROP DOWN
	@Test(priority=2)
	public void selectDropDown() throws InterruptedException
	{
		WebElement selecttag =driver.findElement(By.id("searchDropdownBox"));
		Select s = new Select(selecttag);
		s.selectByVisibleText("Amazon Devices");
		Thread.sleep(3000);
		s.selectByIndex(3);
		Thread.sleep(3000);
		
		s.selectByValue("search-alias=alexa-skills");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTO(0,800)", "");
		
		
	}
	// FOR POP UP WINDOW
	@Test(enabled=false)
	public void alertMsg()
	{
		Alert alt =driver.switchTo().alert();
		alt.accept();
		alt.dismiss();
		String alttext=alt.getText();
		assertEquals(alttext, "Are u sure");
	}
	

}
