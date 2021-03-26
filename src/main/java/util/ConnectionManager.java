package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Handles connection pooling and management.
 */
public class ConnectionManager {
    private static final int MAX_CONNECTIONS = 4;
    private static Connection[] conns = new Connection[MAX_CONNECTIONS];
    // number of connections starts at 0, increases as new connections are created
    private static int numConns = 0;

    /**
     * Connect to the db and create a new Connection instance.
     *
     * @return Connection
     * @throws SQLException
     */
    private static Connection createConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://db2102.cofn08xsx6st.us-west-2.rds.amazonaws.com:5432/postgres?currentSchema=dealership",
                "postgres", "eyeofthevoid");
        return conn;
    }

    /**
     * Creates and returns new connections on demand, adding them to the pool.
     * Will return null if no connections are available.
     *
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = null;

        // find empty connection slot in the pool and create new connection
        if (conns[numConns] == null) {
            conns[numConns] = createConnection();
            conn = conns[numConns++];
        }

        return conn;
    }

    /**
     * Closes a given connection and removes it from the pool.
     *
     * @throws SQLException
     */
    public static void closeConnection(Connection conn) throws SQLException {
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            if (conns[i].equals(conn)) {
                // close the given connection and remove it from the pool
                conns[i].close();
                conns[i] = null;
                numConns--;
            }
        }
    }

    /**
     * Returns true if a connection is available, false otherwise.
     * All calls to getConnection() should first go through here.
     *
     * @return boolean
     */
    public static boolean isAvailable() {
        return numConns != MAX_CONNECTIONS;
    }
}
