package com.google.testing;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;



public class TestGoogleSearch {
	WebDriver driver = null;
	@Parameters("browsername")
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browsername)
	{
		if(browsername.equalsIgnoreCase("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.setAcceptInsecureCerts(false);
		option.addArguments("--Incognito");
		//option.addArguments("--Incognito");
		option.setCapability(CapabilityType.BROWSER_VERSION, "107");
		driver = new ChromeDriver(option);
		}
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browsername.equalsIgnoreCase("edge"))
			
		{
			System.setProperty("webdriver.msedge.driver", "./drivers/msedge.exe");
			driver = new EdgeDriver();
		}
		else
		{
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		
		}
	//@AfterClass
	//public void AfterClass()
	//{
		//driver.close();
	//}
	@Test(priority=1,enabled=false)
	
	public void searchSelinium() throws InterruptedException
	{
		driver.findElement(By.name("q")).clear();
		driver.findElement(By.name("q")).sendKeys("selenium");
		
		Thread.sleep(2000);
		driver.findElement(By.name("btnK")).click();
		driver.findElement(By.xpath("//cite[text()=\"https://www.selenium.dev\"]")).click();
		driver.findElement(By.xpath("//span[text()=\"Search\"]")).click();
		driver.findElement(By.id("docsearch-input")).sendKeys("grid");
		//driver.findElement(By.id("docsearch-input")).click();
		Thread.sleep(2000);
		driver.findElement(By.className("DocSearch-Hit-Container")).click();
		Thread.sleep(2000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		
		//String url= driver.getCurrentUrl();
		//assertEquals(url, "https://www.selenium.dev/documentation/grid/");
		//driver.findElement(By.xpath("//h1[text()=\"Grid\"]")).isDisplayed();

	}
	@Test(enabled = false,priority=2)
	public void navigationSearch()
	{
		driver.navigate().back();
		String url = driver.getCurrentUrl();
		assertEquals(url, "https://www.selenium.dev/");
		driver.navigate().refresh();
		boolean flag=driver.findElement(By.xpath("//h1[text()=\"Selenium automates browsers. That's it!\"]")).isDisplayed();
		if(!flag)
		{
			System.out.println("element not found");
		}
		driver.navigate().forward();
		//driver.navigate().to("https://www.google.com/");
		
	}
	@Test(enabled= false)
	public void brokenUrl()
	{
		driver.navigate().to("https://www.google.com/");
		List<WebElement> alinks=driver.findElements(By.xpath("//a"));
		for(WebElement weble : alinks)
		{
			String url1 = weble.getAttribute("href");
			System.out.println("url1"+ url1  );
		}
	}
	// for uploading a file
	@Test(enabled=false)
	public void upload()
	{
		driver.navigate().to("https://ps.uci.edu/~franklin/doc/file_upload.html");
	   driver.findElement(By.name("userfile")).sendKeys("C:\\Users\\anurag\\eclipse-workspace\\Mymavenproject\\drivers\\2015-7-Asstt. Prof (Political Science)  (CC).docx");	
	}
	@	Test
	public void switchWindow()
	{
		String tab =driver.getWindowHandle();
		driver.navigate().to("https://www.freecodecamp.org/news/");
		boolean br =driver.findElement(By.id("nav-forum")).isDisplayed();
		assertEquals(br, true);
		
		
		driver.findElement(By.id("nav-forum")).click();
	Set<String> tabs=	driver.getWindowHandles();
	tabs.remove(tab);
	String tab2= (String) tabs.toArray()[0];
	driver.switchTo().window(tab2);
		boolean as=driver.findElement(By.xpath("//span[@class=\"d-button-label\"]")).isDisplayed();
		assertEquals(as, true);
		driver.close();
		driver.switchTo().window(tab);
		driver.close();
		
		
		
	}

	
}
