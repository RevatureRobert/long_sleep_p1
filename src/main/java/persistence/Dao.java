package persistence;

import java.sql.SQLException;

/**
 * A generic Data Access Object interface.
 *
 * @param <T>
 */
public interface Dao<T> {
    T select(String param) throws SQLException;
    T selectByID(int id) throws SQLException;
    T[] selectAll() throws SQLException;
    void insert(T t) throws SQLException;
    T update(T t, String[] params) throws SQLException;
    void delete(T t) throws SQLException;
}
