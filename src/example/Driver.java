package example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * References to private mutable class members are not returned (i.e. the account list) - conformant to OBJ05-J.
 * Contains no debugging entry points - conformant with ENV06-J.
 * Objects and code are garbage collection friendly - conformant with OBJ52-J.
 * References to private mutable class members are not returned (i.e. the account list) - conformant to OBJ05-J.
 * No code is signed as it all performed unprivileged operations - conformant to ENV00-J.
 * All File Operations handle returns and thrown exceptions - conformant to FIO02-J.
 * All files created are not temperory, if temporary files are created they will be deleted - conformant to FIO03-J.
 */
public class Driver {
    private static final String DEFAULT_LOG_DIRECTORY = "./src/example/logs"; // variable names use correct naming conventions - conformant with DCL50-J
    private static final String DEFAULT_LOG_FILENAME = "banking_system_log";
    private static final Charset set = StandardCharsets.UTF_8;
    private static final CharsetEncoder encoder = set.newEncoder();
    private static final CharsetDecoder decoder = set.newDecoder();

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java -Xverify:all Driver [inputfile.txt] [outputfile.txt]"); // Do not disable bytecode verification - conformant with ENV04-J
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
        
        Pattern pattern = Pattern.compile("^[A-Za-z][A-Za-z0-9_]*[.A-Za-z]*$"); // This creates a RegEx pattern to test if a file name is valid or not - conformant with IDS50-J
        Matcher matcher = pattern.matcher(filename);
        if(matcher.find())
        {
            System.out.println("Invalid input file name");
            return accounts;
        }
        else
        {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) { // Buffered Reader is used, protects against misreading multibyte characters - conformant with STR50
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
                
                bufferedReader.close(); // Close any open input files when they are no longer needed to free system resources - conformant with FIO14-J
                    
            } catch (FileNotFoundException exception){
                System.out.println("Input file could not be found.");
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            return accounts;
        }

        // Code that would be compliant with rule FIO03-J, if inputFile is considered a temporary file that is no longer needed once we read it into our system, This will delete the file.
        // Code is commented so the input file does not need to be regenerated every time program is run.
        // try{
        //     File inputFile = new File(filename);
        //     if (!inputFile.delete()){
        //         System.out.println("Input File could not be deleted");
        //     }
        // } catch (IOException exception){
        //     exception.printStackTrace();
        // }
        
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
            bufferedWriter.close();
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
        //Use System Property to obtain username
        String curUser = System.getProperty("user.name"); //Does not trust values of environment variable, uses system - conformant to ENV02-J
        ByteBuffer encodedUser = encoder.encode(CharBuffer.wrap(curUser));
        return new LogFile(logFilePath, Files.getLastModifiedTime(logFilePath).toMillis(), encodedUser);
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
            String decodedUser = decoder.decode(logFile.getUser()).toString();
            writer.write(decodedUser + ": " + entry);
            writer.newLine();
            writer.flush();
            writer.close(); // Close any open output files when you are done writing to them to free system resources as well as save all data written to output file - conformant with FIO14-J
        } catch (IOException e) {
            System.err.println("Failed to write log entry: " + e.getMessage());
        }
    }
}

// Class used to identify a log file by multiple attributes - confromant to FIO51-J.
class LogFile {
    private Path path;
    private long creationTime;
    private ByteBuffer user; //encoded with the CharsetEncoder, decoded with the CharsetDecoder - conformant with STR51-J.

    /**
     * Constructor for LogFile class
     * 
     * @param path
     * @param creationTime
     * @param user
     */
    public LogFile(Path path, long creationTime, ByteBuffer user) {
        this.path = path;
        this.creationTime = creationTime;
        this.user = user;
    }

    /**
     * Getter for path
     * 
     * @return path
     */
    public Path getPath() {
        return this.path;
    }

    /**
     * Getter for creation time
     * 
     * @return creationTime
     */
    public long getCreationTime() {
        return this.creationTime;
    }

    /**
     * Getter for user
     * 
     * @return user
     */
    public ByteBuffer getUser() {
        return this.user;
    }
}
