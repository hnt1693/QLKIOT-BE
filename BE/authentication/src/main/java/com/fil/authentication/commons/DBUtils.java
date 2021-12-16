package com.fil.authentication.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    public static Connection getConnection()  {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/authentication",
                            "postgres", "arettes123");
            System.out.println("CONNECT CONNECTION DB SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
        return c;
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null) connection.close();
    }
}
