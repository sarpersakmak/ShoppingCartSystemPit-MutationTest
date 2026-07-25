package com.example.shopping;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Basis-Path Testing for OrderCalculator.calculate()
 *
 * Cyclomatic Complexity: 4
 * Independent Paths: 4
 */
public class OrderCalculatorBasisPathTest {

    // Path 1: A → B(Yes) → C → J
    // Negative subtotal throws exception
    @Test
    public void basisPath1_negativeSubtotal_throwsException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> OrderCalculator.calculate(-10, 0, 0));
        assertEquals("Subtotal cannot be negative", exception.getMessage());
    }

    // Path 2: A → B(No) → D(Yes) → E → J
    // Invalid discount (< 0) throws exception
    @Test
    public void basisPath2_invalidDiscountNegative_throwsException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> OrderCalculator.calculate(100, -5, 10));
        assertEquals("Invalid discount percent", exception.getMessage());
    }

    // Path 3: A → B(No) → D(No) → F(Yes) → G → J
    // Invalid tax (> 100) throws exception
    @Test
    public void basisPath3_invalidTaxOver100_throwsException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> OrderCalculator.calculate(100, 10, 150));
        assertEquals("Invalid tax percent", exception.getMessage());
    }

    // Path 4: A → B(No) → D(No) → F(No) → H → I → J
    // Valid calculation: 100 with 10% discount and 10% tax = 99
    @Test
    public void basisPath4_validInputs_calculatesCorrectly() {
        double result = OrderCalculator.calculate(100, 10, 10);
        assertEquals(99.0, result, 0.001);
    }

    // Additional basis path test: discount at upper boundary
    @Test
    public void basisPath_validInputs_discountAt100_returnsZero() {
        double result = OrderCalculator.calculate(100, 100, 0);
        assertEquals(0.0, result, 0.001);
    }

    // Additional basis path test: tax at upper boundary
    @Test
    public void basisPath_validInputs_taxAt100_doublesPrice() {
        double result = OrderCalculator.calculate(100, 0, 100);
        assertEquals(200.0, result, 0.001);
    }
}
