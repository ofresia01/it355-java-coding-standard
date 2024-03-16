//File created by Tanner Davis

/*
 * Rule name: Distinguish between characters or bytes read from a stream and -1
 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class FIO08
{
    public static void main(String[] args) throws IOException {
        FileReader inputFile = new FileReader(new File("inputFile.txt"));

        int inBuffer;
        char data;
        /*
        * This while loop checks the return value of the character input method against -1
        * When the value returned is not -1, it is then type casted into a char and stored in the data variable
        */
        while((inBuffer = inputFile.read()) != -1)
        {
            data = (char) inBuffer;
            System.out.println(data);
        }

        inputFile.close();
    }
    
}
