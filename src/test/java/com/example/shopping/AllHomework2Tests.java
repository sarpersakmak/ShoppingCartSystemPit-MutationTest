package com.example.shopping;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Complete Test Suite for Homework 2
 * Runs all basis-path tests and table-based tests
 */
@Suite
@SelectClasses({ 
    OrderCalculatorBasisPathTest.class,
    ShoppingCartBasisPathTest.class,
    ProductReduceStockTableTest.class
})
public class AllHomework2Tests {
    // This class remains empty, used only as holder for annotations
}