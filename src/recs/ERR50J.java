/*
 * Author: Owen Fresia
 * The following demonstrates compliancy with Java recommendation "ERR50-J. Use exceptions only for exceptional conditions", from Carnegie Mellon's Software Engineering Institute.
 * https://wiki.sei.cmu.edu/confluence/display/java/ERR50-J.+Use+exceptions+only+for+exceptional+conditions.
 */

/**
 * The following class' `processStrings` method will throw an
 * `IllegalArgumentException` if its parameter is null. THis is considered an
 * exceptional condition as it indicates an error in the usage of the method.
 */
public class ERR50J {
    /**
     * Processes a single string.
     *
     * @param string The string to process.
     * @return The processed string.
     */
    public String processSingleString(String string) {
        // Process the string...
        return string;
    }

    /**
     * Processes an array of strings and concatenates the processed results.
     *
     * @param strings The array of strings to process.
     * @return The concatenated processed strings.
     * @throws IllegalArgumentException if the input array is null.
     */
    public String processStrings(String[] strings) {
        if (strings == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        StringBuilder result = new StringBuilder();
        for (String str : strings) {
            if (str != null) {
                result.append(processSingleString(str));
            }
        }
        return result.toString();
    }

    /**
     * Main method to demonstrate processing strings.
     *
     * @param args Command-line arguments (not used here).
     */
    public static void main(String[] args) {
        ERR50J processor = new ERR50J();
        String[] inputStrings = { "Hello", "World", "!" };
        try {
            String processedResult = processor.processStrings(inputStrings);
            System.out.println("Compliant: Processed result: " + processedResult);
        } catch (IllegalArgumentException e) {
            System.err.println("Compliant: Exception caught: " + e.getMessage());
        }
    }
}