package src.rules;
//Rule 2

/**
 * A class demonstrating nested classes and access to private members of the outer class.
 */
public class OBJ08 {
    private int privateField = 10;

    /**
     * Method demonstrating access to the private member of the outer class.
     * It creates an instance of the nested class and calls a method to access the private field.
     */
    public void outerMethod() {
        NestedClass nested = new NestedClass();
        nested.accessOuterField(); // This is allowed and expected
    }

    /**
     * Nested class that demonstrates access to the private member of the outer class.
     */
    public class NestedClass {
        /**
         * Method to access the private field of the outer class.
         * It prints the value of the private field of the outer class.
         */
        public void accessOuterField() {
            System.out.println("Accessing privateField from NestedClass: " + privateField);
        }
    }
    
    public static void main(String[] args) {
        OBJ08 obj = new OBJ08();
        obj.outerMethod();
    }
}

