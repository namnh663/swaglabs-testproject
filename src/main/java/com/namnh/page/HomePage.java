package com.namnh.page;

import com.namnh.core.AbstractMain;
import com.namnh.elements.HomeElements;

import io.testproject.sdk.drivers.web.SafariDriver;

public class HomePage extends AbstractMain{
    
    public HomePage(SafariDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        navigateTo(HomeElements.home_url);
    }

    public void logout() {
        waitToVisible(HomeElements.menu);
        clickOn(HomeElements.menu);
        waitToClick(HomeElements.logout);
    }

    public void addSingleItem(String xpathExpresion) {
        clickOn(xpathExpresion);
    }

    public void deleteSingleItem(String xpathExpresion) {
        clickOn(xpathExpresion);
    }

    public void sortByNameAsc() {
        waitToVisible(HomeElements.sort_ico);
        selectItemByValue(HomeElements.sort_ico, "az");
    }

    public void sortByNameDesc() {
        waitToVisible(HomeElements.sort_ico);
        selectItemByValue(HomeElements.sort_ico, "za");
    }

    public void sortByPriceAsc() {
        waitToVisible(HomeElements.sort_ico);
        selectItemByValue(HomeElements.sort_ico, "lohi");
    }

}
