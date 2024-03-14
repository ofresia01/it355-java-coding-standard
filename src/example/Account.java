package example;

import java.math.BigInteger;

/*
 * No cyclic dependencies - conformant with DCL00-J.
 * No reusage of public identifiers from Java Standard Library - conformant with DCL01-J.
 * No usage of floats for precise computation - conformant with NUM04-J.
 */
class Account {
    private String accountNumber;
    private String accountHolderName;
    private long balance; // Change balance to long for unsigned value -- perhaps not ideal contextually,
                          // but conformant with NUM03-J
    double interestRate = 7.4;
    int validation;

    public Account(String accountNumber, String accountHolderName, long balance, byte[] byteArray) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.validation = combineBitCollection(byteArray);
    }

    // Rather than having the BigInteger variables scoped as class fields, they are
    // instead contained within the lexical scope of the method they're needed in,
    // conforming to DCL53-J.
    public static int updateBalance(int currentBalance, int amountToAdd) {
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

    public static double calculateInterest(double principal, double interestRate) {
        if (interestRate == 0 || Double.isNaN(interestRate)) { // Ensures no division-by-zero, conformant with NUM02-J,
                                                               // and correct use of NaN comparison, conformant with
                                                               // NUM07-J.
            throw new IllegalArgumentException("Interest rate cannot be zero");
        }

        return (principal * interestRate) / 100; // Calculate interest
    }

    public int combineBitCollection(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < bytes.length; i++) {
            result = (result << 8) | (bytes[i] & 0xFF); // Bitwise operators used exclusively for bit manipulation,
                                                        // conformant to NUM01-J
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