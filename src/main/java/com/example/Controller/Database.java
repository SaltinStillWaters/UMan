package com.example.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.Model.Session;
import com.example.Model.Validator;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DB = "DB202211284";
    private static final String TABLE = "USER";
    
    private static boolean isInit = false;

    private static Connection connection;

    public static void init() {
        if (isInit) {
            return;
        }
        isInit = true;
        
        try {
            Statement stmt;
            try (Connection tempConn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                stmt = tempConn.createStatement();
                String dbSql = "CREATE DATABASE IF NOT EXISTS " + DB + ";";
                stmt.executeUpdate(dbSql);
                System.out.println("DB created");
            }

            openConnection();
            stmt = connection.createStatement();
            String tableSql =   "CREATE TABLE IF NOT EXISTS " + TABLE +
                                "(ID INT PRIMARY KEY AUTO_INCREMENT, " +
                                "FIRST_NAME VARCHAR(50), " +
                                "LAST_NAME VARCHAR(50), " +
                                "EMAIL VARCHAR(50) UNIQUE, " +
                                "AGE INT(3), " +
                                "BIRTHDAY VARCHAR(15), " +
                                "PASSWORD VARCHAR(128))";
            stmt.executeUpdate(tableSql);
            
            String adminPass = Validator.hashPassword("ADMIN");
            String adminSql =   "INSERT IGNORE INTO " + TABLE + "(" +
            "FIRST_NAME, LAST_NAME, EMAIL, AGE, BIRTHDAY, PASSWORD) " +
            "VALUES('ADMIN', 'ADMIN', 'ADMIN@1', 100, '01/01/2001', '" + adminPass + "');";
            stmt.executeUpdate(adminSql);
            
            
            connection.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error initializing Database");
        }
    }
    
    public static void openConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL + DB, USER, PASSWORD);
                System.out.println("Connection reopened!");
            }
        } catch (SQLException e) {
        }
    }
    
    public static boolean checkRowExists(String valToMatch, int colToMatch) {
        boolean result = false;

        try {
            String col = Session.signupKeys[colToMatch];
            
            String sql =    "SELECT * FROM " + TABLE +
                            " WHERE " + col + "=?";
            System.out.println(connection);
            openConnection();
            System.out.println(connection);
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, valToMatch); //1 because setString expects 1-indexed

            ResultSet rs = stmt.executeQuery();
            result = rs.next();
            
            connection.close();
            stmt.close();
        } catch (IndexOutOfBoundsException e) {

        } catch (SQLException e) {
            System.err.println("Error in checkRowExists");
        }

        return result;
    }

    public static String getRow(String valToMatch, int colToMatch) {
        String row = null;
    
        try {
            String col = Session.signupKeys[colToMatch];
    
            String sql = "SELECT PASSWORD FROM " + TABLE +
                         " WHERE " + col + " = ?";
            openConnection();
    
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, valToMatch); // 1 because setString expects 1-indexed
    
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                row = rs.getString(1);
            }
    
            rs.close();
            stmt.close();
            connection.close();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Invalid column index: " + colToMatch);
        } catch (SQLException e) {
            System.err.println("Error retrieving row from database");
        }
    
        return row;
    }
    
    public static ArrayList<ArrayList<String>> getAllRows() {
        ArrayList<ArrayList<String>> rows = new ArrayList<>();

        try {
            //todo: dont get the id
            String sql =    "SELECT FIRST_NAME, LAST_NAME, EMAIL, AGE, BIRTHDAY, PASSWORD FROM " + TABLE +
                            " ORDER BY FIRST_NAME ASC";
            openConnection();
    
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                //-1 because CONFIRM_PASSWORD is not needed
                for (int x = 1; x <= Session.signupKeys.length - 1; ++x) {
                    row.add(rs.getString(x));
                }
                rows.add(row);
            }

    
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error retrieving row from database");
            return null;
        }

        return rows;
    }

    public static void insertUser() {
        try {
            String sql =    "INSERT INTO " + TABLE + "(" +
                            "FIRST_NAME, LAST_NAME, EMAIL, AGE, BIRTHDAY, PASSWORD) " +
                            "VALUES(?, ?, ?, ?, ?, ?)";
    
            openConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            for (int x = 0; x < Session.signupKeys.length - 1; ++x) {
                String val = Session.getSignupVal(x);
                
                if (Session.signupKeys[x].equals("AGE")) {
                    try {
                        int num = Integer.parseInt(val);
                        stmt.setInt(x + 1, num);
                    } catch (NumberFormatException e) {
                        System.err.println("Unexpected value for AGE in DB: " + val);
                    }
                } else {
                    stmt.setString(x + 1, val);
                }
                
            }

            stmt.executeUpdate();
            connection.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error Inserting into Database");
        }
    }
}
