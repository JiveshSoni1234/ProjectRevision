package RevisionFirst.ProjectRevision;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Section11CountLinks {
	/**
	 * @param args
	 */
	public static void main(String args[]) {

		// Set Chrome options to handle remote origin issues
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");

		// Setup ChromeDriver using WebDriverManager
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(op);

		// Open the practice website
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();

		// 1. Find total number of links on the entire page
		System.out.println(driver.findElements(By.tagName("a")).size());

		// 2. Find total number of links present in the footer section
		WebElement footer = driver.findElement(By.id("gf-BIG"));
		System.out.println(footer.findElements(By.tagName("a")).size());

		// 3. Find number of links present in the first column of the footer
		footer = driver.findElement(By.xpath("//div[contains(@id,'gf-BIG')]/table/tbody/tr/td[1]/ul"));
		System.out.println(footer.findElements(By.tagName("a")).size());

		// 4. Open each link of the first footer column in a new tab
		for (int i = 0; i < footer.findElements(By.tagName("a")).size(); i++) {
			String clickOnNewTab = Keys.chord(Keys.CONTROL, Keys.ENTER); // Ctrl+Enter = open link in new tab
			footer.findElements(By.tagName("a")).get(i).sendKeys(clickOnNewTab);
		}

		// 5. Get window handles of all opened tabs
		Set<String> abc = driver.getWindowHandles();
		Iterator<String> it = abc.iterator();

		// 6. Iterate through all opened tabs and print their URL and Title
		while (it.hasNext()) {
			driver.switchTo().window(it.next()); // switch to each tab
			System.out.println(driver.getCurrentUrl() + " " + driver.getTitle());
		}

		// 7. Quit the browser
		driver.quit();
	}
}
