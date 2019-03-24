package com.lxc.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;


public class TestJDBC {
	
	public static void main(String[] args) {
		Connection conn = null;
        try {
            String userName = "zhimaa";
            String password = "zhimaa";

            String url = "jdbc:mysql://localhost:3305/fan?allowNativePasswords=true&useUnicode=yes&characterEncoding=UTF-8&connectionCollation=utf8_general_ci&zeroDateTimeBehavior=convertToNull";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server");
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Database Connection Terminated");
                } catch (Exception e) {}
            }
        }
	}
}
