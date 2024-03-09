package src.rules;

import java.io.FileWriter;
import java.io.IOException;

/**
 * A class demonstrating the use of finalizers and associated problems.
 */
public class MET12 {
    private FileWriter fileWriter;

    /**
     * Constructs a FinalizerExample object and opens a FileWriter.
     *
     * @param filename the name of the file to be written
     * @throws IOException if an I/O error occurs
     */
    public MET12(String filename) throws IOException {
        fileWriter = new FileWriter(filename);
    }

    /**
     * Writes a message to the file.
     *
     * @param message the message to be written
     * @throws IOException if an I/O error occurs
     */
    public void writeToFile(String message) throws IOException {
        fileWriter.write(message);
    }

    /**
     * Finalizer method to close the FileWriter.
     * WARNING: Finalizers are not reliable and may not be executed in all cases.
     */
    @Override
    protected void finalize() throws Throwable {
        try {
            fileWriter.close(); // Close the FileWriter
        } catch (IOException e) {
            // Handle IOException
            System.err.println("Error occurred while closing FileWriter: " + e.getMessage());
        } finally {
            super.finalize();
        }
    }

    /**
     * Main method to demonstrate the usage of FinalizerExample.
     *
     * @param args the command-line arguments (not used in this example)
     * @throws IOException if an I/O error occurs
     */
    public static void main(String[] args) throws IOException {
        // Create a FinalizerExample object
        MET12 example = new MET12("example.txt");

        try {
            // Write message to file
            example.writeToFile("Hello, world!");
        } finally {
            // WARNING: Finalizers may not execute reliably
            // Close resources manually if possible
            // example.fileWriter.close();
        }
    }
}
