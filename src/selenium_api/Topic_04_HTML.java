package selenium_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_HTML {
	WebDriver driver;
	String user = "mngr161493";
	String pass = "harErAh";

	@AfterClass
	public void afterClass() {
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/v4");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_TextBox_TextArea() {
		By logOut = By.xpath("//a[text()='Log out']");
		Assert.assertTrue(isControlDisplay(logOut));
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		

	}

	public void TC_02_HtmlDropDown_List() {

	}

	public void TC_03_CustomDropDown_List() {

	}

	@BeforeClass
	public void beforeClass() {
	}
	public boolean isControlDisplay(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element [" + by + "] is displayed");
			return true;
		} else {
			System.out.println("Element [" + by + "] is not displayed");
			return false;
		}
	}

}
