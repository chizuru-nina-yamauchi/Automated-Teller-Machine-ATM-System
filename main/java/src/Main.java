import java.util.Scanner;

// Main class = ATM class
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        FileManager fileManager = FileManager.getInstance(); // singleton pattern

        System.out.println("Enter the username to login: ");
        String username = input.nextLine();

        System.out.println("Enter the password to login: ");
        String password = input.nextLine();

        User authenticatedUser = fileManager.authenticateUser(username, password);
        if (authenticatedUser != null) {
            System.out.println("Authentication succeeded. Welcome " + username);
            while (true) {
                System.out.println("----------------------------");
                System.out.println("Choose your option: ");
                System.out.println("1. Check current balance.");
                System.out.println("2. Deposit money.");
                System.out.println("3. Withdraw money.");
                System.out.println("4. Transfer money.");
                System.out.println("5. Change PIN");
                System.out.println("6. Exit the system(Log out).");
                System.out.println("----------------------------");

                try {
                    String optionInput = input.nextLine();
                    int option = Integer.parseInt(optionInput);

                    switch (option) {
                        case 1:
                            System.out.println("Current balance: $ " + authenticatedUser.getAccountBalance());
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            System.exit(0);
                            System.out.println("Exiting the system");
                        default:
                            System.out.println("Invalid number. Please choose from 1 to 6.");

                    }


                } catch (NumberFormatException e) {
                    System.out.println("Please enter the valid 'NUMBER'.");
                }
            }


            }else{
                System.out.println("Authentication failed. Invalid username or password. Try again to log in.");
            }
        }

    }








