public class NUM02J {
    public static void main(String[] args) {
        /*
         * Noncompliant Code Example (Division)
         * The result of the / operator is the quotient from the division of the first
         * arithmetic operand by the second arithmetic operand. Division operations are
         * susceptible to divide-by-zero errors. Overflow can also occur during
         * two's-complement signed integer division when the dividend is equal to the
         * minimum (negative) value for the signed integer type and the divisor is equal
         * to −1 (see NUM00-J. Detect or prevent integer overflow for more information).
         * This noncompliant code example can result in a divide-by-zero error during
         * the division of the signed operands num1 and num2:
         */
        long num1, num2, result;
        /* Initialize num1 and num2 */
        result = num1 / num2;

        /*
         * Compliant Solution (Division)
         * This compliant solution tests the divisor to guarantee there is no
         * possibility of divide-by-zero errors:
         */

        long num1Com, num2Com, resultCom;
        /* Initialize num1 and num2 */
        if (num2Com == 0) {
            // Handle error
        } else {
            resultCom = num1Com / num2Com;
        }

        /*
         * Noncompliant Code Example (Remainder)
         * The % operator provides the remainder when two operands of integer type are
         * divided. This noncompliant code example can result in a divide-by-zero error
         * during the remainder operation on the signed operands num1 and num2:
         */

        long num1Rem, num2Rem, resultRem;

        /* Initialize num1 and num2 */

        resultRem = num1Rem % num2Rem;

        /*
         * Compliant Solution (Remainder)
         * This compliant solution tests the divisor to guarantee there is no
         * possibility of a divide-by-zero error:
         */
        long num1RemCom, num2RemCom, resultRemCom;

        /* Initialize num1 and num2 */

        if (num2RemCom == 0) {
            // Handle error
        } else {
            resultRemCom = num1RemCom % num2RemCom;
        }
    }
}