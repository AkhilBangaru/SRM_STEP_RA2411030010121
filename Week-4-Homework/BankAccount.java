// Question - 2

import java.util.Random;

public class BankAccount {
    String accountHolder;
    int accountNumber;
    double balance;

    // Default constructor → balance = 0
    public BankAccount() {
        this("Unknown", 0);
    }

    // Constructor with name → random account number
    public BankAccount(String accountHolder) {
        this(accountHolder, new Random().nextInt(900000) + 100000, 0.0);
    }

    // Constructor with name and initial balance → random account number
    public BankAccount(String accountHolder, double balance) {
        this(accountHolder, new Random().nextInt(900000) + 100000, balance);
    }

    // Full constructor → sets all fields
    public BankAccount(String accountHolder, int accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + " deposited. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(amount + " withdrawn. New balance: " + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    // Display account info
    public void displayAccount() {
        System.out.println("\n--- Bank Account Details ---");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: ₹" + balance);
    }

    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount(); // Default
        BankAccount acc2 = new BankAccount("Akhil"); // Name only
        BankAccount acc3 = new BankAccount("Chaitanya", 5000); // Name + balance
        BankAccount acc4 = new BankAccount("Sai", 123456, 10000); // Full constructor

        // Deposit and withdraw
        acc2.deposit(2000);
        acc3.withdraw(1000);
        acc4.deposit(500);
        acc4.withdraw(1500);

        // Display all accounts
        acc1.displayAccount();
        acc2.displayAccount();
        acc3.displayAccount();
        acc4.displayAccount();
    }
}
