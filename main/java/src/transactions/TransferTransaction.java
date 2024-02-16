package transactions;

public class TransferTransaction implements Transaction{
    @Override
    public void execute(){
        // code for transfer transaction
        System.out.println("Executing Transfer Transaction");
    }
}
