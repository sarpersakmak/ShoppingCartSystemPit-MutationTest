package com.example.shopping;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * ProductTest - Complete test suite from Homework 1
 * Contains 20+ test cases for Product class
 */
public class ProductTest {

    // --- Constructor validation tests ---

    @Test
    public void validProductCreation_shouldStoreFields() {
        Product p = new Product(1, "Lamp", 10.0, 5);
        assertNotNull(p);
        assertEquals("Lamp", p.getName());
        assertEquals(10.0, p.getPrice(), 0.001);
        assertEquals(5, p.getStock());
    }

    @Test
    public void nullName_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Product(1, null, 10, 5));
    }

    @Test
    public void emptyName_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Product(1, "   ", 10, 5));
    }

    @Test
    public void negativePrice_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Product(1, "TV", -5, 2));
    }

    @Test
    public void negativeStock_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Product(1, "TV", 5, -1));
    }

    // --- Stock operation tests ---

    @Test
    public void reduceStock_validAmount_decreasesStock() {
        Product p = new Product(1, "TV", 10, 5);
        p.reduceStock(3);
        assertEquals(2, p.getStock());
    }

    @Test
    public void reduceStock_moreThanAvailable_throws() {
        Product p = new Product(1, "TV", 10, 1);
        assertThrows(IllegalArgumentException.class, () -> p.reduceStock(2));
    }

    @Test
    public void reduceStock_negativeAmount_throws() {
        Product p = new Product(1, "TV", 10, 5);
        assertThrows(IllegalArgumentException.class, () -> p.reduceStock(-1));
    }

    @Test
    public void increaseStock_valid_increasesValue() {
        Product p = new Product(1, "Book", 5, 5);
        p.increaseStock(3);
        assertEquals(8, p.getStock());
    }

    @Test
    public void increaseStock_negative_throws() {
        Product p = new Product(1, "Book", 5, 5);
        assertThrows(IllegalArgumentException.class, () -> p.increaseStock(-2));
    }

    // --- Price setter and validations ---

    @Test
    public void setPrice_valid_updatesPrice() {
        Product p = new Product(1, "Pen", 1.0, 10);
        p.setPrice(2.5);
        assertEquals(2.5, p.getPrice(), 0.001);
    }

    @Test
    public void setPrice_negative_throws() {
        Product p = new Product(1, "Pen", 1.0, 10);
        assertThrows(IllegalArgumentException.class, () -> p.setPrice(-3));
    }

    // --- Miscellaneous / boundary tests ---

    @Test
    public void toString_notNull() {
        Product p = new Product(1, "Phone", 100, 2);
        assertNotNull(p.toString());
    }

    @Test
    public void reduceToZero_stockBecomesZero() {
        Product p = new Product(1, "Mouse", 20, 2);
        p.reduceStock(2);
        assertEquals(0, p.getStock());
    }

    @Test
    public void zeroStock_creationAllowed() {
        Product p = new Product(1, "Game", 50, 0);
        assertEquals(0, p.getStock());
    }

    @Test
    public void zeroPrice_creationAllowed() {
        Product p = new Product(1, "Gift", 0, 5);
        assertEquals(0, p.getPrice(), 0.001);
    }

    @Test
    public void getId_returnsCorrectValue() {
        Product p = new Product(99, "X", 1.0, 1);
        assertEquals(99, p.getId());
    }

    @Test
    public void stockNeverNegativeAfterOperations() {
        Product p = new Product(1, "Cable", 2, 1);
        assertThrows(IllegalArgumentException.class, () -> p.reduceStock(2));
        assertTrue(p.getStock() >= 0);
    }

    @Test
    public void reduceFromZero_throws() {
        Product p = new Product(1, "Item", 1, 0);
        assertThrows(IllegalArgumentException.class, () -> p.reduceStock(1));
    }

    @Test
    public void validProduct_doesNotThrow() {
        assertDoesNotThrow(() -> new Product(1, "Valid", 1.0, 1));
    }

    @Test
    public void toString_containsProductName() {
        Product p = new Product(1, "Lamp", 5.0, 2);
        assertTrue(p.toString().contains("Lamp"));
    }

    @Test
    public void increaseStock_zeroLeavesStockUnchanged() {
        Product product = new Product(1, "Book", 5, 5);

        product.increaseStock(0);

        assertEquals(5, product.getStock());
    }

    @Test
    public void validationMessages_areDescriptive() {
        IllegalArgumentException nameError = assertThrows(
                IllegalArgumentException.class,
                () -> new Product(1, " ", 5, 1));

        IllegalArgumentException priceError = assertThrows(
                IllegalArgumentException.class,
                () -> new Product(1, "Book", -1, 1));

        assertAll(
                () -> assertEquals("Product name cannot be empty", nameError.getMessage()),
                () -> assertEquals("Price cannot be negative", priceError.getMessage()));
    }
}
