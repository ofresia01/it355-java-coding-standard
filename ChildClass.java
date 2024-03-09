package src.rules.MET04;

/**
 * A class representing a ChildClass that attempts to increase the accessibility of the overridden method.
 */
public class ChildClass extends ParentClass {
    /**
     * Attempting to increase accessibility of the overridden method.
     * Overrides the restrictedMethod from ParentClass and prints "Overridden method in ChildClass".
     */
    @Override
    public void restrictedMethod() {
        System.out.println("Overridden method in ChildClass");
    }
}
