//A class demonstrating utilizing ints over floats for iteration
class NUM09{
    public static void main(String[] args){
        float counter = 0.0;
        int x = 0;
        //The counter is swapped for an int before use in iteration
        int i = (int) counter;
        for(i = 0; i < 5; i++){
            x +=i ;
        }
    }
}
