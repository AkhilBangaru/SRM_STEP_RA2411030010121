// File: Assignment_1_Akhil.java
// Author: Bangaru Chaitanya Venkata Sai Akhil

class PersonalAccount {
    // Private attributes
    private String accountHolderName;
    private String accountNumber;
    private double currentBalance;
    private double totalIncome;
    private double totalExpenses;

    // Static variables
    private static int totalAccounts = 0;
    private static String bankName = "Default Bank";

    // Constructor
    public PersonalAccount(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = generateAccountNumber();
        this.currentBalance = 0.0;
        this.totalIncome = 0.0;
        this.totalExpenses = 0.0;
        totalAccounts++;
    }

    // Method to add income
    public void addIncome(double amount, String description) {
        if (amount > 0) {
            currentBalance += amount;
            totalIncome += amount;
            System.out.println(accountHolderName + " received income: " + description + " | +" + amount);
        } else {
            System.out.println("Invalid income amount!");
        }
    }

    // Method to add expense
    public void addExpense(double amount, String description) {
        if (amount > 0 && amount <= currentBalance) {
            currentBalance -= amount;
            totalExpenses += amount;
            System.out.println(accountHolderName + " spent on: " + description + " | -" + amount);
        } else {
            System.out.println("Invalid expense or insufficient balance!");
        }
    }

    // Method to calculate savings
    public double calculateSavings() {
        return totalIncome - totalExpenses;
    }

    // Method to display account summary
    public void displayAccountSummary() {
        System.out.println("\n--- Account Summary ---");
        System.out.println("Bank Name: " + bankName);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: " + currentBalance);
        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expenses: " + totalExpenses);
        System.out.println("Savings: " + calculateSavings());
    }

    // Static methods
    public static void setBankName(String name) {
        bankName = name;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public static String generateAccountNumber() {
        return "AC" + (1000 + totalAccounts + 1);
    }
}

public class Assignment_1_Akhil {
    public static void main(String[] args) {
        // Set bank name (shared by all accounts)
        PersonalAccount.setBankName("OpenAI Bank");

        // Create accounts
        PersonalAccount acc1 = new PersonalAccount("Akhil");
        PersonalAccount acc2 = new PersonalAccount("Ravi");
        PersonalAccount acc3 = new PersonalAccount("Priya");

        // Perform transactions
        acc1.addIncome(5000, "Salary");
        acc1.addExpense(1500, "Groceries");
        acc1.addExpense(2000, "Rent");

        acc2.addIncome(8000, "Freelance Work");
        acc2.addExpense(2500, "Laptop EMI");

        acc3.addIncome(10000, "Business");
        acc3.addExpense(3000, "Supplies");
        acc3.addExpense(2000, "Transport");

        // Display summaries
        acc1.displayAccountSummary();
        acc2.displayAccountSummary();
        acc3.displayAccountSummary();

        // Show total accounts created
        System.out.println("\nTotal Accounts Created: " + PersonalAccount.getTotalAccounts());

        // Demonstrating static vs instance
        System.out.println("\nNote: Bank Name is shared across all accounts (static),");
        System.out.println("but balances & transactions are unique to each account (instance variables).");
    }
}
