public class NUM07J {
    /*
     * Noncompliant Code Example
     * This noncompliant code example attempts a direct comparison with NaN. In
     * accordance with the semantics of NaN, all comparisons with NaN yield false
     * (with the exception of the != operator, which returns true). Consequently,
     * this comparison always return false, and the "result is NaN" message is never
     * printed.
     */
    public class NaNComparison {
        public static void main(String[] args) {
            double x = 0.0;
            double result = Math.cos(1 / x); // Returns NaN if input is infinity
            if (result == Double.NaN) { // Comparison is always false!
                System.out.println("result is NaN");
            }
        }
    }

    /*
     * Compliant Solution
     * This compliant solution uses the method Double.isNaN() to check whether the
     * expression corresponds to a NaN value:
     */
    public class NaNComparisonCom {
        public static void main(String[] args) {
            double x = 0.0;
            double result = Math.cos(1 / x); // Returns NaN when input is infinity
            if (Double.isNaN(result)) {
                System.out.println("result is NaN");
            }
        }
    }
}