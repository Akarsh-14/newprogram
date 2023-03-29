package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataintoExcelPractice {

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
		Cell cell = row.createCell(6);
		
		//step:6 read the data inside the cell
		cell.setCellValue("virat");
		
		//step:7 open the document in write mode
		FileOutputStream fos=new FileOutputStream(".\\src\\test\\resources\\TestData.xlsx");
		
		//step:8 write the data
		wb.write(fos);
		System.out.println("data added");
     
		wb.close();
	}

}
