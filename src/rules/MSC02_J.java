import java.security.SecureRandom;

public class MSC02_J {
    public static void main(String[] args){
        
        System.out.println("Key value:" + getKey(1000));
    }
    /*
     * generates a secure reandom number within a certain range
     * @param range range within which the key should be generated
     * @return a secure random number
     */
    public static boolean getKey(int range){
        SecureRandom key = new SecureRandom();
        return key.nextInt(range);
    }
}
