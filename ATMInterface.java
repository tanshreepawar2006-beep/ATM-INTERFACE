import java.util.*;

class ATM {
    private String userId;
    private String userPin;
    private double balance;
    private ArrayList<String> transactionHistory;

    public ATM(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean login(String id, String pin) {
        return this.userId.equals(id) && this.userPin.equals(pin);
    }

    public void showTransactionHistory() {
        System.out.println("\n------ Transaction History ------");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String t : transactionHistory) {
                System.out.println(t);
            }
        }
        System.out.println("---------------------------------\n");
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: ₹" + amount);
            System.out.println("Successfully deposited ₹" + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdra-w: ₹" + amount);
            System.out.println("Successfully withdraw ₹" + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    public void transfer(String receiver, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Transferred ₹" + amount + " to " + receiver);
            System.out.println("Successfully transferred ₹" + amount + " to " + receiver);
        } else {
            System.out.println("Transfer failed! Check amount or balance.");
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ATM atm = new ATM("Om Bhusari", "0906");

        System.out.println("===== Welcome to Oasis Infobyte ATM =====");

        // Login
        System.out.print("Enter User ID: ");
        String id = sc.nextLine();

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        if (!atm.login(id, pin)) {
            System.out.println("Incorrect User ID or PIN!");
            return;
        }

        System.out.println("Login successful!\n");

        while (true) {
            System.out.println("----- ATM Menu -----");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance");
            System.out.println("6. Quit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    atm.showTransactionHistory();
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double w = sc.nextDouble();
                    atm.withdraw(w);
                    break;

                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double d = sc.nextDouble();
                    atm.deposit(d);
                    break;

                case 4:
                    sc.nextLine();
                    System.out.print("Enter receiver name: ");
                    String r = sc.nextLine();
                    System.out.print("Enter amount to transfer: ");
                    double t = sc.nextDouble();
                    atm.transfer(r, t);
                    break;

                case 5:
                    System.out.println("\nCurrent Balance: ₹" + atm.getBalance() + "\n");
                    break;

                case 6:
                    System.out.println("Thank you for using the ATM!");
                    return;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
