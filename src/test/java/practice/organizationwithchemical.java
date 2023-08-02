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
import vtiger.ObjectRepository.ContactsPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationPage;

public class organizationwithchemical {

	public static void main(String[] args) throws IOException, InterruptedException {
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
				        String IndustryType = eutil.readDataFromExcell("Organizations", 4, 3);
				        
						
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
				LoginPage lp=new LoginPage(driver);
				lp.logintoApp(USR, PSW);
				lp.getSubmitbtn();
				
				
				//step:3 navigate to organization page
				
				HomePage hp=new HomePage(driver);
				hp.OrganizationLink();
				
				//step:4 click on create organization lookup page
				OrganizationPage op=new OrganizationPage(driver);
				op.OrganizationCreateImg();
				
				//step:5 create organization with mandatory fields
			    op.Orgdropdown(ORGNAME, IndustryType);
				op.Organizationsave();
			
		//step:6 verificaton
			
			     
				OrganizationInfoPage oip=new OrganizationInfoPage(driver);
				String actualdata = oip.getOrgHeader();
		 
				//String actualdata = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		        if(actualdata.contains(ORGNAME))
		        {
		        	System.out.println("pass");
		        }
		        else
		        {
		        	System.out.println("fail");
		         }
		        
		    
				//step:7 signout from app
		        hp.Signout(driver);
			        System.out.println("signout successfull");
				
	}

}
