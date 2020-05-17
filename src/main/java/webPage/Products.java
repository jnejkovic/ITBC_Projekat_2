package webPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

import utility.ObjectsMap;

public class Products extends Page {

	public static void clickProductsIcon(WebDriver driver) {
		findByXpath(driver, "ProductsIcon").click();
	}

	public static void createProduct(WebDriver driver) {
		findByXpath(driver, "CreateProduct").click();
	}

	public static void inputProductName(WebDriver driver, String data) {
		findByXpath(driver, "ProductName").sendKeys(data);
	}

	public static void inputProductID(WebDriver driver, String data) {
		findByXpath(driver, "ProductID").sendKeys(data);
	}

	public static void inputProductDescription(WebDriver driver, String data) {
		findByXpath(driver, "ProductDescription").sendKeys(data);
	}

	public static void inputLongDescription(WebDriver driver, String data) {
		findByXpath(driver, "LongDescription").sendKeys(data);
	}

	public static WebElement priceField(WebDriver driver) {
		return findByXpath(driver, "Price");
	}

	public static void inputPrice(WebDriver driver, String data) {
		priceField(driver).sendKeys(data);
	}

	public static void inputApprovedUrl(WebDriver driver, String data) {
		findByXpath(driver, "ApprovedUrl").sendKeys(data);
	}

	public static void clickSaveChanges(WebDriver driver) {
		findByXpath(driver, "SaveChanges").click();

	}

	public static List<WebElement> editProduct(WebDriver driver) {
		return driver.findElements(By.cssSelector(ObjectsMap.getObjects("EditProduct")));
	}

	public static WebElement product(WebDriver driver, int i) {
		return editProduct(driver).get(i);
	}

	public static void clickProduct(WebDriver driver, int i) {
		product(driver, i).click();
	}

	public static void changePrice(WebDriver driver, String newPrice) {
		priceField(driver).clear();
		priceField(driver).sendKeys(newPrice);

	}

	public static List<WebElement> currentPrice(WebDriver driver) {
		return driver.findElements(By.cssSelector(ObjectsMap.getObjects("CurrentPrice")));
	}

	public static WebElement productCurrentPrice(WebDriver driver, int i) {
		return currentPrice(driver).get(i);
	}

	public static List<WebElement> delete(WebDriver driver) {
		return driver.findElements(By.name(ObjectsMap.getObjects("SelectToDelete")));
	}

	public static void selectToDelete(WebDriver driver, int i) {
		delete(driver).get(i).click();
	}

	public static void clickEditBTN(WebDriver driver) {
		findByXpath(driver, "EditBTN").click();
	}

	public static void clickDelete(WebDriver driver) {
		findByXpath(driver, "Delete").click();
	}

	public static void clickConfirmDeletion(WebDriver driver) {
		findByXpath(driver, "ConfirmDeletion").click();
	}

	public static List<WebElement> editProduct1(WebDriver driver) {
		return driver.findElements(By.xpath(ObjectsMap.getObjects("EditProduct1")));
	}

	public static WebElement product1(WebDriver driver, int i) {
		return editProduct1(driver).get(i);
	}

	public static void clickProduct1(WebDriver driver, int i) {
		product1(driver, i).click();
	}

	public static void changePrice1(WebDriver driver, int i, String newPrice) {
		product1(driver, i).clear();
		product1(driver, i).sendKeys(newPrice);

	}

	public static void clickSaveProductChanges(WebDriver driver) {
		findByXpath(driver, "SaveProductChanges").click();
	}

}
