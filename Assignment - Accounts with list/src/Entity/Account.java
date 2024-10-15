package Entity;

public class Account {
    private final int accountNumber;
    private double balance = 5000;
    private String accountHolderName;
    private String location;
    private final double minBalance=5000;



    public Account(String accountHolderName, int accountNumber, String location) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.location = location;
    }


    public int getAccountNumber() {
        return accountNumber;
    }



    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", location='" + location + '\'' +
                ", minBalance=" + minBalance +
                '}';
    }
}
