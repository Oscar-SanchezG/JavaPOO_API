package com.esanchez.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionDB {
    private static String url = "jdbc:oracle:thin:@//localhost:1521/xe";
    private static String username = "system";
    private static String password = "root";
    private static Connection connection;


    public  static Connection getInstance() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        if (connection == null){
            connection = DriverManager.getConnection(url,username,password);
        }
        return connection;
    }
}
