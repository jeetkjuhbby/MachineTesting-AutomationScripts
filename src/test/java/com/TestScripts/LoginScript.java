package com.TestScripts;

import java.time.Duration;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginScript {
	public WebDriver driver;
	public WebDriverWait wait;
	
	@Parameters({"browserName"})
	@BeforeMethod
	public void OpenApp(String browser){
		if(browser.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else {
			driver=new FirefoxDriver();
		}
		
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://testffc.nimapinfotech.com/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
	}
	@Test
	public void login() throws InterruptedException {
		
		
		WebElement id = driver.findElement(By.xpath("//input[@formcontrolname='username']"));
		wait.until(ExpectedConditions.visibilityOf(id)).sendKeys("jshankhpal40@gmail.com");
		WebElement password = driver.findElement(By.xpath("//input[@formcontrolname='password']"));
		wait.until(ExpectedConditions.visibilityOf(password)).sendKeys("jitesh0987");
		WebElement loginButton = driver.findElement(By.id("kt_login_signin_submit"));
		String str = JOptionPane.showInputDialog("Enter Your captcha");
		WebElement captcha = driver.findElement(By.xpath("//input[@formcontrolname='captchaValue']"));
		captcha.sendKeys(str);
		//Thread.sleep(10000);
		loginButton.click();
		
		Thread.sleep(5000);
		
         String actualUrl= "https://testffc.nimapinfotech.com/dashboard";
         String expectedUrl = driver.getCurrentUrl();
    
         
         if(actualUrl.endsWith(expectedUrl)) {
        	 System.out.println("Login Successfull");
         }
         else {
        	 System.out.println("Login Unsuccessfull");
         }
       
 		
	}
		
		@AfterMethod
		public void closeApp() {
		   driver.close();	
		}
		
		
		
		
		
		
		
		
}
