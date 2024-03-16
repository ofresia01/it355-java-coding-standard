//File created by Tanner Davis

/*
 * Rule name: Do not expose buffers or their backing arrays methods to untrusted code
 * When using a Buffer object, make sure any methods using the Buffer object are read only,
 *  otherwise the method could change the original, backing array
 */

import java.nio.CharBuffer;
import java.nio.ReadOnlyBufferException;

final class Buffer
{
    private char[] dataArray;

    public Buffer()
    {
        dataArray = new char[10];
        for(int i = 0; i < dataArray.length; i++)
        {
            dataArray[i] = (char) ('A' + i);
        }
    }

    /*
     * This method calls the wrap method as a read only, preventing malicious users from altering the original array
     */
    public CharBuffer getBufferCopy()
    {
        return CharBuffer.wrap(dataArray).asReadOnlyBuffer();
    }
}

public class FIO05
{
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        CharBuffer readOnlyBuffer = buffer.getBufferCopy();

        try
        {
            readOnlyBuffer.put(0, 'Z');
            System.out.println("Successfully modified the read-only buffer.");
        }
        catch(ReadOnlyBufferException e)
        {
            System.out.println("Failed to modify the read-only buffer. Exception: " + e.getMessage());
        }

        System.out.println("Original array content: " + new String(buffer.getBufferCopy().array()));
    }
}
