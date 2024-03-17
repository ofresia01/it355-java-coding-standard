import java.io.FileWriter;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

//Privileged Class
public class Env01{
    /**
     * Method to perform a privileged operation
     * 
     * @throws IOException
     */
    public static void priviledgedOperation() throws IOException{
        try{
            AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                public Void run() throws IOException {
                    FileWriter sensitiveFile = new FileWriter("env01.txt", true);

                    SecSensClass ssc = new SecSensClass();

                    if(ssc.getPassKey().equals("key")){
                        sensitiveFile.write("New information\n");
                        System.out.println("Writing Info");
                    } else {
                        throw new IOException("Key Mismatch");
                    }

                    return null;
                }
            });
        } catch (PrivilegedActionException e){
            //Handle Privilege exception
        }
    }

    public static void main(String[] args) throws IOException{
        Env01 env = new Env01();
        Env01.priviledgedOperation();
    }

}

//Should be present in the same jar file as the above class
class SecSensClass {
    private String passkey = "key";

    public String getPassKey(){
        return this.passkey;
    }
}