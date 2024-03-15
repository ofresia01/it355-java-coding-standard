public class NUM14_J {
    public static void main(String[] args){
            int x = halve(4);
            System.out.println(x);
            
    }
    /*Halves the provided int
     * @param num number to havle
     * @return halved number
     */
    public static int halve(int num){
        return num >>> 1;
        
    }
}
