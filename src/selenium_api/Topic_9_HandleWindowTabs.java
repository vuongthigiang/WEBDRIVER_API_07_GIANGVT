package selenium_api;

import org.testng.annotations.BeforeClass;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_9_HandleWindowTabs {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public void TC_01_Window_ID() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");

		String parentWindowID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text() = 'Click Here']")).click();
		Common.switchToWindowByID(parentWindowID, driver);

		Assert.assertEquals(driver.getTitle(), "Google");

		Common.switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO", driver);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");

		Common.switchToWindowByTitle("Google", driver);
		Assert.assertEquals(driver.getTitle(), "Google");
	}
	public void TC_03_AddtToCompare() {
		driver.get("http://live.guru99.com/index.php/");

		String parentID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text() = 'Mobile']")).click();
		driver.findElement(By.xpath("//a[text() = 'Sony Xperia']/parent::h2/following-sibling::div[@class=''actions]//a[text()=''Add to compare]")).click();
		driver.findElement(By.xpath("//a[text() = 'Samsung Galaxy']/parent::h2/following-sibling::div[@class=''actions]//a[text()=''Add to compare]")).click();
		driver.findElement(By.xpath("//button[@title = 'Compare']")).click();

		Common.switchToWindowByTitle("Products Comparison List - Magento Commerce", driver);
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce, driver");
		Common.switchToWindowByID(parentID, driver);
		

	}


	@AfterClass
	public void afterClass() {
		driver.quit();

	}
}
