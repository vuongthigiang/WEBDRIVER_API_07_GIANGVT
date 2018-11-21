package selenium_api;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_WebElement {
	WebDriver driver;
	String loginURL = "http://live.guru99.com/index.php/customer/account/login/";
	String createURL = "http://live.guru99.com/index.php/customer/account/create/";
	

	@BeforeClass
	public void beforeClass() {
		// Chrome
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://live.guru99.com/");
	}

	@Test
	public void TC_01_VerifyURLandTitle() throws InterruptedException {
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");
		driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals(currentURL, loginURL);
		Thread.sleep(30);
		driver.findElement(By.xpath("//*[@id='login-form']/div/div[1]/div[2]/a/span/span")).click();
		Thread.sleep(30);
		String currentURL1 = driver.getCurrentUrl();
		Assert.assertEquals(currentURL1, createURL);
		driver.navigate().back();

	}
	public void TC_02_LoginEmpty() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.id("send2")).click();
		String requiredEmail = driver.findElement(By.id("advice-required-entry-email")).getText();
		String requiredPass = driver.findElement(By.id("advice-required-entry-pass")).getText();
		String validMessage = "This is a required field.";
		Assert.assertEquals(requiredEmail, validMessage);
		Assert.assertEquals(requiredPass, validMessage);	
	}
	public void TC_03_LoginWithEmailInvalid() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("23434234@12312.123123");
		driver.findElement(By.id("send2")).click();
		String acEmailInvalid = driver.findElement(By.id("advice-required-entry-email")).getText();
		String exEmailInvalid = "Please enter a valid email address. For example johndoe@domain.com.";
		Assert.assertEquals(acEmailInvalid, exEmailInvalid);	
	}
	public void TC_04_LoginWithPassLess6Chars() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		String acvalidPassnMess = driver.findElement(By.id("advice-required-entry-pass")).getText();
		String exvalidPassMess = "Please enter 6 or more characters without leading or trailing spaces.";
		Assert.assertEquals(acvalidPassnMess, exvalidPassMess);
	}
	public void TC_05_LoginWithPassIncorrect() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123123");
		driver.findElement(By.id("send2")).click();
		String acPassIncorrectMess = driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div/div/ul/li/ul/li/span")).getText();
		String exPassIncorrectMess = "Invalid login or password.";
		Assert.assertEquals(acPassIncorrectMess, exPassIncorrectMess);
	}
	public void TC_06_CreateAnAccount() throws InterruptedException {
		String randomEmail = "seleniumonline" + randomEmail() + "@gmail.com";
		
		driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id='login-form']/div/div[1]/div[2]/a/span/span")).click();
		driver.findElement(By.id("firstname")).sendKeys("Selenium");
		driver.findElement(By.id("lastname")).sendKeys("Online 07");
		driver.findElement(By.id("email_address")).sendKeys("randomEmail");
		driver.findElement(By.className("validate-password")).sendKeys("123123");
		driver.findElement(By.className("validate-cpassword")).sendKeys("123123");
		driver.findElement(By.xpath("//*[@id='form-validate']/d		iv[2]/button")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text() = 'Thank you for registering with Main Website Store.'")).isDisplayed());
		
	}
	
	
	public int randomEmail() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}