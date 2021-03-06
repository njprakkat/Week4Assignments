package week4.day1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MouseHover {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		 WebDriverManager.chromedriver().setup(); 
		 WebDriver driver = new ChromeDriver();
		 driver.get("https://www.snapdeal.com/");
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		 System.out.println("URL Loaded");	
		 Actions builder = new Actions(driver);
		 WebElement menfashion = driver.findElement(By.xpath("(//a[@class='menuLinks leftCategoriesProduct '])[6]/span"));
		 WebElement loafer = driver.findElement(By.xpath("//div[@class='colDataInnerBlk']//span[text()='Loafers']"));
		 builder.moveToElement(menfashion).perform();
		 builder.moveToElement(loafer).click().perform();
		 Thread.sleep(2000);
		 WebElement firstproduct = driver.findElement(By.xpath("//picture[@class='picture-elem']"));
		 WebElement quickview = driver.findElement(By.xpath("//div[@class='center quick-view-bar  btn btn-theme-secondary  ']"));
		 builder.moveToElement(firstproduct).click(quickview).perform();
		 System.out.println("Clicked on QUICK VIEW");
		 driver.quit();
		 
		 
	}

}
