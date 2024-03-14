
/**
 * A class demonstrating the usage of the ParentClass and ChildClass.
 */
public class MET04 {
    static MET04 rule = new MET04();
    public class ParentClass {
        public void restrictedMethod() {
        }
    }

    /**
     * A class representing a ChildClass that attempts to increase the accessibility
     * of the overridden method.
     */
    public class ChildClass extends ParentClass {
        /**
         * Attempting to increase accessibility of the overridden method.
         * Overrides the restrictedMethod from ParentClass and prints "Overridden method
         * in ChildClass".
         */
        @Override
        public void restrictedMethod() {
            System.out.println("Overridden method in ChildClass");
        }
    }

    /**
     * Main method to demonstrate the usage of ParentClass and ChildClass.
     *
     * @param args the command-line arguments (not used in this example)
     */
    public static void main(String[] args) {
        ParentClass parent = rule.new ParentClass();
        parent.restrictedMethod(); // Allowed, calls the method from ParentClass

        ChildClass child = rule.new ChildClass();
        child.restrictedMethod(); // Allowed, calls the overridden method from ChildClass
    }
}
