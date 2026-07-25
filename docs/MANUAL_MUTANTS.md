# Manual Mutation Examples

The original project included ten commented implementations of
`OrderCalculator.calculate`. They have been moved from the test source tree to
this document because they are educational examples rather than executable
tests.

Automated mutation testing should be performed with PIT:

```bash
mvn clean test-compile pitest:mutationCoverage
```

## Mutant Catalogue

| No. | Mutation | Expected detecting test |
| ---: | --- | --- |
| 1 | Change `subtotal < 0` to `subtotal <= 0` | `subtotal_zeroAllowed` |
| 2 | Change the lower discount check so valid positive discounts are rejected | Valid calculation tests |
| 3 | Change discount validation from logical OR to logical AND | Negative and over-100 discount tests |
| 4 | Add the discount instead of subtracting it | Valid total assertions |
| 5 | Subtract tax instead of adding it | Valid total assertions |
| 6 | Divide the discount percentage by `200` instead of `100` | Parameterized totals |
| 7 | Remove the negative-subtotal validation | `subtotal_negative_throws` |
| 8 | Negate the lower tax boundary condition | Valid calculation tests |
| 9 | Replace the calculated return value with `0` | Non-zero total assertions |
| 10 | Divide by the tax multiplier instead of multiplying | Valid total assertions |

## Example: Relational Boundary Mutation

Original:

```java
if (subtotal < 0) {
    throw new IllegalArgumentException("Subtotal cannot be negative");
}
```

Mutant:

```java
if (subtotal <= 0) {
    throw new IllegalArgumentException("Subtotal cannot be negative");
}
```

A test with a subtotal of exactly zero distinguishes these versions.

## Example: Logical Operator Mutation

Original:

```java
if (discountPercent < 0 || discountPercent > 100) {
    throw new IllegalArgumentException("Invalid discount percent");
}
```

Mutant:

```java
if (discountPercent < 0 && discountPercent > 100) {
    throw new IllegalArgumentException("Invalid discount percent");
}
```

Because a number cannot be both below zero and above one hundred, the mutant
accepts every discount value. Tests below zero and above one hundred are both
useful for detecting this defect.

## Example: Arithmetic Mutation

Original:

```java
double discountedSubtotal =
        subtotal * (1 - discountPercent / 100.0);
```

Mutant:

```java
double discountedSubtotal =
        subtotal * (1 + discountPercent / 100.0);
```

A test with a non-zero discount and an exact expected total kills this mutant.

## Why the Examples Are Not Java Test Classes

Keeping fully commented mutant implementations under `src/test/java` makes the
test tree harder to read and can suggest that the class is executed. PIT
generates and evaluates mutants automatically, so these manual examples are
better maintained as supporting documentation.
