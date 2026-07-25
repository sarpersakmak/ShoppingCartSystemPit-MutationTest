package com.example.shopping;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * ShoppingCartTest - Complete test suite from Homework 1
 */
public class ShoppingCartTest {

    @Test
    public void newCart_isInitiallyEmpty() {
        ShoppingCart cart = new ShoppingCart();
        assertTrue(cart.isEmpty());
    }

    @Test
    public void addItem_validProduct_reducesStockAndAdds() {
        Product p = new Product(1, "Book", 10, 5);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(p, 2);
        assertEquals(3, p.getStock());
        assertEquals(20, cart.getTotalPrice(), 0.001);
    }

    @Test
    public void addItem_nullProduct_throws() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, () -> cart.addItem(null, 1));
    }

    @Test
    public void addItem_zeroQuantity_throws() {
        Product p = new Product(1, "Pen", 1.0, 5);
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, () -> cart.addItem(p, 0));
    }

    @Test
    public void addItem_exceedStock_throws() {
        Product p = new Product(1, "Laptop", 1000, 1);
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, () -> cart.addItem(p, 2));
    }

    @Test
    public void removeItem_valid_returnsStock() {
        Product p = new Product(1, "Lamp", 5, 5);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(p, 2);
        cart.removeItem(p, 1);
        assertEquals(4, p.getStock());
    }

    @Test
    public void removeItem_allQuantity_removesFromCart() {
        Product p = new Product(1, "Chair", 50, 2);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(p, 2);
        cart.removeItem(p, 2);
        assertTrue(cart.isEmpty());
    }

    @Test
    public void removeItem_notInCart_throws() {
        ShoppingCart cart = new ShoppingCart();
        Product p = new Product(1, "Desk", 100, 5);
        assertThrows(IllegalArgumentException.class, () -> cart.removeItem(p, 1));
    }

    @Test
    public void removeItem_moreThanInCart_throws() {
        Product p = new Product(1, "Toy", 10, 5);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(p, 2);
        assertThrows(IllegalArgumentException.class, () -> cart.removeItem(p, 3));
    }

    @Test
    public void getTotalPrice_multipleProducts_correctSum() {
        Product a = new Product(1, "A", 10, 10);
        Product b = new Product(2, "B", 20, 10);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(a, 2);
        cart.addItem(b, 3);
        assertEquals(2 * 10 + 3 * 20, cart.getTotalPrice(), 0.001);
    }

    @Test
    public void clear_returnsStock() {
        Product p = new Product(1, "TV", 100, 3);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(p, 2);
        cart.clear();
        assertEquals(3, p.getStock());
        assertTrue(cart.isEmpty());
    }

    @Test
    public void floatingPointTotals_toleranceCheck() {
        Product p = new Product(1, "Cable", 0.3333, 10);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(p, 3);
        assertEquals(0.9999, cart.getTotalPrice(), 0.001);
    }

    @Test
    public void addSameProductTwice_accumulatesQuantities() {
        Product p = new Product(1, "Book", 10, 10);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(p, 2);
        cart.addItem(p, 3);
        assertEquals(5, cart.getItems().get(p));
    }

    @Test
    public void removeItem_nullProduct_throws() {
        ShoppingCart cart = new ShoppingCart();

        assertThrows(
                IllegalArgumentException.class,
                () -> cart.removeItem(null, 1));
    }

    @Test
    public void removeItem_nonPositiveQuantity_throwsWithoutChangingCart() {
        Product product = new Product(1, "Book", 10, 5);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product, 2);

        assertAll(
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> cart.removeItem(product, 0)),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> cart.removeItem(product, -1)),
                () -> assertEquals(2, cart.getItems().get(product)),
                () -> assertEquals(3, product.getStock()));
    }

    @Test
    public void getItems_isUnmodifiable() {
        Product product = new Product(1, "Book", 10, 5);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product, 1);

        assertThrows(
                UnsupportedOperationException.class,
                () -> cart.getItems().put(product, 2));
    }

    @Test
    public void clear_multipleProducts_returnsAllReservedStock() {
        Product book = new Product(1, "Book", 10, 5);
        Product pen = new Product(2, "Pen", 2, 8);
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(book, 2);
        cart.addItem(pen, 3);
        cart.clear();

        assertAll(
                () -> assertTrue(cart.isEmpty()),
                () -> assertEquals(5, book.getStock()),
                () -> assertEquals(8, pen.getStock()));
    }
}
