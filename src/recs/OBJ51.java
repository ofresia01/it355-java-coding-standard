// Imagine this is part of a package related to bank transactions
class Account {
    private double balance; // Balance should NEVER be directly accessible 

    public Account(double initialBalance) {
        if (initialBalance >= 0.0) {
            this.balance = initialBalance;
        } else {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
    }
    public double getBalance() {
        return balance;
    }

    /**
     * A method to deposit a positive amount to the balance.
     *
     * @param  amount  the amount to be deposited
     */
    public void deposit(double amount) {
        if (amount > 0.0) {
            balance += amount;
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
    }

    /**
     * Withdraws the specified amount from the balance.
     *
     * @param  amount   the amount to be withdrawn
     */
    public void withdraw(double amount) {
        if (amount > 0.0 && amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }
    }
}

public class OBJ51 {
    public static void main(String[] args) {
        Account myAccount = new Account(1000.0);

        System.out.println("Initial balance: " + myAccount.getBalance());
        myAccount.deposit(250.0);
        System.out.println("Balance after deposit: " + myAccount.getBalance());
    }
}
