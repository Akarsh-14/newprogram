package vtiger.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vtiger.GenericUtilities.WebDriverUtility;

public class ContactsPage extends  WebDriverUtility{
public ContactsPage(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}

@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
private WebElement ContactsImg;

@FindBy(name="lastname")
private WebElement Contactsnametxtfield;

@FindBy(xpath="//img[@src='themes/softed/images/select.gif']")
private WebElement contactaddbtn;

@FindBy(id="search_txt")
private WebElement ContactOrganizationsearchtxtfield;


@FindBy(name="search")
private WebElement contactssearchbtn;

@FindBy(name="button")
private WebElement contactsavebtn;

@FindBy(xpath="//input[@class='crmButton small save']")
private WebElement contactsorganizationsave;

public WebElement getContactsImg() {
	return ContactsImg;
}

public WebElement getContactsnametxtfield() {
	return Contactsnametxtfield;
}

public WebElement getContactaddbtn() {
	return contactaddbtn;
}

public WebElement getContactOrganizationsearchtxtfield() {
	return ContactOrganizationsearchtxtfield;
}

public WebElement getContactssearchbtn() {
	return contactssearchbtn;
}

public WebElement getContactsavebtn() {
	return contactsavebtn;
}

public WebElement getContactsorganizationsave() {
	return contactsorganizationsave;
}
public void ContactsImg()
{
	ContactsImg.click();
}
public void Contactsnametxtfield(String name)
{
	Contactsnametxtfield.sendKeys(name);
}
public void contactaddbtn()
{
	contactaddbtn.click();
}
public void ContactOrganizationsearchtxtfield(String Orgdata)
{
	ContactOrganizationsearchtxtfield.sendKeys(Orgdata);
}
public void contactssearchbtn()
{
	contactssearchbtn.click();
}
public void contactsavebtn()
{
	contactsavebtn.click();
}
public void contactsorganizationsave()
{
	contactsorganizationsave.click();
}
public void createnewcontact(WebDriver driver,String name,String Orgdata)
{
	Contactsnametxtfield.sendKeys(name);
	contactaddbtn.click();
	SwitchToWindow(driver,"Accounts");
	ContactOrganizationsearchtxtfield.sendKeys(Orgdata);
	contactssearchbtn.click();
	driver.findElement(By.xpath("//a[text()='"+Orgdata+"']")).click();
	SwitchToWindow(driver,"Contacts");
	contactsavebtn.click();
	
}

}
