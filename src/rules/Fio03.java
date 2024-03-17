import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Fio03 class to demostrate removing temporary files after use
 */
public class Fio03{

    /**
     * Main Method for Fio03 class
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args){
        String tempInfo = "Temporary";
        File file = new File("Fio03Temp.txt");
        try{
            FileWriter writer = new FileWriter(file);
            writer.write(tempInfo);
            writer.close();
        } catch (IOException e){
            //Error case
        }
        file.delete();
    }
}