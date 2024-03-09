package src.rules;
import java.util.Arrays;

//Rule 1
public class EXP02 {
    /**
     * Main method to compare two arrays for equality.
     *
     * @param args the command-line arguments (not used in this example)
     */
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};

        // Comparing arrays using Arrays.equals() method
        boolean arraysEqual = Arrays.equals(array1, array2);

        // Output the result based on the comparison
        if (arraysEqual) {
            System.out.println("Arrays are equal.");
        } else {
            System.out.println("Arrays are not equal.");
        }
    }
}
