package com.qualitystream.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CrossBrowserTesting {

  private WebDriver driver;
  By searchBox = By.name("q");
  By videoLinkLocator = By.cssSelector("a[href='https://www.youtube.com/watch?v=R_hh3jAqn8M']");


  @BeforeClass
  @Parameters({"URL", "BrowserType"})
  public void beforeClass(String url, String browserType) throws InterruptedException {

    if (browserType.equalsIgnoreCase("Chrome")) {
      System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
      driver = new ChromeDriver();
    } else if (browserType.equalsIgnoreCase("Firefox")) {
      System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
      driver = new FirefoxDriver();
    } else if (browserType.equalsIgnoreCase("Edge")) {
      System.setProperty("webdriver.edge.driver", "/usr/local/bin/msedgedriver");
      driver = new EdgeDriver();
    } else if (browserType.equalsIgnoreCase("Safari")) {
      driver = new SafariDriver();
      driver.get("https://qavbox.github.io/demo/signup/");
      Thread.sleep(3_000);
    }
    driver.manage().window().maximize();
    driver.get(url);

    System.out.println("Opening:" + browserType);
  }

  @Test
  public void googleSearch() {
    WebElement searchBoxElement = driver.findElement(searchBox);
    searchBoxElement.clear();
    searchBoxElement.sendKeys("quality-stream Introducción a la Automatización de Pruebas de Software");
    searchBoxElement.submit();

    WebDriverWait wait = new WebDriverWait(driver, 7);
    wait.until(ExpectedConditions.presenceOfElementLocated(videoLinkLocator));

    assertTrue(driver.findElement(videoLinkLocator).isDisplayed());
  }

  @AfterClass
  public void afterClass() {

  }
}
