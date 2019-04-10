package selenium_api;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Topic_05_HandleDropdownListJava {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javaExcutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		javaExcutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void TC_01_HTML_Dropdown() throws InterruptedException {
		/*
		 * Step 01 - Truy cập vào trang:
		 * https://daominhdam.github.io/basic-form/index.html Step 02 - Kiểm tra
		 * dropdown Job Role 01 không hỗ trợ thuộc tính multi-select Step 03 - Chọn giá
		 * trị Automation Tester trong dropdown bằng phương thức selectVisible Step 04 -
		 * Kiểm tra giá trị đã được chọn thành công Step 05 - Chọn giá trị Manual Tester
		 * trong dropdown bằng phương thức selectValue Step 06 - Kiểm tra giá trị đã
		 * được chọn thành công Step 07 - Chọn giá trị Mobile Tester trong dropdown bằng
		 * phương thức selectIndex Step 08 - Kiểm tra giá trị đã được chọn thành công
		 * Step 09 - Kiểm tra dropdown có đủ 5 giá trị
		 */

		driver.get("https://daominhdam.github.io/basic-form/index.html");

		Select select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		/*
		 * <select id="job1" name="user_job1"> <option value="automation">Automation
		 * Tester</option> <option value="manual">Manual Tester</option> <option
		 * value="website">Website Tester</option> <option value="mobile">Mobile
		 * Tester</option> <option disabled="disabled" value="disabled">Dropdown
		 * disable</option> </select>
		 */
		// chon
		// select.selectByIndex(0); // khong dung
		// select.selectByValue("manual");// khong dung
		select.selectByVisibleText("Automation Tester");
		select.getFirstSelectedOption().getText();
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");
		Thread.sleep(3000);

		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");
		Thread.sleep(3000);

		select.selectByIndex(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");
		Thread.sleep(3000);

		Assert.assertEquals(select.getOptions().size(), 5);

		// Assert.assertTrue(select.isMultiple());
		// Assert.assertFalse(select.isMultiple());
	}

	/*
	 * Click vào dropdown Wait để tất cả phần tử trong dropdown được hiển thị Get
	 * tất cả item trong dropdown vào 1 list element (List <WebElement>) Dùng vòng
	 * lặp for duyệt qua từng phần tử sau đó getText Nếu actual text = expected text
	 * thì click vào phần tử đó và break khỏi vòng lặp
	 */
	@Test
	public void TC_02_JQuery_Dropdown() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemCustomerDropdown("//div[@class='demo']", "//span[@id='number-button']",
				"//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "19");
		Assert.assertTrue(driver
				.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']"))
				.isDisplayed());
		Thread.sleep(3000);
	}

	public void selectItemCustomerDropdown(String scrollToXpath, String parentXpath, String childXpath,
			String expectedItem) throws Exception

	{
		// scroll toi element cha
		javaExcutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(scrollToXpath)));

		// click vao dropdown
		WebElement element = driver.findElement(By.xpath(parentXpath));
		element.click();

		// get tat ca item trong dropdown vao list
		List<WebElement> childList = driver.findElements(By.xpath(childXpath));

		// wait de tat ca cac phan tu trong dropdown duoc hien thi
		waitExplicit.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(childXpath))));

		for (WebElement child : childList) {
			String textItem = child.getText();
			if (textItem.equals(expectedItem)) {
				javaExcutor.executeScript("arguments[0].scrollIntoView(true);", child);
				Thread.sleep(2000);
				child.click();
				break;
			}

		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}
}
