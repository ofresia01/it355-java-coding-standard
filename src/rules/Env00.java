public class Env00{
    public static int num = 0;
    public static String sent = "No sentence added";

    /**
     * Constructor for Env00
     * @param sentence
     * @param number
     */
    public Env00(String sentence, int number){
        this.num = number;
        this.sent = sentence;
    }

    /**
     * Method to print out sent value of object
     */
    public static void saySentence(){
        System.out.println(sent);
    }

    /**
     * Method to double the currently stored number
     * 
     * @return new stored number value
     */
    public int doubleNumber(){
        this.num = this.num*2;
        System.out.println("Number doubled: " + this.num);
        return this.num;
    }

    /**
     * Main Method for Env00
     * @param args
     */
    public static void main(String[] args){
        Env00 env = new Env00("Hello World", 10);
        env.saySentence();
        int number = env.doubleNumber();
    }
}