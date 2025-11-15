package com.example.shopping;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Basis-Path Testing for ShoppingCart.addItem()
 * 
 * Cyclomatic Complexity: 4
 * Independent Paths: 4
 */
public class ShoppingCartBasisPathTest {

    // Path 1: A → B(Yes) → C → J
    // Null product throws exception
    @Test
    public void basisPath1_nullProduct_throwsException() {
        ShoppingCart cart = new ShoppingCart();
        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> cart.addItem(null, 5));
        assertEquals("Product cannot be null", exception.getMessage());
    }

    // Path 2: A → B(No) → D(Yes) → E → J
    // Invalid quantity (zero or negative) throws exception
    @Test
    public void basisPath2_zeroQuantity_throwsException() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product(1, "Book", 10.0, 5);
        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> cart.addItem(product, 0));
        assertEquals("Quantity must be positive", exception.getMessage());
    }

    // Additional test for negative quantity
    @Test
    public void basisPath2b_negativeQuantity_throwsException() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product(1, "Book", 10.0, 5);
        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> cart.addItem(product, -3));
        assertEquals("Quantity must be positive", exception.getMessage());
    }

    // Path 3: A → B(No) → D(No) → F(Yes) → G → J
    // Insufficient stock throws exception
    @Test
    public void basisPath3_insufficientStock_throwsException() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product(1, "Laptop", 1000.0, 2);
        Exception exception = assertThrows(IllegalArgumentException.class, 
            () -> cart.addItem(product, 5));
        assertTrue(exception.getMessage().contains("Insufficient stock"));
    }

    // Path 4: A → B(No) → D(No) → F(No) → H → I → J
    // Successful add updates cart and reduces stock
    @Test
    public void basisPath4_validAdd_updatesCartAndStock() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product(1, "Phone", 500.0, 10);
        
        cart.addItem(product, 3);
        
        // Verify stock reduced
        assertEquals(7, product.getStock());
        
        // Verify cart total
        assertEquals(1500.0, cart.getTotalPrice(), 0.001);
        
        // Verify cart not empty
        assertFalse(cart.isEmpty());
        
        // Verify quantity in cart
        assertEquals(3, cart.getItems().get(product));
    }
    
    // Edge case: adding all available stock
    @Test
    public void basisPath4b_addAllStock_stockBecomesZero() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product(1, "Mouse", 25.0, 5);
        
        cart.addItem(product, 5);
        
        assertEquals(0, product.getStock());
        assertEquals(125.0, cart.getTotalPrice(), 0.001);
    }
}