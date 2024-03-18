public class Env06{

    /**
     * Identification class with id information
     */
    static class Identification{

        private static boolean DEBUG = false;
        private String name;
        private String title;
        private String ssn;
        private int age;

        /**
         * Constructor for Indentification class
         * 
         * @param name
         * @param title
         * @param ssn
         * @param age
         * 
         */
        public Identification(String name, String title, String ssn, int age){
            this.name = name;
            this.title = title;
            this.ssn = ssn;
            this.age = age;
        }

        /**
         * Getter for name
         * 
         * @return this.name name of the Identification object
         */
        public String getName(){
            return this.name;
        }

        /**
         * Setter for Name
         * 
         * @param name New name value
         */
        public void setName(String name){
            this.name = name;
        }

        /**
         * Getter for title
         * 
         * @return title of object
         */
        public String getTitle(){
            return this.title;
        }

        /**
         * Setter for title 
         * 
         * @param title New title value
         */
        public void setTitle(String title){
            this.title = title;
        }

        /**
         * Getter for Age
         * @return age of object
         */
        public int getAge(){
            return this.age;
        }

        /**
         * Method to increase age of object by 1
         */
        public void birthday(){
            this.age++;
        }

        /**
         * Method to get SSN of object is allowed
         * 
         * @param password
         * @return SSN of object or Access not allowed
         */
        public String getSSN(String password){
            if(password.equals("12345") || DEBUG == true){
                return this.ssn;
            } else {
                return "Access not allowed";
            }
        }

        // public static void main() {
        //     Identification.DEBUG = true;
        //     //Debug Methods
        //     System.out.println("Debugging");
        // }


    }

    /**
     * Main method
     * 
     * @param args
     */
    public static void main(String[] args) {
        //Could Call id.main(); and get Access I shouldnt if it was included
        try {
            //If the below line ever works, main is present and rule violated
            // Identification.main();
            System.out.println("Do not include a main method in the Identification class");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}