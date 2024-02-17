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
        System.out.println("Executing Transfer Transaction");
    }
}
