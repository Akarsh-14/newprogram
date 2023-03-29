package vtiger.ContactsTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import vtiger.GenericUtilities.BaseClass;
import vtiger.ObjectRepository.ContactsInfoPage;
import vtiger.ObjectRepository.ContactsPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationPage;

public class ContactWithOrganizationTest extends BaseClass{
	@Test(groups="ReggressionSuite")
	public void ContactWithOrganizationtest() throws EncryptedDocumentException, IOException, InterruptedException
	{ 
	        /* Read data from excel sheet - Test data */
	        String OrgData = eutil.readDataFromExcell("Organizations", 1, 2)+jutil.getRandomNumber();
		
	//step:3 navigate to organization page
	HomePage hp=new HomePage(driver);
	hp.OrganizationLink();
	Reporter.log("click on Organization Link successfull");
	
	//step:4 click on create organization lookup page
	OrganizationPage op=new OrganizationPage(driver);
	Thread.sleep(3000);
	op.OrganizationCreateImg();
	Reporter.log("click on Create Organization Img successfull");
	
	//step:5 create organization with mandatory fields
	op.Organizationnametxtfield(OrgData);
	op.Organizationsave();
	Reporter.log("Organization created successfull");
	
	OrganizationInfoPage oip=new OrganizationInfoPage(driver);
	String orgHeader = oip.getOrgHeader();
	Assert.assertTrue(orgHeader.contains(OrgData));
	
	
	//step:6 navigating to contacts page
	hp.Contacts();
	Reporter.log("navigate to contacts link successfull");
	
	//step:7 click on create contact lookup page
	ContactsPage cp=new ContactsPage(driver);
	cp.ContactsImg();
	Reporter.log("click on Contacts Lookup Img successfull");
	
//	Assert.fail();  //failing intentionally to check failed report
	
	
	//step:8 create contacts with mandatory fields and save
	String Contactdata = eutil.readDataFromExcell("contacts", 4, 3)+jutil.getRandomNumber();
	//cp.Contactsnametxtfield(Contactdata);
	//cp.contactaddbtn();

	
//	wutil.SwitchToWindow(driver, "Accounts&action");
//	
//	cp.ContactOrganizationsearchtxtfield(OrgData);
//	cp.contactssearchbtn();
cp.createnewcontact(driver, Contactdata, OrgData);

Reporter.log("create contact successfull");

	//driver.findElement(By.xpath("//a[text()='"+OrgData+"']")).click();
	
//	wutil.SwitchToWindow(driver,"Contacts&action");
//	cp.contactsavebtn();
	

	//step:6 verificaton
Thread.sleep(1000);
	ContactsInfoPage cip=new ContactsInfoPage(driver);
	String actualdata = cip.getContactHeaders();

	Assert.assertTrue(actualdata.contains(Contactdata));
	System.out.println(actualdata+"---contact created----");
//	if(actualdata.contains(Contactdata))
//	{
//		System.out.println("pass");
//	}
//	else   
//	{
//		System.out.println("fail");
//	}
}
}
