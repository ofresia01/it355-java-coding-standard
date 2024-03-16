/*
 * Author: Owen Fresia
 * The following demonstrates compliancy with Java rule "DCL02-J. Do not modify the collection's elements during an enhanced for statement", from Carnegie Mellon's Software Engineering Institute.
 * https://wiki.sei.cmu.edu/confluence/display/java/DCL02-J.+Do+not+modify+the+collection%27s+elements+during+an+enhanced+for+statement.
 */
import java.util.ArrayList;
import java.util.List;

public class DCL02J {
    private static final DCL02J rule = new DCL02J();

    /**
     * Represents a banking system where transactions are processed. In this
     * example, the `processTransactions` method takes a list of transactions as
     * input and process them. We ensure compliance by not modifying elements of the
     * `transactions` collection within the enhanced for-loop.
     */
    public class BankingSystem {
        /**
         * Represents a banking transaction.
         */
        public class Transaction {
            private final String transactionId;
            private final double amount;

            /**
             * Constructs a Transaction object with a unique transaction ID and an amount.
             * 
             * @param transactionId The unique transaction ID.
             * @param amount        The amount of the transaction.
             */
            public Transaction(String transactionId, double amount) {
                this.transactionId = transactionId;
                this.amount = amount;
            }

            /**
             * Retrieves the transaction ID.
             * 
             * @return The transaction ID.
             */
            public String getTransactionId() {
                return transactionId;
            }

            /**
             * Retrieves the amount of the transaction.
             * 
             * @return The amount of the transaction.
             */
            public double getAmount() {
                return amount;
            }
        }

        /**
         * Processes a list of transactions.
         * 
         * @param transactions The list of transactions to be processed.
         */
        public void processTransactions(List<Transaction> transactions) {
            boolean first = true;

            System.out.println("Processing transactions...");
            List<Transaction> modifiedTransactions = new ArrayList<>();

            for (final Transaction transaction : transactions) {
                Transaction modifiedTransaction = transaction;
                if (first) {
                    first = false;
                    // Modifying the first transaction
                    modifiedTransaction = new Transaction("999999999", 1000.0);
                }
                System.out.println("New transaction: " + modifiedTransaction);
                modifiedTransactions.add(modifiedTransaction);
                // Process modifiedTransaction
            }

            System.out.println("Modified transaction list:");
            for (Transaction transaction : modifiedTransactions) {
                System.out.println("Transaction: " + transaction);
            }
        }

        /**
         * Main method to demonstrate processing transactions.
         * 
         * @param args Command-line arguments (unused).
         */
        public static void main(String[] args) {
            BankingSystem bankingSystem = rule.new BankingSystem();

            // Creating a list of transactions
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(bankingSystem.new Transaction("123456789", 500.0));
            transactions.add(bankingSystem.new Transaction("987654321", 750.0));

            // Processing transactions
            bankingSystem.processTransactions(transactions);
        }
    }
}