public class NUM02J {
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
    result=num1/num2;

    /*
     * Compliant Solution (Division)
     * This compliant solution tests the divisor to guarantee there is no
     * possibility of divide-by-zero errors:
     */

    long num1Com, num2Com, resultCom;
    /* Initialize num1 and num2 */
    if(num2==0)
    {
        // Handle error
    }else
    {
        result = num1 / num2;
    }
}