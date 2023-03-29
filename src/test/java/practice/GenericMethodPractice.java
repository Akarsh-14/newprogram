package practice;

import vtiger.GenericUtilities.ExcelUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;

public class GenericMethodPractice {

	public static void main(String[] args) throws Throwable {
		PropertyFileUtility putil=new PropertyFileUtility();
		JavaUtility jutil=new JavaUtility();
		String URL = putil.readDataFromPropertyFile("url");
		System.out.println(URL);
		
		ExcelUtility eutil=new ExcelUtility();
		String value = eutil.readDataFromExcell("Contacts", 4, 2);
		System.out.println(value);
		
		System.out.println(eutil.getRowCount("contacts"));
		
		
		eutil.writeDataintoExcel("contacts", 4, 6,"anushka");
		System.out.println(jutil.getSystemDate());
		System.out.println(jutil.getSystemDateInFormat());
		System.out.println(jutil.getRandomNumber());
	}

}
