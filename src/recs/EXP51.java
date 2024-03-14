

/**
 * A class demonstrating the recommendation to avoid performing assignments in conditional expressions.
 */
public class EXP51 {

    /**
     * Main method to demonstrate incorrect usage of assignment in a conditional expression.
     *
     * @param args the command-line arguments (not used in this example)
     */
    public static void main(String[] args) {
        // Incorrect usage: Assignment in conditional expression
        int x = 5;
        if (x == 10) { // This will result in a compilation error if 1 equals were to be used instead of double equals
            System.out.println("x is equal to 10");
        } else {
            System.out.println("x is not equal to 10");
        }
    }
}
