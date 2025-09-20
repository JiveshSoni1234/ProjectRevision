package RevisionFirst.ProjectRevision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StreamsinWEB {
	public static void main(String[] args) throws InterruptedException {

		// ⚙️ Setup Chrome with remote origins allowed
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");

		// 🚀 Launch Chrome browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(op);

		// 🌐 Open webpage
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.manage().window().maximize();

		Thread.sleep(5000);

		// 📊 Click on the first column header to sort the table
		driver.findElement(By.xpath("//tr/th[1]")).click();

		// 🔎 Capture all values from the first column
		List<WebElement> element = driver.findElements(By.xpath("//tr/td[1]"));

		// 📝 Convert WebElements to a list of Strings
		List<String> originalList = element.stream().map(s -> s.getText()).collect(Collectors.toList());

		// 📌 Create a sorted version of the list
		List<String> sortedList = originalList.stream().sorted().collect(Collectors.toList());

		// ✅ Compare original vs sorted list
		if (originalList.equals(sortedList)) {
			System.out.println("Column is sorted correctly");
		}

		// 💲 Declare a list to store prices
		List<String> price;

		// 🔄 Keep clicking Next until "Beans" is found
		do {
			List<WebElement> rows = driver.findElements(By.xpath("//tr/td[1]"));

			// 🥗 Find the price of "Beans"
			price = rows.stream()
					.filter(s -> s.getText().contains("Beans"))
					.map(s -> getPriceVeg(s))
					.collect(Collectors.toList());

			// 🖨️ Print price of Beans if found
			price.forEach(a -> System.out.println(a));

			// ⏭️ If "Beans" not found, click Next
			if (price.size() < 1) {
				driver.findElement(By.cssSelector("a[aria-label='Next']")).click();
				Thread.sleep(2000);
			}
		} while (price.size() < 1);

		// 🔍 Search for "Rice"
		driver.findElement(By.cssSelector("input[id='search-field']")).sendKeys("Rice");
		Thread.sleep(1000);

		// 📥 Capture all items in the first column
		List<WebElement> veggies = driver.findElements(By.xpath("//tr/td[1]"));

		// 🎯 Filter items containing "Rice"
		List<WebElement> filtered = veggies.stream()
				.filter(s -> s.getText().contains("Rice"))
				.collect(Collectors.toList());

		// 📏 Compare list sizes (to check filtering worked correctly)
		System.out.println(veggies.size() + " " + filtered.size());

		// 🧾 Create an ArrayList
		ArrayList<String> ls = new ArrayList<>();
		ls.add("Jivesh1");
		ls.add("Ashish");
		ls.add("Harshit");
		ls.add("Anuj");
		ls.add("Amit");

		// 🔢 Count names starting with "d"
		long c = ls.stream().filter(s -> s.startsWith("d")).count();
		System.out.println(c);

		// 📦 Directly use Stream without ArrayList
		c = Stream.of("Jivesh", "Mayank", "Anuj")
				.filter(s -> { 
					s.startsWith("A");  // (condition not applied properly here)
					return true; 
				}).count();
		System.out.println(c);

		// 🖨️ Print names with length > 4
		ls.stream().filter(s -> s.length() > 4).forEach(System.out::println);

		// ⏹️ Limit example (print only first match where length > 4)
		ls.stream().filter(s -> s.length() > 4).limit(1).forEach(System.out::println);

		// 🔠 Convert names to UPPERCASE where length > 4
		ls.stream().filter(s -> s.length() > 4).map(String::toUpperCase).forEach(System.out::println);

		// 📑 Sort names with length > 4
		ls.stream().filter(s -> s.length() > 4).sorted().forEach(System.out::println);

		// 🔗 Concatenate two lists
		List<String> list1 = Arrays.asList("Jivesh3", "Anuj3", "Ashish3", "Amit3", "Harshit3");
		List<String> list2 = Arrays.asList("Jivesh2", "Anuj2", "Ashish2", "Amit2", "Harshit2");

		// 🌀 Merge list1 + list2 and sort
		Stream<String> stream1 = Stream.concat(list1.stream(), list2.stream());
		stream1.sorted().forEach(System.out::println);

		// ✅ Re-create stream for reuse (since streams can’t be reused after terminal op)
		stream1 = Stream.concat(list1.stream(), list2.stream());
		boolean flag = stream1.anyMatch(s -> s.equalsIgnoreCase("Jivesh2"));
		System.out.println(flag);

		// 🅰️ Filter strings starting with "A" and convert to uppercase
		List<String> ls1 = Stream.of("J", "A", "M", "Ass")
				.filter(name -> name.startsWith("A"))
				.map(String::toUpperCase)
				.collect(Collectors.toList());
		System.out.println(ls1);

		// 🔢 Print unique numbers from a stream
		Stream.of(1, 2, 3, 4, 1, 4, 3, 5).distinct().forEach(System.out::println);

		// 🏁 Close browser
		driver.quit();
	}

	// 🛠️ Utility method to fetch price of a vegetable
	private static String getPriceVeg(WebElement s) {
		return s.findElement(By.xpath("following-sibling::td[1]")).getText();
	}
}
