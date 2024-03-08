import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IDS11 {
    /**
     *  Sanitizes input to prevent XSS by performing HTML entity encoding.  
     * 
     * @param input The string to process.
     * @return The sanitized string, or null if XSS is detected.
     */
    public String sanitizeInput(String input) {
        // Enhanced sanitization with HTML entity encoding
        String sanitizedInput = replaceHTMLEntities(input);

        // Validation (keep for extra security)
        String xssPattern = "[<>&\"']";
        Pattern pattern = Pattern.compile(xssPattern);
        Matcher matcher = pattern.matcher(sanitizedInput);

        return matcher.find() ? null : sanitizedInput;
    }

    /**
     * Replaces risky characters with HTML entities.
     */
    private String replaceHTMLEntities(String input) {
        input = input.replaceAll("&", "&amp;");
        input = input.replaceAll("<", "&lt;");
        input = input.replaceAll(">", "&gt;");
        input = input.replaceAll("\"", "&quot;");
        input = input.replaceAll("'", "&#x27;");
        input = input.replaceAll("/", "&#x2F;"); 
        return input;
    }

    public static void main(String[] args) {
        IDS11 sanitizer = new IDS11();
        String testInput = "<script>alert('xss')</script>";
        String sanitizedOutput = sanitizer.sanitizeInput(testInput);

        System.out.println(sanitizedOutput); // Expected: &lt;script&gt;alert('xss')&lt;/script&gt; or null
    }
}
