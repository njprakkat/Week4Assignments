package week4.day1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class dropdown {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    
		 WebDriverManager.chromedriver().setup(); 
		 WebDriver driver = new ChromeDriver();
		 driver.get("http://leafground.com/pages/Dropdown.html");
		 System.out.println("URL Loaded");	
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		 driver.findElement(By.xpath("(//div[@class='example'])[6]"));
		 Actions builder = new Actions (driver);
		 WebElement firstelement = driver.findElement(By.xpath("(//div[@class='example'])[6]//option[text()='Appium']"));
		 WebElement toelement = driver.findElement(By.xpath("(//div[@class='example'])[6]//option[text()='Loadrunner']"));
		 builder.keyDown(Keys.CONTROL).click(firstelement).click(toelement).keyUp(Keys.CONTROL).perform();
		 
	}

}
