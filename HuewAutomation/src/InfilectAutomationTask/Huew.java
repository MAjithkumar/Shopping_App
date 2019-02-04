package InfilectAutomationTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Huew {
	public static WebDriver d;

	public static void main(String[] args) throws InterruptedException, IOException, Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the username");
		String email = reader.readLine();
		System.out.println(email);
		System.out.println("Enter the password");
		String password = reader.readLine();
		System.out.println(password);
		System.out.println("Enter the sample upload image location ");
		String pictureLocation = reader.readLine();
		System.out.println(pictureLocation);
// Create WebDriver 
//		System.setProperty("webdriver.chrome.driver", "E:\Ajith\Personal\My Programs\ChromeDriver\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		d = new ChromeDriver(options);
		d.manage().window().maximize();
// Open the website
		d.get("https://huew.co");
// Click the YOU icon	
		Thread.sleep(2000);
		d.findElement(By.xpath("//html/body/div[3]/div[2]/div/div/a[6]/div")).click();
// Click the Login using google button	
		d.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		d.findElement(By.xpath("/html/body/div[4]/div/div/div[1]/div[1]/center/div/img[2]")).click();
		d.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
// Switch to the pop up window		
		ArrayList<String> tabs = new ArrayList<String>(d.getWindowHandles());
		d.switchTo().window(tabs.get(1));
		Thread.sleep(4000);

// Pass the email to the email field		  
		d.findElement(By.id("identifierId")).sendKeys(email);
		d.findElement(By.id("identifierId")).sendKeys(Keys.RETURN);
		d.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

// Pass the password  to the password field	        
		d.findElement(By.name("password")).sendKeys(password);
		d.findElement(By.name("password")).sendKeys(Keys.RETURN);
		Thread.sleep(4000);

// Switch back to main window
		d.switchTo().window(tabs.get(0));

// Click the Discover icon	
		Thread.sleep(4000);
		d.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/a[2]/div")).click();
		Thread.sleep(5000);

// Click the upload section and upload the photo from local drive
		WebElement uploadElement = d.findElement(By.xpath("/html/body/div[4]/div/div[1]/form/input"));
		Thread.sleep(7000);
		uploadElement.sendKeys(pictureLocation);

// Wait until the results are fetched
		WebDriverWait wait = new WebDriverWait(d, 18);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div/div[1]/div[2]/center/button[1]"))).click();

// Scroll down to bottom of the page
		Thread.sleep(40000);
		JavascriptExecutor js = (JavascriptExecutor) d;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		Thread.sleep(25000);

// Scroll up to top of the page			
		js.executeScript("window.scrollTo(0,0)");
		Thread.sleep(15000);

// Click the Save button
		d.findElement(By.xpath("/html/body/div[4]/div/div[1]/div[2]/center/button[1]")).click();

// Click the link text button
		Thread.sleep(15000);
		d.findElement(By.xpath("//*[@id=\"anchor-inspiration-created\"]/p/a[1]")).click();
		Thread.sleep(15000);

// Scroll down to bottom of the page
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		Thread.sleep(10000);

// Close the current window
		d.close();
	}
}
