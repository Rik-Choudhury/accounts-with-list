package UI;

import Entity.Account;
import Entity.BackendOperations;
import Entity.LowBalanceException;

import java.util.*;

public class Main {
    BackendOperations ops = new BackendOperations();
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main app = new Main();


        while (true) {
            System.out.println("What you want to do?");
            System.out.println("1. Add Account");
            System.out.println("2. Search account by account number");
            System.out.println("3. Deposit Balance");
            System.out.println("4. Withdraw Balance");
            System.out.println("5. Transfer Funds");
            System.out.println("6. Display all accounts in a location");
            System.out.println("7. Display all accounts in a balance range");
            System.out.println("8. Display all accounts in the bank");
            System.out.println("9. Exit");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:app.addAccount();
                break;
                case 2:app.searchAccountByNumber();
                break;
                case 3:app.depositBalance();
                break;
                case 4:app.withdrawBalance();
                break;
                case 5:app.fundTransfer();
                break;
                case 6:app.displayAccountsByLocation();
                break;
                case 7:app.displayAccountsByBalanceRange();
                break;
                case 8:app.dispplayAllAccounts();
                break;
                case 9:System.exit(0);
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

    }

    private void addAccount() {
        System.out.println("Enter account holder name :");
        String accountHolderName = scanner.nextLine();
        System.out.println("Enter the bank location :");
        String bankLocation = scanner.nextLine();
        Account account=ops.createAccount(accountHolderName,bankLocation);
        System.out.println("Account created successfully" + account);
    }

    private void searchAccountByNumber() {
        System.out.println("Enter account number :");
        int accountNumber = Integer.parseInt(scanner.nextLine());
        System.out.println(ops.searchAccountByNumber(accountNumber));
    }

    private void depositBalance() {
        System.out.println("Enter account number :");
        int accountNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the amount :");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.println(ops.deposit(accountNumber,amount));
    }

    private void withdrawBalance () throws LowBalanceException {
        System.out.println("Enter account number :");
        int accountNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the amount :");
        double amount = Double.parseDouble(scanner.nextLine());
        try {
            ops.withdraw(accountNumber,amount);
            System.out.println("Balance withdrawn successfully");
        }
        catch (LowBalanceException e) {
            System.out.println(e.getMessage());
        }

    }

    private void fundTransfer() {
        System.out.println("Enter sender account number :");
        int accountNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter receiver account number :");
        int receiverAccountNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter amount :");
        double amount = Double.parseDouble(scanner.nextLine());

        try{
            ops.fundTransfer(accountNumber,receiverAccountNumber,amount);
        }
        catch (LowBalanceException e) {
            System.out.println(e.getMessage());
        }

    }

    private void displayAccountsByLocation() {
        System.out.println("Enter bank location :");
        String bankLocation = scanner.nextLine();
        for (Account account : ops.filterAccountsByLocation(bankLocation)) {                    //ops.filterAccountsByLocation(bankLocation) returns filtered arraylist by location
            System.out.println(account);
        }
    }

    private void displayAccountsByBalanceRange() {
        System.out.println("Enter the minimum balance :");
        double minBalance = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter the maximum balance :");
        double maxBalance = Double.parseDouble(scanner.nextLine());

         for (Account account : ops.filterAccountByBalanceRange(minBalance,maxBalance)) {               //ops.filterAccountByBalanceRange(minBalance,maxBalance)  returns filtered arrayList by balance range;
             System.out.println(account);
         }
    }

    private void dispplayAllAccounts() {
        for (Account account:ops.getAllAccounts()){
            System.out.println(account);
        }
    }
}