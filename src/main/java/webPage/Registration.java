package webPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Registration extends Page {

	public static String RegUrl = "https://sandbox.2checkout.com/sandbox/home/dashboard";

	public static void clickSignUp(WebDriver driver) {
		findByXpath(driver, "SignUp").click();
	}

	public static void inputUsername(WebDriver driver, String data) {
		findById(driver, "UserID").sendKeys(data);
	}

	public static void inputEmail(WebDriver driver, String data) {
		findById(driver, "EmailID").sendKeys(data);
	}

	public static void inputPass(WebDriver driver, String data) {
		findById(driver, "PassID").sendKeys(data);
	}

	public static void inputConfirmPass(WebDriver driver, String data) {
		findById(driver, "ConfirmPassID").sendKeys(data);
	}

	public static void clickSubmit(WebDriver driver) {
		findByXpath(driver, "SubmitBTN").click();
	}

	public static Select aboutYou(WebDriver driver) {
		return new Select(findById(driver, "AboutYouID"));
	}

	public static void chooseAnswer(WebDriver driver, int i) {
		aboutYou(driver).selectByIndex(i);
		;
	}

	public static boolean option1(WebDriver driver) {
		return findByXpath(driver, "Option1").isDisplayed();
	}

	public static void inputWebsite(WebDriver driver, String data) {
		findByXpath(driver, "Option1").sendKeys(data);
	}

	public static boolean userIcon(WebDriver driver) {
		return findByXpath(driver, "UserIcon").isDisplayed();
	}

	public static boolean emailIcon(WebDriver driver) {
		return findByXpath(driver, "EmailIcon").isDisplayed();
	}

	public static boolean passIcon(WebDriver driver) {
		return findByXpath(driver, "PassIcon").isDisplayed();
	}

	public static boolean confPassIcon(WebDriver driver) {
		return findByXpath(driver, "ConfPassIcon").isDisplayed();
	}

	public static boolean aboutYouIcon(WebDriver driver) {
		return findByXpath(driver, "AboutYouError").isDisplayed();

	}

	public static WebElement accountAv(WebDriver driver) {
		return findByXpath(driver, "AccountAvatar");
	}

	public static void clickAccountAv(WebDriver driver) {
		accountAv(driver).click();
	}

	public static void clickLogOut(WebDriver driver) {
		findById(driver, "LogOutID").click();

	}

	public static WebElement confirmLogin(WebDriver driver) {
		return findById(driver, "ConfirmLogin");
	}

	public static WebElement nekielement(WebDriver driver) {
		return driver
				.findElement(By.xpath("//span[contains(text(),'We found your email address in our system, are you')]"));
	}
//	public static void clickConfirmLogin(WebDriver driver) {
//		findById(driver,"ConfirmLogin").click();
//	}

}
