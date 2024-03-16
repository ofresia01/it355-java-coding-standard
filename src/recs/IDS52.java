//File created by Tanner Davis

/*
 * Rule name: Prevent code injection
 * When taking input from a user, make sure it matches a set pattern before
 *  ever using the input in your code to prevent malicious users from injecting harmful code
 *  into your system
 */
import javax.script.ScriptException;

class IDS52
{
    private static boolean evalScript(String name) throws ScriptException {
        // Allow only alphanumeric and underscore chars in name
        if (!name.matches("[\\w]*")) {
          // String does not match whitelisted characters
          return false;
        }
        
        return true;
      }

      public static void main(String[] args) throws ScriptException {
        String name1 = "David";
        String name2 = "O'Connor";
        String name3 = "False5";

        if(evalScript(name1))
          System.out.println("String " + name1 + " passed evaluation");
        else
          System.out.println("String " + name1 + " did not pass evaluation");

        if(evalScript(name2))
          System.out.println("String " + name2 + " passed evaluation");
        else
          System.out.println("String " + name2 + " did not pass evaluation");

        if(evalScript(name3))
          System.out.println("String " + name3 + " passed evaluation");
        else
          System.out.println("String " + name3 + " did not pass evaluation");
      
      }
}
