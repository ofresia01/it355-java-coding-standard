//A class showing the standard of using braces for bodies of ifs
public class EXP52 {
    public static void main(String[] args){
        int x = 4;
        /*Despite the following statements being single statements
        * The ifs are represented with bracket blocks
        */
        if (x % 2 == 0){
            System.out.println("Even");
        }
        if (x < 10){
            System.out.println("x<10");
        }
    }
}
