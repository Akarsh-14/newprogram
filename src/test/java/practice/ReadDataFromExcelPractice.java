package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelPractice {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		//step:1 open the document in java readable format
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		
		//step:2 create a workbook
		Workbook wb = WorkbookFactory.create(fis);
		
		//step:3 get control of sheet
		Sheet sheet = wb.getSheet("contacts");
		
		//step:4 get control of row
		Row row = sheet.getRow(4);
		
		//step:5 get control of cell
		Cell cell = row.getCell(3);
		
		//step:6 read the data inside the cell
		String data = cell.getStringCellValue();
		System.out.println(data);
		

	}

}
