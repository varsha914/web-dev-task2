import java.util.Scanner;

// User class to represent ATM users
class User {
    private String userID;
    private String userPIN;
    private double accountBalance;

    // Constructor
    public User(String userID, String userPIN, double accountBalance) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.accountBalance = accountBalance;
    }

    // Getters
    public String getUserID() {
        return userID;
    }

    public String getUserPIN() {
        return userPIN;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    // Method to check balance
    public void checkBalance() {
        System.out.println("Your current balance is: $" + accountBalance);
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount <= accountBalance) {
            accountBalance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: $" + accountBalance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    // Method to deposit money
    public void deposit(double amount) {
        accountBalance += amount;
        System.out.println("Deposit successful. Updated balance: $" + accountBalance);
    }
}

// ATM class to encapsulate ATM functionalities
public class task2 {
    private User currentUser;

    // Constructor
    public task2(User currentUser) {
        this.currentUser = currentUser;
    }

    // Method for user authentication
    public boolean authenticate(String userID, String userPIN) {
        return currentUser.getUserID().equals(userID) && currentUser.getUserPIN().equals(userPIN);
    }

    // Method to display ATM menu
    public void displayMenu() {
        System.out.println("Welcome to the ATM");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Deposit Money");
        System.out.println("4. Exit");
    }

    // Method to handle user input and perform operations
    public void performOperation(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                currentUser.checkBalance();
                break;
            case 2:
                System.out.print("Enter amount to withdraw: $");
                double withdrawAmount = scanner.nextDouble();
                currentUser.withdraw(withdrawAmount);
                break;
            case 3:
                System.out.print("Enter amount to deposit: $");
                double depositAmount = scanner.nextDouble();
                currentUser.deposit(depositAmount);
                break;
            case 4:
                System.out.println("Thank you for using the ATM. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void main(String[] args) {
        // Create a user
        User user = new User("123456", "7890", 1000.00);

        // Create an ATM with the user
        task2 atm = new task2(user);

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt for user authentication
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String userPIN = scanner.nextLine();

        // Authenticate user
        if (atm.authenticate(userID, userPIN)) {
            System.out.println("Authentication successful. Welcome, " + user.getUserID() + "!");
            while (true) {
                // Display ATM menu
                atm.displayMenu();
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                atm.performOperation(choice, scanner);
            }
        } else {
            System.out.println("Authentication failed. Invalid User ID or PIN.");
        }
    }
}
