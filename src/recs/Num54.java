public class Num54{

    /**
     * Main Method for Num54 class
     * 
     * @param args
     */
    public static void main(String[] args){
        //Bad Code
        float bad = 1.0f/3.0f;
        System.out.println("Original Number: " + bad);
        //Denormalize it
        bad = (float)(bad * 5e-40);
        System.out.println("Denormalized Number: " + bad);
        //Restore it
        bad = (float)(bad / 5e-40);
        System.out.println("Restored Number: " + bad);

        // Compliant Code
        double good = 1.0/3.0;
        System.out.println("Original Number: " + good);
        //Prevent denormalization
        good = good * 5e-40;
        System.out.println("Denormalized Number: " + good);
        //Restore it
        good = good / 5e-40;
        System.out.println("Restored Number: " + good);
    
    }
}

