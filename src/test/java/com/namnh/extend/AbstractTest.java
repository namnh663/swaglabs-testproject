package com.namnh.extend;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.namnh.core.AbstractMain;

public class AbstractTest extends AbstractMain {
    
    public void verifyTitlePage(String expected) {
        assertEquals(getPageTitle(), expected);
    }

    public void verifyUrl(String expected) {
        assertEquals(getUrl(), expected);
    }

    public void verifyText(String xpathExpression, String expected) {
        assertEquals(getTextOfElement(xpathExpression), expected);
    }

    public void verifyValueInTextbox(String xpathExpression, String expected) {
        assertEquals(getValueOfElement(xpathExpression), expected);
    }

    public void verifyDisplayed(String xpathExpression) {
        assertTrue(isDisplayed(xpathExpression));
    }

    public void verifySortedListAscStr(String xpathExpression) {
        assertTrue(isSortedByAscStr(xpathExpression));
    }

    public void verifySortedListDescStr(String xpathExpression) {
        assertTrue(isSortedByDescStr(xpathExpression));
    }

    public void verifySortedListAscFlt(String xpathExpression) {
        assertTrue(isSortedByAscFlt(xpathExpression));
    }

}
