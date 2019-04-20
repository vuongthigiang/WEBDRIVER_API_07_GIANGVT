package selenium_api;

import org.testng.annotations.Test;

import com.google.common.base.Function;

import org.testng.annotations.BeforeTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_12_WebdriverWait2_PartII {
	WebDriver driver;
	WebDriverWait waitExplicit;

	@BeforeTest
	public void beforeTest() {
		// System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		// driver = new ChromeDriver();
		driver = new FirefoxDriver();
		// driver.manage().window().maximize();
		waitExplicit = new WebDriverWait(driver, 30);

	}


	public void TC_01_AjaxLoading_WithoutExplicit() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='contentWrapper']")).isDisplayed());

		String beforeSelect = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText();
		System.out.println(beforeSelect);

		driver.findElement(By.xpath("//a[text()='16']")).click();

		// wait cho element ke tiep duoc hien thi
		Assert.assertTrue(driver.findElement(By.xpath(
				"//span[@id='ctl00_ContentPlaceholder1_Label1' and contains(text(), 'Tuesday, April 16, 2019')]"))
				.isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//a[text()='16']/parent::td[@class='rcSelected']")).isDisplayed());
		String afterSelect = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText();

		System.out.println(afterSelect);

	}


	public void TC_01_AjaxLoading_WithExplicit() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='contentWrapper']")));

		String beforeSelect = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText();
		System.out.println(beforeSelect);

		driver.findElement(By.xpath("//a[text()='16']")).click();

		// wait cho ajax biến mất
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));

		// wait cho element duoc dien thi

		waitExplicit.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//a[text()='16']/parent::td[@class='rcSelected']")));

		String afterSelect = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText();
		Assert.assertEquals(afterSelect, "Tuesday, April 16, 2019");
		System.out.println(afterSelect);
	}
	@Test
	public void TC_02_FluentWait() {
		driver.get("https://daominhdam.github.io/fluent-wait/");
		WebElement countdount = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		waitExplicit.until(ExpectedConditions.visibilityOf(countdount));

		// Khởi tạo Fluent wait
		new FluentWait<WebElement>(countdount)
				// Tổng time wait là 15s
				.withTimeout(15, TimeUnit.SECONDS)
				// Tần số mỗi 1s check 1 lần
				.pollingEvery(1, TimeUnit.SECONDS)
				// Nếu gặp exception là find ko thấy element sẽ bỏ qua
				.ignoring(NoSuchElementException.class)
				// Kiểm tra điều kiện
				.until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement element) {
						// Kiểm tra điều kiện countdount = 00
						boolean flag = element.getText().endsWith("00");
						System.out.println("Time = " + element.getText());
						// return giá trị cho function apply
						return flag;
					}
				});
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	public Date getDateTime() {
		Date date = new Date();
		date = new Timestamp(date.getTime());
		return date;
	}
}
