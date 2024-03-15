public class EXP00_J {
    public static void main(String[] args){
        int a = 1;
        if (flipSign(a)){
            System.out.println(a);
        }else{
            System.out.println("Error");
        }
    }
    /* Method to flip the sign on an int
     * @param x int to flip
     * @return success code
     */
    public static boolean flipSign(int x){
        int ret = x;
        //Error checking
        try{
            ret *= -1;
            return true;
        } catch(Exception e){
            return false;
        }
    }
}
