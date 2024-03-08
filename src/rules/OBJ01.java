/**
 * Represents a simple bank account.
 */
class BankAccount {
    private double balance; // not accessible from outside
    public BankAccount() {
        this.balance = 0;
    }

    /**
     * Deposit the specified amount into the account balance.
     * @param  amount   the amount to be deposited
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount; 
    }

    /**
     * Withdraws a specified amount from the account.
     * @param amount Amount to withdraw (must be positive and within balance).
     * @throws IllegalArgumentException If amount is invalid.
     */
    public void withdraw(double amount) {
        if (amount <= 0 || amount > balance) { 
            throw new IllegalArgumentException("Invalid withdrawal amount.");
        }
        balance -= amount;
    }

    /**
     * Gets the current balance of the bank account.
     * @return The account balance.
     */
    public double getBalance() {
        return balance;
    }
}

public class OBJ01 {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount();
        // There are no setters for balance, so users are forced to use deposit/withdraw methods
        myAccount.deposit(100.0);

        System.out.println("Balance after deposit: $" + myAccount.getBalance());

        try {
            myAccount.withdraw(50.0);
            System.out.println("Balance after withdrawal: $" + myAccount.getBalance()); 
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
