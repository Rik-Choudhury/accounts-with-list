package Entity;

import java.util.ArrayList;
import java.util.Random;

public class BackendOperations {
    ArrayList<Account> allAccounts=new ArrayList<>();

    public ArrayList<Account> getAllAccounts() {
        return allAccounts;
    }

    public void setAllAccounts(ArrayList<Account> allAccounts) {
        this.allAccounts = allAccounts;
    }

    public Account createAccount(String accountHolderName, String location){
        int accountNumber = new Random().nextInt(10000);
        Account account = new Account(accountHolderName,accountNumber,location);
        allAccounts.add(account);
        return account;
    }

    public Account searchAccountByNumber(int accountNumber){
        for(Account a:allAccounts){
            if(a.getAccountNumber()==accountNumber){
                return a;
            }
        }
        return null;
    }

    public Account deposit(int accountNumber,double amount){
        if(allAccounts!=null){
            for(Account account:allAccounts){
                if(account.getAccountNumber()==accountNumber){
                    account.setBalance(account.getBalance()+amount);
                    return account;
                }
            }//end for
        }
        else {
            System.out.println("No account found");
            return null;
        }
        return null;
    }//end deposit


    public void withdraw(int accountNumber,double amount) throws LowBalanceException{
        boolean found=false;

        if(allAccounts!=null){
            for(Account account:allAccounts){
                if(account.getAccountNumber()==accountNumber){
                    found=true;
                    if (account.getBalance()>amount && (account.getBalance()-amount)>5000){                         //account.getBalance()>amount = amount less than available balance || account.getBalance()-amount<5000 = after withdrawal minBalance is maintained
                        account.setBalance(account.getBalance()-amount);
                        break;
                    }
                    else {
                        throw new LowBalanceException("Insufficient balance");
                    }
                }
            }//end for

//            if (!found){
//                System.out.println("There is no such account");
//            }
//        }
//        else {
//            System.out.println("No account found");
        }

    }//end withdraw

    public void fundTransfer(int fromAccountNumber,int toAccountNumber,double amount) throws LowBalanceException{
        boolean found=false;
        if(allAccounts!=null){
            for (Account account1:allAccounts) {
                if(account1.getAccountNumber()==fromAccountNumber){
                    for (Account account2:allAccounts) {
                        if(account2.getAccountNumber()==toAccountNumber){
                            found=true;
                            if (account1.getBalance()>amount && (account1.getBalance() - amount)>5000){                 //same as withdrawal, see comment
                                account2.setBalance(account2.getBalance()+amount);
                                account1.setBalance(account1.getBalance()-amount);
                                break;
                            }
                            else{
                                throw new LowBalanceException("Insufficient balance");

                            }
                        }
                    }//end inner for
                }
//                if (!found){
//                    throw new LowBalanceException("No such account");
//                }
            }
        }
    }//end fundTransfer

    public ArrayList<Account> filterAccountsByLocation(String location){
        ArrayList<Account> filteredAccounts=new ArrayList<>();
        for(Account account:allAccounts){
            if(account.getLocation().equals(location)){
                filteredAccounts.add(account);
            }
        }
        return filteredAccounts;
    }

    public ArrayList<Account> filterAccountByBalanceRange(double minBalance, double maxBalance){
        ArrayList<Account> filteredAccounts=new ArrayList<>();
        for(Account account:allAccounts){
            if(account.getBalance()>=minBalance && account.getBalance()<=maxBalance){
                filteredAccounts.add(account);
            }
        }
        return filteredAccounts;
    }

}
