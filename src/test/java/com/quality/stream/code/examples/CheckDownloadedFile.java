package com.quality.stream.code.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;

public class CheckDownloadedFile {

  private WebDriver driver;

  @BeforeClass
  public void beforeClass() {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    driver = new ChromeDriver();
    driver.get("https://the-internet.herokuapp.com/download");
  }

  @Test
  public void checkDownloadedFile() throws IOException {

    String link = driver.findElement(By.cssSelector("#content > div > a:nth-child(4)")).getAttribute("href");

    HttpURLConnection httpURLConnection = (HttpURLConnection) (new URL(link).openConnection());
    httpURLConnection.setRequestMethod("HEAD");
    httpURLConnection.connect();

    String contentType = httpURLConnection.getContentType();
    int contentLength = httpURLConnection.getContentLength();

    System.out.println("Content Type: " + contentType);
    System.out.println("Content Length: " + contentLength);

    assertEquals(contentType, "application/octet-stream");
    assertNotEquals(contentLength, 0);
  }

  @AfterClass
  public void afterClass() {

  }
}
