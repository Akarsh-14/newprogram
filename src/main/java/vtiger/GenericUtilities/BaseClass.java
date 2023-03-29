package vtiger.GenericUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;
/**
 * This class consists of Basic configuration annotations of  TestNG
 * @author akarsh
 *
 */
public class BaseClass {
	public WebDriverUtility wutil=new WebDriverUtility();
	public PropertyFileUtility putil=new PropertyFileUtility();
	public ExcelUtility eutil=new ExcelUtility();
	public JavaUtility jutil=new JavaUtility();
	public static WebDriver sdriver;  //listeners
	
	public WebDriver driver;

	@BeforeSuite(groups={"SmokeSuite","ReggressionSuite"})
	public void bsConfig()
	{
		System.out.println("-----Database connection successfull----");
	}
	//@Parameters("browser")//for cross browser execution
	//@BeforeTest//cross browser
	@BeforeClass(groups={"SmokeSuite","ReggressionSuite"})
	public void bcConfig(/*String BROWSER*/) throws IOException  //string added for cross browser
	{
		String URL = putil.readDataFromPropertyFile("url");
		String BROWSER = putil.readDataFromPropertyFile("browser");  //cmted for cross browser
		
		// Step 2: Launch the browser - runtime polymorphism
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		} else {
			System.out.println("Invalid Browser name");
		}
		   sdriver=driver;  //listeners
			wutil.maximizewindow(driver);
			wutil.waitforpage(driver);
			driver.get(URL);
		}
	
	@BeforeMethod(groups={"SmokeSuite","ReggressionSuite"})
	public void bmConfig() throws IOException
	{
		String USERNAME = putil.readDataFromPropertyFile("username");
		String PASSWORD = putil.readDataFromPropertyFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.logintoApp(USERNAME, PASSWORD);
		
		System.out.println("---Login Successfull---");

		
	}
	@AfterMethod(groups={"SmokeSuite","ReggressionSuite"})
	public void amConfig()
	{
		HomePage hp = new HomePage(driver);
		hp.Signout(driver);
		System.out.println("----Logout Successfull----");
	}
	@AfterClass(groups={"SmokeSuite","ReggressionSuite"})
	public void acConfig()
	{
	   driver.quit();
	   System.out.println("----Browser closed  Successfully----");
	}
	@AfterSuite(groups={"SmokeSuite","ReggressionSuite"})
	public void asConfig()
	{
		System.out.println("-----Database Connection Successfull-----");
		
	}
}
