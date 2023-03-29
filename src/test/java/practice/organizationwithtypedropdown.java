package practice;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.ExcelUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;

public class organizationwithtypedropdown {

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
		        String ORGNAME = eutil.readDataFromExcell("Organizations", 1, 2)+jutil.getRandomNumber();
				
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
		
		//step:2 login to app
		driver.findElement(By.name("user_name")).sendKeys(USR);
		driver.findElement(By.name("user_password")).sendKeys(PSW);
		driver.findElement(By.id("submitButton")).click();
		
		//step:3 navigate to organization page
		driver.findElement(By.linkText("Organizations")).click();
		
		//step:4 click on create organization lookup page
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
	
		
		//step:5 create organization with mandatory fields
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		WebElement element = driver.findElement(By.name("industry"));
        Select s=new Select(element);
        s.selectByIndex(10);
       WebElement element2 = driver.findElement(By.name("accounttype"));
       Select s2=new Select(element2);
        s2.selectByIndex(3);
       driver.findElement(By.name("button")).click();
        
    	
		//step:6 verificaton
        String actualdata = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
        if(actualdata.contains(ORGNAME))
        {
        	System.out.println("pass");
        }
        else
        {
        	System.out.println("fail");
        }
    	//step:7 signout from app
		 WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.MouseHoverAction(driver, ele);
		 driver.findElement(By.linkText("Sign Out")).click();
	        System.out.println("signout successfull");
       

	}

}
