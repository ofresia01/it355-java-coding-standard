import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;

public class NUM00J {
    /*
     * Precondition Testing
     * The following code example shows the necessary precondition checks required
     * for each arithmetic operation on arguments of type int. The checks for the
     * other integral types are analogous. These methods throw an exception when an
     * integer overflow would otherwise occur; any other conforming error handling
     * is also acceptable. Since ArithmeticException inherits from RuntimeException,
     * we do not need to declare it in a throws clause.
     */
    static final int safeAdd(int left, int right) {
        if (right > 0 ? left > Integer.MAX_VALUE - right
                : left < Integer.MIN_VALUE - right) {
            throw new ArithmeticException("Integer overflow");
        }
        return left + right;
    }

    static final int safeSubtract(int left, int right) {
        if (right > 0 ? left < Integer.MIN_VALUE + right
                : left > Integer.MAX_VALUE + right) {
            throw new ArithmeticException("Integer overflow");
        }
        return left - right;
    }

    static final int safeMultiply(int left, int right) {
        if (right > 0 ? left > Integer.MAX_VALUE / right
                || left < Integer.MIN_VALUE / right
                : (right < -1 ? left > Integer.MIN_VALUE / right
                        || left < Integer.MAX_VALUE / right
                        : right == -1
                                && left == Integer.MIN_VALUE)) {
            throw new ArithmeticException("Integer overflow");
        }
        return left * right;
    }

    static final int safeDivide(int left, int right) {
        if ((left == Integer.MIN_VALUE) && (right == -1)) {
            throw new ArithmeticException("Integer overflow");
        }
        return left / right;
    }

    static final int safeNegate(int a) {
        if (a == Integer.MIN_VALUE) {
            throw new ArithmeticException("Integer overflow");
        }
        return -a;
    }

    static final int safeAbs(int a) {
        if (a == Integer.MIN_VALUE) {
            throw new ArithmeticException("Integer overflow");
        }
        return Math.abs(a);
    }

    /*
     * Noncompliant Code Example
     * Either operation in this noncompliant code example could result in an
     * overflow. When overflow occurs, the result will be incorrect.
     */
    public static int multAccum(int oldAcc, int newVal, int scale) {
        // May result in overflow
        return oldAcc + (newVal * scale);
    }

    /*
     * Compliant Solution (Precondition Testing)
     * This compliant solution uses the safeAdd() and safeMultiply() methods defined
     * in the "Precondition Testing" section to perform secure integral operations
     * or throw ArithmeticException on overflow:
     */
    public static int multAccumFixed(int oldAcc, int newVal, int scale) {
        return safeAdd(oldAcc, safeMultiply(newVal, scale));
    }

    /*
     * Compliant Solution (Java 8, Math.*Exact())
     * This compliant solution uses the addExact() and multiplyExact() methods
     * defined in the Math class. These methods were added to Java as part of the
     * Java 8 release, and they also either return a mathematically correct value or
     * throw ArithmeticException. The Math class also provides SubtractExact() and
     * negateExact() but does not provide any methods for safe division or absolute
     * value.
     */
    public static int multAccumFixedVar(int oldAcc, int newVal, int scale) {
        return Math.addExact(oldAcc, Math.multiplyExact(newVal, scale));
    }

    /*
     * Compliant Solution (Upcasting)
     * This compliant solution shows the implementation of a method for checking
     * whether a value of type long falls within the representable range of an int
     * using the upcasting technique. The implementations of range checks for the
     * smaller primitive integer types are similar.
     */
    public static long intRangeCheck(long value) {
        if ((value < Integer.MIN_VALUE) || (value > Integer.MAX_VALUE)) {
            throw new ArithmeticException("Integer overflow");
        }
        return value;
    }

    public static int multAccumFixedVar2(int oldAcc, int newVal, int scale) {
        final long res = intRangeCheck(
                ((long) oldAcc) + intRangeCheck((long) newVal * (long) scale));
        return (int) res; // Safe downcast
    }

    /*
     * Compliant Solution (BigInteger)
     * This compliant solution uses the BigInteger technique to detect overflow:
     */
    private static final BigInteger bigMaxInt = BigInteger.valueOf(Integer.MAX_VALUE);
    private static final BigInteger bigMinInt = BigInteger.valueOf(Integer.MIN_VALUE);

    public static BigInteger intRangeCheck(BigInteger val) {
        if (val.compareTo(bigMaxInt) == 1 ||
                val.compareTo(bigMinInt) == -1) {
            throw new ArithmeticException("Integer overflow");
        }
        return val;
    }

    public static int multAccumFixedVar3(int oldAcc, int newVal, int scale) {
        BigInteger product = BigInteger.valueOf(newVal).multiply(BigInteger.valueOf(scale));
        BigInteger res = intRangeCheck(BigInteger.valueOf(oldAcc).add(product));
        return res.intValue(); // Safe conversion
    }

    /*
     * Noncompliant Code Example (AtomicInteger)
     * Operations on objects of type AtomicInteger suffer from the same overflow
     * issues as other integer types. The solutions are generally similar to the
     * solutions already presented; however, concurrency issues add additional
     * complications. First, potential issues with time-of-check, time-of-use
     * (TOCTOU) must be avoided (see VNA02-J. Ensure that compound operations on
     * shared variables are atomic for more information). Second, use of an
     * AtomicInteger creates happens-before relationships between the various
     * threads that access it. Consequently, changes to the number of accesses or
     * order of accesses can alter the execution of the overall program. In such
     * cases, you must either choose to accept the altered execution or carefully
     * craft your implementation to preserve the exact number of accesses and order
     * of accesses to the AtomicInteger.
     * 
     * This noncompliant code example uses an AtomicInteger, which is part of the
     * concurrency utilities. The concurrency utilities lack integer overflow
     * checks.
     */
    class InventoryManager {
        private final AtomicInteger itemsInInventory = new AtomicInteger(100);

        // ...
        public final void nextItem() {
            itemsInInventory.getAndIncrement();
        }
    }

    /*
     * Compliant Solution (AtomicInteger)
     * This compliant solution uses the get() and compareAndSet() methods provided
     * by AtomicInteger to guarantee successful manipulation of the shared value of
     * itemsInInventory. This solution has the following characteristics:
     * 
     * The number and order of accesses to itemsInInventory remain unchanged from
     * the noncompliant code example.
     * All operations on the value of itemsInInventory are performed on a temporary
     * local copy of its value.
     * The overflow check in this example is performed in inline code rather than
     * encapsulated in a method call. This is an acceptable alternative
     * implementation. The choice of method call versus inline code should be made
     * according to your organization's standards and needs.
     */
    class InventoryManagerFixed {
        private final AtomicInteger itemsInInventory = new AtomicInteger(100);

        public final void nextItem() {
            while (true) {
                int old = itemsInInventory.get();
                if (old == Integer.MAX_VALUE) {
                    throw new ArithmeticException("Integer overflow");
                }
                int next = old + 1; // Increment
                if (itemsInInventory.compareAndSet(old, next)) {
                    break;
                }
            } // End while
        } // End nextItem()
    }
}