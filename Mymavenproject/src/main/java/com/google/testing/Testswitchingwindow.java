package com.google.testing;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Testswitchingwindow {
	WebDriver driver =null;
	DesiredCapabilities cap = null;
	
	@BeforeClass
	public void beforeClass() throws MalformedURLException
	{
		cap = new DesiredCapabilities();
		//System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.setAcceptInsecureCerts(false);
		option.addArguments("--incognito");
		//option.addArguments("--Incognito");
		option.setCapability(CapabilityType.BROWSER_VERSION, "107");
		//driver = new ChromeDriver(option);	
		cap.setBrowserName("chrome");
		cap.merge(option);
		driver = new RemoteWebDriver(new URL("http://local host:4444/ui"), cap);
		
		driver.manage().window().maximize();
	}
	@Test(enabled = false)
public void switchWindow()
{
	driver.navigate().to("https://www.freecodecamp.org/news/");
	String tab=driver.getWindowHandle();
	boolean br =driver.findElement(By.id("nav-forum")).isDisplayed();
	assertEquals(br, true);
	driver.findElement(By.id("nav-forum")).click();
	Set<String> tab1=driver.getWindowHandles();
	tab1.remove(tab);
	String tab2 = (String) tab1.toArray()[0];
	driver.switchTo().window(tab2);
	boolean q = driver.findElement(By.className("header-buttons")).isDisplayed();
	assertEquals(q, true);
	driver.close();
	driver.switchTo().window(tab);
	boolean br1 =driver.findElement(By.id("nav-forum")).isDisplayed();
	assertEquals(br1, true);
	driver.close();
	}
	@Test(priority =1,enabled=false)
	public void draggable()
	{
		driver.navigate().to("https://jqueryui.com/");
		driver.findElement(By.linkText("Draggable")).click();
		WebElement frame = driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(frame);
		
		Actions action = new Actions(driver);
		WebElement source=driver.findElement(By.id("draggable"));
		action.dragAndDropBy(source, 150, 100).build().perform();
		
		
	}
	@Test(priority =2,enabled = false)
	public void dropable()
	{
		driver.navigate().to("https://jqueryui.com/");
		driver.findElement(By.linkText("Droppable")).click();
		WebElement frame =driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(frame);
		Actions action = new Actions(driver);
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement dest = driver.findElement(By.id("droppable"));
		action.dragAndDrop(source, dest).build().perform();
	}
	@Test(enabled=false)
	public void resizeable()
	{

		driver.navigate().to("https://jqueryui.com/");
		driver.findElement(By.linkText("Resizable")).click();
		WebElement frame =driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(frame);
		Actions action = new Actions(driver);
		WebElement source = driver.findElement(By.xpath("//div[contains(@class,'resizable-se')]"));
		action.dragAndDropBy(source, 50, 100).build().perform();
	}@Test(enabled=false)
	public void selectable()
	{
		driver.navigate().to("https://jqueryui.com/");
		driver.findElement(By.linkText("Selectable")).click();
		WebElement frame =driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(frame);
		Actions action = new Actions(driver);	
		WebElement ele1 = driver.findElement(By.xpath("//li[text()='Item 1']"));
		WebElement ele3 = driver.findElement(By.xpath("//li[text()='Item 3']"));
		WebElement ele5 = driver.findElement(By.xpath("//li[text()='Item 5']"));
		action.keyDown(Keys.CONTROL).click(ele1).click(ele3).click(ele5).build().perform();
	}
	@Test(enabled=false)
	public void sortable()
	{
		driver.navigate().to("https://jqueryui.com/");
		driver.findElement(By.linkText("Sortable")).click();
		WebElement frame =driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(frame);
		Actions action = new Actions(driver);
		WebElement ele1 = driver.findElement(By.xpath("//li[text()=\"Item 1\"]"));
		WebElement ele5 = driver.findElement(By.xpath("//li[text()=\"Item 5\"]"));
		action.clickAndHold(ele1).build().perform();
		action.moveToElement(ele5).release().build().perform();
	}
	@Test
	public void menuSearch() throws IOException
	{
		driver.navigate().to("https://jqueryui.com/");
		driver.findElement(By.linkText("Menu")).click();
		WebElement frame=driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(frame);
		Actions action = new Actions(driver);
		WebElement elmusic = driver.findElement(By.id("ui-id-9"));
		WebElement elrock = driver.findElement(By.id("ui-id-10"));
		action.moveToElement(elmusic).pause(Duration.ofSeconds(5)).moveToElement(elrock).build().perform();
		

		
		TakesScreenshot scrshot = (TakesScreenshot) driver;
		File srcfile = scrshot.getScreenshotAs(OutputType.FILE);
		String filepath = "./Screenshot/image-1.jpg";
		File destfile=new File(filepath);
		FileUtils.copyFile(srcfile, destfile);
		
	}
}