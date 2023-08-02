package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.ExcelUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;
import vtiger.ObjectRepository.ContactsInfoPage;
import vtiger.ObjectRepository.ContactsPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;
import vtiger.ObjectRepository.OrganizationPage;

public class contactwithorganization {

	public static void main(String[] args) throws Throwable {
		
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
				        String OrgData = eutil.readDataFromExcell("Organizations", 1, 2)+jutil.getRandomNumber();
		
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
				op.Organizationnametxtfield(OrgData);
				op.Organizationsave();
				
				Thread.sleep(3000);
				
				//step:6 navigating to contacts page
				hp.Contacts();
				
				//step:7 click on create contact lookup page
				ContactsPage cp=new ContactsPage(driver);
				cp.ContactsImg();
				
				//step:8 create contacts with mandatory fields and save
				String Contactdata = eutil.readDataFromExcell("contacts", 4, 3)+jutil.getRandomNumber();
				cp.Contactsnametxtfield(Contactdata);
				cp.contactaddbtn();

				
				wutil.SwitchToWindow(driver, "Accounts&action");
				
				cp.ContactOrganizationsearchtxtfield(OrgData);
				cp.contactssearchbtn();

				driver.findElement(By.xpath("//a[text()='"+OrgData+"']")).click();
				
				wutil.SwitchToWindow(driver,"Contacts&action");
				cp.contactsavebtn();
				

				//step:6 verificaton
			
				ContactsInfoPage cip=new ContactsInfoPage(driver);
				String actualdata = cip.getContactHeaders();
			
				if(actualdata.contains(Contactdata))
				{
					System.out.println("pass");
				}
				else   
				{
					System.out.println("fail");
				}
				//step:7 signout from app
				hp.Signout(driver);
//                hp.Administrator();
//                hp.Signout();
			        System.out.println("signout successfull");
	}

}
