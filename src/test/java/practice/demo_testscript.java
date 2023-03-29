package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class demo_testscript {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		//step:1 launch the browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		//step:2 login to app
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties pro=new Properties();
		pro.load(fis);
		String URL = pro.getProperty("url");
		String USR = pro.getProperty("username");
		String PSW = pro.getProperty("password");
		
		driver.get(URL);
	driver.findElement(By.name("user_name")).sendKeys(USR);
		driver.findElement(By.name("user_password")).sendKeys(PSW);
		driver.findElement(By.id("submitButton")).click();
		
		//step:3 navigate to contact
		driver.findElement(By.linkText("Contacts")).click();
		
		//step:4 click on create contact lookup page
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		//step:5 create contacts with mandatory fields and save
		Random ran=new Random();
		int rannum = ran.nextInt(1000);
		
		
		FileInputStream fis1=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sheet = wb.getSheet("contacts");
		Row row = sheet.getRow(4);
		Cell cell = row.getCell(3);
		String data = cell.getStringCellValue()+rannum;
		
		driver.findElement(By.name("lastname")).sendKeys(data);
		driver.findElement(By.name("button")).click();
		
		//step:6 verification for contact
		String actualdata = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
        if(actualdata.contains(data))
        {
        	System.out.println("pass");
        }
        else
        {
        	System.out.println("fail");
        }
        //step:7 logout of app
        WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
        Actions a=new Actions(driver);
        a.moveToElement(ele).perform();
        driver.findElement(By.linkText("Sign Out")).click();
        System.out.println("signout successfull");
	}

}
