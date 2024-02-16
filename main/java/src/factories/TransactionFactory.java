package factories;

import transactions.DepositTransaction;
import transactions.Transaction;
import transactions.TransferTransaction;
import transactions.WithdrawalTransaction;
import users.User;

// Following the Factory Method Pattern
public class TransactionFactory {
    // Method that returns different Transaction objects based on the transaction type
    public Transaction createTransaction(String transactionType, User loggedinUser, double transactionAmount){
        switch(transactionType.toLowerCase()){ // Use 'toLowerCase()' to avoid the error because of Capital or lower case issues
            case "deposit":
                return new DepositTransaction(loggedinUser, transactionAmount);
                /* If transactionType is "deposit",
                 * it creates a new instance of DepositTransaction using return new DepositTransaction();.
                 * The created transaction object is then returned to the calling code
                 */
            case "withdrawal":
                return new WithdrawalTransaction();
                /* If transactionType is "withdrawal",
                 * it creates a new instance of WithdrawalTransaction using return new WithdrawalTransaction();.
                 * The created transaction object is then returned to the calling code
                 */
            case "transfer":
                return new TransferTransaction();
                /* If transactionType is "transfer",
                 * it creates a new instance of TransferTransaction using return new TransferTransaction();.
                 * The created transaction object is then returned to the calling code
                 */
            default:
                throw new IllegalArgumentException("Invalid transaction type: " + transactionType);
        }
    }
}
