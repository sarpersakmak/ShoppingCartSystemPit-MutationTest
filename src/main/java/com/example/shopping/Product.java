package com.example.shopping;

/**
 * Represents a product that can be added to a shopping cart.
 *
 * <p>A product has an immutable identifier and name, while its price and stock
 * can change through validated operations.</p>
 */
public final class Product {

    private final int id;
    private final String name;
    private double price;
    private int stock;

    /**
     * Creates a product.
     *
     * @param id unique product identifier
     * @param name non-blank product name
     * @param price non-negative unit price
     * @param stock non-negative initial stock
     * @throws IllegalArgumentException if any value is invalid
     */
    public Product(int id, String name, double price, int stock) {
        if (name == null) {
            throw new IllegalArgumentException("Product name cannot be null");
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    /**
     * Updates the product's unit price.
     *
     * @param price non-negative unit price
     */
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        this.price = price;
    }

    /**
     * Reduces the available stock.
     *
     * @param amount number of units to reserve
     * @throws IllegalArgumentException if the amount is negative or exceeds stock
     */
    public void reduceStock(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to reduce cannot be negative");
        }
        if (amount > stock) {
            throw new IllegalArgumentException("Insufficient stock");
        }

        stock -= amount;
    }

    /**
     * Returns units to the available stock.
     *
     * @param amount number of units to return
     * @throws IllegalArgumentException if the amount is negative
     */
    public void increaseStock(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to increase cannot be negative");
        }

        stock += amount;
    }

    @Override
    public String toString() {
        return "Product{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", price=" + price
                + ", stock=" + stock
                + '}';
    }
}
