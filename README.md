# Automated-Teller-Machine-ATM--System
My assignment for ATM System with Factory Method Pattern, Singleton Pattern

---


# Automated Teller Machine (ATM) System in Java

## Introduction

This project is an exciting opportunity to delve into the world of Java programming by developing an Automated Teller Machine (ATM) system. This system will not only allow you to apply basic Java programming concepts but also explore more advanced features such as file handling, Command Line Interface (CLI) operations, and design patterns. The ATM system will simulate real-world banking operations such as balance inquiries, deposits, withdrawals, and fund transfers, all managed through text files instead of a database. This approach emphasizes the importance of file I/O in Java and provides a practical scenario to implement object-oriented programming principles. By completing this assignment, you will gain valuable experience in developing a comprehensive Java application that incorporates a wide range of programming concepts and techniques.

## Features and Implementation Guide

### Design Patterns Requirement

**Objective:** Apply design patterns to enhance code modularity, reusability, and maintainability.

**Implementation Guide:**

- **Factory Method Pattern:** Use this pattern for creating objects without specifying the exact class of object that will be created. This is particularly useful for creating different types of transactions (e.g., deposit, withdrawal, transfer) based on user input.

  **Hint:** Define a `TransactionFactory` class with a method that returns different `Transaction.Transaction` objects based on the transaction type.

- **Singleton Pattern:** Although we are not managing database connections, the Singleton pattern is still valuable for managing access to the file that stores user records. This ensures that only one instance of the file manager exists throughout the application, preventing concurrent file access issues.

  **Hint:** Implement a `FileManager` class as a Singleton, which will handle all read and write operations to the user records file.

### User Authentication

**Objective:** Verify user credentials against information stored in text files.

**Implementation Guide:**
- Store user data (username, password, balance) in a text file.
- Read the file and search for the username and password entered by the user.
- If a match is found, grant access; otherwise, display an error message.

### Balance Inquiry

**Objective:** Display the user's current balance.

**Implementation Guide:**
- After successful login, read the user's balance from the file.
- Display the balance to the user.

### Deposit Funds

**Objective:** Allow users to deposit money into their account.

**Implementation Guide:**
- Prompt the user for the amount to deposit.
- Update the user's balance in the file.
- Confirm the deposit to the user.

### Withdraw Funds

**Objective:** Enable users to withdraw money, ensuring the amount does not exceed their current balance.

**Implementation Guide:**
- Ask the user for the withdrawal amount.
- Check if the balance is sufficient.
- If so, update the balance in the file and confirm the withdrawal; otherwise, display an error message.

### Transfer Funds

**Objective:** Allow users to transfer funds to another user.

**Implementation Guide:**
- Prompt for the recipient's username and transfer amount.
- Verify both users' existence and balance sufficiency.
- Update both users' balances in the file.

### Change PIN

**Objective:** Users can change their password.

**Implementation Guide:**
- Verify the current password.
- Prompt for a new password and update it in the user's record.

### Additional Advanced Features

Continue with the advanced features as previously outlined, including Transaction.Transaction History, Scheduled Transactions, Account Alerts, Multi-Currency Support, User Profile Management, and Security Enhancements.

### JUnit Testing

**Objective:** Ensure the reliability and functionality of the ATM program.

**Implementation Guide:**
- Write unit tests for individual methods, focusing on edge cases and expected behavior.
- Implement integration tests to verify the system's behavior as a whole.

## Submission Guidelines

- Include all source files, test cases, and text files for user records.
- Ensure your code is well-commented and adheres to Java coding standards.
- Provide a report documenting your design decisions and any challenges encountered.

## Evaluation Criteria

- Correct implementation of required features.
- Effective application of Java concepts and design patterns.
- Code quality and organization.
- Comprehensive JUnit tests.

This assignment is designed to challenge your Java programming skills and encourage you to think critically about software design and implementation.

***Good luck!*** :wink:

---

## Resources

- https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples (Singleton Pattern)

- Head First Java 3rd Edition(serialization and file I/O)