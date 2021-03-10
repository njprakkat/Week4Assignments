package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Ass1Servicenow {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
	//options.addArguments("--Incognito mode");
	ChromeDriver driver = new ChromeDriver(options);
	
	//Load ServiceNow application https://dev68594.service-now.com/ & print title
	
	driver.get("https://dev68594.service-now.com/");
	driver.manage().window().maximize();
	String title = driver.getTitle();
	System.out.println("Title of Page:" +title);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	//Enter Credentials & Login
	WebElement frame = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
	driver.switchTo().frame(frame);
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='form-control']"))).sendKeys("admin");
	driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("India@123");
	driver.findElement(By.xpath("//button[@id='sysverb_login']")).click();
	
	//Search “incident “ Filter Navigator
	driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("Incident");
	System.out.println("PASSED");
	
	//CLICK ALL
	driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
	System.out.println("ALL Button clicked");
	
	//CLICK NEW BUTTON
	WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
	driver.switchTo().frame(frame2);
	driver.findElement(By.xpath("//button[@class='selected_action action_context btn btn-primary']")).click();
	System.out.println("New button clicked");
	
		
	//Select caller option
	driver.findElement(By.xpath("//button[@id='lookup.incident.caller_id']")).click();
    Set<String> allwindowHandles = driver.getWindowHandles();
    List<String> listHandles = new ArrayList<String>(allwindowHandles);
    driver.switchTo().window(listHandles.get(1));
    System.out.println(" SWITCHED TO CHILD WINDOW");
    WebElement search = driver.findElement(By.xpath("//input[@class='form-control']"));
    search.sendKeys("Guest", Keys.ENTER);
    System.out.println("Guest Keyed in");
    //DOESNT WORK WHEN USING WAIT ??
    //WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(2));
    //wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody[@class='list2_body']/tr[1]/td[3]/a"))).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//tbody[@class='list2_body']/tr[1]/td[3]/a")).click();
    System.out.println("Guest Selected, now switch back windows");
    driver.switchTo().window(listHandles.get(0));
    WebElement frame3 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
	driver.switchTo().frame(frame3);
    driver.findElement(By.xpath("//input[@id='incident.short_description']")).sendKeys("Automation Testing");
    
    //READ THE INCIDENT NO
  	String incidentno  = driver.findElement(By.id("incident.number")).getAttribute("value");
   	System.out.println(incidentno); 
  	 
  	 
  	//NEW INCIDENT SUBMITTED
    driver.findElement(By.xpath("//button[@class='form_action_button  action_context btn btn-default']")).click();
    System.out.println("Submit button clicked");
    // WORKS TILL HERE ...
   
    System.out.println("REached HEre");
    WebElement searchfield = driver.findElement(By.xpath("//span[@class='sr-only']/following-sibling::input"));
    searchfield.sendKeys(incidentno, Keys.ENTER);
    Thread.sleep(2000);
    String verifyincno = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
    if (verifyincno.equals(incidentno))
    System.out.println("INCIDENT IS CREATED SUCCESSFULLY");
    

	}

}
