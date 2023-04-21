package com.TestScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddCustomerScript {
	
	public WebDriver driver;
	ChromeOptions capability;
	
	@BeforeMethod
	public void OpenBrowser() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://testffc.nimapinfotech.com/auth/register");
		 
		
	}
	
	@Test
	public void AddCustomer() throws InterruptedException {
		driver.findElement(By.xpath("//input[@formcontrolname='name']")).sendKeys("Jitesh");
		driver.findElement(By.xpath("//input[@formcontrolname='mobileno']")).sendKeys("9617408555");
		driver.findElement(By.xpath("//input[@formcontrolname='emailid']")).sendKeys("jshankhpal42@gmail.com");
		Thread.sleep(8000);
		driver.findElement(By.id("kt_login_signin_submit")).click();
		Thread.sleep(8000);
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("admin12345");
		driver.findElement(By.xpath("//input[@formcontrolname='confirmPassword']")).sendKeys("admin12345");
		driver.findElement(By.id("kt_login_signin_submit")).click();
		
		WebElement errorMessage = driver.findElement(By.xpath("//div[text()='Please Enter Correct OTP']"));
		
		String actualErrorMessage=errorMessage.getText();
		
		if(actualErrorMessage.endsWith("Please Enter Correct OTP")) {
			System.out.println("Sign-up failed");
		}
		else {
			System.out.println("Sign-up successfull");
		}
		
		
		
	}
		
		@AfterMethod
		public void CloseBrowser() {
			//driver.close();
			
	
	}

}
