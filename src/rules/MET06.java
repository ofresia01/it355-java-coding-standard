import java.util.ArrayList;
import java.util.List;

//Rule 5
/**
 * A class representing a Person with a name and a list of emails.
 */
class MET06 implements Cloneable {
    private String name;
    private List<String> emails;

    /**
     * Constructs a Person object with the specified name and list of emails.
     *
     * @param name   the name of the person
     * @param emails the list of emails associated with the person
     */
    public MET06(String name, List<String> emails) {
        this.name = name;
        this.emails = new ArrayList<>(emails);
    }

    /**
     * Adds an email to the list of emails associated with the person.
     *
     * @param email the email to be added
     */
    public void addEmail(String email) {
        emails.add(email);
    }

    /**
     * Returns a shallow copy of the list of emails associated with the person.
     *
     * @return a shallow copy of the list of emails
     */
    public List<String> getEmails() {
        return new ArrayList<>(emails);
    }

    /**
     * Clones this Person object.
     * Avoids invoking overridable methods in clone() to ensure the integrity of the
     * cloning process.
     *
     * @return a clone of this Person object
     */
    @Override
    public Object clone() {
        try {
            MET06 cloned = (MET06) super.clone();
            // Avoid invoking overridable methods in clone() to prevent security
            // vulnerabilities
            // cloned.addEmail("cloned@example.com"); // Potential security vulnerability
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Returns a string representation of this Person object.
     *
     * @return a string representation of this Person object
     */
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", emails=" + emails +
                '}';
    }

    /**
     * A class demonstrating the usage of the Person class and the clone() method.
     */
    /**
     * Main method to demonstrate the usage of the Person class and the clone()
     * method.
     *
     * @param args the command-line arguments (not used in this example)
     */
    public static void main(String[] args) {
        List<String> emails = new ArrayList<>();
        emails.add("example@example.com");

        MET06 original = new MET06("John", emails);
        original.addEmail("john@example.com");

        // Clone the original person
        MET06 cloned = (MET06) original.clone();

        // Add an email to the original person
        original.addEmail("new@example.com");

        // Display original and cloned persons
        System.out.println("Original: " + original);
        System.out.println("Cloned: " + cloned);
    }
}