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

    private static List<Account> readAccountsFromFile(String filename) {
        List<Account> accounts = new ArrayList<Account>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
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

    private static void logActivity(String activity) {
        try {
            String filename = createLogFile();
            writeLogEntry(activity, filename);
        } catch (IOException e) {
            System.err.println("Failed to log activity: " + e.getMessage());
        }
    }

    // The following method makes no assumptions about file creation - handling appropriately if the file doesn't exist, conformant to FIO50-J.
    private static String createLogFile() throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDate = dateFormat.format(new Date());
        Path logDirectory = Paths.get(DEFAULT_LOG_DIRECTORY);
        if (!Files.exists(logDirectory)) {
            Files.createDirectories(logDirectory);
        }
        String filename = DEFAULT_LOG_FILENAME + "_" + currentDate + ".txt";
        Path logFilePath = logDirectory.resolve(filename);
        Files.createFile(logFilePath);
        return logFilePath.toString();
    }

    private static void writeLogEntry(String entry, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(entry);
            writer.newLine();
        }
    }
}