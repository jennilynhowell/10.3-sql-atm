package com.jennilyn.Model;

import com.jennilyn.Helpers.DatabaseManager;

import java.sql.Date;
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

    public double deposit(double transactionAmount) throws SQLException {
        this.balance += transactionAmount;

        String formattedSql = String.format("INSERT INTO bank(balance, transactionAmount, transactionType) VALUES (%s, %s, 'deposit')", this.balance,transactionAmount);
        statement.executeUpdate(formattedSql);

        return balance;
    }

    public double withdraw(double transactionAmount) throws SQLException {
        this.balance -= transactionAmount;

        String formattedSql = String.format("INSERT INTO bank(balance, transactionAmount, transactionType) VALUES (%s, %s, 'withdrawal')", this.balance, transactionAmount);
        statement.executeUpdate(formattedSql);

        return balance;
    }

    public double getBalance() {
        return balance;
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
