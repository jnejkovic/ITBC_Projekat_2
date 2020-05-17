package webPageTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import utility.ExcelUtils;
import utility.ObjectsMap;
import webPage.Login;
import webPage.Products;

public class ProductsTest {
	WebDriver driver;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		ExcelUtils.setExcel(ObjectsMap.getObjects("Products"));
		ExcelUtils.setWorkSheetByIndex(0);
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void createProducts() {
		SoftAssert sa = new SoftAssert();

		Login.openHomePage(driver);
		Login.inputUsername(driver, "jelena1234");
		Login.inputPassword(driver, "123qweASD");
		Login.clickLogIn(driver);
		for (int i = 1; i <= 5; i++) {
			Products.clickProductsIcon(driver);
			Products.createProduct(driver);
			Products.inputProductName(driver, ExcelUtils.getCellData(i, 1));
			Products.inputProductID(driver, ExcelUtils.getCellData(i, 0));
			Products.inputProductDescription(driver, ExcelUtils.getCellData(i, 2));
			Products.inputLongDescription(driver, ExcelUtils.getCellData(i, 3));
			Products.inputPrice(driver, ExcelUtils.getCellData(i, 4));
			Products.inputApprovedUrl(driver, ExcelUtils.getCellData(i, 5));
			Products.clickSaveChanges(driver);
			boolean ActualResult = driver.getCurrentUrl()
					.contains("https://sandbox.2checkout.com/sandbox/products/edit_product?product_id");
			sa.assertEquals(ActualResult, true);
			Products.clickProductsIcon(driver);
		}
		sa.assertAll();

	}

//  // Increase price for each products (first way)
//	@Test(priority = 2)
//	public void increasePrice() {
//		SoftAssert sa = new SoftAssert();
//		Products.clickProductsIcon(driver);
//
//		for (int i = 0; i < Products.editProduct(driver).size(); i++) {
//
//			String oldPrice = Products.productCurrentPrice(driver, i).getText();
//			double expectedPrice = Double.parseDouble(oldPrice) + 100;
//			String expectedPriceS = String.format("%.2f", expectedPrice);
//			Products.clickProduct(driver, i);
//			double newPrice = Double.parseDouble(Products.priceField(driver).getAttribute("value")) + 100;
//			Products.changePrice(driver, Double.toString(newPrice));
//			Products.clickSaveChanges(driver);
//			Products.clickProductsIcon(driver);
//			String currentPrice = Products.productCurrentPrice(driver, i).getText();
//			sa.assertEquals(currentPrice, expectedPriceS);
//			if (currentPrice.equals(expectedPriceS)) {
//				ExcelUtils.setCellData(ObjectsMap.getObjects("Products"), i + 1, 4, currentPrice);
//			} else {
//				ExcelUtils.setCellData(ObjectsMap.getObjects("Products"), i + 1, 4, "Error");
//			}
//			Products.clickProductsIcon(driver);
//		}
//		for (int i = 0; i < Products.delete(driver).size(); i++) {
//			Products.selectToDelete(driver, i);
//		}
//		Products.clickDelete(driver);
//		Products.clickConfirmDeletion(driver);
//		sa.assertAll();
//	}
	// Increase price for each products (second way)
	@Test(priority = 3)
	public void increasePrice1() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String expectedPriceS;

		SoftAssert sa = new SoftAssert();
		Products.clickProductsIcon(driver);
		List<String> expectedPriceList = new ArrayList<String>();
		List<WebElement> oldPriceList = Products.currentPrice(driver);

		for (int i = 0; i < oldPriceList.size(); i++) {
			String oldPrice = oldPriceList.get(i).getText();
			double expectedPrice = Double.parseDouble(oldPrice) + 100;
			expectedPriceS = String.format("%.2f", expectedPrice);
			expectedPriceList.add(expectedPriceS);
		}

		Products.clickEditBTN(driver);

		for (int i = 0; i < Products.editProduct1(driver).size(); i++) {
			double newPrice = Double.parseDouble(Products.product1(driver, i).getAttribute("value")) + 100;
			Products.changePrice1(driver, i, Double.toString(newPrice));
		}
		js.executeScript("window.scrollBy(0,600)");
		Products.clickSaveProductChanges(driver);
		Products.clickProductsIcon(driver);

		List<WebElement> editedPriceList = Products.currentPrice(driver);
		for (int i = 0; i < 5; i++) {

			String currentPrice = editedPriceList.get(i).getText();
			sa.assertEquals(currentPrice, expectedPriceList.get(i));

			if (currentPrice.equals(expectedPriceList.get(i))) {
				ExcelUtils.setCellData(ObjectsMap.getObjects("Products"), i + 1, 4, currentPrice);
			} else {
				ExcelUtils.setCellData(ObjectsMap.getObjects("Products"), i + 1, 4, "Error");
			}
		}
		sa.assertAll();
		for (int i = 0; i < Products.delete(driver).size(); i++) {
			Products.selectToDelete(driver, i);
		}
		Products.clickDelete(driver);
		Products.clickConfirmDeletion(driver);

	}

	@AfterSuite
	public void TestQuit() {
		ExcelUtils.closeExcel();
		driver.close();
	}
}
