package com.pboreg;

import java.sql.*;

public class KoneksiDB {
    private Connection conn;
    private Statement st;

    public KoneksiDB() {
        try {
            String user = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/project-pbo";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public ResultSet getData(String query) {
        try {
            st = conn.createStatement();
            return st.executeQuery(query);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public ResultSet getData2(String query) {
        try {
            st = conn.createStatement();
            return st.executeQuery(query);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public int manipulasiData(String query) {
        try {
            st = conn.createStatement();
            return st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
            return 0;
        }
    }
}