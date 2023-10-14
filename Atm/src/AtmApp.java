import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String userId;
    private String pin;
    private double balance;
    // You might want to add more user-related information

    public User(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

public class AtmApp {
    private Map<String, User> users;

    public AtmApp() {
        users = new HashMap<>();
        // You might want to load users from a database in a real-world scenario
        users.put("1234", new User("1234", "5678", 1000.0));
        users.put("5678", new User("5678", "1234", 500.0));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (authenticateUser(userId, pin)) {
            displayMenu(userId);
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
    }

    private boolean authenticateUser(String userId, String pin) {
        if (users.containsKey(userId)) {
            User user = users.get(userId);
            return user.getPin().equals(pin);
        }
        return false;
    }

    private void displayMenu(String userId) {
        Scanner scanner = new Scanner(System.in);
        User user = users.get(userId);

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Transactions History: (Not implemented in this example)");
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (withdrawAmount > 0 && withdrawAmount <= user.getBalance()) {
                        user.setBalance(user.getBalance() - withdrawAmount);
                        System.out.println("Withdrawal successful. Remaining balance: " + user.getBalance());
                    } else {
                        System.out.println("Invalid amount or insufficient funds.");
                    }
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    if (depositAmount > 0) {
                        user.setBalance(user.getBalance() + depositAmount);
                        System.out.println("Deposit successful. Updated balance: " + user.getBalance());
                    } else {
                        System.out.println("Invalid amount.");
                    }
                    break;
                case 4:
                    // Implement transfer functionality
                    System.out.println("Transfer: (Not implemented in this example)");
                    break;
                case 5:
                    System.out.println("Quitting ATM. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    public static void main(String[] args) {
        AtmApp atm = new AtmApp();
        atm.start();
    }
}
