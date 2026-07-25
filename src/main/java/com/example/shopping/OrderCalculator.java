package com.example.shopping;

/**
 * Calculates a final order amount after discount and tax are applied.
 */
public final class OrderCalculator {

    private OrderCalculator() {
        // Utility class: instances are not needed.
    }

    /**
     * Calculates the final total using the following order:
     *
     * <pre>
     * discountedSubtotal = subtotal * (1 - discountPercent / 100)
     * finalTotal = discountedSubtotal * (1 + taxPercent / 100)
     * </pre>
     *
     * @param subtotal non-negative subtotal
     * @param discountPercent discount percentage from 0 to 100
     * @param taxPercent tax percentage from 0 to 100
     * @return discounted and taxed total
     * @throws IllegalArgumentException if an input is outside its valid range
     */
    public static double calculate(
            double subtotal,
            double discountPercent,
            double taxPercent) {

        if (subtotal < 0) {
            throw new IllegalArgumentException("Subtotal cannot be negative");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Invalid discount percent");
        }
        if (taxPercent < 0 || taxPercent > 100) {
            throw new IllegalArgumentException("Invalid tax percent");
        }

        double discountedSubtotal =
                subtotal * (1 - discountPercent / 100.0);

        return discountedSubtotal * (1 + taxPercent / 100.0);
    }
}
