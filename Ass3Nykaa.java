package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Ass3Nykaa {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//options.addArguments("--Incognito mode");
		
		
		//Load URL
		
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		
		//Print Title of Page
		
		String title = driver.getTitle();
		System.out.println("Title of Page:" +title);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	   //Hover over Brands & Popular , Finally click on LOREAL
		
		WebElement hoverover1 = driver.findElement(By.xpath("//li[@class='menu-dropdown-icon']"));
		WebElement hoverover2 = driver.findElement(By.xpath("//div[@class='BrandsCategoryHeading']/a"));
		
		Actions builder = new Actions(driver);
		builder.moveToElement(hoverover1).pause(2000).moveToElement(hoverover2).pause(2000).perform();
		System.out.println("Passed 1st & 2nd Hover");
		Thread.sleep(2000);
		WebElement clickelement = driver.findElement(By.xpath("//div[@id='brandCont_Popular']/ul[1]/li[5]/a[1]/img[1]"));
		builder.moveToElement(clickelement).click(clickelement).perform();
		System.out.println("LOREAL PARIS Clicked");
		
		
		// Go to the newly opened window and check the title contains L'Oreal Paris
		Thread.sleep(2000);	
		Set<String> setwindowHandles = driver.getWindowHandles();
		List<String> listwindowHandles = new ArrayList<String> (setwindowHandles);
		driver.switchTo().window(listwindowHandles.get(1));
		System.out.println("SWITCHED TO NEW WINDOW");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains("L'Oreal Paris"));
		String title2 = driver.getTitle();
		System.out.println("Reached here");
		if(title2.contains("L'Oreal Paris"))
			System.out.println("TITLE CONTAINS L'Oreal Paris");
		else
			System.out.println("TITLE DOES NOT CONTAIN L'Oreal Paris");
		
		// Sort By Customer top rated
		Thread.sleep(3000);		
		
		//js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[@class='pull-right']")));
		js.executeScript("window.scrollBy(0,700)");
		WebElement sortby = driver.findElement(By.xpath("//span[@class='pull-right']"));
		sortby.click();
        System.out.println("Clicked on Sort By");
        WebElement customerTopRated=driver.findElement(By.xpath("//div[@for='3']"));
        customerTopRated.click();
        System.out.println("Clicked on customer Top Rated");
		
	   // Click Category and select Shampoo Category
		
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,500)");
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement category = driver.findElement(By.xpath("//div[@class='filter-sidebar__filter-title pull-left']"));
		wait2.until(ExpectedConditions.visibilityOf(category));
		category.click();
		System.out.println("CATEGORY CLICKED");
		Thread.sleep(1000);
		WebElement shampoocategory = driver.findElement(By.xpath("(//div[@class='control__indicator'])[12]"));
		shampoocategory.click();
		System.out.println("SHAMPOO CATEGORY CLICKED");
				
		
		//CHECK WHETHER SHAMPOO FILTER IS APPLIED
		String shampoofilter = driver.findElement(By.xpath("//ul[@class='pull-left applied-filter-lists']/li")).getText();
		if(shampoofilter.equalsIgnoreCase("Shampoo"))
		{
			System.out.println("Shampoo Filter successfully applied");
		}
			
		//CLICK ON LOREAL PARIS COLOR PROTECT SHAMPOO
		
		driver.findElement(By.xpath("(//div[@class='m-content__product-list__title']//span)[4]")).click();
		Set<String> setwindowHandles2 = driver.getWindowHandles();
		List<String> listwindowHandles2 = new ArrayList<String>(setwindowHandles2);
		driver.switchTo().window(listwindowHandles2.get(2));
		System.out.println("SWITCHED TO SHAMPOO WINDOW");
		
		//SELECT SIZE OF SHAMPOO , PRINT MRP & ADD TO SHOPPING BAG
		
		WebElement selectsize = driver.findElement(By.xpath("//div[@class='select-style shade-select']/select"));
		Select dropdownvalue = new Select(selectsize);
		dropdownvalue.selectByValue("0");
		String price = driver.findElement(By.xpath("//span[@class='post-card__content-price-offer']")).getText();
		System.out.println("MRP OF SHAMPOO PRODUCT IS:" +price);
		driver.findElement(By.xpath("//div[@class='pull-left']//button")).click();
		System.out.println("PRODUCT ADDED TO BAG");
		
		//GO TO SHOPPING BAG
		WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement addbag = driver.findElement(By.xpath("//div[@class='AddBagIcon']"));
		wait3.until(ExpectedConditions.visibilityOf(addbag));
		addbag.click();
		
		String grandtotal = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText().replaceAll("\\D", "");
		Integer.parseInt(grandtotal);
		System.out.println("GRAND TOTAL :" +grandtotal);
		
				
		//CLICK ON PROCEED BUTTON
		Thread.sleep(2000);
		WebElement proceed = driver.findElement(By.xpath("//div[@class='second-col']/button"));
		js.executeScript("arguments[0].click()", proceed);
		
		//CLICK ON CONTINUE AS GUEST
		WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement guestcheckout = driver.findElement(By.xpath("//button[@class='btn full big']"));
		wait3.until(ExpectedConditions.visibilityOf(guestcheckout));
		guestcheckout.click();
		
		String grandtotal2 = driver.findElement(By.xpath("//div[@class='payment-details-tbl grand-total-cell prl20']//span")).getText();
		if(grandtotal.contentEquals(grandtotal2))
		{
			System.out.println("GRAND TOTAL MATCHES");
		}
		
	}

}
