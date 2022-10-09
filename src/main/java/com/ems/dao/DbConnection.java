package com.ems.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    private static Connection CONN = null;
    public static Connection getConn() {
        if (CONN == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/ems";
                String username = "root";
                String password = "laasyam1";
                Class.forName("com.mysql.cj.jdbc.Driver");
                CONN = DriverManager.getConnection(url, username, password);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return CONN;
    }
}
