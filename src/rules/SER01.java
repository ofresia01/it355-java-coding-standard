


import java.io.*;

/**
 * A class representing a Person with name and age attributes.
 * Implements Serializable to support serialization.
 */
public class SER01 implements Serializable {
    private String name;
    private int age;

    /**
     * Constructs a Person object with the specified name and age.
     *
     * @param name the name of the person
     * @param age  the age of the person
     */
    public SER01(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Getter method for the name attribute.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the age attribute.
     *
     * @return the age of the person
     */
    public int getAge() {
        return age;
    }

    /**
     * Custom serialization method to write object state to the stream.
     *
     * @param out the ObjectOutputStream to write to
     * @throws IOException if an I/O error occurs while writing to the stream
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // Writes non-static and non-transient fields

    }

    /**
     * Custom deserialization method to read object state from the stream.
     *
     * @param in the ObjectInputStream to read from
     * @throws IOException            if an I/O error occurs while reading from the stream
     * @throws ClassNotFoundException if the class of a serialized object cannot be found
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Reads non-static and non-transient fields

    }

    /**
     * Main method to demonstrate serialization and deserialization of Person objects.
     *
     * @param args the command-line arguments (not used in this example)
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Serialize a Person object
        SER01 person = new SER01("Alice", 30);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.ser"));
        oos.writeObject(person);
        oos.close();

        // Deserialize a Person object
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.ser"));
        SER01 deserializedPerson = (SER01) ois.readObject();
        ois.close();

        // Display deserialized person's attributes
        System.out.println("Deserialized Person - Name: " + deserializedPerson.getName() +
                ", Age: " + deserializedPerson.getAge());
    }
}
