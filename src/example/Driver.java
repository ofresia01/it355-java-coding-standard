package example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
 * References to private mutable class members are not returned (i.e. the account list) - conformant to OBJ05-J.
 */
public class Driver {
    private static final String DEFAULT_LOG_DIRECTORY = "./src/example/logs";
    private static final String DEFAULT_LOG_FILENAME = "banking_system_log";

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Driver [inputfile.txt] [outputfile.txt]");
            return;
        }

        List<Account> accounts = readAccountsFromFile(args[0]);
        writeAccountsToFile(accounts, args[1]);
        logActivity("User activity logged.");
    }

    /**
     * Reads accounts from a file and returns a list of Account objects.
     *
     * @param  filename  the name of the file to read accounts from
     * @return           a list of Account objects read from the file
     */
    private static List<Account> readAccountsFromFile(String filename) {
        List<Account> accounts = new ArrayList<Account>(); // This list is not exposed (a mutable object) - conformant with OBJ13-J
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    // Modify strings before validating them -  conformant with IDS11-J.
                    // Normalize strings before validating them - conformant with IDS01-J.
                    for (int i = 0; i < parts.length; i++) {
                        // Modificaitons
                        parts[i] = parts[i].trim(); // Remove leading and trailing whitespace
                        parts[i] = parts[i].replace("\"", ""); // Remove leading and trailing double quotes
                        parts[i] = parts[i].replace("'", ""); // Remove leading and trailing single quotes
                        // Normalizaitions
                        parts[i] = normalizeString(parts[i]);
                   }
                   
                    String accountNumber = parts[0];
                    String accountHolderName = parts[1];
                    long balance = Integer.parseInt(parts[2]) & 0xFFFFFFFFL; // Mask integer with 32 one-bits, ensuring
                                                                             // unsigned value -- conformant with
                                                                             // NUM03-J
                    // Bit manipulation exclusively for byte array conversion
                    byte[] byteArray = new byte[4];
                    for (int i = 0; i < 4; i++) {
                        byteArray[i] = (byte) Integer.parseInt(parts[3].substring(i * 2, i * 2 + 2), 16);
                    }
                    accounts.add(new Account(accountNumber, accountHolderName, balance, byteArray));
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return accounts;
    }

    
    /**
     * Normalize the input text by capitalizing the first letter of each word and making the rest lowercase.
     *
     * @param  text  the input text to be normalized
     * @return       the normalized text
     */
    private static String normalizeString(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
    
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;  // Flag to track capitalization
    
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                if (capitalizeNext) {
                    result.append(Character.toUpperCase(c));
                    capitalizeNext = false;  // Reset for the rest of the word
                } else {
                    result.append(Character.toLowerCase(c));
                }
            } else {
                result.append(c);          // Non-letter characters added as is
                capitalizeNext = true;     // Capitalize the next word
            }
        }
    
        return result.toString();
    }

    /**
     * Writes the given list of accounts to the specified file.
     *
     * @param  accounts  the list of accounts to be written to the file
     * @param  filename  the name of the file to write the accounts to
     */
    private static void writeAccountsToFile(List<Account> accounts, String filename) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            for (final Account account : accounts) { // Usage of `final` ensures no mutation - conformant to DCL02-J
                bufferedWriter.write(account.toString());
                bufferedWriter.newLine();
            }
            System.out.println("Accounts written to file: " + filename);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Logs the given activity to a file.
     *
     * @param  activity  the activity to be logged
     */
    private static void logActivity(String activity) {
        try {
            LogFile logFile = createLogFile();
            writeLogEntry(activity, logFile);
        } catch (IOException e) {
            System.err.println("Failed to log activity: " + e.getMessage());
        }
    }

    // The following method makes no assumptions about file creation - handling appropriately if the file doesn't exist, conformant to FIO50-J.
    /**
     * Creates a log file with the current date and time in the default log directory.
     *
     * @return         	the path of the newly created log file
     */
    private static LogFile createLogFile() throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDate = dateFormat.format(new Date());
        Path logDirectory = Paths.get(DEFAULT_LOG_DIRECTORY);
        if (!Files.exists(logDirectory)) {
            Files.createDirectories(logDirectory);
        }
        String filename = DEFAULT_LOG_FILENAME + "_" + currentDate + ".txt";
        Path logFilePath = logDirectory.resolve(filename);
        Files.createFile(logFilePath);
        
        return new LogFile(logFilePath, Files.getLastModifiedTime(logFilePath).toMillis());
    }

    /**
     * A description of the entire Java function.
     *
     * @param  entry     description of parameter
     * @param  filename  description of parameter
     * @return           description of return value
     */
    private static void writeLogEntry(String entry, LogFile logFile) throws IOException {
        if (Files.getLastModifiedTime(logFile.getPath()).toMillis() != logFile.getCreationTime()) {
            // Potential file tampering or change check
            throw new IOException("Log file may have been modified");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile.getPath().toString(), true))) {
            writer.write(entry);
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.err.println("Failed to write log entry: " + e.getMessage());
        }
    }
}

// Class used to identify a log file by multiple attributes - confromant to FIO51-J.
class LogFile {
    private Path path;
    private long creationTime;

    public LogFile(Path path, long creationTime) {
        this.path = path;
        this.creationTime = creationTime;
    }

    public Path getPath() {
        return this.path;
    }

    public long getCreationTime() {
        return this.creationTime;
    }
}