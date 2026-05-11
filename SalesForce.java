package week6.day15;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class SalesForce {

	@Test
	@Parameters({ "url", "username", "password" })
	public void createLegalEntity(String url, String username, String password) throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-data-dir=/Users/vigneswaranmurugesan/SeleniumProfile");
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// Login to https://login.salesforce.com
		driver.get(url);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("Login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Click View All and click Legal Entities from App Launcher
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='App Launcher']"))).click();
		driver.findElement(By.xpath("//input[@type='search' and @role='combobox']")).sendKeys("Legal Entities");
		driver.findElement(By.xpath("//input[@type='search' and @role='combobox']")).sendKeys(Keys.ENTER);

		// Click on New Legal Entity
		driver.findElement(By.xpath("//li[.//div[@title='New']]")).click();
		String entityName = "Divya";
		driver.findElement(By.xpath("//input[@name='Name']")).sendKeys(entityName);
		
		// Enter the Company name as 'TestLeaf
		driver.findElement(By.xpath("//input[@name='CompanyName']")).sendKeys("TestLeaf");
		
		// Enter Description as 'Salesforces'.
		driver.findElement(By.xpath("//label[text()='Description']/following-sibling::div//textarea")).sendKeys("Salesforces");
		
		// Select Status as 'Active'
		WebElement Status = driver.findElement(By.xpath("//button[@aria-label='Status']"));
		driver.executeScript("arguments[0].click();", Status);
		driver.findElement(By.xpath("//span[@title='Active']")).click();
		
		// Click on Save
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		Thread.sleep(3000);
		//Verify the Alert message
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
		String entityNameFromToast = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'toast')]//a"))).getText();
		if(entityNameFromToast.equals(entityName)) {
		    System.out.println("Verification passed");
		} else {
		    System.out.println("Verification failed");
		}

		driver.quit();
	}
}