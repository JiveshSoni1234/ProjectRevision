package RevisionFirst.ProjectRevision;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HANDLEAJAXMOUSEINTERACTIONSDRAGDROPSWITCHTONEWTAB {
	public static void main(String args[]) throws IOException, InterruptedException {

		// ⚙️ Setup Chrome with remote origins allowed
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();

		// 🚀 Launch Chrome browser
		WebDriver driver = new ChromeDriver(options);

		// ========================== WINDOW HANDLING ==========================
//		// Navigate to login page
//		driver.get("https://rahulshettyacademy.com/loginpagePractise/#");
//		driver.findElement(By.cssSelector("a[href='https://rahulshettyacademy.com/documents-request']")).click();
//
//		// Get window handles and switch to child window
//		Set<String> set = driver.getWindowHandles();
//		Iterator<String> it = set.iterator();
//
//		String parentString = it.next();
//		String childString = it.next();
//		driver.switchTo().window(childString);
//
//		// Extract email domain text from paragraph
//		String string = driver.findElement(By.cssSelector("p[class='im-para red']")).getText()
//				.split("at")[1].trim().split("with")[0].trim();
//
//		System.out.print(string);

		// ========================== DRAG AND DROP ==========================
//		driver.get("https://jqueryui.com/droppable/");
//		driver.manage().window().maximize();
//
//		// Switch into iframe containing draggable elements
//		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[class='demo-frame']")));
//		Thread.sleep(1000);
//
//		// Identify source and target
//		WebElement sourceElement = driver.findElement(By.id("draggable"));
//		WebElement target = driver.findElement(By.id("droppable"));
//
//		// Perform drag and drop action
//		Actions actions = new Actions(driver);
//		actions.dragAndDrop(sourceElement, target).build().perform();
//
//		// Switch back to main page
//		driver.switchTo().defaultContent();

		// ========================== MOUSE ACTIONS ON AMAZON ==========================

		driver.get("https://www.amazon.com/");
		driver.manage().window().maximize();

		// Dismiss popup (if appears)
		driver.findElement(By.xpath("//button[text()='Continue shopping']")).click();
		Thread.sleep(2000);

		// Create Actions instance for mouse and keyboard interactions
		Actions actions = new Actions(driver);

//		// Hover on account & lists (example - currently commented)
//		WebElement element = driver.findElement(By.cssSelector("[id='nav-link-accountList']"));
//		actions.moveToElement(element).build().perform();

		Thread.sleep(2000);

		// Move to search box -> double click -> press SHIFT -> type "hello"
		actions.moveToElement(driver.findElement(By.cssSelector("input[id='twotabsearchtextbox']")))
				.doubleClick()
				.keyDown(Keys.SHIFT)
				.sendKeys("hello")
				.build()
				.perform();

		// 🔚 (Browser not closed so you can see the result, 
		// but best practice is to call driver.quit() at the end.)
	}
}
