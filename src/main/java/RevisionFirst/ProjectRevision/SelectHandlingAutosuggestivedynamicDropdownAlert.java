package RevisionFirst.ProjectRevision;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectHandlingAutosuggestivedynamicDropdownAlert {
	 public static void main(String [] args) throws InterruptedException, IOException {
		 
	        // ⚙️ Setup Chrome options to allow remote origins (needed for some Chrome versions)
	        ChromeOptions op = new ChromeOptions();
	        op.addArguments("--remote-allow-origins=*");
	        
	        // 🚀 Setup WebDriverManager and launch Chrome browser
	        WebDriverManager.chromedriver().setup();
	        WebDriver driver = new ChromeDriver(op);
	        
	        // 🌐 Open the webpage
			driver.get("https://rahulshettyacademy.com/AutomationPractice/");
	        driver.manage().window().maximize();
	        
	        Thread.sleep(1000);
	        WebElement ele=driver.findElement(By.id("dropdown-class-example"));
	      
	        Select dropDown=new Select(ele);
	        dropDown.selectByIndex(1);
	        
	       
	        driver.findElement(By.id("alertbtn")).click();
	        driver.switchTo().alert().accept();
	        
	       
	        
	 }
}
