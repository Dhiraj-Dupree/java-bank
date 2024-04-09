import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Bank {
    private double balance = 300;
    private List<String> transactions = new ArrayList<>();
    private Map<String, Runnable> options = new HashMap<>();

    public Bank() {
        options.put("1", this::showTransactions);
        options.put("2", this::withdraw);
        options.put("3", this::deposit);
        options.put("4", this::exit);
    }

    private void showTransactions() {
        System.out.println("Past transactions:");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How much would you like to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        if (amount <= balance) {
            balance -= amount;
            transactions.add(String.format("Withdrawn: $%.2f", amount));
            System.out.printf("New Balance: $%.2f%n", balance);
        } else {
            System.out.println("Invalid amount, check your balance and try again.");
        }
    }

    private void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How much would you like to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        balance += amount;
        transactions.add(String.format("Deposited: $%.2f", amount));
        System.out.printf("New Balance: $%.2f%n", balance);
    }

    private void exit() {
        System.out.println("Exiting...");
        System.exit(0);
    }

    public void main() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nWelcome, choose what you'd like to do:");
            System.out.println("(1) Show transactions");
            System.out.println("(2) Withdraw");
            System.out.println("(3) Deposit");
            System.out.println("(4) Exit\n");

            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();
            if (options.containsKey(choice)) {
                options.get(choice).run();
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.main();
    }
}
