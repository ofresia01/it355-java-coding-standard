//File created by Tanner Davis

/*
 * Rule name: Protect sensitive operations with security manaager checks
 */

import java.util.Hashtable;

@SuppressWarnings("removal")
class SEC04
{
    private Hashtable<Integer, String> hashTable = new Hashtable<>();
 
    /*
    * Adds a security check that prevents entries in the hash table from being removed by malicious users
    */
    public void removeEntry(Object key)
    {
        check("removeKeyPermission");
        hashTable.remove(key);
    }

    /*
    *   Check the input string to see if it is allowed
    */
    private void check(String directive)
    {
        SecurityManager securityManager = System.getSecurityManager();

        if(securityManager != null)
        {
            // Perform a custom security check using the custom security manager
            ((CustomSecurityManager) securityManager).checkSecurityAccess(directive);
        }
    }
 
    public static void main(String[] args)
    {
        // Set the custom security manager
        System.setSecurityManager(new CustomSecurityManager());

        SEC04 sec04 = new SEC04();

        // Try to remove an entry
        try
        {
            sec04.removeEntry(1);
            System.out.println("Entry removed successfully.");
        }
        catch(SecurityException e)
        {
            System.out.println("SecurityException: " + e.getMessage());
        }
    }
}

class CustomSecurityManager extends SecurityManager
{
    /*
    *   Method used to check if the input string is allowed to be used by the user
    */
    public void checkSecurityAccess(String directive)
    {
        if("removeKeyPermission".equals(directive))
        {
            // Custom security check logic would go here
            // For simplicity, disallow removing entries
            throw new SecurityException("Permission denied: Removing entries is not allowed.");
        }
    }
}
