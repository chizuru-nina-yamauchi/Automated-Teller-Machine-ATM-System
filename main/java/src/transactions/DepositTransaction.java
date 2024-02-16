package transactions;
import users.User;



public class DepositTransaction implements Transaction{
    // Attributes
    private User authenticatedUser;
    private double depositAmount;

    // Constructor to initialize the authenticated user and deposit amount
    public DepositTransaction(User authenticatedUser, double depositAmount){
        this.authenticatedUser = authenticatedUser;
        this.depositAmount = depositAmount;
    }

    @Override
    public void execute(){
            // code for deposit transaction
            // Validate the deposit amount(ensure it is not negative)
            if (depositAmount <= 0) {
                System.out.println("Deposit amount is negative. Please enter the positive deposit amount.");
                return;
            }
            // Update the user's account balance
            double newBalance = authenticatedUser.getAccountBalance() + depositAmount;
            authenticatedUser.setAccountBalance(newBalance);

            // Display a conformation message
            System.out.println("Deposit successful. Your new balance is: " + newBalance);

    }
}
