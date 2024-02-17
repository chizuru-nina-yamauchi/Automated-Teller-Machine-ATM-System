package transactions;

import users.User;

public class TransferTransaction implements Transaction{
    // Attributes
    private User authenticatedUser;
    private User recipientUser;

    private double transferAmount;

    // Constructor
    public TransferTransaction(User authenticatedUser, User recipientUser, double transferAmount){
        this.authenticatedUser = authenticatedUser;
        this.recipientUser = recipientUser;
        this.transferAmount = transferAmount;
    }


    @Override
    public void execute(){
        // code for transfer transaction
        // Validate the transfer amount(ensure it is not negative)
        if (transferAmount <= 0) {
            System.out.println("Transfer amount is negative. Please enter the positive transfer amount.");
            return;
        }
        // Update the user's account balance
        double newBalanceForAuthenticatedUser = authenticatedUser.getAccountBalance() - transferAmount;
        authenticatedUser.setAccountBalance(newBalanceForAuthenticatedUser);

        double newBalanceForRecipientUser = recipientUser.getAccountBalance() + transferAmount;
        recipientUser.setAccountBalance(newBalanceForRecipientUser);

        // Display a conformation message
        System.out.println("Transfer successful. The money has sent to " + recipientUser + "\n The amount: " + transferAmount + ".\n" + "Your new balance is: " + newBalanceForAuthenticatedUser);
    }
}
