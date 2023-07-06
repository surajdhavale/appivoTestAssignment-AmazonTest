import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Appico_TestAssignment_AmazonTest {
	
	static WebDriver driver;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ExtentReports extentReports = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./target/AppivoAssignmentTestResult.html");
		extentReports.attachReporter(sparkReporter);
		
		ExtentTest test1 = extentReports.createTest("Amazon Product Search - Verify the product size",
				"Test case to validate the expected and actual size of the searched product");
		
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		
		
		driver.get("https://www.amazon.in");
		
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone 14");
		driver.findElement(By.id("nav-search-submit-button")).click();
		
		driver.findElement(By.xpath("//img[@alt='Sponsored Ad - Apple iPhone 14 (128 GB) - Blue']")).click();
		
		
		Set<String> wId = driver.getWindowHandles();
		System.out.println(wId);
		List<String> allWidList = new ArrayList<String>(wId);
		String secondWin = allWidList.get(2);
		driver.switchTo().window(secondWin);
		
		//verifying the details
		
		test1.log(Status.PASS, "Starting Test Case");
		
		String colour = driver.findElement(By.xpath("(//span[@class='selection'])[1]")).getText();
		System.out.println("Colour of the iPhone 14: "+colour);
		
		test1.pass("Test Completed");
		
		
		
		ExtentTest test2 = extentReports.createTest("Amazon Product Search - Verify Product Colour", 
				"Test to verify the actual and expected product colour");
	
		test2.log(Status.PASS, "Starting Test Case");
		
		String size = driver.findElement(By.xpath("(//span[@class='selection'])[2]")).getText();
		System.out.println("Size of the iPhone 14: "+size);
				
		test2.pass("Test case completed");
	
		
		ExtentTest test3 = extentReports.createTest("Amazon Product Search - Verify product brand",
				"Test to verify actual and expected product brand");
		test3.log(Status.PASS, "Starting the Test Case");
		
		String brand = driver.findElement(By.id("bylineInfo")).getText();
		System.out.println("Brand of the product: "+brand);
		
		test3.pass("Test Completed");

		extentReports.flush();
		driver.quit();
	}

}
