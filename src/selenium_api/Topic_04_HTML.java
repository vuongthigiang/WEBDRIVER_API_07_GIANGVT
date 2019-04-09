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
	private String newName, newDob, newAddr, newCity, newState, newPin, newPhone, newEmail, newPass;
	private String editAddr, editCity, editState, editPin, editPhone, editEmail, customerID;
	String user = "mngr161493";
	String pass = "harErAh";
	By nameByTextbox = By.xpath("//input[@name = 'name']");
	By dobByTextbox = By.xpath("//input[@name = 'dob']");
	By addressByTextarea = By.xpath("//textarea[@name = 'addr']");
	By cityByTextbox = By.xpath("//input[@name = 'city']");
	By stateByTextbox = By.xpath("//input[@name = 'state']");
	By pinByTextbox = By.xpath("//input[@name = 'pinno']");
	By phoneByTextbox = By.xpath("//input[@name = 'telephoneno']");
	By emailByTextbox = By.xpath("//input[@name = 'emailid']");
	By passwordByTextbox = By.xpath("//input[@name = 'password']");
	By submitByButton = By.xpath("//input[@name = 'sub']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/v4");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//khai bao
		newName = "Automation test";
		newDob = "0201-02-01";
		newAddr = "123 address";
		newCity = "Lao cai";
		newState = "Sa pa";
		newPin = "123456";
		newPhone = "01638097067";
		newEmail = "autotest" + Common.randomEmail() + "@gmail.com";
		newPass = "123123";
		
		editAddr = "234 edit address";
		editCity = "Ha noi";
		editState = "Ha noi";
		editEmail = "234567";
		editPhone = "0957867435";
		editPin = "edittest" + Common.randomEmail() + "@gmail.com";
	}

	@Test
	public void TC_01_CreatedCustomer() {
		driver.findElement(By.xpath("//input[@name = 'uid']")).sendKeys(user);
		driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys(pass);
		driver.findElement(By.xpath("//input[@name = 'btnLogin']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		//new customer
		driver.findElement(nameByTextbox).sendKeys(newName);
		driver.findElement(dobByTextbox).sendKeys(newDob);
		driver.findElement(addressByTextarea).sendKeys(newAddr);
		driver.findElement(cityByTextbox).sendKeys(newCity);
		driver.findElement(stateByTextbox).sendKeys(newState);
		driver.findElement(pinByTextbox).sendKeys(newPin);
		driver.findElement(phoneByTextbox).sendKeys(newPhone);
		driver.findElement(emailByTextbox).sendKeys(newEmail);
		driver.findElement(passwordByTextbox).sendKeys(newPass);
		driver.findElement(submitByButton).click();
		
		//get customer id
		customerID = driver.findElement(By.xpath("//td[text() = 'Customer ID']/following-sibling::td")).getText();
		//verify inout data
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Customer Name']/following-sibling::td")).getText(), newName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Birthdate']/following-sibling::td")).getText(), newDob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Address']/following-sibling::td")).getText(), newAddr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'State']/following-sibling::td")).getText(), newState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'City']/following-sibling::td")).getText(), newCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Pin']/following-sibling::td")).getText(), newPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Mobile No.']/following-sibling::td")).getText(), newPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Email']/following-sibling::td")).getText(), newEmail);
		
		
		System.out.println(driver.findElement(By.xpath("//td[text() = 'Customer Name']/following-sibling::td")).getText());
		System.out.println(driver.findElement(By.xpath("//td[text() = 'Birthdate']/following-sibling::td")).getText());
		System.out.println(driver.findElement(By.xpath("//td[text() = 'Address']/following-sibling::td")).getText());
		System.out.println(driver.findElement(By.xpath("//td[text() = 'State']/following-sibling::td")).getText());
		System.out.println(driver.findElement(By.xpath("//td[text() = 'City']/following-sibling::td")).getText());
		System.out.println(driver.findElement(By.xpath("//td[text() = 'Pin']/following-sibling::td")).getText());
		System.out.println(driver.findElement(By.xpath("//td[text() = 'Mobile No.']/following-sibling::td")).getText());
		System.out.println(driver.findElement(By.xpath("//td[text() = 'Email']/following-sibling::td")).getText());
		
	}

	public void TC_02_EditCustomer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name = 'cusid'")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name = 'AccSubmit']")).click();
		//verify name
		Assert.assertEquals(driver.findElement(nameByTextbox).getAttribute("value"), newName);	
		Assert.assertEquals(driver.findElement(addressByTextarea).getText(), newAddr);	
		
		//edit customer
		driver.findElement(addressByTextarea).clear();
		driver.findElement(cityByTextbox).clear();
		driver.findElement(stateByTextbox).clear();
		driver.findElement(pinByTextbox).clear();
		driver.findElement(phoneByTextbox).clear();
		driver.findElement(emailByTextbox).clear();
		
		
		driver.findElement(addressByTextarea).sendKeys(editAddr);
		driver.findElement(cityByTextbox).sendKeys(editCity);
		driver.findElement(stateByTextbox).sendKeys(editState);
		driver.findElement(pinByTextbox).sendKeys(editPin);
		driver.findElement(phoneByTextbox).sendKeys(editPhone);
		driver.findElement(emailByTextbox).sendKeys(editEmail);
		driver.findElement(submitByButton).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Address']/following-sibling::td")).getText(), editAddr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Email']/following-sibling::td")).getText(), editEmail);
	}
	@AfterClass
	public void afterClass() {

	}
}

