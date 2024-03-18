public class OBJ09 {
    // THe two classes are compared by reference rather than by checking their class names
    public static boolean compareClass(Object x, Object y){
        return x.getClass() == y.getClass();
    }
}
