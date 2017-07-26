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
    public BankAccount(Statement statement, double transactionAmount, String transactionType, String transactionDate) {
        this.statement = statement;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    public double deposit(double transactionAmount) throws SQLException {
        String formattedSql = String.format("INSERT INTO bank(transactionAmount, transactionType) VALUES (%s, 'deposit')", transactionAmount);

        statement.executeUpdate(formattedSql);
        this.balance += transactionAmount;

        return balance;
    }

    public double withdraw(double transactionAmount) throws SQLException {
        String formattedSql = String.format("INSERT INTO bank(transactionAmount, transactionType) VALUES (%s, 'withdrawal')", transactionAmount);
        statement.executeUpdate(formattedSql);
        this.balance -= transactionAmount;
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
            double transactionAmount = rs.getDouble("transactionAmount");
            String transactionType = rs.getString("transactionType");
            String transactionDate = rs.getString("transactionDate");
            BankAccount tempResult = new BankAccount(tempStatement, transactionAmount, transactionType, transactionDate);
            tempCollection.add(tempResult);
        }
        return tempCollection;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "transactionAmount=" + transactionAmount +
                ", transactionType='" + transactionType + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                '}';
    }
}
