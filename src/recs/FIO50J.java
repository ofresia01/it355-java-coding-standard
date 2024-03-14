
/*
 * Author: Owen Fresia
 * The following demonstrates compliancy with Java recommendation "FIO50-J. Do not make assumptions about file creation", from Carnegie Mellon's Software Engineering Institute.
 * https://wiki.sei.cmu.edu/confluence/display/java/FIO50-J.+Do+not+make+assumptions+about+file+creation.
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The following class adheres to FIO50-J by making no assumptions about the
 * file creation process, instead of assuming creation is always successful, it
 * explicitly handles file creation using
 * `Files.createFile(Paths.get(filename))`, atomically creating a new file only
 * if it does not already exist.
 */
public class FIO50J {
    /**
     * Logs user activity to a file.
     *
     * @param activity The activity to log.
     * @param filename The name of the log file.
     * @throws IOException If an I/O error occurs.
     */
    public void logActivity(String activity, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)))) {
            writer.println(activity);
        }
    }

    /**
     * Creates a new log file for the current session.
     *
     * @return The name of the new log file.
     * @throws IOException If an I/O error occurs.
     */
    public String createLogFile() throws IOException {
        String filename = "log_" + System.currentTimeMillis() + ".txt";
        Files.createFile(Paths.get(filename));
        return filename;
    }

    /**
     * Main method to demonstrate user activity logging.
     *
     * @param args Command-line arguments (not used here).
     */
    public static void main(String[] args) {
        FIO50J logger = new FIO50J();
        try {
            String filename = logger.createLogFile();
            logger.logActivity("User logged in", filename);
            logger.logActivity("User performed action A", filename);
            logger.logActivity("User performed action B", filename);
            System.out.println("User activity logged to: " + filename);
        } catch (IOException e) {
            System.err.println("Failed to log user activity: " + e.getMessage());
        }
    }
}