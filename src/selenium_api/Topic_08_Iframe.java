package selenium_api;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_08_Iframe {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public void TC_01_Iframe() {
		driver.get("http://www.hdfcbank.com/");
		//div[@id='container-div']/img
		List<WebElement>  notification= driver.findElements(By.xpath("//iframe[@id='viruzy']"));
		if(notification.size() > 0)
		{
			driver.switchTo().frame(notification.get(0));
			Common.clickElementByJavascript(driver, driver.findElement(By.cssSelector("#div-close")));
			driver.findElement(By.xpath("//div[@id=''div-close]")).click();
			driver.switchTo().defaultContent();
		}
			
			WebElement slidingIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
			driver.switchTo().frame(slidingIframe);
			
			List<WebElement>  notifications = driver.findElements(By.xpath("//iframe[@id='viruzy']"));
			
			String messageText = driver.findElement(By.xpath("//span[@id='messageText']")).getText();
			Assert.assertEquals(messageText, "What are you looking for?");
		

	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}
}