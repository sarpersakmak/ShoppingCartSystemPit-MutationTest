package com.example.shopping;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Optional suite that runs every test class in this project.
 *
 * <p>Run it explicitly with:
 * {@code mvn -Dtest=AllShoppingTestsSuite test}</p>
 */
@Suite
@SelectClasses({
    OrderCalculatorTest.class,
    OrderCalculatorBasisPathTest.class,
    ProductTest.class,
    ProductReduceStockTableTest.class,
    ShoppingCartTest.class,
    ShoppingCartBasisPathTest.class
})
public class AllShoppingTestsSuite {
    // JUnit Platform uses this class only as an annotation holder.
}
