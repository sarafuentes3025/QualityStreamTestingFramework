package com.checkingLinks.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CheckLinksTest {

  WebDriver driver;
  CheckingLinksPage page;

  @BeforeClass
  public void beforeClass() {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    driver = new ChromeDriver();
    page = new CheckingLinksPage(driver);
    driver.get("http://demo.guru99.com/test/newtours/index.php");
  }

  @Test
  public void testName() {
    assertTrue(page.checkingPageLinks(), "There are broken links");
  }

  @AfterClass
  public void afterClass() {
    driver.close();
  }
}
