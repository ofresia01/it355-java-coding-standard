/*
 * Author: Owen Fresia
 * The following demonstrates compliancy with Java rule "NUM03-J. Use integer types that can fully represent the possible range of unsigned data", from Carnegie Mellon's Software Engineering Institute.
 * https://wiki.sei.cmu.edu/confluence/display/java/NUM03-J.+Use+integer+types+that+can+fully+represent+the+possible+range+of++unsigned+data.
 */
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Represents a class responsible for handling data streams, uses
 * `readUnsignedInt` to read 32-bit integers from the given `DataInputStream`.
 * It masks the result of `is.readInt()` with `0xFFFFFFFFL`, converting the
 * signed integer value to an unsigned long value with the same bit pattern.
 */
public class NUM03J {
    /**
     * Reads an unsigned 32-bit integer from the given data input stream.
     * 
     * @param is The data input stream to read from.
     * @return The unsigned 32-bit integer value.
     * @throws IOException if an I/O error occurs while reading the data.
     */
    public static long readUnsignedInt(DataInputStream is) throws IOException {
        return is.readInt() & 0xFFFFFFFFL; // Mask with 32 one-bits to ensure unsigned value
    }

    /**
     * Main method to demonstrate reading unsigned integer values.
     * 
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        try (DataInputStream dis = new DataInputStream(System.in)) {
            long unsignedValue = readUnsignedInt(dis);
            System.out.println("Unsigned integer value: " + unsignedValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
