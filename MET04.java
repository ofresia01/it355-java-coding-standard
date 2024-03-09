package src.rules.MET04;


/**
 * A class demonstrating the usage of the ParentClass and ChildClass.
 */
public class MET04 {
    /**
     * Main method to demonstrate the usage of ParentClass and ChildClass.
     *
     * @param args the command-line arguments (not used in this example)
     */
    public static void main(String[] args) {
        ParentClass parent = new ParentClass();
        parent.restrictedMethod(); // Allowed, calls the method from ParentClass

        ChildClass child = new ChildClass();
        child.restrictedMethod(); // Allowed, calls the overridden method from ChildClass
    }
}
