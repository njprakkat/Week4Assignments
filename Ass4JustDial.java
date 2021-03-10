package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Ass4JustDial {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		//options.addArguments("--Incognito mode");

		
		//Load URL
		
		driver.get("https://www.justdial.com/in");
		driver.manage().window().maximize();
		
		//Print Title of Page
		
		String title = driver.getTitle();
		System.out.println("Title of Page:" +title);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
		
		//Set the Location as Chennai
		WebElement cityelement = driver.findElement(By.xpath("//input[@id='city']"));
		cityelement.click();
		cityelement.sendKeys("Chennai");
		Thread.sleep(1000);
		WebElement chennai = driver.findElement(By.linkText("Chennai"));
		chennai.click();
		System.out.println("CITY - CHENNAI selected");
		
		//Click Auto Care in the left menu
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(4));
		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='hotkeys-text'])[" +8+"]"))).click();
		System.out.println("AUTO CARE LINK CLICKED");
		
		//Click Car Repair 
		Thread.sleep(2000);
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(4));
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Car Repair')]"))).click();
		System.out.println("CAR REPAIR LINK CLICKED");
		
		//Click Car Brand as Hyundai
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Hyundai')]")).click();
		System.out.println("CAR BRAND HYUNDAI CLICKED");
		
		//Click Make as Hyundai Xcent
		Thread.sleep(2000);
		//WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(4));
		//wait4.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(text(),'Accent')]"))).click();
		driver.findElement(By.xpath("//span[contains(text(),'Accent')]")).click();
		System.out.println("CAR BRAND HYUNDAI ACCENT CLICKED");
		
		/*Click on All Options
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'All Options')]")).click();
		System.out.println("SELECTED ALL OPTIONS");
		
		*/
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='srchbx']")).click();
		driver.findElement(By.xpath("//input[@id='insrch']")).sendKeys("Porur", Keys.ENTER);
		Thread.sleep(2000);
		
		
		//driver.switchTo().alert().dismiss();
		driver.findElement(By.linkText("Location")).click();
	    Set<String> setwindowHandles = driver.getWindowHandles();
	    List<String> listwindowHandles = new ArrayList<String> (setwindowHandles);
	    driver.switchTo().window(listwindowHandles.get(1));
	    driver.findElement(By.id("sortbydist")).sendKeys("Porur");
	    driver.findElement(By.id("sortbydistbtn")).click();
	    System.out.println("Porur Location selected ");
	    
	    Actions builder = new Actions(driver);
	    WebElement distance = driver.findElement(By.xpath("//i[@class='res_downarrowic resultimg']"));
	    WebElement onekm = driver.findElement(By.xpath("//a[@class='lng_commn_all ']"));
	    builder.click(distance).click(onekm).perform();
	    System.out.println("Distance 1km selected ");
	    
	    
	    // FIND SERVICE CENTERS WITH VOTES GREATER THAN OR EQUAL TO 50
	    
	    List<WebElement> listallvotes = driver.findElements(By.xpath("//span[@class='rt_count lng_vote']"));
	    List<WebElement> listservicecenters = driver.findElements(By.xpath("//span[@class='lng_cont_name']"));
	    
	    Map<String, Integer> scvotesgreaterfifty = new HashMap<String, Integer>();
	    int scvotes = 0;
		 for(int i=0; i < listservicecenters.size(); i++)
		 {
			 for(int j=2; j<listallvotes.size(); j=j+2)
			 {
				 scvotes = Integer.parseInt(listallvotes.get(j).getText().replaceAll("\\D", "")); 
			 }
			 
		 scvotesgreaterfifty.put(listservicecenters.get(i).getText(),scvotes);
		 } 
		
	for(Entry<String, Integer> eachServicecenter: scvotesgreaterfifty.entrySet())
	{
	String servicecentername = eachServicecenter.getKey();
	Integer servicecentervote = eachServicecenter.getValue();

    if(servicecentervote >= 50)
    System.out.println(servicecentername);	
    
    // FIND SERVICE CENTERS WITH RATING GREATER THAN OR EQUAL TO 4.5
	}
	
    List<WebElement> listallratings = driver.findElements(By.xpath("//span[@class='green-box']"));
    Map<String, Float> scratinggreaterfive = new HashMap<String, Float>();
    float scrating = 0;
	 for(int i=0; i < listallratings.size(); i++)
	 {
		 scrating = Float.parseFloat(listallratings.get(i).getText()); 
         scratinggreaterfive.put(listservicecenters.get(i).getText(),scrating);
	 } 
	
for(Entry<String, Float> eachServicecenter2: scratinggreaterfive.entrySet())
{
String  servicecentername2 = eachServicecenter2.getKey();
Float servicecenterrating = eachServicecenter2.getValue();

if(servicecenterrating >= 4.5)
System.out.println(servicecentername2);	
	}

	}
}	

