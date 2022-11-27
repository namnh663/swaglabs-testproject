package com.namnh.core;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.Gson;
import com.namnh.page.HomePage;
import com.namnh.page.LoginPage;

import io.testproject.sdk.DriverBuilder;
import io.testproject.sdk.drivers.ReportingDriver;
import io.testproject.sdk.drivers.web.SafariDriver;
import io.testproject.sdk.interfaces.testng.ExceptionsReporter;

public class AbstractMain implements ExceptionsReporter {

    public SafariDriver driver;
    public LoginPage loginPage;
    public HomePage homePage;

    static final String REMOTE_URL = "http://localhost:8585";
    static final String BASE_URL = "https://api.testproject.io/v2";
    static final String SDK_TOKEN = "yIOZaAjDZdjo50I8aPFTFvydqoOWk6_yTHUJJR1usPg1";
    static final String ACCESS_KEY = "fFZ75ZV0lo7M88vY6oYSm7Ffmi2mY6eCUgn74Bnl9Qg1";

    public void launchSafariBrowser() throws MalformedURLException {
        driver = new DriverBuilder<SafariDriver>(new SafariOptions())
                .withRemoteAddress(new URL(REMOTE_URL))
                .withToken(SDK_TOKEN)
                .withProjectName("Swag Labs")
                .build(SafariDriver.class);
    }

    public void exitBrowser() {
        driver.quit();
    }

    public void disableAutoReport() {
        driver.report().disableTestAutoReports(true);
        driver.report().disableCommandReports(true);
    }

    public String toJsonFromObject(Object obj) {
        return new Gson().toJson(obj);
    }

    public void saveAsJson(Object obj) {
        try (PrintWriter out = new PrintWriter(new FileWriter("cookies.json"))) {
            String jsonStr = toJsonFromObject(obj);
            out.write(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readJsonToString() throws IOException {
        return new String(Files.readAllBytes(Paths.get("cookies.json")));
    }

    public Cookie getCookies() {
        return driver.manage().getCookieNamed("session-username");
    }

    public void setCookies(Cookie cookie) {
        driver.manage().addCookie(cookie);
    }

    public void deleteCookies(Cookie cookie) {
        driver.manage().deleteCookie(cookie);
    }

    public static void print(Object object) {
        System.out.println(object);
    }

    public WebElement findByCssSelector(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector));
    }

    public WebElement findByXpathExpression(String xpathExpression) {
        return driver.findElement(By.xpath(xpathExpression));
    }

    public List<WebElement> findListByXpath(String xpathExpression) {
        return driver.findElements(By.xpath(xpathExpression));
    }

    public void waitToClickable(String xpathExpression) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(findByXpathExpression(xpathExpression)));
    }

    public void waitToClick(String xpathExpression) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(findByXpathExpression(xpathExpression))).click();
    }

    public void waitToVisible(String xpathExpression) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
    }

    public String waitToGetText(String xpathExpression) {
        return new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression))).getText();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void waitFor(long secs) {
        try {
            Thread.sleep(secs * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getTextOfElement(String xpathExpression) {
        waitToVisible(xpathExpression);
        return findByXpathExpression(xpathExpression).getText();
    }

    public String getValueOfElement(String xpathExpression) {
        return findByXpathExpression(xpathExpression).getAttribute("value");
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public void selectItemByValue(String xpathExpression, String value) {
        new Select(findByXpathExpression(xpathExpression)).selectByValue(value);
    }

    public void selectItemByIndex(String xpathExpression, int index) {
        new Select(findByXpathExpression(xpathExpression)).selectByIndex(index);
    }

    public void inputTo(String xpathExpression, String value) {
        findByXpathExpression(xpathExpression).clear();
        findByXpathExpression(xpathExpression).sendKeys(value);
    }

    public void clear(String xpathExpression) {
        findByXpathExpression(xpathExpression).clear();
    }

    public void clickOn(String xpathExpression) {
        findByXpathExpression(xpathExpression).click();
    }

    public void doubleClick(String xpathExpression) {
        new Actions(driver).doubleClick(findByXpathExpression(xpathExpression)).perform();
    }

    public boolean isDisplayed(String xpathExpression) {
        return findByXpathExpression(xpathExpression).isDisplayed();
    }

    public boolean isSortedByAscStr(String xpathExpression) {
        ArrayList<String> arrayList = new ArrayList<>();
        List<WebElement> elements = findListByXpath(xpathExpression);
        for (WebElement element : elements) {
            arrayList.add(element.getText());
        }
        ArrayList<String> sortedList = new ArrayList<>();
        for (String child : arrayList) {
            sortedList.add(child);
        }
        Collections.sort(sortedList);
        return sortedList.equals(arrayList);
    }

    public boolean isSortedByDescStr(String xpathExpression) {
        ArrayList<String> arrayList = new ArrayList<>();
        List<WebElement> elements = findListByXpath(xpathExpression);
        for (WebElement element : elements) {
            arrayList.add(element.getText());
        }
        ArrayList<String> sortedList = new ArrayList<>();
        for (String child : arrayList) {
            sortedList.add(child);
        }
        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        return sortedList.equals(arrayList);
    }

    public boolean isSortedByAscFlt(String xpathExpression) {
        ArrayList<Float> arrayList = new ArrayList<Float>();
        List<WebElement> elements = findListByXpath(xpathExpression);
        for (WebElement element : elements) {
            arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
        }
        ArrayList<Float> sortedList = new ArrayList<Float>();
        for (Float child : arrayList) {
            sortedList.add(child);
        }
        Collections.sort(sortedList);
        return sortedList.equals(arrayList);
    }

    public static void apiGetProject() throws MalformedURLException, IOException {
        String inputLine;
        StringBuffer response = new StringBuffer();

        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(BASE_URL + "/projects")
                .openConnection();
        httpURLConnection.setRequestProperty("Authorization", ACCESS_KEY);
        httpURLConnection.setRequestProperty("accept", "application/json");
        httpURLConnection.setRequestMethod("GET");
        if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            while ((inputLine = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))
                    .readLine()) != null) {
                response.append(inputLine);
            }
            new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())).close();
            print(response.toString());
        }
    }

    @Override
    public ReportingDriver getDriver() {
        return driver;
    }

}
