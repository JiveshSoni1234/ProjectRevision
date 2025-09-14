package RevisionFirst.ProjectRevision;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JSExecutor {
    public static void main(String args[]) throws InterruptedException {
        
        // ⚙️ Setup Chrome options to allow remote origins
        ChromeOptions op = new ChromeOptions();
        op.addArguments("--remote-allow-origins=*");
        
        // 🚀 Setup WebDriverManager and launch Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(op);
        
        // 🌐 Open the webpage
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        
        // 🔽 Scroll the main page vertically (down by 500px)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        
        Thread.sleep(1000);
        
        // 📜 Scroll inside the fixed table (scrollable div)
        js.executeScript("document.querySelector('.tableFixHead').scrollTop=5000");
        
        // 📊 Collect all values from 4th column of the table
        List<WebElement> ls = driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));
        int sum = 0;
        
        // ➕ Add each value and print running total
        for (int i = 0; i < ls.size(); i++) {
            sum = sum + Integer.parseInt(ls.get(i).getText());
            System.out.println("Running Total = " + sum);
        }
        
        // ✅ Get total amount displayed at bottom of table
        String s = driver.findElement(By.cssSelector(".totalAmount")).getText().split(":")[1].trim();
        
        // 🔍 Compare calculated sum with displayed total
        if (sum == Integer.parseInt(s)) {
            System.out.println("✔️ Value matches with displayed total: " + s);
        } else {
            System.out.println("❌ Value does NOT match! Calculated: " + sum + " | Displayed: " + s);
        }
        
        // 🛑 Quit browser
        driver.quit();
    }
}
