package RevisionFirst.ProjectRevision;

// Import RelativeLocator methods (above, below, toLeftOf, etc.)
import static org.openqa.selenium.support.locators.RelativeLocator.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RelativeLocatorsInvokingMulttiplewindows {
    public static void main(String[] args) throws InterruptedException, IOException {

        // ⚙️ Setup Chrome options to allow remote origins (needed for some Chrome versions)
        ChromeOptions op = new ChromeOptions();
        op.addArguments("--remote-allow-origins=*");

        // 🚀 Setup WebDriverManager and launch Chrome browser
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(op);

        // 🌐 Open the target webpage
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.manage().window().maximize(); // Maximize browser window
        Thread.sleep(1000); // Wait 1 second for page to load

        // 🔹 Find "Name" input field
        WebElement nameEditBox = driver.findElement(By.name("name"));
        
        // 🔹 Find the label above the "Name" input field using RelativeLocator
        System.out.println(driver.findElement(with(By.tagName("label")).above(nameEditBox)).getText());

        // 🔹 Find the "Date of Birth" label
        WebElement dateOfBirth = driver.findElement(By.cssSelector("label[for='dateofBirth']"));
        // Click on the input field below the "Date of Birth" label
        driver.findElement(with(By.tagName("input")).below(dateOfBirth)).click();

        // 🔹 Find the checkbox label text
        WebElement checkBox = driver.findElement(By.xpath("//*[text()='Check me out if you Love IceCreams!']"));
        // Click on the input field to the left of the checkbox label
        driver.findElement(with(By.tagName("input")).toLeftOf(checkBox)).click();

        // ➕ Open a new browser tab
        driver.switchTo().newWindow(WindowType.TAB);

        // 🔑 Get all window handles (IDs for all open windows/tabs)
        Set<String> s = driver.getWindowHandles();
        Iterator<String> it = s.iterator();

        // Store parent (first) and child (newly opened) window IDs
        String parentWindow = it.next();
        String childWindow = it.next();

        // 🔄 Switch to child window (new tab) and open a new URL
        driver.switchTo().window(childWindow);
        driver.get("https://rahulshettyacademy.com/"); // Open different URL in child tab

        // 🔄 Switch back to parent window
        driver.switchTo().window(parentWindow);

        // ✍️ Enter text into the "Name" field
        driver.findElement(By.name("name")).sendKeys("Keys");

        // 📸 Take screenshot of the "Name" input field only
        WebElement takess = driver.findElement(By.name("name"));
        File src = takess.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("D:\\java practice\\ProjectRevision\\src2.png"));

        // 📏 Get height and width of the "Name" input field
        System.out.println(
                takess.getRect().getDimension().getHeight() + " " + takess.getRect().getDimension().getWidth());

        // ❌ Close all browser windows
        driver.quit();
    }
}
