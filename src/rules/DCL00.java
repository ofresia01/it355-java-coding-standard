/*
 * Author: Owen Fresia
 * The following demonstrates compliancy with Java rule "DCL00-J. Prevent class initialization cycles", from Carnegie Mellon's Software Engineering Institute.
 * https://wiki.sei.cmu.edu/confluence/display/java/DCL00-J.+Prevent+class+initialization+cycles.
 */
public class DCL00 {
  private static final DCL00 rule = new DCL00();

  /**
   * The following is an example scenario in which we have an `Account` class with
   * a `balance` fild that is initialized with a default value. A
   * `generateAccountMethod()` method is used to simulate the generation of a
   * random account number. The structure of this class ensures no cyclic
   * dependencies exist during the initialization of classes.
   *
   */
  public class Account {
    private final int accountNumber;
    private final double balance;
    private static final double DEFAULT_BALANCE = 100.0; // Default balance

    /**
     * Constructs an Account object with a random account number and a default
     * balance.
     */
    public Account() {
      this.accountNumber = generateAccountNumber();
      this.balance = DEFAULT_BALANCE;
    }

    /**
     * Generates a random account number.
     * 
     * @return The randomly generated account number.
     */
    private int generateAccountNumber() {
      // Simulated method for generating account numbers
      return (int) (Math.random() * 100000);
    }

    /**
     * Gets the account number.
     * 
     * @return The account number.
     */
    public int getAccountNumber() {
      return accountNumber;
    }

    /**
     * Gets the current balance of the account.
     * 
     * @return The balance of the account.
     */
    public double getBalance() {
      return balance;
    }

    /**
     * Main method to demonstrate account creation and balance retrieval.
     * 
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
      Account account = rule.new Account();
      System.out.println("Account Number: " + account.getAccountNumber());
      System.out.println("Balance: $" + account.getBalance());
    }
  }
}