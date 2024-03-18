//A class demonstrating comparison of objects by class
public class OBJ09 {
    // THe two classes are compared by class rather than class names
    public static boolean compareClass(Object x, Object y){
        return x.getClass() == y.getClass();
    }
    public static void main(String[] args){
        Object x = new Object();
        Object y = new Object();
        System.out.println(compareClass(x, y));
    }
}
