package week6.day15;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class CreateAccount extends projectSpecificMethod {

	@Test
	public void createAccount() {
		
		driver.findElement(By.id("accountName")).sendKeys("DivyaSri");
		driver.findElement(By.name("description")).sendKeys("Selenium Automation Tester.");
		driver.findElement(By.id("numberEmployees")).sendKeys("10");
		driver.findElement(By.id("officeSiteName")).sendKeys("LeafTaps");
		driver.findElement(By.className("smallSubmit")).click();
		System.out.println(driver.getTitle());
		
	}

}
