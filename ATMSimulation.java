import java.util.Scanner;

public class ATMSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Predefined PIN and initial balance
        final int CORRECT_PIN = 5544;
        final int MAX_ATTEMPTS = 3;
        double balance = 1000.00;

        // Array to store transaction history
        String[] transactionHistory = new String[100];
        int transactionCount = 0;

        // 1. Authentication System using while loop
        int attempts = 0;
        boolean isAuthenticated = false;

        System.out.println("=================================");
        System.out.println("     WELCOME TO ATM SYSTEM       ");
        System.out.println("=================================");

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter your 4-digit PIN: ");
            if (scanner.hasNextInt()) {
                int enteredPin = scanner.nextInt();
                if (enteredPin == CORRECT_PIN) {
                    isAuthenticated = true;
                    System.out.println("Login successful!\n");
                    break;
                } else {
                    attempts++;
                    int remaining = MAX_ATTEMPTS - attempts;
                    if (remaining > 0) {
                        System.out.println("Incorrect PIN. Attempts remaining: " + remaining);
                    }
                }
            } else {
                System.out.println("Invalid input. Please enter numbers only.");
                scanner.next(); // Clear invalid input
                attempts++;
            }
        }

        if (!isAuthenticated) {
            System.out.println("\nMaximum number of attempts reached. Your card is blocked. Exiting...");
            scanner.close();
            return;
        }

        // 2. Main Menu using while loop
        boolean exit = false;
        while (!exit) {
            System.out.println("---------------------------------");
            System.out.println("            MAIN MENU            ");
            System.out.println("---------------------------------");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid choice. Please enter a number between 1 and 5.\n");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Check Balance
                    System.out.printf("Current Balance: $%.2f%n%n", balance);
                    break;

                case 2:
                    // Deposit Money
                    System.out.print("Enter amount to deposit: $");
                    if (scanner.hasNextDouble()) {
                        double depositAmount = scanner.nextDouble();
                        if (depositAmount > 0) {
                            balance += depositAmount;
                            if (transactionCount < transactionHistory.length) {
                                transactionHistory[transactionCount] = String.format("Deposited: +$%.2f | Balance: $%.2f", depositAmount, balance);
                                transactionCount++;
                            }
                            System.out.printf("Successfully deposited $%.2f. New Balance: $%.2f%n%n", depositAmount, balance);
                        } else {
                            System.out.println("Error: Deposit amount must be greater than zero.\n");
                        }
                    } else {
                        System.out.println("Error: Invalid amount entered.\n");
                        scanner.next();
                    }
                    break;

                case 3:
                    // Withdraw Money
                    System.out.print("Enter amount to withdraw: $");
                    if (scanner.hasNextDouble()) {
                        double withdrawAmount = scanner.nextDouble();
                        if (withdrawAmount <= 0) {
                            System.out.println("Error: Withdrawal amount must be greater than zero.\n");
                        } else if (withdrawAmount <= balance) {
                            balance -= withdrawAmount;
                            if (transactionCount < transactionHistory.length) {
                                transactionHistory[transactionCount] = String.format("Withdrew:  -$%.2f | Balance: $%.2f", withdrawAmount, balance);
                                transactionCount++;
                            }
                            System.out.printf("Successfully withdrew $%.2f. Remaining Balance: $%.2f%n%n", withdrawAmount, balance);
                        } else {
                            System.out.println("Error: Insufficient balance! Transaction cancelled.\n");
                        }
                    } else {
                        System.out.println("Error: Invalid amount entered.\n");
                        scanner.next();
                    }
                    break;

                case 4:
                    // View Transaction History using for loop
                    System.out.println("\n------ TRANSACTION HISTORY ------");
                    if (transactionCount == 0) {
                        System.out.println("No transactions recorded yet.");
                    } else {
                        for (int i = 0; i < transactionCount; i++) {
                            System.out.println((i + 1) + ". " + transactionHistory[i]);
                        }
                    }
                    System.out.println("---------------------------------\n");
                    break;

                case 5:
                    // Exit
                    exit = true;
                    System.out.println("Thank you for using our ATM. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please select between 1 and 5.\n");
            }
        }

        scanner.close();
    }
}
