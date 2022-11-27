package com.namnh.suite;

import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.namnh.elements.HomeElements;
import com.namnh.extend.AbstractTest;
import com.namnh.page.HomePage;

@Listeners(io.testproject.sdk.internal.reporting.extensions.testng.ExceptionsReporter.class)
public class Sort extends AbstractTest {
    
    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        launchSafariBrowser();
    }

    @BeforeMethod
    public void beforeMethod() {
        setCookies(Cookies.cookies);
        homePage = new HomePage(driver);
    }

    @Test(priority = 1, testName = "Sort ascending & descending")
    public void sorting() {
        homePage.openPage();
        verifySortedListAscStr(HomeElements.item_name_list);
        homePage.sortByNameDesc();
        verifySortedListDescStr(HomeElements.item_name_list);
        homePage.sortByPriceAsc();
        verifySortedListAscFlt(HomeElements.item_price_list);
    }

    @Test(priority = 2, testName = "Remove products after adding")
    public void removeItem() {
        homePage.openPage();
        homePage.addSingleItem(HomeElements.add_sauce_labs_backpack_to_cart_btn);
        verifyText(HomeElements.item_number, "1");
        homePage.addSingleItem(HomeElements.add_sauce_labs_bike_light_to_cart_btn);
        verifyText(HomeElements.item_number, "2");
        homePage.deleteSingleItem(HomeElements.delete_sauce_labs_backpack_to_cart_btn);
        verifyText(HomeElements.item_number, "1");
    }

    @AfterClass
    public void after() {
        exitBrowser();
    }
    
}
