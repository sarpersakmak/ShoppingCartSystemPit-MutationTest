package com.example.shopping;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores products and their reserved quantities for a single shopping cart.
 *
 * <p>Adding an item reserves stock from the product. Removing or clearing an
 * item returns the corresponding quantity to the product.</p>
 */
public final class ShoppingCart {

    private final Map<Product, Integer> items = new HashMap<>();

    /**
     * Adds a positive quantity of a product to the cart.
     *
     * @param product product to add
     * @param quantity number of units to reserve
     * @throws IllegalArgumentException if the product or quantity is invalid,
     *                                  or if the product has insufficient stock
     */
    public void addItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (product.getStock() < quantity) {
            throw new IllegalArgumentException(
                    "Insufficient stock for " + product.getName());
        }

        // Reserve the stock before recording the cart quantity.
        product.reduceStock(quantity);
        items.merge(product, quantity, Integer::sum);
    }

    /**
     * Removes a positive quantity of a product from the cart.
     *
     * @param product product to remove
     * @param quantity number of units to remove
     * @throws IllegalArgumentException if the request is invalid
     */
    public void removeItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (!items.containsKey(product)) {
            throw new IllegalArgumentException("Product not found in cart");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        int currentQuantity = items.get(product);
        if (quantity > currentQuantity) {
            throw new IllegalArgumentException(
                    "Trying to remove more than present in cart");
        }

        product.increaseStock(quantity);

        if (quantity == currentQuantity) {
            items.remove(product);
        } else {
            items.put(product, currentQuantity - quantity);
        }
    }

    /**
     * Calculates the sum of unit price multiplied by quantity for every item.
     *
     * @return current cart subtotal
     */
    public double getTotalPrice() {
        double total = 0.0;

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }

        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Returns an unmodifiable live view of the cart contents.
     *
     * @return product-to-quantity mapping
     */
    public Map<Product, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    /**
     * Removes every item and returns all reserved quantities to stock.
     */
    public void clear() {
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().increaseStock(entry.getValue());
        }

        items.clear();
    }
}
