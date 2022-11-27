package com.namnh.page;

import com.namnh.core.AbstractMain;
import com.namnh.elements.HomeElements;
import com.namnh.elements.LoginElements;

import io.testproject.sdk.drivers.web.SafariDriver;

public class LoginPage extends AbstractMain {

    public LoginPage(SafariDriver driver) {
        this.driver = driver;
    }

    public void createCookies() {
        navigateTo(LoginElements.login_url);
        inputTo(LoginElements.username_txt, "standard_user");
        inputTo(LoginElements.password_txt, "secret_sauce");
        clickOn(LoginElements.login_btn);
    }

    public void openPage() {
        navigateTo(LoginElements.login_url);
    }

    public void tryOpenHomePage() {
        navigateTo(HomeElements.home_url);
    }

    public void loginWith(String username, String password) {
        inputTo(LoginElements.username_txt, username);
        inputTo(LoginElements.password_txt, password);
        clickOn(LoginElements.login_btn);
    }
    
}
