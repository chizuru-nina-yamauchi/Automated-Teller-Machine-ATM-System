package atm_application;

import factories.TransactionFactory;
import file.FileManager;
import transactions.Transaction;

import java.util.Scanner;

import users.User;

// atm_application.Main class = ATM class
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        FileManager fileManager = FileManager.getInstance(); // singleton pattern
        TransactionFactory transactionFactory = new TransactionFactory();

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
                            System.out.println("Current balance: $ " + fileManager.getBalance(username));
                            break;
                        case 2:
                            System.out.println("Enter the amount you want to deposit");
                            String depositAmountInput = input.nextLine();
                            double depositAmount = Double.parseDouble(depositAmountInput);

                            String userInputToDepositMoney;
                            Transaction depositTransaction = null;
                            do {
                                System.out.println("Enter 'deposit' to deposit the money");
                                userInputToDepositMoney = input.nextLine();
                                try {
                                    depositTransaction = transactionFactory.createTransaction(userInputToDepositMoney, authenticatedUser, depositAmount);
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            }while(depositTransaction == null);

                            depositTransaction.execute();

                            // Update the user account balance in the user_record.txt file
                            fileManager.updateBalance(username, authenticatedUser.getAccountBalance());

                            break;
                        case 3:
                            System.out.println("Enter the amount you want to withdraw");
                            String withdrawalAmountInput = input.nextLine();
                            double withdrawalAmount = Double.parseDouble(withdrawalAmountInput);

                            String userInputToWithdrawMoney;
                            Transaction withdrawalTransaction = null;
                            do {
                                System.out.println("Enter 'withdrawal' to withdraw the money");
                                userInputToWithdrawMoney = input.nextLine();
                                try {
                                    withdrawalTransaction = transactionFactory.createTransaction(userInputToWithdrawMoney, authenticatedUser, withdrawalAmount);
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            }while(withdrawalTransaction == null);

                            withdrawalTransaction.execute();

                            // Update the user account balance in the user_record.txt file
                            fileManager.updateBalance(username, authenticatedUser.getAccountBalance());

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








