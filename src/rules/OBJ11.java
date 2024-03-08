class BankAccount {
    private final String accountHolderName;
    private double balance;

    public BankAccount(String accountHolderName, double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    /**
     * Deposits an amount into the account.
     * @param amount Amount to deposit (must be positive).
     * @throws IllegalArgumentException If deposit amount is invalid.
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
    }

    /**
     * Withdraws an amount from the account.
     * @param amount Amount to withdraw (must be positive and within balance).
     * @throws IllegalArgumentException If withdrawal amount is invalid.
     */
    public void withdraw(double amount) {
        if (amount <= 0 || amount > balance) { 
            throw new IllegalArgumentException("Invalid withdrawal amount.");
        }
        balance -= amount;
    }

    /**
     * Gets the current balance.
     * @return The account balance.
     */
    public double getBalance() {
        return balance;
    }

     /**
     * Gets the account holder's name.
     * @return The account holder's name.
     */
    public String getAccountHolderName() {
        return accountHolderName;
    }
}

public class OBJ11 {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount("John Doe", 500.0);

        System.out.println("Account Holder: " + myAccount.getAccountHolderName());
        System.out.println("Initial Balance: $" + myAccount.getBalance());

        myAccount.deposit(200.0);
        myAccount.withdraw(50.0);

        System.out.println("Final Balance: $" + myAccount.getBalance());
    }
}