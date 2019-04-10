package selenium_api;

import org.testng.annotations.Test;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_11_UploadFile {
	WebDriver driver;

	// get relative path
	String rootFolder = System.getProperty("user.dir");
	String fileName01 = "image1.jpg";
	String fileName02 = "image2.jpg";
	String fileName03 = "image3.jpg";

	// get path file
	String fileNamPath01 = rootFolder + "\\uploadFiles\\" + fileName01;
	String fileNamPath02 = rootFolder + "\\uploadFiles\\" + fileName02;
	String fileNamPath03 = rootFolder + "\\uploadFiles\\" + fileName03;

	String[] files = { fileNamPath01, fileNamPath02, fileNamPath03 };

	@BeforeTest
	public void beforeTest() {
		//firefox 
		//selenium 2.xx + firefox<= 47 + không cần dùng gecko
		//selenium 3.xx + firefox >= 48 + phải dùng gecko
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe"); 
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}


	public void TC_01_SendKeys() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		for (String file : files) {
			WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
			uploadFile.sendKeys(file);
			Thread.sleep(1000);

		}

		driver.findElement(By.xpath("//span[text() = 'Start upload']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + fileName01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + fileName02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + fileName03 + "']")).isDisplayed());
	}
	
	@Test
	public void TC_02_UploadMultiple() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

			WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
			uploadFile.sendKeys(fileNamPath01 + "\n" + fileNamPath02 + "\n" + fileNamPath03);
			Thread.sleep(1000);
			
			List <WebElement> startButtons = driver.findElements(By.xpath("//span[text() = 'Start']"));
			
			for(WebElement startButton: startButtons ) {
				startButton.click();
				Thread.sleep(1000);
			}
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + fileName01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + fileName02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + fileName03 + "']")).isDisplayed());
		
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
