# Refactoring Notes

## Scope

The project remains a small educational Java application focused on white-box
and mutation testing. The public behavior and calculation order were preserved.

## Production Code

- Added consistent braces and formatting.
- Improved English Javadoc and parameter descriptions.
- Made domain and utility classes `final` where inheritance was unnecessary.
- Added a private constructor to `OrderCalculator`.
- Renamed local variables for readability.
- Corrected the `ShoppingCart.getItems` documentation: it returns an
  unmodifiable live view rather than a copied map.
- Preserved the original `double`-based API and validation rules to avoid
  changing the assignment's intended control-flow paths.

## Test Code

- Preserved the original unit, basis-path, and decision-table tests.
- Added missing upper/lower percentage boundary validation tests.
- Added cart removal validation tests.
- Added a test proving that the returned item map cannot be modified.
- Added a multi-product stock-restoration test.
- Replaced the misleading `AllHomework2Tests` class with the optional
  `AllShoppingTestsSuite`.
- Moved commented manual mutant implementations into
  `docs/MANUAL_MUTANTS.md`.

## Build Configuration

- Simplified JUnit dependencies using the JUnit Jupiter aggregate artifact.
- Centralized dependency and plugin versions in Maven properties.
- Configured Java compilation through the `release` option.
- Kept JaCoCo report generation attached to the test phase.
- Limited PIT targets to the three production classes.
- Prevented the optional suite from running alongside individual tests during
  the normal Maven test phase.
- Kept the original 80% coverage and mutation thresholds.

## Repository Cleanup

- Removed generated `target` contents.
- Removed Eclipse-specific `.classpath`, `.project`, and `.settings` files.
- Added a repository-focused `.gitignore`.
- Added a comprehensive English README.

## Verification Limitation

The source code was checked with the available Java compiler and local smoke
tests. Maven and its external dependencies were not available in the editing
environment, so the final JUnit, JaCoCo, and PIT commands should be run on a
machine with Maven installed.
