//A class showing the proper usage of abstract equality over reference equality
public class EXP50 { 
    public static void main(String[] args){
        Object rand1 = new Object();
        Object rand2 = new Object();
        // Use default or overriden .equals method over == operator
        System.out.println(rand1.equals(rand2));
    }
}
