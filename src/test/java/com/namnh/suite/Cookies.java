package com.namnh.suite;

import java.net.MalformedURLException;

import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeTest;

import com.namnh.extend.AbstractTest;
import com.namnh.page.LoginPage;

public class Cookies extends AbstractTest {

    public static Cookie cookies;

    @BeforeTest
    public void before() throws MalformedURLException {
        launchSafariBrowser();
        loginPage = new LoginPage(driver);
        loginPage.createCookies();
        cookies = getCookies();
        exitBrowser();
    }

}
