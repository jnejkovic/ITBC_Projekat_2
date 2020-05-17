package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import webPage.Page;

public class ExcelUtils {

	private static XSSFWorkbook wb;
	private static XSSFSheet sheet;
	private static XSSFRow row;
	private static XSSFCell cell;
	private static DataFormatter formatter;

	public static void setExcel(String path) {

		try {
			FileInputStream fis = new FileInputStream(path);

			wb = new XSSFWorkbook(fis);
		} catch (Exception e) {
			System.out.println("An error has occurred!");
			e.printStackTrace();
		}

	}

	public static void setWorkSheet(String data) {
		sheet = wb.getSheet(data);
	}

	public static void setWorkSheetByIndex(int index) {
		sheet = wb.getSheetAt(index);
	}

	public static String getCellData(int row, int column) {

		DataFormatter dataFormatter = new DataFormatter();
		String temp = dataFormatter.formatCellValue(sheet.getRow(row).getCell(column)).toString();

		return temp;
	}

	public static void setCellData(String path, int row, int column, String data) {
		cell = sheet.getRow(row).getCell(column, MissingCellPolicy.RETURN_BLANK_AS_NULL);
		if (cell == null)
			sheet.getRow(row).createCell(column).setCellValue(data);
		else
			cell.setCellValue(data);

		try {
			FileOutputStream fos = new FileOutputStream(path);
			wb.write(fos);
			fos.flush();
			fos.close();

		} catch (Exception e) {
			System.out.println("An error has occurred!");
		}

	}

	public static boolean closeExcel() {
		if (wb != null) {
			try {
				wb.close();
				wb = null;
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				wb = null;
				return false;
			}
		}
		return true;
	}

	public static void fillExcell(String path) {
		for (int i = 1; i <= 30; i++) {
			setCellData(path, i, 1, Page.generateEmail());
			setCellData(path, i, 2, Page.generateUser());
		}
	}

}
