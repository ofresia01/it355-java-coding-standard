//File created by Tanner Davis

/*
 * Rule name: Protect sensitive operations with security manaager checks
 */

import java.util.Hashtable;

@SuppressWarnings("removal")
class SEC04J
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

        SEC04J sec04J = new SEC04J();

        // Try to remove an entry
        try
        {
            sec04J.removeEntry(1);
            System.out.println("Entry removed successfully.");
        }
        catch(SecurityException e)
        {
            System.out.println("SecurityException: " + e.getMessage());
        }
    }
}
 
@SuppressWarnings("removal")
class CustomSecurityManager extends SecurityManager
{
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