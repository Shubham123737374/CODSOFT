import java.util.Scanner;

// Class 1: Bank Account (User ka khata)
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Successfully Deposited: ₹" + amount);
        } else {
            System.out.println("❌ Invalid Amount. Please enter a positive number.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ Withdrawal Successful: ₹" + amount);
            return true;
        } else if (amount > balance) {
            System.out.println("❌ Insufficient Balance! Transaction Failed.");
            return false;
        } else {
            System.out.println("❌ Invalid Amount.");
            return false;
        }
    }
}

// Class 2: ATM Machine (User Interface)
class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to the ATM!");

        do {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("💰 Current Balance: ₹" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using our ATM. Goodbye! 👋");
                    break;
                default:
                    System.out.println("⚠️ Invalid choice. Please select 1-4.");
            }
        } while (choice != 4);
        
        sc.close();
    }
}

// Main Class to run the program
public class ATMInterface {
    public static void main(String[] args) {
        // User ke account mein shuruwat mein ₹1000 daal rahe hain
        BankAccount userAccount = new BankAccount(1000.00);
        
        // ATM machine ko user account ke sath start kar rahe hain
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
