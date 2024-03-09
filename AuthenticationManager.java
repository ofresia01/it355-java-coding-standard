package src.rules.MET03;

// Rule 3

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

