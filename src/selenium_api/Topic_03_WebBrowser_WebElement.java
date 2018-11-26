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

public class Topic_03_WebBrowser_WebElement {
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id ='mail']");
	By under18ByRadio = By.xpath("//input[@id ='under_18']");
	By educationByTextArea = By.xpath("//textarea[@id ='edu']");
	By addressByTextbox = By.xpath("//input[@id='address']");
	By jobRole1ByDropdown = By.xpath("//input[@id='job1']");
	By developmentByCheckbox = By.xpath("//input[@id='development']");
	By slide01 = By.xpath("//input[@id='slider-1']");
	By buttonEnable = By.xpath("//input[@id='button-enabled']");
	
	By passTextbox = By.xpath("//input[@id='password']");
	By radioByRadio = By.xpath("//input[@id='radio-disabled']");
	By biographyTextbox = By.xpath("//input[@id='bio']");
	By jobRole2ByDropdown = By.xpath("//input[@id='job2']");
	By checkboxByCheckbox = By.xpath("//input[@id='check-disbaled']");
	By slide02 = By.xpath("//input[@id='slider-2']");
	By buttonDisable = By.xpath("//input[@id='button-disabled']");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_CheckDisplay() {
		if(Common.isControlDisplay(emailTextbox, driver)) {
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
		}
		if(Common.isControlDisplay(under18ByRadio, driver)) {
			driver.findElement(under18ByRadio).click();
		}
		if(Common.isControlDisplay(educationByTextArea, driver)) {
			driver.findElement(educationByTextArea).sendKeys("Automation Testing");
		}
		
	}

	public void TC_02_CheckEnable() {
		//check enable
		Assert.assertTrue(Common.isControlEnable(emailTextbox, driver));
		Assert.assertTrue(Common.isControlEnable(educationByTextArea, driver));
		Assert.assertTrue(Common.isControlEnable(under18ByRadio, driver));
		Assert.assertTrue(Common.isControlEnable(jobRole1ByDropdown, driver));
		Assert.assertTrue(Common.isControlEnable(developmentByCheckbox, driver));
		Assert.assertTrue(Common.isControlEnable(slide01, driver));
		Assert.assertTrue(Common.isControlEnable(buttonEnable, driver));
		//check disable
		Assert.assertFalse(Common.isControlEnable(passTextbox, driver));
		Assert.assertFalse(Common.isControlEnable(radioByRadio, driver));
		Assert.assertFalse(Common.isControlEnable(biographyTextbox, driver));
		Assert.assertFalse(Common.isControlEnable(jobRole2ByDropdown, driver));
		Assert.assertFalse(Common.isControlEnable(checkboxByCheckbox, driver));
		Assert.assertFalse(Common.isControlEnable(slide02, driver));
		Assert.assertFalse(Common.isControlEnable(buttonDisable, driver));
	}

	public void TC_03_CheckSelected() {
		if(Common.isControlDisplay(under18ByRadio, driver)) {
			driver.findElement(under18ByRadio).click();
		}
		if(Common.isControlDisplay(developmentByCheckbox, driver)) {
			driver.findElement(developmentByCheckbox).click();
		}
	}

	@AfterClass
	public void afterClass() {
	}

	

}
