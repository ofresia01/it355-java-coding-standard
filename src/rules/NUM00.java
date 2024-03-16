/*
 * Author: Owen Fresia
 * The following demonstrates compliancy with Java rule "NUM00-J. Detect or prevent integer overflow", from Carnegie Mellon's Software Engineering Institute.
 * https://wiki.sei.cmu.edu/confluence/display/java/NUM00-J.+Detect+or+prevent+integer+overflow.
 */
import java.math.BigInteger;

/**
 * Represents a class responsible for the management of banking account
 * balances. `updateBalance` takes the current balance, the amount to add, and
 * per NUM00-J, checks for integer overflow via `BigInteger`.
 */
public class NUM00 {
    /**
     * Updates the balance of an account by adding the specified amount.
     * 
     * @param currentBalance The current balance of the account.
     * @param amountToAdd    The amount to add to the balance.
     * @return The updated balance after adding the amount.
     * @throws ArithmeticException if an overflow occurs during the operation.
     */
    public static int updateBalance(int currentBalance, int amountToAdd) {
        BigInteger bigCurrentBalance = BigInteger.valueOf(currentBalance);
        BigInteger bigAmountToAdd = BigInteger.valueOf(amountToAdd);

        BigInteger result = bigCurrentBalance.add(bigAmountToAdd);

        // Check for overflow
        if (result.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0 ||
                result.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0) {
            throw new ArithmeticException("Integer overflow");
        }

        return result.intValue();
    }

    /**
     * Main method to demonstrate updating account balances.
     * 
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        int currentBalance = 1500;
        int amountToAdd = 700;

        try {
            int updatedBalance = updateBalance(currentBalance, amountToAdd);
            System.out.println("Updated balance: " + updatedBalance);
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}