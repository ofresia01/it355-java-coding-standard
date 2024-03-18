public class OBJ07 {
      
    public static class someObject{
        public someObject(){

        }
        public final someObject clone() throws CloneNotSupportedException{
            throw new CloneNotSupportedException();
        }
    }
    
    public static void main(String[] args) throws CloneNotSupportedException{
        someObject newObj = new someObject();
        someObject clonedObj = newObj.clone();

    }
}
