package com.smakab.datingapp.zustrichalnya.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "zust",
                databaseUser = "root",
                databasePassword = "root",
                link = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            databaseLink = DriverManager.getConnection(link, databaseUser, databasePassword);
        }catch (Exception error){
            error.printStackTrace();
            error.getCause();
        }
        return databaseLink;
    }

}