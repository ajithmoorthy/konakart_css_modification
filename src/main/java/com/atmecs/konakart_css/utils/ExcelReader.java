package com.atmecs.konakart_css.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel reader class have the method to read the excel file using the apache
 * poi 
 */
public class ExcelReader {
	/**
	 * This excelDataProviderArray method take input as a
	 * @param path. 
	 * using that path to read the value from the .xlsx file 
	 * store the reading values in array 
	 * and @return excelarray as two dimensional array object.
	 * @throws IOException
	 */
	public String[][] excelDataProviderArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fileInput = new FileInputStream(file);
		XSSFWorkbook book = new XSSFWorkbook(fileInput);
		Sheet sheet = book.getSheetAt(0);
		int rowCount = sheet.getLastRowNum();
		rowCount += 1;
		// System.out.println(row1);
		int columnCount = sheet.getRow(0).getLastCellNum();
		String excelarray[][] = new String[rowCount][columnCount];
		int count = 0;
		for (Row row : sheet) {
			int count1 = 0;
			for (Cell cell : row) {
				String data = cell.toString();
				excelarray[count][count1] = data;
				count1++;
			}
			count++;
		}
		return excelarray;
	}
	/**
	 * This excelDataProviderArray method take input as a
	 * @param path and
	 * @param sheetIndex
	 * using that path and sheet index to read the values from the .xlsx file sheet what ever we want.
	 * store the reading values in array 
	 * and @return excelarray as two dimensional array object.
	 * @throws IOException
	 */
	public String[][] excelDataProviderArray(String path, int sheetIndex) throws IOException {
		File file = new File(path);
		FileInputStream fileInput = new FileInputStream(file);
		XSSFWorkbook book = new XSSFWorkbook(fileInput);
		Sheet sheet = book.getSheetAt(sheetIndex);
		int rowCount = sheet.getLastRowNum();
		rowCount += 1;
		// System.out.println(row1);
		int columnCount = sheet.getRow(0).getLastCellNum();
		String array[][] = new String[rowCount][columnCount];
		int count = 0;
		for (Row row : sheet) {
			int count1 = 0;
			for (Cell cell : row) {
				String Data = cell.toString();
				array[count][count1] = Data;
				count1++;
			}
			count++;
		}
		return array;
	}
}
