package factories;

import transactions.DepositTransaction;
import transactions.Transaction;
import transactions.TransferTransaction;
import transactions.WithdrawalTransaction;
import users.User;

// Following the Factory Method Pattern
public class TransactionFactory {
    // Method that returns different Transaction objects based on the transaction type
    public Transaction createTransaction(String transactionType, User authenticatedUser, double transactionAmount) {
        switch (transactionType.toLowerCase()) { // Use 'toLowerCase()' to avoid the error because of Capital or lower case issues
            case "deposit":
                return new DepositTransaction(authenticatedUser, transactionAmount);
            /* If transactionType is "deposit",
             * it creates a new instance of DepositTransaction using return new DepositTransaction();.
             * The created transaction object is then returned to the calling code
             */
            case "withdrawal":
                return new WithdrawalTransaction(authenticatedUser, transactionAmount);
            /* If transactionType is "withdrawal",
             * it creates a new instance of WithdrawalTransaction using return new WithdrawalTransaction();.
             * The created transaction object is then returned to the calling code
             */
            default:
                // Throw the exception handling for user input error
                throw new IllegalArgumentException("Invalid transaction type: " + transactionType);
        }

    }
    // Overloaded method for Transfer transaction with a recipient(extra parameter, arguments will be needed)
    public Transaction createTransaction(String transactionType, User authenticatedUser, User recipientUser, double transactionAmount){
        /* If transactionType is "transfer",
         * it creates a new instance of TransferTransaction using return new TransferTransaction();.
         * The created transaction object is then returned to the calling code
         */
        if("transfer".equals(transactionType.toLowerCase())){ // Use 'toLowerCase()' to avoid the error because of Capital or lower case issues
            return new TransferTransaction(authenticatedUser, recipientUser, transactionAmount);
        }else {
            throw new IllegalArgumentException("Invalid transaction type: " + transactionType);
        }
    }

}
