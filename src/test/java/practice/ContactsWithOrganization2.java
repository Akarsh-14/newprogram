package practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.ExcelUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;

public class ContactsWithOrganization2 {

	public static void main(String[] args) throws IOException {
		//step:1 create objects for all utilities
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
		        String ORGNAME = eutil.readDataFromExcell("Contacts", 4, 2) + jutil.getRandomNumber();
				String LASTNAME = eutil.readDataFromExcell("Contacts", 4, 3);
		      //  String OrgData = eutil.readDataFromExcell("Organizations", 1, 2)+jutil.getRandomNumber();

		        WebDriver driver = null;

				// Step 3: Launch the browser - runtime polymorphism
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
		// Step 3: Login to App
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

				// Step 8: Validate for Organization
				String orgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if (orgHeader.contains(ORGNAME)) {
					System.out.println(orgHeader + "Organizationm created");
				} else {
					System.out.println("Organization not created");
				}

				// Step 9: Navigate to contacts Link
				driver.findElement(By.linkText("Contacts")).click();

				// Step 10:Click on create contact look up image
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

				// Step 11: Create contact with mandatory fields and save
				driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
				
				// Step 12: Click on Organizatiion look up image
				driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@title='Select']")).click();
				
				//Step 13: Switch the window handle to Child
				wutil.SwitchToWindow(driver, "Accounts");
				
				//Step 14: Search for Org name
				driver.findElement(By.name("search_text")).sendKeys(ORGNAME);
				driver.findElement(By.name("search")).click();
				
				/*driver.findElement(By.linkText(ORGNAME)).click();*/
		                             
				driver.findElement(By.xpath("//a[text()='"+ORGNAME+"']")).click();
				
				//Step 15: Switch the control back to parent and save
				wutil.SwitchToWindow(driver, "Contacts");
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				// Step 16: Validate for Contacts
				String ContactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(ContactHeader.contains(LASTNAME))
				{
					System.out.println(ContactHeader+" --- PASS ---");
				}
				else
				{
					System.out.println("-- FAIL --");
				}
				
				//Step 17: Logout of Application
				WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				wutil.MouseHoverAction(driver, ele);
				driver.findElement(By.linkText("Sign Out")).click();
				System.out.println("Sign out successfull");
				                             
				


	}

}
