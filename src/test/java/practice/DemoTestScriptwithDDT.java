
package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoTestScriptwithDDT {

	public static void main(String[] args) throws IOException {
		// Step 1: Read all the necessary Data
				/* Read data from property File - Common Data */
				FileInputStream fisp = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
				Properties pObj = new Properties();
				pObj.load(fisp);
				String URL = pObj.getProperty("url");
				String BROWSER = pObj.getProperty("browser"); // chrome, Firefox, edge
				String USERNAME = pObj.getProperty("username");
				String PASSWORD = pObj.getProperty("password");

				/* Read data from excel sheet - Test data */
				Random ran=new Random();
				int rannum = ran.nextInt(1000);
				FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
				Workbook wb = WorkbookFactory.create(fise);
				String LASTNAME = wb.getSheet("Contacts").getRow(1).getCell(2).getStringCellValue()+rannum;

				WebDriver driver = null;

				// Step 2: Launch the browser - runtime polymorphism
				if (BROWSER.equalsIgnoreCase("chrome")) {
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
				} else if (BROWSER.equalsIgnoreCase("firefox")) {
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
				} else {
					System.out.println("Invalid Browser name");
				}

				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.get(URL);

				// Step 3:Login to Application
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();

				// Step 4:Navigate to Contacts Link
				driver.findElement(By.linkText("Contacts")).click();

				// Step 5:Click on create contact look up image
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

				// Step 6: Create contact with mandatory fields and save
				driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

				// step 7: Verification for contact
				String ContactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if (ContactHeader.contains(LASTNAME)) {
					System.out.println(ContactHeader + " --- PASS ---");
				} else {
					System.out.println("-- FAIL --");
				}

				// Step 8: Logout of Application
				WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				Actions act = new Actions(driver);
				act.moveToElement(ele).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				System.out.println("Sign out successfull");


	}

}
