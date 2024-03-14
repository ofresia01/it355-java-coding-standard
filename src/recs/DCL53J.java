/*
 * Author: Owen Fresia
 * The following demonstrates compliancy with Java recommendation "DCL53-J. Minimize the scope of variables", from Carnegie Mellon's Software Engineering Institute.
 * https://wiki.sei.cmu.edu/confluence/display/java/DCL53-J.+Minimize+the+scope+of+variables.
 */

/**
 * The following class has method `compliantExample` that keeps the `total`
 * variable declaration within the method, minimizing its scope. It also a class
 * `FooCompliant` which declares teh variable `count` inside the method where
 * its used, minimizing its scope and complying with DCL53-J.
 */
public class DCL53J {
    /**
     * Compliant solution with minimized variable scope using a for loop.
     */
    public static void compliantExample() {
        int total = 0;
        for (int i = 0; i < 10; i++) {
            total += i;
        }
        System.out.println("Compliant Total: " + total);
    }

    /**
     * Compliant solution with minimized variable scope by declaring the variable
     * inside the method.
     */
    public static class FooCompliant {
        public void processAndDisplay() {
            int count = 0;
            for (int i = 0; i < 5; i++) {
                count += i;
            }
            System.out.println("Compliant Count: " + count);
        }
    }

    /**
     * Main method to demonstrate scope minimization.
     *
     * @param args Command-line arguments (not used here).
     */
    public static void main(String[] args) {
        // Demonstrating compliant solution
        compliantExample();

        // Demonstrating compliant solution by declaring variable inside the method
        FooCompliant fooCompliant = new FooCompliant();
        fooCompliant.processAndDisplay();
    }
}