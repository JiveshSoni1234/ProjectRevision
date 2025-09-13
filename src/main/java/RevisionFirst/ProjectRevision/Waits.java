package RevisionFirst.ProjectRevision;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;


public class Waits {  // ✅ renamed to avoid conflict with Selenium's Wait interface
    public static void main(String [] args) {
    	
        // Create ChromeOptions to handle browser settings
        ChromeOptions op = new ChromeOptions();
        op.addArguments("--remote-allow-origins=*");
        
        // Setup ChromeDriver automatically using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(op);

        // Open the application URL
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
        
        // ---------------- Implicit Wait ----------------
        // Applies globally for all findElement calls
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // ---------------- Explicit Wait ----------------
        // Used for a specific condition
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Example: wait until some element becomes invisible
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(""))));
        
        // ---------------- Fluent Wait ----------------
        // More customizable: can define polling frequency & exceptions to ignore
        Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))   // max timeout
                .pollingEvery(Duration.ofSeconds(4))   // check every 4 sec
                .ignoring(NoSuchElementException.class); // ignore element not found

        // Use the FluentWait with custom condition
        WebElement foo = wait1.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                // Try to find the element
                WebElement element = driver.findElement(By.cssSelector("#yourElement")); // replace with actual selector
                // If element is visible, return it
                if (element.isDisplayed()) {
                    return element;
                }
                // Otherwise keep waiting (returning null tells FluentWait to retry)
                return null;
            }
        });

        // At this point, 'foo' will contain the element once condition is true
    }
}
