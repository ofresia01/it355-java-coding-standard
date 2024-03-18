import java.security.SecureRandom;
// A class utilizng secure random numbers rather than the Java random class
public class MSC02 {
    public static void main(String[] args){
        
        System.out.println("Key value:" + getKey(1000));
    }
    /*
     * generates a secure reandom number within a certain range
     * @param range range within which the key should be generated
     * @return a secure random number
     */
    public static int getKey(int range){
        SecureRandom key = new SecureRandom();
        return key.nextInt(range);
    }
}
