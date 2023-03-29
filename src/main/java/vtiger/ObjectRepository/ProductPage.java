package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	public ProductPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement Productcreateimg;
	
	@FindBy(name="productname")
	private WebElement Producttxtfield;
	
	@FindBy(name="button")
	private WebElement savebtn;

	public WebElement getProductcreateimg() {
		return Productcreateimg;
	}

	public WebElement getProducttxtfield() {
		return Producttxtfield;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	public void Productcreateimg()
	{
		Productcreateimg.click();
	}
public void Producttxtfield(String name)
{
	Producttxtfield.sendKeys(name);
}
public void Savebtn()
{
	savebtn.click();
}
}
