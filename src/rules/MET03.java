/**
 * A class demonstrating the usage of the AuthenticationManager class.
 */
public class MET03 {
    static MET03 rule = new MET03();

    public class AuthenticationManager {
        private boolean isAuthenticated = false;

        /**
         * Final method for granting access after successful authentication.
         * Prints "Access granted." if authenticated, otherwise "Access denied."
         */
        public final void grantAccess() {
            if (isAuthenticated) {
                System.out.println("Access granted.");
            } else {
                System.out.println("Access denied.");
            }
        }

        /**
         * Method to simulate the authentication process.
         * Sets the isAuthenticated flag to true, indicating successful authentication.
         * Prints "Authentication successful." upon successful authentication.
         */
        public void authenticate() {
            isAuthenticated = true;
            System.out.println("Authentication successful.");
        }
    }

    /**
     * Main method to demonstrate the usage of the AuthenticationManager class.
     *
     * @param args the command-line arguments (not used in this example)
     */
    public static void main(String[] args) {
        AuthenticationManager authManager = rule.new AuthenticationManager();
        authManager.authenticate(); // Simulate authentication

        authManager.grantAccess(); // Call the access granting method
    }
}
