/*
 * Author: Owen Fresia
 * The following demonstrates compliancy with Java rule "DCL01-J. Do not reuse public identifiers from the Java Standard Library", from Carnegie Mellon's Software Engineering Institute.
 * https://wiki.sei.cmu.edu/confluence/display/java/DCL01-J.+Do+not+reuse+public+identifiers+from+the+Java+Standard+Library.
 */
public class DCL01 {
    private static final DCL01 rule = new DCL01();

    /**
     * The following is a transaction class that demonstrates a contextualized
     * example of DCL01-J, in which no conflicting class names are used.
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

        /**
         * Main method to demonstrate transaction creation and retrieval.
         * 
         * @param args Command-line arguments (unused).
         */
        public static void main(String[] args) {
            Transaction transaction = rule.new Transaction("123456789", 500.0);
            System.out.println("Transaction ID: " + transaction.getTransactionId());
            System.out.println("Amount: $" + transaction.getAmount());
        }
    }
}