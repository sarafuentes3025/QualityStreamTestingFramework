package com.qualitystream.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestNGDataProviderExample {

  WebDriver driver;

  By signInLocator = By.linkText("Sign in");
  By emailLocator = By.id("email");
  By passwordLocator = By.id("passwd");
  By signInButtonLocator = By.id("SubmitLogin");

  By signOutButtonLocator = By.cssSelector("a.logout");

  @BeforeClass
  public void beforeClass() {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("http://automationpractice.com/index.php");
  }

  @Test(dataProvider = "authenticationData")
  public void login(String email, String password) {

    if (driver.findElement(signInLocator).isDisplayed()) {
      driver.findElement(signInLocator).click();

      WebDriverWait wait = new WebDriverWait(driver, 10);
      wait.until(ExpectedConditions.presenceOfElementLocated(emailLocator));

      driver.findElement(emailLocator).sendKeys(email);
      driver.findElement(passwordLocator).sendKeys(password);
      driver.findElement(signInButtonLocator).click();

      assertEquals(driver.findElement(signOutButtonLocator).getText(), "Sign out");

      driver.findElement(signOutButtonLocator).click();
    } else {
      System.out.println("Sing in link is not present");
    }
  }

  @DataProvider(name = "authenticationData")
  public Object[][] getData() {

    Object[][] data = new Object[2][2];

    data[0][0] = "qs123@gmail.com";
    data[0][1] = "qs123";
    data[1][0] = "testng_qs@gmail.com";
    data[1][1] = "qs123";

    return data;
  }

  @AfterClass
  public void afterClass() {
//  driver.close();
  }


}
