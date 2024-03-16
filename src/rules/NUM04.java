/*
 * Author: Owen Fresia
 * The following demonstrates compliancy with Java rule "NUM04-J. Do not use floating-point numbers if precise computation is required", from Carnegie Mellon's Software Engineering Institute.
 * https://wiki.sei.cmu.edu/confluence/display/java/NUM04-J.+Do+not+use+floating-point+numbers+if+precise+computation+is+required.
 */
import java.math.BigDecimal;

/**
 * Class that demonstrates compliant solutions for using an integer type to
 * represent currency in cents and using `BigDecimal` for precise currency
 * calculations, as outlined by NUM04-J.
 */
public class NUM04J {
        /**
         * Main method to demonstrate currency calculations.
         *
         * @param args Command-line arguments (not used here).
         */
        public static void main(String[] args) {
                int number = 7;
                // Compliant Solution using integer type (cents)
                int dollarCents = 100;
                int dimeCents = 10;
                System.out.println(
                                "Compliant: A dollar less " + number + " dimes is $"
                                                + (dollarCents - number * dimeCents) / 100.0);

                // Compliant Solution using BigDecimal
                BigDecimal dollarBD = new BigDecimal("1.00");
                BigDecimal dimeBD = new BigDecimal("0.10");
                BigDecimal result = dollarBD.subtract(dimeBD.multiply(BigDecimal.valueOf(number)));
                System.out.println(
                                "Compliant: A dollar less " + number + " dimes is $" + result);
        }
}