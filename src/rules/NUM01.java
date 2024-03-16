/*
 * Author: Owen Fresia
 * The following demonstrates compliancy with Java rule "NUM01-J. Do not perform bitwise and arithmetic operations on the same data", from Carnegie Mellon's Software Engineering Institute.
 * https://wiki.sei.cmu.edu/confluence/display/java/NUM01-J.+Do+not+perform+bitwise+and+arithmetic+operations+on+the+same+data.
 */

/**
 * This class' `combineBitCollection` method ensures valid combinations from
 * byte array, with bitwise operators used exclusively for manipulation of bits.
 */
public class NUM01J {
    /**
     * Computes a result by combining bit collection from a byte array.
     *
     * @param bytes The array of bytes containing the bit collection.
     * @return The result after combining the bit collection.
     */
    public int combineBitCollection(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < bytes.length; i++) {
            result = (result << 8) | (bytes[i] & 0xFF);
        }
        return result;
    }

    /**
     * Main method to demonstrate combining bit collection from a byte array.
     *
     * @param args Command-line arguments (not used here).
     */
    public static void main(String[] args) {
        NUM01J operations = new NUM01J();
        byte[] byteArray = { -1, -1, -1, -1 };
        int combinedResult = operations.combineBitCollection(byteArray);
        System.out.println("Compliant: Combined result: " + combinedResult);
    }
}