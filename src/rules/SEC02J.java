//File created by Tanner Davis

/*
 * Rule name: Do not base security checks on untrusted sources
 */

import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.AccessController;
import java.security.PrivilegedAction;

@SuppressWarnings("removal")
class SEC02J {
    public RandomAccessFile openFile(java.io.File fileName)
    {
        /*
         * Creates a new File object in order to ensure any methods called on the object are trusted objects and not overridden methods
         */
        final java.io.File copyFile = new java.io.File(fileName.getPath());

        // Security check using a trusted source
        if (!isAccessAllowed(copyFile))
        {
            throw new SecurityException("Access denied. Security check failed.");
        }

        return AccessController.doPrivileged(new PrivilegedAction<RandomAccessFile>()
        {
            public RandomAccessFile run()
            {
                try
                {
                    return new RandomAccessFile(copyFile, "r");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }

    private boolean isAccessAllowed(java.io.File file)
    {
        // Implementation of security check logic would go here
        // For demonstration purposes, allow access only if the file name contains "allowed"
        return file.getName().toLowerCase().contains("allowed");
    }

    public static void main(String[] args) throws IOException
    {
        SEC02J sec02J = new SEC02J();
        java.io.File file = new java.io.File("example_allowed.txt");

        try
        {
            RandomAccessFile randomAccessFile = sec02J.openFile(file);
            randomAccessFile.read();
        }
        catch (SecurityException e)
        {
            System.out.println("SecurityException: " + e.getMessage());
        }
    }
}