package RevisionFirst.ProjectRevision;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectDynamicAutosuggest {
	public static void main(String[] args) throws InterruptedException, IOException {

		// ⚙️ Setup Chrome options to allow remote origins (needed for some Chrome versions)
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");

		// 🚀 Setup WebDriverManager and launch Chrome browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(op);

		// 🌐 Open the webpage
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/#");
		driver.manage().window().maximize();

		// ========================== STATIC DROPDOWN ==========================

		// Using Select class to handle dropdown with <select> tag
		WebElement element = driver.findElement(By.cssSelector("Select[id='ctl00_mainContent_DropDownListCurrency']"));
		Select s = new Select(element);
		s.selectByVisibleText("AED"); // Select currency AED from dropdown

		Thread.sleep(1000);

		// ========================== DYNAMIC DROPDOWN ==========================

		// Click on departure city dropdown
		driver.findElement(By.xpath("//span[@id='ctl00_mainContent_ddl_originStation1_CTXTaction']")).click();
		driver.findElement(By.xpath("//a[@value='AMD']")).click(); // Select 'Ahmedabad' as source

		Thread.sleep(5000);

		// Select 'Bangalore' as destination (appears in second dropdown)
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='BLR']"))
				.click();

		// ========================== CALENDAR ==========================

		// Select the current date (highlighted in calendar)
		driver.findElement(By.cssSelector(".ui-state-default.ui-state-active")).click();

		// ========================== PASSENGERS ==========================

		// Open passenger info dropdown
		driver.findElement(By.cssSelector("div[id='divpaxinfo']")).click();
		Thread.sleep(1000);

		// Increase adults by clicking + button 3 times
		for (int i = 0; i < 3; i++) {
			driver.findElement(By.cssSelector("span[id='hrefIncAdt']")).click();
		}

		// Close passenger selection popup
		driver.findElement(By.cssSelector("#btnclosepaxoption")).click();

		// ========================== AUTO-SUGGEST DROPDOWN ==========================

		// Enter country name prefix
		driver.findElement(By.cssSelector("#autosuggest")).sendKeys("ind");

		// Get all auto-suggested results
		List<WebElement> element2 = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
		Thread.sleep(3000);

		// Loop through suggestions and select "India"
		for (WebElement Element : element2) {
			if (Element.getText().equalsIgnoreCase("India")) {
				Element.click();
				break;
			}
		}

		// ========================== CHECKBOX ==========================

		// Check if Senior Citizen Discount checkbox is selected
		System.out.println(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());

		// Click on Senior Citizen Discount checkbox
		driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();

		// Again verify if it is selected
		System.out.println(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());

		// Count total number of checkboxes
		System.out.println(driver.findElements(By.cssSelector("input[type='checkbox']")).size());

		// ========================== RADIO BUTTON ==========================

		// Check if trip selection radio buttons are enabled
		System.out.println(driver.findElement(By.name("ctl00$mainContent$rbtnl_Trip")).isEnabled());

		// Get the 'style' attribute of return date field (to check if enabled/disabled)
		System.out.println(driver.findElement(By.id("Div1")).getAttribute("style"));

		// Select "Round Trip"
		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
		System.out.println(driver.findElement(By.id("Div1")).getAttribute("style"));

		// Verify if return date is enabled after selecting Round Trip
		if (driver.findElement(By.id("Div1")).getAttribute("style").contains("1")) {
			System.out.println("Return date is enabled");
		} else {
			System.out.println("Return date is not enabled");
		}

		// Again check if radio button group is enabled
		System.out.println(driver.findElement(By.name("ctl00$mainContent$rbtnl_Trip")).isEnabled());

		// Select "One Way" again
		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0")).click();

		Thread.sleep(1000);

		// Close the browser
		driver.quit();
	}
}
