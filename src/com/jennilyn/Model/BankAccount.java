package com.jennilyn.Model;

import com.jennilyn.Helpers.DatabaseManager;

import javax.swing.plaf.nimbus.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    private Statement statement;
    private double transactionAmount;
    private String transactionType;
    private String transactionDate;

    public BankAccount(Statement statement) {
        this.statement = statement;
    }

    //for reporting:
    public BankAccount(double balance, Statement statement, double transactionAmount, String transactionType, String transactionDate) {
        this.balance = balance;
        this.statement = statement;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    public void transaction(DatabaseManager dbm, double amount, String type) throws SQLException {
        double mostRecentBalance = 0.0;
        double newBalance;
        Statement statement = dbm.getStatement();
        ResultSet rs = dbm.getLastActivity("bank");

        if(rs.next()) {
            mostRecentBalance = rs.getDouble("balance");

        } else {
            mostRecentBalance = 0.0;
            System.out.println("I did not smell a recent balance. Welcome to your new account.");
        }


        if (type == "deposit"){
            newBalance = mostRecentBalance + amount;
            pushTransaction(amount, newBalance, type);
            System.out.println("Deposit approved.");

        } else if (type == "withdraw") {
            newBalance = mostRecentBalance - amount;
            pushTransaction(amount, newBalance, type);
            System.out.println("Withdrawal approved.");

        } else {
            System.out.println("Sorry, something went wrong with this transaction.");
        }


    }

    public void pushTransaction(double transactionAmount, double newBalance, String type) throws SQLException {

        String formattedSql = String.format("INSERT INTO bank(balance, transactionAmount, transactionType) VALUES (%s, %s, '%s')", newBalance, transactionAmount, type);
        statement.executeUpdate(formattedSql);

    }

    public double getBalance(DatabaseManager dbm) throws SQLException {
        ResultSet rs = dbm.getLastActivity("bank");
        double mostRecentBalance = 0.0;

        if(rs.next()) {
            mostRecentBalance = rs.getDouble("balance");
        }

        return mostRecentBalance;
    }

    public List<BankAccount> showTransactionHistory(DatabaseManager dbm) throws SQLException {
        ResultSet rs = dbm.getActivity("bank");
        List<BankAccount> tempCollection = new ArrayList<>();
        Statement tempStatement = dbm.getStatement();

        while(rs.next()){
            double balance = rs.getDouble("balance");
            double transactionAmount = rs.getDouble("transactionAmount");
            String transactionType = rs.getString("transactionType");
            String transactionDate = rs.getString("transactionDate");
            BankAccount tempResult = new BankAccount(balance, tempStatement, transactionAmount, transactionType, transactionDate);
            tempCollection.add(tempResult);
        }
        return tempCollection;
    }

    @Override
    public String toString() {
        return "Account History{" +
                "balance=" + balance +
                ", transactionAmount=" + transactionAmount +
                ", transactionType='" + transactionType + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                '}';
    }
}
