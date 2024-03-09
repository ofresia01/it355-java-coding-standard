package src.recs;
/**
 * A class demonstrating the recommendation to use parentheses for the precedence of operations in Java.
 */
public class EXP53 {

    /**
     * Main method to demonstrate incorrect and correct usage of parentheses for operator precedence.
     *
     * @param args the command-line arguments (not used in this example)
     */
    public static void main(String[] args) {
        // Incorrect usage: Without parentheses
        int result1 = 10 + 5 * 2; // Expected result: 20 (5 * 2) + 10 = 20
        System.out.println("Result without parentheses: " + result1);

        // Correct usage: With parentheses
        int result2 = (10 + 5) * 2; // Expected result: 30 (10 + 5) * 2 = 30
        System.out.println("Result with parentheses: " + result2);
    }
}
