import java.util.Scanner;

public class BankingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double balance = 10000.0;

        System.out.println("Welcome to Sakshi's Bank");
        System.out.println("Opening Balance: ₹" + (int)balance);

        while (true) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            // Handle invalid choices that are not integers
            if (!scanner.hasNextInt()) {
                System.out.println("Error: Invalid choices / amounts. Please select a valid number.");
                scanner.next(); 
                continue;
            }

            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Current Balance: ₹" + (int)balance);

            } else if (choice == 2) {
                System.out.print("Enter deposit amount: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Error: Invalid choice / amount. Transaction cancelled.");
                    scanner.next();
                    continue;
                }
                double depositAmount = scanner.nextDouble();

                // Validation rule: Zero or negative amounts not allowed
                if (depositAmount <= 0) {
                    System.out.println("Transaction failed: Zero or negative amounts are not allowed.");
                } else {
                    balance += depositAmount; // Update balance variable
                    System.out.println("Deposit successful.");
                    System.out.println("Updated Balance: ₹" + (int)balance);
                }

            } else if (choice == 3) {
                System.out.print("Enter withdrawal amount: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Error: Invalid choice / amount. Transaction cancelled.");
                    scanner.next();
                    continue;
                }
                double withdrawAmount = scanner.nextDouble();

                // Validation rules for withdrawal
                if (withdrawAmount <= 0) {
                    System.out.println("Transaction failed: Zero or negative amounts are not allowed.");
                } else if (withdrawAmount > balance) {
                    System.out.println("Transaction failed: Insufficient balance.");
                    System.out.println("Current Balance: ₹" + (int)balance);
                } else {
                    balance -= withdrawAmount; 
                    System.out.println("Withdrawal successful.");
                    System.out.println("Updated Balance: ₹" + (int)balance);
                }

            } else if (choice == 4) {
                // Step 6: Exit program
                System.out.println("Thank you for banking with us. Goodbye!");
                break; 
            } else {
                System.out.println("Error: Invalid choice. Please select a valid option (1-4).");
            }
        }

        scanner.close();
    }
}