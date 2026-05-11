package week6.day15;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CreateAccount2 extends projectSpecificMethod{
	
	@Test
	public void createAccount2() throws InterruptedException{

	driver.findElement(By.xpath("//input[@id='accountName']")).sendKeys("AAAAE1F");
	driver.findElement(By.xpath("//textarea[@class='inputBox']")).sendKeys("Selenium Automation Tester1");
	WebElement findElement = driver.findElement(By.name("industryEnumId"));
	
	Select drop = new Select(findElement);
	drop.selectByVisibleText("Computer Software");
	
	WebElement findElement2 = driver.findElement(By.name("ownershipEnumId"));
	Select drop2 = new Select (findElement2);
	drop2.selectByVisibleText("S-Corporation");
	
	WebElement findElement3 = driver.findElement(By.id("dataSourceId"));
	Select drop3 = new Select (findElement3);
	drop3.selectByValue("LEAD_EMPLOYEE");
	
	WebElement findElement4 = driver.findElement(By.name("marketingCampaignId"));
	Select drop4 = new Select(findElement4);
	drop4.selectByIndex(6);
	
	WebElement findElement5 = driver.findElement(By.id("generalStateProvinceGeoId"));
	Select drop5 = new Select(findElement5);
	drop5.selectByValue("TX");
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	// Click button
	wait.until(ExpectedConditions.elementToBeClickable(By.className("smallSubmit"))).click();

	// Wait for text element
	WebElement text = wait.until(
	    ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='tabletext'])[3]"))
	);

	// Get text
	String value = text.getText();
	System.out.println(value);
	
}

}

