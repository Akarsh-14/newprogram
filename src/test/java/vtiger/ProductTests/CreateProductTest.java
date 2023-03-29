package vtiger.ProductTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.BaseClass;
import vtiger.GenericUtilities.ExcelUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;
import vtiger.ObjectRepository.ProductInfoPage;
import vtiger.ObjectRepository.ProductPage;

public class CreateProductTest extends BaseClass {

	@Test
	public void CreateProducttest() throws IOException, InterruptedException
	{
		
		String ProData = eutil.readDataFromExcell("Products", 1, 2)+jutil.getRandomNumber();
		
        
		HomePage hp = new HomePage(driver);
		hp.Products();
		Reporter.log("click on Product Link successfull");
		
		ProductPage pp=new ProductPage(driver);
		Thread.sleep(3000);
		pp.Productcreateimg();
		Reporter.log("click on Product Lookup Img successfull");
		
		pp.Producttxtfield(ProData);
		pp.Savebtn();
		Reporter.log("Product created successfull");
		
         Thread.sleep(3000);
		ProductInfoPage pip=new ProductInfoPage(driver);
		String actualdata = pip.getProInfoHeader();
		Thread.sleep(2000);

		Assert.assertTrue((actualdata.contains(ProData)));
		System.out.println(actualdata+"---product created----");
	}

}
