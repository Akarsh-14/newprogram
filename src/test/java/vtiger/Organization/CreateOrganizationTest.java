package vtiger.Organization;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import vtiger.GenericUtilities.BaseClass;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationPage;
@Listeners(vtiger.GenericUtilities.ListenersImplementation.class)
public class CreateOrganizationTest extends BaseClass{    
@Test(groups="SmokeSuite")
public void createOrganizationTest() throws IOException, InterruptedException{
		
		   /* Read data from excel sheet - Test data */
	 	String ORGNAME = eutil.readDataFromExcell("Organizations", 1, 2)+jutil.getRandomNumber();
				
	
				//step:3 navigate to organization page
				HomePage hp=new HomePage(driver);
				hp.OrganizationLink();
				Reporter.log("click on Organization Link successfull");
				
				//step:4 click on create organization lookup page
				OrganizationPage op=new OrganizationPage(driver);
				Thread.sleep(3000);
				op.OrganizationCreateImg();
				Reporter.log("click on Create Organization Lookup Img successfull");
				
				//step:5 create organization with mandatory fields
				op.Organizationnametxtfield(ORGNAME);
				op.Organizationsave();
				Reporter.log("create Organization successfull");
	//Assert.fail();
		
				//step:6 verificaton
				OrganizationInfoPage oip=new OrganizationInfoPage(driver);
				String actualdata = oip.getOrgHeader();
				Assert.assertTrue(actualdata.contains(ORGNAME));
				System.out.println(actualdata+"----organization created----");
			
	}

/*
@Test
public void demo()
{
	System.out.println("executing");
}
@Test
public void regional()
{
	System.out.println("regional regression");
}
*/

}