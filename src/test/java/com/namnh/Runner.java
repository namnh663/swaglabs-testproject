package com.namnh;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class Runner {
    
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        List<String> suite = new ArrayList<String>();
        suite.add("testng.xml");
        testNG.setTestSuites(suite);
        testNG.run();
    }

}
