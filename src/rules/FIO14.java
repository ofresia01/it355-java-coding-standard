//File created by Tanner Davis


/*
 * Rule name: Perform proper cleanup at program termination
 */

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class FIO14
{
public static void main(String[] args) throws FileNotFoundException
{
    final PrintStream outputFile =  new PrintStream(new BufferedOutputStream(new FileOutputStream("output.txt")));
    try
    {
        outputFile.println("Hello World!");
    }
    finally
    {
        outputFile.close(); // Always closes the file regardless if the program crashes or not to make sure any data written to the file is saved
    }
    Runtime.getRuntime().exit(1);
}
}
