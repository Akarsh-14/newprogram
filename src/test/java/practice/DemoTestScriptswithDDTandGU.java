package practice;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.ExcelUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;

public class DemoTestScriptswithDDTandGU {

	public static void main(String[] args) throws IOException {
		//step:1 create Object for all utilities
		PropertyFileUtility putil=new PropertyFileUtility();
		WebDriverUtility wutil=new WebDriverUtility();
		JavaUtility jutil=new JavaUtility();
		ExcelUtility eutil=new ExcelUtility();
		
		//step:2 read all the necessary data
		/*read data from property file-common data*/
          String URL = putil.readDataFromPropertyFile("url");
          String BROWSER = putil.readDataFromPropertyFile("browser");
         String USR = putil.readDataFromPropertyFile("username");
       String PSW = putil.readDataFromPropertyFile("password");
				
				/* Read data from excel sheet - Test data */
				String ORGNAME = eutil.readDataFromExcell("Organizations", 1, 2)+jutil.getRandomNumber();
		               
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

				
				wutil.maximizewindow(driver);
				wutil.waitforpage(driver);
				driver.get(URL);

				// Step 3:Login to Application
				driver.findElement(By.name("user_name")).sendKeys(USR);
				driver.findElement(By.name("user_password")).sendKeys(PSW);
				driver.findElement(By.id("submitButton")).click();

				// Step 4: Click on Organizations link
				driver.findElement(By.linkText("Organizations")).click();

				// Step 5: Click on Create Organization look up image
				driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

				// Step 6: Create Organization with mandatory details
				driver.findElement(By.name("accountname")).sendKeys(ORGNAME);

				// Step 7: save
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

				// Step 8: Validate
				String orgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if (orgHeader.contains(ORGNAME)) {
					System.out.println(orgHeader + "PASS");
				} else {
					System.out.println("FAIL");
				}

				// Step 9: Logout of App
				WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				wutil.MouseHoverAction(driver, ele);
				driver.findElement(By.linkText("Sign Out")).click();
			}

		}
