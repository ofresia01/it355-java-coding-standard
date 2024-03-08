import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  Provides a more secure way to work with a sensitive log file.
 */
class SecureLogWriter {
    private final File logFile;
    private final BasicFileAttributes fileAttributes;

    public SecureLogWriter(String logFilePath) throws IOException {
        logFile = new File(logFilePath);
        // Store file attributes for later verification
        fileAttributes = Files.readAttributes(logFile.toPath(), BasicFileAttributes.class);
    }

    /**
     * Appends a log message to the log file.
     *
     * @param  message  the message to be appended to the log file
     * @throws IOException  if an I/O error occurs while appending to the log file
     */
    public void appendLogMessage(String message) throws IOException { 
        if (!verifyLogFileIdentity()) {
            throw new IOException("Log file might have been tampered with!");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) { // true for appending
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            writer.write(timestamp + " : " + message);
            writer.newLine();
        } 
    }

    /**
     * A function to verify the identity of a log file.
     *
     * @throws IOException
     * @return true if the log file identity is verified, false otherwise
     */
    private boolean verifyLogFileIdentity() throws IOException {
        BasicFileAttributes currentAttributes = Files.readAttributes(logFile.toPath(), BasicFileAttributes.class);

        // Adjust your verification as needed
        return currentAttributes.lastModifiedTime().equals(fileAttributes.lastModifiedTime()) &&
               currentAttributes.size() == fileAttributes.size(); 
    }
}

public class FIO51 {
    public static void main(String[] args) {
        try {
            File myObj = new File("my_secure_log.txt");
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } else {
              System.out.println("Log file already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Check for tampering by validating file through multiple attributes
        try {
            SecureLogWriter logWriter = new SecureLogWriter("my_secure_log.txt");
            logWriter.appendLogMessage("System started.");
            logWriter.appendLogMessage("User logged in."); 
        } catch (IOException e) {
            System.err.println("Error writing to log: " + e.getMessage());
        }
    }
}