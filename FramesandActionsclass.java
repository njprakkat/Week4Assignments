package week4.day1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesandActionsclass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		 WebDriverManager.chromedriver().setup(); 
		 WebDriver driver = new ChromeDriver();
		 driver.get("https://jqueryui.com");
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		 System.out.println("URL Loaded");	
		 driver.findElement(By.xpath("//a[@href='https://jqueryui.com/sortable/']")).click();
		 WebElement frame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
		 driver.switchTo().frame(frame);
		 int xcor = driver.findElement(By.xpath("//ul[@id='sortable']/li[4]")).getLocation().getX();
		 int ycor = driver.findElement(By.xpath("//ul[@id='sortable']/li[4]")).getLocation().getY();
		 Actions builder = new Actions(driver);
		 WebElement source = driver.findElement(By.xpath("//ul[@id='sortable']/li[1]"));
		 builder.dragAndDropBy(source, xcor, ycor).perform();
		 //driver.close();
		 
		 
		 
		
	}

}
