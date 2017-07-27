package com.jennilyn;

import com.jennilyn.Helpers.DatabaseManager;
import com.jennilyn.Model.BankAccount;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bank.db")) {

            DatabaseManager dbm = new DatabaseManager(connection);
            Statement statement = dbm.getStatement();
            BankAccount myAccount = new BankAccount(statement);
            Scanner scanner = new Scanner(System.in);

            welcomeMenu(dbm, myAccount, scanner);


        } catch (SQLException ex) {
            System.out.println("Something went wrong with your database connections.");
            ex.printStackTrace();
        }
    }

    private static void welcomeMenu(DatabaseManager dbm, BankAccount myAccount, Scanner scanner) throws SQLException {
        System.out.println("\n=========");
        System.out.println("Welcome to PantherBank ATM");
        System.out.println("Where serving you is our first prowlority");
        System.out.println("What can we pounce on for you today?");
        System.out.println("1) Stalk my account balance.");
        System.out.println("2) Scratch out a new transaction.");
        System.out.println("3) I'm just here for bad cat puns.");
        System.out.println("=========\n");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Meeee-yow. Here is your current balance:");
                System.out.println(myAccount.getBalance(dbm));
                break;
            case 2:
                System.out.println("Purrrfect. Are you making a 1) deposit or 2) withdrawal today?");
                int transChoice = scanner.nextInt();
                String transType = "";

                switch (transChoice) {
                    case 1:
                        transType = "deposit";
                        break;
                    case 2:
                        transType = "withdraw";
                        break;
                    default:
                        System.out.println("Meow? Please try again.");
                        break;
                }

                System.out.println("Thank you. What is the amount you wish to " + transType + "?");
                double transAmount = scanner.nextDouble();

                myAccount.transaction(dbm, transAmount, transType);
                System.out.println("Appawciate your business.");
                System.out.println("Your balance is now: " + myAccount.getBalance(dbm));

                break;
            case 3:
                System.out.println("Live long and pawsper, panther pal.");
                break;
            default:
                System.out.println("Don't stress meowt. Pawlese choose a valid number.");
                break;
        }

        welcomeMenu(dbm, myAccount, scanner);
    }
}
