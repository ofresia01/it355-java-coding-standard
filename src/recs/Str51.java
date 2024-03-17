import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

public class Str51 {

    /**
     * Main method of Str51 class
     * 
     * @param args
     */
    public static void main(String[] args){
        String str = "Hello World";

        Charset set = StandardCharsets.UTF_8;
        CharsetEncoder encode = set.newEncoder();
        CharsetDecoder decode = set.newDecoder();

        try {
            ByteBuffer encodedBuffer = encode.encode(CharBuffer.wrap(str));

            CharBuffer decodeBuffer = decode.decode(encodedBuffer);

            String decodedString = decodeBuffer.toString();

            System.out.println("Original String: " + str);
            System.out.println("Encoded String: " + encodedBuffer);
            System.out.println("Decoded String: " + decodedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
