package com.quality.stream.code.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class Download_File {
  private WebDriver driver;
  private final String downloadFilePath = "/Users/sarafuentes/IdeaProjects/QualityStreamTestingFramework/src/main/resources";

  @BeforeClass
  public void beforeClass() {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

    Map<String, Object> chromePrefs = new HashMap<>();

//    Disable option that opens the window
//    chromePrefs.put("profile.default_content_settings.popups", 0);

    chromePrefs.put("download.default_directory", downloadFilePath);

    ChromeOptions options = new ChromeOptions();
    options.setExperimentalOption("prefs", chromePrefs);

    driver = new ChromeDriver(options);
  }

  @Test
  public void testName() throws InterruptedException {
    driver.get("https://the-internet.herokuapp.com/download");
    driver.findElement(By.cssSelector("#content > div > a:nth-child(4)")).click();
    Thread.sleep(2_000);

    File folder = new File(downloadFilePath);

    File[] listOfFiles = folder.listFiles();

    assert listOfFiles != null;
    assertTrue(listOfFiles.length > 0, "File not downloaded correctly");
  }

  @AfterClass
  public void afterClass() {
  }
}
