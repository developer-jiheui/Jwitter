package com.jwt.jwitter.selenium;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

final class TestDisableUser {

    @Test
    void test() throws JsonProcessingException {
        System.setProperty("webdriver.gecko.driver", "/home/strogiyotec/Downloads/geckodriver");
        final WebDriver driver = new FirefoxDriver(); //Creating an object of FirefoxDriver
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://localhost:3000/");
        this.login(driver);
        this.adminPage(driver);
        final Map<String, Object> disableUser = this.disableUser(driver);
        if ((Boolean) disableUser.get("enabled")) {
            this.checkEnabled(disableUser.get("email").toString(), true, driver);
        } else {
            this.checkEnabled(disableUser.get("email").toString(), false, driver);
        }
        driver.quit();
    }

    private void checkEnabled(final String email, final boolean enabled, final WebDriver driver) throws JsonProcessingException {
        String url = "http://localhost:8080/api/admin/users/enabled?email={email}";
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
            email
        );
        final Map map = new ObjectMapper().readValue(response.getBody(), Map.class);
        Assertions.assertEquals(map.get("enabled"), enabled);
    }

    private String jwt(final WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return window.localStorage.getItem('jwt');");
    }

    private Map<String, Object> disableUser(final WebDriver driver) {
        final String email = driver.findElement(By.xpath("/html/body/div/div/div/div/table[1]/tbody/tr[1]/td[1]")).getText();
        final WebElement disableBtn = driver.findElement(By.xpath("/html/body/div/div/div/div/table[1]/tbody/tr[1]/td[3]/div/button[1]"));
        if (disableBtn.getAttribute("disabled") != null) {
            final WebElement enableBtn = driver.findElement(By.xpath("/html/body/div/div/div/div/table[1]/tbody/tr[1]/td[3]/div/button[2]"));
            enableBtn.click();
            return Map.of("email", email, "enabled", true);
        } else {
            disableBtn.click();
            return Map.of("email", email, "enabled", false);
        }
    }

    private void adminPage(final WebDriver driver) {
        for (final String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    private void login(final WebDriver driver) {
        final WebElement element = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/input[1]"));
        element.click();
        element.sendKeys("admin@gmail.com");
        final WebElement password = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/input[2]"));
        password.click();
        password.sendKeys("12345678");
        driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div/button[1]")).click();
    }
}
