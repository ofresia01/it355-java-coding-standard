import java.util.Arrays;

public class EXP02_J {
    public static void main(String[] args){
        int[] a = {1,2,3};
        int[] b = {1,2,3};

        System.out.println(compareArrayContents(a, b));

    }
    
    /*
     * Compares two arrays based on elements
     * @param array1 first array to compare
     * @param array2 second array to compare
     * @return equivalency in boolean form
     */
    public static boolean compareArrayContents(int[] array1, int[] array2){
        return Arrays.equals(array1, array2);
    }
}
