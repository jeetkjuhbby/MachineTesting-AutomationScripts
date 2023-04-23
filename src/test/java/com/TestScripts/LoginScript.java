package com.TestScripts;

import java.time.Duration;

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
		wait.until(ExpectedConditions.visibilityOf(id)).sendKeys("admin");
		WebElement password = driver.findElement(By.xpath("//input[@formcontrolname='password']"));
		wait.until(ExpectedConditions.visibilityOf(password)).sendKeys("admin");
		WebElement loginButton = driver.findElement(By.id("kt_login_signin_submit"));
		String str = JOptionPane.showInputDialog("Enter Your captcha");
		WebElement captcha = driver.findElement(By.xpath("//input[@formcontrolname='captchaValue']"));
		captcha.sendKeys(str);
		loginButton.click();
		WebElement actualErrorMessage = driver.findElement(By.xpath("//div[contains(text(),'Invalid Email Id / Mobile No or Password.')]"));
		Assert.assertEquals(actualErrorMessage.getText(),"Invalid Email Id / Mobile No or Password.");
		
		String errorMessage = actualErrorMessage.getText();
		if(errorMessage.equals("Invalid Email Id / Mobile No or Password.")) {
			System.out.println("Failed To login ");
		}
		else {
			System.out.println("Succesfully logged In");
		}
		
		
	}
		
		@AfterMethod
		public void closeApp() {
		   driver.close();	
		}
		
		
		
		
		
		
		
		
}
