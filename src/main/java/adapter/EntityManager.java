package adapter;

// well, idk where to find JPA, so we'll have to do this the hard way
//import javax.persistence.*

import demo.Car;
import demo.User;
import persistence.CarDao;
import persistence.UserDao;

import java.sql.SQLException;

/**
 * The meat of the "ORM." Adapts data objects to persistence objects.
 */
public class EntityManager {
    // represents the different configuration options available
    private int config;
    // in a different system, these could be replaced by whatever dao was needed
    private UserDao udao;
    private CarDao cdao;

    public EntityManager(int config) {
        this.config = config;
        udao = new UserDao();
        cdao = new CarDao();
    }

    public void save(Object o) throws SQLException {
        if (o instanceof User) {
            // TODO: implement insert for users
            udao.insert((User) o);
        } else if (o instanceof Car) {
            cdao.insert((Car) o);
        }
    }

    public Object retrieve(String param) throws SQLException {
        Object result = null;

        // if we got a car ID, find a car; otherwise find a user
        try {
            int id = Integer.parseInt(param);
            result = cdao.selectByID(id);
        } catch (NumberFormatException n) {
            result = udao.select(param);
        }

        return result;
    }

    public void edit(Object o, String[] params) throws SQLException {
        if (o instanceof User) {
            udao.update((User) o, params);
        } else if (o instanceof Car) {
            // TODO: implement update for cars
            cdao.update((Car) o, params);
        }
    }

    public void remove(Object o) throws SQLException {
        if (o instanceof User) {
            // TODO: implement delete for users
            udao.delete((User) o);
        } else if (o instanceof Car) {
            cdao.delete((Car) o);
        }
    }
}
