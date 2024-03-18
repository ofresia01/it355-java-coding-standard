//A class showing the proper usage of logical over bitwise operators
public class EXP54 { 
    public static void main(String[] args){
        int[] x = {1,2,3,4,5};
        // The verification the object exists is checked before attempting 
        //to access its methods and using an && operator
        if (x != null && x[0] == 1){
            System.out.println("x is not null");
        }
    }
}
