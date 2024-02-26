import java.io.DataInputStream;
import java.io.IOException;

public class NUM03J {
    /*
     * Noncompliant Code Example
     * This noncompliant code example uses a generic method for reading integer data
     * without considering the signedness of the source. It assumes that the data
     * read is always signed and treats the most significant bit as the sign bit.
     * When the data read is unsigned, the actual sign and magnitude of the values
     * may be misinterpreted.
     */
    public static int getInteger(DataInputStream is) throws IOException {
        return is.readInt();
    }

    /*
     * Compliant Solution
     * This compliant solution requires that the values read are 32-bit unsigned
     * integers. It reads an unsigned integer value using the readInt() method. The
     * readInt() method assumes signed values and returns a signed int; the return
     * value is converted to a long with sign extension. The code uses an &
     * operation to mask off the upper 32 bits of the long, producing a value in the
     * range of a 32-bit unsigned integer, as intended. The mask size should be
     * chosen to match the size of the unsigned integer values being read.
     */
    public static long getIntegerCompliant(DataInputStream is) throws IOException {
        return is.readInt() & 0xFFFFFFFFL; // Mask with 32 one-bits
    }
}