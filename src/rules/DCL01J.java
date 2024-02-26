public class DCL01J {
    private static final DCL01J rule = new DCL01J();

    /*
     * Noncompliant Code Example (Class Name).
     * This noncompliant code example implements a class that reuses the name of the
     * class java.util.Vector.
     * It attempts to introduce a different condition for the isEmpty() method for
     * interfacing with native legacy code by overriding the corresponding method in
     * java.util.Vector.
     * Unexpected behavior can arise if a maintainer confuses the isEmpty() method
     * with the java.util.Vector.isEmpty() method.
     */
    class Vector {
        private int val = 1;

        public boolean isEmpty() {
            if (val == 1) { // Compares with 1 instead of 0
                return true;
            } else {
                return false;
            }
        }
        // Other functionality is same as java.util.Vector
    }

    // import java.util.Vector; omitted
    public class VectorUser {
        public static void main(String[] args) {
            Vector v = rule.new Vector();
            if (v.isEmpty()) {
                System.out.println("Vector is empty");
            }
        }
    }

    /*
     * Compliant Solution (Class Name)
     * This compliant solution uses a different name for the class, preventing any
     * potential shadowing of the class from the Java Standard Library:
     */
    class MyVector {
        // Other code
    }
}