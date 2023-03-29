package vtiger.ContactsTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import vtiger.GenericUtilities.BaseClass;
import vtiger.ObjectRepository.ContactsInfoPage;
import vtiger.ObjectRepository.ContactsPage;
import vtiger.ObjectRepository.HomePage;

public class CreateContactsTest extends BaseClass{
	@Test
	public void createContactsTest() throws IOException, InterruptedException
 {		
		String LASTNAME = eutil.readDataFromExcell("Contacts", 4, 3)+ jutil.getRandomNumber();
		// Step 3:Login to Application
		HomePage hp = new HomePage(driver);
		hp.Contacts();

		// Step 4:Navigate to Contacts Link
		ContactsPage cp=new ContactsPage(driver);
		Thread.sleep(3000);
		// Step 5:Click on create contact look up image
		cp.ContactsImg();
		// Step 6: Create contact with mandatory fields and save
		cp.Contactsnametxtfield(LASTNAME);
		cp.contactsavebtn();
		

		// step 7: Verification for contact
		ContactsInfoPage cip=new ContactsInfoPage(driver);
	    String ContactHeader = cip.getContactHeaders();
		//String ContactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	    Assert.assertTrue(ContactHeader.contains(LASTNAME));
	    System.out.println(ContactHeader+"----contact created----");
//		if (ContactHeader.contains(LASTNAME)) {
//			System.out.println(ContactHeader + " --- PASS ---");
//		} else {
//			System.out.println("-- FAIL --");
//		}

	}

}
