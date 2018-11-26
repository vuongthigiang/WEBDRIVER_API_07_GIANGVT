package selenium_api;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Common {
	
	public static int randomEmail() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
	}
	
	public static void clickElementByJavascript(WebDriver driver, WebElement element) {
	    JavascriptExecutor je = (JavascriptExecutor) driver;
	    je.executeScript("arguments[0].click();", element);
	}
	
	public static boolean isControlDisplay(By by, WebDriver driver) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element [" + by + "] is displayed");
			return true;
		} else {
			System.out.println("Element [" + by + "] is not displayed");
			return false;
		}
	}
	public static boolean isControlEnable(By by, WebDriver driver) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element [" + by + "] is enable");
			return true;
		} else {
			System.out.println("Element [" + by + "] is disable");
			return false;
		}
	}
	public static boolean isControlSelected(By by, WebDriver driver) {
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
