/* Automation script to Add  an customer and validation 

    Steps:1)Open the browser enter the Url
          2)Fill the mandotary Details
          3)Check Whether new account is created
	  4)Use paremeterization 
*/
package com.TestScripts;

import java.time.Duration;
import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddCustomerScript {
	WebDriver driver;
	WebDriverWait wait;

	// @Parameters({"browserName"})
	@BeforeMethod
	public void oppenApp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://testffc.nimapinfotech.com/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	@Test(priority = 1)
	public void login() throws InterruptedException {
		WebElement id = driver.findElement(By.xpath("//input[@formcontrolname='username']"));
		wait.until(ExpectedConditions.visibilityOf(id)).sendKeys("jshankhpal40@gmail.com");
		WebElement password = driver.findElement(By.xpath("//input[@formcontrolname='password']"));
		wait.until(ExpectedConditions.visibilityOf(password)).sendKeys("jitesh0987");
		WebElement loginButton = driver.findElement(By.id("kt_login_signin_submit"));
		String str = JOptionPane.showInputDialog("Enter Your captcha");
		WebElement captcha = driver.findElement(By.xpath("//input[@formcontrolname='captchaValue']"));
		captcha.sendKeys(str);
		loginButton.click();
		Thread.sleep(5000);

	}

	@AfterMethod
	public void addCustomer() throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='My Customers']")).click();
		driver.findElement(By.xpath("//span[text()=' New Customer ']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='LeadName']")).sendKeys("nimapInfotech");
		driver.findElement(By.xpath("//input[@formcontrolname='RefNo']")).sendKeys("10023");
		driver.findElement(By.xpath("//input[@formcontrolname='PersonName']")).sendKeys("Jitesh");
		driver.findElement(By.xpath("//input[@formcontrolname='MobileNo']")).sendKeys("9637408888");
		driver.findElement(By.xpath("//input[@formcontrolname='Email']")).sendKeys("Jitesh@gmail.com");
		driver.findElement(By.xpath("//input[@formcontrolname='PersonDesignation']")).sendKeys("Tester");

		driver.findElement(By.xpath("(//div[@class='mat-select-value'])[15]")).click();
		List<WebElement> allCountries = driver
				.findElements(By.xpath("//mat-option[@class='mat-option ng-star-inserted']"));

		System.out.println(allCountries.size());

		for (WebElement country : allCountries) {
			if (country.getText().equals("India")) {
				country.click();
			}
		}

		driver.findElement(By.xpath("//div[@class='mat-checkbox-inner-container']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//button[@class='mat-raised-button mat-button-base mat-primary'])[4]")).click();

		driver.findElement(By.xpath("//span[text()=' Save ']")).click();
		Thread.sleep(5000);

		WebElement accCreated = driver
				.findElement(By.xpath("//div[@class='toast-top-right toast-container']"));

		System.out.println(accCreated.getText());
		

	}

}
