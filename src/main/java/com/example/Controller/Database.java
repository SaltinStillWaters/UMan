package com.example.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.Model.Session;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DB = "DB202211284";
    private static final String TABLE = "USER";
    private static boolean isInit = false;

    private static Connection connection;

    public static void openConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL + DB, USER, PASSWORD);
                System.out.println("Connection reopened!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                System.out.println("length: " + Session.signupKeys.length + "curr val: " + val);
                
                if (Session.signupKeys[x].equals("AGE")) {
                    try {
                        int num = Integer.parseInt(val);
                        stmt.setInt(x + 1, num);
                    } catch (NumberFormatException e) {
                        //error
                    }
                } else {
                    stmt.setString(x + 1, val);
                }
                
            }

            stmt.executeUpdate();
        } catch (Exception e) {
        }
    }

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
                                "EMAIL VARCHAR(50), " +
                                "AGE INT(3), " +
                                "BIRTHDAY VARCHAR(15), " +
                                "PASSWORD VARCHAR(50))";
            stmt.executeUpdate(tableSql);
            
            String adminSql =   "INSERT IGNORE INTO " + TABLE + "(" +
                                "FIRST_NAME, LAST_NAME, EMAIL, AGE, BIRTHDAY, PASSWORD) " +
                                "VALUES('ADMIN', 'ADMIN', 'ADMIN.ADMIN@ADMIN', 100, '01/01/2001', 'ADMIN');";
            stmt.executeUpdate(adminSql);

            // Close connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
