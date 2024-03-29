//File created by Tanner Davis

/*
 * Rule name: Use conservative file naming conventions
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class IDS50
{
    public static void main(String[] args) throws IOException {
        /*
         * When naming files, only use ASCII characters and start the file name with a letter
         */
        File inputFile = new File("inFile.txt");


        /* 
         * When taking a file name from a user, use a pattern (RegEx) to determine whether the file name provided in valid or not
         */
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter the file you would like to be your output file: ");
        String fileName = scan.nextLine();
        Pattern pattern = Pattern.compile("[^A-Za-z]*[A-Za-z0-9_]*[.A-Za-z]");
        Matcher matcher = pattern.matcher(fileName);

        if(matcher.find())
        {
            // If file name provided by user conforms to standard file naming conventions, open the file and write 
            File outputFile = new File(fileName);
            OutputStream output = new FileOutputStream(outputFile);
            output.write(12);
            output.close();
        }
        else
        {
            System.out.println("Invalid file name"); // If name does not conform to standard file naming conventions, error message is printed to console
        }
        scan.close();
    }
}
