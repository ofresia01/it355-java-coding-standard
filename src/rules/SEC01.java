//File created by Tanner Davis


/*
 * Rule name: Do not allow tainted variables in priileged blocks
 * When taking input from the user, make sure to sanitize the input to prevent
 *  any malicious users from running malicious input on your system
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

@SuppressWarnings("removal")
class SEC01
{
    /*
    *   Method that accepts a string to sanitize before being used in any privileged code blocks
    */
    private String cleanAFileNameAndPath(String fileName) throws Exception
    {
        /*
        * Actual sanitization implementation would occur here
        * For simplicity, the method will just return the original file name
        */

        return fileName;
    }

    /*
    *   Example priviliged method that reads a byte from the input file
    */
    private void privilegedMethod(final String fileName) throws IOException
    {
        final String cleanedFileName;
        try
        {
            /*
             * Calls the cleanAFileNameAndPath to sanitize possibly malicious inputs
             */
            cleanedFileName = cleanAFileNameAndPath(fileName);
        }
        catch(Exception e)
        {
            return;
        }

        try {
            FileInputStream inputFile = AccessController.doPrivileged(new PrivilegedExceptionAction<FileInputStream>()
            {
                public FileInputStream run() throws FileNotFoundException
                {
                    return new FileInputStream(cleanedFileName);
                }
            });

            int byteRead = inputFile.read();
            System.out.println("Byte read from the file: " + byteRead);
        }
        catch(PrivilegedActionException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        SEC01 sec01 = new SEC01();

        try
        {
            sec01.privilegedMethod("exampleFile.txt");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
