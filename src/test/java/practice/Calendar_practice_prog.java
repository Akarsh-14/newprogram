package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Calendar_practice_prog {
@Test
public void calendar()
{
	WebDriver driver=new FirefoxDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://www.jetcost.co.in/en");
	driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();
	WebElement dep = driver.findElement(By.xpath("//input[@placeholder='Departure place']"));
	dep.sendKeys("Bangalore");
	
	
	WebElement arv = driver.findElement(By.xpath("//input[@placeholder='Arrival place']"));
	arv.sendKeys("Delhi");
	
	driver.findElement(By.xpath("//input[@placeholder='Period']")).click();
	driver.findElement(By.xpath("//div[text()='April 2023']/ancestor::div[@class='z-50 bg-white dark:bg-neutral']//descendant::div[text()='14']")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
}
}
