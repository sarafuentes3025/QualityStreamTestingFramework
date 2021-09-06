package com.uploadfile.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class UploadFileClass {

  private static WebDriver driver;

  @BeforeAll
  public static void setUp() {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    driver = new ChromeDriver();
  }

  @Test
  void checkFileUploaded() {
    File file = new File("/Users/sarafuentes/IdeaProjects/QualityStreamTestingFramework/sTest123.txt");
    String path = file.getAbsolutePath();

    driver.get("http://the-internet.herokuapp.com/upload");
    driver.findElement(By.id("file-upload")).sendKeys(path);
    driver.findElement(By.id("file-submit")).click();

    String text = driver.findElement(By.id("uploaded-files")).getText();
    assertThat(text).isEqualTo("sTest123.txt");
  }

  @AfterAll
  static void tearDown() {
    driver.quit();
  }
}
