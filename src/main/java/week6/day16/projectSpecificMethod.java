package week6.day16;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class projectSpecificMethod {
	
	RemoteWebDriver driver;
	
	String filename;
	
	@Parameters({"browserName", "url", "username", "passWord" })
	@BeforeMethod
	public void precondition(String browserName,String url, String username, String passWord) throws InterruptedException {
		
		switch (browserName) {
		case "Chrome":
			System.out.println("The current execusion is in Chrome");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--guest");
			driver = new ChromeDriver(options);
			break;
		case "FireFox":
			System.out.println("The current execution is in FireFox");
			driver = new FirefoxDriver();
			break;
		case "Edge":
			System.out.println("The current execution is in Edge");
			System.setProperty("webdriver.edge.driver", "./Driver/MicrosoftEdgeSetup.exe");
			driver = new EdgeDriver();
			break;
		case "Safari":
			System.out.println("The current execution is in Safari");
			break;
		default:
			System.out.println("The current execution is in IE");
			break;
		}

		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("username")).sendKeys(username);
		Thread.sleep(3000);
		driver.findElement(By.id("password")).sendKeys(passWord);
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Leads")).click();
	}

	@AfterMethod
	public void postCondition() {
		driver.close();
	}

	@DataProvider(name = "getValue")
	public String[][] fetchData() throws IOException{
		
		return ReadValuesFromExcel.getValue(filename);
	}
}