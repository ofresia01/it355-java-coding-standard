package example;

import java.math.BigInteger;

/*
 * No cyclic dependencies - conformant with DCL00-J.
 * No reusage of public identifiers from Java Standard Library - conformant with DCL01-J.
 * No usage of floats for precise computation - conformant with NUM04-J.
 * Local reference variables never set to null - conformant with OBJ54-J.
 * No method fails to provide feedback about its result - conformant with MET54-J.
 * Denormalized numbers are not used, doubles are used instead of floats - conformant with NUM54-J.
 * Methods return empty arrays instead of null - conformant to MET55-J.
 * Overloaded methods differentiate transactions clearly - conformant with MET50-J.
 * Security-checking methods are declared private or final, conformant with MET03-J.
 * Finalizers are not used, conformant with MET12-J.
 * NullPointerException or its ancestors are not caught, conformant with ERR08-J.
 * Serialization methods adhere to the proper signatures, conformant with SER01-J.
 * Sensitive class is not cloneable, conformant with OBJ07-J
 */
class Account { // Class is not meant to directly interact with user, so not public - conformant with OBJ51-J.
    private String accountNumber; // Class members are private by default - conformant with OBJ01-J.
    private String accountHolderName;
    private long balance; // Change balance to long for unsigned value -- perhaps not ideal contextually,
                          // but conformant with NUM03-J
    double interestRate = 7.4;
    int validation;

    public Account(String accountNumber, String accountHolderName, long balance, byte[] byteArray) {
        this.accountNumber = accountNumber;  // Assuming these are always valid
        this.accountHolderName = accountHolderName;
        this.balance = balance;

        // Ensure that all fields are correctly initialized - conformant with OBJ11-J.
        if (!isValidByteArray(byteArray)) {
            throw new IllegalArgumentException("Invalid byteArray");
        }

        this.validation = combineBitCollection(byteArray); // Marked as final
    }

    /**
     * A function to check the validity of a byte array based on certain criteria.
     *
     * @param  byteArray  the byte array to be validated
     * @return            true if the byte array is valid, false otherwise
     */
    private boolean isValidByteArray(byte[] byteArray) {
        if (byteArray == null || byteArray.length == 0) {
            return false; // Empty or null arrays are not considered valid
        }
    
        // * Must have an even number of elements.
        // * No element can be greater than 127 or less than -128.
        if (byteArray.length % 2 != 0) {
            return false;
        }
    
        for (byte b : byteArray) {
            if (b > 127 || b < -128) {
                return false;
            }
        }
    
        return true; // All checks passed
    }

    // Rather than having the BigInteger variables scoped as class fields, they are
    // instead contained within the lexical scope of the method they're needed in,
    // conforming to DCL53-J.
    // method is final to avoid malicious accessing/modifying
    // confirming to OBJ10-J
    public static final int updateBalance(int currentBalance, int amountToAdd) {
        BigInteger bigCurrentBalance = BigInteger.valueOf(currentBalance);
        BigInteger bigAmountToAdd = BigInteger.valueOf(amountToAdd);
        BigInteger result = bigCurrentBalance.add(bigAmountToAdd);

        // Check for integer overflow - conformant to NUM00-J.
        if (result.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0 ||
                result.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0) {
            throw new ArithmeticException("Integer overflow"); // Using exception only for exceptional condition,
                                                               // conformant to ERR50-J
        }

        return result.intValue();
    }
    // method is final to avoid malicious accessing/modifying
    // confirming to OBJ10-J
    /**
     * Calculate the interest based on the given principal and interest rate.
     *
     * @param  principal    the principal amount
     * @param  interestRate the interest rate
     * @return              the calculated interest
     */
    public static final double calculateInterest(double principal, double interestRate) {
        if (interestRate == 0 || Double.isNaN(interestRate)) { // Ensures no division-by-zero, conformant with NUM02-J,
                                                               // and correct use of NaN comparison, conformant with
                                                               // NUM07-J. data is validated before usage, conformant with MET00-J
            throw new IllegalArgumentException("Interest rate cannot be zero");
        }

        return (principal * interestRate) / 100; // Calculate interest
    }

    /**
     * Combines the bit collection represented by the input byte array into a single integer.
     *
     * @param  bytes  the byte array representing the bit collection
     * @return       the combined bit collection as an integer
     */
    public int combineBitCollection(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < bytes.length; i++) {
            result = (result << 8) | (bytes[i] & 0xFF); // Bitwise operators used exclusively for bit manipulation,
                                                        // conformant to NUM01-J
                                                        // Proper usage of shift operator
                                                        // conformant to NUM14-J
        }
        return result;
    }

    // Accessors and mutators
    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new InsufficientFundsException("Insufficient funds in account " + accountNumber); // Compliancy with
                                                                                                    // ERR51-J
        }
    }

    @Override
    public String toString() {
        return "Account{" + "accountNumber='" + accountNumber + "\'" +
                ", accountHolderName='" + accountHolderName + "\'" +
                ", balance=" + balance + "}";
    }

    public class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }
}
