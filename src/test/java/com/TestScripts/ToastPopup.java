package com.TestScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ToastPopup {
	public WebDriver driver;
	public WebDriverWait wait;

	@BeforeMethod
	public void OpenApp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://testffc.nimapinfotech.com/auth/login");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	@Test
	public void ValidateToastpopup() throws InterruptedException {
		WebElement id = driver.findElement(By.xpath("//input[@formcontrolname='username']"));
		wait.until(ExpectedConditions.visibilityOf(id)).sendKeys("admin");
		WebElement password = driver.findElement(By.xpath("//input[@formcontrolname='password']"));
		wait.until(ExpectedConditions.visibilityOf(password)).sendKeys("admin");
		WebElement loginButton = driver.findElement(By.id("kt_login_signin_submit"));
		Thread.sleep(10000);
		loginButton.click();

		WebElement popupElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Please Enter Correct Captcha')]")));

		assert popupElement.getText().equals("Please Enter Correct Captcha");
		
		String toastPopup=popupElement.getText();
		
		if(toastPopup.equals("\"Please Enter Correct Captcha\"")) {
			System.out.println("Test script passed");
		}
		else {
			System.out.println("Test script Failed");
		}

	}

	@AfterMethod
	public void CloseAp() {
		driver.close();
	}

}
