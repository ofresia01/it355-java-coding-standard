

import java.io.*;

/**
 * An outer class containing an inner class.
 */
public class SER05 implements Serializable {
    private int outerField;

    /**
     * Constructs an OuterClass object with the specified field value.
     *
     * @param outerField the value of the outer field
     */
    public SER05(int outerField) {
        this.outerField = outerField;
    }

    /**
     * Inner class that should not be serialized.
     */
    private class InnerClass {
        private int innerField;

        /**
         * Constructs an InnerClass object with the specified field value.
         *
         * @param innerField the value of the inner field
         */
        public InnerClass(int innerField) {
            this.innerField = innerField;
        }

        /**
         * Getter method for the inner field.
         *
         * @return the value of the inner field
         */
        public int getInnerField() {
            return innerField;
        }
    }

    /**
     * Main method to demonstrate serialization.
     *
     * @param args the command-line arguments (not used in this example)
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Create an OuterClass object
        SER05 outer = new SER05(10);
        // Create an InnerClass object
        SER05.InnerClass inner = outer.new InnerClass(20);

        // Serialize the OuterClass object (should NOT serialize InnerClass)
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("outer.ser"));
        oos.writeObject(outer);
        oos.close();

        // Deserialize the OuterClass object
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("outer.ser"));
        SER05 deserializedOuter = (SER05) ois.readObject();
        ois.close();

        // Verify that inner field of InnerClass is not serialized
        System.out.println("Deserialized OuterClass - outerField: " + deserializedOuter.outerField);
        // Attempt to access inner field of deserialized OuterClass
        try {
            System.out.println("Deserialized OuterClass - innerField: " + deserializedOuter.new InnerClass(0).getInnerField());
        } catch (NullPointerException e) {
            System.out.println("InnerClass was not serialized");
        }
    }
}
