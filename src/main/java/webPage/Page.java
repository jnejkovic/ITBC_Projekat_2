package webPage;

import java.util.NoSuchElementException;
import org.apache.commons.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.ObjectsMap;

public class Page {
	WebDriver driver;

	public static String URL = "https://sandbox.2checkout.com/sandbox";

	public static void openHomePage(WebDriver driver) {
		driver.get(URL);

	}

	public static void navigateToHP(WebDriver driver) {
		driver.navigate().to(URL);
	}

	// Methods to finding objects (by xpath and id)
	public static WebElement findByXpath(WebDriver driver, String objects) {
		// WebElement element=
		// driver.findElement(By.xpath(ObjectsMap.getObjects(objects)));
		return driver.findElement(By.xpath(ObjectsMap.getObjects(objects)));
	}

	public static WebElement findById(WebDriver driver, String objects) {
		return driver.findElement(By.id(ObjectsMap.getObjects(objects)));
	}

	public static String generateUser() {

		int length = 8;
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		return generatedString;

	}

	public static String generateEmail() {
		return generateUser() + "@test.com";
	}

}
