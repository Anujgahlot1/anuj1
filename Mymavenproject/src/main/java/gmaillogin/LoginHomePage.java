package gmaillogin;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginHomePage {
	public Classdriversetup driversetup;
	public WebDriver driver;
	public WebDriverWait wait;
	public String passwordText;
	public String userStatus;
	public String duplicateUsername;
	public String passwordMismatchAlert;
	
	
	public LoginHomePage(String browser) {
		// TODO Auto-generated constructor stub
				
		driversetup = new Classdriversetup(browser);
		driver = driversetup.getDriverInstance();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	public void createAccountForMyPersonalUse(String firstname,String lastname,String username,String pswd)
	{
		driver.findElement(By.xpath("//span[text()=\"Create account\"]")).click();
		driver.findElement(By.xpath("//span[text()=\"For my personal use\"]")).click();
		driver.findElement(By.id("firstName")).sendKeys(firstname);
		driver.findElement(By.name("lastName")).sendKeys(lastname);
		driver.findElement(By.name("Username")).sendKeys(username);
		driver.findElement(By.name("Passwd")).sendKeys(pswd);
		driver.findElement(By.name("ConfirmPasswd")).sendKeys(pswd);
		driver.findElement(By.xpath("//input[@type=\"checkbox\"]")).click();
		
	}



}
