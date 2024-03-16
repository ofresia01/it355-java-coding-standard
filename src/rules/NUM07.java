/*
 * Author: Owen Fresia
 * The following demonstrates compliancy with Java rule "NUM07-J. Do not attempt comparisons with NaN", from Carnegie Mellon's Software Engineering Institute.
 * https://wiki.sei.cmu.edu/confluence/display/java/NUM07-J.+Do+not+attempt+comparisons+with+NaN.
 */

/**
 * The following class demonstrates compliancy with NUM07-J by correctly using
 * `Double.isNaN()` to check for NaN values, ensuring proper handling of
 * floating-point calculations.
 */
public class NUM07 {
    /**
     * Main method to demonstrate NaN comparison.
     *
     * @param args Command-line arguments (not used here).
     */
    public static void main(String[] args) {
        double x = 0.0;

        // Compliant Solution
        // This compliant solution uses the method Double.isNaN() to check whether the
        // expression corresponds to a NaN value.
        double resultCompliant = Math.cos(1 / x); // Returns NaN when input is infinity
        if (Double.isNaN(resultCompliant)) {
            System.out.println("Compliant: result is NaN");
        }
    }
}