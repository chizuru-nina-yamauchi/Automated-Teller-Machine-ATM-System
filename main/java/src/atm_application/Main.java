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

        User authenticatedUser = null;
        String username = null;
        String password = null;

        int maxLoginAttempts = 3;
        int loginAttempts = 0;
        while(authenticatedUser == null && loginAttempts < maxLoginAttempts){

            System.out.println("Enter the username to login: ");
            username = input.nextLine();

            System.out.println("Enter the password to login: ");
            password = input.nextLine();

            authenticatedUser = fileManager.authenticateUser(username, password);
            if (authenticatedUser != null) {
                System.out.println("Authentication succeeded. Welcome " + username);
            }else {
                loginAttempts++;
                System.out.println("Authentication failed. Invalid username or password. \nAttempt left: " + (maxLoginAttempts - loginAttempts));
            }
        }
        if(authenticatedUser != null){
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
                            } while (depositTransaction == null);

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
                            } while (withdrawalTransaction == null);

                            withdrawalTransaction.execute();

                            // Update the user account balance in the user_record.txt file
                            fileManager.updateBalance(username, authenticatedUser.getAccountBalance());

                            break;
                        case 4:
                            int maxAttempts = 3;
                            int attemptCount = 0;

                            while (true) {
                                System.out.println("Enter the recipient's username you want to transfer money to: ");
                                String recipientUsername = input.nextLine();
                                User recipientUser = fileManager.getUserByUsername(recipientUsername);
                                if (recipientUser != null) {
                                    System.out.println("Enter the amount you want to transfer");
                                    String transferAmountInput = input.nextLine();
                                    double transferAmount = Double.parseDouble(transferAmountInput);

                                    String userInputToTransferMoney;
                                    Transaction transferTransaction = null;
                                    do {
                                        System.out.println("Enter 'transfer' to transfer the money");
                                        userInputToTransferMoney = input.nextLine();
                                        try {
                                            transferTransaction = transactionFactory.createTransaction(userInputToTransferMoney, authenticatedUser, recipientUser, transferAmount);
                                        } catch (IllegalArgumentException e) {
                                            System.out.println(e.getMessage());
                                        }
                                    } while (transferTransaction == null);

                                    transferTransaction.execute();

                                    // Update the user account balance in the user_record.txt file for both authenticatedUser and recipientUser
                                    fileManager.updateBalance(username, authenticatedUser.getAccountBalance());
                                    fileManager.updateBalance(recipientUsername, recipientUser.getAccountBalance());
                                    break; // Exit the loop and return to the main menu
                                } else {
                                    System.out.println("Recipient not found. Try again");
                                    attemptCount++;
                                    if (attemptCount >= maxAttempts) {
                                        System.out.println("Maximum attempts reached. Returning to the main menu.");
                                        break; // Exit the loop and return to the main menu
                                    }
                                }
                            }
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
            }else {
            System.out.println("Maximum login attempts reached. Exiting the system.");
        }

        }

    }








