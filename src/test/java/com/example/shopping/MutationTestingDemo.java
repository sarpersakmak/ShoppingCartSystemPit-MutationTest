package com.example.shopping;

/**
 * This class contains the 10 manual mutants for OrderCalculator.calculate()
 * Each mutant is commented out and documented.
 * 
 * To test a mutant:
 * 1. Comment out the original OrderCalculator.calculate() method
 * 2. Uncomment one mutant method
 * 3. Run OrderCalculatorTest to see if the mutant is killed
 */
public class MutationTestingDemo {

    /* ORIGINAL METHOD (for reference)
    public static double calculate(double subtotal, double discountPercent, double taxPercent) {
        if (subtotal < 0)
            throw new IllegalArgumentException("Subtotal cannot be negative");
        if (discountPercent < 0 || discountPercent > 100)
            throw new IllegalArgumentException("Invalid discount percent");
        if (taxPercent < 0 || taxPercent > 100)
            throw new IllegalArgumentException("Invalid tax percent");

        double discounted = subtotal * (1 - discountPercent / 100.0);
        return discounted * (1 + taxPercent / 100.0);
    }
    */

    // MUTANT 1: Relational Operator Replacement (< to <=)
    // Status: KILLED by subtotal_zeroAllowed()
    /*
    public static double calculate(double subtotal, double discountPercent, double taxPercent) {
        if (subtotal <= 0)  // MUTATED: < changed to <=
            throw new IllegalArgumentException("Subtotal cannot be negative");
        if (discountPercent < 0 || discountPercent > 100)
            throw new IllegalArgumentException("Invalid discount percent");
        if (taxPercent < 0 || taxPercent > 100)
            throw new IllegalArgumentException("Invalid tax percent");

        double discounted = subtotal * (1 - discountPercent / 100.0);
        return discounted * (1 + taxPercent / 100.0);
    }
    */

    // MUTANT 2: Relational Operator Replacement (< to >)
    // Status: KILLED by orderCalculation_validValues_returnsExpectedTotal()
    /*
    public static double calculate(double subtotal, double discountPercent, double taxPercent) {
        if (subtotal < 0)
            throw new IllegalArgumentException("Subtotal cannot be negative");
        if (discountPercent > 0 || discountPercent > 100)  // MUTATED: first < changed to >
            throw new IllegalArgumentException("Invalid discount percent");
        if (taxPercent < 0 || taxPercent > 100)
            throw new IllegalArgumentException("Invalid tax percent");

        double discounted = subtotal * (1 - discountPercent / 100.0);
        return discounted * (1 + taxPercent / 100.0);
    }
    */

    // MUTANT 3: Logical Operator Replacement (|| to &&)
    // Status: KILLED by invalidDiscountRate_throws()
    /*
    public static double calculate(double subtotal, double discountPercent, double taxPercent) {
        if (subtotal < 0)
            throw new IllegalArgumentException("Subtotal cannot be negative");
        if (discountPercent < 0 && discountPercent > 100)  // MUTATED: || changed to &&
            throw new IllegalArgumentException("Invalid discount percent");
        if (taxPercent < 0 || taxPercent > 100)
            throw new IllegalArgumentException("Invalid tax percent");

        double discounted = subtotal * (1 - discountPercent / 100.0);
        return discounted * (1 + taxPercent / 100.0);
    }
    */

    // MUTANT 4: Arithmetic Operator Replacement (- to +)
    // Status: KILLED by orderCalculation_validValues_returnsExpectedTotal()
    /*
    public static double calculate(double subtotal, double discountPercent, double taxPercent) {
        if (subtotal < 0)
            throw new IllegalArgumentException("Subtotal cannot be negative");
        if (discountPercent < 0 || discountPercent > 100)
            throw new IllegalArgumentException("Invalid discount percent");
        if (taxPercent < 0 || taxPercent > 100)
            throw new IllegalArgumentException("Invalid tax percent");

        double discounted = subtotal * (1 + discountPercent / 100.0);  // MUTATED: - changed to +
        return discounted * (1 + taxPercent / 100.0);
    }
    */

    // MUTANT 5: Arithmetic Operator Replacement (+ to -)
    // Status: KILLED by orderCalculation_validValues_returnsExpectedTotal()
    /*
    public static double calculate(double subtotal, double discountPercent, double taxPercent) {
        if (subtotal < 0)
            throw new IllegalArgumentException("Subtotal cannot be negative");
        if (discountPercent < 0 || discountPercent > 100)
            throw new IllegalArgumentException("Invalid discount percent");
        if (taxPercent < 0 || taxPercent > 100)
            throw new IllegalArgumentException("Invalid tax percent");

        double discounted = subtotal * (1 - discountPercent / 100.0);
        return discounted * (1 - taxPercent / 100.0);  // MUTATED: + changed to -
    }
    */

    // MUTANT 6: Constant Replacement (100.0 to 200.0)
    // Status: KILLED by parameterizedTotals()
    /*
    public static double calculate(double subtotal, double discountPercent, double taxPercent) {
        if (subtotal < 0)
            throw new IllegalArgumentException("Subtotal cannot be negative");
        if (discountPercent < 0 || discountPercent > 100)
            throw new IllegalArgumentException("Invalid discount percent");
        if (taxPercent < 0 || taxPercent > 100)
            throw new IllegalArgumentException("Invalid tax percent");

        double discounted = subtotal * (1 - discountPercent / 200.0);  // MUTATED: 100.0 to 200.0
        return discounted * (1 + taxPercent / 100.0);
    }
    */

    // MUTANT 7: Remove Conditional (first if removed)
    // Status: KILLED by subtotal_negative_throws()
    /*
    public static double calculate(double subtotal, double discountPercent, double taxPercent) {
        // MUTATED: First if statement removed
        if (discountPercent < 0 || discountPercent > 100)
            throw new IllegalArgumentException("Invalid discount percent");
        if (taxPercent < 0 || taxPercent > 100)
            throw new IllegalArgumentException("Invalid tax percent");

        double discounted = subtotal * (1 - discountPercent / 100.0);
        return discounted * (1 + taxPercent / 100.0);
    }
    */

    // MUTANT 8: Negate Conditional (< to >=)
    // Status: KILLED by orderCalculation_validValues_returnsExpectedTotal()
    /*
    public static double calculate(double subtotal, double discountPercent, double taxPercent) {
        if (subtotal < 0)
            throw new IllegalArgumentException("Subtotal cannot be negative");
        if (discountPercent < 0 || discountPercent > 100)
            throw new IllegalArgumentException("Invalid discount percent");
        if (taxPercent >= 0 || taxPercent > 100)  // MUTATED: < changed to >=
            throw new IllegalArgumentException("Invalid tax percent");

        double discounted = subtotal * (1 - discountPercent / 100.0);
        return discounted * (1 + taxPercent / 100.0);
    }
    */

    // MUTANT 9: Return Value Replacement
    // Status: KILLED by all calculation tests
    /*
    public static double calculate(double subtotal, double discountPercent, double taxPercent) {
        if (subtotal < 0)
            throw new IllegalArgumentException("Subtotal cannot be negative");
        if (discountPercent < 0 || discountPercent > 100)
            throw new IllegalArgumentException("Invalid discount percent");
        if (taxPercent < 0 || taxPercent > 100)
            throw new IllegalArgumentException("Invalid tax percent");

        double discounted = subtotal * (1 - discountPercent / 100.0);
        return 0;  // MUTATED: Always return 0
    }
    */

    // MUTANT 10: Arithmetic Operator Replacement (* to /)
    // Status: KILLED by orderCalculation_validValues_returnsExpectedTotal()
    /*
    public static double calculate(double subtotal, double discountPercent, double taxPercent) {
        if (subtotal < 0)
            throw new IllegalArgumentException("Subtotal cannot be negative");
        if (discountPercent < 0 || discountPercent > 100)
            throw new IllegalArgumentException("Invalid discount percent");
        if (taxPercent < 0 || taxPercent > 100)
            throw new IllegalArgumentException("Invalid tax percent");

        double discounted = subtotal * (1 - discountPercent / 100.0);
        return discounted / (1 + taxPercent / 100.0);  // MUTATED: * changed to /
    }
    */
}