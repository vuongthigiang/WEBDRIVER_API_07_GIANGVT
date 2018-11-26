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
		if(isControlDisplay(emailTextbox)) {
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
		}
		if(isControlDisplay(under18ByRadio)) {
			driver.findElement(under18ByRadio).click();
		}
		if(isControlDisplay(educationByTextArea)) {
			driver.findElement(educationByTextArea).sendKeys("Automation Testing");
		}
		
	}

	public void TC_02_CheckEnable() {
		//check enable
		Assert.assertTrue(isControlEnable(emailTextbox));
		Assert.assertTrue(isControlEnable(educationByTextArea));
		Assert.assertTrue(isControlEnable(under18ByRadio));
		Assert.assertTrue(isControlEnable(jobRole1ByDropdown));
		Assert.assertTrue(isControlEnable(developmentByCheckbox));
		Assert.assertTrue(isControlEnable(slide01));
		Assert.assertTrue(isControlEnable(buttonEnable));
		//check disable
		Assert.assertFalse(isControlEnable(passTextbox));
		Assert.assertFalse(isControlEnable(radioByRadio));
		Assert.assertFalse(isControlEnable(biographyTextbox));
		Assert.assertFalse(isControlEnable(jobRole2ByDropdown));
		Assert.assertFalse(isControlEnable(checkboxByCheckbox));
		Assert.assertFalse(isControlEnable(slide02));
		Assert.assertFalse(isControlEnable(buttonDisable));
	}

	public void TC_03_CheckSelected() {
		if(isControlDisplay(under18ByRadio)) {
			driver.findElement(under18ByRadio).click();
		}
		if(isControlDisplay(developmentByCheckbox)) {
			driver.findElement(developmentByCheckbox).click();
		}
	}

	@AfterClass
	public void afterClass() {
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
	public boolean isControlEnable(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element [" + by + "] is enable");
			return true;
		} else {
			System.out.println("Element [" + by + "] is disable");
			return false;
		}
	}
	public boolean isControlSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element [" + by + "] is selected");
			return true;
		} else {
			System.out.println("Element [" + by + "] is not selected");
			return false;
		}
	}

}
