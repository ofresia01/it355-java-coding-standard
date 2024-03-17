import java.util.Map;
import java.io.*;

/**
 * Env02 Class with main to show how to obtain environment variables
 */
public class Env02{

    /**
     * main method
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args){
        //Correct Method to obtain environment variables
        String goodUsername = System.getProperty("user.name");

        System.out.println("Your Username: " + goodUsername);

        //Do not use this method to obtain the username, or any other environemnt value
        String badUsername = System.getenv("USER");
        System.out.println("Unsafe Username: " + badUsername);
    }
}


// String user = "attacker";
//         ProcessBuilder pb = new ProcessBuilder();
//         pb.command("/usr/bin/printenv");
//         Map<String, String> environment = pb.environment();
//         environment.put("USER", user);
//         try {
//             Process process = pb.start();
//             InputStream in = process.getInputStream();
//             int c;
//             while((c = in.read()) != -1){
//                 System.out.print((char) c);
//             }
//             int exitVal = process.waitFor();
//         } catch (IOException e){
//             //Do Something
//         } catch (InterruptedException e){
//             //Do something
//         }