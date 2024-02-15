package Transaction;

public class WithdrawalTransaction implements Transaction{
    @Override
    public void execute(){
        // code for withdrawal transaction
        System.out.println("Executing Withdrawal Transaction");
    }
}
