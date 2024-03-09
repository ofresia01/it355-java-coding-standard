package src.rules.MET03;



/**
 * A class demonstrating the usage of the AuthenticationManager class.
 */
public class MET03 {
    /**
     * Main method to demonstrate the usage of the AuthenticationManager class.
     *
     * @param args the command-line arguments (not used in this example)
     */
    public static void main(String[] args) {
        AuthenticationManager authManager = new AuthenticationManager();
        authManager.authenticate(); // Simulate authentication

        authManager.grantAccess(); // Call the access granting method
    }
}
