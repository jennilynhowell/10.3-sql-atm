package com.jennilyn.Helpers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private Statement statement;

    public DatabaseManager(Connection connection) throws SQLException {
        this.statement = connection.createStatement();
    }

    public void dropBankTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS bank");
    }

    public void createBankTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE bank (id INTEGER PRIMARY KEY, transactionAmount DOUBLE, transactionType STRING, transactionDate DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    public Statement getStatement() {
        return statement;
    }

    public ResultSet getActivity(String tableName) throws SQLException {
        String formattedSQL = String.format("SELECT * FROM %s", tableName);
        ResultSet rs = statement.executeQuery(formattedSQL);
        return rs;
    }
}
