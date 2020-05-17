package webPageTest;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utility.ExcelUtils;
import utility.ObjectsMap;
import webPage.Login;
import webPage.Page;
import webPage.Registration;

public class RegistrationTest {
	WebDriver driver;

	@BeforeSuite
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		ExcelUtils.setExcel(ObjectsMap.getObjects("Users"));
		ExcelUtils.setWorkSheet("data");
		ExcelUtils.setCellData(ObjectsMap.getObjects("Users"), 0, 5, "registration");
		ExcelUtils.fillExcell(ObjectsMap.getObjects("Users"));
		driver.manage().window().maximize();

	}

	// Manually register one user
	@Test(priority = 1)
	public void oneUserReg() {
		Registration.openHomePage(driver);
		Registration.clickSignUp(driver);
		Registration.inputUsername(driver, Page.generateUser());
		Registration.inputEmail(driver, Page.generateEmail());
		Registration.inputPass(driver, "123qweAS");
		Registration.inputConfirmPass(driver, "123qweAS");
		Registration.chooseAnswer(driver, 2);
		if (Registration.option1(driver)) {
			Registration.inputWebsite(driver, "https://www.latazamlata.rs");
		}
		Registration.clickSubmit(driver);
		if (driver.findElements(By.id(ObjectsMap.getObjects("ConfirmLogin"))).size() != 0)
			Registration.confirmLogin(driver).click();

		Assert.assertEquals(driver.getCurrentUrl(), Registration.RegUrl);
		Registration.clickAccountAv(driver);
		Registration.clickLogOut(driver);

	}

	// Missing username registration
	@Test(priority = 2)
	public void registration1() {
		Registration.openHomePage(driver);
		Registration.clickSignUp(driver);
		Registration.inputEmail(driver, Page.generateEmail());
		Registration.inputPass(driver, "123qweAS");
		Registration.inputConfirmPass(driver, "123qweAS");
		Registration.chooseAnswer(driver, 0);
		if (Registration.option1(driver))
			Registration.inputWebsite(driver, "https://www.latazamlata.rs");
		Registration.clickSubmit(driver);
		if (driver.findElements(By.id(ObjectsMap.getObjects("ConfirmLogin"))).size() != 0)
			Registration.confirmLogin(driver).click();

		Assert.assertEquals(Registration.userIcon(driver), true);

	}

	// Missing email registration
	@Test(priority = 3)
	public void registration2() {
		Registration.openHomePage(driver);
		Registration.clickSignUp(driver);
		Registration.inputUsername(driver, Page.generateUser());
		Registration.inputPass(driver, "123qweAS");
		Registration.inputConfirmPass(driver, "123qweAS");
		Registration.chooseAnswer(driver, 1);
		if (Registration.option1(driver))
			Registration.inputWebsite(driver, "https://www.latazamlata.rs");
		Registration.clickSubmit(driver);
		if (driver.findElements(By.id(ObjectsMap.getObjects("ConfirmLogin"))).size() != 0)
			Registration.confirmLogin(driver).click();
		Assert.assertEquals(Registration.emailIcon(driver), true);

	}

	// Missing password registration
	@Test(priority = 4)
	public void registration3() {
		Registration.openHomePage(driver);
		Registration.clickSignUp(driver);
		Registration.inputUsername(driver, Page.generateUser());
		Registration.inputEmail(driver, Page.generateEmail());
		Registration.inputConfirmPass(driver, "123qweAS");
		Registration.chooseAnswer(driver, 3);
		if (Registration.option1(driver))
			Registration.inputWebsite(driver, "https://www.latazamlata.rs");
		Registration.clickSubmit(driver);
		if (driver.findElements(By.id(ObjectsMap.getObjects("ConfirmLogin"))).size() != 0)
			Registration.confirmLogin(driver).click();
		Assert.assertEquals(Registration.passIcon(driver), true);

	}

	// Missing confirm password registration
	@Test(priority = 5)
	public void registration4() {
		Registration.openHomePage(driver);
		Registration.clickSignUp(driver);
		Registration.inputUsername(driver, Page.generateUser());
		Registration.inputEmail(driver, Page.generateEmail());
		Registration.inputPass(driver, "123qweAS");
		Registration.chooseAnswer(driver, 3);
		if (Registration.option1(driver))
			Registration.inputWebsite(driver, "https://www.latazamlata.rs");
		Registration.clickSubmit(driver);
		if (driver.findElements(By.id(ObjectsMap.getObjects("ConfirmLogin"))).size() != 0)
			Registration.confirmLogin(driver).click();
		Assert.assertEquals(Registration.confPassIcon(driver), true);
	}

	// Missing about you
	@Test(priority = 6)
	public void registration5() {
		Login.openHomePage(driver);
		Registration.clickSignUp(driver);
		Registration.inputUsername(driver, Page.generateUser());
		Registration.inputEmail(driver, Page.generateEmail());
		Registration.inputPass(driver, "123qweAS");
		Registration.inputConfirmPass(driver, "123qweAS");
		Registration.clickSubmit(driver);
		if (driver.findElements(By.id(ObjectsMap.getObjects("ConfirmLogin"))).size() != 0)
			Registration.confirmLogin(driver).click();
		Assert.assertEquals(Registration.aboutYouIcon(driver), true);

	}

	// Register random users
	@Test(priority = 7)
	public void registerRandomUsers() {
		SoftAssert sa = new SoftAssert();

		Page.navigateToHP(driver);
		for (int i = 1; i <= 30; i++) {
			if (driver.getCurrentUrl() != Page.URL)
				Page.navigateToHP(driver);
			;
			Registration.clickSignUp(driver);
			Registration.inputUsername(driver, ExcelUtils.getCellData(i, 2));
			Registration.inputEmail(driver, ExcelUtils.getCellData(i, 1));
			Registration.inputPass(driver, ExcelUtils.getCellData(i, 3));
			Registration.inputConfirmPass(driver, ExcelUtils.getCellData(i, 3));
			Registration.chooseAnswer(driver, Integer.parseInt(ExcelUtils.getCellData(i, 4).trim()));
			if (Registration.option1(driver))
				Registration.inputWebsite(driver, "https://www.latazamlata.rs");
			Registration.clickSubmit(driver);
			if (driver.findElements(By.id(ObjectsMap.getObjects("ConfirmLogin"))).size() != 0)
				Registration.confirmLogin(driver).click();
			sa.assertEquals(driver.getCurrentUrl(), Registration.RegUrl);

			if (driver.getCurrentUrl().contentEquals(Registration.RegUrl)) {
				Registration.clickAccountAv(driver);
				Registration.clickLogOut(driver);
				ExcelUtils.setCellData(ObjectsMap.getObjects("Users"), i, 5, "PASS");
			} else {
				System.out.println("Registration Unsuccessful");
				ExcelUtils.setCellData(ObjectsMap.getObjects("Users"), i, 5, "FAIL");

			}
		}
		sa.assertAll();
	}
}
