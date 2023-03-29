package vtiger.ContactsTest;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import vtiger.GenericUtilities.BaseClass;
import vtiger.ObjectRepository.ContactsInfoPage;
import vtiger.ObjectRepository.ContactsPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationPage;

public class ContactWithOrg2Test extends BaseClass{
	@Test
	public void contactWithOrg2Test() throws EncryptedDocumentException, IOException, InterruptedException
	{
	  String ORGNAME = eutil.readDataFromExcell("Contacts", 4, 2) + jutil.getRandomNumber();
		String LASTNAME = eutil.readDataFromExcell("Contacts", 4, 3);
    //  String OrgData = eutil.readDataFromExcell("Organizations", 1, 2)+jutil.getRandomNumber();

		// Step 4: Click on Organizations link
		HomePage hp=new HomePage(driver);
		hp.OrganizationLink();
		// Step 5: Click on Create Organization look up image
		OrganizationPage op=new OrganizationPage(driver);
		Thread.sleep(1000);
		op.OrganizationCreateImg();
		// Step 6: Create Organization with mandatory details
		op.Organizationnametxtfield(ORGNAME);
		// Step 7: save
		op.Organizationsave();
		// Step 8: Validate for Organization
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String orgHeader = oip.getOrgHeader();
		Assert.assertTrue(orgHeader.contains(ORGNAME));
		System.out.println(orgHeader+"---Organization created---");
//		if (orgHeader.contains(ORGNAME)) {
//			System.out.println(orgHeader + "Organizationm created");
//		} else {
//			System.out.println("Organization not created");
//		}

		// Step 9: Navigate to contacts Link
		hp.Contacts();
	
		// Step 10:Click on create contact look up image
		ContactsPage cp=new ContactsPage(driver);
		cp.ContactsImg();
        cp.createnewcontact(driver, LASTNAME, ORGNAME);
		// Step 16: Validate for Contacts
		ContactsInfoPage cip=new ContactsInfoPage(driver);
		String ContactHeader = cip.getContactHeaders();
		Assert.assertTrue(ContactHeader.contains(LASTNAME));
		System.out.println(ContactHeader+"----contact created---");
//		if(ContactHeader.contains(LASTNAME))
//		{
//			System.out.println(ContactHeader+" --- PASS ---");
//		}
//		else
//		{
//			System.out.println("-- FAIL --");
//		}
	}
}
