package practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vtiger.GenericUtilities.BaseClass;
import vtiger.ObjectRepository.HomePage;

public class CreateMultipleOrgwithIndustryTest extends BaseClass{

	@Test(dataProvider="getdata")
	public void createMultipleOrgTest(String Org,String Industry)
	{
		HomePage hp=new HomePage(driver);
		hp.OrganizationLink();
	}
	
	@DataProvider
	public Object[][] getdata() throws EncryptedDocumentException, IOException
	{
		Object[][] data= eutil.readMultipleData("Multiple");
		return data;
	}
}

