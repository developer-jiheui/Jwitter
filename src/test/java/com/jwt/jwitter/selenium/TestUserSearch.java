package com.jwt.jwitter.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;



//public class TestUserSearch {
//}
//
//
//public class TagTestCreateAndClickWithLoginTestTest {
//    private WebDriver driver;
//    private Map<String, Object> vars;
//    JavascriptExecutor js;
//    @Before
//    public void setUp() {
//        System.setProperty("webdriver.chrome.driver",
//                "/Users/AiNo/Downloads/chromedriver");
//        driver = new ChromeDriver();
//        ChromeOptions chromeOptions = new ChromeOptions();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//        js = (JavascriptExecutor) driver;
//        vars = new HashMap<String, Object>();
//    }
//    @After
//    public void tearDown() {
//        driver.quit();
//    }
//    @Test
//    public void tagTestCreateAndClickWithLoginTest() {
//        driver.get("http://localhost:3000/");
//        driver.manage().window().setSize(new Dimension(1440, 829));
//        driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/input[1]")).click();
////        driver.element.click();
//        driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/input[1]")).sendKeys("ainomc+11@gmail.com");
//        driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/input[2]")).click();
//        driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/input[2]")).sendKeys("123456");
////        driver.findElement(By.cssSelector("input:nth-child(1)")).click();
//        driver.findElement(By.cssSelector(".login_sign")).click();
//        driver.findElement(By.cssSelector(".login_sign")).click();
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
////        driver.wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]")));
//        driver.findElement(By.cssSelector(".MuiButtonBase-root:nth-child(3) > .MuiButton-label")).click();
//        driver.findElement(By.cssSelector(".tweetInput")).click();
//        driver.findElement(By.id("tweet_box")).click();
//        driver.findElement(By.id("tweet_box")).sendKeys("This is a tweet for test #tags");
//        driver.findElement(By.cssSelector(".MuiButton-text > .MuiButton-label")).click();
//        driver.findElement(By.cssSelector(".sidebarLink:nth-child(3) > .sidebarOption")).click();
//        driver.findElement(By.cssSelector(".MuiButtonBase-root:nth-child(3) > .MuiTab-wrapper")).click();
//        driver.findElement(By.cssSelector(".embed_input:nth-child(2) > input")).click();
//        driver.findElement(By.cssSelector(".embed_input:nth-child(2) > input")).sendKeys("tag");
//        driver.findElement(By.cssSelector(".embed_input:nth-child(2) > input")).sendKeys(Keys.ENTER);
//        {
//            List<WebElement> elements = driver.findElements(By.cssSelector(".post:nth-child(6) .post_headerDescription"));
//            assert(elements.size() > 0);
//        }
//    }
//}

public class TestUserSearch {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "/Users/AiNo/Downloads/chromedriver");

        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void TestUserSearch() {
        driver.get("http://localhost:3000/");
        driver.manage().window().setSize(new Dimension(1440, 829));
        driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/input[1]")).click();
//        driver.element.click();
        driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/input[1]")).sendKeys("ainomc+11@gmail.com");
        driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/input[2]")).click();
        driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/input[2]")).sendKeys("123456");
//        driver.findElement(By.cssSelector("input:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".login_sign")).click();
        driver.findElement(By.cssSelector(".login_sign")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(".MuiButtonBase-root:nth-child(3) > .MuiButton-label")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".sidebarLink:nth-child(3) h2"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/a[2]/div/h2")).click();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/div/div/div/button[2]")).click();
        driver.findElement(By.cssSelector(".embed_input:nth-child(2) > input")).sendKeys("aino");
        driver.findElement(By.cssSelector(".embed_input:nth-child(2) > input")).sendKeys(Keys.ENTER);
        {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div[2]/div[5]/h1/div[1]/div[2]/div/div/h3")));
        }
    }

}