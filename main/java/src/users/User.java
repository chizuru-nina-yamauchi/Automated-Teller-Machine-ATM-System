package users;

public class User {
    // Attributes
    private String accountUsername;
    private String accountPassword;
    private double accountBalance;
    // Constructor
    public User(String accountUsername, String accountPassword, double accountBalance){
        this.accountUsername = accountUsername;
        this.accountPassword = accountPassword;
        this.accountBalance = accountBalance;
    }
    // Default constructor
    public User(){

    }
    // Getter and setter

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }


    // toString
    @Override
    public String toString() {
        return "users.User{" +
                "accountUsername='" + accountUsername + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
