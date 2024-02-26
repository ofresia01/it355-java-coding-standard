import java.math.BigDecimal;

public class NUM04J {
    public static void main(String[] args) {
        /*
         * Noncompliant Code Example
         * This noncompliant code example performs some basic currency calculations:
         */
        double dollar = 1.00;
        double dime = 0.10;
        int number = 7;
        System.out.println(
                "A dollar less " + number + " dimes is $" + (dollar - number * dime));

        /*
         * Compliant Solution
         * This compliant solution uses an integer type (such as int) and works with
         * cents rather than dollars:
         */
        int dollarCom = 100;
        int dimeCom = 10;
        int numberCom = 7;
        System.out.println(
                "A dollar less " + numberCom + " dimes is $0." + (dollarCom - numberCom * dimeCom));

        /*
         * Compliant Solution
         * This compliant solution uses the BigDecimal type, which provides exact
         * representation of decimal values. Note that on most platforms, computations
         * performed using BigDecimal are less efficient than those performed using
         * primitive types.
         */

        BigDecimal dollarComV2 = new BigDecimal("1.0");
        BigDecimal dimeComV2 = new BigDecimal("0.1");
        int numberComV2 = 7;
        System.out.println("A dollar less " + numberComV2 + " dimes is $" +
                (dollarComV2.subtract(new BigDecimal(numberComV2).multiply(dimeComV2))));
    }
}