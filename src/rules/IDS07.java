import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IDS07 {
    /**
     * Executes an external command in a safer manner,
     * mitigating some command injection risks.
     *
     * @param command The command to execute as an array of strings.
     * @return The standard output of the executed command.
     * @throws IOException If an I/O error occurs.
     */
    public String executeCommand(String[] command) throws IOException {
        // ProcessBuilder uses Runtime.exec() under the hood, allows more control
        ProcessBuilder pb = new ProcessBuilder(command); 
        Process process = pb.start();

        // Read and return the command output
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(process.getInputStream()))) {
            String line;
            // Sanitize here
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }

        return output.toString();
    }

    public static void main(String[] args) {
        IDS07 executor = new IDS07();

        // Example usage (Replace with desired command)
        String[] command = {"ping", "google.com"};

        try {
            String output = executor.executeCommand(command);
            System.out.println(output);
        } catch (IOException e) {
            System.err.println("Error executing command: " + e.getMessage());
        }
    }
}
