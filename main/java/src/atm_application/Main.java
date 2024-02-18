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

        // If the user fails to log-in 3 times, finish the system
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
                                    System.out.println("Recipient not found. \nAttempt left: " + (maxAttempts - attemptCount -1));
                                    attemptCount++;
                                    if (attemptCount >= maxAttempts) {
                                        System.out.println("Maximum attempts reached. Returning to the main menu.");
                                        break; // Exit the loop and return to the main menu
                                    }
                                }
                            }
                            break;
                        case 5:
                            String newPassword = null;
                            String newPasswordToConfirm;
                            // If the user fails to confirm 3 times, returning to the main menu
                            int maxAttemptsToChangePIN = 3;
                            int attemptsToChangePIN = 0;
                            while(newPassword == null && attemptsToChangePIN < maxAttemptsToChangePIN) {
                                System.out.println("Enter the new password");
                                newPassword = input.nextLine();
                                System.out.println("Enter the new password again to confirm");
                                newPasswordToConfirm = input.nextLine();
                                if(newPassword.equals(newPasswordToConfirm)) {
                                    fileManager.updatePassword(username, newPassword);
                                    authenticatedUser.setAccountPassword(newPassword);
                                    System.out.println("Password update successful. New password: " + authenticatedUser.getAccountPassword());
                                    break;
                                }else {
                                    attemptsToChangePIN++;
                                    System.out.println("Passwords don't match. \nAttempt left: " + (maxAttemptsToChangePIN - attemptsToChangePIN));
                                    if(attemptsToChangePIN>=maxAttemptsToChangePIN){
                                        System.out.println("Maximum attempts reached. Returning to the main menu. Try later.");
                                    }else {
                                        // Reset newPassword to null for the next iteration: otherwise it returns to the main menu with 'Attempt left: 2'
                                        newPassword = null;
                                    }
                                }
                            }
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








