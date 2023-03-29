package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInfoPage {
	public ProductInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement ProductInfoHeader;
	
	
	public WebElement getProductInfoHeader() {
		return ProductInfoHeader;
	}
	
	public String getProInfoHeader()
	{
		return ProductInfoHeader.getText();
	}

}
