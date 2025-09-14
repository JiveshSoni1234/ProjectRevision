package RevisionFirst.ProjectRevision;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CookiesScreenshotBrokenLink {
    public static void main(String args[]) throws InterruptedException, IOException {
        
        // ⚙️ Setup Chrome options to allow remote origins
        ChromeOptions op = new ChromeOptions();
        op.addArguments("--remote-allow-origins=*");
        
        // 🚀 Setup WebDriverManager and launch Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(op);
        
        // 🌐 Open the webpage
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        
        // 🍪 (Optional) Manage cookies
        // driver.manage().deleteAllCookies();  // delete all cookies
        // driver.manage().deleteCookieNamed("asdf"); // delete specific cookie by name
        
        // 📸 Take a screenshot of the page
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("D:\\java practice\\ProjectRevision\\target\\src.png"));
        
        // 🔗 Automate broken links check for a single link (soapUI)
        String url = driver.findElement(By.cssSelector("a[href*='soapui']")).getAttribute("href");
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("HEAD");
        con.connect();
        System.out.println("Response Code (soapUI link): " + con.getResponseCode());
        
        // 🔗 Get all footer links
        List<WebElement> ls = driver.findElements(By.cssSelector(".gf-li a"));
        
        // 🔎 Check all links manually
        for (WebElement ele : ls) {
            url = ele.getAttribute("href");
            System.out.println("Checking: " + url);
            
            con = (HttpURLConnection) new URL(url).openConnection();
            int responseCode = con.getResponseCode();
            System.out.println("Response: " + responseCode);
            
            if (responseCode > 400) {
                System.out.println("❌ Broken Link found: " + url);
            } else {
                System.out.println("✅ Working Link: " + url);
            }
        }
        
        // 🧪 Soft Assertions with TestNG
//        SoftAssert s = new SoftAssert();
//        for (WebElement ele : ls) {
//            url = ele.getAttribute("href");
//            con = (HttpURLConnection) new URL(url).openConnection();
//            int responseCode = con.getResponseCode();
//            
//            // Soft check (doesn’t stop execution immediately)
//            s.assertTrue(responseCode < 400, 
//                "❌ Broken link: " + ele.getText() + " | Response Code: " + responseCode);
//        }
//        // 🚨 Collect all assertion results
//        s.assertAll();
        
        // 🛑 Quit browser
        driver.quit();
    }
}
