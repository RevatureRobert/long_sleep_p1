package util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Some simple thread stuff for transaction commitment.
 */
public class MyThread extends Thread {
    private Connection conn;

    public MyThread(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void run() {
        // apparently we have to do this here...
        try {
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
