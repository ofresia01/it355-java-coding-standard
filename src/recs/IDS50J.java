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

class IDS50J
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
        Pattern pattern = Pattern.compile("[^A-Za-z0-9._]");
        Matcher matcher = pattern.matcher(fileName);

        if(matcher.find())
        {
            System.out.println("Invalid file name");
        }
        else
        {
            File outputFile = new File(fileName);
            OutputStream output = new FileOutputStream(outputFile);
            output.write(12);
            output.close();
        }
        scan.close();
    }
}