package RevisionFirst.ProjectRevision;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

public class Locators {
    // Using static email so it can be reused in signup + login
    public static String email = "jivesh156@gmail.com";

    public static void main(String args[]) throws InterruptedException {
        // ✅ ChromeOptions → to allow cross-origin access (important for some sites)
        ChromeOptions op = new ChromeOptions();
        op.addArguments("--remote-allow-origins=*");

        // ✅ WebDriverManager handles driver setup automatically (no need to download exe manually)
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(op);

        // Open application
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");

        // ✅ Flow: Try login → if not registered → signup → then login again
        login(driver);
        sigup(driver);
        login(driver);
        driver.navigate().to("https://www.youtube.com/");
        driver.navigate().back();
        driver.navigate().forward();
        driver.quit();
    }

    // ================= SIGNUP =================
    public static void sigup(WebDriver driver) throws InterruptedException {
        // ✅ CSS selectors using id
        driver.findElement(By.cssSelector("[id='firstName']")).sendKeys("Jivesh");
        driver.findElement(By.cssSelector("[id='lastName']")).sendKeys("Jivesh");
        driver.findElement(By.cssSelector("[id='userEmail']")).sendKeys(email);
        driver.findElement(By.cssSelector("[id='userMobile']")).sendKeys("8168175536");

        // ✅ Dropdown handling
        driver.findElement(By.cssSelector("select[formcontrolname='occupation']")).click();

        // Print dropdown values for debugging
        List<WebElement> list = driver.findElements(By.cssSelector("select[formcontrolname='occupation']"));
        for (WebElement elements : list) {
            System.out.println(elements.getText() + " " + elements.getSize());
        }

        // ✅ Using Select class for dropdown
        WebElement dropdownElement = driver.findElement(By.cssSelector("select[formcontrolname='occupation']"));
        Select selectObject = new Select(dropdownElement);
        selectObject.selectByVisibleText("Doctor");
        dropdownElement.click();

        // ✅ Xpath locators
        driver.findElement(By.xpath("//input[@value='Male']")).click(); // gender
        driver.findElement(By.xpath("//div/input[@formcontrolname='userPassword']")).sendKeys("Manuson7@");
        driver.findElement(By.xpath("//div/input[@formcontrolname='confirmPassword']")).sendKeys("Manuson7@");
        driver.findElement(By.xpath(" //div/input[@type='checkbox']")).click(); // terms & conditions
        driver.findElement(By.cssSelector(".login-btn")).click(); // register button
        Thread.sleep(1000);

        // ✅ Click login after signup
        driver.findElement(By.xpath("//button[text()='Login']")).click();
    }

    // ================= LOGIN =================
    public static void login(WebDriver driver) throws InterruptedException {
        driver.manage().window().maximize();

        // ✅ Login form locators
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.id("userPassword")).sendKeys("Manuson7@");
        driver.findElement(By.id("login")).click();

        System.out.println(driver.getCurrentUrl());
        Thread.sleep(1000);

        // ✅ If login successful → search page
        if (driver.getCurrentUrl().equals("https://rahulshettyacademy.com/client/#/dashboard/dash")) {
            Search(driver);
        } else {
            // ✅ If login failed → print error & redirect to signup
            WebElement element = driver.findElement(By.className("toast-message"));
            String s = element.getAttribute("aria-label");
            System.out.println(s);

            driver.findElement(By.linkText("Register here")).click();
        }
    }

    // ================= SEARCH =================
    public static void Search(WebDriver driver) throws InterruptedException {
        // ❌ Problem with direct locator → "element not interactable"
        // Reason: element is in DOM but hidden OR overlapped
        // ✅ Solution: Loop through all and interact with only displayed one
        List<WebElement> inputs = driver.findElements(By.cssSelector("input[formcontrolname='productName']"));
        for (WebElement input : inputs) {
            if (input.isDisplayed()) {
                input.click(); // bring focus
                input.sendKeys("ZARA COAT 3"); // enter product name
                break;
            }
        }

        // ✅ Sign Out button using text()
        driver.findElement(By.xpath("//*[text()=' Sign Out ']")).click();

        // ✅ Fetch href attribute examples
        String s = driver.findElement(By.cssSelector("span a[href*='emailto']")).getAttribute("href");
        System.out.println(s);

        s = driver.findElement(By.xpath("//a[contains(@href,'dummywebsite@rahulshettyacademy.com')]"))
                .getAttribute("href");
        System.out.println(s);

        // ✅ Extra locator practice examples
        // 1. Following-sibling
        //    //header/div/button[1]//following-sibling::button[1]
        // 2. Parent
        //    //header/div/button[1]//parent::div
        // 3. Ancestor
        //    //header/div/button[1]//parent::div//parent::header
        // 4. CSS nth-child
        //    div[class="tableFixHead"] tbody tr:nth-child(1)
        // 5. Starts-with
        //    //input[starts-with(@id,'user')]
        // 6. OR / AND
        //    //input[@id='userEmail' or @name='email']
        //    //input[@id='login' and @type='submit']
        // 7. By.tagName
        //    By.tagName("p")
        // 8. By.name
        //    By.name("dgd")
    }
}
