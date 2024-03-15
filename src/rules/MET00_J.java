public class MET00_J {
    /* returns month string based on numeric qualifier
     * @param num numerical month value
     * @return month as strtng
     */
    public static String getMonthByNum(int num){
        // Proper validation of input data
        if (num < 1 || num > 12){
            return null;
        }
       switch (num){
        case (1):
        return "January";
        /*
         * continued throuigh december
         */
       }
       return null;

    }
}
