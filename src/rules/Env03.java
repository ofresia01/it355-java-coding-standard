import java.security.Permission;
import java.security.Permissions;
import java.io.FilePermission;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Env03 Class containing only main to demonstrate Permissions
 */
public class Env03{
    /**
     * main method
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args){
        //Only grant the permissions required, Do not go overboard and grant everything
        Permissions perms = new Permissions();
        perms.add(new FilePermission("/temp/*", "read"));
        perms.add(new FilePermission("/temp1/*", "write"));

        try {
            //Simple copy info from 1 file to the other
            File read = new File("temp/read.txt");
            File write = new File("temp1/write.txt");

            Scanner reader = new Scanner(read);
            FileWriter writer = new FileWriter(write);
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                writer.write(line);
            }
            writer.close();
            reader.close();

        } catch (IOException e) {
            System.out.println("Error has occured");
            e.printStackTrace();
        }

    }
}