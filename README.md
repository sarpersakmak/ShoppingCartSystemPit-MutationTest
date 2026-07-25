# Shopping Cart Mutation Testing

A compact Java 11 project that demonstrates how different white-box testing
techniques can be applied to a small shopping-cart domain. The production code
contains products, cart operations, and an order-total calculator. The test
suite combines conventional unit tests with basis-path testing, decision-table
testing, JaCoCo coverage analysis, and PIT mutation testing.

## Project Goals

This project is designed to show that high line coverage alone does not
guarantee strong tests. Mutation testing deliberately changes small parts of
the production code and checks whether the existing tests detect those
changes.

The project demonstrates:

- JUnit 5 unit and parameterized tests
- Boundary-value and negative-case testing
- Basis-path testing
- Decision-table testing
- JaCoCo line and branch coverage
- PIT mutation testing
- An optional JUnit Platform test suite

## Domain Model

### `Product`

Stores a product identifier, name, unit price, and available stock.

Main behaviors:

- Validates product data during construction
- Updates the unit price
- Reduces stock when an item is reserved
- Increases stock when an item is returned

### `ShoppingCart`

Stores products and their reserved quantities.

Main behaviors:

- Adds a product after validating quantity and stock
- Removes some or all of a product
- Returns removed quantities to stock
- Calculates the current subtotal
- Exposes cart contents through an unmodifiable view
- Clears the cart and restores all reserved stock

### `OrderCalculator`

Applies a discount and then tax:

```text
discountedSubtotal = subtotal × (1 - discountPercent / 100)
finalTotal         = discountedSubtotal × (1 + taxPercent / 100)
```

The subtotal must be non-negative. Discount and tax percentages must remain
between `0` and `100`, inclusive.

## Testing Strategy

| Test class | Purpose |
| --- | --- |
| `ProductTest` | Constructor, price, stock, boundary, and error behavior |
| `ProductReduceStockTableTest` | Decision-table cases for stock reduction |
| `ShoppingCartTest` | Cart operations, stock restoration, totals, and collection safety |
| `ShoppingCartBasisPathTest` | Independent paths through `addItem` |
| `OrderCalculatorTest` | Calculation, validation, boundaries, and parameterized inputs |
| `OrderCalculatorBasisPathTest` | Independent paths through `calculate` |
| `AllShoppingTestsSuite` | Optional suite containing every test class |

The manual mutant examples from the original assignment are documented in
[`docs/MANUAL_MUTANTS.md`](docs/MANUAL_MUTANTS.md). Automated mutation analysis
is performed by PIT.

## Requirements

- Java Development Kit 11 or newer
- Apache Maven 3.8 or newer

Confirm the tools are available:

```bash
java -version
mvn -version
```

## Running the Tests

Run every test class discovered by Maven Surefire:

```bash
mvn clean test
```

Run the optional suite explicitly:

```bash
mvn -Dtest=AllShoppingTestsSuite test
```

Run one test class:

```bash
mvn -Dtest=OrderCalculatorTest test
```

Run one test method:

```bash
mvn -Dtest=OrderCalculatorTest#parameterizedTotals test
```

## Code Coverage with JaCoCo

JaCoCo is attached automatically during the Maven test phase:

```bash
mvn clean test
```

Open the generated HTML report:

```text
target/site/jacoco/index.html
```

Useful values in the report include:

- Instruction coverage
- Branch coverage
- Line coverage
- Method coverage
- Class coverage

Coverage indicates which code was executed. It does not prove that the tests
would detect incorrect behavior, which is why PIT is also included.

## Mutation Testing with PIT

Run PIT mutation analysis:

```bash
mvn clean test-compile pitest:mutationCoverage
```

The generated HTML report is written to:

```text
target/pit-reports/index.html
```

The project mutates these production classes:

```text
com.example.shopping.OrderCalculator
com.example.shopping.Product
com.example.shopping.ShoppingCart
```

PIT runs test classes matching:

```text
com.example.shopping.*Test
```

The Maven configuration uses the default PIT mutators and requires at least
`80%` test coverage and `80%` mutation coverage.

### Interpreting PIT Results

A **killed mutant** changed the program in a way that caused at least one test
to fail. This indicates that the tests detected the introduced defect.

A **surviving mutant** changed the program but all tests still passed. Common
reasons include:

- A missing test case
- An assertion that is too weak
- Equivalent behavior for the tested inputs
- An equivalent mutant that cannot be distinguished from the original code

A **no-coverage mutant** was created in code that no test executed.

A **timed-out mutant** caused the test process to exceed the configured limit.

The mutation score is generally calculated as:

```text
killed mutants / testable mutants × 100
```

Equivalent mutants should be reviewed manually because they can lower the
reported score even when no useful test can kill them.

## Project Structure

```text
.
├── docs
│   └── MANUAL_MUTANTS.md
├── src
│   ├── main
│   │   └── java/com/example/shopping
│   │       ├── OrderCalculator.java
│   │       ├── Product.java
│   │       └── ShoppingCart.java
│   └── test
│       └── java/com/example/shopping
│           ├── AllShoppingTestsSuite.java
│           ├── OrderCalculatorBasisPathTest.java
│           ├── OrderCalculatorTest.java
│           ├── ProductReduceStockTableTest.java
│           ├── ProductTest.java
│           ├── ShoppingCartBasisPathTest.java
│           └── ShoppingCartTest.java
├── .gitignore
├── pom.xml
├── README.md
└── REFACTORING_NOTES.md
```

## Important Design Notes

### Product identity

`ShoppingCart` uses `Product` objects as map keys. `Product` intentionally does
not override `equals` or `hashCode`, so two separate product objects are treated
as different cart entries even when they have the same identifier.

### Monetary values

The assignment uses `double` to keep the calculation examples simple. A
production payment system should normally use `BigDecimal` or a dedicated
money type with an explicit rounding policy.

### Stock reservation

Adding an item immediately decreases product stock. Removing or clearing an
item restores stock. The project is an in-memory educational example and does
not provide transaction management or concurrency control.

## Troubleshooting

### `mvn` is not recognized

Install Apache Maven and ensure its `bin` directory is included in the system
`PATH`.

### PIT reports no tests or no mutations

Run the following command first:

```bash
mvn clean test
```

Then verify that test names end in `Test` and production classes remain under
`com.example.shopping`.

### Mutation threshold causes the build to fail

Open the PIT report and inspect surviving mutants. Add meaningful assertions
for missing behaviors rather than lowering the threshold immediately.

### Reports are missing

Generated reports are created under `target`, which is ignored by Git. Run the
corresponding Maven command again to regenerate them.

## Possible Future Improvements

- Replace `double` with a decimal money representation
- Add immutable cart-line objects
- Introduce product equality based on an explicit domain rule
- Add concurrency-safe stock reservation
- Add property-based tests
- Add continuous integration for unit, coverage, and mutation tests
- Publish JaCoCo and PIT reports as CI artifacts
