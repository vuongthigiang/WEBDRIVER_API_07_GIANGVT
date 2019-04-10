package selenium_api;

import org.testng.annotations.Test;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import org.testng.annotations.BeforeTest;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
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

	String firefoxPath = rootFolder + "\\uploadFiles\\FirefoxFileUpload.exe";
	String chromePath = rootFolder + "\\uploadFiles\\ChromeFileUpload.exe";
	String iePath = rootFolder + "\\uploadFiles\\IEFileUpload.exe";

	@BeforeTest
	public void beforeTest() {
		// firefox
		// selenium 2.xx + firefox<= 47 + không cần dùng gecko
		// selenium 3.xx + firefox >= 48 + phải dùng gecko
		
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
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

	public void TC_02_UploadMultiple() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(fileNamPath01 + "\n" + fileNamPath02 + "\n" + fileNamPath03);
		Thread.sleep(1000);

		List<WebElement> startButtons = driver.findElements(By.xpath("//span[text() = 'Start']"));

		for (WebElement startButton : startButtons) {
			startButton.click();
			Thread.sleep(1000);
		}
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + fileName01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + fileName02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = '" + fileName03 + "']")).isDisplayed());

	}

	@Test
	public void TC_03_AutoIT() throws Exception {
		
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		if (driver.toString().contains("firefox")) {
			WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
			Common.clickElementByJavascript(driver, uploadFile);
			Thread.sleep(3000);

			// Excute file exe
			Runtime.getRuntime().exec(new String[] { firefoxPath, fileNamPath01 });
			Thread.sleep(4000);
		} else if (driver.toString().contains("chrome")) {
			WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));
			uploadFile.click();
			Thread.sleep(3000);

			// Excute file exe
			Runtime.getRuntime().exec(new String[] { chromePath, fileNamPath01 });
			Thread.sleep(4000);
		} else {
			WebElement uploadFile = driver.findElement(By.xpath("//span[contain(text(), 'Add file...')]"));
			uploadFile.click();
			Thread.sleep(3000);

			// Excute file exe
			Runtime.getRuntime().exec(new String[] { iePath, fileNamPath01 });
			Thread.sleep(4000);
		}

	}
	
	@Test
	public void TC_04_Robot() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	    // Specify the file location with extension
        StringSelection select = new  StringSelection(fileNamPath01);

        // Copy to clipboard
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

        if (driver.toString().contains("chrome")  || driver.toString().contains("firefox")) {
            WebElement uploadFile =  driver.findElement(By.cssSelector(".fileinput-button"));
            uploadFile.click();
            Thread.sleep(1000);
        } else {
            System.out.println("Go to IE");
            WebElement uploadFile =  driver.findElement(By.xpath("//input[@type='file']"));
            Common.clickElementByJavascript(driver, uploadFile);
            Thread.sleep(1000);
        }

        Robot robot = new Robot();
        Thread.sleep(1000);

        // Nhan phim Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Nhan xuong Ctrl - V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        // Nha Ctrl - V
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        Thread.sleep(1000);

        // Nhan Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public void TC_05_UploadFile() {
		driver.get("https://encodable.com/uploaddemo/");
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
	}
	
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
