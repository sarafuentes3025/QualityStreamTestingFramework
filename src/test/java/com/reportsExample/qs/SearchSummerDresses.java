package com.reportsExample.qs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SearchSummerDresses {

  WebDriver driver;
  By searchBoxLocator = By.id("search_query_top");
  By resultsLocator = By.cssSelector("span.heading-counter");

  @BeforeClass
  public void beforeClass() {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("http://automationpractice.com/index.php");
  }

  @Test
  public void search_SummerDresses() {
    WebElement searchBox = driver.findElement(searchBoxLocator);
    searchBox.clear();
    searchBox.sendKeys("summer dresses");
    searchBox.submit();

    WebDriverWait wait = new WebDriverWait(driver, 7);
    wait.until(ExpectedConditions.presenceOfElementLocated(resultsLocator));

    System.out.println("This is the result number:" + driver.findElement(resultsLocator).getText());

    assertThat(driver.findElement(resultsLocator).getText()).isEqualTo("4 results have been found.");
  }

  @AfterClass
  public void afterClass() {
  }
}
