package com.jwt.jwitter.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TagTestCreateAndClickWithLoginTestTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "D:/software_engineer/chromedriver.exe");
        driver = new ChromeDriver();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void tagTestCreateAndClickWithLoginTest() {
        driver.get("http://localhost:3000/");
        driver.manage().window().setSize(new Dimension(1440, 829));
        driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/input[1]")).click();
//        driver.element.click();
        driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/input[1]")).sendKeys("anita@gmail.com");
        driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/input[2]")).click();
        driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/input[2]")).sendKeys("123456");
//        driver.findElement(By.cssSelector("input:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".login_sign")).click();
        driver.findElement(By.cssSelector(".login_sign")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        driver.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]")));
        driver.findElement(By.cssSelector(".MuiButtonBase-root:nth-child(3) > .MuiButton-label")).click();
        driver.findElement(By.cssSelector(".tweetInput")).click();
        driver.findElement(By.id("tweet_box")).click();
        driver.findElement(By.id("tweet_box")).sendKeys("This is a tweet for test #tags");
        driver.findElement(By.cssSelector(".MuiButton-text > .MuiButton-label")).click();
        driver.findElement(By.cssSelector(".sidebarLink:nth-child(3) > .sidebarOption")).click();
        driver.findElement(By.cssSelector(".MuiButtonBase-root:nth-child(3) > .MuiTab-wrapper")).click();
        driver.findElement(By.cssSelector(".embed_input:nth-child(2) > input")).click();
        driver.findElement(By.cssSelector(".embed_input:nth-child(2) > input")).sendKeys("tags");
        driver.findElement(By.cssSelector(".embed_input:nth-child(2) > input")).sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            List<WebElement> elements = driver.findElements(By.cssSelector(".post:nth-child(1) .post_headerDescription"));
            Assertions.assertTrue(elements.size() >0);
    }
}
