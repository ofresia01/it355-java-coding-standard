//File created by Tanner Davis

/*
 * Rule name: Do not base security checks on untrusted sources
 */

import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.io.File;

@SuppressWarnings("removal")
class SEC02 {
    public RandomAccessFile openFile(java.io.File fileName)
    {
        /*
         * Creates a new File object in order to ensure any methods called on the object are trusted objects and not overridden methods
         */
        final File copyFile = new File(fileName.getPath());

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

    private boolean isAccessAllowed(File file)
    {
        // Implementation of security check logic would go here
        // For simplicity, allow access only if the file name contains "allowed"
        return file.getName().toLowerCase().contains("allowed");
    }

    public static void main(String[] args) throws IOException
    {
        SEC02 sec02 = new SEC02();
        File file = new File("example_allowed.txt");

        try
        {
            RandomAccessFile randomAccessFile = sec02.openFile(file);
            randomAccessFile.read();
        }
        catch (SecurityException e)
        {
            System.out.println("SecurityException: " + e.getMessage());
        }
    }
}
