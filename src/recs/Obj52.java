import java.util.ArrayList;
import java.util.List;

/**
 * Obj52 class
 */
public class Obj52 {

    /**
     * Name Class
     */
    static class Name{
        String first = "";
        String last = "";

        /**
         * Constructor for Name
         * 
         * @param firstName
         * @param lastName
         */
        public Name(String firstName, String lastName){
            this.first = firstName;
            this.last = lastName;
        }

        /**
         * Getter for Last Name
         * 
         * @return last of Name
         */
        public String getLast(){
            return this.last;
        }
    }

    /**
     * Class Age
     */
    static class Age {
        private int age = 0;
        private String month = "";

        /**
         * Constructor for Age Class
         * 
         * @param age
         * @param birthMonth
         */
        public Age(int age, String birthMonth){
            this.age = age;
            this.month = birthMonth;
        }

        /**
         * Getter for age
         * 
         * @return age value
         */
        public int getAge(){
            return this.age;
        }
    }

    /**
     * Main Method of Obj54 class
     * 
     * @param args
     */
    public static void main(String[] args) {
        List<Name> names = new ArrayList<>();
        List<Age> ages = new ArrayList<>();

        Name drew = new Name("Drew", "Casey");
        names.add(drew);
        Name colin = new Name("Colin", "Casey");
        names.add(colin);

        Age drewAge = new Age(24, "March");
        ages.add(drewAge);
        Age colinAge = new Age(26, "April");
        ages.add(colinAge);

        //Use Names
        System.out.println("Drew: " + names.get(0).getLast());
        //Clear Names if done with it so garbage can clean it up
        names.clear();

        //Use Ages
        int mean = 0;
        for(Age a : ages){
            mean += a.getAge();
        }
        mean = mean / ages.size();
        System.out.println("Mean Age: " + mean);

        ages.clear();

        //Other Operations later
    }
}
