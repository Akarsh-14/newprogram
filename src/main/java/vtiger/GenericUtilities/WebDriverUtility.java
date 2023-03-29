 package vtiger.GenericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class consists of generic methods related to Webdriver actions
 * @author akarsh
 *
 */
public class WebDriverUtility {
	/**
	 * This method will maximize the window
	 * @param driver
	 */
	public void  maximizewindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	/**
	 * this method will minimize the window
	 * @param driver
	 */
	public void minimizewindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	/**
	 * this method will wait for the page to load
	 * @param driver
	 */
	public void waitforpage(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	/**
	 * this method will wait until the element becomes visible
	 * @param driver
	 * @param element
	 */
	public void waitforElementtobeVisible(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * this method will wait until the element becomes clickable
	 * @param driver
	 * @param element
	 */
	public void waitforElementtobeClickable(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * this method will handle the drop down element based on index value
	 * @param element
	 * @param index
	 */
	public void handledropdown(WebElement element,int index)
	{
		Select s=new Select(element);
		s.selectByIndex(index);
	}
	/**
	 * this method will handle the drop down element based on visible text
	 * @param element
	 * @param text
	 */
	public void handledropdown(WebElement element,String text)
	{
		Select s=new Select(element);
		s.selectByVisibleText(text);
	}
	/**
	 * this method will handle the drop down element based on value
	 * @param value
	 * @param element
	 */
	public void handledropdown(String value,WebElement element)
	{
		Select s=new Select(element);
		s.selectByValue(value);
	}
	/**
	 * This method will perform right click anywhere in web page
	 * @param driver
	 */
	public void rightClickAction(WebDriver driver)
	{
		Actions act=new Actions(driver);
	    act.contextClick().perform();
	}
	/**
	 * This method will perform right click on a web element
	 * @param driver
	 * @param element
	 */
	public void rightClickAction(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
		
	}
	/**
	 * This method will perform mouse Hover action
	 * @param driver
	 * @param element
	 */
	public void MouseHoverAction(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	/**
	 * This method will perform double click anywhere on web page
	 * @param driver
	 */
	public void doubleClickAction(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.doubleClick().perform();
	}
	
	/**
	 * This method will perform double click on a web element
	 * @param driver
	 * @param element
	 */
	public void doubleClickAction(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	/**
	 * This method will perform drag and drop action
	 * @param driver
	 * @param srcElement
	 * @param dstElement
	 */
	public void dragAndDropAction(WebDriver driver, WebElement srcElement, WebElement dstElement)
	{
		Actions act = new Actions(driver);
		act.dragAndDrop(srcElement, dstElement).perform();
	}
	/**
	 * this method will accept the alert
	 * @param driver
	 */
	public void acceptalert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	/**
	 * this method will dismiss the alert
	 * @param driver
	 */
	public void dismissAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
		
	}
	/**
	 * this method will capture the text from alert popup
	 * @param driver
	 * @return
	 */
	public String getAlertText(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}
	/**
	 *this method will handle frame based on index
	 * @param driver
	 * @param index
	 */
	public void handleFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	/**
	 * this method will handle frame based on name or ID
	 * @param driver
	 * @param nameorId
	 */
	public void handleFrame(WebDriver driver,String nameorId)
	{
		driver.switchTo().frame(nameorId);
	}
	/**
	 * this method will handle frame based on web element
	 * @param driver
	 * @param element
	 */
	public void handleFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	/**
	 * this method will switch to immediate parent frame
	 * @param driver
	 */
	public void switchToParentFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	/**
	 * this method will switch to default frame
	 * @param driver
	 */
	public void switchToDefaultFrame(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	/**
	 * this method will switch the window based on partial window title.
	 * @param driver
	 * @param PartialWindowTitle
	 */
	public void SwitchToWindow(WebDriver driver,String PartialWindowTitle)
	{
		Set<String> allid = driver.getWindowHandles();
		Iterator<String> it = allid.iterator();
		while(it.hasNext())
		{
			String wid = it.next();
			driver.switchTo().window(wid);
			String title = driver.getTitle();
			if(title.contains(PartialWindowTitle))
					{
				break;
					}
		}

	}
	/**
	 * this method will take screen shot and save it in folder
	 * @param driver
	 * @param screenshotName
	 * @return
	 * @throws IOException
	 */
	public String takeScreenShot(WebDriver driver,String screenshotName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dst=new File(".\\ScreenShots\\"+screenshotName+".png");
		FileUtils.copyFile(src, dst);
		return dst.getAbsolutePath();  //used for extent reports...
	}
	/**
	 * this method will perform random scroll downwards vertically
	 * @param driver
	 */
	public void scrollAction(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)","");
		
	}
	/**
	 * this method will scroll until the element is identified in DOM
	 * @param driver
	 * @param element
	 */
	public void scrollAction(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		int y = element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+")",element);
	}
}
