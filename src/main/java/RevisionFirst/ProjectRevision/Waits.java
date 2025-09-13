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


public class Waits {
public static void main(String [] args) {
	
    ChromeOptions op = new ChromeOptions();
    op.addArguments("--remote-allow-origins=*");
    
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver(op);

    driver.get("https://rahulshettyacademy.com/client/#/auth/login");
    
    //Syntax of implicit wait
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    
    //Syntax of explicit wait
    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(""))));
    
    //Syntax of fluent wait
    Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofSeconds(4))
            .ignoring(NoSuchElementException.class);

    WebElement foo = wait1.until(new Function<WebDriver, WebElement>() {
        @Override
        public WebElement apply(WebDriver driver) {
            WebElement element = driver.findElement(By.cssSelector("#yourElement")); // replace with actual CSS selector
            if (element.isDisplayed()) {
                return element;
            }
            return null; // Important: must return null if condition not met
        }
    });

	
}
}
