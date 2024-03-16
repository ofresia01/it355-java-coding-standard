/*
 * Author: Owen Fresia
 * The following demonstrates compliancy with Java recommendation "ERR51-J. Prefer user-defined exceptions over more general exception types", from Carnegie Mellon's Software Engineering Institute.
 * https://wiki.sei.cmu.edu/confluence/display/java/ERR51-J.+Prefer+user-defined+exceptions+over+more+general+exception+types.
 */

import java.io.FileNotFoundException;
import java.util.concurrent.TimeoutException;

/**
 * The following class uses specific exception types to catch different
 * exceptional behaviors. Each catch block handles errors specific to the type
 * of exception caught, demonstrating compliancy with ERR51-J.
 */
public class ERR51J {
    /**
     * Performs some operation that may throw specific exceptions.
     *
     * @throws FileNotFoundException if a file is not found.
     * @throws TimeoutException      if a connection timeout occurs.
     * @throws SecurityException     if a security violation is detected.
     */
    public void doSomething() throws FileNotFoundException, TimeoutException, SecurityException {
        // Perform operation that may throw exceptions...
    }

    /**
     * Main method to demonstrate exception handling with specific types.
     *
     * @param args Command-line arguments (not used here).
     */
    public static void main(String[] args) {
        ERR51J handler = new ERR51J();
        try {
            handler.doSomething();
        } catch (FileNotFoundException e) {
            System.err.println("Compliant: File not found error occurred");
            // Handle error specific to file not found
        } catch (TimeoutException e) {
            System.err.println("Compliant: Connection timeout error occurred");
            // Handle error specific to connection timeout
        } catch (SecurityException e) {
            System.err.println("Compliant: Security violation error occurred");
            // Handle error specific to security violation
        }
    }
}