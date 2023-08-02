package vtiger.GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class contains generic methods related to excel file
 * @author akarsh
 *
 */
public class ExcelUtility {
	/**
	 * This method will read data from excel sheet based on row and cell value
	 * @param sheet
	 * @param row
	 * @param cell
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String readDataFromExcell(String sheet,int row,int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(IConstantUtilities.ExcelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheet);
	    Row rw = sh.getRow(row);
	    Cell ce = rw.getCell(cell);
	    String value = ce.getStringCellValue();
	    wb.close();
	    return value;
	}
	/**
	 * This method will get the total rows utilized.
	 * @param sheet
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public int getRowCount(String sheet) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(IConstantUtilities.ExcelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheet);
		int rowcount = sh.getLastRowNum();
		wb.close();
		return rowcount;
		
		
	}
	public void writeDataintoExcel(String sheet,int row,int cell,String value) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(IConstantUtilities.ExcelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		Row rw = wb.getSheet(sheet).getRow(row);
		rw.createCell(cell).setCellValue(value);
		
		FileOutputStream fos=new FileOutputStream(IConstantUtilities.ExcelFilePath);
		wb.write(fos);
		System.out.println("data added");
		wb.close();
				
	}
	/**
	 * this method will load the data from the excel sheet to data provider
	 * @param sheet
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public Object[][] readMultipleData(String sheetname) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(IConstantUtilities.ExcelFilePath);
		
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
	    int lastRow = sh.getLastRowNum();
	    int lastCel = sh.getRow(0).getLastCellNum();
	    
	    Object[][] data=new Object[lastRow][lastCel];
	    
	    for(int i=0;i<lastRow;i++)
	    {
	    	for(int j=0;j<lastCel;j++)
	    	{
	    		data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
	    	}
	    }
	    return data;
	}
}
