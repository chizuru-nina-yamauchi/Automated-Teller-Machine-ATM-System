package transactions;

import users.User;

public class WithdrawalTransaction implements Transaction{
    // Attributes
    private final User authenticatedUser;
    private final double withdrawalAmount;

    // Constructor to initialize the authenticated user and deposit amount
    public WithdrawalTransaction(User authenticatedUser, double withdrawalAmount){
        this.authenticatedUser = authenticatedUser;
        this.withdrawalAmount = withdrawalAmount;
    }

    @Override
    public void execute(){
        // code for withdrawal transaction
        // Validate the withdrawal amount(ensure it is not negative)
        if(withdrawalAmount <= 0){
            System.out.println("Withdrawal amount is invalid. Please enter the positive withdrawal amount.");
            return;
        }
        // Update the user's account balance
        double newBalance = authenticatedUser.getAccountBalance() - withdrawalAmount;
        authenticatedUser.setAccountBalance(newBalance);


        // Display a conformation message
        System.out.println("Withdrawal successful. Your new balance is: " + newBalance);
    }
}
