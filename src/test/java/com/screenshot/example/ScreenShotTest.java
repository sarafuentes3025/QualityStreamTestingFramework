package com.screenshot.example;

import com.checkingLinks.test.CheckingLinksPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

@ExtendWith(ScreenShotTest.ScreenshotIfFails.class)
public class ScreenShotTest {

  private static WebDriver driver;
  private static CheckingLinksPage page;

  @BeforeAll
  public static void setUp() {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    driver = new ChromeDriver();
  }

  @Test
  void googleSearchTest() {
    driver.get("https://www.google.com/");
    assertThat(driver.getTitle()).isEqualTo("Google!");
  }

  @Test
  public void testName() {
    page = new CheckingLinksPage(driver);
    driver.get("http://demo.guru99.com/test/newtours/index.php");
    assertTrue(page.checkingPageLinks(), "There are broken links");
  }

  @AfterAll
  public static void tearDown() {
    driver.quit();
  }

  static class ScreenshotIfFails implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable throwable) {
      var screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      var timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy_hh"));
      try {
        Files.copy(screenshotFile.toPath(),
            Paths.get("%s_error_en_%s.png".formatted(timestamp, context.getTestMethod())),
            StandardCopyOption.REPLACE_EXISTING);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
