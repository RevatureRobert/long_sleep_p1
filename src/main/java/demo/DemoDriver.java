package demo;

import persistence.ConnectionManager;

import java.sql.*;

/**
 * Runs the demo application for the long-sleep ORM.
 */
public class DemoDriver {

    public static void main(String[] args) {
        if (ConnectionManager.isAvailable()) {
            try {
                Connection conn = ConnectionManager.getConnection();
                System.out.println(conn.isClosed());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
