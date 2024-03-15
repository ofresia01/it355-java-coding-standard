import java.util.Locale;
public class STR02_J {
    public static void main(String[] args){
        String s1 = "HELLO WORLD";
        System.out.println(lowerCase(s1));
    }
    /*lowercases the string using proper locale
     * @param str string to lowercase
     * @return lowercased string
     */
    public static String lowerCase(String str){
        return str.toLowerCase(Locale.ENGLISH);
    }
}
