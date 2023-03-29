package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vtiger.GenericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility {
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Organizations")
	private WebElement Organizationlinktxt;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement Administrator;
	
	@FindBy(linkText="Sign Out")
	private WebElement SignOut;
	
	@FindBy(linkText="Contacts")
	private WebElement Contactspage;
	
    @FindBy(linkText="Products")
    private WebElement ProductLinktxt;
    
    
	public WebElement getProductLinktxt() {
		return ProductLinktxt;
	}

	public WebElement getContactspage() {
		return Contactspage;
	}

	public WebElement getOrganizationlinktxt() {
		return Organizationlinktxt;
	}
	
public WebElement getAdministrator() {
		return Administrator;
	}

	public WebElement getSignOut() {
		return SignOut;
	}

public void OrganizationLink()
{
	Organizationlinktxt.click();
}
//public void Administrator()
//{
//	Administrator.click();
//}
/**
 * this method will perform logout operation
 * @param driver
 */
public void Signout(WebDriver driver)
{
	MouseHoverAction(driver, Administrator);
	SignOut.click();
}
public void Contacts()
{
	Contactspage.click();
}
public void Products()
{
	ProductLinktxt.click();
}

}
