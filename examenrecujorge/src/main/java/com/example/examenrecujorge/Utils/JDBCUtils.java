package com.example.examenrecujorge.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    static private Connection con = null;

    static {
        String url = "jdbc:mysql://localhost:3306/recu";
        String user = "root";
        String password = "";
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static Connection getConnection() {
        return con;
    }
}
