package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_12_WebdriverWait {
	WebDriver driver;
	WebDriverWait waitExplicit;
	By startButton = By.xpath("//div[@id='start']/button");
	By loadingIcon = By.xpath("//div[@id='loading']/img");
	By helloWord = By.xpath("//div[@id='finish']/h4[text()='Hello World!']");

	@BeforeTest
	public void beforeTest() {
		// System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		// driver = new ChromeDriver();
		driver = new FirefoxDriver();
		// driver.manage().window().maximize();

	}

	public void TC_01_ImplicitWait_2S() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(startButton).click();
		// 5s render ra helloword
		// implicitlyWait anh huong cho findelement
		Assert.assertTrue(driver.findElement(helloWord).isDisplayed());
	}

	public void TC_02_ImplicitWait_5S() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(startButton).click();
		// 5s render ra helloword
		// implicitlyWait anh huong cho findelement
		Assert.assertTrue(driver.findElement(helloWord).isDisplayed());
	}

	public void TC_03_LoadingIconVisible() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 5);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(startButton).click();
		// 5s render ra helloword
		// implicitlyWait anh huong cho findelement
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		Assert.assertTrue(driver.findElement(helloWord).isDisplayed());
	}

	public void TC_04_HelloWordTextVisible() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 2);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(startButton).click();
		// 5s render ra helloword
		// implicitlyWait anh huong cho findelement
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloWord));
		Assert.assertTrue(driver.findElement(helloWord).isDisplayed());
	}

	@Test
	public void TC_05_HelloWordText_LoadingIcon_NoLongerInDOM() {
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 5);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		// khong visible khong co trong DOM

		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(helloWord));
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));

		driver.findElement(startButton).click();

		// khong visible co trong DOM
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));

		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(startButton));

		Assert.assertTrue(driver.findElement(helloWord).isDisplayed());

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
