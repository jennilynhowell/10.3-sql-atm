package com.jennilyn;

import com.jennilyn.Helpers.DatabaseManager;
import com.jennilyn.Model.BankAccount;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bank.db")) {

            DatabaseManager dbm = new DatabaseManager(connection);
            Statement statement = dbm.getStatement();
            BankAccount myAccount = new BankAccount(statement);
//            dbm.dropBankTable();
//            dbm.createBankTable();
            //myAccount.deposit(1035.77);
            //System.out.println(myAccount.getBalance());
            myAccount.withdraw(15.0);
            //System.out.println(myAccount.getBalance());

//            List<BankAccount> activity = myAccount.showTransactionHistory(dbm);
//            for (BankAccount account : activity){
//                System.out.println(account);
//            }

        } catch (SQLException ex) {
            System.out.println("Something went wrong with your database connections.");
            ex.printStackTrace();
        }
    }
}
