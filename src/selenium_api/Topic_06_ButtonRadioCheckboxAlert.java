package selenium_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import static org.junit.Assert.assertArrayEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_ButtonRadioCheckboxAlert {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}


	public void TC_01_HandleButton() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class ='footer']//a[text()='My Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");

		WebElement createAnAccountButton = driver.findElement(By.xpath("//a[@title = 'Create an Account']"));

		Common.clickElementByJavascript(driver, createAnAccountButton);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");

	}

	public void TC_02_HandleCheckbox() throws InterruptedException {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		WebElement dualZoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		Common.clickElementByJavascript(driver, dualZoneCheckbox);

		Assert.assertTrue(dualZoneCheckbox.isSelected());

		Common.clickElementByJavascript(driver, dualZoneCheckbox);
		Assert.assertFalse(dualZoneCheckbox.isSelected());

	}
	@Test
	public void TC_03_HandleRadioButton() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		WebElement petrolRadioButton = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		Common.clickElementByJavascript(driver, petrolRadioButton);

		Assert.assertTrue(petrolRadioButton.isSelected());

		Common.clickElementByJavascript(driver, petrolRadioButton);
		Assert.assertFalse(petrolRadioButton.isSelected());
	}

	public void TC_04_HandleAlert() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}
}
