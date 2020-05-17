package webPageTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utility.ExcelUtils;
import utility.ObjectsMap;
import webPage.Login;
import webPage.Page;
import webPage.Registration;

public class LoginTest {
	public static WebDriver driver;

//	@BeforeClass
//	public void createDriver() {
//		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
//		ExcelUtils.setExcel(ObjectsMap.getObjects("Users"));
//		ExcelUtils.setWorkSheet("data");
//		ExcelUtils.setCellData(ObjectsMap.getObjects("Users"),0, 6, "login");
//	}

	// Login without registration
	@Test
	public void loginTest1() {
		Page.openHomePage(driver);
		Login.inputUsername(driver, Page.generateUser());
		Login.inputPassword(driver, "123qweaS");
		Login.clickLogIn(driver);
		Assert.assertEquals(Login.incorrectUserPass(driver).isDisplayed(), true, "This is bug");

	}

	// Login without username and password
	@Test
	public void loginTest2() {
		Login.navigateToHP(driver);
		Login.clickLogIn(driver);
		Assert.assertEquals(Login.missingUserPass(driver).isDisplayed(), true, "This is bug");
	}

	@Test
	public void loginRandomUsers() {
		ExcelUtils.setCellData(ObjectsMap.getObjects("Users"), 0, 6, "login");

		SoftAssert sa = new SoftAssert();
		Page.openHomePage(driver);
		for (int i = 1; i <= 30; i++) {
			Page.openHomePage(driver);
			Login.inputUsername(driver, ExcelUtils.getCellData(i, 2));
			Login.inputPassword(driver, ExcelUtils.getCellData(i, 3));
			Login.clickLogIn(driver);
			sa.assertEquals(driver.getCurrentUrl(), Registration.RegUrl);
			boolean ExistsAccountAvatar = Registration.accountAv(driver).isDisplayed();
			if (ExistsAccountAvatar) {

				Registration.clickAccountAv(driver);
				Registration.clickLogOut(driver);
				ExcelUtils.setCellData(ObjectsMap.getObjects("Users"), i, 6, "PASS");
			} else {
				System.out.println("Login failed");
				ExcelUtils.setCellData(ObjectsMap.getObjects("Users"), i, 6, "FAIL");

			}
		}
		sa.assertAll();
	}
}
