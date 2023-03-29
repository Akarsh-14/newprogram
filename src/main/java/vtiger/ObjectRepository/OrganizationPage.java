package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import vtiger.GenericUtilities.WebDriverUtility;

public class OrganizationPage extends WebDriverUtility{
	public OrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement OrganizationCreateImg;
	
	@FindBy(name="accountname")
	private WebElement Organizationnametxtfield;
	
	@FindBy(name="button")
	private WebElement Organizationsavebtn;
	
	@FindBy(name="industry")
	private WebElement dropdown;
	


	public WebElement getDropdown() {
		return dropdown;
	}

	public WebElement getOrganizationCreateImg() {
		return OrganizationCreateImg;
	}

	public WebElement getOrganizationnametxtfield() {
		return Organizationnametxtfield;
	}

	public WebElement getOrganizationsavebtn() {
		return Organizationsavebtn;
	}
	public void OrganizationCreateImg() {
		OrganizationCreateImg.click();
	}
	public void Organizationnametxtfield(String Orgname)
	{
		Organizationnametxtfield.sendKeys(Orgname);
	}
	public void Organizationsave()
	{
		Organizationsavebtn.click();
	}
	public void Orgdropdown(String Orgname,String IndustryType)
	{
		Organizationnametxtfield.sendKeys(Orgname);
	handledropdown(dropdown,IndustryType);
	Organizationsavebtn.click();

	
}
	
}
