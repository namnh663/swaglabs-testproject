package com.namnh.suite;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.namnh.data.Account;
import com.namnh.elements.HomeElements;
import com.namnh.elements.LoginElements;
import com.namnh.extend.AbstractTest;
import com.namnh.page.HomePage;
import com.namnh.page.LoginPage;

@Listeners(io.testproject.sdk.internal.reporting.extensions.testng.ExceptionsReporter.class)
public class Login extends AbstractTest {

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        launchSafariBrowser();
        setCookies(Cookies.cookies);
    }

    @Test(priority = 1, testName = "User log out & try again with locked account")
    public void loginFailed() throws FileNotFoundException, IOException, ParseException {
        homePage = new HomePage(driver);
        homePage.openPage();
        verifyText(HomeElements.header_title, "Products");
        verifyUrl(HomeElements.home_url);
        homePage.logout();
        loginPage = new LoginPage(driver);
        verifyValueInTextbox(LoginElements.username_txt, "");
        verifyValueInTextbox(LoginElements.password_txt, "");
        verifyUrl(LoginElements.login_url);
        loginPage.loginWith(Account.Username.locked, Account.Password.for_all);
        verifyText(LoginElements.error_msg, Account.Message.locked_msg);
    }

    @Test(priority = 2, testName = "User has not entered password")
    public void notEnteredPassword() throws FileNotFoundException, IOException, ParseException {
        loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.loginWith(Account.Username.locked, "");
        verifyText(LoginElements.error_msg, Account.Message.password_empty_msg);
        loginPage.tryOpenHomePage();
        verifyText(LoginElements.error_msg, Account.Message.not_access_msg);
    }

    @AfterClass
    public void after() {
        exitBrowser();
    }

}
