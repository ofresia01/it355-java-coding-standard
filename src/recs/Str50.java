import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Str50 {
    
    public static final int BUF_SIZE = 1024;

    /*
     * Main Method
     * 
     * @params args Input arguments, should be the file to read data from
     */
    public static void main(String[] args){
        InputStream input = null;
        try {
            input = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("Could not open File");
            e.printStackTrace();
        }
        

        //Either use a reader which converts as it reads or make the string at the end of reading
        try {
            Reader r = new InputStreamReader(input);
            char[] data = new char[BUF_SIZE+1];
            StringBuilder builder = new StringBuilder();
        
            int charsRead = 0;

            while((charsRead = r.read(data)) != -1 ){
                builder.append(data, 0, charsRead);
                if(builder.length() > BUF_SIZE){
                    throw new IOException("Input too large");
                }
            }
            String str = builder.toString();

            input.close();

            System.out.println("Read data from file: " + str);
        } catch (IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }
        
    }

    
}
