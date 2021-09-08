package com.checkingLinks.test;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CheckingLinksPage {

  private final WebDriver driver;

  public boolean checkingPageLinks() {
    List<WebElement> links = driver.findElements(By.tagName("a"));
    String url;
    List<String> brokenLinks = new ArrayList<>();
    List<String> okLinks = new ArrayList<>();

    HttpURLConnection httpConnection;
    int responseCode;

    for (WebElement link : links) {
      url = link.getAttribute("href");
      if (url == null || url.isEmpty()) {
        System.out.println(url + "url is not configured or it is empty");
        continue;
      }
      try {
        httpConnection = (HttpURLConnection) (new URL(url).openConnection());
        httpConnection.setRequestMethod("HEAD");
        httpConnection.connect();
        responseCode = httpConnection.getResponseCode();

        if (responseCode > 400) {
          System.out.println("ERROR BROKEN LINK: -- " + url);
          brokenLinks.add(url);
        } else {
          System.out.println("VALID LINK: -- " + url);
          okLinks.add(url);
        }

      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    System.out.println("Valid Links" + okLinks.size());
    System.out.println("Invalid Links" + brokenLinks.size());

    if (brokenLinks.size() > 0) {
      System.out.println("******* ERROR ------------- Broken Links");
      for (String brokenLink : brokenLinks) {
        System.out.println(brokenLink);
      }
      return false;
    } else
      return true;
  }
}
