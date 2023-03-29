package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFilePractice {

	public static void main(String[] args) throws IOException {
		//step:1 open the file in a java readable format
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		
		//step:2 create object of properties from java.util package
		Properties pro=new Properties();
		
		//step:3 load the fileinputstream into the properties
	    pro.load(fis);
		
		//step:4 access the values with key
		String URL = pro.getProperty("url");
		String USERNAME = pro.getProperty("username");
		
		System.out.println(URL);
		System.out.println(USERNAME);

	}

}
