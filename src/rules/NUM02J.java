/*
 * Author: Owen Fresia
 * The following demonstrates compliancy with Java rule "NUM02-J. Ensure that division and remainder operations do not result in divide-by-zero errors", from Carnegie Mellon's Software Engineering Institute.
 * https://wiki.sei.cmu.edu/confluence/display/java/NUM02-J.+Ensure+that+division+and+remainder+operations+do+not+result+in+divide-by-zero+errors.
 */

/**
 * Represents a class responsible for the calculation of interest on loan
 * amounts. `calculateInterest` ensures compliancy with NUM02-J by ensuring
 * there is no zero interest rate.
 */
public class NUM02J {
    /**
     * Calculates the interest on a loan amount based on the principal and the
     * interest rate.
     * 
     * @param principal    The principal loan amount.
     * @param interestRate The annual interest rate.
     * @return The calculated interest amount.
     * @throws IllegalArgumentException if the interest rate is zero.
     */
    public static double calculateInterest(double principal, double interestRate) {
        if (interestRate == 0) {
            throw new IllegalArgumentException("Interest rate cannot be zero");
        }

        return (principal * interestRate) / 100; // Calculate interest
    }

    /**
     * Main method to demonstrate interest calculation.
     * 
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        double principal = 5000.0;
        double interestRate = 0.05; // 5%

        try {
            double interest = calculateInterest(principal, interestRate);
            System.out.println("Interest amount: $" + interest);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}