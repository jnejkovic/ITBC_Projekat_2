package webPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login extends Page {
	public static WebElement username(WebDriver driver) {
		return findByXpath(driver, "USERNAME");
	}

	public static WebElement password(WebDriver driver) {
		return findById(driver, "PasswordID");
	}

	public static WebElement logIn(WebDriver driver) {
		return findByXpath(driver, "LoginBTN");
	}

	public static WebElement missingUserPass(WebDriver driver) {
		return findByXpath(driver, "MissingUserPass");

	}

	public static WebElement incorrectUserPass(WebDriver driver) {
		return findByXpath(driver, "IncorrectUserPass");
	}

	public static void inputUsername(WebDriver driver, String data) {
		username(driver).sendKeys(data);
	}

	public static void inputPassword(WebDriver driver, String data) {
		password(driver).sendKeys(data);
	}

	public static void clickLogIn(WebDriver driver) {
		logIn(driver).click();

	}

}
