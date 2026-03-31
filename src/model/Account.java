package smartwallet.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String name;
    private String username;
    private double balance;
    private String accountType;
    private List<Transaction> transactions;

    public Account(String name, String username, String accountType) {
        this.name = name;
        this.username = username;
        this.accountType = accountType;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t) {
        this.transactions.add(t);
        this.balance += t.getNetAmount();
    }

    // Getters
    public String getUsername() { return username; }
    public String getName() { return name; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactions() { return transactions; }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%.1f", username, name, accountType, balance);
    }
}