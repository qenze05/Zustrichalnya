package com.smakab.datingapp.zustrichalnya.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class config {
    protected String dbHost = "localhost";
    protected String dbPort = "3306";
    protected String dbUser = "root1";
    protected String dbPass = "root1";
    protected String dbName = "bdzl";

    public class DataBaseConnect extends config {
        Connection dbConnection;


        public class Const {
            public static final String USER_TABLE = "users";
            public static final String USER_EMAIL = "email";
            public static final String USER_PASSWORD = "password";


            public Connection getDbConnection()
                    throws ClassNotFoundException, SQLException {
                String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

                Class.forName("com.mysql.jdbc.Driver");
                dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

                return dbConnection;

            }
            //Функция к которой нужно обратится, берет и вводит в бд то что пользователь ввел по кнопке
            public void singUpUser(String email, String password) {
                String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_EMAIL + "," + Const.USER_PASSWORD + ")" + "VALUES(?,?,?)";


                try {

                    PreparedStatement prSt = getDbConnection().prepareStatement(insert);

                    prSt.setString(1, email);
                    prSt.setString(2, password);


                    prSt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}


