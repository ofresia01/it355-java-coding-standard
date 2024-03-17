import java.io.File;
import java.io.IOException;

public class Fio02{

    /**
     * Main Method for Fio02 class
     * 
     * @param args
     */
    public static void main(String[] args){
        //Open a file
        try {
            //Create file object
            File file = new File("FIO02-JExample.txt");

            //Create physical file
            if (file.createNewFile()){
                System.out.println("File made successfully\n");
            } else {
                System.out.println("File could not be created\n");
            }

            //Delete File
            if(!file.delete()){
                //Handle Failure
                System.out.println("File could not be deleted\n");
                System.exit(1);
            }

        } catch (IOException e) {
            System.out.println("File could not be opened due to " + e.getMessage() + "\n");
            System.exit(1);
        }

        System.out.println("File successfully created and deleted\n");
    }
}
