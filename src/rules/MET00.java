public class MET00 {
    /* returns month name string based on numeric identifier
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
         * continued through december
         */
       }
       return null;

    }
}
