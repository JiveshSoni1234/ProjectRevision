package RevisionFirst.ProjectRevision;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalenderSelect {
    public static void main(String [] args) throws InterruptedException {
        // 🎯 Year we want to select
        String year = "1997";
        
        // ⚙️ Setup Chrome options to allow remote origins
        ChromeOptions op = new ChromeOptions();
        op.addArguments("--remote-allow-origins=*");
        
        // 🚀 Setup WebDriverManager and launch Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(op);
        
        // 🌐 Open the webpage
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        driver.manage().window().maximize();
        Thread.sleep(1000);
        
        // 📅 Open the calendar widget
        driver.findElement(By.cssSelector("button[class*='react-date-picker__calendar-button']")).click();
        
        // 🔄 Switch to year view
        driver.findElement(By.cssSelector("span[class*='react-calendar__navigation__label__labelText']")).click();
        
        // 🔄 Switch to decade view
        driver.findElement(By.cssSelector("button[class='react-calendar__navigation__label']")).click();

        int year1 = Integer.parseInt(year);
        
        // ⏳ Loop until the desired year (1997) is found
        if(year1 <= 2025) {
            while(true) {
                Thread.sleep(1000);
                
                // 🔍 Pick a year element from the grid
                WebElement selectYear = driver.findElement(By.xpath("//button[contains(@class,'react-calendar__tile')][7]"));
                String txt = selectYear.getText();
                
                // ✅ If year matches → click it
                if(txt.equals(year)) {
                    selectYear.click();
                    break;
                } 
                else {
                    // ⬅️ Navigate to previous decade
                    driver.findElement(By.xpath("//button[contains(@class,'react-calendar__navigation__arrow react-calendar__navigation__prev-button')]")).click();
                }
            }
        }
        
        // 📌 Select a month (example: March → index [2])
        driver.findElement(By.xpath("//button[@class='react-calendar__tile react-calendar__year-view__months__month'][2]")).click();  
        Thread.sleep(1000);
        
        // 📌 Select a date (example: 7th day)
        driver.findElement(By.xpath("//button[contains(@class,'react-calendar__tile react-calendar')][7]")).click();
       
        // 📝 Print selected year from input box
        System.out.println(
            driver.findElement(By.cssSelector("input[class='react-date-picker__inputGroup__input react-date-picker__inputGroup__year']")).getAttribute("value")
        );
        
        // 🛑 Quit browser
        driver.quit();
    }
}
