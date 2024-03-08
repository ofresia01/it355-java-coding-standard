// Enum for authentication outcomes
enum AuthenticationResult {
    SUCCESS,
    INVALID_CREDENTIALS,
    SYSTEM_ERROR 
}
// Custom Exception Classes for demonstration purposes
class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
class AuthenticationException extends Exception {
    public AuthenticationException(String message) {
        super(message);
    }
}

class AuthenticationService {
    /**
     * Authenticates a user against the database. 
     *
     * @param username The user's username.
     * @param password The user's password.
     * @return An AuthenticationResult enum indicating the outcome.
     * @throws InvalidCredentialsException if username/password is incorrect.
     * @throws AuthenticationException for other authentication system failures.
     */
    public AuthenticationResult authenticateUser(String username, String password) 
        throws InvalidCredentialsException, AuthenticationException {
        // Simulating authentication logic for demonstration
        if (!isValidCredentials(username, password)) {
            throw new InvalidCredentialsException("Username or password incorrect");
        }

        if (!canAccessSystem(username)) {
            throw new AuthenticationException("Account temporarily locked");
        }

        return AuthenticationResult.SUCCESS;  // Successful authentication
    }

    // Mock implementations for compilation
    private boolean isValidCredentials(String username, String password) {
        return username.equals("testuser") && password.equals("password"); 
    }
    private boolean canAccessSystem(String username) {
        return true; // Assume the user can access the system
    }
}

public class MET54 {
    public static void main(String[] args) 
        throws InvalidCredentialsException, AuthenticationException {
        AuthenticationService authService = new AuthenticationService();
        AuthenticationResult result = authService.authenticateUser("testuser", "password"); 
        System.out.println("Authentication Result: " + result);
    }
}
