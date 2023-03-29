package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
public LoginPage(WebDriver driver)
{
	PageFactory.initElements(driver,this);
}
@FindBy(name="user_name")
private WebElement UsernameTxtField;

@FindAll({@FindBy(name="user_password"),@FindBy(xpath="//input[@type='password']")})
private WebElement Passwordtxtfield;

@FindBy(id="submitButton")
private WebElement Submitbtn;

public WebElement getUsernameTxtField() {
	return UsernameTxtField;
}

public WebElement getPasswordtxtfield() {
	return Passwordtxtfield;
}

public WebElement getSubmitbtn() {
	return Submitbtn;
}
//Business Libraries -generic method specific to current project
/**
 * This method will login to app with username and password
 * @param Username
 * @param Password
 */
public void logintoApp(String USR,String PSW)
{
	UsernameTxtField.sendKeys(USR);
	Passwordtxtfield.sendKeys(PSW);
	Submitbtn.click();
	
}

}
