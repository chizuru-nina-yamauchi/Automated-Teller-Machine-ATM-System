package Transaction;

public class DepositTransaction implements Transaction{
    @Override
    public void execute(){
        // code for deposit transaction
        System.out.println("Executing Deposit Transaction");
    }
}
