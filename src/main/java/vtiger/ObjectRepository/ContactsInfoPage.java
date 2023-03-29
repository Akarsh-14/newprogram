package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsInfoPage {
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement ContactHeadertext;
	
	public ContactsInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}


	public WebElement getContactHeadertext() {
		return ContactHeadertext;
	}


	/**
	 * this method will capture the text from org header and return it to caller
	 * @return
	 */
public String getContactHeaders()

{
	return ContactHeadertext.getText();
}
}
