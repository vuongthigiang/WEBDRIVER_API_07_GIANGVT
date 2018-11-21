package selenium_api;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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


	@AfterClass
	public void afterClass() {
		driver = new ChromeDriver();
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_CheckDisplay() {
		if (isControlDisplay(under18ByRadio)) {
			driver.findElement(under18ByRadio).click();
		}
		if (isControlDisplay(developmentByCheckbox)) {
			driver.findElement(developmentByCheckbox).click();
		}
		
	}

	public void TC_02_CheckEnable() {
		//check enable
		isControlEnable(emailTextbox);
		isControlEnable(educationByTextArea);
		isControlEnable(under18ByRadio);
		isControlEnable(jobRole1ByDropdown);
		isControlEnable(developmentByCheckbox);
		isControlEnable(slide01);
		isControlEnable(buttonEnable);
		
		//check disable
		isControlEnable(passTextbox);
		isControlEnable(radioByRadio);
		isControlEnable(biographyTextbox);
		isControlEnable(jobRole2ByDropdown);
		isControlEnable(checkboxByCheckbox);
		isControlEnable(slide02);
		isControlEnable(buttonDisable);
		
	}

	public void TC_03_CheckSelected() {
		if (isControlDisplay(emailTextbox)) {
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
		}
		if (isControlDisplay(educationByTextArea)) {
			driver.findElement(educationByTextArea).sendKeys("Automation Testing");
		}
		if (isControlDisplay(under18ByRadio)) {
			driver.findElement(educationByTextArea).click();
		}
		if (isControlDisplay(addressByTextbox)) {
			driver.findElement(educationByTextArea).sendKeys("Automation Testing");
		}
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
