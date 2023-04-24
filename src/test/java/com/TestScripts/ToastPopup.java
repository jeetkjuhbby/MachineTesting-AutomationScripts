package com.TestScripts;

import java.time.Duration;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ToastPopup {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeMethod
	public void oppenApp() {
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://testffc.nimapinfotech.com/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
	}
	@Test
	public void Login() {

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
		
		driver.findElement(By.xpath("//button[@class='buttonData punchBtn mat-raised-button mat-button-base mat-primary']")).click();
		
		WebElement toastPopup = driver
				.findElement(By.xpath("//div[@class='toast-top-right toast-container']"));

		System.out.println(toastPopup.getText());
		
		
		
	}

}
