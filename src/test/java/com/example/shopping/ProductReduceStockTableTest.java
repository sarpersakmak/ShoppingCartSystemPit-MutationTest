package com.example.shopping;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Table-Based Testing for Product.reduceStock()
 * 
 * Decision Table with 8 test cases covering all condition combinations
 */
public class ProductReduceStockTableTest {

    // TC1: Negative amount with stock=5
    // Condition: amount < 0 = TRUE
    // Expected: Exception "Amount to reduce cannot be negative"
    @Test
    public void tableTest_TC1_negativeAmount_throwsException() {
        Product p = new Product(1, "Item", 10.0, 5);
        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> p.reduceStock(-1));
        assertEquals("Amount to reduce cannot be negative", exception.getMessage());
        assertEquals(5, p.getStock()); // Stock unchanged
    }

    // TC2: Amount exceeds stock (10 > 5)
    // Condition: amount < 0 = FALSE, amount > stock = TRUE
    // Expected: Exception "Insufficient stock"
    @Test
    public void tableTest_TC2_amountExceedsStock_throwsException() {
        Product p = new Product(1, "Item", 10.0, 5);
        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> p.reduceStock(10));
        assertEquals("Insufficient stock", exception.getMessage());
        assertEquals(5, p.getStock()); // Stock unchanged
    }

    // TC3: Valid reduction (5 - 3 = 2)
    // Condition: amount < 0 = FALSE, amount > stock = FALSE
    // Expected: Success, stock = 2
    @Test
    public void tableTest_TC3_validReduction_updatesStock() {
        Product p = new Product(1, "Item", 10.0, 5);
        p.reduceStock(3);
        assertEquals(2, p.getStock());
    }

    // TC4: Reduce to exactly zero (5 - 5 = 0)
    // Condition: amount < 0 = FALSE, amount > stock = FALSE (boundary)
    // Expected: Success, stock = 0
    @Test
    public void tableTest_TC4_reduceToZero_stockBecomesZero() {
        Product p = new Product(1, "Item", 10.0, 5);
        p.reduceStock(5);
        assertEquals(0, p.getStock());
    }

    // TC5: Reduce by zero (no change)
    // Condition: amount < 0 = FALSE, amount > stock = FALSE
    // Expected: Success, stock = 5 (unchanged)
    @Test
    public void tableTest_TC5_reduceByZero_stockUnchanged() {
        Product p = new Product(1, "Item", 10.0, 5);
        p.reduceStock(0);
        assertEquals(5, p.getStock());
    }

    // TC6: Try to reduce from zero stock
    // Condition: amount < 0 = FALSE, amount > stock = TRUE (1 > 0)
    // Expected: Exception "Insufficient stock"
    @Test
    public void tableTest_TC6_reduceFromZeroStock_throwsException() {
        Product p = new Product(1, "Item", 10.0, 0);
        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> p.reduceStock(1));
        assertEquals("Insufficient stock", exception.getMessage());
        assertEquals(0, p.getStock()); // Stock unchanged
    }

    // TC7: Valid reduction with larger stock (10 - 1 = 9)
    // Condition: amount < 0 = FALSE, amount > stock = FALSE
    // Expected: Success, stock = 9
    @Test
    public void tableTest_TC7_validReductionLargeStock_updatesStock() {
        Product p = new Product(1, "Item", 10.0, 10);
        p.reduceStock(1);
        assertEquals(9, p.getStock());
    }

    // TC8: Negative amount with zero stock
    // Condition: amount < 0 = TRUE
    // Expected: Exception "Amount to reduce cannot be negative"
    @Test
    public void tableTest_TC8_negativeAmountZeroStock_throwsException() {
        Product p = new Product(1, "Item", 10.0, 0);
        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> p.reduceStock(-5));
        assertEquals("Amount to reduce cannot be negative", exception.getMessage());
        assertEquals(0, p.getStock()); // Stock unchanged
    }
    
    // Bonus TC9: Boundary test - reduce by 1 from stock of 1
    @Test
    public void tableTest_TC9_reduceLastItem_stockBecomesZero() {
        Product p = new Product(1, "Item", 10.0, 1);
        p.reduceStock(1);
        assertEquals(0, p.getStock());
    }
    
    // Bonus TC10: Large numbers test
    @Test
    public void tableTest_TC10_largeNumbers_worksCorrectly() {
        Product p = new Product(1, "Item", 10.0, 1000);
        p.reduceStock(500);
        assertEquals(500, p.getStock());
    }
}