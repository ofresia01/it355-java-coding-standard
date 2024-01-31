public class ExampleRule {
    // Fields (variables)
    private int number;
    private String text;

    // Constructor
    public ExampleRule(int number, String text) {
        this.number = number;
        this.text = text;
    }

    // Getter methods
    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    // Setter methods
    public void setNumber(int number) {
        this.number = number;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Other methods
    public void displayInfo() {
        System.out.println("Number: " + number);
        System.out.println("Text: " + text);
    }

    // Main method (for testing purposes)
    public static void main(String[] args) {
        ExampleRule example = new ExampleRule(42, "Hello, World!");
        example.displayInfo();
    }
}