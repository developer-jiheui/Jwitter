package com.jwt.jwitter.selenium;
import com.jwt.jwitter.models.Comment;
import netscape.javascript.JSException;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.json.JsonException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestDeletePost {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/ialee/Downloads/chromedriver 2");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void testDeletePost() throws JsonProcessingException {
        driver.get("http://localhost:3000/");
        driver.manage().window().setSize(new Dimension(1080, 1920));
        driver.findElement(By.cssSelector("input:nth-child(1)")).click();
        driver.findElement(By.cssSelector("input:nth-child(1)")).sendKeys("ia@gmail.com");
        driver.findElement(By.cssSelector("input:nth-child(2)")).sendKeys("1234qwer");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div/button[1]/span[1]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElement(By.id("tweet_box")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".MuiButton-text > .MuiButton-label"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.id("tweet_box")).sendKeys("Delete Post test");
        driver.findElement(By.cssSelector(".MuiButton-text > .MuiButton-label")).click();
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);



        {
            WebElement element = driver.findElement(By.cssSelector(".post:nth-child(1) .MuiButtonBase-root .MuiSvgIcon-root"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector(".post:nth-child(1) .MuiButtonBase-root .MuiSvgIcon-root")).click();
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }

        driver.findElement(By.cssSelector(".MuiPaper-root:nth-child(3) .MuiButtonBase-root")).click();
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.MINUTES);

        checkPostDeleted("Delete Post test", driver);

    }


    private void checkPostDeleted(final String tweet, final WebDriver driver) throws JsonProcessingException {
        String url = "http://localhost:8080/api/auth/tweet-search/{searchWord}";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + this.jwt(driver));
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class,
                tweet
        );
        final List<Comment> tweets = new ObjectMapper().readValue(response.getBody(), List.class);
        Assertions.assertEquals(false, tweets.size()>0);
    }

    private String jwt(final WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return window.localStorage.getItem('jwt');");
    }


}
