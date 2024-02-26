import java.util.Arrays;
import java.util.List;

public class DCL02J {
    private static final DCL02J rule = new DCL02J();

    public static void main(String[] args) {
        /*
         * Noncompliant Code Example
         * This noncompliant code example attempts to process a collection of integers
         * using an enhanced for loop. It further intends to modify one item in the
         * collection for processing:
         */
        List<Integer> list = Arrays.asList(new Integer[] { 13, 14, 15 });
        boolean first = true;

        System.out.println("Processing list...");
        for (Integer i : list) {
            if (first) {
                first = false;
                i = new Integer(99);
            }
            System.out.println(" New item: " + i);
            // Process i
        }

        System.out.println("Modified list?");
        for (Integer i : list) {
            System.out.println("List item: " + i);
        }

        /*
         * Compliant Solution
         * Declaring i to be final mitigates this problem by causing the compiler to
         * fail to permit i to be assigned a new value:
         */
        // ...
        for (final Integer i : list) {
            // ...
        }

        /*
         * Compliant Solution
         * This compliant solution processes the "modified" list but leaves the actual
         * list unchanged:
         */
        // ...
        for (final Integer i : list) {
            Integer item = i;
            if (first) {
                first = false;
                item = new Integer(99);
            }
            System.out.println(" New item: " + item);
            // Process item
        }
        // ...
    }
}