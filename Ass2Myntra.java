package week4.day2;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Ass2Myntra {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		options.addArguments("--Incognito mode");
		
		//Load URL
		
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		
		//Print Title of Page
		
		String title = driver.getTitle();
		System.out.println("Title of Page:" +title);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//Hover over WOMEN link & click on Jackets & Coats
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
	    WebElement firstelement = driver.findElement(By.xpath("//a[text()='Women']"));
	    wait.until(ExpectedConditions.visibilityOf(firstelement));
	    Actions mhover = new Actions(driver);
		mhover.moveToElement(firstelement).perform();
		WebElement toelement = driver.findElement(By.xpath("//a[text()='Jackets & Coats']"));
		wait.until(ExpectedConditions.visibilityOf(toelement));
		mhover.moveToElement(toelement).click().perform();
		
		//Find the total count of item 
		
		String numberofitems = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		numberofitems = numberofitems.replaceAll("\\D", "");
		System.out.println(numberofitems);
		int sum=0;
		List<WebElement> containerlst = driver.findElements(By.xpath("//ul[@class='categories-list']//span"));
		
		for(int i=0; i<containerlst.size(); i++)
		{
			
			String containervalue = containerlst.get(i).getText().replaceAll("\\D", "");
			int containervalue1 = Integer.parseInt(containervalue);
			System.out.println(containervalue1);
			sum = sum + containervalue1;
		}
		System.out.println("SUM OF CATEGORIES COUNT IS: " +sum);
		if(sum==Integer.parseInt(numberofitems))
		System.out.println("SUM OF CATEGORY COUNT MATCHES");
		else
		System.out.println("SUM OF CATEGORY COUNT DOES NOT MATCH");
		
		driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[2]")).click();
		
		//Wait for Coat Brands & click on More Option under Brands
		
		WebDriverWait wait1= new WebDriverWait(driver, Duration.ofSeconds(2));
		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='brand-more']"))).click();
		
		// FILTER BRANDS BY MANGO
		
		driver.findElement(By.xpath("//input[@class='FilterDirectory-searchInput']")).sendKeys("Mango");
		WebDriverWait wait2= new WebDriverWait(driver, Duration.ofSeconds(4));
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='FilterDirectory-count']/following-sibling::div")));
		WebElement mangocheckbox = driver.findElement(By.xpath("//span[@class='FilterDirectory-count']/following-sibling::div"));
		mangocheckbox.click();
		System.out.println("Mango Check box clicked");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();
		System.out.println("Pop Up box closed");
		
		//Confirm all the Coats are of brand MANGO
		
		List<WebElement> listcheckBrand = driver.findElements(By.xpath("//h3[@class='product-brand']"));
		int flag = 0;
		for (int i=0; i < listcheckBrand.size(); i++)
		{
			String stringcheckbrand = listcheckBrand.get(i).getText();
			if(!stringcheckbrand.equalsIgnoreCase("MANGO"))
			flag=1;
			break;
			
		}
		
		if(flag!=1)
		System.out.println("ALL BRAND CONTENTS ARE MANGO BRANDS");
		else
		System.out.println("ALL BRAND CONTENTS ARE NOT MANGO BRANDS");	
		
		//Sort by Better Discount
		
		WebElement fromelement = driver.findElement(By.xpath("//div[@class='sort-sortBy']"));
		WebElement betterdiscountelement = driver.findElement(By.xpath("(//label[@class='sort-label '])[3]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(fromelement).moveToElement(betterdiscountelement).click().perform();
		
		//Find the price of first displayed item
		
		Thread.sleep(2000);
		String pricefirstitem = driver.findElement(By.xpath("//span[@class='product-discountedPrice']")).getText();
		System.out.println("PRICE OF FIRST ITEM :" +pricefirstitem);
		
		//CLICK ON WISHLIST
		
		WebElement fromelement2 = driver.findElement(By.xpath("//li[@class='product-base']"));
		WebElement wishlistelement = driver.findElement(By.xpath("//span[@class='product-wishlistFlex product-actionsButton product-wishlist ']"));
		Actions builder3 = new Actions(driver);
		
		builder3.moveToElement(fromelement2).moveToElement(wishlistelement).click().perform();
		
		driver.close();
		
		
	}

}
