package persistence;

import demo.User;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

/**
 * A data access object specifically for User objects.
 */
public class UserDao implements persistence.Dao<User> {

    /**
     * Retrieves and returns the user with the given username.
     *
     * @param param
     * @return
     * @throws SQLException
     */
    @Override
    public User select(String param) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement getUsername = conn.prepareStatement(
                "select * from \"Users\" where \"Username\" = ?");
        getUsername.setString(1, param);
        ResultSet userData = getUsername.executeQuery();
        // parse the results of the query to make a user and return that
        userData.next();
        return new User(userData.getString("Username"), userData.getString("Password"),
                userData.getInt("Role"));
    }

    @Override
    public User selectByID(int id) throws SQLException {
        return null;
    }

    @Override
    public User[] selectAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(User newUser) {

    }

    /**
     * Updates the user with the given data.
     *
     * @param oldUser
     * @param params
     * @return
     * @throws SQLException
     */
    @Override
    public User update(User oldUser, String[] params) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        // okay, turns out you can't make a column name a parameter...
        PreparedStatement getUsername = conn.prepareStatement(
                "update \"Users\" set \"Role\" = ? where \"Username\" = ?");
        // watch out for NumberFormatException!
        getUsername.setInt(1, parseInt(params[0]));
        getUsername.setString(2, oldUser.getUsername());
        getUsername.executeUpdate();

        // gotta change the existing activeUser...hrmph, it's a hack
        //oldUser.setRole(parseInt(params[0]));
        return oldUser;
    }

    @Override
    public void delete(User user) {

    }
}
