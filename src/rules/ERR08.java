/**
 * This class demonstrates handling NullPointerException.
 */
public class ERR08 {
    
    /**
     * The main method of the program.
     * 
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        try {
            // Your code here that may potentially throw NullPointerException
            String str = null;
            int length = str.length(); // This will throw NullPointerException
        } catch (NullPointerException e) {
            // Handle the NullPointerException appropriately
            System.err.println("Caught NullPointerException: " + e.getMessage());
            e.printStackTrace();
        }
    }
}



