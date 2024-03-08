import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class IDS01 {

    /**
     * Validates an input string against XSS patterns, after
     * normalizing to prevent evasion attempts.
     *
     * @param input The string to validate.
     * @return true if safe, false if potential XSS is detected.
     */
    public boolean validateInput(String input) {
        // Normalize using NFKC for compatibility 
        String normalizedInput = Normalizer.normalize(input, Normalizer.Form.NFKC);

        // Perform XSS validation on the normalized input
        String xssPattern = "[<>&\"']"; // example pattern
        Pattern pattern = Pattern.compile(xssPattern);
        Matcher matcher = pattern.matcher(normalizedInput);

        return !matcher.find(); // Return true if no match is found
    }

    public static void main(String[] args) {
        IDS01 validator = new IDS01();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string to validate: ");
        String input = scanner.nextLine();

        if (validator.validateInput(input)) {
            System.out.println("Input is safe.");
        } else {
            System.out.println("Potential XSS detected!");
        }

        scanner.close(); // Close the scanner
    }
}
