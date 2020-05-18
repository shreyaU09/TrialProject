package com.trial.web.lib;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ExcelLibrary {
	
	/* To get the Total number of rows(data filled rows) in the sheet */
	public int getExcelRowCount(String xlPath, String sheetName) {
		int iRowNum = -1;
		try {
			FileInputStream fis = new FileInputStream(xlPath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			iRowNum = wb.getSheet(sheetName).getLastRowNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iRowNum;
	}

	/* To Read the data from the cell */
	public String getExcelData(String xlPath, String sheetName, int rowNo, int cellNo) {
		String data = "";
		try {
			FileInputStream fis = new FileInputStream(xlPath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sheetName);
			data = sht.getRow(rowNo).getCell(cellNo).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/* To get the Number of columns from the Each row */
	public int getExcelCellCount(String xlPath, String sheetName, int rowNo) {
		int cellCount = 0;
		try {
			FileInputStream fis = new FileInputStream(xlPath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sheetName);
			cellCount = sht.getRow(rowNo).getLastCellNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellCount;
	}
	
	
	/*
	 *
	 * 
	 * Description:To read test data from excel sheet based on TestcaseID
	 */
	public static String[] toReadExcelData(String sFilepath, String sSheet, String sTestCaseID) {
		DataFormatter dataFormatter = new DataFormatter();
		String sData[] = null;
		try {
			//File Read
			FileInputStream fis = new FileInputStream(sFilepath);
			//Workbook Create
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			//Specify Sheet
			Sheet sht = wb.getSheet(sSheet);
			//Row Count
			int iRowNum = sht.getLastRowNum();
			//Find out the testcase based on testcaseID
			for (int i = 0; i <= iRowNum; i++) {
				//Match the Test Case ID
				if (sht.getRow(i).getCell(0).toString().equals(sTestCaseID)) {
					//Cell Count of MAtched Row
					int iCellNum = sht.getRow(i).getPhysicalNumberOfCells();
					// Initialize the String Array Length
					sData = new String[iCellNum];
					//Loop TO Read Column Data And Store it in Array
					for (int j = 0; j < iCellNum; j++) {
						Cell cell = sht.getRow(i).getCell(j);
						//Store Read Data Into Array
						sData[j] = dataFormatter.formatCellValue(cell);

					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sData;
	}

	@DataProvider
	public Integer[][] getData() {

		int row = new ExcelLibrary().getExcelRowCount("D:\\TrialProject\\TrialProject\\src\\test\\resources\\TestData\\TestDemoData.xlsx", "Demo");

		Integer[][] data = new Integer[row][1];
		for (int i = 1; i <= row; i++) {
			data[i - 1][0] = i;
		}
		return data;
		
	}

//				Integer[][] data = new Integer[1][1];
//		
//					data[0][0] = 1;
//
//				return data;
//	}
	public static int getColumnIndex(String filepath, String sSheet, String colName,String firstRowName) {
		//Read First Row as Excel Sheet Headings
		String[] firstRow = ExcelLibrary.toReadExcelData(filepath, sSheet, firstRowName);
		
		int index = 0;
		
		for (int i = 0; i < firstRow.length; i++) {
			
			if (firstRow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}
	
}
