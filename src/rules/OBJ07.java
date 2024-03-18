//A class demonstrating securing object by not allowing cloning
public class OBJ07 {
      
    public static class someObject{
        public someObject(){

        }
        public final someObject clone() throws CloneNotSupportedException{
            throw new CloneNotSupportedException();
        }
    }
    // The attempt to clone "newObj" will result in an exception
    public static void main(String[] args) throws CloneNotSupportedException{
        someObject newObj = new someObject();
        someObject clonedObj = newObj.clone();

    }
}
